package com.scs.web.blog.dao;

import com.scs.web.blog.domain.dto.CommentDto;
import com.scs.web.blog.entity.Comment;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.util.DataUtil;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * @author hk
 * @ClassName CommentDaoTest
 * @Description dd
 * @Date 2019/12/18
 * @Version 1.0
 **/
public class CommentDaoTest {
    private CommentDao commentDao = DaoFactory.getCommentDaoInstance();
    @Test
    public void insert() throws SQLException {

        CommentDto commentDto = new CommentDto();
        commentDto.setUserId((long) 1);
        commentDto.setArticleId((long) 1);
        commentDto.setContent("非常好");

        commentDao.insert(commentDto);
    }

    @Test
    public void delete() throws SQLException {

        commentDao.delete((long) 16);

    }


    @Test
    public void findArticle() throws SQLException {
        List<Comment> comment=commentDao.findArticle((long) 1);
        System.out.println(comment);
    }
}
