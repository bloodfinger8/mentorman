package noticeboard.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import noticeboard.bean.BoardPaging;
import noticeboard.bean.NoticeboardDTO;
import noticeboard.service.NoticeboardService;

@Controller
@RequestMapping("/noticeboard")
public class NoticeboardController {
	@Autowired
	private NoticeboardService noticeboardService;
	@Autowired
	private BoardPaging boardPaging;
	/**
	 * 
	 * @Title : 공지사항 글 작성
	 * @Author : kujun95, @Date : 2019. 11. 2.
	 */
	@RequestMapping(value = "noticeboardWriteForm", method = RequestMethod.GET)
	public ModelAndView noticeBoardWriteForm() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("display","/noticeboard/noticeboardWriteForm.jsp");
		mav.setViewName("/main/index");
		return mav;
	}
	/**
	 * 
	 * @Title : 공지사항 글 DB저장
	 * @Author : kujun95, @Date : 2019. 11. 2.
	 */
	 //HttpSession session 나중에 관리자 email 받아서 써야됨
	@RequestMapping(value = "noticeboardWrite", method = RequestMethod.POST)
	@ResponseBody
	public void noticeboardWrite(@RequestParam Map<String,String> map) {
		System.out.println(map);
		
		noticeboardService.noticeboardWrite(map); 
	}
	
	@RequestMapping(value = "noticeboardImage", method = RequestMethod.POST)
	@ResponseBody
	public String noticeboardImage(@RequestParam("file") MultipartFile file) {
		String filePath = "C:/github/MentorMan/mentor/src/main/webapp/storage";
		String fileName = file.getOriginalFilename();
		File files = new File(filePath, fileName);
		System.out.println(fileName);
		try {
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(files));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(fileName);
		return fileName;
	}
	
	
	/**
	 * @Title : List 창
	 * @Author : kujun95, @Date : 2019. 11. 2.
	 */
	@RequestMapping(value = "noticeboardList", method = RequestMethod.GET)
	public String boardList(@RequestParam(required = false, defaultValue = "1") String pg, Model model) {
		model.addAttribute("pg", pg);
		model.addAttribute("display","/noticeboard/noticeboardList.jsp");
		return "/main/index";
	}
	
	/**
	 * @Title : List의 값 가져오기 및 페이징 처리
	 * @Author : kujun95, @Date : 2019. 11. 2.
	 */
	//공지글은 로그인 하지 않아도 확인이 가능하게 하는가? 아니면 로그인 하면 볼 수 있게하는지 논의하기
	@RequestMapping(value = "getBoardList", method = RequestMethod.POST)
	public ModelAndView getBoardList(@RequestParam(required = false, defaultValue = "1") String pg) {
		System.out.println(pg);
		int endNum = Integer.parseInt(pg)*10;
		int startNum = endNum-9;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("endNum", endNum);
		map.put("startNum", startNum);
		List<NoticeboardDTO> list = noticeboardService.getBoardList(map);
		
		int totalA = noticeboardService.getTotalA();
		boardPaging.setCurrentPage(Integer.parseInt(pg));
		boardPaging.setPageBlock(3);
		boardPaging.setPageSize(10);
		boardPaging.setTotalA(totalA);
		boardPaging.makePagingHTML();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardPaging", boardPaging);
		mav.addObject("totalA", totalA);
		mav.addObject("startNum", startNum);
		mav.addObject("endNum", endNum);
		mav.addObject("list", list);
		mav.setViewName("jsonView");
		return mav;
	}
	/**
	 * @Title : List 글 삭제
	 * @Author : kujun95, @Date : 2019. 11. 5.
	 */
	@RequestMapping(value = "noticeboardDelete", method = RequestMethod.POST)
	public String noticeboardDelete(@RequestParam String[] check, Model model) {
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("array", check);
		noticeboardService.noticeboardDelete(map);
		model.addAttribute("display","/noticeboard/noticeboardDelete.jsp");
		return "/main/index";
	}
	
	/**
	 * @Title : 공지글 view
	 * @Author : kujun95, @Date : 2019. 11. 5.
	 */
	@RequestMapping(value = "noticeboardView", method = RequestMethod.GET)
	public String noticeboardView(@RequestParam String seq, @RequestParam String pg, Model model) {
		NoticeboardDTO noticeboardDTO = noticeboardService.getNoticeboardView(Integer.parseInt(seq));
		System.out.println(noticeboardDTO);
		model.addAttribute("pg", pg);
		model.addAttribute("noticeboardDTO", noticeboardDTO);
		model.addAttribute("display", "/noticeboard/noticeboardView.jsp");
		return "/main/index";
	}
	
	/**
	 * @Title : List 검색
	 * @Author : kujun95, @Date : 2019. 11. 6.
	 */
	@RequestMapping(value = "noticeboardSearch", method = RequestMethod.POST)
	public ModelAndView noticeboardSearch(@RequestParam Map<String,String> map) {
		int endNum = Integer.parseInt(map.get("pg"))*10;
		int startNum = endNum-9;
		
		map.put("startNum", startNum+"");
		map.put("endNum", endNum+"");
		
		List<NoticeboardDTO> list = noticeboardService.noticeboardSearch(map);
		
		System.out.println(list);
		int totalA = noticeboardService.getSearchTotalA(map);
		boardPaging.setCurrentPage(Integer.parseInt(map.get("pg")));
		boardPaging.setPageBlock(3);
		boardPaging.setPageSize(10);
		boardPaging.setTotalA(totalA);
		System.out.println(totalA);
		boardPaging.makeSelectPagingHTML();
		ModelAndView mav = new ModelAndView();
		mav.addObject("searchText",map.get("searchText"));
		mav.addObject("totalA", totalA);
		mav.addObject("startNum", startNum);
		mav.addObject("endNum", endNum);
		mav.addObject("list", list);
		mav.addObject("boardPaging",boardPaging);
		mav.setViewName("jsonView");
		return mav;
	}
	/**
	 * @Title : 공지글 수정 Form
	 * @Author : kujun95, @Date : 2019. 11. 6.
	 */
	@RequestMapping(value = "noticeboardUpdateForm", method = RequestMethod.GET)
	public String noticeboardUpdateForm(@RequestParam String seq ,@RequestParam String pg, Model model) {
		NoticeboardDTO noticeboardDTO = noticeboardService.getNoticeboardView(Integer.parseInt(seq));
		
		model.addAttribute("noticeboardDTO",noticeboardDTO);
		model.addAttribute("pg", pg);
		model.addAttribute("seq", seq);
		model.addAttribute("display", "/noticeboard/noticeboardUpdateForm.jsp");
		return "/main/index";
	}
	
	@RequestMapping(value = "noticeboardUpdate", method = RequestMethod.POST)
	@ResponseBody
	public void noticeboardUpdate(@RequestParam Map<String,String> map) {
		noticeboardService.noticeboardUpdate(map);
	}
	
}
