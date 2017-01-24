package com.voidking.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class Stop
 */
@WebServlet("/Stop")
public class Stop extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Stop() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//freemarker配置  
        Configuration config=new Configuration();
        ServletContext context = request.getServletContext();
        config.setServletContextForTemplateLoading(context, "template");
        
        //加载模板文件  
        Template template=config.getTemplate("stop.ftl"); 
        
        //创建数据模型  
        Map<String,String> map=new HashMap<String,String>();  
        map.put("basePath", request.getContextPath());
        String busName = request.getParameter("busName");
        String firstStop = request.getParameter("firstStop");
        map.put("busName", busName);
        map.put("firstStop", firstStop);
        
        response.setCharacterEncoding("utf8");
        PrintWriter out = response.getWriter();
        try {
            // 输出模板到页面上
            template.process(map, out);
            out.flush();
            out.close();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
