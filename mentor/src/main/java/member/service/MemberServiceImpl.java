package member.service;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.bean.MemberDTO;
import member.dao.MemberDAO;
import mentor.bean.MentorDTO;
/**
 * @Title : 회원가입 Service.
 * @author : ginkgo1928
 * @date : 2019. 11. 5.
 */
@Service(value="memberService")
public class MemberServiceImpl implements MemberService {
	@Autowired 
	private MemberDAO memberDAO;
	
	/** @Title : 닉네임 중복확인.
	 * @author : ginkgo1928 @date : 2019. 11. 5.*/
	@Override
	public MemberDTO writeNicknamecheck(String member_nickname) {
		return memberDAO.writeNicknamecheck(member_nickname);
	}
	
	/** @Title : 이메일 중복확인
	 * @author : ginkgo1928  @date : 2019. 11. 5.*/
	@Override
	public MemberDTO writeEmailCheck(String member_email) {
		return memberDAO.writeEmailCheck(member_email);
	}
	
	/* 회원가입 완료 */
	@Override
	public void write(Map<String, String> map) {
		memberDAO.write(map);
	}
	
	/* 로그인 */
	@Override
	public MemberDTO login(Map<String, String> map) {
		return memberDAO.login(map);
	}
	/* 나의 질문 답변 */
	@Override
	public List<MentorDTO> getQandA(Map<String, String> map) {
		return memberDAO.getQandA(map);
	}
	
	/* 비밀번호 찾기 */
	@Override
	public MemberDTO setmemberpwd(Map<String, String> map) {
		return memberDAO.setsetmemberpwd(map);
	}
	
	/** @Title : 이메일 인증을 하고 새로운 비밀번호로 변경
	 * @author : ginkgo1928
	 * @date : 2019. 11. 13. */
	@Override
	public MemberDTO newPwdCommit(Map<String, String> map) {
		return memberDAO.newPwdCommit(map);
	}
	
	/**
	 * Q&A페이징
	 */
	@Override
	public int getTotalA(String member_email) {
		return memberDAO.getTotalA(member_email);
	}
	
	/**
	 * Q&A 멘토 정도 및 질문 내용
	 */
	@Override
	public MentorDTO getMentor_info(Map<String, String> map) {
		return memberDAO.getMentor_info(map);
	}

	@Override
	public List<MentorDTO> getMentoring_type(Map<String, String[]> arrayMap) {
		return memberDAO.getMentoring_type(arrayMap);
	}

	@Override
	public void questionDelete(int question_seq) {
		memberDAO.questionDelete(question_seq);
	}
}

	

	


	

	