# JDBC Project - One-to-Many and Many-to-Many Relations

This JDBC project focuses on implementing one-to-many and many-to-many relations between tables. It provides functionality for handling the following relationships:

- One-to-Many Relations:
  - Product and Brand: Each product belongs to a specific brand.
  - Product and Category: Each product is associated with a specific category.

- Many-to-Many Relations:
  - Shareholder and Brand: Users can assign shares of brands to shareholders.

## Features

- User Registration and Login:
  - Users can register by providing their information, including a unique username and a strong password.
  - User information is validated to ensure uniqueness and adherence to the correct password format defined by regular expressions (regex).

- Product, Brand, and Category Management:
  - Users can add or delete new products, brands, or categories.
  - Each product belongs to a category and a specific brand.
  - Categories and brands can contain several products.

- Shareholder and Brand Management:
  - Users can assign shares of brands to shareholders.
  - Shareholders can be registered by providing their identity information, such as phone number and national code.
  - Shareholder information is validated to ensure uniqueness and adherence to the correct format defined by regular expressions.

## Prerequisites

To run this project, the following prerequisites should be installed in the system:

- Java Development Kit (JDK)
- Java IDE 
- SQL

