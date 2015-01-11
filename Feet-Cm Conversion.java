//header file, similar to library
import java . io .*;

//main class
public class Pro2_sookiamu {
    
	//object for input/output
	public static BufferedReader cin = new BufferedReader (new InputStreamReader (System.in) );
    
	//initiating variables and constants || solution used in FeetInchestoCM, value used in getDouble
	public static final double _IN_TO_CM = 2.54;
	public static int men_op; static double feet; static double inches; static double centimeters; static double solution; static double value;
	public static boolean valid;
	public static int check;
    
	//Display the menu
	public static void displayMenu () {
	System.out.println("   JAVA CONVERSION PROGRAM");
	System.out.println("0 - Quit" + "\n1 - Convert feet and inches to centimeters" + "\n2 - Convert centimeters to feet and inches"); }
                 
     //Menu Input: stay in loop using condition valid: reset men_op value to prevent double error messaging
     public static int getInteger (String prompt, double LB, double UB) {
            do {
               valid = true;
               men_op = 0;
               System.out.print(prompt);
               
                try { men_op = Integer.parseInt(cin.readLine()); }
                
                	catch (NumberFormatException e) {
                    System.out.println("ERROR: Input must be an integer in [0, 2]!");
                    valid = false;        }
                
                	catch (IOException e) {
                    System.out.println("ERROR: Input must be an integer in [0, 2]!");
                    valid = false;            }
                
                if (men_op < LB || men_op > UB) {
                    System.out.println("ERROR: Input must be an integer in [0, 2]!");
                    valid = false;         }
     
                 } while (!valid);
            
            return men_op;    }
     
     //Data Input: stay in loop using valid: reset value to prevent double error messaging
     public static double getDouble (String prompt, double LB, double UB) {
         do {
             valid = true;
             value = 0;
             System.out.print(prompt);
             try { value = Double.parseDouble(cin.readLine()); }
             
                catch (NumberFormatException e) {
                    System.out.format("ERROR: Input must be a real number in [%.2f, infinity]!\n\n", LB);
                    valid = false;        }
                
                catch (IOException e) {
                    System.out.format("ERROR: Input must be a real number in [%.2f, infinity]!\n\n", LB);
                    valid = false;            }
                
             if (value < LB || value > UB) {
                    System.out.format("ERROR: Input must be a real number in [%.2f, infinity]!\n\n", LB);
                    valid = false;            }
         
                 } while (!valid);
             
         return value;
             
         }
         
	//Get feet and inches from the user, then print the number of centimeters.
	public static void feetInchesToCm(double feet, double inches) {
		
	solution = ((feet * 12) + inches)* _IN_TO_CM;
	
	System.out.format("\n%.2f feet %.2f inches equals %.2f centimeters.\n\n", feet, inches, solution); }

	//Get centimeters from the user, then print the number of feet and inches.
	public static void cmToFeetInches (double centimeters) {  
		
	double cmfeet; double cminches;
	
	if ((centimeters/2.54)/12 >= 1) {
		
		cmfeet = Math.floor( (centimeters/2.54) / 12); }
	
	else cmfeet = 0.00;
		
	cminches = (centimeters/2.54) % 12;
	
	System.out.format("\n%.2f centimeters equals %.2f feet %.2f inches.\n\n", centimeters, cmfeet, cminches); }

public static void main(String[] args) throws NumberFormatException, IOException {
	           
	//begin do-while loop - Open menu and provide options, use check to stay in loop (or exit)
	do {
	    check = 0;
	    displayMenu();
	    
	    	men_op = getInteger ("\nEnter choice: ", 0, 2);
	         
	           //if 0, exit
	           if (men_op == 0) {
	           System.out.println("\nGoodbye!");
	           check = 0; }
	            
	                        
	           //if user selects 1: convert feet/inches to centimeters
	            else if (men_op == 1) {
	            System.out.print("\n");
	            feet = getDouble ("Enter feet: ", 0, Double.MAX_VALUE);
	            inches = getDouble ("Enter inches: ", 0, Double.MAX_VALUE);
	            feetInchesToCm (feet, inches);     
	            check = 1; }
	         
	            //if user selects 2: convert centimeters to feet/inches
	            else if (men_op == 2) {
	            System.out.print("\n");
	            centimeters = getDouble ("Enter centimeters: ", 0, Double.MAX_VALUE);
	            cmToFeetInches (centimeters);
	            check = 1; }
	           
	            //condition for staying in loop - input should not be one
	         } while (check != 0);
	
}//end main
}//end class

