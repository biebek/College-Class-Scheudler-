/**
   Imports Java util package
*/
import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

/**
This CourseScheduler class uses different arraylist and variables that implement a backtracking solution.
*/
public class CourseScheduler {

    public ArrayList < CourseInfo > courseArray = new ArrayList < > ();       //array list to store course objects
    public boolean[][] adjacency;                                             //2D array to store boolean values for adjacency status between courses
    public int addedNodes;                                                    //total number of courses we added on the time slots as a whole

    ArrayList<ArrayList<String>> adjlist = new ArrayList<>();

/**
Constructor to read in data from a text file.
@param filename the file whose data are processed inorder to avoid the scheduling conflicts.
*/
    public CourseScheduler(String filename) {
        /**
         This try block opens the file. If successful this block is executed, otherwise control is transmitted to catch block.
         */
        try {
            Scanner in = new Scanner(new File(filename));
            int number = Integer.parseInt( in .nextLine());
            adjacency = new boolean[number][number];        
            addedNodes = 0;

            while ( in .hasNext()) {
                String[] line = in .nextLine().split(",");
                courseArray.add(new CourseInfo(line[0] + " " + line[1], line[0], Integer.parseInt(line[1]), line[2], line[3], line[4]));        
            }
            createAdjacencyMatrix();         
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
        }
    }

/**
This method resets the entire process and runs the graph coloring again with provided parameters in case the same object is used again with different parameters.
@param m the numbers of courses.
*/
    public void reset(int m) {
        addedNodes = 0;
        for (int j = 0; j < courseArray.size(); j++) {
            CourseInfo course = courseArray.get(j);
            course.setTimeSlot(0);        
        }
        schedule(0, m);
    }

/**
This method creates an Adjaceny Matrix to avoid class conflicts by checking the conditions mentioned in the above program description.
*/
    public void createAdjacencyMatrix() {
        for (int i = 0; i < courseArray.size(); i++) {
            CourseInfo course1 = courseArray.get(i);
            for (int j = 0; j < courseArray.size(); j++) {
                CourseInfo course2 = courseArray.get(j);
                String first_coursenum_course1 = (course1.getDeptNum() + "").charAt(0) + "";
                String first_coursenum_course2 = (course2.getDeptNum() + "").charAt(0) + "";

                boolean course1_3or4 = first_coursenum_course1.equals("3") || first_coursenum_course1.equals("4");
                boolean course2_3or4 = first_coursenum_course2.equals("3") || first_coursenum_course2.equals("4");

                if (course1.getInstructor().equals(course2.getInstructor()) ||
                        course1.getRoom().equals(course2.getRoom()) ||
                        (course1.getDept().equals(course2.getDept()) && course1_3or4 && course2_3or4)) {
                    adjacency[i][j] = true;
                } else {
                    adjacency[i][j] = false;
                }
            }
        }

        for (boolean[] row : adjacency) {
        }
    }


/**
This schedule method checks whether each class is ideal for our solution making a recursive call and sets time time for class.
@param i the class for the time is being checked.
@param allowedTimeSlots the time slot that is being checked.
*/
    public void schedule(int i, int allowedTimeSlots) {
        for (int time = 1; time <= allowedTimeSlots; time++) {                      
            if (promising(i, time) && courseArray.get(i).timeSlot() == 0) {         
                courseArray.get(i).setTimeSlot(time);                              
                addedNodes++;
                if (i + 1 < courseArray.size())          
                    schedule(i + 1, allowedTimeSlots);   
            }
        }
    }

/**
This promising method check whether a time slot has been found for a course.
@param i the class for the time is being checked.
@param time the time slot for the class.
@return returns if a promising time slot has been found for the class.
*/
    public boolean promising(int i, int time) {
        for (int j = 0; j < courseArray.size(); j++) {                     
            if (adjacency[i][j] && time == courseArray.get(j).timeSlot()) {   
               return false;
            }
        }
        return true;
    }

/**
This methods gets the courses based on the number of slots.
@param m the number of slots that is required.
@return returns the list of classes. If not possible, returns the message accordingly. 
*/
    public String getSchedule(int m) {
        String toPrint = "";
        adjlist = new ArrayList<>();
        for (int i = 1; i <= m; i++) {
            toPrint += "\nTime " + i + ":";
            ArrayList<String> sublist = new ArrayList<>();
            for (int j = 0; j < courseArray.size(); j++) { 
                CourseInfo course = courseArray.get(j);
                if (course.timeSlot() == i) {              
                    toPrint += course;
                    String[] crs = course.toString().split(" ");
                    sublist.add(crs[3]+" "+crs[4]);
                }
            }
            adjlist.add(sublist);
        }
        if (!solutionExists(m))
            return "No solution with " + m + " slots.";

        return toPrint;
    }
/**
This method checks if the solution in terms of class schedule is possible with m slots.
@param m the number of slots for classes.
@return returns true or false based on classes.
*/
    public boolean solutionExists(int m) {
        reset(m);                               
        if (addedNodes == courseArray.size())   
            return true;
        return false;
    }

/**
This method retunrs the number and list of courses reachable from the course given as argument,
@param from the course name given in the form DEPT ####
@return returns the name and number of the courses.
*/
    public String reachable(String from) {
        solutionExists(3);
        getSchedule(3);
        if(adjlist.size()==0){
            return "";
        }
        String solution = "";
        int row = 0;
        int index = Integer.MAX_VALUE;
        for(ArrayList<String> cst:adjlist){
            index = cst.indexOf(from);
            if(index==-1){
                row+=1;
            }
            else{
                break;
            }
            
        }
        solution = adjlist.size() + " courses ";
        for(ArrayList<String> cst:adjlist){
            solution += cst.get(index)+", ";
        }

        return solution.substring(0,solution.length()-2);
        
    }
    
/**
This is a helper method for method reachable that performs depth first search to produce course graph.
@param start the course for which the course graph is needed.
@param visited the array that has the records of visited courses already.
*/

    private  void DFS(int start, boolean[] visited)
    {
        visited[start] = true;

        for (int i = 0; i < adjacency.length; i++) {

            if (adjacency[start][i] && (!visited[i])) {
                DFS(i, visited);
            }
        }
    }

} 