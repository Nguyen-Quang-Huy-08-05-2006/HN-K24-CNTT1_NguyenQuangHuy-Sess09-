import java.util.List;
import java.util.Scanner;

public class main {
    private static ProductDatabase database = ProductDatabase.getInstance();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;
        boolean continueLoop = true;

        while (continueLoop) {
            displayMenu();
            System.out.print("Lựa chọn của bạn: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        viewAllProducts();
                        break;
                    case 3:
                        updateProduct();
                        break;
                    case 4:
                        deleteProduct();
                        break;
                    case 5:
                        continueLoop = false;
                        System.out.println("\n Đã tắt hệ thóng");
                        break;
                    default:
                        System.out.println(" Lựa chọn không hợp lệ Vui lòng thử lại.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số nguyên hợp lệ\n");
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("---------------------- QUẢN LÝ SẢN PHẨM ----------------------");
        System.out.println("1. Thêm mới sản phẩm");
        System.out.println("2. Xem danh sách sản phẩm");
        System.out.println("3. Cập nhật thông tin sản phẩm");
        System.out.println("4. Xoá sản phẩm");
        System.out.println("5. Thoát");
        System.out.println("-----------------------------------------------------------------------");
    }

    private static void addProduct() {
        System.out.print("Nhập loại sản phẩm (1 = Vật lý, 2 = Kỹ thuật số): ");
        int type;
        try {
            type = Integer.parseInt(scanner.nextLine());
            if (type != 1 && type != 2) {
                System.out.println("Loại sản phẩm không hợp lệ");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Vui lòng nhập một số nguyên hợp lệ");
            return;
        }

        System.out.print("Nhập ID sản phẩm: ");
        String id = scanner.nextLine().trim();

        System.out.print("Nhập tên sản phẩm: ");
        String name = scanner.nextLine().trim();

        System.out.print("Nhập giá sản phẩm (VNĐ): ");
        double price;
        try {
            price = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Giá sản phẩm không hợp lệ");
            return;
        }

        double attribute;
        if (type == 1) {
            System.out.print("Nhập trọng lượng (kg): ");
            try {
                attribute = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Trọng lượng không hợp lệ!");
                return;
            }
        } else {
            System.out.print("Nhập dung lượng (MB): ");
            try {
                attribute = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(" Dung lượng không hợp lệ!");
                return;
            }
        }

        Product product = ProductFactory.createProduct(type, id, name, price, attribute);
        if (product != null) {
            database.addProduct(product);
        }

    }

    private static void viewAllProducts() {
        System.out.println("\nDANH SÁCH SẢN PHẨM");

        List<Product> products = database.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("Không có sản phẩm nào trong kho");
        } else {
            System.out.println("Tổng số sản phẩm: " + database.getProductCount());

            for (Product product : products) {
                product.displayInfo();
            }
        }

    }

    private static void updateProduct() {
        viewAllProducts();

        System.out.print("\nNhập ID sản phẩm cần cập nhật: ");
        String id = scanner.nextLine().trim();

        Product product = database.getProductById(id);
        if (product == null) {
            System.out.println("Không tìm thấy sản phẩm với ID: " + id);
            return;
        }

        System.out.print("Nhập tên sản phẩm mới: ");
        String name = scanner.nextLine().trim();

        System.out.print("Nhập giá sản phẩm mới (VNĐ): ");
        double price;
        try {
            price = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Giá sản phẩm không hợp lệ");
            return;
        }

        if (product instanceof PhysicalProduct) {
            System.out.print("Nhập trọng lượng mới (kg): ");
            try {
                double weight = Double.parseDouble(scanner.nextLine());
                ((PhysicalProduct) product).setWeight(weight);
            } catch (NumberFormatException e) {
                System.out.println("Trọng lượng không hợp lệ");
                return;
            }
        } else if (product instanceof DigitalProduct) {
            System.out.print("Nhập dung lượng mới (MB): ");
            try {
                double size = Double.parseDouble(scanner.nextLine());
                ((DigitalProduct) product).setSize(size);
            } catch (NumberFormatException e) {
                System.out.println(" Dung lượng không hợp lệ");
                return;
            }
        }

        product.setName(name);
        product.setPrice(price);

        System.out.println("Sản phẩm đã được cập nhật thành công");
    }

    private static void deleteProduct() {
        System.out.println("\nXÓA SẢN PHẨM");

        viewAllProducts();

        System.out.print("\n Nhập ID sản phẩm cần xóa: ");
        String id = scanner.nextLine().trim();

        System.out.print("Bạn có chắc chắn muốn xóa? (Y/N): ");
        String confirm = scanner.nextLine().trim().toUpperCase();

        if (confirm.equals("Y")) {
            database.deleteProduct(id);
        } else {
            System.out.println(" Hủy thao tác xóa sản phẩm");
        }

    }
}
