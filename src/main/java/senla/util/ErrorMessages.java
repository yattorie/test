package senla.util;

public enum ErrorMessages {
    CONNECTION_POOL_INIT_ERROR("Error initializing connection pool."),
    CONNECTION_ACQUIRE_ERROR("Failed to acquire connection from the pool."),
    CONNECTION_STATE_ERROR("Error checking connection state."),
    TRANSACTION_BEGIN_ERROR("Failed to begin transaction."),
    TRANSACTION_COMMIT_ERROR("Failed to commit transaction."),
    TRANSACTION_ROLLBACK_ERROR("Failed to rollback transaction."),
    AUTO_COMMIT_ENABLE_ERROR("Failed to enable auto-commit after rollback/commit."),
    DAO_CREATE_ERROR("Failed to create."),
    DAO_SAVE_ERROR("Failed to save."),
    DAO_FIND_ERROR("Failed to find."),
    DAO_UPDATE_ERROR("Failed to update."),
    DAO_DELETE_ERROR("Failed to delete."),
    PROPERTY_LOAD_ERROR("Failed to load properties file."),
    TRANSACTION_FAILED("Transaction failed."),
    SERVICE_CREATE_ERROR(""),
    SERVICE_FIND_ERROR(""),
    SERVICE_UPDATE_ERROR(""),
    SERVICE_DELETE_ERROR("");

    private final String description;

    ErrorMessages(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
