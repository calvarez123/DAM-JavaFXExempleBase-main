package com.project;


import java.net.URL;
import java.util.ResourceBundle;



import org.json.JSONArray;
import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;



public class ControllerMobile0  implements Initializable{

    @FXML
    private VBox yPanel;
    @FXML
    private Label titul;
    @FXML
    private Button back;
    @FXML
    private AnchorPane info;
    @FXML
    private ScrollPane var;
    
    private String tit;
    String opcions[] = { "Personatges", "Jocs", "Consoles" };
  

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      load();
    } 
     @FXML
    private void go_back(ActionEvent event) {
      if (yPanel.isVisible()){
        titul.setText("Nintendo DB");
        load();
      }else{
          titul.setText(tit);
          info.getChildren().clear();
          info.setVisible(false);
          yPanel.setVisible(true);
          var.setVisible(true);
      }
    }
     
      
    
    public void load(){
        try {
            showList();
          } catch (Exception e) {
            System.out.println("ControllerDesktop: Error showing list opc.");
          }
    }

    public void showList() throws Exception {
        back.setVisible(false);
        info.setVisible(false);
        URL resource = this.getClass().getResource("/assets/listatem.fxml");
    
        // Esborrar la llista actual
        yPanel.getChildren().clear();
    
        // Carregar la llista amb les dades
        for (int i = 0; i < 3; i++) {
                String nom = opcions[i];
                FXMLLoader loader = new FXMLLoader(resource);
                Parent itemTemplate = loader.load();
                ControllerList itemController = loader.getController();
                itemController.setText(nom);
                final String type =  opcions[i];
                itemTemplate.setOnMouseClicked(event -> {
                  titul.setText(type);
                  back.setVisible(true);
                  yPanel.getChildren().clear();
                  load_show(type);
                  return;
                });
                yPanel.getChildren().add(itemTemplate);
        }
    }
     public void load_show(String type){
        try {
            
            show(type);
          } catch (Exception e) {
            System.out.println("ControllerDesktop: Error showing list.MOBILE0");
          }
        } 

     public void show(String type) throws Exception {
      tit=type;
      
      AppData appData = AppData.getInstance();

      // Obtenir les dades de l'opció seleccionada
      JSONArray dades = appData.getData(type);
      
      // Carregar la plantilla
      URL resource = this.getClass().getResource("/assets/template_list_item.fxml");

      // Esborrar la llista actual
      yPanel.getChildren().clear();

      // Carregar la llista amb les dades
      for (int i = 0; i < dades.length(); i++) {
          JSONObject consoleObject = dades.getJSONObject(i);

          if (consoleObject.has("nom")) {
              
              String nom = consoleObject.getString("nom");
              
              String imatge = "assets/images/" + consoleObject.getString("imatge");
          
              FXMLLoader loader = new FXMLLoader(resource);
              Parent itemTemplate = loader.load();
              ControllerListItem itemController = loader.getController();
              itemController.setText(nom);
              itemController.setImage(imatge);
              
              // Defineix el callback que s'executarà quan l'usuari seleccioni un element
              // (cal passar final perquè es pugui accedir des del callback)
              final int index = i;
                itemTemplate.setOnMouseClicked(event -> {
                  titul.setText(nom);
                  info.setVisible(true);
                  showInfo(type, index);
                });
              yPanel.getChildren().add(itemTemplate);
              
          }

        }

     }



  void showInfo(String type, int index) {
        yPanel.setVisible(false);
        var.setVisible(false);
        // Obtenir una referència a l'ojecte AppData que gestiona les dades
        AppData appData = AppData.getInstance();
      
        // Obtenir les dades de l'opció seleccionada
        JSONObject dades = appData.getItemData(type, index);
      
        // Carregar la plantilla
        URL resource = this.getClass().getResource("/assets/template_info_item.fxml");
      
        // Esborrar la informació actual
        info.getChildren().clear();
        // Carregar la llista amb les dades
        try {
            FXMLLoader loader = new FXMLLoader(resource);
            Parent itemTemplate = loader.load();
            ControllerInfoItem itemController = loader.getController();
            itemController.setImage("assets/images/" + dades.getString("imatge"));
            itemController.setTitle(dades.getString("nom"));
            switch (type) {
            case "Consoles": itemController.setText(dades.getString("procesador")+"\n"+dades.getString("data")+"\n"+dades.getInt("venudes")+"\n"+dades.getString("color")); break;
            case "Jocs": itemController.setText(dades.getString("descripcio")+"\n"+dades.getInt("any")+"\n"+dades.getString("tipus")); break;
            case "Personatges": itemController.setText(dades.getString("nom_del_videojoc")+"\n"+dades.getString("color")); break;
            }

            info.getChildren().add(itemTemplate);
            // Afegeix la informació a la vista
           
            // Estableix que la mida de itemTemplaate s'ajusti a la mida de info
                

                } catch (Exception e) {
                System.out.println("ControllerDesktop: Error showing info.");
                System.out.println(e);
                }
            }
}