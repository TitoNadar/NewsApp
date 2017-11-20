package comb.example.tito.newsapp.Model;

import java.util.List;

import javax.xml.transform.Source;

/**
 * Created by Tito on 18/10/2017.
 */

public class Website {
    private String status;
    private List<Sources> sources;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Sources> getSources() {
        return sources;
    }

    public void setSources(List<Sources> sources) {
        this.sources= sources;
    }

    public Website(String status, List<Sources> sources) {

        this.status = status;
        this.sources = sources;
    }
}
