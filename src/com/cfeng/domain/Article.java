package com.cfeng.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter@Getter@ToString
public class Article {
    private Integer article_id;
    private String article_title;
    private Long article_time;
    private String article_content;
    private String article_pic;
    private String article_desc;
    private String article_label;
    private Category category;
    private User user;
    private Integer article_type;
}
