# Library Management System

A Java-based **Library Management System** that allows users to manage books, members, and borrowing transactions through a simple command-line interface.

## Main Menu

```text
===============================================================
:                   Library Management System                 :
===============================================================

Please select an option:

1.  Add a Book
2.  Remove a Book by ID
3.  List Books
4.  Search Books
5.  Add Member
6.  List Members
7.  Borrow Book
8.  Return Book
9.  Transaction History
10. Save Data
11. Load Data
0.  Exit
```

## Features

| Option | Function            | Description                                  |
| -----: | ------------------- | -------------------------------------------- |
|      1 | Add a Book          | Add a new book to the library                |
|      2 | Remove a Book       | Remove a book using its ID                   |
|      3 | List Books          | Display all books in the library             |
|      4 | Search Books        | Search for books using available information |
|      5 | Add Member          | Register a new library member                |
|      6 | List Members        | Display all registered members               |
|      7 | Borrow Book         | Allow a member to borrow an available book   |
|      8 | Return Book         | Return a previously borrowed book            |
|      9 | Transaction History | View borrowing and returning history         |
|     10 | Save Data           | Save the current library data                |
|     11 | Load Data           | Load previously saved library data           |
|      0 | Exit                | Exit the application                         |

## Project Overview

The system is designed to provide basic library management functionality through a command-line application. It handles:

* Book management
* Member management
* Book borrowing and returning
* Transaction tracking
* Data persistence
* Book searching
* Command-line user interaction

## Technologies

* Java
* Object-Oriented Programming
* Java Collections
* File I/O
* Exception Handling
* Streams API
* Date and Time API
* Serialization / Data Persistence

The application will display the main menu and wait for the user to select an option.

## Project Structure

```text
Library Management System
│
├── src
│   ├── ...
│   └── App.java
│
├── README.md
└── ...
```
