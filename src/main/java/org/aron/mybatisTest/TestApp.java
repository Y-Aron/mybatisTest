package org.aron.mybatisTest;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.aron.mybatisTest.domain.dao.RoleMapper;
import org.aron.mybatisTest.domain.dao.UserMapper;
import org.aron.mybatisTest.domain.pojo.Role;
import org.aron.mybatisTest.domain.pojo.User;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.List;

@Slf4j
public class TestApp {

    private SqlSession sqlSession;

    @Before
    public void init() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        this.sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    public void testNamespaceId() {
        // selectList(namespace.id)
        List<User> userList = this.sqlSession.selectList("org.aron.mybatisTest.domain.dao.UserMapper.selectAll");
        System.out.println(userList);
    }

    @Test
    public void testMapper() {
        UserMapper mapper = this.sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.selectAll();
        System.out.println(users);
    }

    @Test
    public void testInsert() {
        UserMapper mapper = this.sqlSession.getMapper(UserMapper.class);
        int row = mapper.insert(new User() {{
            setName("name10");
//            setDesc("desc10");
        }});
        this.sqlSession.commit();
        System.out.println(row);
    }

    @Test
    public void test() {
        RoleMapper mapper = this.sqlSession.getMapper(RoleMapper.class);
        List<Role> roles = mapper.selectAll();
        System.out.println(roles);
        List<Role> roles1 = mapper.selectAll();
        System.out.println(roles1);
    }

    @Test
    public void testCache() {
        //1、获取到XML文件位置的URL
        URL myUrl = getClass().getResource("/org/aron/mybatisTest/domain/dao/ehcache.xml");
        //2、实例化一个XmlConfiguration，将XML文件URL传递给它
        Configuration xmlConfig = new XmlConfiguration(myUrl);
        //3、使用静态的org.ehcache.config.builders.CacheManagerBuilder.newCacheManager(org.ehcache.config.Configuration)
        //使用XmlConfiguration的Configuration创建你的CacheManager实例。
        CacheManager myCacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
        myCacheManager.init();
        Cache<String, String> foo = myCacheManager.getCache("mybatisCache",String.class,String.class);
//        Cache<Number, String> bar = myCacheManager.getCache("bar",Number.class,String.class);
//        Cache<Long, String> simpleCache = myCacheManager.getCache("simpleCache",Long.class,String.class);
        foo.put("1","zhangsan");
        foo.put("2","lisi");
        log.info("foo,姓名：{}",foo.get("1"));

//        bar.put(1.1,"zhangsan");
//        bar.put(2.2,"lisi");
//        log.info("bar,姓名：{}",bar.get(1.1));
//
//        simpleCache.put(1L,"zhangsan");
//        simpleCache.put(2L,"lisi");
//        log.info("simpleCache,姓名：{}",simpleCache.get(1L));

        myCacheManager.close();
    }
}
