import java.io.*;

public class CSV {
    /**
     * Scrapes flag data off CIA website by visiting each country in region name and grabbing
     * url for each flag. Compiles data into CSV file named 'flagURL.csv'.
     *
     * @param fileName region name
     * @throws IOException if problem with writing data into {@param fileName}
     */
    public static void inputIntoCSV(String fileName, String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(data);
        writer.flush();
    }

    /**
     * Looking at CSV file compiled of flag data, sends requests to each URL to download image.
     * Downloaded into folder 'flag_images'.
     *
     * @return data
     * @throws IOException if problem with reading CSV
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
