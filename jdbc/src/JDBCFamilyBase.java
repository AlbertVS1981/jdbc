import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCFamilyBase {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        final String URL = "jdbc:mysql://localhost:3306/myschema";
        final String USERNAME = "root";
        final String PASSWORD = "root";
        final String SqlQueryPrepared = "INSERT INTO mybase VALUES(?,?,?,?)";
        final String DELETE = "DELETE FROM mybase WHERE id=?";
//    Инициализируем наш DriverManager 1-й способ (Но нужно ClassNotFoundException)
        Class.forName("com.mysql.jdbc.Driver");
//    Либо инициализируем DriverManager через блок кода :
//          DBWorker dbWorker = new DBWorker();
//          Statement statement = dbWorker.getConnection().createStatement(); В try catch обернуть

//   Создаем Conection c нашим DriverManager и инициализируем наш объект запросов: Statement через который и будем делать все query для базы
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQueryPrepared);
            PreparedStatement preparedStatement1 = connection.prepareStatement(DELETE)
        ){
//   Statment - стандартная библиотека запросов SQL
//   statement.execute(sql запрос) получает объект запроса и/или выполняет его. Различные ResultSet!
            statement.execute("INSERT INTO mybase(id,fname,sname,dateofbirth) " +
                    "VALUES (3, 'Svirskiy', 'Timofey', '2010-12-24')");
//   statement.executeUpdate(sql запрос) делает UPDATE, INSERT, DELETE не делае SELECT. Выдает int - количество изменений
           int res = statement.executeUpdate("UPDATE mybase SET sname='Timofiy' WHERE id=3");
//   statement.addBatch(sql запрос) добавить в пакет запросов запрос (если выполняется несколько запросов подряд)
//   statement.addBatch(sql запрос1); statement.addBatch(sql запрос2); statement.addBatch(sql запрос3);
//   И затем выполняем все эти запросы          statement.executeBatch();
//   Чтоб при продолжении работы програмы в следующие Batch запросы не поподали предыдущие нужно выполнить statement.clearBatch();
            boolean status = statement.isClosed(); //Состояние
//   PreparedStatement запрос своеобразный Generic который инициализируется значениями и затем выполняется
            preparedStatement.setInt(1,4);
            preparedStatement.setString(2,"Svirskiy");
            preparedStatement.setString(3,"Albert");
            preparedStatement.setDate(4, Date.valueOf("1981-04-22"));
            preparedStatement.execute(); // Выполнить запрос

            preparedStatement1.setInt(1,3);
            preparedStatement1.execute();       // Выполнить запрос DELET для id = 3

            preparedStatement1.setInt(1,4);
            preparedStatement1.executeUpdate(); // Выполнить запрос для DELET id = 4

//   statement.executeQuery(sql запрос) выдает ResultSet (Результирующий набор) для получения данных-SELECT
            ResultSet resultSet = statement.executeQuery("SELECT * FROM mybase");

//   Проходим по результирующему набору (Перед этим Создаем class - соответствующий нашей таблице)
            List<FamilyMember> mainSvirskie = new ArrayList<>();
            while (resultSet.next()){
                FamilyMember familyMember = new FamilyMember();
                familyMember.setId(resultSet.getByte(1));
                familyMember.setFname(resultSet.getString(2));
                familyMember.setSname(resultSet.getString("sname"));
                familyMember.setDateofbirth(resultSet.getDate(4));
                mainSvirskie.add(familyMember);
            }
            int i=1;
            for (FamilyMember fm: mainSvirskie) {
                System.out.println(i++ + " " +fm.toString());
            }
        }
    }
}
