package column;

public enum AccountTypeColumn {
    ID("account_type_id"),
    NAME("account_type_name"),
    DESCRIPTION("account_type_description");

    private String value;

    AccountTypeColumn(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
