package repository;

import config.MysqlConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersRepository {

    public int countUsersByEmailAndPassword(String email, String password){
        int count = 0;
        //Mở kết nối tới database
        Connection connection = MysqlConfig.getConnection();
//        String query = "select count(*) from users u \n" +
//                "where u.email = '" + email + "' and password = '" + password + "';";
//    Ký tự ? : đại diện cho tham số jdbc sẽ truyền vào khi thực thi câu query
        String query = "select count(*) as count from users u where u.email = ? and password = ?";
        try {
            //Chuẩn bị câu query và truyền tham số vào câu query
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,email);
            statement.setString(2,password);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return count;
    }

}
