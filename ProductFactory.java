public class ProductFactory {

    public static Product createPhysicalProduct(String id, String name, double price, double weight) {
        return new PhysicalProduct(id, name, price, weight);
    }

    public static Product createDigitalProduct(String id, String name, double price, double size) {
        return new DigitalProduct(id, name, price, size);
    }

    public static Product createProduct(int type, String id, String name, double price, double attribute) {
        switch (type) {
            case 1:
                return createPhysicalProduct(id, name, price, attribute);
            case 2:
                return createDigitalProduct(id, name, price, attribute);
            default:
                System.out.println("Loại sản phẩm không hợp lệ");
                return null;
        }
    }
}
