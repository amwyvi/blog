package com.scs.web.blog.dao;

import com.scs.web.blog.domain.dto.CommentDto;
import com.scs.web.blog.entity.Comment;
import com.scs.web.blog.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author hk
 * @ClassName CommentDao
 * @Description dd
 * @Date 2019/12/18
 * @Version 1.0
 **/
public interface CommentDao {
//    User findUserByMobile(String mobile) throws SQLException;
    /**
     *查看当前文章评论
     */
    List<Comment> findArticle(Long articleId) throws SQLException;

    /**
     * 添加评论
     * @param commentDto
     * @throws SQLException
     */
    void insert(CommentDto commentDto) throws SQLException;

    /**
     * 删除品论
     * @param id
     * @throws SQLException
     */
    void delete(Long id) throws SQLException;


}
