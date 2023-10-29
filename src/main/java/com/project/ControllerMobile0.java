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
    private Label titulo;
    @FXML
    private Button atras;
    @FXML
    private AnchorPane info;
    @FXML
    private ScrollPane elecion;
    @FXML
    private Button boton1;
    
    private String layaut_titulo;
    String opcions[] = { "Personatges", "Jocs", "Consoles" };
  
    @FXML
    private void ir_atras(ActionEvent event) {
      if (yPanel.isVisible()){
        titulo.setText("Nintendo DB");
        load();
      }else{
          titulo.setText(layaut_titulo);
          info.getChildren().clear();
          info.setVisible(false);
          yPanel.setVisible(true);
          elecion.setVisible(true);
      }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      loadList("Personatges");
      loadList("Jocs");
      loadList("Consoles");
      load();
    } 

    public void loadList(String opcion) {

      // Obtenir l'opció seleccionada
      String opcio = opcion;
  
      // Obtenir una referència a AppData que gestiona les dades
      AppData appData = AppData.getInstance();
  
      // Mostrar el missatge de càrrega
   
  
      // Demanar les dades
      appData.load(opcio, (result) -> {
          if (result == null) {
            System.out.println("ControllerDesktop: Error loading data.");
          } else {
            // Cal afegir el try/catch a la crida de ‘showList’
            try {

            } catch (Exception e) {
              System.out.println("ControllerDesktop: Error showing list.");
            }
          }
        });
      }
    
     
     
    
    
    public void load(){
        try {
            showList();
          } catch (Exception e) {
            System.out.println("ControllerDesktop: Error showing list opc.");
          }
    }

    

    public void showList() throws Exception {
        atras.setVisible(false);
        info.setVisible(false);
        URL resource = this.getClass().getResource("/assets/listItem2.fxml");
    
        // Esborrar la llista actual
        yPanel.getChildren().clear();
        
      
        // Carregar la llista amb les dades
        for (int i = 0; i < 3; i++) {
                String nom = opcions[i];
                FXMLLoader loader = new FXMLLoader(resource);
                Parent itemTemplate = loader.load();
                ControllerListItem2 itemController = loader.getController();
                itemController.setText(nom);
                final String  type =  opcions[i];
            
                itemTemplate.setOnMouseClicked(event -> {
                  
                  titulo.setText(type);
                  atras.setVisible(true);
                  yPanel.getChildren().clear();
                  load_show(type);
                  return;
                });
                yPanel.getChildren().add(itemTemplate);
                
        }
        // Mostrar el missatge de càrrega
      
    }
     public void load_show(String type){
        try {
            
            show(type);
          } catch (Exception e) {
            System.out.println("ControllerDesktop: Error showing list.MOBILE0");
          }
        } 

     public void show(String type) throws Exception {
      layaut_titulo=type;
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
              
              
              final int index = i;
                itemTemplate.setOnMouseClicked(event -> {
                  titulo.setText(nom);
                  info.setVisible(true);
                  showInfo(type, index);
                });
              yPanel.getChildren().add(itemTemplate);
              
          }

        }

     }



  void showInfo(String type, int index) {
        yPanel.setVisible(false);
        elecion.setVisible(false);
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
                
                }
            }
}