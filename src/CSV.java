package src;

import java.io.*;
import java.util.List;
import java.util.Map;

public class CSV {
    /**
     * Scrapes flag data off CIA website by visiting each country in region name and grabbing
     * url for each flag. Compiles data into src.CSV file named 'flagURL.csv'.
     *
     * @param fileName filename
     * @throws IOException if problem with writing data into {@param fileName}
     */
    public static void inputIntoCSV(String fileName, Map<String, List<String>> data)
            throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : data.entrySet()) {
            sb.append(entry.getKey()).append(",");
            for (String val : entry.getValue()) {
                sb.append(val).append(",");
            }
            sb.append("\n");
        }
        writer.write(sb.toString());
        writer.flush();
    }

    /**
     * Looking at src.CSV file compiled of flag data, sends requests to each URL to download image.
     * Downloaded into folder 'flag_images'.
     *
     * @return data
     * @throws IOException if problem with reading src.CSV
     */
    public static String getCSVData(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuilder res = new StringBuilder();
        while (reader.ready()) {
            res.append(reader.readLine());
        }
        return res.toString();
    }
}
