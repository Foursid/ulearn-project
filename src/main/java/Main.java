import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Internal.getConfig();

        Database.connectDB();
        Database.initializeDB();
        //Database.addDataToDB(Internal.parseCSV());
        Database.solveTasks();
        Database.disconnectDB();
    }
}
