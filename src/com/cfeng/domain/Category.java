package com.cfeng.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter@Getter@ToString
public class Category {
    private Integer cid;
    private String cname;
    private Integer parentid;
}
