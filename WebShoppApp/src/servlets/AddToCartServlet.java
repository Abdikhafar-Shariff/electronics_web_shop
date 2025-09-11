package servlets;

import bl.CartHandler;
import bl.CartItem;
import bl.Item;
import bl.ItemHandler;
import ui.CartItemInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        // Try-with-resources for auto-closing PrintWriter
        try (PrintWriter out = response.getWriter()) {
            // Access the item id from the URL parameters
            int item_id = Integer.parseInt(request.getParameter("item_id"));
            Item itemId = Item.getItemById(item_id);

            // Create a new cart item with default quantity of 1
            CartItem cartItem = new CartItem(itemId.getItemId(),itemId.getItemName(),itemId.getDescription(),
                    itemId.getPrice(),itemId.getQuantity());
            cartItem.setItemId(item_id);
            cartItem.setQuantity(1);

            // Check session for existing cart items
            HttpSession session = request.getSession();
            List<CartItem> cartLists = (ArrayList<CartItem>) session.getAttribute("cartList");

            if (cartLists == null) {
                // No cart exists, so create a new cart list and add the item
                cartLists = new ArrayList<>();
                cartLists.add(cartItem);
                session.setAttribute("cartList", cartLists);  // Set the new cart list in the session
            } else {
                // Cart exists, check if the item is already in the cart
                boolean ifItExist = false;

                for (CartItem c : cartLists) {
                    if (c.getItemId() == item_id) {
                        ifItExist = true;
                        // Update the quantity instead of just notifying
                        c.setQuantity(c.getQuantity() + 1);  // Increase the quantity by 1
                        break;
                    }
                }

                // If the item is not already in the cart, add it to the cart list
                if (!ifItExist) {
                    cartLists.add(cartItem);
                }

                // Update the session with the modified cart list
                session.setAttribute("cartList", cartLists);
            }

            // Redirect to the cart or another page after adding/updating
            response.sendRedirect("cart.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}











