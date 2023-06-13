package org.example;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Restaurant {
    private String name;
    private String address;
    private List<String> menu;

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
        this.menu = new ArrayList<>();
    }

    public void addToMenu(String foodItem) {
        menu.add(foodItem);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<String> getMenu() {
        return menu;
    }
}

class SwiggyApplication {
    private Map<String, Restaurant> restaurants;
    private Map<String, List<String>> cart;

    public SwiggyApplication() {
        restaurants = new HashMap<>();
        cart = new HashMap<>();
    }

    public void addRestaurant(String name, String address) {
        Restaurant restaurant = new Restaurant(name, address);
        restaurants.put(name, restaurant);
        System.out.println("Restaurant added: " + name);
    }

    public void addToMenu(String restaurantName, String foodItem) {
        Restaurant restaurant = restaurants.get(restaurantName);
        if (restaurant != null) {
            restaurant.addToMenu(foodItem);
            System.out.println("Food item added to the menu: " + foodItem);
        } else {
            System.out.println("Restaurant not found.");
        }
    }

    public List<String> getRestaurantNames() {
        return new ArrayList<>(restaurants.keySet());
    }

    public void addToCart(String restaurantName, String foodItem) {
        List<String> restaurantCart = cart.getOrDefault(restaurantName, new ArrayList<>());
        restaurantCart.add(foodItem);
        cart.put(restaurantName, restaurantCart);
        System.out.println("Added " + foodItem + " to the cart.");
    }

    public void displayMenu(String restaurantName) {
        Restaurant restaurant = restaurants.get(restaurantName);
        if (restaurant != null) {
            System.out.println("Menu for " + restaurantName + ":");
            List<String> menu = restaurant.getMenu();
            for (String foodItem : menu) {
                System.out.println(foodItem);
            }
        } else {
            System.out.println("Restaurant not found.");
        }
    }

    public void displayCart() {
        System.out.println("Cart:");
        for (Map.Entry<String, List<String>> entry : cart.entrySet()) {
            String restaurantName = entry.getKey();
            List<String> items = entry.getValue();
            System.out.println("Restaurant: " + restaurantName);
            System.out.println("Items:");
            for (String item : items) {
                System.out.println(item);
            }
            System.out.println("------");
        }
    }

    public double calculateTotalBill() {
        double totalBill = 0.0;
        for (List<String> items : cart.values()) {
            for (String item : items) {
                totalBill += getItemPrice(item);
            }
        }
        return totalBill;
    }

    private double getItemPrice(String item) {
        // You can implement your own logic to fetch the price of the item from a database or other sources
        // For simplicity, we assume a fixed price for each item
        if (item.equals("Chilli Chicken")) {
            return 150.0;
        } else if (item.equals("Chick. Fried Rice")) {
            return 180.0;
        } else if (item.equals("Chilli Paneer")) {
            return 120.0;
        } else if (item.equals("V. Fried Rice")) {
            return 130.0;
        }else if (item.equals("Chick. Noodles")) {
            return 170.0;
        }else if (item.equals("V. Noodles")) {
            return 110.0;
        }else if (item.equals("Kothey Momo")) {
            return 140.0;
        }else if (item.equals("Tandoori Momo")) {
            return 170.0;
        }else if (item.equals("Kurkure Momo")) {
            return 170.0;
        }else if (item.equals("Steam Momo")) {
            return 100.0;
        }else if (item.equals("Fried Momo")) {
            return 150.0;
        }else if (item.equals("Pan Fried Momo")) {
            return 180.0;
        }
        return 0.0;
    }

    public void placeOrder() {
        double totalBill = calculateTotalBill();
        System.out.println("Total bill amount: Rs." + totalBill);

        // Implement payment integration logic here
        // You can use payment gateways or simulate payment using dummy data

        System.out.println("Payment successful. Order placed!");
        cart.clear();
    }
}

public class Main {
    public static void main(String[] args) {
        SwiggyApplication swiggyApp = new SwiggyApplication();
        Scanner scanner = new Scanner(System.in);

        // Add sample restaurants
        swiggyApp.addRestaurant("Indian Accent", "Delhi");
        swiggyApp.addToMenu("Indian Accent", "Chilli Chicken");
        swiggyApp.addToMenu("Indian Accent", "Chick. Fried Rice");
        swiggyApp.addToMenu("Indian Accent", "Chilli Paneer");
        swiggyApp.addToMenu("Indian Accent", "V. Fried Rice");
        swiggyApp.addToMenu("Indian Accent", "Chick. Noodles");
        swiggyApp.addToMenu("Indian Accent", "V. Noodles");

        swiggyApp.addRestaurant("Bukhara", "Delhi");
        swiggyApp.addToMenu("Bukhara", "Kothey Momo");
        swiggyApp.addToMenu("Bukhara", "Tandoori Momo");
        swiggyApp.addToMenu("Bukhara", "Kurkure Momo");
        swiggyApp.addToMenu("Bukhara", "Steam Momo");
        swiggyApp.addToMenu("Bukhara", "Fried Momo");
        swiggyApp.addToMenu("Bukhara", "Pan Fried Momo");

        boolean running = true;
        while (running) {
            System.out.println("\n--- Swiggy Customer View ---");
            System.out.println("1. Browse Restaurants");
            System.out.println("2. View Menu");
            System.out.println("3. Add Item to Cart");
            System.out.println("4. View Cart");
            System.out.println("5. View Total Bill");
            System.out.println("6. Place Order");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    List<String> restaurantNames = swiggyApp.getRestaurantNames();
                    System.out.println("Restaurants:");
                    for (String name : restaurantNames) {
                        System.out.println(name);
                    }
                    break;
                case 2:
                    System.out.print("Enter restaurant name: ");
                    String restaurantName = scanner.nextLine();
                    swiggyApp.displayMenu(restaurantName);
                    break;
                case 3:
                    System.out.print("Enter restaurant name: ");
                    String res = scanner.nextLine();
                    System.out.print("Enter food item: ");
                    String item = scanner.nextLine();
                    swiggyApp.addToCart(res, item);
                    break;
                case 4:
                    swiggyApp.displayCart();
                    break;
                case 5:
                    double totalBill = swiggyApp.calculateTotalBill();
                    System.out.println("Total bill amount: $" + totalBill);
                    break;
                case 6:
                    swiggyApp.placeOrder();
                    break;
                case 7:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}