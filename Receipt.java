package Task2;

import java.util.Scanner;

public class Receipt {
    private boolean isVeg;
    private boolean isPremium;

    public Receipt(boolean isVeg, boolean isPremium) {
        this.isVeg = isVeg;
        this.isPremium = isPremium;
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
        return cost;
    }

    public double addToppings(double cost, String topping) {
        switch (topping) {
            case "cheese":
                cost += 20;
                break;
            case "chicken":
                cost += 50;
                break;
            case "onion":
                cost += 15;
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
        
        System.out.println("Welcome to My Pizza Shop");
        
        // Pizza Type Selection
        System.out.println("Select the pizza type: ");
        System.out.println("1. Veg");
        System.out.println("2. Non-Veg");
        System.out.println("3. Exit");
        
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        
        if (choice == 3) {
            System.out.println("Thanks for Trying -My Pizza- !!");
            return; // Exit the program
        }

        boolean isVeg = (choice == 1);
        
        // Premium Pizza Selection
        System.out.println("Do you want premium pizza?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        
        System.out.print("Enter your choice: ");
        choice = scanner.nextInt();
        
        boolean isPremium = (choice == 1);

        Receipt receipt = new Receipt(isVeg, isPremium);
        double cost = receipt.getCost();

        // Toppings Selection
        System.out.println("Do you want to add any toppings?");
        System.out.println("1. Cheese");
        System.out.println("2. Chicken");
        System.out.println("3. Onion");
        System.out.println("4. No toppings");

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
        System.out.println("Do you have any discount coupon?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        System.out.print("Enter your choice: ");
        choice = scanner.nextInt();
        
        if (choice == 1) {
            System.out.print("Enter Discount Coupon (as a decimal, e.g., 0.10 for 10%): ");
            double discount = scanner.nextDouble();
            cost = receipt.addDiscount(cost, discount);
        }

        // Adding Tax
        cost = receipt.addTax(cost);

        // Final Cost Output
        System.out.printf("Total Cost: %.2f%n", cost);
        
        scanner.close(); // Close the scanner resource
    }
}