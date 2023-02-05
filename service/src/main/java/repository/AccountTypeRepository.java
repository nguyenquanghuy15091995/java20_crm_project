package repository;

import column.AccountTypeColumn;
import config.MysqlConfig;
import model.AccountTypeModel;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AccountTypeRepository {
    public int addNewAccountType(String name, String desc) {
        int isSuccess = 0;
        Connection connection = MysqlConfig.getConnection();
        String query = "insert into tb_account_type(name,description) values(?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, desc);

            isSuccess = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error add Account Type " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (Exception e) {

            }
        }
        return isSuccess;
    }

    public List<AccountTypeModel> getAllAccountTypes(){
        List<AccountTypeModel> listAccountTypes = new ArrayList<>();
        Connection connection = MysqlConfig.getConnection();
        String query = "select * from tb_account_type";

        try{
            ResultSet resultSet = connection.prepareStatement(query).executeQuery();
            while(resultSet.next()){
                AccountTypeModel accountTypeModel = new AccountTypeModel();
                accountTypeModel.setId(resultSet.getInt(AccountTypeColumn.ID.getValue()));
                accountTypeModel.setName(resultSet.getString(AccountTypeColumn.NAME.getValue()));
                accountTypeModel.setDescription(resultSet.getString(AccountTypeColumn.DESCRIPTION.getValue()));

                listAccountTypes.add(accountTypeModel);
            }
        }catch (Exception e){
            System.out.println("Get Account type list error: " + e.getMessage());
        }

        return listAccountTypes;
    }
}
