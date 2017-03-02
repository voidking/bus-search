package com.voidking.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.voidking.service.LineService;

/**
 * Servlet implementation class DeleteLine
 */
@WebServlet("/DeleteLine")
public class DeleteLine extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private LineService lineService = new LineService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteLine() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject jsonObj = null;
		int lineId = Integer.parseInt(request.getParameter("lineId"));
		boolean flag = lineService.deleteLineById(lineId);

		if(flag){			
			jsonObj = new JSONObject("{'code':'0','ext':'success'}");
		}else{
			jsonObj = new JSONObject("{'code':'1','ext':'删除失败'}");
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
