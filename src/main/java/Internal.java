import org.yaml.snakeyaml.Yaml;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

public class Internal {
    public static Map<String, Object> yaml;

    public static void getConfig() {
        InputStream inputStream = Internal.class.getClassLoader().getResourceAsStream("config");
        yaml = new Yaml().load(inputStream);
    }

    public static ArrayList<CountryInfo> parseCSV() {
        ArrayList<CountryInfo> countries = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(yaml.get("csvPath").toString()))) {
            reader.readLine();
            while (reader.ready()) {
                String[] data = reader.readLine().split(",");
                countries.add(new CountryInfo(data));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return countries;
    }
}
