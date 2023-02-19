package repository;

import column.AccountColumn;
import column.AccountTypeColumn;
import config.MysqlConfig;
import model.AccountModel;
import model.AccountTypeModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    private String getAllAccountQuery(int page, int limit) {
        return "select" +
                " tb_account.account_id as account_id, " +
                " tb_account.email as email, " +
                " tb_account.pass as pass, " +
                " tb_account.full_name as full_name, " +
                " tb_account.address as address, " +
                " tb_account.phone_number as phone_number, " +
                " tb_account_type.account_type_id as account_type_id, " +
                " tb_account_type.account_type_name as account_type_name, " +
                " tb_account_type.account_type_description as account_type_description " +
                " from tb_account " +
                " inner join tb_account_type " +
                " on tb_account.account_type_id = tb_account_type.account_type_id " +
                " where not tb_account.is_deleted is true " +
                " limit  " + limit + " offset " + (limit * (page - 1));
    }

    private String getAccountByEmailQuery(String email) {
        return "select" +
                " tb_account.account_id as account_id, " +
                " tb_account.email as email, " +
                " tb_account.full_name as full_name, " +
                " tb_account.pass as pass, " +
                " tb_account.address as address, " +
                " tb_account.phone_number as phone_number, " +
                " tb_account_type.account_type_id as account_type_id, " +
                " tb_account_type.account_type_name as account_type_name, " +
                " tb_account_type.account_type_description as account_type_description " +
                " from tb_account " +
                " inner join tb_account_type " +
                " on tb_account.account_type_id = tb_account_type.account_type_id" +
                " where email=\"" + email + "\"";
    }

    public List<AccountModel> getAllAccounts(int page, int limit) {
        List<AccountModel> listAccounts = new ArrayList<>();
        Connection connection = MysqlConfig.getConnection();
        String query = this.getAllAccountQuery(page, limit);

        try {
            ResultSet resultSet = connection.prepareStatement(query).executeQuery();
            while (resultSet.next()) {
                AccountModel accountModel = new AccountModel();
                AccountTypeModel accountTypeModel = new AccountTypeModel();
                accountModel.setId(resultSet.getInt(AccountColumn.ID.getValue()));
                accountModel.setEmail(resultSet.getString(AccountColumn.EMAIL.getValue()));
                accountModel.setFullName(resultSet.getString(AccountColumn.FULL_NAME.getValue()));
                accountModel.setAddress(resultSet.getString(AccountColumn.ADDRESS.getValue()));
                accountModel.setPhoneNumber(resultSet.getString(AccountColumn.PHONE_NUMBER.getValue()));
                accountTypeModel.setId(resultSet.getInt(AccountTypeColumn.ID.getValue()));
                accountTypeModel.setName(resultSet.getString(AccountTypeColumn.NAME.getValue()));
                accountTypeModel.setDescription(resultSet.getString(AccountTypeColumn.DESCRIPTION.getValue()));
                accountModel.setAccountType(accountTypeModel);
                listAccounts.add(accountModel);
            }
        } catch (Exception e) {
            System.out.println("Get Account type list error: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (Exception e2) {

            }
        }
        return listAccounts;
    }

    public AccountModel getAccountByEmail(String email) {
        AccountModel accountModel = new AccountModel();
        Connection connection = MysqlConfig.getConnection();
        String query = this.getAccountByEmailQuery(email);

        try {
            ResultSet resultSet = connection.prepareStatement(query).executeQuery();
            while (resultSet.next()) {
                accountModel.setId(resultSet.getInt(AccountColumn.ID.getValue()));
                accountModel.setEmail(resultSet.getString(AccountColumn.EMAIL.getValue()));
                accountModel.setFullName(resultSet.getString(AccountColumn.FULL_NAME.getValue()));
                accountModel.setAddress(resultSet.getString(AccountColumn.ADDRESS.getValue()));
                accountModel.setPhoneNumber(resultSet.getString(AccountColumn.PHONE_NUMBER.getValue()));
                AccountTypeModel accountTypeModel = new AccountTypeModel();
                accountTypeModel.setId(resultSet.getInt(AccountTypeColumn.ID.getValue()));
                accountTypeModel.setName(resultSet.getString(AccountTypeColumn.NAME.getValue()));
                accountTypeModel.setDescription(resultSet.getString(AccountTypeColumn.DESCRIPTION.getValue()));
                accountModel.setAccountType(accountTypeModel);
            }
        } catch (Exception e) {
            System.out.println("Get Account type list error: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (Exception e2) {

            }
        }
        return accountModel;
    }

    public AccountModel createAccount(String email, String password, String fullName, String address, String phoneNumber, int accountTypeId) {
        Connection connection = MysqlConfig.getConnection();
        String query = "INSERT INTO tb_account (email, pass, full_name, address, phone_number, account_type_id)" +
                " VALUES (" +
                email + ", " +
                password + ", " +
                fullName + ", " +
                address + ", " +
                phoneNumber + ", " +
                accountTypeId + " ) ";
        AccountModel accountModel = new AccountModel();
        try {
            connection.prepareStatement(query).executeUpdate();
            ResultSet resultSet = connection.prepareStatement(this.getAccountByEmailQuery(email)).executeQuery();
            while (resultSet.next()) {
                accountModel.setId(resultSet.getInt(AccountColumn.ID.getValue()));
                accountModel.setEmail(resultSet.getString(AccountColumn.EMAIL.getValue()));
                accountModel.setFullName(resultSet.getString(AccountColumn.FULL_NAME.getValue()));
                accountModel.setAddress(resultSet.getString(AccountColumn.ADDRESS.getValue()));
                accountModel.setPhoneNumber(resultSet.getString(AccountColumn.PHONE_NUMBER.getValue()));
                AccountTypeModel accountTypeModel = new AccountTypeModel();
                accountTypeModel.setId(resultSet.getInt(AccountTypeColumn.ID.getValue()));
                accountTypeModel.setName(resultSet.getString(AccountTypeColumn.NAME.getValue()));
                accountTypeModel.setDescription(resultSet.getString(AccountTypeColumn.DESCRIPTION.getValue()));
                accountModel.setAccountType(accountTypeModel);
            }
        } catch (Exception e) {
            System.out.println("Get Account type list error: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (Exception e2) {

            }
        }
        return accountModel;
    }

    public AccountModel updateAccount(String email, String password, String fullName, String address, String phoneNumber, int accountTypeId) {
        Connection connection = MysqlConfig.getConnection();
        AccountModel accountModel = new AccountModel();
        AccountTypeModel accountTypeModel = new AccountTypeModel();
        try {
            ResultSet resultSet = connection.prepareStatement(this.getAccountByEmailQuery(email)).executeQuery();
            while (resultSet.next()) {
                accountModel.setId(resultSet.getInt(AccountColumn.ID.getValue()));
                accountModel.setEmail(resultSet.getString(AccountColumn.EMAIL.getValue()));
                accountModel.setPassword(resultSet.getString(AccountColumn.PASSWORD.getValue()));
                accountModel.setFullName(resultSet.getString(AccountColumn.FULL_NAME.getValue()));
                accountModel.setAddress(resultSet.getString(AccountColumn.ADDRESS.getValue()));
                accountModel.setPhoneNumber(resultSet.getString(AccountColumn.PHONE_NUMBER.getValue()));
                accountTypeModel.setId(resultSet.getInt(AccountTypeColumn.ID.getValue()));

                if (password != null && !password.equals("")) {
                    accountModel.setPassword(password);
                }
                if (fullName != null && !fullName.equals("")) {
                    accountModel.setFullName(fullName);
                }
                if (address != null && !address.equals("")) {
                    accountModel.setAddress(address);
                }
                if (phoneNumber != null && !phoneNumber.equals("")) {
                    accountModel.setPhoneNumber(phoneNumber);
                }
                if (accountTypeId != -1) {
                    accountTypeModel.setId(accountTypeId);
                }
                accountModel.setAccountType(accountTypeModel);
                String query = "UPDATE tb_account" +
                        " SET " +
                        " full_name=\"" + accountModel.getFullName() + "\"," +
                        " pass=\"" + accountModel.getPassword() + "\"," +
                        " address=\"" + accountModel.getAddress() + "\"," +
                        " phone_number=\"" + accountModel.getPhoneNumber() + "\"," +
                        " account_type_id=\"" + accountModel.getAccountType().getId() + "\"" +
                        " where email=\"" + accountModel.getEmail() + "\"";
                connection.prepareStatement(query).executeUpdate();
                accountModel.setPassword("");
            }
            resultSet = connection.prepareStatement(this.getAccountByEmailQuery(email)).executeQuery();
            while (resultSet.next()) {
                accountTypeModel.setId(resultSet.getInt(AccountTypeColumn.ID.getValue()));
                accountTypeModel.setName(resultSet.getString(AccountTypeColumn.NAME.getValue()));
                accountTypeModel.setDescription(resultSet.getString(AccountTypeColumn.DESCRIPTION.getValue()));
            }
        } catch (Exception e) {
            System.out.println("Get Account type list error: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (Exception e2) {

            }
        }

        return accountModel;
    }

    public boolean checkAccount(String email, String password) {
        Connection connection = MysqlConfig.getConnection();
        String query = "select" +
                " tb_account.email as email, " +
                " tb_account.pass as pass " +
                " from tb_account where tb_account.email=\"" + email + "\" and tb_account.pass=\"" + password + "\"";
        boolean result = false;
        try {
            ResultSet resultSet = connection.prepareStatement(query).executeQuery();
            while (resultSet.next()) {
                result = true;
            }
        } catch (Exception e) {
            System.out.println("Get Account type list error: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (Exception e2) {

            }
        }
        return result;
    }

    public boolean checkToken(String email) {
        Connection connection = MysqlConfig.getConnection();
        String query = "select" +
                " tb_account.email as email, " +
                " tb_account.pass as pass " +
                " from tb_account where email=\"" + email + "\"";
        boolean result = false;
        try {
            ResultSet resultSet = connection.prepareStatement(query).executeQuery();
            while (resultSet.next()) {
                result = true;
            }
        } catch (Exception e) {
            System.out.println("Get Account type list error: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (Exception e2) {

            }
        }
        return result;
    }
}
