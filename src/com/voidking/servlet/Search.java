package com.voidking.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.voidking.db.LineService;
import com.voidking.model.Line;

@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private LineService lineService = new LineService();
    private JSONObject jsonObj = null;
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf8");
		PrintWriter pw = response.getWriter();
		
		String key = request.getParameter("key");
		ArrayList<Line> lineList = lineService.searchLine(key);
		if(lineList.size() > 0){
			jsonObj = new JSONObject("{'code':'0','ext':'success'}");
			jsonObj.put("lineList", lineList);
		}else{
			jsonObj = new JSONObject("{'code':'1','ext':'error'}");
		}
		
		pw.println(jsonObj);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
