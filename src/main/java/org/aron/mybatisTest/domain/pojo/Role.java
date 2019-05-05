package org.aron.mybatisTest.domain.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter @Setter
public class Role {
    private Integer id;
    private String name;
    private Long uid;
    private List<User> users;
}