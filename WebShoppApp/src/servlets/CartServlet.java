/*package servlets;

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


/*import bl.CartHandler;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            // If the user is not logged in, redirect to the login page
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // Get the user's cart and display it
            int cartId = CartHandler.getCart(user.getUserId()).getCartId();
            List<CartItemInfo> cartItems = CartHandler.getAllCartItem(cartId);
            request.setAttribute("cartItems", cartItems);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred while viewing the cart.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            // If the user is not logged in, redirect to the login page
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            switch (action) {
                case "add":
                    int addItemId = Integer.parseInt(request.getParameter("itemId"));
                    CartHandler.addItemToCart(addItemId, user.getUserId());
                    break;

                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
                    return;
            }

            // After modifying the cart, redirect to the doGet() to display the updated cart
            response.sendRedirect("cart"); // Calls doGet() method to refresh the cart view

        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error or invalid item ID.");
        }
    }
}*/

