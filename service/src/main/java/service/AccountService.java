package service;

import io.jsonwebtoken.Jwts;
import model.AccountModel;
import model.AccountTypeModel;
import repository.AccountRepository;
import repository.AccountTypeRepository;

import java.util.List;

public class AccountService {
    public List<AccountModel> getAllAccounts() {
        AccountRepository accountRepository = new AccountRepository();
        return accountRepository.getAllAccounts();
    }

    public AccountModel getAccountByEmail(String email) {
        AccountRepository accountRepository = new AccountRepository();
        return accountRepository.getAccountByEmail(email);
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
