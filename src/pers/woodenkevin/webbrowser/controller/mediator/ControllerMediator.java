package pers.woodenkevin.webbrowser.controller.mediator;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import pers.woodenkevin.webbrowser.controller.Controller;
import pers.woodenkevin.webbrowser.controller.contentbar.ContentBarController;
import pers.woodenkevin.webbrowser.controller.contentbar.ListController;
import pers.woodenkevin.webbrowser.controller.contentbar.MenuController;
import pers.woodenkevin.webbrowser.controller.contentbar.WebViewController;
import pers.woodenkevin.webbrowser.controller.titlebar.TitleBarController;
import pers.woodenkevin.webbrowser.controller.toolbar.ToolBarController;

public class ControllerMediator extends Controller implements MediateControllers {
    private TitleBarController titleBarController;
    private ToolBarController toolBarController;
    private ContentBarController contentBarController;
    private WebViewController webViewController;
    private ListController listController;
    private MenuController menuController;

    private ControllerMediator() {
        super();
    }

    @Override
    public void registerTitleBarController(TitleBarController controller) {
        titleBarController = controller;
    }

    @Override
    public void registerToolBarController(ToolBarController controller) {
        toolBarController = controller;
    }

    @Override
    public void registerContentBarController(ContentBarController controller) {
        contentBarController = controller;
    }

    @Override
    public void registerWebViewController(WebViewController controller) {
        webViewController = controller;
    }

    @Override
    public void registerListController(ListController controller) {
        listController = controller;
    }

    @Override
    public void registerMenuController(MenuController controller) {
        menuController = controller;
    }

    @Override
    public void loadWebPage(String url) {
        try {
            webViewController.webEngine.load(url);
        } catch (NullPointerException e) {
            if (webViewController == null) {
                System.out.println("WebViewController is null!");
            }
        }
    }

    @Override
    public void stopLoadingWebPage() {
        webViewController.webEngine.getLoadWorker().cancel();
    }

    @Override
    public void reloadWebPage() {
        webViewController.webEngine.reload();
    }

    @Override
    public void goBackward() {
        webViewController.webEngine.executeScript("window.history.go(-1);");
    }

    @Override
    public void goForward() {
        webViewController.webEngine.executeScript("window.history.go(1);");
    }

    @Override
    public void updateTitleBar(String title) {
        titleBarController.title.setText(title);
    }

    @Override
    public void updateURLBar(String url) {
        toolBarController.urlBar.setText(url);
    }

    @Override
    public void updateBackwardBtn(Boolean isUnable) {
        if (isUnable) {
            toolBarController.backwardBtn.setId("backwardBtnUnable");
            if (toolBarController.backwardBtn.getTooltip() != null) {
                toolBarController.backwardBtn.getTooltip().hide();
            }
            toolBarController.backwardBtnImg.setImage(new Image(super.getIconURL("backwardBtn_unable.png")));
        } else {
            toolBarController.backwardBtn.setId("backwardBtn");
            toolBarController.backwardBtn.setTooltip(new Tooltip("后退"));
            toolBarController.backwardBtnImg.setImage(new Image(super.getIconURL("backwardBtn.png")));
        }
    }

    @Override
    public void updateForwardBtn(Boolean isUnable) {
        if (isUnable) {
            toolBarController.forwardBtn.setId("forwardBtnUnable");
            if (toolBarController.forwardBtn.getTooltip() != null) {
                toolBarController.forwardBtn.getTooltip().hide();
            }
            toolBarController.forwardBtnImg.setImage(new Image(super.getIconURL("forwardBtn_unable.png")));
        } else {
            toolBarController.forwardBtn.setId("forwardBtn");
            toolBarController.forwardBtn.setTooltip(new Tooltip("前进"));
            toolBarController.forwardBtnImg.setImage(new Image(super.getIconURL("forwardBtn.png")));
        }
    }

    @Override
    public void updateRefreshBtn(Boolean isLoading) {
        if (isLoading) {
            toolBarController.refreshBtn.getTooltip().setText("停止加载");
            toolBarController.refreshBtnImg.setImage(new Image(super.getIconURL("refreshBtn_stop_loading.png")));
        } else {
            toolBarController.refreshBtn.getTooltip().setText("刷新");
            toolBarController.refreshBtnImg.setImage(new Image(super.getIconURL("refreshBtn.png")));
        }
    }

    @Override
    public void updateAdd2FavBtn(Boolean isAdded) {
        if (isAdded) {
            toolBarController.add2FavBtn.getTooltip().setText("取消收藏");
            toolBarController.add2FavBtnImg.setImage(new Image(super.getIconURL("add2FavBtn_added.png")));
        } else {
            toolBarController.add2FavBtn.getTooltip().setText("添加到收藏夹");
            toolBarController.add2FavBtnImg.setImage(new Image(super.getIconURL("add2FavBtn.png")));
        }
    }

    @Override
    public void updateIsLoadingTag(Boolean isLoading) {
        toolBarController.isLoading = isLoading;
    }

    @Override
    public void updateIsListDisplayedTag(Boolean isDisplayed) {
        toolBarController.isListDisplayed = isDisplayed;
    }

    @Override
    public void updateFavouritesIndexTag(int index) {
        toolBarController.favouritesIndex = index;
    }

    @Override
    public void setListDisplayed(Boolean listDisplayed) {
        contentBarController.list.setVisible(listDisplayed);
        contentBarController.list.setManaged(listDisplayed);
        listController.currentTab = 1;
        listController.updateTab();
        listController.updateListContent();
    }

    @Override
    public String getCurrentURL() {
        return webViewController.webEngine.getLocation();
    }

    @Override
    public String getCurrentTitle() {
        return webViewController.webEngine.getTitle();
    }


    public static ControllerMediator getInstance() {
        return ControllerMediatorHolder.INSTANCE;
    }

    private static class ControllerMediatorHolder {
        private static final ControllerMediator INSTANCE = new ControllerMediator();
    }
}
