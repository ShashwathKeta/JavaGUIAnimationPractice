//© A+ Computer Science  -  www.apluscompsci.com
//Name - 
//Date -
//Class -
//Lab  -

import java.util.ArrayList;
import java.util.Scanner;

// You should make sure that you know the methods that
// belong to the ArrayList class.

// Common Methods: 
// (index means the postion or index of the item in the list)
// (object means an object needs to be sent or passed

// get(index)           returns the object at this position 
// set(index, object)   replaces the current object with object at index position index (an object must exist)
// add(object)          adds object to the end of the list
// add(index, object)   inserts object at index position index (elements slide to the right one position)
// remove(index)        removes element at index position and all other elements slide left one position
// toString()           returns a String with all of the elements of the list
// clear()              clears out all of the elements (the list will now be empty)
// size()               returns the number of elements in our list

// YOU WILL ALWAYS NEED A REFERENCE VARIABLE.method()
// Example:
// ArrayList <Integer> list = new ArrayList<Integer>();
// list.add(5);   // YOU CANNOT SAY SIMPLY  add(5);

// Looping Reminders:
// ArrayList<Integer> list = ...
//
// for (int i=0; i<list.size(); i++)
// {
//      int x = list.get(i);
//      do whatever here
// }

// OR

// for (int x : list)  // for each loop gets next item from list each time
// {
//      do whatever here with x (you don't get x, they already got it for you)
//      YOU MAY NOT CHANGE THE VALUE OF THE LIST ELEMENTS
//      The for each loop is simply an easy way to look at all of the elements one at a time (traverse the list)
// }



public class ExpressionSolver
{
	String expressionAsString;
	ArrayList <String> expression;
	

	// s will contain an expression  Example: "3 + 5"
	// we will take each element and put it into an ArrayList
	public ExpressionSolver(String s)
	{
		expressionAsString = s; // we save the expression 
		
		// create a new ArrayList to hold String objects
		expression = new ArrayList <String>();
		
		// create a Scanner object and pass it s
		Scanner scan = new Scanner(s);
		
		
		// add each element to the expression list
		while (scan.hasNext())  // while the Scanner has a next element we keep going
		{
			String element = scan.next();   // we get the next element
			
			expression.add(element);  // we add the element to our expression ArrayList
			
		}
		
		
		
	}



	public void setExpression(String s)
	{
		expressionAsString = s; // we save the expression

		expression.clear(); // clear out the elements

		// create a Scanner object and pass it s
		Scanner scan = new Scanner(s);
		
		
		// add each element to the expression list
		while (scan.hasNext())  // while the Scanner has a next element we keep going
		{
			String element = scan.next();  // we get the next element
			
			expression.add(element);  // we add the element to our expression ArrayList
			
		}
	}


	public void handleMultiplicationAndDivision()
	{
		// YOU MUST USE AN INDEX BASED LOOP, not the for each loop since we are modifying the elements
		
		
		// we will loop through the list and do any *,/, or % operators
		
		for (int i=0; i<expression.size()-1;) // DO NOT INCREMENT i here
		{
			
			// get the first number as a String and convert it to an int
			String numAsString = expression.get(i);
			int num1 = Integer.parseInt(numAsString);
			
			// get the operator
			String operator = expression.get(i+1);
			
			// get the second number as a String and convert it to an int
			numAsString = expression.get(i+2);
			int num2 = Integer.parseInt(numAsString);
			
			
			// start of  *, /, or % operator *****************************  
			if (operator.equals("*") || operator.equals("/") || operator.equals("%"))
			{
				// do the math and get the answer
				
				int answer = 0;
				
				if (operator.equals("*"))
				{
					answer = num1 * num2;
				}
				
				else if (operator.equals("/")) {
					answer = num1 / num2;
				}
				else if (operator.equals("%")) {
					answer = num1 % num2;
				}

				// now remove the i and i+1 elements from the expression
				expression.remove(i);
				expression.remove(i);  // yes this should be i since the i+1 element moved down 1 
				
				
				
				// now change the i+2 element which moved down into the i position
				expression.set(i, ""+answer); // yes this should be i
				
				// do not change i
				
			}
			else
			{
				//                  i
				// move i past the num operator1  (example: num operator1 num operator2
				i = i + 2;
			}			
			
		} // end of for loop that handles *, /, and % operators 
		
	}

	public void handleAdditionAndSubtraction()
	{
		// YOU MUST USE AN INDEX BASED LOOP, not the for each loop since we are modifying the elements
		
		
				// we will loop through the list and do any *,/, or % operators
				
				for (int i=0; i<expression.size()-1;) // DO NOT INCREMENT i here
				{
					
					// get the first number as a String and convert it to an int
					String numAsString = expression.get(i);
					int num1 = Integer.parseInt(numAsString);
					
					// get the operator
					String operator = expression.get(i+1);
					
					// get the second number as a String and convert it to an int
					numAsString = expression.get(i+2);
					int num2 = Integer.parseInt(numAsString);
					
					
					// start of  *, /, or % operator *****************************  
					if (operator.equals("+") || operator.equals("-"))
					{
						// do the math and get the answer
						
						int answer = 0;
						
						if (operator.equals("+"))
						{
							answer = num1 + num2;
						}
						
						else if (operator.equals("-")) {
							answer = num1 - num2;
						}
						
						// now remove the i and i+1 elements from the expression
						expression.remove(i);
						expression.remove(i);  // yes this should be i since the i+1 element moved down 1 
						
						
						
						// now change the i+2 element which moved down into the i position
						expression.set(i, ""+answer); // yes this should be i
						
						// do not change i
						
					}
					else
					{
						//                  i
						// move i past the num operator1  (example: num operator1 num operator2
						i = i + 2;
					}			
					
				} // end of for loop that handles *, /, and % operators 

	}
	
	
	public void solveExpression()
	{

		handleMultiplicationAndDivision();

		handleAdditionAndSubtraction();

		
	}



	public String toString( )
	{
		return expressionAsString + " = " + expression.get(0);
	}
}