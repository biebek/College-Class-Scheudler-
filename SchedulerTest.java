

public class SchedulerTest
{
   public static void main (String[] args)
   {
      CourseScheduler schedule1 = new CourseScheduler("CBAClasses10.txt");
      CourseScheduler schedule2 = new CourseScheduler("CBAClasses20.txt");
      
      System.out.println("SCHEDULE 1 RESULTS:\n");
      System.out.println("There is a 2-slot solution: " + schedule1.solutionExists(2)); 
      System.out.println("Two-slot solution: " + schedule1.getSchedule(2));
      System.out.println("\nThere is a 3-slot solution: " + schedule1.solutionExists(3)); 
      System.out.println("\nThree-slot solution: " + schedule1.getSchedule(3));   
      System.out.println("\nReachable from BUSN 1001: " + schedule1.reachable("BUSN 1001"));

      System.out.println("\nSCHEDULE 2 RESULTS:");
      System.out.println("\nThere is a 3-slot solution: " + schedule2.solutionExists(3)); 
      System.out.println("\nReachable from BUSN 1001: " + schedule2.reachable("BUSN 1001"));
    }
}
