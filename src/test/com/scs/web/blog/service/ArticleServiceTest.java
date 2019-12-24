package com.scs.web.blog.service;

import com.scs.web.blog.entity.Article;
import com.scs.web.blog.factory.ServiceFactory;
import com.scs.web.blog.util.Result;
import org.junit.Test;

import javax.servlet.ServletOutputStream;
import java.net.SocketOption;
import java.time.LocalDateTime;

public class ArticleServiceTest {
    private ArticleService articleService = ServiceFactory.getArticleServiceInstance();

    @Test
    public void getHotArticles() {
       Result result =  articleService.getHotArticles();
        System.out.println(result.getData());
    }

    @Test
    public void getPageArticles() {

    }

    @Test
    public void getArticle() {

    }

    @Test
    public void delete() {
        articleService.delete((long) 104);
    }

    @Test
    public void insert() {
        Result result = new Result();
        Article article = new Article();
        article.setUserId((long)4);
        article.setTitle("lh");
        article.setSummary("lhxgs");
        article.setThumbnail("https://upload-images.jianshu.io/upload_images/3097674-ef123e711a6ffa5b.jpg");
        article.setContent("");
        article.setLikes(2);
        article.setComments(6);
        result=articleService.insert(article);
        System.out.println(result.toString());
    }


    @Test
    public void changeArticle() {
        Result result = new Result();

        Article article = new Article();

        article.setTopicId((long)5);
        article.setTitle("lh");
        article.setSummary("lhxgs");
        article.setThumbnail("https://upload-images.jianshu.io/upload_images/3097674-ef123e711a6ffa5b.jpg");
        article.setContent("null");
        article.setId((long)90);
        result= articleService.changeArticle(article);
        System.out.println(result.toString());
    }
}