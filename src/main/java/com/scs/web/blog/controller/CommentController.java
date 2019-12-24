package com.scs.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scs.web.blog.domain.dto.CommentDto;
import com.scs.web.blog.factory.ServiceFactory;
import com.scs.web.blog.service.CommentService;
import com.scs.web.blog.util.Result;
import com.scs.web.blog.util.UrlPatten;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author hk
 * @ClassName CommentController
 * @Description dd
 * @Date 2019/12/18
 * @Version 1.0
 **/
@WebServlet(urlPatterns = {"/api/comment","/api/comment/*"})
public class CommentController extends HttpServlet {
    private CommentService commentService= ServiceFactory.getCommentServiceInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BufferedReader reader=req.getReader();
        StringBuilder stringBuilder=new StringBuilder();
        String line=null;
        while((line=reader.readLine())!=null){
            stringBuilder.append(line);
        }
        System.out.println(stringBuilder.toString());
        Gson gson=new GsonBuilder().create();
        CommentDto commentDto= gson.fromJson(stringBuilder.toString(),CommentDto.class);
        Result result;
        result =commentService .insert(commentDto);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out =resp.getWriter();
        out.print(gson.toJson(result));
        out.close();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI().trim();
        if ("/api/comment".equals(uri)) {
            String id = req.getParameter("id");
            if (id != null)
                getComment(Long.parseLong(id), resp);
        }
    }
    private void getComment(long article_id, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new GsonBuilder().create();
        Result result = commentService.findArticle(article_id);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url=req.getRequestURI();
        int id= url.lastIndexOf("/");
        String id1=url.substring(id+1);
        Result result=commentService.delete(Long.parseLong(id1));
        System.out.println(id1);
        PrintWriter out=resp.getWriter();
        Gson gson=new GsonBuilder().create();
        out.print(gson.toJson(result));
        out.close();

    }

}
