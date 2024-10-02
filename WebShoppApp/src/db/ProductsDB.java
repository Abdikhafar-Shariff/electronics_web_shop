package db;

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
            query = "select * from item";
            pst = this.con.prepareStatement(query);
            rst = pst.executeQuery();

            while (rst.next()){
                Products row = new Products();
                row.setItem_id(rst.getInt("item_id"));
                row.setName(rst.getString("name"));
                row.setPrice(rst.getString("price"));
                row.setCategory_id(rst.getString("category_id"));
                row.setImage_url(rst.getString("image_url"));

                products.add(row);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return products;
    }
}
