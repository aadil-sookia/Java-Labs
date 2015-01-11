//header file, similar to library
import java . io .*;
import java.lang.Math.*;

//main class
public class Pro1_sookiamu {
	
	public static void main (String[] args) throws NumberFormatException, IOException {
		
		//object for input/output
		BufferedReader cin = new BufferedReader (new InputStreamReader (System.in) );

		//variables for input
		double a; double b; double c; double root1 = 0; double root2 = 0;
		
		//opening sentence	
		System.out.println("   JAVA QUADRATIC FORMULA PROGRAM");
		
		//obtain input
		System.out.print("Enter A (0 to quit): ");
		a = Double.parseDouble(cin.readLine());
		
		//if a is not 0, receive B and C
		while (a != 0) {
			System.out.print("Enter B: ");
			b = Double.parseDouble(cin.readLine());
			
			System.out.print("Enter C: ");
			c = Double.parseDouble (cin.readLine());
			
			//check if real roots exist			
			if ((b*b - 4*a*c) >= 0) {
				//apply formula for root 1 and root 2
				root1 = (-b + Math.sqrt((b*b)-(4*a*c)))/(2*a);
				root2 = (-b - Math.sqrt((b*b)-(4*a*c)))/(2*a);
					
					//print roots
					System.out.format("\nThe real roots are %.4f and %.4f.\n\n", root1, root2);
		}
			//if no roots, print error
			else System.out.print("\nERROR: No real roots exist!\n\n");
			
			//restart program
			System.out.println("   JAVA QUADRATIC FORMULA PROGRAM");
			System.out.print("Enter A (0 to quit): ");
			a = Double.parseDouble(cin.readLine());
							
		}
		//if a is 0, quit program
		 System.out.println("\nQuitting program.\n");
			
    
}

	private static double sqrt(double d) {
		// TODO Auto-generated method stub
		return 0;
	}
    
    
}
