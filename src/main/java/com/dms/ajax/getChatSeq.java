package com.dms.ajax;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dms.service.service;

@RestController
@RequestMapping("/multiRoom")
public class getChatSeq {
	
	@PostMapping("/getChatSeq")
	public int getChatSeqAjax(HttpServletResponse response) throws IOException {
		service service = new service(); 
		int chatSeq = service.getChatSeq();
		return chatSeq;
	}
}
