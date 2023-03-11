import java.io.Serializable;
import java.text.DecimalFormat;

public class Item implements Comparable<Item> , Serializable {
    private String type;
    private int quantity;
    private double price;

    public Item() {
    }

    public Item(String type) {
        this.type = type;
    }


    public Item setQuantity(int quantity) { //set a quantity and return the obj
        this.quantity = quantity;
        return this;
    }

    public Item setPrice(double price) { //set a price and return the obj
        this.price = price;
        return this;
    }

    public double getPrice() {
      return Double.parseDouble( new DecimalFormat("###.##").format(price));
    }

    public int getQuantity() {
        return quantity;
    }

    public String getType() {
        return type;
    }
    public Item update (int qtyIncrease ){              // revalue qty
        this.setQuantity(this.getQuantity()+qtyIncrease)  ;
        return this;
    }
    public Item update (double adjustmentFactor){       // update th price of item
        this.setPrice(this.getPrice()+(adjustmentFactor*getPrice()));
        return this;
    }

    @Override
    public int compareTo(Item o) {      // show who is the large between the two item
        int  largerItem = 0;
        largerItem= (int)(this.getPrice()-o.getPrice());
        return largerItem;
    }
}
