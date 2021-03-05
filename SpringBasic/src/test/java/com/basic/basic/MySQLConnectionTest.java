package com.basic.basic;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class MySQLConnectionTest {

	@Autowired
	private DataSource dataSource;
	
	@Test
	public void testConnection() throws Exception {
		Connection c = null;
		try {
			c = dataSource.getConnection();
			assertThat(c, not(nullValue()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
