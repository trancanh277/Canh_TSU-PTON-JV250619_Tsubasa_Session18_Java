import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Product {
    private int productId;
    private String productName;
    private float price;
    private String title;
    private Date created;
    private String catalog;
    private boolean status;

    public Product() {
    }

    public Product(int productId, String productName, float price, String title, Date created, String catalog, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.title = title;
        this.created = created;
        this.catalog = catalog;
        this.status = status;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputData(Scanner scanner) {
        this.productName = inputProductName(scanner);
        System.out.println("Nhập vào giá sản phẩm:");
        this.price = Float.parseFloat(scanner.nextLine());
        System.out.println("Nhập vào tiêu đề sản phẩm:");
        this.title = scanner.nextLine();
        System.out.println("Nhập vào ngày tạo sản phẩm:");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.created = sdf.parse(scanner.nextLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Nhập vào danh mục sản phẩm:");
        this.catalog = scanner.nextLine();
    }

    public String inputProductName(Scanner scanner) {
        System.out.println("Nhập vào tên sản phẩm:");
        do {
            String productName = scanner.nextLine();
            if (!ProductBusiness.isExistProductName(productName)) {
                return productName;
            }
            System.err.println("Tên sản phẩm đã tồn tại, vui lòng nhập lại");
        } while (true);
    }

    @Override
    public String toString() {
        return String.format("Mã SP: %d - Tên SP: %s - Giá: %f\nTiêu đề: %s - Ngày tạo: %s - Danh mục: %s - Trạng thái: %s\n",
                this.productId, this.productName, this.price, this.title, this.created, this.catalog, this.status ? "Hoạt động" : "Không hoạt động");
    }
}