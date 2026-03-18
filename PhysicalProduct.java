public class PhysicalProduct extends Product {
    private double weight;

    public PhysicalProduct(String id, String name, double price, double weight) {
        super(id, name, price);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public void displayInfo() {
        System.out.println("[ Loại: Sản phẩm vật lý - ID:" + id + " - Tên: " + name + " - Giá: "
                + String.format("%.2f", price) + " VNĐ - Trọng lượng: " + weight + " kg ]");
    }
}
