# E2_Radulesu_Richard_java
-lab1

Sumdigit will remove the last digit of n add it to the sum until it runs out of digits.
If the sum is greater then 9 it will return a function call with the sum as the parameter

-homework1

1) Func. validate: will check if a string is a number using regex
2) Funct. validateS: will check if a string is made of only one character and its defined.
3) Funct. LetterCheck: will check if at least one character from string a is in string b.
4) Funct. relations: will parse all the words and check if they have at least one letter in common (using function LetterCheck).
If there is then it will mark in the boolean matrix with true, othrwise false
After that it will pass this new matrix and for every word it will create a new string containing the words that have a letter in common.
5) Funct. printR: will print an array of strings (size n).
6) Funct. cycle3: it will check if 3 words (W1 W2 W3) are neighbours in this way W1 <-> W2 <-> W3 <-> W1.
7) MAIN :  it will validate the parameters from args. If the input is valid it will generate n words of size p with the given alphabet.
After that it will call the function relations with the necesary parameters.
----------------------------------------------------------------------------------------------------------------------------------------------------------

-lab2

1) Rooms: varibles: string name, int size start end 
generated the setter getter and made constructor for the class 
override the toString method to return a string containing all the variables
2) Events: same as before
3) type: made and enum
4) Main: in the main method I created objects of type Rooms and Events just like in the laboratory example and printed the result of the toString methode for 2 of the objects.

-homework2
1) All classes from lab2 but no more enum
2) Two classes that extends the Room class: Laboratory, LectureHall
3) Main: I instanciate 2 arraysList of type Event and Room and call the assignRoom function. The assignRoom function uses a greedy algorithm (events that end earlier takes priority and as a second criteria the size of the event takes priority) to assigns a room for as many events as possible.
----------------------------------------------------------------------------------------------------------------------------------------------------------

-lab3
1) Class Network:
* Methode printIdentifiable prints a list of all the Nodes that implement the Identifiable interface.
* Methode printNetworkCosts prints the map of every node which contains the name of the neighbour node and the cost of the edge between them.
* Methode floydWasrshall applies the known algorithm for the network.
* Methode dikstra applies the known algoritm and return the cost from source to target.
* Methode generateNetwork generates a network randomly(kinda).
* Methode initialize generates the network from the example.
2) Class MyRandom contains several methodes to generate string/integers in different formats/sizes.
3) Class Node: abstract class has strings with the name, macAddres, location, a map for the cost between nodes, setter and getters for them.
4) Storage Interface: has a default methode converse with 2 param one of type Capacity (an enum) and one of type int. It returns the integer in the given unit measure.
5) Identifiable Interface: has one methode getIp which is implemented in the classes Computer/Storage.
6) Class Computer, Switch, Router that inherite the Node class and implement the Storage or/and Identifiable interfaces.
7) NetworkTest: asserts that the function dikstra from the network class returns the expected value for a known input.

 
