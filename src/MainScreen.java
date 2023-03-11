import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;



public class MainScreen extends Application {
    // the pane in main is border main
    BorderPane bpMain = new BorderPane();
    Label titleMain = new Label("Inventory Management System: Comp 2311 Project, Phase 2");
    Button addBT = new Button("Add");
    Button modifyBT = new Button("Modify");
    Button deleteBT = new Button("Delete");
    Button stockReportBT = new Button("Stock Report");



    // for add button
    AddButton addOpj = new AddButton(); //  make object from add class
    Scene sceneAdd = new Scene(addOpj.getS() , 500,400);
    Stage stageAdd = new Stage();

    ModifyButton modifyOpj = new ModifyButton();  //  make object from Modify class
    Scene sceneModify = new Scene(modifyOpj.getS() , 550,400);
    Stage stageModify = new Stage();

    DeleteButton deleteOpj = new DeleteButton();//  make object from Delete class
    Scene sceneDelete = new Scene(deleteOpj.getS() , 550,350);
    Stage stageDelete = new Stage();

    StockReportButton stockReportOpj = new StockReportButton();//  make object from Stock Report class
    Scene sceneStockReport = new Scene(stockReportOpj.getS() , 600,300);
    Stage stageStockReport = new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStageMain)  {
        addBT.setStyle("-fx-font-weight: bold;");modifyBT.setStyle("-fx-font-weight: bold;");
        deleteBT.setStyle("-fx-font-weight: bold;");stockReportBT.setStyle("-fx-font-weight: bold;");

        // in the Top
        Font font = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 20);
        titleMain.setFont(font);
        titleMain.setTextFill(Color.DARKSLATEGREY);
        HBox hbTop = new HBox(10);
        hbTop.getChildren().add(titleMain);
        hbTop.setAlignment(Pos.CENTER);
        bpMain.setTop(hbTop);

        // in the center
        Image m = new Image("inventoryManagement.jpg");
        ImageView mView = new ImageView(m);
        mView.setFitWidth(400);
        mView.setFitHeight(350);
        mView.setLayoutX(60);
        mView.setLayoutY(60);
        mView.setRotate(40);
        bpMain.setCenter(mView);

         Inventory.readData();

        //in the bottom
        //handle the nods
        MainButtonsEvent mainButtonsEvent = new MainButtonsEvent();
        //the add button
        addBT.setOnAction(mainButtonsEvent);
        stageAdd.setScene(sceneAdd);
        stageAdd.setTitle("Add new Item");
        // second  Modify  button
        modifyBT.setOnAction(mainButtonsEvent);
        stageModify.setScene(sceneModify);
        stageModify.setTitle("update Item");
        // third Delete button
        deleteBT.setOnAction(mainButtonsEvent);
        stageDelete.setScene(sceneDelete);
        stageDelete.setTitle("Delete  Item");
        // 4th  Stock Report  class
        stockReportBT.setOnAction(mainButtonsEvent);
        stageStockReport.setScene(sceneStockReport);
        stageStockReport.setTitle("Stock Report");


            //HBox for all button
        HBox hbBottom = new HBox(20);
        hbBottom.getChildren().addAll(addBT,modifyBT,deleteBT,stockReportBT);
        hbBottom.setAlignment(Pos.CENTER);
            //set the HBox in the bottom
        bpMain.setBottom(hbBottom);
        bpMain.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, new CornerRadii(0), Insets.EMPTY)));
        // the main scene
        Scene sceneMain = new Scene(bpMain,800,600,Color.BEIGE);
        //sceneMain.setFill();
        primaryStageMain.setTitle("Inventory Management System");
        primaryStageMain.setScene(sceneMain);
        primaryStageMain.show();
    }
    public  class MainButtonsEvent implements EventHandler<ActionEvent> { // the handler button
        @Override
        public void handle(ActionEvent event) {
            CancelButtonsEvent c = new CancelButtonsEvent();

            if (event.getSource() ==  addBT){
                addOpj.cancelBT.setOnAction(c);
                stageAdd.show();
            }else if (event.getSource() ==  modifyBT){
                modifyOpj.cancelBT.setOnAction(c);
                stageModify.show();
            }else if (event.getSource() ==  deleteBT){
                deleteOpj.cancelBT.setOnAction(c);
                stageDelete.show();
            }else if (event.getSource() ==  stockReportBT){
                stageStockReport.show();
            }
        }
    }
    public  class CancelButtonsEvent implements EventHandler<ActionEvent> { // the handler button
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() ==  addOpj.cancelBT){
                stageAdd.close();
            }else if (event.getSource() ==  modifyOpj.cancelBT){
                stageModify.close();
            }if (event.getSource() ==  deleteOpj.cancelBT){
                stageDelete.close();
            }
        }

        }

}
