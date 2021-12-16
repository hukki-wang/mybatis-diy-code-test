package com.hukki.example.mybatisframework.jdbc;

import com.hukki.example.mybatisframework.jdbc.domian.Admin;
import com.hukki.example.mybatisframework.jdbc.domian.core.io.Resources;
import com.hukki.example.mybatisframework.jdbc.domian.core.io.SqlSessionFactoryBuilder;
import com.hukki.example.mybatisframework.jdbc.domian.dao.IUserDao;
import com.hukki.example.mybatisframework.jdbc.domian.factory.SqlSession;
import com.hukki.example.mybatisframework.jdbc.domian.factory.SqlSessionFactory;
import com.hukki.example.mybatisframework.jdbc.domian.po.User;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author wanghui
 * @date 2021/12/15 2:51 下午
 * @des
 */
public class Test {


    @org.junit.Test
    public void test() throws Exception{
        Admin admin = new Admin();
        String url = "jdbc:mysql://172.16.116.120:3306/my_jdbc";
        String user = "root";
        String passwd = "123456";
        //加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //创建链接
        Connection connection = DriverManager.getConnection(url,user,passwd);

        String sql = "select aid,password,lastdate,flag,status from admin";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            admin.setAid(resultSet.getString(1));
            admin.setPassword(resultSet.getString(2));
            admin.setLastDate(resultSet.getTimestamp(3));
            admin.setFlag(resultSet.getInt(4));
            admin.setStatus(resultSet.getInt(5));
            System.out.printf("result:"+admin.toString());
        }

        if (resultSet != null){
            resultSet.close();
        }

        if (preparedStatement != null){
            preparedStatement.close();
        }

        if (connection != null){
            connection.close();
        }
    }

    @org.junit.Test
    public void test_mybatis() throws Exception{
        InputStream resourceAsStream =
                Resources.getResourceAsStream("sqlMapConfig.xml");
        new SqlSessionFactoryBuilder().build(resourceAsStream);
    }


    @org.junit.Test
    public void test_persistence() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = factory.openSession();

        User user = new User();
        user.setId(1);
        user.setUsername("lwq");
        User result = sqlSession.selectOne("user.selectOne",user);
        System.out.printf("result:"+result);
    }


    @org.junit.Test
    public void test_dao() throws Exception {
        InputStream resourceAsStream =
                Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new
                SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User condition = new User();
        condition.setId(1);
        condition.setUsername("lwq");
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        List<User> userList = userDao.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
        System.out.println("*****************查询单个********************");
        User user = userDao.findByCondition(condition);
        System.out.println(user);
    }
}
