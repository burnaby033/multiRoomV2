package com.dms.process;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/multiRoom")
public class loginPcs {

	@GetMapping("/loginPcs")
	public void loginPcsRc(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String my_id = request.getParameter("my_id");
		session.setAttribute("my_id", my_id);
		response.sendRedirect("/multiRoom/home");
	}
}
