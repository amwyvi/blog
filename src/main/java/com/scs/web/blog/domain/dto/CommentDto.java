package com.scs.web.blog.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author cyq-xn
 * @ClassName CommentDto
 * @Description TODO
 * @Date 2019/12/17
 * @Version 1.0
 **/
@Data
public class CommentDto {
    private Long id;
    private Long userId;
    private Long articleId;
    private String content;
    private LocalDateTime createTime;
}
