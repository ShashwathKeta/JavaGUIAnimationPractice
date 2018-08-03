//© A+ Computer Science  -  www.apluscompsci.com
//Name - 
//Date -
//Class -
//Lab  -
import static java.lang.System.*;

import java.util.Scanner;

// Lab Chapter 16 - #2  ExpressionSolver    2015

// Uses files ExpressionSolverRunner.java and ExpressionSolver.java

// HINT:  Look at sample ArrayList pictures to understand what you need to do in your code.
//		  DRAW PICTURES and walk through what you need to do.
//        If you don't know what needs to be done, you certainly cannot give
//        instructions to the computer to tell it what to do.


public class ExpressionSolverRunner
{
	public static void main( String args[] )
	{
		Scanner scan = new Scanner (System.in);
		
		
		System.out.println("Lab Chapter 16 - #2  ExpressionSolver    2015");
		System.out.println();
		System.out.println();
		
		// ***** fill in your name
		System.out.print("Enter equation: ");
		String equation = scan.nextLine();
		
		ExpressionSolver test = new ExpressionSolver(equation);
		test.solveExpression();
		out.println(test);
		out.println();
		
		// get your add to work first
		/*ExpressionSolver test = new ExpressionSolver("3 + 5");
		test.solveExpression();
		out.println(test);
		out.println();


		// once you get all of the other operators finished, uncomment this code
		// and see if they all work


		test.setExpression("3 * 5");
		test.solveExpression();
		out.println(test);
		out.println();

		test.setExpression("3 - 5");
		test.solveExpression();
		out.println(test);
		out.println();

		test.setExpression("3 / 5");
		test.solveExpression();
	   	out.println(test);
		out.println();

	   	test.setExpression("5 / 5 * 2 + 8 / 2 + 5");
		test.solveExpression();
		out.println(test);
		out.println();

	   	test.setExpression("5 * 5 + 2 / 2 - 8 + 5 * 5 - 2");
		test.solveExpression();
		out.println(test);
		out.println();
		out.println();
		*/


	}
}