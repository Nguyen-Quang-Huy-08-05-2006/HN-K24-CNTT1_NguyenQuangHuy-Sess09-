import java.util.ArrayList;
import java.util.List;

public class ProductDatabase {
    private static ProductDatabase instance;
    private List<Product> products;

    private ProductDatabase() {
        this.products = new ArrayList<>();
    }

    public static synchronized ProductDatabase getInstance() {
        if (instance == null) {
            instance = new ProductDatabase();
        }
        return instance;
    }

    public void addProduct(Product product) {
        for (Product p : products) {
            if (p.getId().equals(product.getId())) {
                System.out.println("Lỗi: ID sản phẩm '" + product.getId() + "' đã tồn tại");
                return;
            }
        }
        products.add(product);
        System.out.println("Sản phẩm được thêm thành công");
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public Product getProductById(String id) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public void updateProduct(String id, String name, double price) {
        Product product = getProductById(id);
        if (product != null) {
            product.setName(name);
            product.setPrice(price);

            if (product instanceof PhysicalProduct) {
                System.out.print("Nhập trọng lượng mới (kg): ");
                try {
                    double weight = Double.parseDouble(System.console().readLine());
                    ((PhysicalProduct) product).setWeight(weight);
                } catch (Exception e) {
                    System.out.println("Nhập trọng lượng không hợp lệ");
                }
            } else if (product instanceof DigitalProduct) {
                System.out.print("Nhập dung lượng mới (MB): ");
                try {
                    double size = Double.parseDouble(System.console().readLine());
                    ((DigitalProduct) product).setSize(size);
                } catch (Exception e) {
                    System.out.println("Nhập dung lượng không hợp lệ");
                }
            }

            System.out.println("Sản phẩm đã được cập nhật thành công");
        } else {
            System.out.println("Không tìm thấy sản phẩm với ID: " + id);
        }
    }

    public void deleteProduct(String id) {
        Product product = getProductById(id);
        if (product != null) {
            products.remove(product);
            System.out.println("Sản phẩm đã được xóa thành công!");
        } else {
            System.out.println("Không tìm thấy sản phẩm với ID: " + id);
        }
    }

    public int getProductCount() {
        return products.size();
    }
}
