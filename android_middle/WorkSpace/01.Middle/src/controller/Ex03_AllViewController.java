package controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * Servlet implementation class Ex03_AllViewController
 */
@WebServlet("*.vw")
public class Ex03_AllViewController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Ex03_AllViewController까지 접근을 했음.");
		System.out.println(req.getServletPath());
		initMybatis();
	}
	
	public void initMybatis() {
		try {
		String resource = "mybatis/config.xml";
		InputStream inputStream;
		inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		try (SqlSession session = sqlSessionFactory.openSession()) {
			//select.selectone .. 기타등등.. mapper의 네임스페이스 . id
			  int data = session.selectOne("mybatis.test.test");
			  System.out.println(data);
			  session.close();
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
