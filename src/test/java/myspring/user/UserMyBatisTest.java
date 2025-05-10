package myspring.user;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Consumer;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import myspring.user.vo.UserVO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:spring-beans-mybatis.xml")
public class UserMyBatisTest {
	protected static final Logger Logger = LogManager.getLogger();
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	SqlSessionFactory sessionFactory;
	
	@Autowired
	SqlSession sqlSession;
	
	@Test
	void sqlSession() {
		System.out.println(sessionFactory.getClass().getName());
		UserVO user = sqlSession.selectOne("userNS.selectUserById","dooly");
		Logger.debug(user);
		
		//Anonymous Inner Class(익명 내부 클래스)
		List<UserVO> userList = sqlSession.selectList("userNS.selectUserList"); // List<UserVO>
		//기존의 For Loop
		for (UserVO userVO : userList) {
			Logger.debug(userVO);
		}
		//Anonymous(Consumer)애서 Consumer를 Anonymous Inner class 형태로 선언하는 방식
		userList.forEach(new Consumer<UserVO>() {
			@Override
			public void accept(UserVO user) {
				Logger.debug(user);
			}
		});
		//Consumer 추상 메서드 void accept(T t)
		//.forEach(Consumer)에서 Consumer를 Lambda Expression (람다식)으로 선언하는 방식
		userList.forEach(user1 -> System.out.println(user1));
		//.forEach(Consumer)에서 Consumer를 Method Reference으로 선언하는 방식
		userList.forEach(System.out::println);
	}
	
	@Test
	void connection() {
		try {
			Connection connection = dataSource.getConnection();
			DatabaseMetaData metaData = connection.getMetaData();
			Logger.debug("DB URL = " + metaData.getURL());
			Logger.debug("DB Username = " + metaData.getUserName());
			Logger.debug("DB Vender Name = " + metaData.getDatabaseProductName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
