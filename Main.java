import java.util.*;

class FoodItem {
    private int id;
    private String name;
    private double price;

    public FoodItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return id + ". " + name + " - $" + price;
    }
}

class CartItem {
    private FoodItem foodItem;
    private int quantity;

    public CartItem(FoodItem foodItem, int quantity) {
        this.foodItem = foodItem;
        this.quantity = quantity;
    }

    public FoodItem getFoodItem() { return foodItem; }
    public int getQuantity() { return quantity; }
    public double getTotalPrice() { return foodItem.getPrice() * quantity; }

    @Override
    public String toString() {
        return foodItem.getName() + " x" + quantity + " - $" + getTotalPrice();
    }
}

class FoodOrderingSystem {
    private List<FoodItem> menu = new ArrayList<>();
    private List<CartItem> cart = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private final double TAX_RATE = 0.08;
    private final double DELIVERY_CHARGE = 5.00;

    public FoodOrderingSystem() {
        menu.add(new FoodItem(1, "Burger", 5.99));
        menu.add(new FoodItem(2, "Pizza", 8.99));
        menu.add(new FoodItem(3, "Pasta", 7.49));
        menu.add(new FoodItem(4, "Salad", 4.99));
        menu.add(new FoodItem(5, "Soda", 1.99));
    }

    public void displayMenu() {
        System.out.println("\n----- Menu -----");
        for (FoodItem item : menu) {
            System.out.println(item);
        }
    }

    public void addToCart() {
        System.out.print("\nEnter item number to add to cart (or 0 to finish): ");
        int itemId = scanner.nextInt();
        if (itemId == 0) return;

        FoodItem selectedItem = menu.stream().filter(item -> item.getId() == itemId).findFirst().orElse(null);

        if (selectedItem == null) {
            System.out.println("Invalid selection. Try again.");
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        cart.add(new CartItem(selectedItem, quantity));
        System.out.println(quantity + " x " + selectedItem.getName() + " added to cart.");
    }

    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("\nYour cart is empty.");
            return;
        }

        System.out.println("\n----- Your Cart -----");
        for (CartItem item : cart) {
            System.out.println(item);
        }
    }

    public void checkout() {
        if (cart.isEmpty()) {
            System.out.println("\nYour cart is empty. Add items before checkout.");
            return;
        }

        double subtotal = cart.stream().mapToDouble(CartItem::getTotalPrice).sum();
        double tax = subtotal * TAX_RATE;
        double total = subtotal + tax + DELIVERY_CHARGE;

        scanner.nextLine();
        System.out.print("\nEnter any special instructions: ");
        String instructions = scanner.nextLine();

        System.out.println("\n----- Order Summary -----");
        viewCart();
        System.out.printf("Subtotal: $%.2f\n", subtotal);
        System.out.printf("Tax (8%%): $%.2f\n", tax);
        System.out.printf("Delivery Charge: $%.2f\n", DELIVERY_CHARGE);
        System.out.printf("Total: $%.2f\n", total);
        System.out.println("Special Instructions: " + (instructions.isEmpty() ? "None" : instructions));
        System.out.println("\nOrder placed successfully! Thank you for ordering.");
    }

    public void startOrdering() {
        while (true) {
            System.out.println("\n===== Food Ordering System =====");
            System.out.println("1. View Menu");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> displayMenu();
                case 2 -> addToCart();
                case 3 -> viewCart();
                case 4 -> checkout();
                case 5 -> {
                    System.out.println("Thank you! Goodbye.");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        new FoodOrderingSystem().startOrdering();
    }
}
