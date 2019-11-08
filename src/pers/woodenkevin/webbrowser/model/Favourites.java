package pers.woodenkevin.webbrowser.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Favourites {
    private final StringProperty url;
    private final StringProperty title;

    /**
     * 默认构造函数
     */
    public Favourites() {
        this(null, null);
    }


    /**
     * 带有初始化数据的构造函数
     * @param url 网页URL
     * @param title 网页标题
     */
    public Favourites(String url, String title) {
        this.url = new SimpleStringProperty(url);
        this.title = new SimpleStringProperty(title);
    }

    public void setUrl(String url) {
        this.url.set(url);
    }

    public String getUrl() {
        return url.get();
    }

    public StringProperty urlProperty() {
        return url;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }
}
