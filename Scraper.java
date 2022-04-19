import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Scraper {
    private String html;
    private HttpURLConnection httpConnection;

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
            URL currentURL = new URL(url);
            URLConnection connection = currentURL.openConnection();
            this.httpConnection = (HttpURLConnection) connection;
            this.html = this.getContentsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Read the html of the webpage with a scanner, store in a variable
     * @return returns the content of the html page
     */
    private String getContentsString() {
        StringBuilder contents = new StringBuilder();
        try {
            Scanner in = new Scanner(httpConnection.getInputStream());
            while (in.hasNextLine()) {
                String line = in.nextLine();
                contents.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contents.toString();
    }
}
