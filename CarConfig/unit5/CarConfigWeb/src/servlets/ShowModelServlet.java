package servlets; // Always use packages. Never use default package.

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

/**
 * show the available models from the linkedhashmap of server
 * @author Tianqi Wen (tianqiw)
 *
 */
@WebServlet("/show")
public class ShowModelServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext context = getServletContext();
		RequestDispatcher dispather = context
				.getRequestDispatcher("/showModel.jsp");
		dispather.forward(request, response);
	}
}
