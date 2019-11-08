package pers.woodenkevin.webbrowser.controller.mediator;

import pers.woodenkevin.webbrowser.controller.contentbar.ContentBarController;
import pers.woodenkevin.webbrowser.controller.contentbar.ListController;
import pers.woodenkevin.webbrowser.controller.contentbar.MenuController;
import pers.woodenkevin.webbrowser.controller.contentbar.WebViewController;
import pers.woodenkevin.webbrowser.controller.titlebar.TitleBarController;
import pers.woodenkevin.webbrowser.controller.toolbar.ToolBarController;

public interface MediateControllers {
    void registerTitleBarController(TitleBarController controller);
    void registerToolBarController(ToolBarController controller);
    void registerContentBarController(ContentBarController controller);
    void registerWebViewController(WebViewController controller);
    void registerListController(ListController controller);
    void registerMenuController(MenuController controller);

    void loadWebPage(String url);
    void stopLoadingWebPage();
    void reloadWebPage();
    void goBackward();
    void goForward();

    void updateTitleBar(String title);
    void updateURLBar(String url);
    void updateBackwardBtn(Boolean isUnable);
    void updateForwardBtn(Boolean isUnable);
    void updateRefreshBtn(Boolean isLoading);
    void updateAdd2FavBtn(Boolean isAdded);

    void updateIsLoadingTag(Boolean isLoading);
    void updateIsListDisplayedTag(Boolean isVisible);
    void updateFavouritesIndexTag(int index);

    void setListDisplayed(Boolean listVisible);

    String getCurrentURL();
    String getCurrentTitle();
}