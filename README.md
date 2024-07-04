# Count Command Program

This is a command-line program written in Java that provides various counting operations on files. It can count the number of bytes, lines, words, and characters in a file.

## Features

- Count the number of bytes in a file (`-c` option)
- Count the number of lines in a file (`-l` option)
- Count the number of words in a file (`-w` option)
- Count the number of characters in a file (`-m` option)

## Prerequisites

- Java 21 or higher
- Maven

## How to Run

1. Clone the repository to your local machine.
2. Navigate to the project directory.
3. Build the project using Maven:

    ```bash
    mvn clean package
    ```

4. Run the program with one of the counting options and the file as arguments. For example, to count the number of lines in a file:

    ```bash
    java -jar target/ccwc-jar-with-dependencies.jar -l src/test/resources/test.txt
    ```

Replace `src/test/resources/test.txt` with the path to your file. Replace `-l` with the option for the operation you want to perform (`-c`, `-l`, `-w`, or `-m`).

## Link to the Coding Challenge

[Coding Challenge](https://codingchallenges.fyi/challenges/challenge-wc/)