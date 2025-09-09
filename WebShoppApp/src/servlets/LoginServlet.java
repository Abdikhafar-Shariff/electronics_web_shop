package servlets;

import bl.User;
import bl.UserHandler;
import db.UserDb;
import ui.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/user-login-servlet")
public class LoginServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        try(PrintWriter out = response.getWriter()) {
            //out.println("this is a login servlet"); test to se it

            // Get user data submitted from the form
            String userEmail = request.getParameter("login-email");
            String password = request.getParameter("login-password");

            //check if we get dqta from the form
           // out.println(userEmail+password);

            // create new user with submitted email and password
            UserInfo userInfo = new UserInfo();
            userInfo.setEmail(userEmail);
            userInfo.setPassword(password);

            // use UserDb to is if user is valid
            boolean isValid = false;
            try {
                isValid= UserHandler.isValidUser(userInfo);

            } catch (Exception e) {
                e.printStackTrace();
                out.println("User is invalid");
                return;
            }
            if(isValid){
                // If valid, redirect to a index.jsp
                User user = UserHandler.getUserByCredentials(userInfo);
                userInfo.setUserId(user.getUserId());

                request.getSession().setAttribute("Authorized",userInfo);
                response.sendRedirect("index.jsp");
            }else {
                out.println("Invalid email and password");

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
