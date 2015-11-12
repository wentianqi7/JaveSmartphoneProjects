package servlets; // Always use packages. Never use default package.

import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

/**
 * get the optionsets of chosen model from server and display in a selectable
 * form using jsp
 * 
 * @author Tianqi Wen (tianqiw)
 * 
 */
@WebServlet("/optionset")
public class GetOptionSetServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		// get the chosen model
		String model = request.getParameter("model");
		// get the automobile of selected model from server
		BuildClientCommand command = new BuildClientCommand();
		command.openConnection();
		try {
			command.getSelectedModel(model.replaceAll("@", " "));
		} catch (Exception e) {
			command.closeSession();
			return;
		}
		command.closeSession();

		// get optionsets information aboutn the selected model
		String make = command.getMake();
		ArrayList<String> opsetNames = command.getOpsetNames();
		HttpSession session = request.getSession();
		session.setAttribute("command", command);

		// write the options into a form that user can choose from
		out.write("<DD>Make/Model:</DD><DD>" + make + " "
				+ model.replaceAll("@", " ") + "</DD>");
		out.write("<form method=\"post\" action=\"showUserChoice.jsp\">");
		out.write("<input type=\"hidden\" name=\"model\" value=\"" + make + "@"
				+ model + "\">");
		for (String opsetName : opsetNames) {
			ArrayList<String> optionNames = command.getOptionNames(opsetName);
			out.write("<DD>" + opsetName + "<select name="
					+ opsetName.replaceAll(" ", "@")
					+ " style=\"width: 300px; height: 24px; font-size:16px\"");
			out.write("<option value=\"choice\">Please select one");

			for (String optionName : optionNames) {
				out.write("<option value=" + optionName.replaceAll(" ", "@")
						+ ">" + optionName);
			}

			out.write("</select></DD>");
		}
		out.write("<input type=\"hidden\" name=\"choice\" id=\"choice\"><DD>"
				+ "<input type=\"submit\" value=\"click\" nsame=\"choice\" id=\"choice\">"
				+ "</DD></DL></form>");
	}
}
