import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ProductManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("***************PRODUCT MANAGEMENT**************");
            System.out.println("1. Danh sách sản phẩm");
            System.out.println("2. Thêm mới sản phẩm");
            System.out.println("3. Cập nhật sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Tìm kiếm sản phẩm theo tên sản phẩm");
            System.out.println("6. Sắp xếp sản phẩm theo giá tăng dần");
            System.out.println("7. Thống kê số lượng sản phẩm theo danh mục");
            System.out.println("8. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    displayProducts();
                    break;
                case 2:
                    createProduct(scanner);
                    break;
                case 3:
                    updateProduct(scanner);
                    break;
                case 4:
                    deleteProduct(scanner);
                    break;
                case 5:
                    searchProductByName(scanner);
                    break;
                case 6:
                    sortProductByPriceASC();
                    break;
                case 7:
                    statisticProductByCatalog();
                    break;
                case 8:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn từ 1 đến 8");
            }
        } while (true);
    }

    public static void displayProducts() {
        List<Product> listProducts = ProductBusiness.findAllProduct();
        listProducts.forEach(System.out::println);
    }

    public static void createProduct(Scanner scanner) {
        Product product = new Product();
        product.inputData(scanner);
        boolean result = ProductBusiness.createProduct(product);
        if (result) {
            System.out.println("Thêm mới sản phẩm thành công");
        } else {
            System.err.println("Có lỗi trong quá trình thêm mới sản phẩm");
        }
    }

    public static void updateProduct(Scanner scanner) {
        System.out.println("Nhập vào mã sản phẩm cần cập nhật:");
        int productId = Integer.parseInt(scanner.nextLine());
        Product productUpdate = ProductBusiness.findById(productId);
        if (productUpdate == null) {
            System.err.println("Mã sản phẩm không tồn tại");
            return;
        }
        boolean isExit = true;
        do {
            System.out.println("1. Cập nhật tên sản phẩm");
            System.out.println("2. Cập nhật giá sản phẩm");
            System.out.println("3. Cập nhật ngày tạo sản phẩm");
            System.out.println("4. Cập nhật tiêu đề sản phẩm");
            System.out.println("5. Cập nhật danh mục sản phẩm");
            System.out.println("6. Cập nhật trạng thái sản phẩm");
            System.out.println("7. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Nhập vào tên mới của sản phẩm:");
                    productUpdate.setProductName(scanner.nextLine());
                    break;
                case 2:
                    System.out.println("Nhập vào giá mới của sản phẩm:");
                    productUpdate.setPrice(Float.parseFloat(scanner.nextLine()));
                    break;
                case 3:
                    System.out.println("Nhập vào ngày tạo mới của sản phẩm:");
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        productUpdate.setCreated(sdf.parse(scanner.nextLine()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    System.out.println("Cập nhật tiêu đề sản phẩm:");
                    productUpdate.setTitle(scanner.nextLine());
                    break;
                case 5:
                    System.out.println("Nhập vào danh mục mới của sản phẩm:");
                    productUpdate.setCatalog(scanner.nextLine());
                    break;
                case 6:
                    System.out.println("Nhập vào trạng thái mới của sản phẩm:");
                    productUpdate.setStatus(Boolean.parseBoolean(scanner.nextLine()));
                    break;
                case 7:
                    isExit = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-7");
            }
        } while (isExit);
        boolean result = ProductBusiness.updateProduct(productUpdate);
        if (result) {
            System.out.println("Cập nhật thành công");
        } else {
            System.err.println("Cập nhật thất bại");
        }
    }

    public static void deleteProduct(Scanner scanner) {
        System.out.println("Nhập vào mã sản phẩm cần xóa:");
        int productId = Integer.parseInt(scanner.nextLine());
        Product product = ProductBusiness.findById(productId);
        if (product == null) {
            System.err.println("Mã sản phẩm không tồn tại");
            return;
        }
        boolean result = ProductBusiness.deleteProduct(productId);
        if (result) {
            System.out.println("Xóa sản phẩm thành công");
        } else {
            System.err.println("Xóa sản phẩm thất bại");
        }
    }

    public static void searchProductByName(Scanner scanner) {
        System.out.println("Nhập vào tên sản phẩm cần tìm:");
        String productName = scanner.nextLine();
        List<Product> listProducts = ProductBusiness.searchProductByName(productName);
        listProducts.forEach(System.out::println);
        System.out.printf("Tìm thấy %d sản phẩm thỏa mãn yêu cầu\n", listProducts.size());
    }

    public static void sortProductByPriceASC() {
        List<Product> listProducts = ProductBusiness.findAllProduct();
        listProducts.stream().sorted(Comparator.comparing(Product::getPrice)).forEach(System.out::println);
    }

    public static void statisticProductByCatalog() {
        List<StatisticProduct> listStaProducts = StatisticProductBusiness.getStatisticProduct();
        listStaProducts.forEach(System.out::println);
    }
}