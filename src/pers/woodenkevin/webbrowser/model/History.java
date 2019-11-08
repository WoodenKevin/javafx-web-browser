package pers.woodenkevin.webbrowser.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class History {
    private final StringProperty url;
    private final StringProperty title;
    private final ObjectProperty<Date> date;

    /**
     * 默认构造函数
     */
    public History() {
        this(null, null, null);
    }

    /**
     * 带有初始化数据的构造函数
     * @param url 网页URL
     * @param title 网页标题
     * @param date 访问日期、时间
     */
    public History(String url, String title, Date date) {
        this.url = new SimpleStringProperty(url);
        this.title = new SimpleStringProperty(title);
        this.date = new SimpleObjectProperty<>(date);
    }

    public void setUrl(String url) {
        this.url.set(url);
    }

    public String getUrl() {
        return this.url.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getTitle() {
        return this.title.get();
    }

    public void setDate(Date date) {
        this.date.set(date);
    }

    public Date getDate() {
        return date.get();
    }
}
