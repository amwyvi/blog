package com.scs.web.blog.dao;

import com.scs.web.blog.domain.vo.ArticleVo;
import com.scs.web.blog.entity.Article;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.util.SpiderUtil;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class ArticleDaoTest {

    private ArticleDao articleDao = DaoFactory.getArticleDaoInstance();

    @Test
    public void batchInsert() throws SQLException {
        articleDao.batchInsert(SpiderUtil.getArticles());
    }

    @Test
    public void selectHotArticles() throws SQLException {
        List<ArticleVo> articleVoList = articleDao.selectHotArticles();
        System.out.println(articleVoList.size());
    }

    @Test
    public void getArticle() throws SQLException {
        ArticleVo article = articleDao.getArticle(1);
        System.out.println(article);
    }

    @Test
    public void selectByKeywords() throws SQLException {
        List<ArticleVo> articleVoList = articleDao.selectByKeywords("微");
        System.out.println(articleVoList.size());
    }

    @Test
    public void selectByPage() throws SQLException {
        List<ArticleVo> articleVoList = articleDao.selectByPage(1, 10);
        articleVoList.forEach(System.out::println);
    }

    @Test
    public void deleteById() throws SQLException {
        articleDao.deleteById((long) 105);
    }


    @Test
    public void insert() throws SQLException {
        Article article = new Article();
        article.setUserId((long)4);
        article.setTitle("lh");
        article.setSummary("lhxgs");
        article.setThumbnail("https://upload-images.jianshu.io/upload_images/3097674-ef123e711a6ffa5b.jpg");
        article.setContent("");
        article.setLikes(2);
        article.setComments(2);

        articleDao.insert(article);
    }

    @Test
    public void changeArticle() throws SQLException {
        ArticleVo article = articleDao.getArticle(90);
        article.getArticle().setTopicId((long)5);
        article.getArticle().setTitle("mkdn");
        article.getArticle().setSummary("123");
        article.getArticle().setThumbnail("");
        article.getArticle().setContent("345");
        articleDao.changeArticle(article.getArticle());
    }
}