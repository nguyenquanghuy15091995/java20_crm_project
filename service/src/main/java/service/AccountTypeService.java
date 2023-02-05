package service;

import model.AccountTypeModel;
import repository.AccountTypeRepository;

import java.util.List;

public class AccountTypeService {
    public List<AccountTypeModel> getAllAccountTypes() {
        AccountTypeRepository accountTypeRepository = new AccountTypeRepository();
        return accountTypeRepository.getAllAccountTypes();
    }

    public boolean addNewAccountType(String name, String desc) {
        AccountTypeRepository accountTypeRepository = new AccountTypeRepository();
        return accountTypeRepository.addNewAccountType(name, desc) >= 1;
    }
}
