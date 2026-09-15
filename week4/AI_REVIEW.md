# WEEK 4 AI REVIEW

## OOP — Encapsulation, Access Control & Input Validation

**Project:** Week 4
**Course:** Object-Oriented Programming (OOP)
**Project Folder:** `week4`

---

## 1. Introduction

This document provides an AI-assisted review of the Week 4 OOP project.

The project focused on:

- Encapsulation
- Access control
- Constructors
- Getters and setters
- Input validation
- Exceptions
- JUnit testing
- Git version control

The project was developed incrementally, with Git commits used to record the major stages of development, errors, fixes, and successful implementations.

---

# 2. Part 1 — Public Balance Problem

The first version of the `BankAccount` class exposed the account balance as a public field.

This allowed another class to directly modify the balance:

```java
jacobsAccount.balance = -9999;
```

The program produced:

```text
Balance: -9999.0
```

### AI Review

This demonstrates a major problem with public fields.

A bank account should not allow other parts of the program to directly assign an invalid balance. A negative balance could be introduced without any validation.

### Lesson Learned

Sensitive object data should be protected from uncontrolled direct access.

### Git checkpoint

```text
a853430 Week 4 Part 1 demonstrate public balance problem
```

---

# 3. Part 2 — Private Balance

The `balance` field was changed from public to private:

```java
private double balance;
```

When the old code attempted to access the field directly, compilation failed with:

```text
balance has private access in com.oop.BankAccount
```

The code was then changed to use the public methods of the class instead of directly accessing the private field.

### AI Review

This was an important improvement because the balance could no longer be modified directly from outside the `BankAccount` class.

This demonstrates the principle of **encapsulation**.

### Lesson Learned

Private fields protect an object's internal state and force other classes to interact with the object through controlled methods.

### Git checkpoint

```text
aa235fa Week 4 Part 2 encapsulate balance with private access
```

---

# 4. Part 3 — Access Modifiers

The project then demonstrated the four main Java access levels:

```java
private
default
protected
public
```

The `Probe` class contained:

```java
private int fieldPrivate = 1;
int fieldDefault = 2;
protected int fieldProtected = 3;
public int fieldPublic = 4;
```

## Same-package testing

The program demonstrated that within the same package:

- `private` was inaccessible from another class
- default was accessible
- protected was accessible
- public was accessible

The successful output was:

```text
4
2
3
```

The private field caused the expected compilation error:

```text
fieldPrivate has private access in com.oop.Probe
```

## Different-package testing

A separate class named `Auditor` was created in:

```text
com.auditor
```

It attempted to access fields from:

```text
com.oop.Probe
```

The compiler correctly rejected:

```text
fieldDefault is not public in com.oop.Probe
fieldProtected has protected access in com.oop.Probe
```

The public field remained accessible.

### Access Modifier Summary

| Modifier  | Same Class | Same Package | Different Package |
| --------- | ---------- | ------------ | ----------------- |
| private   | Yes        | No           | No                |
| default   | Yes        | Yes          | No                |
| protected | Yes        | Yes          | No\*              |
| public    | Yes        | Yes          | Yes               |

`protected` can also be accessed from another package through inheritance.

### Errors encountered

The file was initially created with the wrong filename:

```text
probe.java
```

while the class was:

```java
public class Probe
```

Java reported:

```text
class Probe is public, should be declared in a file named Probe.java
```

The file was renamed to:

```text
Probe.java
```

Another issue occurred when the `Auditor.java` file was initially placed in the wrong location. After correcting the folder structure, Maven successfully detected the expected access-control errors.

### AI Review

The access modifier experiments successfully demonstrated how Java controls visibility between classes and packages.

### Git checkpoints

```text
2543d88 Week 4 Part 3 test private access modifier
3661dd2 Week 4 Part 3 test public default and protected access
4698025 Week 4 Part 3 test access modifiers across packages
b3c4ff1 Week 4 Part 3 complete access control demonstration
```

---

# 5. Part 4 — Getter and Setter

The `BankAccount` class was extended with:

```java
public double getBalance() {
    return this.balance;
}
```

and:

```java
public void setBalance(double balance) {
    this.balance = balance;
}
```

The getter was used to read the private balance:

```java
System.out.println("Balance: " + jacobsAccount.getBalance());
```

The program produced:

```text
Balance: 500.0
```

The setter was then tested:

```java
jacobsAccount.setBalance(1000);
```

Output:

```text
Before setter: 500.0
After setter: 1000.0
```

### AI Review

The getter provides controlled access for reading the private field.

The setter provides controlled access for changing the field.

However, at this stage the setter itself did not validate the new value. Validation was added later in Part 6.

### Lesson Learned

Getters and setters can provide controlled access to private data, but setters should also apply appropriate validation when necessary.

### Git checkpoints

```text
9180d69 Week 4 Part 4 add balance getter
7e853da Week 4 Part 4 add getter and setter
```

---

# 6. Part 5 — Constructor

A parameterized constructor was added:

```java
public BankAccount(String accountHolder, double balance) {
    this.accountHolder = accountHolder;
    this.balance = balance;
}
```

The account could then be created using:

```java
BankAccount jacobsAccount = new BankAccount("Jacob", 500);
```

The program produced:

```text
Account holder: Jacob
Balance: 500.0
```

## Error encountered

The previous code attempted to create the object using:

```java
new BankAccount();
```

After adding the parameterized constructor, Maven reported that the constructor could not be applied to the supplied arguments.

The code was corrected to use:

```java
new BankAccount("Jacob", 500);
```

### AI Review

The constructor improves object creation because required account information can be supplied when the object is created.

It also provides a suitable place to validate initial account data.

### Git checkpoint

```text
835d74e Week 4 Part 5 add BankAccount constructor
```

---

# 7. Part 6 — Input Validation and Exceptions

Validation was added to the `BankAccount` class.

## Constructor validation

Negative starting balances are rejected:

```java
if (balance < 0) {
    throw new IllegalArgumentException("Balance cannot be negative");
}
```

## Deposit validation

Deposits must be positive:

```java
if (amount <= 0) {
    throw new IllegalArgumentException("Deposit amount must be positive");
}
```

## Withdrawal validation

Withdrawals must be positive:

```java
if (amount <= 0) {
    throw new IllegalArgumentException("Withdrawal amount must be positive");
}
```

Withdrawals greater than the current balance are rejected:

```java
if (amount > this.balance) {
    throw new IllegalArgumentException("Insufficient funds");
}
```

## Setter validation

The setter also prevents a negative balance:

```java
if (balance < 0) {
    throw new IllegalArgumentException("Balance cannot be negative");
}
```

## Validation test

The program was tested using:

```text
Starting balance: 500.0
After deposit: 700.0
After withdrawal: 600.0
Error: Insufficient funds
```

The invalid withdrawal caused an `IllegalArgumentException`, which was caught and displayed by the program.

Maven reported:

```text
BUILD SUCCESS
```

### AI Review

This was a significant improvement over the earlier versions.

The class now protects its internal state by rejecting invalid input instead of allowing invalid balances or transactions.

The use of `IllegalArgumentException` is appropriate because the caller supplied an invalid argument.

### Git checkpoint

```text
9f4263d Week 4 Part 6 add input validation and exceptions
```

---

# 8. Part 7 — JUnit Tests

JUnit 6.0.3 was included as a test dependency in the Maven project.

Four tests were created in:

```text
src/test/java/com/oop/BankAccountTest.java
```

## Test 1 — Deposit

The test verifies that depositing 200 into an account with 500 produces:

```text
700
```

## Test 2 — Withdrawal

The test verifies that withdrawing 100 from an account with 500 produces:

```text
400
```

## Test 3 — Negative Deposit

The test verifies that a negative deposit throws:

```java
IllegalArgumentException
```

## Test 4 — Insufficient Funds

The test verifies that attempting to withdraw more than the available balance throws:

```java
IllegalArgumentException
```

The tests were executed with:

```text
mvn test
```

The final result was:

```text
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

### AI Review

The JUnit tests provide automated verification of the main account operations and validation rules.

This is better than relying only on manually observing program output because the tests can be run repeatedly whenever the code changes.

### Git checkpoint

```text
de25645 Week 4 Part 7 add JUnit tests
```

---

# 9. Overall Encapsulation Review

The project successfully demonstrates encapsulation.

The most important improvement was changing:

```java
public double balance;
```

to:

```java
private double balance;
```

The balance is now protected from direct external modification.

Operations such as deposits and withdrawals are controlled through methods.

This follows an important OOP principle:

> An object should control access to its own internal state.

---

# 10. Access Control Review

The project successfully demonstrated all four major Java access levels.

### Private

Used to protect internal fields.

### Default

Demonstrated package-level access.

### Protected

Demonstrated package access and the restrictions that apply across packages.

### Public

Used for methods and members that need to be accessed externally.

The practical experiments helped demonstrate the difference between these modifiers rather than only describing them theoretically.

---

# 11. Validation Review

The validation design is now much stronger than the original Part 1 implementation.

The following invalid operations are rejected:

| Invalid Operation               | Result    |
| ------------------------------- | --------- |
| Negative starting balance       | Exception |
| Zero deposit                    | Exception |
| Negative deposit                | Exception |
| Zero withdrawal                 | Exception |
| Negative withdrawal             | Exception |
| Withdrawal greater than balance | Exception |
| Negative balance through setter | Exception |

This prevents the object from entering several invalid states.

---

# 12. Exception Handling Review

The project uses:

```java
IllegalArgumentException
```

for invalid input.

This is appropriate because the problem is caused by an invalid argument supplied to a method or constructor.

The application also demonstrated catching the exception:

```java
catch (IllegalArgumentException e) {
    System.out.println("Error: " + e.getMessage());
}
```

This allows the program to report the problem without terminating unexpectedly.

---

# 13. JUnit Review

The project contains four automated tests.

All four passed:

```text
Tests run: 4
Failures: 0
Errors: 0
Skipped: 0
```

This confirms that the current implementation satisfies the tested requirements.

The tests cover both normal and invalid operations.

---

# 14. Git Version Control Review

Git was used throughout the project to record development progress.

The final commit history is:

```text
de25645 Week 4 Part 7 add JUnit tests
9f4263d Week 4 Part 6 add input validation and exceptions
835d74e Week 4 Part 5 add BankAccount constructor
7e853da Week 4 Part 4 add getter and setter
9180d69 Week 4 Part 4 add balance getter
b3c4ff1 Week 4 Part 3 complete access control demonstration
4698025 Week 4 Part 3 test access modifiers across packages
3661dd2 Week 4 Part 3 test public default and protected access
2543d88 Week 4 Part 3 test private access modifier
aa235fa Week 4 Part 2 encapsulate balance with private access
a853430 Week 4 Part 1 demonstrate public balance problem
```

This history demonstrates the evolution of the project from an unsafe public field to a more controlled and tested object-oriented implementation.

---

# 15. Errors Encountered During Development

Several errors occurred during development and were corrected.

### Error 1 — Private field access

```text
balance has private access in com.oop.BankAccount
```

Cause:

The old `Main` class attempted to directly access the private balance.

Solution:

The program was changed to use methods such as:

```java
getBalance()
deposit()
```

---

### Error 2 — Incorrect Java filename

```text
class Probe is public, should be declared in a file named Probe.java
```

Cause:

The file was named:

```text
probe.java
```

while the public class was:

```java
Probe
```

Solution:

The file was renamed to:

```text
Probe.java
```

---

### Error 3 — Accessing fields from another package

The compiler rejected default and protected fields from the `com.auditor` package.

This was expected behavior and successfully demonstrated Java's access-control rules.

---

### Error 4 — Incorrect constructor usage

The program attempted:

```java
new BankAccount();
```

after the parameterized constructor had been added.

Solution:

```java
new BankAccount("Jacob", 500);
```

---

### Error 5 — Incorrect file placement

The `Auditor.java` file was initially not located in the correct package directory.

The file was moved to:

```text
src/main/java/com/auditor/Auditor.java
```

After correcting the location, Maven detected and demonstrated the expected access-control errors.

---

# 16. Maven Review

Maven was used to compile and test the project.

The project successfully completed:

```text
mvn compile
```

and:

```text
mvn test
```

The final JUnit execution produced:

```text
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

There was also a Maven compiler warning concerning the use of `source` and `target` version 25 and the recommendation to use `--release 25`.

The warning did not prevent compilation or testing.

---

# 17. Recommendations

The implementation is functional and satisfies the main Week 4 learning objectives.

However, the following improvements could be made in a future version.

## Recommendation 1 — Make accountHolder private

Currently:

```java
public String accountHolder;
```

A stronger encapsulation design would use:

```java
private String accountHolder;
```

with a getter such as:

```java
public String getAccountHolder() {
    return this.accountHolder;
}
```

This would make the account holder information encapsulated in the same way as the balance.

## Recommendation 2 — Add more JUnit tests

Future tests could cover:

- Negative starting balance
- Zero deposit
- Zero withdrawal
- Negative withdrawal
- Negative setter value
- Exact withdrawal equal to the balance
- Multiple deposits
- Multiple withdrawals

## Recommendation 3 — Improve validation messages

The current messages are already useful, but a larger application could provide more detailed error information where appropriate.

## Recommendation 4 — Consider constructor design

If multiple account creation options are required in the future, overloaded constructors could be introduced carefully.

---

# 18. Final AI Assessment

The Week 4 project successfully demonstrates the core OOP concepts required by the lab.

### Assessment

| Area                | Assessment |
| ------------------- | ---------- |
| Encapsulation       | Excellent  |
| Private fields      | Excellent  |
| Access modifiers    | Excellent  |
| Constructors        | Good       |
| Getters/setters     | Good       |
| Input validation    | Excellent  |
| Exception handling  | Good       |
| JUnit testing       | Excellent  |
| Git version control | Excellent  |

The project evolved from an unsafe implementation where the balance could be directly changed to a more secure implementation where the balance is private, operations are validated, invalid arguments generate exceptions, and important behavior is automatically tested with JUnit.

The final automated tests passed successfully with:

```text
4 tests
0 failures
0 errors
0 skipped
BUILD SUCCESS
```

## Conclusion

The Week 4 project successfully demonstrates how encapsulation and access control improve the design of an object-oriented program.

The development process also demonstrated the importance of testing, debugging, exception handling, and version control.

The final implementation is significantly safer and more maintainable than the original public-field implementation.

**Overall result: PASS — Week 4 objectives successfully demonstrated.**
