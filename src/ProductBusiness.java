import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductBusiness {
    public static List<Product> findAllProduct() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> listProducts = null;
        try {
            conn = ConnectionDb.openConnection();
            callSt = conn.prepareCall("{call find_all_product()}");
            ResultSet rs = callSt.executeQuery();
            listProducts = new ArrayList<Product>();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setPrice(rs.getFloat("product_price"));
                product.setTitle(rs.getString("product_title"));
                product.setCreated(rs.getDate("product_created"));
                product.setCatalog(rs.getString("product_catalog"));
                product.setStatus(rs.getBoolean("product_status"));
                listProducts.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDb.closeConnection(conn, callSt);
        }
        return listProducts;
    }

    public static boolean isExistProductName(String productName) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDb.openConnection();
            callSt = conn.prepareCall("{call is_exist_product_name(?,?)}");

            callSt.setString(1, productName);

            callSt.registerOutParameter(2, Types.INTEGER);
            callSt.execute();
            int countProduct = callSt.getInt(2);
            if (countProduct > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDb.closeConnection(conn, callSt);
        }
        return result;
    }

    public static boolean createProduct(Product product) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDb.openConnection();
            callSt = conn.prepareCall("{call create_product(?,?,?,?,?)}");
            callSt.setString(1, product.getProductName());
            callSt.setFloat(2, product.getPrice());
            callSt.setString(3, product.getTitle());
            callSt.setDate(4, new java.sql.Date(product.getCreated().getTime()));
            callSt.setString(5, product.getCatalog());
            callSt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDb.closeConnection(conn, callSt);
        }
        return false;
    }

    public static Product findById(int productId) {
        Connection conn = null;
        CallableStatement callSt = null;
        Product product = null;
        try {
            conn = ConnectionDb.openConnection();
            callSt = conn.prepareCall("{call find_product_by_id(?)}");
            callSt.setInt(1, productId);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setPrice(rs.getFloat("product_price"));
                product.setTitle(rs.getString("product_title"));
                product.setCreated(rs.getDate("product_created"));
                product.setCatalog(rs.getString("product_catalog"));
                product.setStatus(rs.getBoolean("product_status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDb.closeConnection(conn, callSt);
        }
        return product;
    }

    public static boolean updateProduct(Product product) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDb.openConnection();
            callSt = conn.prepareCall("{call update_product(?,?,?,?,?,?,?)}");
            callSt.setInt(1, product.getProductId());
            callSt.setString(2, product.getProductName());
            callSt.setFloat(3, product.getPrice());
            callSt.setString(4, product.getTitle());
            //product.getCreated(): java.util.Date --> java.sql.Date
            callSt.setDate(5, new java.sql.Date(product.getCreated().getTime()));
            callSt.setString(6, product.getCatalog());
            callSt.setBoolean(7, product.isStatus());
            callSt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDb.closeConnection(conn, callSt);
        }
        return false;
    }

    public static boolean deleteProduct(int productId) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDb.openConnection();
            callSt = conn.prepareCall("{call delete_product(?)}");
            callSt.setInt(1, productId);
            callSt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDb.closeConnection(conn, callSt);
        }
        return false;
    }

    public static List<Product> searchProductByName(String productName) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> listProducts = null;
        try {
            conn = ConnectionDb.openConnection();
            callSt = conn.prepareCall("{call find_product_by_name(?)}");
            callSt.setString(1, productName);
            ResultSet rs = callSt.executeQuery();
            listProducts = new ArrayList<Product>();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setPrice(rs.getFloat("product_price"));
                product.setTitle(rs.getString("product_title"));
                product.setCreated(rs.getDate("product_created"));
                product.setCatalog(rs.getString("product_catalog"));
                product.setStatus(rs.getBoolean("product_status"));
                listProducts.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDb.closeConnection(conn, callSt);
        }
        return listProducts;
    }

}