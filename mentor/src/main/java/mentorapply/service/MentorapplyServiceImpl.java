package mentorapply.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mentorapply.dao.MentorapplyDAO;

@Service
public class MentorapplyServiceImpl implements MentorapplyService{
	@Autowired 
	private MentorapplyDAO mentorapplyDAO;

	@Override
	public void mentorapplyWrite(Map<String, String> map) {
		mentorapplyDAO.mentorapplyWrite(map);
	}
	
}
