//import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.Arrays;

public class Graph {
    
    //creating variables
    public static Pro3_sookiamu M = new Pro3_sookiamu ();
    public static Node N = new Node ();
    private int numNodes;
    private int numArcs;
    private boolean [][] adjMatrix;
    private static double [][] costMatrix;
    private static ArrayList <Node> node = new ArrayList <Node> ();
    
    //2d array to link the count number with its respective node connections (arcs)
    int linksize = 50;
    int [][] links = new int [linksize][2];
    int count;
    
    //constructors
    public Graph () {}
    public Graph (int numNodes) {}
    
   
      
    //setters
    public void setnumNodes (int n) { this.numNodes = n; }
    public void setnumArcs (int m) { this.numArcs = m; }
    public void setArc (int i, int j, boolean b) { adjMatrix [i][j] = b; adjMatrix [j][i] = b; }
    public static void setCost (int i, int j, double c) { costMatrix [i][j] = c; costMatrix [j][i] = c; }
          
    //getters
    public int getnumNodes () { return this.numNodes; }
    public int getnumArcs () { return this.numArcs; }
    public boolean getArc (int i, int j) { return this.adjMatrix [i][j]; }
    public double getCost (int i, int j) { return Graph.costMatrix [i][j]; }
    public ArrayList <Node> getNode () { return node; }
     
    //function to fill in arraylist with strings (names) and doubles (latitudes/longitudes)
    public void initialize (int numNodes) throws IOException {
        
        //setnumNodes (numNodes);
        
        //creating size for arcs array (add 1 to account for arrays counting from 0)
        adjMatrix = new boolean[numNodes + 1][numNodes + 1];
        costMatrix = new double[numNodes + 1][numNodes + 1];
        
        for (int i = 0; i <= numNodes; i++)
            for (int j = 0; j <= numNodes; j++) {
                adjMatrix[i][j] = false;
                costMatrix[i][j] = 0; }
       
        //initializing values of arraylist (node-type)
        for (int i = 1; i <= numNodes; i ++) {
            
            //do-while loop to check if city already exists
            boolean check = false;
            
            do {
                
                check = addNode(i);
            
            } while (!check);
                   
        }
        
        System.out.print("\nNow add arcs:\n");
        //code to access/print from arraylist of type node
        //double dummy = node.get(0).getLatitude();
        //System.out.println(dummy);
        
                
    }
    
    // add a node || NOTE: USED WHEN EDITING CITIES
    public boolean addNode (int i) throws IOException {
        
        boolean check = false;
                
                 N.setName(Pro3_sookiamu.getString(i));
                 N.setLatitude(Pro3_sookiamu.getDouble ("   latitude: ", -90.0, 90.0));
                 N.setLongitude(Pro3_sookiamu.getDouble("   longitude: ", -180.0, 180.0));
                 
                 if (!existsNode(N)) {
                     node.add(i-1, new Node ( N.getName(), N.getLatitude(), N.getLongitude() ));
                     check = true;     }
                 
                 return check;
                 
                 
    }
    
    //function to change node (name, latitude, longitude)
    public void editNode (int i) throws IOException {
        
                 
         N.setName(Pro3_sookiamu.editString(i));
         N.setLatitude(Pro3_sookiamu.getDouble ("   latitude: ", -90.0, 90.0));
         N.setLongitude(Pro3_sookiamu.getDouble("   longitude: ", -180.0, 180.0));
         
         node.set(i-1,new Node ( N.getName(), N.getLatitude(), N.getLongitude() ) );
         
         for (int a = 0; a < getnumNodes(); a ++)
        	 for (int j = 0; j < getnumNodes(); j ++)
        		 distance (a, j);
    
        System.out.println();
    }
    
    // get user info and edit node
    public void userEdit () throws IOException {
        
        int option = Pro3_sookiamu.getInteger("Enter city to edit (0 to quit): ", 0, getnumNodes());
        
        if (option == 0) return;
        
        else editNode(option);
            
    }
    
    // check if node exists to avoid repetitions
    public boolean existsNode ( Node t) {
        
        boolean check = false;
                    
        for (int i = 1; i <= node.size() && check == false; i ++)
            if ( ( t.getName().equals(node.get(i-1).getName()) ) || ( t.getLatitude() == node.get(i-1).getLatitude() && t.getLongitude() == node.get(i-1).getLongitude() ) ) {
                check = true;
                System.out.println("ERROR: City name and/or coordinates already exist!");
            }
                
        return check;
        
      }
    
    
    
    
    
    //function to print Node
    public void printNode () {
        
        System.out.println("\nNODE LIST\n" + "No." + "               " + "Name" + "        " + "Coordinates");
        System.out.println("-----------------------------------------");
        
        for (int i = 1; i <= getnumNodes(); i ++) {
        	
        	int length = Double.toString(node.get(i-1).getLatitude()).length() + Double.toString(node.get(i-1).getLongitude()).length() + 3;
        	       	        	
        	String space = " ";
        	
        	//String latitude = format (node.get(i-1).getLatitude());
        	//String longitude = format (node.get(i-1).getLongitude());
        	
        	
        	System.out.format("  %d%19s%" + (19-length) + "s(%.1f,%.1f)\n", i, node.get(i-1).getName(), space, node.get(i-1).getLatitude(), node.get(i-1).getLongitude());
        	
        }
        
        System.out.println();
        
    }
    
    // add an arc , return T/F success
    public boolean addArc ( int i, int j) {
        
        boolean check = false;
        
        //if arc doesn't exist, create arc || using -1 to match with arrays starting from 0
        if (!existsArc (i-1, j-1)) {
            
            setArc (i-1, j-1, true);
            check = true;
            }
        
        return check;
        }
        
    // check if arc exists to avoid repetitions
    public boolean existsArc ( int i, int j) {
        
        boolean check = true;
        
        //if arc doesn't exist, send back false
        if (!getArc (i, j))
            check = false;
        
        return check;
    
    }
    
    // print arc list
    public void printArcs () {
    	
        System.out.println("ARC LIST");
        System.out.println("No.    Cities       Distance");
        System.out.println("----------------------------");
        
        count = 0;
        
        for (int i = 0; i < numNodes; i ++) {
            for (int j = i + 1; j < numNodes; j ++) {
                if (existsArc (i, j)) {
                    count ++;
                    System.out.format("  %d       %d-%d%15.2f", count, (i+1), (j+1), distance (i, j) );
                    //System.out.printf("  " + count + "       " + (i+1) + "-" + (j+1) + "         " + "%.2f", distance (i, j) );  
                    System.out.println();
                    links [count][0] = i + 1;
                    links [count][1] = j + 1;    
                                      
                      }
                }
            }
        
        System.out.println();
        
        
        //System.out.println(Arrays.deepToString (links));
        
        }
        
    // remove an arc
    public void removeArc ( int count) {
    	
    	System.out.println("\nArc " + count + " removed!\n\n");
        
        setArc( (links [count][0] - 1) , (links[count][1] - 1), false);
        
        for (int i = 0; i < linksize; i ++)
            for (int j = 0; j < 2; j ++)
                links [i][j] = 0;
        
        
        
    }
       
 // print all graph info (number nodes, number arcs, node list, arc list)
    public void print () {
        
    	count = 0;
    	
        for (int i = 0; i < numNodes; i ++) 
            for (int j = i + 1; j < numNodes; j ++) 
                if (existsArc (i, j)) 
                    count ++;
    	
    	
        System.out.println("Number of nodes: " + getnumNodes());
        System.out.println("Number of arcs: " + count);
        printNode();
        printArcs();
                
    }   
    
    //resets program (clears all data in arrays)
    public void reset () {
        
        //clearing arraylist data (nodes/cities)
        node.clear();
        
        //clearing adjMatrix (arcs) and costMatrix (distances)
        for (int i = 0; i < numNodes; i ++)
            for (int j = 0; j < numNodes; j ++) {
                adjMatrix[i][j] = false;
                costMatrix [i][j] = 0;     }
        
        //clearing links array (might not be necessary but just to stay safe)
        for (int i = 0; i < linksize; i ++)
            for (int j = 0; j < 2; j ++)
                links [i][j] = 0;
        
        //zeroing numNodes and count since they are used as bounds in for-loops when printing
        setnumNodes(0);
        count = 0;
        
        System.out.println();
        
    }
    
    // calculates distance between two nodes
    public static double distance (int i, int j) {
        
        final double radians = Math.PI/180;
        
        double lat1 = radians * node.get(i).getLatitude();
        double long1 = radians * node.get(i).getLongitude();
        double lat2 = radians * node.get(j).getLatitude();
        double long2 = radians * node.get(j).getLongitude();
                    
        double a = Math.sin( (lat1 - lat2)/2 ) *  Math.sin( (lat1 - lat2)/2 ) + ( ( Math.cos(lat1) ) * Math.cos(lat2) *  Math.sin( (long1 - long2)/2 ) * Math.sin( (long1 - long2)/2 ) ) ;
        
        double b = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        
        double distance = 6371 * b;
        
        //did some static references
        setCost (i , j , distance);
        
        return distance;
            
    }
    
    // check feasibility of path P
    public boolean checkPath ( int [] path) {
        
        boolean feasible = true;
        
        //first check = see if start city and end city are same
        
        if (path[0] != path[getnumNodes()]) {
            
            System.out.println("\nERROR: Start and end cities must be the same!\n");
            feasible = false;
            return feasible;
            
        }
        
        //second check = see if user input appears twice (excluding last input)
        
        for (int i = 0; i < getnumNodes(); i ++) {
            for (int j = i + 1; j <getnumNodes(); j ++) {
                if (path[i] == path[j]) {
                    System.out.println("\nERROR: Cities cannot be visited more than once!");
                    System.out.println("ERROR: Not all cities are visited!\n");
                    feasible = false;
                    return feasible;
                                        
                }
                    
            }
        }
        
        //third check = see if arc exists
        
        for (int i = 0; i < getnumNodes(); i ++) {
            if (!existsArc( (path[i] - 1), (path[i + 1] - 1) ) ) {
                System.out.println("\nERROR: Arc " + path[i] + "-" + path[i+1] + " does not exist!\n");
                feasible = false;    
                return feasible;
            }
                        
        }
        
        return feasible; 
        
    
    
    } 
    
    // calculate cost of path P
    public double pathCost ( int [] path) { 
    	
    	double distance = 0;
    	    	
    	for (int i = 0; i < getnumNodes () ; i ++) 
    		distance += getCost ( ( path[i] - 1 ), ( path[i + 1] - 1 ) );
                    
    	return distance;
        
    
    }
    
    
        
    
    
}

