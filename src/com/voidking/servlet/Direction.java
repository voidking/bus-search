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

import com.voidking.service.LineService;
import com.voidking.model.Line;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


@WebServlet("/Direction")
public class Direction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LineService lineService = new LineService();
    public Direction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//freemarker配置  
        Configuration config=new Configuration();
        ServletContext context = request.getServletContext();
        config.setServletContextForTemplateLoading(context, "template");
        
        //加载模板文件  
        Template template=config.getTemplate("direction.ftl"); 
        
        //创建数据模型  
        Map<String,Object> map=new HashMap<String,Object>();  
        map.put("basePath", request.getContextPath());
        String busName = request.getParameter("busName");
        Line line = lineService.getLine(busName);
        map.put("line", line);
           
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
