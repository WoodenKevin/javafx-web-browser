package pers.woodenkevin.webbrowser.controller.contentbar;

import javafx.concurrent.Worker;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import pers.woodenkevin.webbrowser.controller.Controller;
import pers.woodenkevin.webbrowser.controller.mediator.ControllerMediator;
import pers.woodenkevin.webbrowser.model.operator.DataOperator;

import java.net.URL;
import java.util.ResourceBundle;

public class WebViewController extends Controller implements Initializable {
    public WebView webView;
    public WebEngine webEngine;
    private WebHistory webHistory;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        webEngine = webView.getEngine();
        webHistory = webEngine.getHistory();

        // 加载空白页
        webEngine.load("about:blank");
        // 加载欢迎页面
        // webEngine.load(super.getWelcomePageURL());

        webEngine.getLoadWorker().stateProperty().addListener((observableValue, state, t1) -> {
            System.out.println(t1);
            System.out.println(webEngine.getLoadWorker().getMessage());

            String currentURL = webEngine.getLocation();
            String currentTitle = webEngine.getTitle();
            ControllerMediator mediator = super.mediator;

            mediator.updateURLBar(currentURL);
            mediator.updateBackwardBtn(isBeginOfWebHistory());
            mediator.updateForwardBtn(isEndOfWebHistory());

            if (t1 == Worker.State.RUNNING) {
                mediator.updateTitleBar("正在加载中");
                mediator.updateRefreshBtn(true);
                mediator.updateIsLoadingTag(true);
            } else if (t1 == Worker.State.SUCCEEDED) {
                if (currentTitle == null) {
                    mediator.updateTitleBar(currentURL);
                } else {
                    mediator.updateTitleBar(currentTitle);
                }
                mediator.updateRefreshBtn(false);
                mediator.updateIsLoadingTag(false);

                int index = DataOperator.getInstance().getIndexInFavouritesList(currentURL);
                if (index == -1) {
                    mediator.updateAdd2FavBtn(false);
                } else {
                    mediator.updateAdd2FavBtn(true);
                }
                mediator.updateFavouritesIndexTag(index);

                DataOperator.getInstance().addHistory(
                        currentURL,
                        currentTitle,
                        webHistory.getEntries().get(webHistory.getCurrentIndex()).getLastVisitedDate()
                );
            } else if (t1 == Worker.State.CANCELLED || t1 == Worker.State.FAILED) {
                mediator.updateTitleBar(currentURL);
                mediator.updateRefreshBtn(false);
                mediator.updateIsLoadingTag(false);
            }
        });
    }

    private Boolean isBeginOfWebHistory() {
        return (webHistory.getEntries().size() == 0) || (webHistory.getCurrentIndex() == 0);
    }

    private Boolean isEndOfWebHistory() {
        return (webHistory.getEntries().size() == 0) || (webHistory.getCurrentIndex() == webHistory.getEntries().size() - 1);
    }
}