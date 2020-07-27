import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBWorker {

    private final String URL = "jdbc:mysql://localhost:3306/myschema";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";

    private Connection connection = null;

    public DBWorker() {
        try{
     //  Эта часть кода нужна если Driver не инициализирован в XML файле например pom.xml Maven
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver); // !!!
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return connection;
    }


}
