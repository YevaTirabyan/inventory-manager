//TIP WELCOME TO MY GAME DEVELOPMENT TEST TASK!
// I'm late, but I hope I can still impress you.
import inventary.Inventory;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    static Inventory inventory;

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hey! It's been a while!");
        System.out.println("Let's check out what you've got in your inventory.");

        Scanner scanner = new Scanner(System.in);
        System.out.println("To open your inventory, input [i]: ");
        String input = scanner.nextLine();
        if (Objects.equals(input, "i")) {
            inventory = new Inventory();
            if(inventory.getItemCount() == 0) {
                hasNoInventory();
            }
        }
    }

    public static void hasInventory() {
        System.out.println("Go ahead, feel free to explore. You know the basics, right?");
        System.out.println(" [a] to add something to the list, [o] for your inventory, and [e] to quit.");
        System.out.println("Easy peasy!");

        Scanner scanner = new Scanner(System.in);
        boolean shouldBreak = false;
        while(!shouldBreak) {
            System.out.println("Never let others predict your next move...");
            System.out.println("Your next input: ");
            String input = scanner.nextLine();
            switch (input) {
                case "a":
                    inventory.addNewItem();
                    break;
                case "o":
                    inventory.showInventoryItemList();
                    break;
                case "e":
                    System.out.print("Bye-bye!");
                    shouldBreak = true;
                    break;
            }
        }
    }

    public static void hasNoInventory() {
        System.out.println("It feels, like you have nothing...");
        System.out.println("Let's fix it! " +
                "Input [g] to generate random start pack or add your own item by inputting [a]: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (Objects.equals(input, "g")) {
            inventory.genNewInventoryList();
        } else if (Objects.equals(input, "a")) {
            inventory.addNewItem();
        }
        inventory.showInventoryItemList();
        hasInventory();
    }
}