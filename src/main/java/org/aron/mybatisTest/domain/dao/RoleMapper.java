package org.aron.mybatisTest.domain.dao;

import org.aron.mybatisTest.domain.pojo.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    List<Role> selectAll();

    int updateByPrimaryKeySelective(Role record);


    int updateByPrimaryKey(Role record);
}