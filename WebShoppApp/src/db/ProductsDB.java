package db;

import bl.CategoryType;
import bl.Products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductsDB {
private Connection con;
private String query;
private PreparedStatement pst;
private ResultSet rst;

    public ProductsDB(Connection con) {
        this.con = con;
    }
    // get list of all products
    public List<Products> getAllProducts(){
        List<Products> products = new ArrayList<>();
        try {
            query = "select * from items";
            pst = this.con.prepareStatement(query);
            rst = pst.executeQuery();

            while (rst.next()){
                String categoryString = rst.getString("category");
                CategoryType category = CategoryType.valueOf(categoryString); // Convert String to Enum
                Products row = new Products();
                row.setItem_id(rst.getInt("item_id"));
                row.setItem_name(rst.getString("item_name"));
                row.setDescription(rst.getString("description"));
                row.setPrice(rst.getString("price"));
                row.setDescription(rst.getString("description"));
                row.setCategory(category);

                products.add(row);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return products;
    }
}
