import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
public class ControllerPersonatges{
    @FXML
    
    private void backView1(ActionEvent event) {
        UtilsViews.setView("Mobile0");
    }
    @FXML
    
    private ListView <VBox> personajesListView;
    
    public void initialize() {
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get("/assets/data/personatges.json")), StandardCharsets.UTF_8);

            // Parsear el JSON
            JSONArray jsonArray = new JSONArray(jsonContent);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String nombre = jsonObject.getString("nom");
                String imagen = jsonObject.getString("imatge");

                VBox personajeBox = new VBox();
                Text nombreText = new Text(nombre);
                ImageView imageView = new ImageView(new Image(imagen));
                personajeBox.getChildren().addAll(nombreText, imageView);
                personajesListView.getItems().add(personajeBox);
                
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        personajesListView.refresh();
        System.out.println("Datos cargados con éxito.");
    }
 
}
