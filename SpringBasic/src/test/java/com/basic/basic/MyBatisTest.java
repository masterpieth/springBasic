//package com.basic.basic;
//
//import static org.hamcrest.CoreMatchers.not;
//import static org.hamcrest.CoreMatchers.nullValue;
//import static org.junit.Assert.assertThat;
//
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.basic.basic.dao.UserDAO;
//
////@RunWith(SpringJUnit4ClassRunner.class)
////@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
//public class MyBatisTest {
//
//	@Autowired
//	private SqlSessionFactory sqlSessionFactory;
//	
//	@Test
//	public void testFactory() {
//		assertThat(sqlSessionFactory, not(nullValue()));
//	}
//	
//	@Test
//	public void testSession() {
//		SqlSession session = sqlSessionFactory.openSession();
//		
//		assertThat(session, not(nullValue()));
//	}
//}
