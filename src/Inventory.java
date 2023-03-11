import java.io.*;
import java.util.ArrayList;

public class Inventory {
    public static ArrayList<Item> inventory;
    private String category;
    int F;

    //  AddButton existsResult = new AddButton();
    // some thing habed
    public int result;
    // public String resultUpdate;

    public Inventory(String category) {
        this.category = category;
        inventory = new ArrayList<>();
    }

    public void newItem(String type, int quantity, double price) {       // to enter a new item in array
        if ((findItem(type, true)) == -1) { //-1 is found
            result = 5;
            inventory.add(new Item(type).setQuantity(quantity).setPrice(price));// set a quantity & price of obj
        }

    }

    public void newItem(String brand, String type, int quantity, double price) {   // to enter a new item with brand in array
        if ((findItem(type, true, brand)) == -1) { //-1 is found
            result = 6;
            inventory.add(new Brand(brand, type).setQuantity(quantity).setPrice(price)); // set a quantity & price of obj
        }
    }

    public void setQuantity(String type, int quantity) {
        F = findItem(type, false);
        if ((F) != -1) {// is  found

            inventory.get(F).setQuantity(quantity);
        }

    }

    public void setQuantity(String brand, String type, int quantity) {// go and check if the type is brand(in the find item method)
        F = findItem(type, false, brand);
        if ((F) != -1) {// is not found
            inventory.get(F).setQuantity(quantity);
        }

    }

    public void setPrice(String type, double price) {
        F = findItem(type, false);
        if ((F) != -1) {// is not found
            inventory.get(F).setPrice(price);
        }
    }

    public void setPrice(String brand, String type, double price) {
        F = findItem(type, false, brand);
        if ((F) != -1) {// is  found
            inventory.get(F).setPrice(price);
        }
    }

    public int getQuantity(String type) {
        if ((findItem(type, false)) != -1) {// is  found
            return (inventory.get(findItem(type, false)).getQuantity());
        } else {
            return -1;
        }
    }

    public int getQuantity(String brand, String type) {
        if ((findItem(type, false, brand)) != -1) {// is  found
            return (inventory.get(findItem(type, false, brand)).getQuantity());
        } else {

            return 0;
        }
    }

    public double getPrice(String type) {
        if ((findItem(type, false)) != -1) {// is  found
            return (inventory.get(findItem(type, false)).getPrice());
        } else
            return Double.NaN;
    }

    public double getPrice(String brand, String type) {
        if ((findItem(type, false, brand)) != -1) {// is  found
            return (inventory.get(findItem(type, false, brand)).getPrice());
        } else {
            return Double.NaN;
        }
    }

    public void update(String type, int qtyIncrease) { // update the qty of the item
        F = findItem(type, false);
        if (F != -1) {// is  found
            inventory.get(F).update(qtyIncrease); // sent this to Item class
        }
    }

    public void update(String brand, String type, int qtyIncrease) {// update the qty of the item with brand
        F = findItem(type, false, brand);
        if (F != -1) {// is  found
            inventory.get(F).update(qtyIncrease);
        }
    }

    public void update(String type, double adjustmentFactor) {
        F = findItem(type, false);
        if (F != -1) {// is  found
            inventory.get(F).update(adjustmentFactor);
        }
    }

    public void update(String brand, String type, double adjustmentFactor) {
        F = findItem(type, false, brand);
        if (F != -1) {// is  found
            inventory.get(F).update(adjustmentFactor);
        }
    }

    public void delete(String brand, String type, int quantity, double price) {
        F = findItem(type, false, brand);
        if (F != -1) {// is  found
            inventory.remove(F);
        }

    }

    public void delete(String type, int quantity, double price) {
        F = findItem(type, false);
        if (F != -1) {// is  found
            inventory.remove(F);
        }

    }

    protected int findItem(String type, boolean warningIfFound) {// check the type if found and sent the index of it
        int index = -1;
        int itemsFound = 0;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getType().equals(type)) {
                itemsFound++;
                //inventory.set(index,inventory.get(i));
                index = i;
            }
        }
        if (itemsFound == 0 && !warningIfFound) {
            System.out.println("\ncan't find  " + type);

        }
        if (itemsFound != 0 && warningIfFound) {
            System.out.println(type + " already exists");
            result = 1;

        }
        if (itemsFound > 1) {
            System.out.println("found more than one brand");

        }
        if (itemsFound == 1) {

            return index;
        }
        return -1;
    }
    //set alert

    protected int findItem(String type, boolean warningIfFound, String brand) { // can check the item if is brand and return the index if it found
        int index = -1;
        int itemsFound = 0;
        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            if (type.equals(item.getType()) && item instanceof Brand && brand.equals(((Brand) item).getBrand())) {//check if brand
                itemsFound++;
                index = i;
            }
        }
        if (itemsFound == 0 && !warningIfFound) {
            System.out.println("can't find  " + brand + " " + type);

        }
        if (itemsFound != 0 && warningIfFound) {
            System.out.println(brand + " " + type + " already exists");
            result = 1;

        }
        if (itemsFound == 1) {

            return index;
        }
        return -1;
    }

    public static void stockReport() { // print the report of all item in the array
        double totalValue = 0;
        for (Item item : inventory) {

            if (item instanceof Brand) {
                totalValue += item.getPrice() * item.getQuantity();
                System.out.println(((Brand) item).getBrand() + item.getType() + " - in stock: " + item.getQuantity() + " , price: $" + item.getPrice());
            } else {
                totalValue += item.getPrice() * item.getQuantity();
                System.out.println(item.getType() + " - in stock: " + item.getQuantity() + " , price: $ " + item.getPrice());
            }
        }
        System.out.printf("Total value: $%.2f \n ", totalValue);
    }

    public void printData() {

        try {

            System.out.println("print data");
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("inventoryDatabase.txt"));

            for (Item i : inventory) {
                out.writeObject(i);
                System.out.println(i.getType());
            }
            out.close();
            System.out.println("Done");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public static void readData() {
        try {
            System.out.println("read data");

            ObjectInputStream in = new ObjectInputStream(new FileInputStream("inventoryDatabase.txt"));
            while (true) {
                inventory.add((Item) in.readObject());

            }

        } catch (Exception e) {
            System.out.println("Done");
        }

    }


}
