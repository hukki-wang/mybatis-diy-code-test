package com.hukki.example.mybatisframework.jdbc.domian.dao;

import com.hukki.example.mybatisframework.jdbc.domian.core.io.Resources;
import com.hukki.example.mybatisframework.jdbc.domian.core.io.SqlSessionFactoryBuilder;
import com.hukki.example.mybatisframework.jdbc.domian.factory.SqlSession;
import com.hukki.example.mybatisframework.jdbc.domian.factory.SqlSessionFactory;
import com.hukki.example.mybatisframework.jdbc.domian.po.User;

import java.io.InputStream;
import java.util.List;

/**
 * @author wanghui
 * @date 2021/12/16 7:18 下午
 * @des
 */
public class UserDaoImpl implements IUserDao{

    @Override
    public List<User> findAll() throws Exception {
        InputStream resourceAsStream =
                Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new
                SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> userList = sqlSession.selectList("user.selectList");
        return userList;
    }

    @Override
    public User findByCondition(User user) throws Exception {
        InputStream resourceAsStream =
                Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new
                SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User result = sqlSession.selectOne("user.selectOne", user);
        return result;
    }
}
