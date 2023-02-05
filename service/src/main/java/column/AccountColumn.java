package column;

public enum AccountColumn {
    ID("account_id"),
    EMAIL("email"),
    PASSWORD("pass"),
    FULL_NAME("full_name"),
    ADDRESS("address"),
    PHONE_NUMBER("phone_number"),
    ACCOUNT_TYPE_ID("account_type_id");

    private String value;

    AccountColumn(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
