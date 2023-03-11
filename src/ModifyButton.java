import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class ModifyButton extends Inventory {

    ModifyButton() {
        super("groceries");

    }

    GridPane s = new GridPane();
    ComboBox<String> comboBrand = new ComboBox<>();

    Text type = new Text("Type");
    TextField typeField = new TextField("Item Type");
    TextField brandNameField = new TextField("Brand Name");

    Text quantity = new Text("Quantity");
    TextField quantityField = new TextField();

    Text price = new Text("Price");
    TextField priceField = new TextField();

    Button searchBT = new Button("Search");
    Button updateBT = new Button("Update");
    Button cancelBT = new Button("Cancel");

    Alert alertWarning = new Alert(Alert.AlertType.WARNING);
    Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
    Label resultOfUpdate = new Label();

    public GridPane getS() {
        // set style on pane
        s.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, new CornerRadii(0), Insets.EMPTY)));

        //style in the texts & buttons
        type.setStyle("-fx-font-weight: bold;");
        quantity.setStyle("-fx-font-weight: bold;");
        price.setStyle("-fx-font-weight: bold;");
        searchBT.setStyle("-fx-font-weight: bold;");
        cancelBT.setStyle("-fx-font-weight: bold;");
        updateBT.setStyle("-fx-font-weight: bold;");

        typeField.setStyle("-fx-text-fill: green;");
        brandNameField.setStyle("-fx-text-fill: green;");
        quantityField.setStyle("-fx-text-fill: green;");
        priceField.setStyle("-fx-text-fill: green;");

//        resultUpdate.setStyle("-fx-text-fill: green;");

        // cases for combo box
        comboBrand.getItems().addAll("with Brand", "without Brand");
        comboBrand.setEditable(true);

        // HBox to Save and cancel button
        HBox hbS_C = new HBox(10);
        hbS_C.getChildren().addAll(updateBT, cancelBT);

        //handle the nodes
        ModifyButtonEvent modifyEvent = new ModifyButtonEvent();

        comboBrand.setOnAction(modifyEvent);
        searchBT.setOnAction(modifyEvent);
        updateBT.setOnAction(modifyEvent);
        cancelBT.setOnAction(modifyEvent);
        typeField.setOnAction(modifyEvent);
        brandNameField.setOnAction(modifyEvent);

        // to force the user to enter the case of item
        typeField.setDisable(true);
        quantityField.setDisable(true);
        priceField.setDisable(true);


        // set nods in pane
        s.add(comboBrand, 1, 0);
        s.add(type, 0, 1);
        s.add(typeField, 1, 1);
        s.add(quantity, 0, 2);
        s.add(quantityField, 1, 2);
        s.add(price, 0, 3);
        s.add(priceField, 1, 3);
        s.add(hbS_C, 1, 5);
        s.add(resultOfUpdate, 1, 6, 2, 6);
        s.setVgap(10);
        s.setHgap(10);
        return s;
    }

    public class ModifyButtonEvent implements EventHandler<ActionEvent> {// the handler button

        @Override
        public void handle(ActionEvent event) {

            if (event.getSource() == searchBT) {
                // set event to combo box
                Object selectedItem = comboBrand.getEditor().getText().trim();
                if (selectedItem.equals("")) {// to set all field to be Disable
                    typeField.setDisable(true);
                    quantityField.setDisable(true);
                    priceField.setDisable(true);
                    System.out.println(" ");
                }
                if (selectedItem.equals("with Brand".trim())) {// the selection is with Brand
                    if (typeField.getText().trim().equals("")) {
                        resultOfUpdate.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                        resultOfUpdate.setText(" type field are empty");
                    } else if (brandNameField.getText().trim().equals("")) {
                        resultOfUpdate.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                        resultOfUpdate.setText(" brand type field are empty");
                    } else {
                        F = findItem(typeField.getText(), false, brandNameField.getText());
                        if (F != -1) { // if item is found then load it
                            quantityField.setText(inventory.get(F).getQuantity() + "");
                            priceField.setText(inventory.get(F).getPrice() + "");
                            resultOfUpdate.setStyle("-fx-text-fill: green; -fx-font-size: 14px;");
                            resultOfUpdate.setText("the item is exists");
                            quantityField.setDisable(false);
                            priceField.setDisable(false);
                        } else { // show the warning
                            quantityField.setDisable(true);
                            priceField.setDisable(true);
                            updateBT.setDisable(true);
                            resultOfUpdate.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                            resultOfUpdate.setText("the item not found");
                            alertWarning.setTitle("Warning ");
                            alertWarning.setHeaderText("the item not found !!");
                            alertWarning.showAndWait();

                        }
                    }
                }
                if (selectedItem.equals("without Brand".trim())) {// the selection is without Brand
                    if (typeField.getText().trim().equals("")) {
                        resultOfUpdate.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                        resultOfUpdate.setText(" type field are empty");
                    } else {
                        F = findItem(typeField.getText(), false);
                        if (F != -1) {// if item is found then load it
                            quantityField.setText(inventory.get(F).getQuantity() + "");
                            priceField.setText(inventory.get(F).getPrice() + "");
                            resultOfUpdate.setStyle("-fx-text-fill: green; -fx-font-size: 14px;");
                            resultOfUpdate.setText("the item is exists");
                            quantityField.setDisable(false);
                            priceField.setDisable(false);
                        } else {// show the warning
                            quantityField.setDisable(true);
                            priceField.setDisable(true);
                            updateBT.setDisable(true);
                            resultOfUpdate.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                            resultOfUpdate.setText("the item not found");
                            alertWarning.setTitle("Warning ");
                            alertWarning.setHeaderText("the item not found !!");
                            alertWarning.showAndWait();

                        }
                    }
                }

            } else if (event.getSource() == updateBT) {

                System.out.println("you have " + inventory.size() + "item in your invoice");
                String selectedItem = comboBrand.getEditor().getText().trim();
                if (selectedItem.equals("with Brand".trim())) {
                    try { // check if some fields are empty
                        if (typeField.getText().trim().equals("")) {
                            resultOfUpdate.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                            resultOfUpdate.setText(" type field are empty");
                        } else if (quantityField.getText().trim().equals("")) {
                            resultOfUpdate.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                            resultOfUpdate.setText("quantity field are empty");
                        } else if (priceField.getText().trim().equals("")) {
                            resultOfUpdate.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                            resultOfUpdate.setText("price field are empty");
                        } else if (Double.parseDouble(priceField.getText()) < 0) { // check if price are negative
                            resultOfUpdate.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                            resultOfUpdate.setText("price value  must not  be negative  ");
                        } else if (brandNameField.getText().trim().equals("")) {
                            resultOfUpdate.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                            resultOfUpdate.setText(" brand type field are empty");
                        } else if (!quantityField.getText().trim().equals("") && !priceField.getText().trim().equals("")) {  // check if price and quantity is not empty to update  it

                            update(brandNameField.getText(), typeField.getText(), Double.parseDouble(priceField.getText().trim()));
                            update(brandNameField.getText(), typeField.getText(), Integer.parseInt(quantityField.getText().trim()));

                            F = findItem(typeField.getText(), false, brandNameField.getText());
                            resultOfUpdate.setStyle("-fx-text-fill: green; -fx-font-size: 14px;");
                            resultOfUpdate.setText(brandNameField.getText() + " " + typeField.getText() + " - in stock: " + inventory.get(F).getQuantity() + " , price: $" + inventory.get(F).getPrice());

                            alertInformation.setHeaderText(resultOfUpdate.getText());
                            alertInformation.setContentText("the update is Done");
                            alertInformation.showAndWait();
                        } else if (priceField.getText().trim().equals("0")) {
                            update(brandNameField.getText(), typeField.getText(), Integer.parseInt(quantityField.getText().trim()));

                            F = findItem(typeField.getText(), false, brandNameField.getText());
                            resultOfUpdate.setStyle("-fx-text-fill: green; -fx-font-size: 14px;");
                            resultOfUpdate.setText(brandNameField.getText() + " " + typeField.getText() + " - in stock: " + inventory.get(F).getQuantity() + " , price: $" + inventory.get(F).getPrice());

                            alertInformation.setHeaderText(resultOfUpdate.getText());
                            alertInformation.setContentText(" update quantity is Done");
                            alertInformation.showAndWait();

                        } else if (quantityField.getText().trim().equals("0")) {
                            update(brandNameField.getText(), typeField.getText(), Double.parseDouble(priceField.getText().trim()));

                            F = findItem(typeField.getText(), false, brandNameField.getText());
                            resultOfUpdate.setStyle("-fx-text-fill: green; -fx-font-size: 14px;");
                            resultOfUpdate.setText(brandNameField.getText() + " " + typeField.getText() + " - in stock: " + inventory.get(F).getQuantity() + " , price: $" + inventory.get(F).getPrice());

                            alertInformation.setHeaderText(resultOfUpdate.getText());
                            alertInformation.setContentText("update price is Done");
                            alertInformation.showAndWait();

                        }
                    } catch (NumberFormatException e) {
                        System.out.println("the quantity and price fields just has number values ");
                    }

                }
                if (selectedItem.equals("without Brand".trim())) {
                    try {  // check if some fields are empty
                        if (typeField.getText().trim().equals("")) {
                            resultOfUpdate.setText(" type field are empty");
                        } else if (quantityField.getText().trim().equals("")) {
                            resultOfUpdate.setText("quantity field are empty");
                        } else if (priceField.getText().trim().equals("")) {
                            resultOfUpdate.setText("price field are empty");
                        } else if (Double.parseDouble(priceField.getText()) < 0) { // check if price are negative
                            resultOfUpdate.setText("price value  must not  be negative  ");
                        } else if (!quantityField.getText().trim().equals("") && !priceField.getText().trim().equals("")) {  // check if price and quantity is not empty to update  it

                            update(typeField.getText(), Double.parseDouble(priceField.getText().trim()));
                            update(typeField.getText(), Integer.parseInt(quantityField.getText().trim()));

                            F = findItem(typeField.getText(), false);
                            resultOfUpdate.setStyle("-fx-text-fill: green; -fx-font-size: 14px;");
                            resultOfUpdate.setText(typeField.getText() + " - in stock: " + inventory.get(F).getQuantity() + " , price: $" + inventory.get(F).getPrice());

                            alertInformation.setHeaderText(resultOfUpdate.getText());
                            alertInformation.setContentText("the update is Done");
                            alertInformation.showAndWait();

                        } else if (priceField.getText().trim().equals("0")) {
                            update(typeField.getText(), Integer.parseInt(quantityField.getText().trim()));

                            F = findItem(typeField.getText(), false);
                            resultOfUpdate.setStyle("-fx-text-fill: green; -fx-font-size: 14px;");
                            resultOfUpdate.setText(typeField.getText() + " - in stock: " + inventory.get(F).getQuantity() + " , price: $" + inventory.get(F).getPrice());

                            alertInformation.setHeaderText(resultOfUpdate.getText());
                            alertInformation.setContentText("update price is Done");
                            alertInformation.showAndWait();


                        } else if (quantityField.getText().trim().equals("0")) {
                            update(typeField.getText(), Double.parseDouble(priceField.getText().trim()));

                            F = findItem(typeField.getText(), false);
                            resultOfUpdate.setStyle("-fx-text-fill: green; -fx-font-size: 14px;");
                            resultOfUpdate.setText(typeField.getText() + " - in stock: " + inventory.get(F).getQuantity() + " , price: $" + inventory.get(F).getPrice());

                            alertInformation.setHeaderText(resultOfUpdate.getText());
                            alertInformation.setContentText("update quantity is Done");
                            alertInformation.showAndWait();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("the quantity and price fields just has number values ");
                    }
                }


            } else if (event.getSource() == comboBrand) {

                alertWarning.setHeaderText("if you don't need to update a price or quantity enter number 0 in the field");
                alertWarning.setContentText("Careful with the next step!");
                alertWarning.showAndWait();
                Object selectedItem = comboBrand.getEditor().getText().trim();
                if (selectedItem.equals("")) {
                    //to push the user to choose the type if it has a brand or not
                    typeField.setDisable(true);
                    quantityField.setDisable(true);
                    priceField.setDisable(true);
                    System.out.println(" ");
                } else if (selectedItem.equals("with Brand".trim())) {
                    typeField.setDisable(false);
                    //to not allowed to but the nodes twice
                    if (!(s.getChildren().contains(brandNameField))) {
                        s.add(brandNameField, 2, 1);
                    }
                    if (!s.getChildren().contains(searchBT))
                        s.add(searchBT, 3, 1);
                } else if (selectedItem.equals("without Brand".trim())) {
                    typeField.setDisable(false);
                    if (s.getChildren().contains(brandNameField)) {
                        s.getChildren().remove(brandNameField);
                    } else if (s.getChildren().contains(searchBT)) {
                        return;
                    } else
                        s.add(searchBT, 3, 1);
                }

            } else if (event.getSource() == typeField) {
                typeField.clear();

            } else if (event.getSource() == brandNameField) {
                brandNameField.clear();

            }

        }
    }


}
