import java.io.*;

public class Pro3_sookiamu {
    
    private static BufferedReader cin = new BufferedReader (new InputStreamReader (System.in) );
    public static Graph G = new Graph ();
    public static Node N = new Node ();
    public Pro3_sookiamu () {}
    
    //use BufferedReader elsewhere
    public static BufferedReader getCin() { return cin;}
    public static void setCin(BufferedReader cin) { Pro3_sookiamu.cin = cin; }
    
    //function to display main menu
    public static void displayMenu () {
        System.out.print("   JAVA TRAVELING SALESMAN PROBLEM V1\n" + "G - Create graph\n" +"C - Edit cities\n" + "A - Edit arcs\n");
        System.out.print("D - Display graph info\n" + "R - Reset graph\n" + "P - Enter salesman's path\n" + "Q - Quit\n\n");
        }
 
      
    //function to get integer
    public static int getInteger (String prompt, int LB, int UB) throws IOException {
        
        boolean valid;
        int value;
        
        do {
           valid = true;
           value = 1;
           System.out.print(prompt);
           
            try { value = Integer.parseInt(Pro3_sookiamu.getCin().readLine()); }
            
                catch (NumberFormatException e) {
                	
                	if (UB == Integer.MAX_VALUE) {
                		System.out.format("ERROR: Input must be an integer in [%d, infinity]!\n\n", LB);
                		valid = false;
                	}
                	else {               	
		                System.out.format("ERROR: Input must be an integer in [%d, %d]!\n\n", LB, UB);
		                valid = false;        }
                }
            
                catch (IOException e) {
                	if (UB == Integer.MAX_VALUE) {
                		System.out.format("ERROR: Input must be an integer in [%d, infinity]!\n\n", LB);
                		valid = false;
                	}
                	else {               	
		                System.out.format("ERROR: Input must be an integer in [%d, %d]!\n\n", LB, UB);
		                valid = false;        }  
                }
            
            if (value < LB || value > UB) {
            	if (UB == Integer.MAX_VALUE) {
            		System.out.format("ERROR: Input must be an integer in [%d, infinity]!\n\n", LB);
            		valid = false;
            	}
            	else {               	
	                System.out.format("ERROR: Input must be an integer in [%d, %d]!\n\n", LB, UB);
	                valid = false;        }    
                }
            
            
 
             } while (!valid);
        
        return value;    }

    //function to get string - no checks necessary
    public static String getString (int i) throws IOException {
        
        String input;
                
        System.out.format("\nCity %d:\n" + "   Name: ", i );
        input = (Pro3_sookiamu.getCin().readLine());  
           
        return input;
        
    }
    
    //function to get string - no checks necessary
    public static String editString (int i) throws IOException {
        
        String input;
        
              
        System.out.format("   Name: ");
        input = (Pro3_sookiamu.getCin().readLine());  
           
        return input;
        
    }
    
    
        
    //function to get double
    public static double getDouble (String prompt, double LB, double UB) {
        boolean valid;
        double value;
        do {
            valid = true;
            value = 0;
            System.out.print(prompt);
            try { value = Double.parseDouble(cin.readLine()); }
            
               catch (NumberFormatException e) {
                   System.out.format("ERROR: Input must be a real number in [%.2f, %.2f]!\n\n", LB, UB);
                   valid = false;        }
               
               catch (IOException e) {
                   System.out.format("ERROR: Input must be a real number in [%.2f, %.2f]!\n\n", LB, UB);
                   valid = false;            }
               
            if (value < LB || value > UB) {
                System.out.format("ERROR: Input must be a real number in [%.2f, %.2f]!\n\n", LB, UB);
                   valid = false;            }
        
                } while (!valid);
            
        return value;
            
        }
    
    //function to create graph - gets number of cities, adds name + latitudes/longitudes || ADD IF STATEMENT TO PRINT INIFINITY
    public static void CreateGraph () throws IOException {
        
        //create number of cities based off user input
    	G.setnumNodes(getInteger ("Enter number of cities (0 to quit): ", 0, Integer.MAX_VALUE ));
        G.initialize(G.getnumNodes());
        addArcs(G);
                       
    }
      
    //Allow the user to add arcs to the graph.
    public static void addArcs(Graph G) throws IOException {
        
    G.printNode();
        
        boolean valid = false;
        
        do {
        
            int city1 = getInteger ("Enter first city index (0 to quit): ", 0, G.getnumNodes());
                if (city1 == 0) { System.out.println(); return; }
            
            int city2 = getInteger("Enter second city index (0 to quit): ", 0, G.getnumNodes());
                if (city2 == 0) { System.out.println(); return; }
                
            if (city1 == city2) {
                System.out.println("\nERROR: A city cannot be linked to itself!\n");
                valid = false;
              }
        
                else {
                    if ( G.addArc(city1, city2) )
                            System.out.println("\nArc " + city1 +"-" + city2 +" added!\n");
                    
                    else {
                        System.out.println("\nERROR: Arc already exists!\n");
                         }
                      }
        
        } while (!valid);
        
               
    }
        
    //Allow the user to edit city information inside the graph.
    public static void editCities() throws IOException {
        G.printNode();
        G.userEdit();
            }
    
    //Allows the user to edit arcs (add or remove)
    public static void editArcs () throws IOException {
        
        String input;   
        char men_op = 'a';
        System.out.println();
        do {
            G.printArcs();
            System.out.println("EDIT ARCS\n" + "A - Add arc\n" + "R - Remove arc\n" + "Q - Quit");
            System.out.print("\nEnter choice: ");
            input = (getCin().readLine());
            
            if (input.length() == 0) { System.out.println("\nERROR: Invalid menu choice!\n"); continue; }
            
            men_op = input.charAt(0);

            if (men_op != 'a' && men_op != 'A' && men_op != 'r' && men_op != 'R' && men_op != 'q' && men_op != 'Q')
                System.out.println("\nERROR: Invalid menu choice!\n");
            
            if (men_op == 'a' || men_op == 'A') {
                   addArcs (G);
                   continue;
                     }
                                
            if (men_op == 'r' || men_op == 'R') {
                    removeArc ();
                    continue;
                    }
            
             if (men_op == 'q' || men_op == 'Q') {
            	System.out.println();
                return;    }
            
        } while (men_op != 'q' || men_op != 'Q');
        
        
    }
    
    //Allow the user to remove arcs from the graph
    public static void removeArc() throws IOException {
        
    	//DO WHILE LOOP TO RUN UNTIL 0 IS PRESSED
    	
    	int option;
    	System.out.println();
    	do {
    	
    	//System.out.println();
        G.printArcs();
        option = getInteger ("Enter arc to remove (0 to quit): ", 0, G.count);
        if (option == 0) { System.out.println(); return; }
        G.removeArc (option);
     
    	} while (option != 0);
        
    }
    
    //Get a path from the user, check its feasibility, and then print its cost.
    public static void tryPath(Graph G) throws IOException {
        
        G.printNode();
    
	    System.out.println("Enter the " + (G.getnumNodes() + 1) + " cities in the route in order: ");
	        
	    int [] path = new int [G.getnumNodes () + 1];
	         for (int i = 0; i < path.length; i ++)
	             path [i] = getInteger ("City " + (i+1) +": ", 1, G.getnumNodes());
	         
	     if(!G.checkPath(path))
	         return;
	     
	     else System.out.format ("\nThe total distance traveled is %.2f km.\n\n", G.pathCost(path) );
        
                
    } 

    
    //Main function
    public static void main(String[] args) throws NumberFormatException, IOException {
    	
    menuOption();
     
       }
       
    //function to get character menu options (used for main menu and edit arcs)
    public static void menuOption () throws IOException {
                
        String input;   
        char men_op = 'a';
        
        do {
        	displayMenu ();
            System.out.print("Enter choice: ");
            input = (getCin().readLine());
            
            if (input.length() == 0) { System.out.println("\nERROR: Invalid menu choice!\n"); continue; }
            
            men_op = input.charAt(0);
            
            if ( men_op != 'g' && men_op != 'G' && men_op != 'c' && men_op != 'C' && men_op != 'd' && men_op != 'D' && men_op != 'r' && men_op != 'R' && men_op != 'p'
            		&& men_op != 'P' && men_op != 'q' && men_op != 'Q' && men_op != 'a' && men_op != 'A' ) {
                System.out.println("\nERROR: Invalid menu choice!\n");
                continue;
            }
            
            if (men_op == 'q' || men_op == 'Q') {
                System.out.print("\nCiao!\n"); 
                return;
                 }
            
            if (men_op == 'g' || men_op == 'G') {
            		G.reset();
                    CreateGraph (); 
                    continue;
                     }
            
            if ( G.getNode().isEmpty() ) {
            	
            	System.out.println ("\nERROR: No graph has been loaded!\n");
            	continue;
            }
                                
            if (men_op == 'c' || men_op == 'C') {
                    editCities ();
                    continue; }
            
            if (men_op == 'a' || men_op == 'A') {
                    editArcs (); 
                    continue; }
            
            if (men_op == 'd' || men_op == 'D') {
                	G.print ();   
                	continue; }
            
            if (men_op == 'r' || men_op == 'R') {
                	G.reset ();   
                	continue; }
            
            if (men_op == 'p' || men_op == 'P') {
                	tryPath (G) ; 
                	continue; }
            
                            
                
        } while (men_op != 'q' || men_op != 'Q');
        
               
    }



}
