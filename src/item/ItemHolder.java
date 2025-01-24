package item;

public class ItemHolder {

    static String[] itemList;

    public ItemHolder() {
        itemList = new String[]{
                "The Ring of Power",
                "Omniscient Boo",
                "Miracle Cure"
        };
    }

    public String[] getItemList() {
        return itemList;
    }
}
