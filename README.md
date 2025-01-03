
# Simple Banking API

The **Simple Banking API** provides basic account management and transaction operations, such as retrieving account details, crediting an account, and debiting an account.

## Table of Contents

- [API Endpoints](#api-endpoints)
  - [Get Account Details](#get-account-details)
  - [Credit Account](#credit-account)
  - [Debit Account](#debit-account)
- [Error Handling](#error-handling)
- [Technologies Used](#technologies-used)

---

## API Endpoints

### Get Account Details

**Endpoint**:  
`GET /account/v1/{accountNumber}`

**Description**:  
Retrieve account details for a specific account.

**cURL Command**:
```bash
curl --location --request GET 'http://localhost:8082/account/v1/669-7788'
```

**Response Example**:
```json
{
    "accountNumber": "123-4567",
    "owner": "John Doe",
    "balance": 1500.0,
    "transactions": [
        {
            "type": "DepositTransaction",
            "amount": 1000.0,
            "date": "2023-01-01T10:00:00",
            "approvalCode": "abc123"
        },
        {
            "type": "WithdrawalTransaction",
            "amount": 500.0,
            "date": "2023-01-02T12:00:00",
            "approvalCode": "def456"
        }
    ]
}
```

---

### Credit Account

**Endpoint**:  
`POST /v1/account/credit/{accountNumber}`

**Description**:  
Credit an account by adding a specified amount.

**cURL Command**:
```bash
curl --location --request POST 'http://localhost:8082/account/v1/credit/669-7788' \
--header 'Content-Type: application/json' \
--data-raw '{
    "amount": 1000.0
}'
```

**Response Example**:
```json
{
    "status": "SUCCESS",
    "approvalCode": "ghi789"
}
```

---

### Debit Account

**Endpoint**:  
`POST /v1/account/debit/{accountNumber}`

**Description**:  
Debit an account by subtracting a specified amount. This operation may fail if the account has insufficient balance.

**cURL Command**:
```bash
curl --location --request POST 'http://localhost:8082/account/v1/debit/669-7788' \
--header 'Content-Type: application/json' \
--data-raw '{
    "amount": 500.0
}'
```

**Response Example**:
```json
{
    "status": "SUCCESS",
    "approvalCode": "jkl012"
}
```

---

## Error Handling

### Invalid Amount

**Error**: `400 Bad Request`  
Occurs when the amount provided in a request is `<= 0`.

**Response Example**:
```json
{
    "error": "Amount must be greater than zero"
}
```

### Account Not Found

**Error**: `404 Not Found`  
Occurs when the specified account number does not exist.

**Response Example**:
```json
{
    "error": "Account not found"
}
```

### Insufficient Balance

**Error**: `400 Bad Request`  
Occurs when attempting to debit an account with insufficient balance.

**Response Example**:
```json
{
    "error": "Insufficient balance"
}
```

---

## Technologies Used

- **Spring Boot**: Backend framework for building REST APIs.
- **H2 Database**: In-memory database for testing and development.
- **Gradle**: Build tool for dependency management.
- **Java 17**: Programming language for backend development.

---

This file provides comprehensive documentation of the Simple Banking API, including example requests, responses, and error handling guidelines.
