package com.scs.web.blog.service;


import com.scs.web.blog.domain.dto.CommentDto;
import com.scs.web.blog.util.Result;

/**
 * @author
 * @ClassName CommentService
 * @Description TODO
 * @Date 2019/12/10
 * @Version 1.0
 **/
public interface CommentService {
    /**
     * 添加评论
     * @param commentDto
     * @return
     */
    Result insert(CommentDto commentDto);

    /**
     * 删除评论
     * @param id
     * @return
     */
    Result delete(Long id);

    /**
     * 查看评论
     */
    Result findArticle(Long articleId);
}
