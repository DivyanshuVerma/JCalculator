JCalculator
===========

Description
------------

Basically it is a scientific calculator with a pluggable festure. When the application starts it searches the directory for the available class files. Those class files are loaded dynamically at runtime when they are selected through the combobox. These other class files represent specific functions that the scientific calculator can perform. At present, there are 3 files which are for matrix, complex calculations and base conversion.
Now, if I want to add a feature of equation solving, all I need to do is create a new class file which performes the function, and place it in the directory. No modification is required to be done to the base class.
Alternatively, the base class can be executed even without the other class files. Then it wont have the complex, matrix and base conversion features.

To Create A New Feature
-----------------------

The class should contain the following:

Fields:

	public int height;
		It contains the height which your implementation requires

Methods:

	public void initCmps()
		It initializes all the components that you want your feature to display
	
	public Component[] getCmps()
		It creates a component array of all the components that you have initialized in initCmps(), and returns it.

	
You do not need to initialize any frame or container, it can run without it also.
Just create the component array and return. The base class will add all of them to its JFrame and display.

To Run
-------

Open terminal and go to the source directory. Type `java Jcalculator`
Runs on both windows and linux.

Authors
-------

Written by Divyanshu Verma.
dev.verma1010@gmail.com
