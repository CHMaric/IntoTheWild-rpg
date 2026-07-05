
package it.unicam.cs.mpgc.rpg127083;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application{

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try{
            AppConfiguration appConfiguration = new AppConfiguration();
            appConfiguration.start(primaryStage);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}