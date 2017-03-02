package com.voidking.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.voidking.model.Admin;
import com.voidking.service.AdminService;
import com.voidking.service.LineService;

/**
 * Servlet implementation class Login
 */
@WebServlet("/AddLine")
public class AddLine extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LineService lineService = new LineService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddLine() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject jsonObj = null;
		String busName = request.getParameter("busName");
		String fullName = request.getParameter("fullName");
		String firstStop = request.getParameter("firstStop");
		String lastStop = request.getParameter("lastStop");
		
		if(busName == null || fullName == null || firstStop==null || lastStop==null || "".equals(busName) || "".equals(fullName) || "".equals(firstStop) || "".equals(lastStop)){
			jsonObj = new JSONObject("{'code':'1','ext':'参数不能为空'}");
		}else{
			boolean flag = lineService.creatLine(busName, fullName, firstStop, lastStop);
			if(flag){
				jsonObj = new JSONObject("{'code':'0','ext':'success'}");
			}else{
				jsonObj = new JSONObject("{'code':'2','ext':'写入数据库失败'}");
			}
		}
		
		response.setCharacterEncoding("utf8");
		PrintWriter pw = response.getWriter();
		pw.println(jsonObj);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
