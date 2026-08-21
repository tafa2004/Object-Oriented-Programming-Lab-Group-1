 OOP Week 1 Lab Overview

This repository contains the work completed for the Object-Oriented Programming (OOP) Week 1 Lab.

The lab introduces the Java development environment, compilation and execution using the Java Virtual Machine (JVM), the problems that can occur with procedural code, and how Object-Oriented Programming can provide a better way of organizing related data.

The lab also introduces basic Git and GitHub workflows for saving, tracking, and sharing project work.

## Learning Objectives

The lab covers:

* Checking that the Java compiler and JVM are installed and working.
* Creating, compiling, and running a basic Java program.
* Understanding Java bytecode and the difference between compilation and execution.
* Identifying problems caused by keeping related data in separate arrays.
* Using classes and objects to group related data.
* Using constructors and methods in a Java class.
* Using Git commits to save versions of a project.
* Using GitHub to store and share project history.
* Using branches to experiment safely with code.
* Using AI tools to explain, modify, and debug code while still understanding the changes.

## Files

### `Hello.java`

A basic Java program used to verify that the Java compiler and JVM are working correctly.

### `procedural.java`

Demonstrates a procedural approach where names and ages are stored in separate arrays. Adding a new person without updating both arrays results in an `ArrayIndexOutOfBoundsException`.

### `oop.java`

Demonstrates the Object-Oriented Programming approach by creating a `Person` class that combines a person's name and age into one object.

Each `Person` object contains:

* `name`
* `age`
* `printDetails()` method

The program uses an array of `Person` objects to store and display information about multiple people.

## Compilation and Execution

Make sure Java is installed:

```bash
java -version
javac -version
```

Compile the programs:

```bash
javac Hello.java
javac procedural.java
javac oop.java
```

Run them using:

```bash
java Hello
java procedural
java oop
```

Compilation produces Java bytecode in `.class` files, which is then executed by the JVM.

## Git Workflow

The project is tracked using Git and stored on GitHub.

Typical workflow:

```bash
git add .
git commit -m "Describe the change"
git push
```

Changes from other contributors can be retrieved using:

```bash
git pull
```

## Key OOP Concept

The procedural example demonstrates how related information can become inconsistent when stored separately. The OOP solution addresses this by combining a person's data into a single `Person` object.

This makes the relationship between a person's name and age explicit and reduces the possibility of the data becoming out of sync.

## Lab Status

* [x] Java environment checked
* [x] `Hello.java` compiled and executed
* [x] Procedural example tested
* [x] Procedural error observed
* [x] OOP solution implemented
* [x] `oop.java` compiled and executed
* [x] Java bytecode generated
* [x] Git repository initialized
* [x] Project prepared for GitHub
