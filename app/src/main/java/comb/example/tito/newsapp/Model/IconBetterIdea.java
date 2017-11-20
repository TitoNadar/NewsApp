package comb.example.tito.newsapp.Model;

// import android.graphics.drawable.Icon;

import java.util.List;

/**
 * Created by Tito on 18/10/2017.
 */

public class IconBetterIdea {
    private String url;
    private List<Icons> icons;

    public IconBetterIdea(String url, List<Icons> icons) {
        this.url = url;
        this.icons = icons;
    }

    public IconBetterIdea() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Icons> getIcons() {
        return icons;
    }

    public void setIconList(List<Icons> iconList) {
        this.icons = icons;
    }
}
