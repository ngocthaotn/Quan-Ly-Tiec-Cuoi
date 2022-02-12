package quanlytieccuoi;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 *
 * @author MyPC
 */
public class QuanLyTiecCuoi extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        URL u = getClass().getResource("FXML_home.fxml");
        Parent root = FXMLLoader.load(u);
        Scene scene = new Scene(root);

        stage.getIcons().add(new Image("quanlytieccuoi/Images/iconapp2.png"));
        stage.setTitle("KINGDOM");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
