package service;

import io.jsonwebtoken.Jwts;
import model.AccountModel;
import model.AccountTypeModel;
import repository.AccountRepository;
import repository.AccountTypeRepository;

import java.util.List;

public class AccountService {
    public List<AccountModel> getAllAccounts(int page, int limit) {
        AccountRepository accountRepository = new AccountRepository();
        return accountRepository.getAllAccounts(page, limit);
    }

    public AccountModel getAccountByEmail(String email) {
        AccountRepository accountRepository = new AccountRepository();
        return accountRepository.getAccountByEmail(email);
    }

    public AccountModel createAccount(String email, String pass, String fullName, String address, String phoneNumber, int accountTypeId) {
        AccountRepository accountRepository = new AccountRepository();
        return accountRepository.createAccount(email, pass, fullName, address, phoneNumber, accountTypeId);
    }

    public AccountModel updateAccount(String email, String password, String fullName, String address, String phoneNumber, int accountTypeId) {
        AccountRepository accountRepository = new AccountRepository();
        return accountRepository.updateAccount(email, password, fullName, address, phoneNumber, accountTypeId);
    }

    public boolean checkToken(String email) {
        AccountRepository accountRepository = new AccountRepository();
        return accountRepository.checkToken(email);
    }

    public String getToken(String email, String password) {
        AccountRepository accountRepository = new AccountRepository();
        boolean isCorrect = accountRepository.checkAccount(email, password);
        String result = "";
        if (isCorrect) {
            result = Jwts.builder()
                    .claim("email", email)
                    .compact();
        }
        return result;
    }
}
