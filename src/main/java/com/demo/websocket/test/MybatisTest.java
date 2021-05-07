package com.demo.websocket.test;

import com.alibaba.fastjson.JSON;
import com.demo.websocket.modules.user.dao.UserDao;
import com.demo.websocket.modules.user.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author pengnian
 * @version V1.0
 * @date 2020/4/24 9:58
 * @Desc
 */
public class MybatisTest {

    public static void main(String[] args) {
        SqlSessionFactory sqlSessionFactory;

        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis/mybatis-config.xml"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        //开启sqlsession，进入在spring管理的service层，由代理对象开启
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //getMapper在依赖注入UserDao时，创建UserDao代理对象
        UserDao userDao = sqlSession.getMapper(UserDao.class);

        //代理对象获取绑定的java数据，obj.invoke()方法后，执行sql等操作
        User user = userDao.getById(3L);
        System.out.println(JSON.toJSONString(user));
    }

}
