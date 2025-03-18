Tic Tac Toe (Java Swing + MySQL)

A simple 2-player Tic Tac Toe game built with Java Swing for the user interface and MySQL for storing game results.

Features

Classic 3x3 Tic Tac Toe board (Player 1: "O", Player 2: "X")
Automatic win/draw detection
Visual highlight on winning combinations
Restart prompt after each game
Saves match results to MySQL (gamedb table)
Displays past winners from the database
Technologies

Java AWT & Swing (GUI)
MySQL Database
JDBC for database connection
Requirements

Java JDK 8 or later
MySQL Server (running locally)
MySQL JDBC Driver (add to your project classpath)
Setup Instructions

Clone this repository.
Create a MySQL database named st and a table:
CREATE DATABASE st;
USE st;
CREATE TABLE gamedb (id INT AUTO_INCREMENT PRIMARY KEY, winner VARCHAR(50));
Ensure MySQL is running on localhost:3306 with user root and no password (adjust code if different).
Compile and run:
javac TicTacToe.java
java TicTacToe
To-Do

Add single-player mode (AI)
Enhance UI/UX design
Improve database error handling
