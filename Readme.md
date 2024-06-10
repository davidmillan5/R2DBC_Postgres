# Individual Final Challenge - Graded

Based on the reactive CRUD task where the following endpoints were specified:

### Users:

- GET /api/users
- GET /api/users/{id}
- POST /api/users
- PUT /api/users/{id}
- DELETE /api/users/{id}

### Bank Accounts:

- GET /api/bankAccounts
- GET /api/bankAccounts/{id}
- POST /api/bankAccounts
- PUT /api/bankAccounts/{id}
- DELETE /api/bankAccounts/{id}

### Transactions:

- GET /api/transactions
- GET /api/transactions/{id}
- POST /api/transactions
- PUT /api/transactions/{id}
- DELETE /api/transactions/{id}

## Tasks to be completed:

1. Error Handling and Validation
    - Task:
        - Add error handling and validation to existing endpoints. Utilize global exception handlers.
    - Endpoints:
        - Validate input data for all POST and PUT endpoints.
        - Create a global exception handler to handle specific errors (such as UserNotFoundException, InvalidDataException, etc.).

2. Entity Relationships
    - Task:
        - Establish relationships between users, bank accounts, and transactions. Ensure transactions are linked to a specific bank account and each bank account is associated with a user.
    - Endpoints:
        - GET /api/users/{id}/bankAccounts: Get all bank accounts of a specific user.
        - GET /api/bankAccounts/{id}/transactions: Get all transactions of a specific bank account.

3. Filtering, Pagination, and Sorting
    - Task:
        - Implement filtering, pagination, and sorting for the lists of users, bank accounts, and transactions.
    - Endpoints:
        - GET /api/users?page=0&size=10&sort=name,asc
        - GET /api/bankAccounts?userId=1&page=0&size=10&sort=balance,desc
        - GET /api/transactions?accountId=1&page=0&size=10&sort=date,desc

4. Batch Operations
    - Task:
        - Add the ability to perform batch operations, such as creating multiple users, bank accounts, or transactions in a single request.
    - Endpoints:
        - POST /api/users/batch: Create multiple users.
        - POST /api/bankAccounts/batch: Create multiple bank accounts.
        - POST /api/transactions/batch: Create multiple transactions.

5. Data Export and Import
    - Task:
        - Allow data export and import in CSV format.
    - Endpoints:
        - GET /api/users/export: Export all users.
        - POST /api/users/import: Import users from a file.
        - GET /api/bankAccounts/export: Export all bank accounts.
        - POST /api/bankAccounts/import: Import bank accounts from a file.
        - GET /api/transactions/export: Export all transactions.
        - POST /api/transactions/import: Import transactions from a file.

6. Notifications
    - Task:
        - Implement a notification system to alert users about important transactions or changes in their account.
    - Endpoints:
        - GET /api/notifications: Get all notifications of a user.
        - POST /api/notifications: Create a new notification.
        - DELETE /api/notifications/{id}: Delete a notification.

7. Testing
    - Task:
        - Write unit tests in the services.
    - Endpoints:
        - Ensure there are tests for each endpoint using JUnit and Mockito.

 