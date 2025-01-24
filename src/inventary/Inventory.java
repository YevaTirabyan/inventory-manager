package inventary;

import item.Item;
import item.ItemHolder;

import java.util.Objects;
import java.util.Scanner;
import java.util.Random;

public class Inventory {
    int capacity = 15;
    int itemCount = 0;
    Item[] items = new Item[15];

    public int getItemCount() {
        if (this.items == null) {
            return 0;
        }
        return this.itemCount;
    }

    public void addNewItem() {
        if (getItemCount() == this.capacity) {
            System.out.print("You have to much staff in your inventory!");
            return;
        }
        System.out.print("Input item name: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("It's time to pick rarity for " + name + ". Input [c] for common, " +
                "[g] for great, [r] for rare, [e] for epic and [l] for legendary: ");
        String rarity = scanner.nextLine();
        switch (rarity) {
            case "c":
                createItem(name, "Common");
                break;
            case "g":
                createItem(name, "Great");
                break;
            case "r":
                createItem(name, "Rare");
                break;
            case "e":
                createItem(name, "Epic");
                break;
            case "l":
                createItem(name, "Legendary");
                break;
            default:
                System.out.print("There's no rarity like that! Try again.");
                addNewItem();
        }
    }

    public void showInventoryItemList() {
        System.out.println("Your inventory: ");
        for (int i = 0; i < this.itemCount; i++) {
            System.out.println("ID: " + i + ", name: " + this.items[i].getName()
                    + ", rarity: " + this.items[i].getRarity());
        }
        System.out.println("Do you want upgrade one or few of your items? [y/n]: ");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        switch (answer) {
            case "y":
                System.out.print("Pick item for upgrade by ID: ");
                String id = scanner.nextLine();
                if (Integer.parseInt(id) >= 0 && Integer.parseInt(id) < this.itemCount) {
                    upgradeItem(Integer.parseInt(id));
                }
        }
    }

    public void genNewInventoryList() {
        Random random = new Random();
        int randomNumber;
        int randomNameID;
                
        ItemHolder itemHolder = new ItemHolder();
        String[] itemList = itemHolder.getItemList();
        
        for (int i = 0; i < 3; i++) {
            randomNameID = random.nextInt(2);
            Item item = new Item();
            item.setName(itemList[randomNameID]);
            item.setUpgradeCount(0);

            randomNumber = random.nextInt(100) + 1;
            if (randomNumber <= 50) {
                item.setRarity("Common");
            } else if (randomNumber <= 75) {
                item.setRarity("Great");
            } else if (randomNumber <= 90) {
                item.setRarity("Rare");
            } else if (randomNumber <= 98){
                item.setRarity("Epic");
            } else {
                item.setRarity("Legendary");
            }

            int id = getItemCount();
            this.items[id] = item;
            this.itemCount += 1;
        }
    }

    private void createItem(String name, String rarity) {
        Item item = new Item();
        item.setName(name);
        item.setRarity(rarity);
        item.setUpgradeCount(0);
        int id = getItemCount();
        this.items[id] = item;
        this.itemCount += 1;
    }

    private void upgradeItem(int id) {
        Item item = this.items[id];
        if (item == null) {
            System.out.println("INTERNAL SERVER ERROR!!!");
            System.out.println("Just kidding, but something went totally wrong");
            return;
        }

        String name = item.getName();
        String rarity = item.getRarity();
        int upgradeCount = item.getUpgradeCount();
        int count = 0;
        for (int i = 0; i < this.itemCount; i++) {
            if (i == id) {
                continue;
            }
            if ((!Objects.equals(rarity, "Epic") || (upgradeCount < 2))
                    && Objects.equals(this.items[i].getRarity(), rarity)
                    && Objects.equals(this.items[i].getName(), name)
                    && this.items[0].getUpgradeCount() <=  upgradeCount) {
                count += 1;
            }
            if (Objects.equals(rarity, "Epic") && upgradeCount == 2
                    && Objects.equals(this.items[i].getRarity(), rarity)
                    && Objects.equals(this.items[i].getName(), name)
                    && upgradeCount == this.items[i].getUpgradeCount()) {
                count += 1;
            }
        }

        switch (rarity) {
            case "Common":
            case "Great":
            case "Rare":
                if(count == 1) {
                    System.out.println("Oops! You miss one element to upgrade... Try again soon!");
                    return;
                } else if (count == 0) {
                    System.out.println("Oops! You don't have any to upgrade. Try again soon!");
                    return;
                }
                break;
            case "Epic":
                if(upgradeCount == 2 && count == 1) {
                    System.out.println("Oops! You miss one element to upgrade... Try again soon!");
                    return;
                } else if (upgradeCount == 2 && count == 0) {
                    System.out.println("Oops! You don't have any to upgrade. Try again soon!");
                    return;
                } else if (count == 0) {
                    System.out.println("Oops! You don't have any to upgrade. Try again soon!");
                    return;
                }
                break;
        }


    }
}
