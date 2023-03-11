import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class DeleteButton extends  Inventory {
    DeleteButton(){
    super("s");
    }
    GridPane s= new GridPane();
    ComboBox<String> comboBrand = new ComboBox<>();
    Text type = new Text("Type");
    TextField typeField = new TextField();

    Text quantity = new Text("Quantity");
    TextField quantityField = new TextField();

    Text price = new Text("Price");
    TextField priceField = new TextField();


    Button searchBT = new Button("Search");
    Button deleteBT = new Button("Delete");
    Button cancelBT = new Button("Cancel");
    TextField brandNameField = new TextField();
    Alert alert = new Alert(Alert.AlertType.WARNING);
    Label resultOfDelete =  new Label();
    public GridPane getS() {
        s.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, new CornerRadii(0), Insets.EMPTY)));


        type.setStyle("-fx-font-weight: bold;");quantity.setStyle("-fx-font-weight: bold;");
        price.setStyle("-fx-font-weight: bold;");deleteBT.setStyle("-fx-font-weight: bold;");cancelBT.setStyle("-fx-font-weight: bold;");

        typeField.setStyle("-fx-text-fill: green;");
        brandNameField.setStyle("-fx-text-fill: green;");
        quantityField.setStyle("-fx-text-fill: green;");
        priceField.setStyle("-fx-text-fill: green;");

        // cases for combo box
        comboBrand.getItems().addAll("with Brand", " without Brand");
        comboBrand.setEditable(true);


        // HBox to Save and cancel button
        HBox hbS_C = new HBox(10);
        hbS_C.getChildren().addAll(deleteBT,cancelBT);

        //handle the nodes
        DeleteButtonEvent modifyEvent = new DeleteButtonEvent();

        comboBrand.setOnAction(modifyEvent);
        searchBT.setOnAction(modifyEvent);
        deleteBT.setOnAction(modifyEvent);
        cancelBT.setOnAction(modifyEvent);

        // to force the user to enter the case of item
        typeField.setDisable(true);
        quantityField.setDisable(true);
        priceField.setDisable(true);



        s.add(comboBrand,1,0);
        s.add(type,0,1);s.add(typeField,1,1);
        s.add(quantity,0,2);s.add(quantityField,1,2);
        s.add(price,0,3);s.add(priceField,1,3);
        s.add(hbS_C,1,5);
        s.add(resultOfDelete,1,6,2,6);
        s.setVgap(10);
        s.setHgap(10);
        return s;
    }
    public  class DeleteButtonEvent implements EventHandler<ActionEvent> {// the handler button
        @Override
        public void handle(ActionEvent event) {
            typeField.setDisable(true);
            quantityField.setDisable(true);
            priceField.setDisable(true);

            if (event.getSource() ==  comboBrand){

                if(comboBrand.getEditor() == null)
                    return;
                String selectedItem = comboBrand.getEditor().getText().toLowerCase().trim();
                if (selectedItem.equals("")){
                    System.out.println(" ");
                } if(selectedItem.equals("with brand".trim())){
                    typeField.setDisable(false);
                    //to not allowed to but the nodes twice
                    if(!(s.getChildren().contains(brandNameField))){
                        s.add(brandNameField,2,1);
                    }if(!s.getChildren().contains(searchBT))
                        s.add(searchBT,3,1);
                }else
                if (selectedItem.equals("without brand".trim())){
                    typeField.setDisable(false);
                    if (s.getChildren().contains(brandNameField)){
                        s.getChildren().remove(brandNameField);
                    }else if (!s.getChildren().contains(searchBT)){
                        s.add(searchBT,3,1);
                    }else
                        return;
                }

            }else if (event.getSource() ==  searchBT){

                String selectedItem = comboBrand.getEditor().getText().trim();
                if (selectedItem.equals("")){// to set all field to be Disable
                    typeField.setDisable(true);
                    quantityField.setDisable(true);
                    priceField.setDisable(true);
                    System.out.println(" ");
                } if(selectedItem.equals("with Brand".trim())){// the selection is with Brand
                    if (typeField.getText().trim().equals("")){
                        resultOfDelete.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                        resultOfDelete.setText(" type field are empty");
                    }else if(brandNameField.getText().trim().equals("")){
                        resultOfDelete.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                        resultOfDelete.setText(" brand type field are empty");
                    }else{
                        F =findItem(typeField.getText(),false,brandNameField.getText());
                        if( F!= -1){
                            quantityField.setText(inventory.get(F).getQuantity()+"");
                            priceField.setText(inventory.get(F).getPrice()+"");
                            resultOfDelete.setText("the item is exists");
                            quantityField.setDisable(false);
                            priceField.setDisable(false);
                            typeField.setDisable(false);
                        }else {
                            typeField.setDisable(false);
                            quantityField.setDisable(true);
                            priceField.setDisable(true);
                            alert.setTitle("Warning ");
                            alert.setHeaderText("the item not found !!");
                            //  alert.setContentText("Careful with the next step!");
                            alert.showAndWait();
                            resultOfDelete.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                            resultOfDelete.setText("the item not found");
                        }
                    }
                }
                if (selectedItem.equals("without Brand".trim())){
                    if (typeField.getText().trim().equals("")){
                        resultOfDelete.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                        resultOfDelete.setText(" type field are empty");
                    }else{
                        F =findItem(typeField.getText(),false);
                        if( F!= -1){
                            quantityField.setText(inventory.get(F).getQuantity()+"");
                            priceField.setText(inventory.get(F).getPrice()+"");
                            resultOfDelete.setText("the item is exists");
                            quantityField.setDisable(false);
                            priceField.setDisable(false);
                        }else {
                            quantityField.setDisable(true);
                            priceField.setDisable(true);
                            resultOfDelete.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                            resultOfDelete.setText("the item not found");
                        }
                    }
                }


            }else if (event.getSource() ==  deleteBT){

                System.out.println(inventory.size());
                Object selectedItem = comboBrand.getEditor().getText().trim();
                System.out.println(comboBrand.getEditor().getText());
                if(selectedItem.equals("with Brand".trim())){
                    // check if some fields are empty
                    if (typeField.getText().trim().equals("")){
                        resultOfDelete.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                        resultOfDelete.setText(" type field are empty");
                    }else if(brandNameField.getText().trim().equals("")){
                        resultOfDelete.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                        resultOfDelete.setText(" brand type field are empty");
                    }else{
                        delete(brandNameField.getText(),typeField.getText(),Integer.parseInt(quantityField.getText().trim()),Double.parseDouble(priceField.getText().trim()));
                        resultOfDelete.setText(" the " + typeField.getText() + " is deleted");
                    }
                    //alert.setHeaderText(resultUpdate);
                    // alert.setContentText("Careful with the next step!");
                    //  alert.showAndWait();

                }if (selectedItem.equals("without Brand".trim())) {
                    // check if some fields are empty
                    if (typeField.getText().trim().equals("")) {
                        resultOfDelete.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                        resultOfDelete.setText(" type field are empty");
                    }else{
                        delete(typeField.getText(),Integer.parseInt(quantityField.getText().trim()),Double.parseDouble(priceField.getText().trim()));
                        resultOfDelete.setText(" the " + typeField.getText() + " is deleted");
                    }
                }
                typeField.setDisable(false);
                quantityField.setDisable(true);
                priceField.setDisable(true);
            }


        }
    }
}
