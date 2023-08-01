package cn.ajavaProject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.PreparableStatement;

import cn.ajavaProject.model.User;
import cn.ajavaProject.model.User1;

public class UserDao {
  private Connection con;
  private String query;
  private PreparedStatement pst;
  private ResultSet rs;
public UserDao(Connection con) {
	this.con = con;
}

  
  public User userLogin(String email , String password) {
	  User user = null;
	  try {
		query ="select * from users where email=? and password= ?";
		pst =this.con.prepareStatement(query);
		pst.setString(1, email);
		pst.setString(2, password);
		rs =pst.executeQuery();
		
		if (rs.next()) {
				user =new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		System.out.println(e.getMessage());
	}
	  return user;
  }
  public boolean register(User1 u) {
		boolean f = false;
		
		try {
			String sql = "insert into users(name, email, password) values(?, ?, ?)";
			
		pst = this.con.prepareStatement(sql);
			
			pst.setString(1, u.getFullname());
			pst.setString(2, u.getEmail());
			pst.setString(3, u.getPassword());
			
			int i = pst.executeUpdate();
			
			if(i == 1) {
				f = true;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
}
