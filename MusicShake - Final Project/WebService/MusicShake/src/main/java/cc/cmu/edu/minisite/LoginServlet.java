package cc.cmu.edu.minisite;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.cmu.edu.minisite.services.RequestHandler;
import cc.cmu.edu.minisite.util.Logger;

public class LoginServlet extends HttpServlet {
	private static final int SIGNIN = 0;
	private static final int SIGNUP = 1;
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Integer login = Integer.parseInt(request.getParameter("login"));
		RequestHandler handler = new RequestHandler();

		try {
			switch (login) {
			case SIGNIN:
				if (handler.signin(username, password)) {
					out.write(SUCCESS);
				} else {
					out.write(FAIL);
				}
				break;
			case SIGNUP:
				if (handler.signup(username, password)) {
					out.write(SUCCESS);
				} else {
					out.write(FAIL);
				}
				break;
			default:
				out.write(FAIL);
				break;
			}
		} catch (Exception e) {
			Logger.log(e.toString(), 1);
			out.write(FAIL);
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
