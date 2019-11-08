package pers.woodenkevin.webbrowser.model.operator;

import javafx.collections.ObservableList;
import pers.woodenkevin.webbrowser.model.Favourites;
import pers.woodenkevin.webbrowser.model.History;

import java.util.Date;

public interface OperateData {
    ObservableList<History> getHistory();
    void addHistory(String url, String title, Date date);
    void removeHistory(int index);
    void clearHistory();
    void loadHistoryListFromFile();
    void saveHistoryListToFile();

    ObservableList<Favourites> getFavourites();
    void addFavourites(String url, String title);
    void removeFavourites(int index);
    void clearFavourites();
    void loadFavouritesListFromFile();
    void saveFavouritesListToFile();
    int getIndexInFavouritesList(String url);
}
