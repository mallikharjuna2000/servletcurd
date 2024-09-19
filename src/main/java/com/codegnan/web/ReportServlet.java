package com.codegnan.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codegnan.beans.Employee;
import com.codegnan.dbhandler.EmpDBHandler;

/**
 * Servlet implementation class ReportServlet
 */
@WebServlet("/reportservlet")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String dept = request.getParameter("dept");
		Employee e = new Employee();
		e.setName(name);
		e.setDept(dept);
		int status = EmpDBHandler.save(e);
		if (status > 0) {
			out.print("<p>Record saved successfully!</p>");
			request.getRequestDispatcher("index.html").include(request, response);
		} else {
			out.println("Sorry! unable to save record");
		}
		out.close();
	}
}
