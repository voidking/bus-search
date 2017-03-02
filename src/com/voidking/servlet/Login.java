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

/**
 * Servlet implementation class Login
 */
@WebServlet("/Admin/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject jsonObj = null;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Admin admin = adminService.login(username, password);
		if(admin != null){
			//使用request对象的getSession()获取session，如果session不存在则创建一个
			HttpSession session = request.getSession();
			//将数据存储到session中
			session.setAttribute("admin", admin);
			//获取session的Id
			//String sessionId = session.getId();
			//判断session是不是新创建的
			if (session.isNew()) {
				//response.getWriter().print("session创建成功，session的id是："+sessionId);
			}else {
				//response.getWriter().print("服务器已经存在该session了，session的id是："+sessionId);
			}
			
			jsonObj = new JSONObject("{'code':'0','ext':'success'}");
		}else{
			jsonObj = new JSONObject("{'code':'1','ext':'用户名或密码错误'}");
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
