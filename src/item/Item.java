package item;

public class Item {
    private String name;
    private String rarity;
    private int upgradeCount;

    public void setName(String name) {
        this.name = name;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public void setUpgradeCount(int upgradeCount) {
        this.upgradeCount = upgradeCount;
    }

    public String getName() {
        return this.name;
    }

    public String getRarity() {
        return this.rarity;
    }

    public int getUpgradeCount() {
        return this.upgradeCount;
    }

    public void upgrade() {

    };
}
