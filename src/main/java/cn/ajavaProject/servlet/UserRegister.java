package cn.ajavaProject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cn.ajavaProject.connection.DbCon;
import cn.ajavaProject.dao.UserDao;
import cn.ajavaProject.model.*;


@WebServlet("/user_register")
public class UserRegister extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		for session handling
		try {
			
			String fullname = req.getParameter("fullname");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			User1 u = new User1(fullname, email, password);
			
			UserDao udao = new UserDao(DbCon.getConnection());
			
			HttpSession session = req.getSession();
			
			boolean f = udao.register(u);
			
			if(f) {
				
				session.setAttribute("successMsg", "Registered successfully");
				resp.sendRedirect("login.jsp");
				
//				System.out.println("Register successful");
			}
			else {
				
				session.setAttribute("errorMsg", "Something wrong on server");
				resp.sendRedirect("signup.jsp");
				
//				System.out.println("Something wrong on server");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
