package servlets;

import bl.CartHandler;
import bl.User;
import ui.CartItemInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

//@WebServlet("/cart")
/*public class CartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        try {
            if ("add".equals(action)) {
                int itemId = Integer.parseInt(request.getParameter("itemId"));
                List<CartItemInfo> cartItemInfoList = CartHandler.addItemToCart(itemId, user.getUserId());
                request.setAttribute("cartItems", cartItemInfoList);
                request.getRequestDispatcher("cart.jsp").forward(request, response);
            } else if ("remove".equals(action)) {
                int itemId = Integer.parseInt(request.getParameter("itemId"));
                // Implement removal logic
                CartHandler.removeItemFromCart(itemId, user.getUserId()); // You'll need to implement this method
                // Refresh cart items
                int cartId = CartHandler.getCart(user.getUserId()).getCartId();
                List<CartItemInfo> cartItems = CartHandler.getAllCartItem(cartId);
                request.setAttribute("cartItems", cartItems);
                request.getRequestDispatcher("cart.jsp").forward(request, response);
            } else if ("increment".equals(action)) {
                int itemId = Integer.parseInt(request.getParameter("itemId"));
                // Logic to increment quantity
                CartHandler.incrementItemQuantity(itemId, user.getUserId()); // Implement this method
                int cartId = CartHandler.getCart(user.getUserId()).getCartId();
                List<CartItemInfo> cartItems = CartHandler.getAllCartItem(cartId);
                request.setAttribute("cartItems", cartItems);
                request.getRequestDispatcher("cart.jsp").forward(request, response);
            } else if ("decrement".equals(action)) {
                int itemId = Integer.parseInt(request.getParameter("itemId"));
                // Logic to decrement quantity
                CartHandler.decrementItemQuantity(itemId, user.getUserId()); // Implement this method
                int cartId = CartHandler.getCart(user.getUserId()).getCartId();
                List<CartItemInfo> cartItems = CartHandler.getAllCartItem(cartId);
                request.setAttribute("cartItems", cartItems);
                request.getRequestDispatcher("cart.jsp").forward(request, response);
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}*/


import bl.CartHandler;
import bl.User;
import ui.CartItemInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/cart") // URL pattern for the servlet
public class CartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user"); // Assuming you have a user object in session
        if (user == null) {
            // If the user is not logged in, redirect to login page
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            if ("add".equals(action)) {
                int itemId = Integer.parseInt(request.getParameter("itemId"));
                List<CartItemInfo> cartItemInfoList = CartHandler.addItemToCart(itemId, user.getUserId());
                session.setAttribute("cartItems", cartItemInfoList);
                request.setAttribute("cartItems", cartItemInfoList);
                request.getRequestDispatcher("cart.jsp").forward(request, response); // Forward to cart view
            }
            else if ("view".equals(action)) {
                int cartId = CartHandler.getCart(user.getUserId()).getCartId();
                List<CartItemInfo> cartItems = CartHandler.getAllCartItem(cartId);
                request.setAttribute("cartItems", cartItems);
                request.getRequestDispatcher("cart.jsp").forward(request, response); // Forward to cart view
            }
            // You can add more actions here (like remove, update, etc.)
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid item ID");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response); // Handle GET requests as POST
    }
}

