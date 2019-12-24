package com.scs.web.blog.dao.impl;

import com.scs.web.blog.dao.CommentDao;
import com.scs.web.blog.domain.dto.CommentDto;
import com.scs.web.blog.domain.vo.CommentVo;
import com.scs.web.blog.entity.Comment;
import com.scs.web.blog.entity.User;
import com.scs.web.blog.util.BeanHandler;
import com.scs.web.blog.util.DataUtil;
import com.scs.web.blog.util.DbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @ClassName CommentDaoImpl
 * @Description TODO
 * @Date 2019/12/9
 * @Version 1.0
 **/

public class CommentDaoImpl implements CommentDao {
    @Override
    public List<Comment> findArticle(Long articleId) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_comment WHERE article_id = ? ORDER BY id ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1, articleId);
        ResultSet rs = pst.executeQuery();
        List<Comment> commentList = BeanHandler.convertComment(rs);
        DbUtil.close(connection,pst,rs);
        return commentList;
    }



    @Override
    public void insert(CommentDto commentDto) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "INSERT INTO t_comment (id,user_id,article_id,content,create_time) VALUES (null,?,?,?,?) ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1, commentDto.getUserId());
        pst.setLong(2, commentDto.getArticleId());
        pst.setString(3,commentDto.getContent());
        pst.setObject(4, DataUtil.getNowTime());

        pst.executeUpdate();
        DbUtil.close(connection, pst);

    }


    @Override
    public void delete(Long id) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "DELETE FROM t_comment WHERE id = ?  ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1,id);
        pst.executeUpdate();
        DbUtil.close(connection, pst);
    }
}
