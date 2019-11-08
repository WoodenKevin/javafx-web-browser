package pers.woodenkevin.webbrowser.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import pers.woodenkevin.webbrowser.controller.contentbar.ContentBarController;
import pers.woodenkevin.webbrowser.controller.mediator.ControllerMediator;
import pers.woodenkevin.webbrowser.controller.titlebar.TitleBarController;
import pers.woodenkevin.webbrowser.controller.toolbar.ToolBarController;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends Controller implements Initializable {
    @FXML public VBox root;
    @FXML private TitleBarController titleBarController;
    @FXML private ToolBarController toolBarController;
    @FXML private ContentBarController contentBarController;

    public MainController() {
        super();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControllerMediator.getInstance().registerTitleBarController(titleBarController);
        ControllerMediator.getInstance().registerToolBarController(toolBarController);
        ControllerMediator.getInstance().registerContentBarController(contentBarController);
    }
}
