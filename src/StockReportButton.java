import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;


import java.io.*;


public class StockReportButton extends ModifyButton {
    StockReportButton() {

    }

    GridPane s = new GridPane();
    Label l = new Label("The following options can be used to print a report");
    CheckBox exportCopyToFileCHB = new CheckBox("Export a Copy To a File");
    CheckBox textAreaCHB = new CheckBox("textArea");
    TextField nameFileField = new TextField();
    Button exportBT = new Button("Export");
    TextArea textAreaReport = new TextArea();//35
    Alert alert = new Alert(Alert.AlertType.WARNING);

    public GridPane getS() {

        // set style on pane
        s.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, new CornerRadii(0), Insets.EMPTY)));
        //style texts on texts and button
        textAreaReport.setStyle("-fx-font-weight: bold;");
        textAreaReport.setStyle("-fx-font-weight: bold;");
        exportCopyToFileCHB.setStyle("-fx-font-weight: bold;");
        exportBT.setStyle("-fx-font-weight: bold;");

        textAreaReport.setMaxHeight(300);
        textAreaReport.setMaxWidth(400);

        // handle the buttons and texts
        StockReportButtonEvent stockReportEvent = new StockReportButtonEvent();
        // handle  the export Copy To File Check Box
        exportCopyToFileCHB.setOnAction(stockReportEvent);
        textAreaCHB.setOnAction(stockReportEvent);
        exportBT.setOnAction(stockReportEvent);

 /*       exportCopyToFileCHB.setOnMouseClicked(new EventHandler<MouseEvent>()  {
            @Override
            public void handle(MouseEvent event) {
                    if(exportCopyToFileCHB.isSelected()){
                        s.add(nameFileField,1,3);
                        s.add(exportBT,1,4);
                    }else
                        s.getChildren().removeAll(nameFileField,exportBT);
                    stockReport();

            }
        });
*/



     /*   textAreaCHB.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if(textAreaCHB.isSelected()){
                    s.add(textAreaReport,2,5,3,6);
                    double totalValue = 0;
                    for (Item item : inventory) {

                        if (item instanceof Brand) {
                            totalValue+=item.getPrice()*item.getQuantity();
                            //   result = (((Brand) item).getBrand() + item.getType() + "- in stock: " + item.getQuantity() + ", price: $" + item.getPrice());
                            textAreaReport.appendText("\n"+((Brand) item).getBrand() +" "+ item.getType() + " - in stock: " + item.getQuantity() + " , price: $" + item.getPrice());
                        } else {
                            totalValue+=item.getPrice()*item.getQuantity();
                            //   result = (item.getType() + "- in stock: " + item.getQuantity() + ", price:$  " +  item.getPrice())+"zzzz";
                            textAreaReport.appendText("\n"+item.getType() + " - in stock: " + item.getQuantity() + " , price:$  " +  item.getPrice());
                        }
                    }
                    textAreaReport.appendText( "\n"+"\n"+"you have [ "+inventory.size()+" ] item in your invoice");
                    textAreaReport.appendText( "\n"+"\n"+"------------------------------------"+"\n");
                    textAreaReport.appendText( "\n"+"the Total value of items in your invoice is : $"+totalValue);
                }else{
                    textAreaReport.clear();
                    s.getChildren().remove(textAreaReport);
                stockReport();
                }

            }
        });*/


        // handle the export Button
  /*      exportBT.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                    if (nameFileField.getText().trim().equals("")){
                        alert.setHeaderText("the name file field is empty");
                         alert.setContentText("Enter the name of file");
                          alert.showAndWait();
                    }else {
                        try {
                            FileWriter f = new FileWriter(nameFileField.getText());

                            double totalValue = 0;
                            for (Item item : inventory) {

                                if (item instanceof Brand) {
                                    totalValue+=item.getPrice()*item.getQuantity();
                                    //   result = (((Brand) item).getBrand() + item.getType() + "- in stock: " + item.getQuantity() + ", price: $" + item.getPrice());
                                    f.write("\n"+((Brand) item).getBrand() +" "+ item.getType() + " - in stock: " + item.getQuantity() + " , price: $" + item.getPrice());
                                } else {
                                    totalValue+=item.getPrice()*item.getQuantity();
                                    //   result = (item.getType() + "- in stock: " + item.getQuantity() + ", price:$  " +  item.getPrice())+"zzzz";
                                    f.write("\n"+item.getType() + " - in stock: " + item.getQuantity() + " , price:$  " +  item.getPrice());
                                }
                            }
                            f.write( "\n"+"the Total value in your invoice is : $"+totalValue);
                            f.close();

                        } catch (IOException e){
                            System.out.println("the is not exists");
                            alert.setHeaderText("the is not exists");
                            alert.setContentText("Enter the Correct name of file");
                            alert.showAndWait();
                    }
                    }


            }
        });*/


        // add nodes in the pane
        s.add(l, 1, 1, 2, 1);
        s.add(exportCopyToFileCHB, 1, 2);//s.add(exportCopyToFileText,2,2);
        s.add(textAreaCHB, 2, 2);//s.add(textAreaText,4,2);


        return s;
    }

    //inner classes
    public class StockReportButtonEvent implements EventHandler<ActionEvent> {// the handler button

        @Override
        public void handle(ActionEvent event) {

            if (event.getSource() == exportCopyToFileCHB) {

                if (exportCopyToFileCHB.isSelected()) { //  to add buttons and fields
                    s.add(nameFileField, 1, 3);
                    s.add(exportBT, 1, 4);
                } else
                    s.getChildren().removeAll(nameFileField, exportBT);
                stockReport();


            } else if (event.getSource() == textAreaCHB) {

                if (textAreaCHB.isSelected()) {
                    s.add(textAreaReport, 2, 5, 3, 6);
                    double totalValue = 0;
                    for (Item item : inventory) {

                        if (item instanceof Brand) {
                            totalValue += item.getPrice() * item.getQuantity();
                            //   result = (((Brand) item).getBrand() + item.getType() + "- in stock: " + item.getQuantity() + ", price: $" + item.getPrice());
                            textAreaReport.appendText("\n" + ((Brand) item).getBrand() + " " + item.getType() + " - in stock: " + item.getQuantity() + " , price: $" + item.getPrice());
                        } else {
                            totalValue += item.getPrice() * item.getQuantity();
                            //   result = (item.getType() + "- in stock: " + item.getQuantity() + ", price:$  " +  item.getPrice())+"zzzz";
                            textAreaReport.appendText("\n" + item.getType() + " - in stock: " + item.getQuantity() + " , price:$  " + item.getPrice());
                        }
                    }
                    textAreaReport.appendText("\n" + "\n" + "you have [ " + inventory.size() + " ] item in your invoice");
                    textAreaReport.appendText("\n" + "\n" + "------------------------------------" + "\n");
                    textAreaReport.appendText("\n" + "the Total value of items in your invoice is : $" + totalValue);
                } else {
                    textAreaReport.clear();
                    s.getChildren().remove(textAreaReport);
                    stockReport();
                }


            } else if (event.getSource() == exportBT) {
                printData();
                if (nameFileField.getText().trim().equals("")) {
                    alert.setHeaderText("the name file field is empty");
                    alert.setContentText("Enter the name of file");
                    alert.showAndWait();
                } else {

                    try {

                        FileWriter f = new FileWriter(nameFileField.getText());


                        for (Item item : inventory) {

                            if (item instanceof Brand) {
                                f.write("\n" + ((Brand) item).getBrand() + " " + item.getType() + " - in stock: " + item.getQuantity() + ", price: $" + item.getPrice());
                            } else {
                                f.write("\n" + item.getType() + " - in stock: " + item.getQuantity() + ", price: $" + item.getPrice());
                            }
                        }
                        f.close();


                    } catch (IOException e) {
                        System.out.println("the is not exists");
                        alert.setHeaderText("the is not exists");
                        alert.setContentText("Enter the Correct name of file");
                        alert.showAndWait();
                    }
                }


            }

        }
    }
}
