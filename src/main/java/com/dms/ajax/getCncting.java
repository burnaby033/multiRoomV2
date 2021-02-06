package com.dms.ajax;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dms.chat.ChatRoomRepository;

@RestController
@RequestMapping("/multiRoom")
public class getCncting {

	@PostMapping("/getCncting")
	public int getLiveCntAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String roomId = request.getParameter("roomId");
		int size = ChatRoomRepository.chatRoomMap.get(roomId).getSessions().size();
		return size;
	}
	
}
