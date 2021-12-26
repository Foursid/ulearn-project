import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private static Connection connection;
    private static Statement statement;

    public static void connectDB() throws ClassNotFoundException, SQLException {
        Class.forName(Internal.yaml.get("dbDriver").toString());
        connection = DriverManager.getConnection(Internal.yaml.get("dbURL").toString());
        statement = connection.createStatement();
        System.out.println("Database successfully connected");
    }

    public static void solveTasks() throws SQLException {
        Map<String, Double> dataset = healthDataByCountry();
        EventQueue.invokeLater(() -> {
            Histogram bc = new Histogram(dataset);
            bc.setVisible(true);
        });

        avgHealth();
        getCountryWithMostAvgData();
    }

    public static void disconnectDB() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Database successfully disconnected");
    }

    public static void initializeDB() {
        createTableCountry();
        System.out.println("Created table 'Country' with information by country");
        createTablePopulation();
        System.out.println("Created table 'Population' with information by population in country");
        System.out.println("Database successfully initialized");
    }

    public static void addDataToDB(ArrayList<CountryInfo> countries) {
        countries.forEach(c -> {
            fillCountryTable(c);
            fillPopulationTable(c);
        });
        System.out.println("Data successfuly added into tables 'Country' and 'Population'");
    }

    public static Map<String, Double> healthDataByCountry() throws SQLException {
        String sql = """
                    SELECT Country.country, Population.health
                    FROM Country
                        INNER JOIN Population ON Country.country = Population.country;
                """;

        ResultSet output = statement.executeQuery(sql);
        Map<String, Double> healthByCountry = new HashMap<>();
        while (output.next()) {
            healthByCountry.put(
                    output.getString("country"),
                    Double.parseDouble(output.getString("health")));
        }
        return healthByCountry;
    }

    public static void avgHealth() throws SQLException {
        String query = """ 
                    SELECT region, AVG(health) AS health
                    FROM Country
                        INNER JOIN Population ON Country.country = Population.country
                        WHERE region IN ('Western Europe', 'Sub-Saharan Africa')
                    GROUP BY region;
                """;

        ResultSet output = statement.executeQuery(query);
        double healthEU = 0d, healthAfrica = 0d;

        while (output.next()) {
            double health = Double.parseDouble(output.getString("health"));
            if (output.getString("region").equals("Western Europe"))
                healthEU = health;
            else healthAfrica = health;
        }

        System.out.printf("\nAnswer for 2 task:\n%s %f\n%s %f%n",
                "Average health score by 'Western Europe' region:    ", healthEU,
                "Average health score by 'Sub-Saharan Africa' region:", healthAfrica);
    }

    public static void getCountryWithMostAvgData() {
        String sql = """
                    SELECT country, AVG(avgCountryValue)
                    FROM (SELECT country,
                                 (AVG(happinessRank) +
                                  AVG(happinessScore) +
                                  AVG(whiskerHigh) +
                                  AVG(whiskerLow) +
                                  AVG(economy) +
                                  AVG(family) +
                                  AVG(health) +
                                  AVG(freedom) +
                                  AVG(generosity) +
                                  AVG(trust) +
                                  AVG(dystopia)) / 11.0 AS avgCountryValue
                          FROM (SELECT *
                                FROM Country C
                                         JOIN Population P ON C.country = P.country
                                WHERE region IN ('Western Europe', 'Sub-Saharan Africa'))
                          GROUP BY country);
                """;
        String country = null;
        try {
            country = statement.executeQuery(sql).getString("country");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("\nAnswer for 3 task\n" + country);
    }

    private static void createTableCountry() {
        String sql = """
                CREATE TABLE IF NOT EXISTS Country
                (
                    country        TEXT PRIMARY KEY,
                    region         TEXT,
                    happinessRank  INTEGER,
                    happinessScore FLOAT,
                    whiskerHigh    FLOAT,
                    whiskerLow     FLOAT,
                    economy        FLOAT,
                    dystopia       FLOAT
                );
                """;
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTablePopulation() {
        String sql = """
                CREATE TABLE IF NOT EXISTS Population
                (
                    country    TEXT,
                    family     FLOAT,
                    health     FLOAT,
                    freedom    FLOAT,
                    generosity FLOAT,
                    trust      FLOAT
                );
                """;
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void fillCountryTable(CountryInfo c) {
        String sql = String.format(
                "INSERT INTO Country " +
                "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                c.getCountryName(),
                c.getRegion(),
                c.getRank(),
                c.getScore(),
                c.getWhiskerHigh(),
                c.getWhiskerLow(),
                c.getEconomy(),
                c.getDystopia()
        );

        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void fillPopulationTable(CountryInfo c) {
        String sql = String.format(
                "INSERT INTO Population " +
                "VALUES ('%s', '%s', '%s', '%s', '%s', '%s');",
                c.getCountryName(),
                c.getFamily(),
                c.getHealth(),
                c.getFreedom(),
                c.getGenerosity(),
                c.getTrust()
        );

        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
