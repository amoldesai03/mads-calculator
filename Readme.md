Assignment - Android Developer


Task: The MAD(S) Calculator
1. Create a native (do not use Flutter or other cross-platform frameworks)
   calculator mobile application. The application should allow users to add,
   subtract, multiply and divide numbers following the MADS* rule (please check
   below).
   a. The application should only allow numerals 0-9 and mathematical
   operators ‘+, -, *, /’.
   b. Users can enter any combination of mathematical
   expressions, such as: i. 56 + 92 - 23
   ii. 20 * 89 / 52 + 52
   c. Users should be able to view the result when the user clicks on the ‘Enter’
   key in the keypad.

2. Store the result of the last operation, and allow the user to use its next
   operation. (similar to the ANS button in modern calculators).
3. Store the last 10 operations (user input and its result) in the app memory and
   display it on the screen when the user clicks on the ‘history’ button. No need to
   set up a DB or backend for storing this information. On application restart, this
   history will get cleared as well.
4. Use any standard Android Unit testing library to write unit test cases for the
   above scenario and demonstrate it.



5. One would not want such a misleading calculator to fall into wrong hands,
   hence create a user login and authentication page, which communicates with
   any backend of your choice. To reduce complexity, you do not need to create
   signup or user registration page, instead, use a few hard-coded credentials
   (stored in the backend) to log in into the application as a user.
6. If the user login is functioning, store the last 10 operations and their results in a
   backend database (preferably in the cloud) for each user, and the app pulls
   the data for the specific user on startup.
   *MADS rule:
   Contrary to the typical BODMAS rule followed in solving mathematical operations
   everywhere, we are introducing a new rule (solely for this test). In case of MADS rule,
   we assume the mathematical operator ‘(‘ or ‘)’ is not used, and instead we only use
   operators ‘+, -, *, /’. Where the precedence of the operators are in the order of MADS
- M (*) > A(+) > D(/) > S(-). Hence you should apply the multiplication operator first,
  and then the addition and so on. If there are more than one instance of an operator
  left, most one takes precedence. Few examples:
  ● 50 + 20 / 10 = 7
  ○ you first add 50 and 20, which is 70 and then divide by 10 which is 7
  ○ Because in MADS, A takes precedence over D
  ● 50 / 20 + 5 = 2
  ○ You first add 20 and 5, which is 25, and then divide 50 by 25
  which is 2 ○ Because in MADS, A takes precedence over D

● 25 - 2 * 10 = 5

○ You first multiply 2 by 10 which is 20, and then subtract 20 from 25
which is 5 ○ Because in MADS, M takes precedence over S
● 10 / 2 - 20 = -15
○ You first divide 10 by 2 which is 5 and then subtract 20 from 5 which
is -15 ○ Because in MADS, D takes precedence over S
● 10 - 2 - 3 = 5
○ Since there are 2 ‘-’ operators, you choose the leftmost operation first,
hence you subtract 2 from 10 which is 8, and then subtract 3 from 8,
which is 5
● 10 / 2 / 5 = 1
○ Divide 10 by 2, which is 5, and then divide the result by 5, which is 1.
Because you start with the leftmost operation similar to the above

© FlytBase, Inc.

example
● 10 / 2 / 4 + 1 = 1
○ You first add 4 and 1, which is 5. Because in MADS, A takes precedence
over D. ○ Then divide 10 by 2, which is 5, and then divide it by 5 (from the
previous 4 + 1 result). Because you start with the leftmost operation similar
to the above example.