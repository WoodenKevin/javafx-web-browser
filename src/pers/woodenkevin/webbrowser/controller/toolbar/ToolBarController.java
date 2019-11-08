package pers.woodenkevin.webbrowser.controller.toolbar;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import pers.woodenkevin.webbrowser.controller.Controller;

import java.net.URL;
import java.util.ResourceBundle;

public class ToolBarController extends Controller implements Initializable {
    @FXML public HBox toolBar;
    @FXML public Button backwardBtn;
    @FXML public ImageView backwardBtnImg;
    @FXML public Button forwardBtn;
    @FXML public ImageView forwardBtnImg;
    @FXML public Button refreshBtn;
    @FXML public ImageView refreshBtnImg;
    @FXML public Button homeBtn;
    @FXML public TextField urlBar;
    @FXML public Button add2FavBtn;
    @FXML public ImageView add2FavBtnImg;
    @FXML public Button saveBtn;
    @FXML public Button listBtn;
    @FXML public Button moreBtn;

    public Boolean isLoading = false;
    public Boolean isListDisplayed = false;
    public int favouritesIndex = -1;

    public ToolBarController() {
        super();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void toolBarOnMouseClicked() {
        if (isListDisplayed) {
            isListDisplayed = false;
            super.mediator.setListDisplayed(false);
        }
    }

    @FXML
    public void backwardBtnOnAction() {
        super.mediator.goBackward();
    }

    @FXML
    public void forwardBtnOnAction() {
        super.mediator.goForward();
    }

    @FXML
    public void refreshBtnOnAction() {
        if (isLoading) {
            super.mediator.stopLoadingWebPage();
        } else {
            super.mediator.reloadWebPage();
        }
    }

    @FXML
    public void homeBtnOnAction() {
        super.mediator.loadWebPage("about:blank");
    }

    @FXML
    public void urlBarOnMouseClicked() {
        urlBar.setStyle("-fx-text-fill: #000000;");
    }

    @FXML
    public void urlBarOnMouseExited() {
        urlBar.setStyle("-fx-text-fill: #757575;");
    }

    @FXML
    public void urlBarOnKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            String url = urlBar.getText();

            // 若输入的网址不以“http://”、“https://”或“file:///"”开头，则自动加上“http://”
            if (!url.contains("http://") && !url.contains("https://") && !url.contains("file:///")) {
                url = "http://" + url;
            }

            super.mediator.loadWebPage(url);
        }
    }

    @FXML
    public void add2FavBtnOnAction() {
        if (favouritesIndex == -1) { // 若当前网页未收藏
            // 只有当前网页非空白页才能收藏
            if (!super.mediator.getCurrentURL().equals("about:blank")) {
                // 若获得的网页标题为空，则以网页地址作为标题保存
                if (super.mediator.getCurrentTitle() == null) {
                    super.operator.addFavourites(super.mediator.getCurrentURL(), super.mediator.getCurrentURL());
                } else {
                    super.operator.addFavourites(super.mediator.getCurrentURL(), super.mediator.getCurrentTitle());
                }
                favouritesIndex = super.operator.getIndexInFavouritesList(super.mediator.getCurrentURL());
                super.mediator.updateAdd2FavBtn(true);
            }
        } else { // 若当前网页已收藏
            super.operator.removeFavourites(favouritesIndex);
            favouritesIndex = -1;
            super.mediator.updateAdd2FavBtn(false);
        }

        // 若列表已打开，更新列表视图
        if (isListDisplayed) {
            super.mediator.setListDisplayed(true);
        }
    }

    @FXML
    public void listBtnOnAction() {
        isListDisplayed = !isListDisplayed;
        super.mediator.setListDisplayed(isListDisplayed);
    }
}
