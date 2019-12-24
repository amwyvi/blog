package com.scs.web.blog.util;

import java.security.PublicKey;

/**
 * @ClassName UrlPatten
 * @Description UrlPatten字符串常量
 * @Author
 * @Date 2019/11/27
 **/
public class UrlPatten {
    public static final String USER = "/api/user";
    public static final String USER_SUB = "/api/user/*";
    public static final String USER_SIGN_IN = "/api/user/sign-in";
    public static final String USER_SIGN_UP = "/api/user/sign-up";
    public static final String USER_CHECK_MOBILE = "/api/user/check";
    public static final String USER_DELETE="/api/user/delete";
    public static final String USER_CHANGE="/api/user/change";


    public static final String TOPIC = "/api/topic";
    public static final String TOPIC_SUB = "/api/topic/*";
    public static final String ARTICLE = "/api/article";
    public static final String ARTICLE_SUB = "/api/article/*";
    public static final String ARTICLE_DELETE="/api/article/delete";

    public static final String WEATHER = "/api/weather";


}
