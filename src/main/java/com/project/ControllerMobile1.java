package com.project;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ControllerMobile1  implements Initializable{
    @FXML
    private VBox yPanel;    
    
    String opcions[] = { "Personatges", "Jocs", "Consoles" };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
      load();
    } 



    public void load(){
        try {
            showList();
            
          } catch (Exception e) {
            System.out.println("ControllerDesktop: Error showing list. MOBILE1");
          }
    }
    public void showList() throws Exception {

      
        // Obtenir una referència a l'ojecte AppData que gestiona les dades
        AppData appData = AppData.getInstance();
    
       
        // Carregar la plantilla
        URL resource = this.getClass().getResource("/assets/listatem.fxml");
    
        // Esborrar la llista actual
        yPanel.getChildren().clear();
    
        // Carregar la llista amb les dades
        for (int i = 0; i < 3; i++) {
                String nom = opcions[i];
                FXMLLoader loader = new FXMLLoader(resource);
                Parent itemTemplate = loader.load();
                ControllerListItem2 itemController = loader.getController();
                itemController.setText(nom);
                
              System.out.println("hola");
                
                
                
                yPanel.getChildren().add(itemTemplate);
             
        }
    }

}


    

