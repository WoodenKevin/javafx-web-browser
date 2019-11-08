package pers.woodenkevin.webbrowser.controller.contentbar;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import pers.woodenkevin.webbrowser.controller.Controller;

import java.net.URL;
import java.util.ResourceBundle;

public class ContentBarController extends Controller implements Initializable {
    @FXML public Parent webView;
    @FXML private WebViewController webViewController;
    @FXML public Parent list;
    @FXML private ListController listController;
    // @FXML public Parent menu;
    // @FXML private MenuController menuController;

    public ContentBarController() {
        super();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list.setVisible(false);
        list.setManaged(false);
        super.mediator.registerWebViewController(webViewController);
        super.mediator.registerListController(listController);
        // super.mediator.registerListController(menuController);
    }
}
