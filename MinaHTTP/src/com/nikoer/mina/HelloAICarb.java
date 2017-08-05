package com.nikoer.mina;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


/**
 * Servlet implementation class HelloAICarb
 */
@WebServlet("/HelloAICarb")
public class HelloAICarb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloAICarb() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("name");
		if(username!=null){
			insertUsers(username);
		}
		response.getWriter().write("Hello aicarb\n");
		Jedis jedis = new Jedis("localhost");
		try {
			String value = jedis.get("foo");
			response.getWriter().write("foo"+value);
		} catch (Exception e) {
			// TODO: handle exception
			response.getWriter().write("foo error");
		}finally{
			jedis.close();
		}
		
		response.getWriter().flush();
	}
	private void insertUsers(String userName){
		String user = "root";
		String password = "s123456";
		String url = "jdbc:mysql://127.0.0.1:3306/aicarb";
		String driver = "com.mysql.jdbc.Driver";
		Connection con = null;
		Statement stmt = null;
		try{
			Class.forName(driver);
			con = (Connection) DriverManager.getConnection(url, user, password);
			stmt = (Statement) con.createStatement();
			String sqlstr = "insert into ac_users(user_name,age) values ('"+userName+"',34)";
			stmt.executeUpdate(sqlstr);
		}catch(Exception e){
			System.out.println(e.toString());
		}finally{
			try{
				if(stmt != null){
					 stmt.close();
				 }
				 if(con != null){
					 con.close();
				 }
			}catch(Exception e){
				System.out.println(e.toString());
			}
			 
		}
	}

}
