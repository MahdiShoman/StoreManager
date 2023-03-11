
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class AddButton extends Inventory {
    //  public String resultAdd ;
    AddButton() {
        super("d");
    }

    GridPane s = new GridPane();


    // initialize  the nodes
    //radio button
    Text brandT = new Text("Brand");
    RadioButton yes = new RadioButton("Yes");
    RadioButton no = new RadioButton("No");

    //text & text field
    Text type = new Text("Type");
    TextField typeField = new TextField();
    Text quantity = new Text("Quantity");
    TextField quantityField = new TextField();
    Text price = new Text("Price");
    TextField priceField = new TextField();
    Text brandName = new Text("Brand Name");
    TextField brandNameField = new TextField();
    Label resultOfAdd = new Label();
    Button saveBT = new Button("Save");
    Button cancelBT = new Button("Cancel");

    public GridPane getS() {
        //set style in the pane
        s.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, new CornerRadii(0), Insets.EMPTY)));

        // style in the texts and buttons
        brandT.setStyle("-fx-font-weight: bold;");
        brandName.setStyle("-fx-font-weight: bold;");
        yes.setStyle("-fx-font-weight: bold;");
        no.setStyle("-fx-font-weight: bold;");
        type.setStyle("-fx-font-weight: bold;");
        quantity.setStyle("-fx-font-weight: bold;");
        price.setStyle("-fx-font-weight: bold;");
        saveBT.setStyle("-fx-font-weight: bold;");
        cancelBT.setStyle("-fx-font-weight: bold;");

        typeField.setStyle("-fx-text-fill: green;");
        brandNameField.setStyle("-fx-text-fill: green;");
        quantityField.setStyle("-fx-text-fill: green;");
        priceField.setStyle("-fx-text-fill: green;");

        resultOfAdd.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");

        AddButtonEvent event = new AddButtonEvent();
        // handle the radio button for clicked
        yes.setOnAction(event);
        no.setOnAction(event);
        //handle the buttons
        saveBT.setOnAction(event);
        cancelBT.setOnAction(event);


        // HBox to Save and cancel button
        HBox hbS_C = new HBox(10);
        hbS_C.getChildren().addAll(saveBT, cancelBT);

        // set the nodes on the pane
        s.add(brandT, 0, 0);
        s.add(yes, 1, 0);
        s.add(no, 2, 0);
        s.add(type, 0, 1);
        s.add(typeField, 1, 1);
        s.add(quantity, 0, 2);
        s.add(quantityField, 1, 2);
        s.add(price, 0, 3);
        s.add(priceField, 1, 3);
        s.add(hbS_C, 1, 5);
        s.add(resultOfAdd, 1, 6, 2, 6);
        s.setVgap(10);
        s.setHgap(10);
        return s;
    }

    public class AddButtonEvent implements EventHandler<ActionEvent> {// the handler button

        @Override
        public void handle(ActionEvent event) {

            if (event.getSource() == saveBT) {

                if (no.isSelected()) {
                    try { // try catch --> string type in int or double field
                        // check if some fields are empty
                        if (typeField.getText().trim().equals("")) {
                            resultOfAdd.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                            resultOfAdd.setText(" type field are empty");
                        } else if (quantityField.getText().trim().equals("")) {
                            resultOfAdd.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                            resultOfAdd.setText("quantity field are empty");
                        } else if (priceField.getText().trim().equals("")) {
                            resultOfAdd.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                            resultOfAdd.setText("price field are empty");
                        } else if (Double.parseDouble(priceField.getText()) < 0) { // check if price are negative
                            resultOfAdd.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                            resultOfAdd.setText("price value  must not  be negative  ");
                        } else { //if all field is fill as it is then save item

                            newItem(typeField.getText(), Integer.parseInt(quantityField.getText()), Double.parseDouble(priceField.getText()));
                            if (result != 1) { // result == 1 means the item is already exists
                                //the result change to 5 to allow to add new item
                                resultOfAdd.setStyle("-fx-text-fill: green; -fx-font-size: 14px;");
                                resultOfAdd.setText(typeField.getText() + " - in stock: " + Integer.parseInt(quantityField.getText()) + " , price: $" + Double.parseDouble(priceField.getText()));

                            } else {
                                resultOfAdd.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                                resultOfAdd.setText(typeField.getText() + " - already exists");
                            }
                            stockReport();

                        }
                    } catch (NumberFormatException e) {
                        resultOfAdd.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                        resultOfAdd.setText("the quantity and price fields just has number values ");
                    }
                }
                if (yes.isSelected()) {
                    try {// try catch --> string type in int or double field
                        // check if some fields are empty
                        if (typeField.getText().trim().equals("")) {
                            resultOfAdd.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                            resultOfAdd.setText(" type field are empty");
                        } else if (quantityField.getText().trim().equals("")) {
                            resultOfAdd.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                            resultOfAdd.setText("quantity field are empty");
                        } else if (priceField.getText().trim().equals("")) {
                            resultOfAdd.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                            resultOfAdd.setText("price field are empty");
                        } else if (Double.parseDouble(priceField.getText()) < 0) { // check if price are negative
                            resultOfAdd.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                            resultOfAdd.setText("price value  must not  be negative  ");
                        } else if (brandNameField.getText().trim().equals("")) {
                            resultOfAdd.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                            resultOfAdd.setText(" brand type field are empty");
                        } else { //if all field is fill as it is then save item
                            // to add the item
                            newItem(brandNameField.getText(), typeField.getText(), Integer.parseInt(quantityField.getText()), Double.parseDouble(priceField.getText()));
                            if (result != 1) { // result == 1 means the item is already exists
                                //the result change to 5 to allow to add new item
                                resultOfAdd.setStyle("-fx-text-fill: green; -fx-font-size: 14px;");
                                resultOfAdd.setText(brandNameField.getText() + " " + typeField.getText() + " - in stock: " + Integer.parseInt(quantityField.getText()) + " , price: $" + Double.parseDouble(priceField.getText()));
                            } else {
                                resultOfAdd.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                                resultOfAdd.setText(brandNameField.getText() + " " + typeField.getText() + " - already exists");
                            }
                            stockReport();

                        }
                    } catch (NumberFormatException e) {
                        resultOfAdd.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                        resultOfAdd.setText("the quantity and price fields just has number values ");
                    }

                }

            } else if (event.getSource() == yes) {// do the yes event

                if (yes.isSelected()) {//to choose just one selection
                    no.setSelected(false);
                    //to not allowed to but the nodes twice
                    if (!s.getChildren().contains(brandName)) {
                        s.add(brandName, 0, 4);
                    }
                    if (!(s.getChildren().contains(brandNameField))) {
                        s.add(brandNameField, 1, 4);
                    }
                }
            } else if (event.getSource() == no) {// do the no event
                if (no.isSelected()) {//to choose just one selection
                    yes.setSelected(false);
                    s.getChildren().remove(brandName);
                    s.getChildren().remove(brandNameField);
                }

            }
        }

    }
}
