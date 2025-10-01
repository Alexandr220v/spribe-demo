package enums;

public enum Role {
    USER("user"),
    ADMIN("admin"),
    SUPERVISOR("supervisor");

    Role(String value) {
        this.value = value;
    }
    private final String value;
    public String getValue() {
        return value;
    }

}
