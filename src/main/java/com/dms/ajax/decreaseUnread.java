package com.dms.ajax;

import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dms.service.service;

@RestController
@RequestMapping("/multiRoom")
public class decreaseUnread {

	@PostMapping("/decreaseUnread")
	public void decreaseUnreadAjax(Model model, HttpServletRequest request) {
		int chat_id  = Integer.parseInt(request.getParameter("chat_id"));
		service service = new  service();
		service.decreaseUnread(chat_id);		
	}
}
