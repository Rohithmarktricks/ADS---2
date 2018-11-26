Information regarding Java program files that were used to conduct experiment and obtain the results.
_____________________________________________________________________________________________________
CrossoverToBinarySearch.java : Contains the main program to create binary and sequential search symbol tables.
It will take the highest limit as 100000. It means those many keys and values are randomly generated and inturn they are inserted in the symbol table.
______________________________________________________________________________________________________
TestAnalysis.java : This is another attempt to generate random keys and values. But, while trying to retrieve the values form the symbol table, query keys are 
randomly generated. The same process is repeated for 50 times(in a loop), then the number of steps that were required to retrieve each of 50 random keys are 
stored separately in sequentialSteps and binarySteps arrays.They are sorted to find out the minimum and the maximum number of steps required to find the value of the key.
______________________________________________________________________________________________________

Other sources include 
BST.java
Queue.java
SequentialSearchSymbolTable.java
StdOut.java
SymbolTable.java

Reuslts :
To view the results simply run CrossoverToBinarySeach.java file. 

For further inference, you can try running TestAnalysis.java file, but you may have to uncomment some portion of code to see the required results.
And there is a communication between CrossoverToBinary and TestAnalysis files. So make sure they are in the same folder.Infact, all the files are to be in the same folder.