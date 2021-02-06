package com.dms.ajax;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dms.entity.whoRead;
import com.dms.service.service;

@RestController
@RequestMapping("/multiRoom")
public class getWhoRead {
	
	@PostMapping("/getWhoRead")
	public List<whoRead> saveAjax(Model model, HttpServletRequest request) {		
		service service = new service();
		String roomId = request.getParameter("roomId");

		List<whoRead> list = service.getWhoRead(roomId);			
		return list;
	}
	
}
