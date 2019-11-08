package pers.woodenkevin.webbrowser.controller.contentbar;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pers.woodenkevin.webbrowser.controller.Controller;
import pers.woodenkevin.webbrowser.model.Favourites;
import pers.woodenkevin.webbrowser.model.History;

import java.net.URL;
import java.util.ResourceBundle;

public class ListController extends Controller implements Initializable {
    @FXML public HBox list;
    @FXML public VBox tabBar;
    @FXML public Button closeBtn;
    @FXML public Label favouritesListTab;
    @FXML public Label historyListTab;
    @FXML public VBox listView;
    @FXML public HBox header;
    @FXML public Label tabTitle;
    @FXML public Button editBtn;
    @FXML public ImageView editBtnImg;
    @FXML public VBox listContentWrapper;
    @FXML public ListView<String> listContent;
    @FXML public HBox footer;
    @FXML public Button clearBtn;

    public int currentTab = 1;

    public ListController() {
        super();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTab();
        updateListContent();

        listContent.setEditable(false);
        // 添加选择监听事件
        listContent.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> {
            int index = (Integer) t1;

            if (index >= 0) {
                super.mediator.loadWebPage(
                        currentTab == 1 ?
                                super.operator.getFavourites().get(index).getUrl() :
                                super.operator.getHistory().get(index).getUrl()
                );
                super.mediator.setListDisplayed(false);
                Platform.runLater(() -> listContent.getSelectionModel().clearSelection());
            }
        });
    }

    @FXML
    public void closeBtnOnAction() {
        super.mediator.setListDisplayed(false);
        super.mediator.updateIsListDisplayedTag(false);
    }

    @FXML
    public void favouritesTabOnMouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            currentTab = 1;
            updateTab();
            updateListContent();
        }
    }

    @FXML
    public void historyTabOnMouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            currentTab = 2;
            updateTab();
            updateListContent();
        }
    }

    @FXML
    public void clearBtnOnAction() {
        if (currentTab == 1) {
            super.operator.clearFavourites();
        } else {
            super.operator.clearHistory();
        }
        updateListContent();
    }

    /**
     * 更新标签
     */
    public void updateTab() {
        if (currentTab == 1) { // 切换到“收藏夹”
            // 更新左侧图标样式
            favouritesListTab.setStyle("-fx-border-width: 0 0 0 3.0;");
            historyListTab.setStyle("-fx-border-width: 0;");

            // 更新右侧标题文本
            tabTitle.setText("收藏夹");
        } else if (currentTab == 2) { // 切换到“历史记录”
            // 更新左侧图标样式
            favouritesListTab.setStyle("-fx-border-width: 0;");
            historyListTab.setStyle("-fx-border-width: 0 0 0 3.0;");

            // 更新右侧标题文本
            tabTitle.setText("历史记录");
        }
    }

    /**
     * 更新列表内容
     */
    public void updateListContent() {
        if (currentTab == 1) { // 切换到“收藏夹”
            // 构建String类型的用于存放标题的ObservableList
            ObservableList<String> favouritesTitle = FXCollections.observableArrayList();
            for (Favourites favourite : super.operator.getFavourites()) {
                favouritesTitle.add(favourite.getTitle());
            }
            // 绑定数据
            listContent.setItems(favouritesTitle);
        } else { // 切换到“历史记录”
            // 构建String类型的用于存放标题的ObservableList
            ObservableList<String> historyTitle = FXCollections.observableArrayList();
            for (History history : super.operator.getHistory()) {
                historyTitle.add(history.getTitle());
            }
            // 绑定数据
            listContent.setItems(historyTitle);
        }
    }
}
