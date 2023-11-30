package futurewomen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDao {
    private static Map<Integer, Product> products = new HashMap<>();

    public List<Product> getProducts() {
        List<Product> productList = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        String query = "SELECT * FROM Product;";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                productList.add(generateProduct(result));
            }
        } catch (SQLException e) {
            System.out.println("Failed to get Products. "+e.getMessage());
        }finally {
            DBUtil.closeDB();
        }
        return productList;
    }

    public ProductDao() {

    }

    public Product getProductByID(int idQuery) {
        Connection connection = DBUtil.getConnection();
        String query = "SELECT * FROM Product WHERE product_id =?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,idQuery);
            ResultSet result = statement.executeQuery();
            if (result.next()){
                return generateProduct(result);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get Products. "+e.getMessage());
        }finally {
            DBUtil.closeDB();
        }
        return null;
    }

    public int addProduct(Product product) {
        Connection connection = DBUtil.getConnection();
        String query = "INSERT INTO Product VALUES (0,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,product.getName());
            statement.setFloat(2,product.getPrice());
            statement.setString(3,product.getCategory());
            statement.setInt(4,product.getStockQty());
            return statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to add product. "+e.getMessage());
        }finally {
            DBUtil.closeDB();
        }
        return 0;
    }

    public int updateProduct(int idQuery, Product product) {
        Connection connection = DBUtil.getConnection();
        String query = "UPDATE Product SET name = ?, price = ?, category = ?, stock_qty = ? " +
                "WHERE product_id= ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,product.getName());
            statement.setFloat(2,product.getPrice());
            statement.setString(3,product.getCategory());
            statement.setInt(4,product.getStockQty());
            statement.setInt(5,idQuery);
            return statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to update product. "+e.getMessage());
        }finally {
            DBUtil.closeDB();
        }
        return 0;
    }

    public int deleteProduct(int id) {
        Connection connection = DBUtil.getConnection();
        String query = "DELETE FROM Product WHERE product_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to delete product. "+e.getMessage());
        }finally {
            DBUtil.closeDB();
        }
        return 0;
    }

    public List<Product> getProductByCategory(String categoryQuery) {
        List<Product> productList = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        String query = "SELECT * FROM Product WHERE category = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, categoryQuery);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                productList.add(generateProduct(result));
            }
        } catch (SQLException e) {
            System.out.println("Failed to get Products. " + e.getMessage());
        } finally {
            DBUtil.closeDB();
        }
        return productList;
    }

    public List<Product> getProductByPrice(float start, float end) {
        List<Product> productList = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        String query = "SELECT * FROM Product WHERE price BETWEEN ? AND ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setFloat(1, start);
            statement.setFloat(2, end);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                productList.add(generateProduct(result));
            }
        } catch (SQLException e) {
            System.out.println("Failed to get Products. " + e.getMessage());
        } finally {
            DBUtil.closeDB();
        }
        return productList;
    }

    private Product generateProduct(ResultSet result) throws SQLException {
        int productId = result.getInt("product_id");
        String name = result.getString("name");
        float price = result.getFloat("price");
        String category = result.getString("category");
        int stockQty = result.getInt("stock_qty");
        return new Product(productId, name, price, category, stockQty);
    }
}
