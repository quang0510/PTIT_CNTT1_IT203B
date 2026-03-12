package Session_04.KiemTra;


import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.print("""
                    1. Thêm sản phẩm mới
                    2. Hiển thị danh sách sản phẩm
                    3. Cập nhật số lượng theo ID
                    4. Xóa sản phẩm có số lượng = 0
                    5. Thoát chương trình
                    """);
            System.out.print("Lựa chọn của bạn: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    addProduct(sc);
                    break;
                case 2:
                    display();
                    break;
                case 3:
                    updateQuantity(sc);
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    System.out.println("Thoát chương trình");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        } while (choice != 5);
    }

    public static void addProduct(Scanner sc) {
        System.out.print("Nhập ID sản phẩm: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Nhập tên sản phẩm: ");
        String name = sc.nextLine();

        System.out.print("Nhập giá sản phẩm: ");
        double price = Double.parseDouble(sc.nextLine());

        System.out.print("Nhập số lượng sản phẩm: ");
        int quantity = Integer.parseInt(sc.nextLine());

        System.out.print("Nhập danh mục sản phẩm: ");
        String category = sc.nextLine();

        for (Product product : Product.productList) {
            if (product.getId() == id) {
                throw new InvalidProductException("ID đã tồn tại");
            }
            if (product.getName().equalsIgnoreCase(name)) {
                throw new InvalidProductException("Tên sản phẩm đã tồn tại");
            }
        }

        Product newProduct = new Product(id, name, price, quantity, category);
        Product.productList.add(newProduct);

        System.out.println("Thêm sản phẩm thành công");
    }

    public static void display() {

        if (Product.productList.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống");
            return;
        }

        for (Product product : Product.productList) {
            System.out.printf(
                    "ID: %d - Name: %s - Price: %.2f - Quantity: %d - Category: %s\n",
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getQuantity(),
                    product.getCategory()
            );
        }
    }

    public static void updateQuantity(Scanner sc) {

        System.out.print("Nhập ID sản phẩm cần cập nhật: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Nhập số lượng mới: ");
        int quantity = Integer.parseInt(sc.nextLine());

        for (Product product : Product.productList) {
            if (product.getId() == id) {
                product.setQuantity(quantity);
                System.out.println("Cập nhật số lượng thành công");
                return;
            }
        }

        throw new InvalidProductException("Không tìm thấy sản phẩm có ID: " + id);
    }

    public static void deleteProduct() {

        Product.productList = Product.productList.stream()
                .filter(product -> product.getQuantity() > 0)
                .collect(Collectors.toList());

        System.out.println("Đã xóa sản phẩm có số lượng = 0");
    }
}
