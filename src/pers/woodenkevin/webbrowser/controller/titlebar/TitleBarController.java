package pers.woodenkevin.webbrowser.controller.titlebar;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import pers.woodenkevin.webbrowser.controller.Controller;

import java.net.URL;
import java.util.ResourceBundle;

public class TitleBarController extends Controller implements Initializable {
    @FXML public HBox titleBar;
    @FXML public Label title;
    @FXML public Button minBtn;
    @FXML public Button maxBtn;
    @FXML public ImageView maxBtnImg;
    @FXML public Button closeBtn;
    @FXML public ImageView closeBtnImg;

    private double oldMousePosX;
    private double oldMousePosY;
    private double olsStagePosX;
    private double oldStagePosY;

    public TitleBarController() {
        super();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 绑定标题宽度
        title.prefWidthProperty().bind(
                titleBar.widthProperty().subtract(
                        minBtn.getPrefWidth() + maxBtn.getPrefWidth() + closeBtn.getPrefWidth()
                ));
    }

    /**
     * 监听标题栏(title)onMousePressed事件，并记录鼠标开始拖拽的起点
     * @param mouseEvent 鼠标事件
     */
    @FXML
    public void windowDragStart(MouseEvent mouseEvent) {
        Stage stage = (Stage) title.getScene().getWindow();

        oldMousePosX = mouseEvent.getScreenX();
        oldMousePosY = mouseEvent.getScreenY();
        olsStagePosX = stage.getX();
        oldStagePosY = stage.getY();
    }

    /**
     * 监听标题栏(title)onMouseDragged事件，并实现窗口拖拽
     * @param mouseEvent 鼠标事件
     */
    @FXML
    public void windowDragging(MouseEvent mouseEvent) {
        Stage stage = (Stage) title.getScene().getWindow();

        stage.setX(olsStagePosX + mouseEvent.getScreenX() - oldMousePosX);
        stage.setY(oldStagePosY + mouseEvent.getScreenY() - oldMousePosY);
    }

    /**
     * 最小化窗口
     */
    @FXML
    public void minimizeApp() {
        ((Stage) minBtn.getScene().getWindow()).setIconified(true);
    }

    /**
     * 最大化窗口/还原窗口
     */
    @FXML
    public void maximizeApp() {
        Stage stage = (Stage) maxBtn.getScene().getWindow();

        if (stage.isMaximized()) {
            stage.setMaximized(false);
            maxBtn.setTooltip(new Tooltip("还原"));
            maxBtnImg.setImage(new Image(super.getIconURL("maxBtn.png")));
        } else {
            stage.setMaximized(true);
            maxBtn.setTooltip(new Tooltip("最大化"));
            maxBtnImg.setImage(new Image(super.getIconURL("maxBtn_already.png")));
        }
    }

    /**
     * 关闭程序
     */
    @FXML
    public void closeApp() {
        ((Stage) closeBtn.getScene().getWindow()).close();
    }

    @FXML
    public void closeBtnOnMouseEntered() {
        closeBtnImg.setImage(new Image(super.getIconURL("closeBtn_hover.png")));
    }

    @FXML
    public void closeBtnOnMouseExited() {
        closeBtnImg.setImage(new Image(super.getIconURL("closeBtn.png")));
    }
}
