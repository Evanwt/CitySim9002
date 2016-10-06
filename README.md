IS2545 - DELIVERABLE 2: Unit Testing

While I used Object Oriented design to write the code.
First of all, I created a class visitor, which includes a variable called type, this class is used for representing the visitor.

Second I wrote some global variables, such as HashMap like, which used for saving the location that the visitors prefer. And so is the Hashmap dislike. Also I wrote two arraylist, which used for saving the location and the type of the visitors.


I used a method called executeEntrance, which has no return value. It is used for consolidating all the method. Here I met a tricky part, the seed can only be typed as a single integer, others cannot be accepted.
Initially I used nextInt() to catch the input value, but it always failed when I typed in a character. Therefore I used nextLine() then used Integer.parseInt() to judge whether I typed is an integer.

Then  I wrote a couple of methods, each of them contains a certain function required by the requirement. They all have return value.

The first one is generateRan method, which is used for outputing the corresponding location in the arrayList,
except the integer 4, when the random number is 4, it will output "has left the city".
The random number is allocated with the same probabilty. 

The second one is the visit method, which is used for outputing the string that contains the information about whether the visitor like or dislike the loction, or whether he left the city.
In fact, It also need two extra method.

The third and the forth method is called 'like' and 'dislike' , both of them have the boolean return value.

For the test code,
First of all, we need to install the package mockito to exclipse, then import the class, using the method in this class to make test, such as the mock() method, which was used for mocking the object.
Each method should consider the base case, edge case, and the corner case.










