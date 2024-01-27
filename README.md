# Reporting API

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)

## Introduction

The Reporting API gives you access to most of the report data in PSP. With the Reporting API you can :
* 		Build custom dashboards to display PSP API data.
* 		Save time by automating complex reporting tasks. 

## Features

The API ensures secure and encrypted communication over the HTTPS protocol to guarantee 
the integrity and confidentiality of transmitted data. This is achieved through the use of 
the TLS protocol, replacing the deprecated SSLv2-3, 
and leveraging authentication certificates issued by an approved third-party authority.

### Feature 1: User Authentication
  - Description: The API provides user authentication functionality to allow users to log in using their email and password.

       ####     User Login:
        * Given a registered user with a valid email and password
        * When the user sends a request to "/api/v3/merchant/user/login"
        * Then the system authenticates the user and provides access with a valid session token.
### Feature 2: Transaction Reporting 
  - Description: The API supports transaction reporting, allowing users to retrieve lists of transactions based on specific criteria.

    #### 	Request Transaction List:
         Given an authenticated user
         When the user sends a request to "/api/v3/transactions/report"
         Then the system provides a list of transactions based on predefined criteria.
    #### 	Request Full Transaction List:
         Given an authenticated user
         When the user sends a request to "/api/v3/transaction/list"
         Then the system provides a comprehensive list of transactions.
    ####		Request Transaction Information:
         Given an authenticated user
         When the user sends a request to "/api/v3/transaction"
         Then the system provides detailed information about a specific transaction.
### Feature 3: Client Information Retrieval
  - Description: The API supports the retrieval of client information.

         Given an authenticated user
         When the user sends a request to "/api/v3/client"
         Then the system provides information about the client associated with the user.


Reporting API endpoints are provided in the table below.

| Interface                   | Description                                 |
|-----------------------------|---------------------------------------------|
| /api/v3/merchant/user/login | Login with email and password.              |
| /api/v3/transactions/report | Request for list of transaction.            |
| /api/v3/transaction/list    | Request for list of transaction.            |
| /api/v3/transaction         | Request for all information of transaction. |
| /api/v3/client              | Request for information of client.          |


## Getting Started

### Prerequisites

Before you begin, ensure you have met the following requirements:

- Requirements
- - Java 8
- - Maven
- build: `mvn clean install` or `mvn clean install -U`
