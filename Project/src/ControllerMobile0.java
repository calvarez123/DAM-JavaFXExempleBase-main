import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ControllerMobile0 {
    ControllerPersonatges personaje = new ControllerPersonatges();
    @FXML
    private void toViewPersonatges(ActionEvent event) {
        UtilsViews.setView("MobilePersonatges");
        
    }
    @FXML
    private void toViewJocs(ActionEvent event) {
        UtilsViews.setView("MobileJocs");
    }
    @FXML
    private void toViewConsoles(ActionEvent event) {
        UtilsViews.setView("MobileConsoles");
    }
    
    }
