package pers.woodenkevin.webbrowser.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement (name = "historyList")
public class HistoryList {
    private List<History> history;

    @XmlElement (name = "history")
    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }
}
