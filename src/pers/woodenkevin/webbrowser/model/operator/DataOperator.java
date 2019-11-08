package pers.woodenkevin.webbrowser.model.operator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pers.woodenkevin.webbrowser.model.Favourites;
import pers.woodenkevin.webbrowser.model.FavouritesList;
import pers.woodenkevin.webbrowser.model.History;
import pers.woodenkevin.webbrowser.model.HistoryList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Date;

public class DataOperator implements OperateData {
    private File historyListData;
    private File favouritesListData;

    private ObservableList<History> history;
    private ObservableList<Favourites> favourites;

    private DataOperator() {
        // 开发使用
        // historyListData = new File("src/pers/woodenkevin/webbrowser/data/HistoryListData.xml");
        // favouritesListData = new File("src/pers/woodenkevin/webbrowser/data/FavouritesListData.xml");
        // 打包使用
        historyListData = new File("data/HistoryListData.xml");
        favouritesListData = new File("data/FavouritesListData.xml");

        history = FXCollections.observableArrayList();
        favourites = FXCollections.observableArrayList();

        this.loadHistoryListFromFile();
        this.loadFavouritesListFromFile();
    }

    @Override
    public ObservableList<History> getHistory() {
        return history;
    }

    @Override
    public void addHistory(String url, String title, Date date) {
        history.add(new History(url, title, date));
        saveHistoryListToFile();
    }

    @Override
    public void removeHistory(int index) {
        history.remove(index);
        saveHistoryListToFile();
    }

    @Override
    public void clearHistory() {
        history.clear();
        saveHistoryListToFile();
    }

    /**
     * 加载历史记录
     * 从存储历史记录的XML文件中加载历史记录
     */
    @Override
    public void loadHistoryListFromFile() {
        try {
            JAXBContext content = JAXBContext.newInstance(HistoryList.class);
            Unmarshaller um = content.createUnmarshaller();

            // 加载XML文件的内容并反编列
            HistoryList historyList = (HistoryList) um.unmarshal(historyListData);

            history.clear();
            history.addAll(historyList.getHistory());
        } catch (Exception error) {
            if (error instanceof NullPointerException) {
                history.clear();
            } else {
                System.out.println(error.toString());
            }
        }
    }

    /**
     * 存储历史记录
     * 将历史记录存储到XML文件中
     */
    @Override
    public void saveHistoryListToFile() {
        try {
            JAXBContext context = JAXBContext.newInstance(HistoryList.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // 将内容添加到XML文件并编列
            HistoryList historyList = new HistoryList();
            historyList.setHistory(history);
            m.marshal(historyList, historyListData);
        } catch (Exception error) {
            System.out.println("Catch an error while saving historyList to file:");
            System.out.println(error.toString());
        }
    }

    @Override
    public ObservableList<Favourites> getFavourites() {
        return favourites;
    }

    @Override
    public void addFavourites(String url, String title) {
        favourites.add(new Favourites(url, title));
        saveFavouritesListToFile();
    }

    @Override
    public void removeFavourites(int index) {
        favourites.remove(index);
        saveFavouritesListToFile();
    }

    @Override
    public void clearFavourites() {
        favourites.clear();
        saveFavouritesListToFile();
    }

    /**
     * 加载收藏夹
     * 从存储收藏夹的XML文件中加载收藏夹
     */
    @Override
    public void loadFavouritesListFromFile() {
        try {
            JAXBContext content = JAXBContext.newInstance(FavouritesList.class);
            Unmarshaller um = content.createUnmarshaller();

            // 加载XML文件的内容并反编列
            FavouritesList favouritesList = (FavouritesList) um.unmarshal(favouritesListData);

            favourites.clear();
            favourites.addAll(favouritesList.getFavourites());
        } catch (Exception error) {
            if (error instanceof NullPointerException) {
                favourites.clear();
            } else {
                System.out.println(error.toString());
            }
        }
    }

    /**
     * 存储收藏夹
     * 将收藏夹存储到XML文件中
     */
    @Override
    public void saveFavouritesListToFile() {
        try {
            JAXBContext context = JAXBContext.newInstance(FavouritesList.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // 将内容添加到XML文件并编列
            FavouritesList favouritesList = new FavouritesList();
            favouritesList.setFavourites(favourites);
            m.marshal(favouritesList, favouritesListData);
        } catch (Exception error) {
            System.out.println("Catch an error while saving favouritesList to file:");
            System.out.println(error.toString());
        }
    }

    /**
     * 获取网页在收藏夹中的索引值
     * @param url 网页URL
     * @return 该网页在收藏夹中的索引值；若返回-1，则该网页不在收藏夹中
     */
    @Override
    public int getIndexInFavouritesList(String url) {
        int result = -1;

        for (int i = 0; i < favourites.size(); i++) {
            if (favourites.get(i).getUrl().equals(url)) {
                result = i;
            }
        }

        return result;
    }

    public static DataOperator getInstance() {
        return DataOperator.DataOperationHolder.INSTANCE;
    }

    private static class DataOperationHolder {
        private static final DataOperator INSTANCE = new DataOperator();
    }
}
