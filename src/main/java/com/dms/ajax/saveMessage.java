package com.dms.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dms.service.service;

@RestController
@RequestMapping("/multiRoom")
public class saveMessage {

	@PostMapping("/saveMessage")
	public void saveMessageAjax(Model model, HttpServletRequest request) {
		
		service service = new service();
		HttpSession session = request.getSession();
		
		String my_id = (String) session.getAttribute("my_id");
		String message = request.getParameter("message");
		String roomId = request.getParameter("roomId");
		int chatId = Integer.parseInt(request.getParameter("chat_id"));
		int size = Integer.parseInt(request.getParameter("size"));

		service.saveMessage(my_id, message, roomId, chatId, size);					
		
	}
}
