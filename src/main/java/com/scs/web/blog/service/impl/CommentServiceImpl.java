package com.scs.web.blog.service.impl;

import com.scs.web.blog.dao.CommentDao;
import com.scs.web.blog.domain.dto.CommentDto;
import com.scs.web.blog.entity.Comment;
import com.scs.web.blog.entity.Topic;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.service.CommentService;
import com.scs.web.blog.util.Result;
import com.scs.web.blog.util.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author
 * @ClassName CommentServiceImpl
 * @Description TODO
 * @Date 2019/12/10
 * @Version 1.0
 **/
public class CommentServiceImpl implements CommentService {
    private static Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);
    private CommentDao commentDao = DaoFactory.getCommentDaoInstance();
    @Override
    public Result insert(CommentDto commentDto) {
        try {
            commentDto.setCreateTime(LocalDateTime.now());
            commentDao.insert(commentDto);
        } catch (SQLException e) {
            logger.error("新增评论失败");
        }
        return Result.success();
    }

    @Override
    public Result delete(Long id) {
        try {
            commentDao.delete(id);
        } catch (SQLException e) {
            logger.error("删除评论失败");
        }
        return null;
    }

    @Override
    public Result findArticle(Long articleId) {
        List<Comment> commentList = null;
        try {
            commentList = commentDao.findArticle(articleId);
        } catch (SQLException e) {
            logger.error("根据关键字查询专题出现异常");
        }
        if (commentList != null) {
            return Result.success(commentList);
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }
}
