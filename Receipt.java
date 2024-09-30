package Task2;

import java.util.Scanner;

public class Receipt {
    private boolean isVeg;
    private boolean isPremium;
    private int quantity; // Added to track quantity of pizzas

    public Receipt(boolean isVeg, boolean isPremium, int quantity) {
        this.isVeg = isVeg;
        this.isPremium = isPremium;
        this.quantity = quantity;
    }

    public double getCost() {
        double cost = 0;	
        if (isVeg) {
            cost += 250;
        } else {
            cost += 300;
        }
        if (isPremium) {
            cost += 100;
        }
        return cost * quantity; // Multiply by quantity
    }

    public double addToppings(double cost, String topping) {
        switch (topping) {
            case "cheese":
                cost += 20 * quantity; // Multiply by quantity
                break;
            case "chicken":
                cost += 50 * quantity; // Multiply by quantity
                break;
            case "onion":
                cost += 15 * quantity; // Multiply by quantity
                break;
            default:
                break; // No additional cost for unknown toppings
        }
        return cost;
    }

    public double addTax(double cost) {
        return cost + cost * 0.05; // Adding 5% tax
    }

    public double addDiscount(double cost, double discount) {
        return cost - (cost * discount);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to My Pizza Shop\n");

        // Pizza Type Selection
        System.out.println("Select the pizza type:\n");
        System.out.println("\t1. Veg Pizza");
        System.out.println("\t2. Non-Veg Pizza");
        System.out.println("\t3. Exit\n");
        
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        
        if (choice == 3) {
            System.out.println("Thanks for Trying -My Pizza- !!");
            return; // Exit the program
        }

        boolean isVeg = (choice == 1);
        
        // Premium Pizza Selection
        System.out.println("\nDo you want premium pizza?");
        System.out.println("\t1. Yes");
        System.out.println("\t2. No\n");
        
        System.out.print("Enter your choice: ");
        choice = scanner.nextInt();
        
        boolean isPremium = (choice == 1);

        // Quantity Selection
        System.out.print("\nEnter the quantity of pizzas: ");
        int quantity = scanner.nextInt();

        Receipt receipt = new Receipt(isVeg, isPremium, quantity);
        double cost = receipt.getCost();

        // Toppings Selection
        System.out.println("\nDo you want to add any toppings?");
        System.out.println("\t1. Cheese");
        System.out.println("\t2. Chicken");
        System.out.println("\t3. Onion");
        System.out.println("\t4. No toppings\n");

        System.out.print("Enter your choice: ");
        choice = scanner.nextInt();
        
        if (choice != 4) {
            String topping = "";
            switch (choice) {
                case 1:
                    topping = "cheese";
                    break;
                case 2:
                    topping = "chicken";
                    break;
                case 3:
                    topping = "onion";
                    break;
                default:
                    System.out.println("Invalid topping choice.");
                    break; // Handle invalid input gracefully
            }
            if (!topping.isEmpty()) {
                cost = receipt.addToppings(cost, topping);
            }
        }

        // Discount Selection
        System.out.println("\nDo you have any discount coupon?");
        System.out.println("\t1. Yes");
        System.out.println("\t2. No\n");

        System.out.print("Enter your choice: ");
        choice = scanner.nextInt();
        
        if (choice == 1) {
            System.out.print("Enter Discount Coupon: ");
            double discount = scanner.nextDouble();
            cost = receipt.addDiscount(cost, discount);
        }

		// Adding Tax
		cost = receipt.addTax(cost);

		// Final Cost Output with S.NO, ITEM, Quantity and Total
		System.out.println("\n\n\t\tThe Final Bill...");
		System.out.printf("%-5s %-20s %-20s %-10s%n", "S.NO", "ITEM", "Quantity", "Total");
		System.out.printf("%-5s %-20s %-20d $%.2f%n", "1",isVeg ? "Veg Pizza" : "Non-Veg Pizza" + (isPremium ? " (Premium)" : ""), quantity, cost);
		
		scanner.close(); // Close the scanner resource
    }
}