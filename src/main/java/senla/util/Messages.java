package senla.util;

public enum Messages {
    TRANSACTION_DELETE_START("Deleting transaction."),
    TRANSACTION_DELETE_SUCCESS("Transaction deleted successfully.");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
