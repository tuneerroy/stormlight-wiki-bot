package src;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class StormlightScraper {
    public static final String MAIN_URL = "https://stormlightarchive.fandom.com/wiki/" +
            "The_Stormlight_Archive";

    /**
     * assumes that all links can be reached from home page, reasonable assumption
     *
     * @return
     * @throws IOException
     */
    public static Map<String, List<String>> scrapeFandom() throws IOException {
        Map<String, List<String>> res = new HashMap<>();

        Set<String> disc = new HashSet<>();
        LinkedList<String> queue = new LinkedList<>();
        queue.add(MAIN_URL);
        disc.add(MAIN_URL);

        int counter = 0;

        while (!queue.isEmpty()) {
            String url = queue.removeFirst();
            Scraper scraper = new Scraper(url);

            if (scraper.failed()) {
                continue;
            }

            List<String> out = scraper
                    .getATags()
                    .stream()
                    .filter(l -> !disc.contains(l))
                    .collect(Collectors.toList());
            queue.addAll(out);
            res.put(url, out);

            counter++;
            if (counter % 10 == 0)
                System.out.println(counter);
        }

        return res;
    }

    public static void main(String[] args) {
        try {
            System.out.println(scrapeFandom());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
