package com.dms.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dms.service.service;

@RestController
@RequestMapping("/multiRoom")
public class leaveId {

	@PostMapping("/leaveId")
	public void leaveIdAjax(HttpServletRequest request) {
		
		service service = new service(); 
		HttpSession session = request.getSession();
		String my_id = (String) session.getAttribute("my_id");
		int chat_id = Integer.parseInt(request.getParameter("chat_id"));
		service.leaveId(chat_id, my_id);

	}
}
