package com.dms.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dms.entity.chatLog;
import com.dms.service.service;

@Controller
@RequestMapping("/multiRoom")
public class room {

	@GetMapping("/room")
	public String roomController(Model model, HttpServletRequest request) {
		service service = new service();
		HttpSession session = request.getSession();
		String roomId = request.getParameter("id");
		String my_id = (String) session.getAttribute("my_id");
		List<chatLog> list = service.getChatlog(roomId);
		
		model.addAttribute("roomId", roomId);
		model.addAttribute("my_id", my_id);
		model.addAttribute("list", list);

		return "room";
	}
}
