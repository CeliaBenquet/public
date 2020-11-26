# Defensive Programming Quiz

### Question 1

One class method receives garbage input. What is the worst that the method can do?

- [y] Compute on the garbage and return garbage
- [n] Compute on the garbage and throw an error, crashing the entire program
- [n] Immediately throw an exception and stop the execution of the program
- [y] Compute on the garbage and modify program state in some unspecified way
- [n] Sanitize the garbage and pass the sanitized input to another method
- [n] Ignore the input and not execute
- [n] Attempt to compute on the garbage and throw a confusing exception

### Question 2

For which of the following exceptional conditions is a RuntimeException more suitable than a checked exception? (Check all that apply)

- [y] A division by zero occurs inside some matrix processing code
- [n] A TCP connection is interrupted while fetching a URL
- [y] A negative number is passed to a natural logarithm function
- [n] A null object is passed to a sorting function

### Question 3

Which of the following are true? Choose all that apply.

- [n] It is a good practice to use exceptions for control flow transfer if we want the execution to reach the upper levels of the program stack trace quickly
- [n] It is a good practice to recover and continue program execution after a failed assertion
- [y] Defensive programming makes explicit what assumptions the code makes about program inputs
- [n] Exceptions are essentially assertions with a message

### Question 4

Which of the following is good defensive programming for a public method?

- [ ] an assertion that checks if all the method's preconditions are satisfied
- [ ] a series of checks that ensure all of the method's preconditions are satisfied and throw an AssertionError if not
- [ ] a series of checks that ensure all of the method's preconditions are satisfied and throw an exception if not

### Question 5

Security software often choose either _whitelisting_ or _blacklisting_ as the technique to restrict access to operations which require additional user privileges. Read about these techniques and select all statements which are true below when these techniques are applied to defensive programming:

- [ ] Blacklisting can only protect against known bad inputs.
- [ ] Anti-virus software is based on whitelisting.
- [ ] Whitelisting is easy to scale to large input patterns.
- [ ] Blacklisting is more secure than whitelisting.
