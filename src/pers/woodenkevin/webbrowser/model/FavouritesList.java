package pers.woodenkevin.webbrowser.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement (name = "favouritesList")
public class FavouritesList {
    private List<Favourites> favourites;

    @XmlElement (name = "favourites")
    public List<Favourites> getFavourites() {
        return  favourites;
    }

    public void setFavourites(List<Favourites> favourites) {
        this.favourites = favourites;
    }
}
