package com.scs.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scs.web.blog.domain.dto.UserDto;
import com.scs.web.blog.entity.User;
import com.scs.web.blog.factory.ServiceFactory;
import com.scs.web.blog.listener.MySessionContext;
import com.scs.web.blog.service.UserService;
import com.scs.web.blog.util.HttpUtil;
import com.scs.web.blog.util.Result;
import com.scs.web.blog.util.ResultCode;
import com.scs.web.blog.util.UrlPatten;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author
 * @ClassName UserController
 * @Description 用户控制器
 * @Date 2019/11/9
 * @Version 1.0
 **/
@WebServlet(urlPatterns = {"/api/user", "/api/user/*"})
public class UserController extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService = ServiceFactory.getUserServiceInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI().trim();
        if (UrlPatten.USER.equals(uri)) {
            String page = req.getParameter("page");
            String keywords = req.getParameter("keywords");
            String count = req.getParameter("count");
            if (page != null) {
                HttpUtil.getResponseBody(resp, userService.selectByPage(Integer.parseInt(page), Integer.parseInt(count)));
            } else if (keywords != null) {
                HttpUtil.getResponseBody(resp, userService.selectByKeywords(keywords));
            } else {
                HttpUtil.getResponseBody(resp, userService.getHotUsers());
            }
        } else {
            System.out.println(uri);
            HttpUtil.getResponseBody(resp, userService.getUser(Long.parseLong(HttpUtil.getPathParam(req))));
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI().trim();
        switch (uri) {
            case UrlPatten.USER_SIGN_IN:
                signIn(req, resp);
                break;
            case UrlPatten.USER_SIGN_UP:
                signUp(req, resp);
                break;
            case UrlPatten.USER_CHECK_MOBILE:
                String mobile = req.getParameter("mobile");
                HttpUtil.getResponseBody(resp, userService.checkMobile(mobile));
                break;
            case UrlPatten.USER_DELETE:
                doDelete(req,resp);
                break;
            case UrlPatten.USER_CHANGE:
                doPut(req,resp);
                break;
            default:
        }
    }

    private void signIn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestBody = HttpUtil.getRequestBody(req);
        logger.info("登录用户信息：" + requestBody);
        Gson gson = new GsonBuilder().create();
        UserDto userDto = gson.fromJson(requestBody, UserDto.class);
        //客户端输入的验证码
        String inputCode = userDto.getCode().trim();
        //取得客户端请求头里带来的token
        String sessionId = req.getHeader("Access-Token");
        //从自定义的监听代码中取得之前的session对象
        MySessionContext myc = MySessionContext.getInstance();
        HttpSession session = myc.getSession(sessionId);
        //取得当时存入的验证码
        String correctCode = session.getAttribute("code").toString();
        //忽略大小写比对
        if (inputCode.equalsIgnoreCase(correctCode)) {
            HttpUtil.getResponseBody(resp, userService.signIn(userDto));
            //验证码正确，进入登录业务逻辑调用
        } else {
            //验证码错误，直接将错误信息返回给客户端，不要继续登录流程了
            HttpUtil.getResponseBody(resp, Result.failure(ResultCode.USER_VERIFY_CODE_ERROR));
        }
    }


    private void signUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BufferedReader reader=req.getReader();
        StringBuilder stringBuilder=new StringBuilder();
        String line=null;
        while((line=reader.readLine())!=null){
            stringBuilder.append(line);
        }
        System.out.println(stringBuilder.toString());
        Gson gson=new GsonBuilder().create();
        UserDto user= gson.fromJson(stringBuilder.toString(),UserDto.class);
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        System.out.println(user);
        Result result;
        result = userService.signUp(user);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out =resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url=req.getRequestURI();
        int id = url.lastIndexOf("/");
        String id1 = url.substring(id+1);
        Result result = userService.delete(Long.parseLong(id1));
        PrintWriter out=resp.getWriter();
        Gson gson=new GsonBuilder().create();
        out.print(gson.toJson(result));
        out.close();

    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson=new GsonBuilder().create();
        BufferedReader reader=req.getReader();
        StringBuilder stringBuilder=new StringBuilder();
        String line=null;
        while ((line=reader.readLine())!=null){
            stringBuilder.append(line);
        }
        System.out.println(stringBuilder);
        User user=gson.fromJson(stringBuilder.toString(),User.class);
        Result result=userService.changeUser(user);
        PrintWriter out=resp.getWriter();
        out.print(gson.toJson(result));
        out.close();

    }
}
