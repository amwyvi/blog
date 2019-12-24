package com.scs.web.blog.service;

import com.scs.web.blog.dao.CommentDao;
import com.scs.web.blog.domain.dto.CommentDto;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.factory.ServiceFactory;
import com.scs.web.blog.util.DataUtil;
import com.scs.web.blog.util.Result;
import org.junit.Test;

import java.sql.SQLException;


public class CommentServiceTest {
    private CommentDao commentDao = DaoFactory.getCommentDaoInstance();
    private CommentService commentService= ServiceFactory.getCommentServiceInstance();
    @Test
    public void insert() throws SQLException {

        CommentDto commentDto = new CommentDto();
        commentDto.setUserId((long) 2);
        commentDto.setArticleId((long) 1);
        commentDto.setContent("非常好");

        commentDao.insert(commentDto);
    }

    @Test
    public void delete() throws SQLException {

        commentDao.delete((long) 17);

    }

    @Test
    public void findArticle() {
        Result result=commentService.findArticle((long)1 );
        System.out.println(result);
    }
}
