import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StatisticProductBusiness {
    public static List<StatisticProduct> getStatisticProduct() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<StatisticProduct> statisticProductList = null;
        try {
            conn = ConnectionDb.openConnection();
            callSt = conn.prepareCall("{call statistic_product_by_catalog()}");
            ResultSet rs = callSt.executeQuery();
            statisticProductList = new ArrayList<>();
            while (rs.next()) {
                StatisticProduct statisticProduct = new StatisticProduct();
                statisticProduct.setCatalog(rs.getString("product_catalog"));
                statisticProduct.setCatalog(String.valueOf(rs.getInt("quantity")));
                statisticProductList.add(statisticProduct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDb.closeConnection(conn, callSt);
        }
        return statisticProductList;
    }
}