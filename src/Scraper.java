package src;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Scraper {
    private Document doc;
    private boolean failed;

    /**
     * Opens the connection, loads html
     * @param url the url of the website to be scraped
     */
    public Scraper(String url) {
        this.initConnection(url);
    }

    /**
     * Open a connection the specified url
     * @param url the url of the website to be scraped
     */
    public void initConnection(String url) {
        try {
            doc = Jsoup.connect(url).get();
            failed = false;
        } catch (IOException e) {
            failed = true;
        }
    }

    public Set<String> getATags() {
        Set<String> res = new HashSet<>();
        for (String href : doc.select("a").eachAttr("href")) {
            if (href.startsWith("/wiki")) {
                res.add("https://stormlightarchive.fandom.com" + href);
            } else if (href.startsWith("https://stormlightarchive")) {
                res.add(href);
            }
        }
        return res;
    }

    public boolean failed() {
        return failed;
    }
}
