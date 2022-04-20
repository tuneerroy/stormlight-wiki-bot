package src;

import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class StormlightScraper {
    public static final String MAIN_URL = "https://stormlightarchive.fandom.com/wiki/";

    /**
     * assumes that all links can be reached from home page, reasonable assumption
     *
     * @return
     * @throws IOException
     */
    public static void scrapeFandom(String csvFileName) {
        Map<String, List<String>> res = new HashMap<>();
        Map<String, List<String>> recent = new HashMap<>();

        Set<String> disc = new HashSet<>();
        LinkedList<String> queue = new LinkedList<>();
        queue.add("Stormlight_Archive_Wiki");
        disc.add("Stormlight_Archive_Wiki");

        int counter = 0;

        while (!queue.isEmpty()) {
            String url = queue.removeFirst();
            Scraper scraper = new Scraper(MAIN_URL + url);

            if (scraper.failed()) {
                continue;
            }

            Set<String> tags = scraper.getATags();
            for (String tag : tags) {
                if (tag.contains("?")) {
                    tag = tag.substring(0, tag.indexOf("?"));
                }

                if (!disc.contains(tag)) {
                    disc.add(tag);
                    queue.add(tag);
                }
            }

            res.put(url, new ArrayList<>(tags));
            recent.put(url, new ArrayList<>(tags));

            counter++;
            if (counter % 50 == 0) {
                System.out.printf("Counter: %d\n", counter);
                System.out.printf("Queue Size: %d\n", queue.size());
            }

            if (recent.size() >= 1000) {
                try {
                    CSV.inputIntoCSV(csvFileName, recent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                recent.clear();
            }
        }

        try {
            CSV.inputIntoCSV("urls.csv", recent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            scrapeFandom("urls.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
