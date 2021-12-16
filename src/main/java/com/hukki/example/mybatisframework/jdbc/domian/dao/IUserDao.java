package com.hukki.example.mybatisframework.jdbc.domian.dao;

import com.hukki.example.mybatisframework.jdbc.domian.po.User;

import java.util.List;

/**
 * @author wanghui
 * @date 2021/12/16 7:16 下午
 * @des
 */
public interface IUserDao {

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    List<User> findAll() throws Exception;

    /**
     * 按条件查询
     * @param user
     * @return
     * @throws Exception
     */
    User findByCondition(User user) throws Exception;
}
