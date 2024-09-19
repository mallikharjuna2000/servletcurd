package com.codegnan.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * Servlet implementation class UpdateServlet2
 */
@WebServlet("/updateservlet2")
public class UpdateServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);
		String name = request.getParameter("name");
		String dept = request.getParameter("dept");
		Employee e = new Employee();
		e.setId(id);
		e.setName(name);
		e.setDept(dept);
		int status = EmpDBHandler.update(e);
		if (status > 0) {
			out.println("Record updated succesfully...");
			response.sendRedirect("viewservlet");
		} else {
			out.println("Sorry! unable to update record");
		}
		out.close();
	}

}
