package com.jlstest.dao;

import com.jlstest.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @className: UserDao
 * @Description: TODO
 * @author: JLS
 * @date: 2023/6/28 14:39
 */
@Mapper
public interface UserDao {

    /**
     * 根据用户名获取用户信息
     */
    User getByUsername(String userName);

}
