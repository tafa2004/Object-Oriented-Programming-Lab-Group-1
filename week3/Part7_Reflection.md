# OOP Week 3 — Part 7 Reflection

## Question 1

**Why did `acc1` change when you changed `acc2` in Part 2?**

**Answer:**
`acc1` and `acc2` were referring to the same `BankAccount` object. Therefore, when `acc2.balance` was changed, the balance of the same object seen through `acc1` also changed.

---

## Question 2

**Why did `balance` remain 1000 in Part 4 even though `reduceBalance()` changed `amount`?**

**Answer:**
`balance` remained 1000 because `int` is a primitive type. The value was copied when it was passed to `reduceBalance()`, so changing the method's `amount` variable did not change the original `balance` variable.
