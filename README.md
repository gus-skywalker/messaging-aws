# Java Banking Project

This is a Java banking project developed to allow the creation of accounts, deposits, withdrawals, and balance checks in a simple and efficient manner.

## Features

- Create a bank account.
- Make deposits into an account.
- Make withdrawals from an account.
- Check the balance of an account.

## How to Use

### Endpoints

#### Create Bank Account

- **Method:** POST
- **URL:** `/api/accounts/create-account`
- **Input Parameters:**
  - `customerName`: Customer's name.
  - `customerEmail`: Customer's email.
  - `customerPhone`: Customer's phone number.
- **Response:** Returns an `Account` object representing the created account.

#### Make Deposit

- **Method:** POST
- **URL:** `/api/accounts/deposit`
- **Input Parameters:**
  - `accountNumber`: Account number where the deposit will be made.
  - `amount`: Amount to deposit.
- **Response:** No explicit response.

#### Make Withdrawal

- **Method:** POST
- **URL:** `/api/accounts/withdraw`
- **Input Parameters:**
  - `accountNumber`: Account number from where the withdrawal will be made.
  - `amount`: Amount to withdraw.
- **Response:** No explicit response.

#### Check Balance

- **Method:** GET
- **URL:** `/api/accounts/balance`
- **Input Parameters:**
  - `accountNumber`: Account number to check the balance.
- **Response:** Returns the balance of the specified account.

## Contributing

Contributions are welcome! Feel free to open issues to report bugs, suggest improvements, or create pull requests.

## License

This project is licensed under the [MIT License](LICENSE).

## Versions

- Java: 17
- SpringBoot: v3.2.3
