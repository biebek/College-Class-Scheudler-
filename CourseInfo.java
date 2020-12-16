
/**
Initializes necessary variables.
*/
public class CourseInfo {
   private String courseName;
   private String dept;
   private int number;
   private String building;
   private String room;
   private String instructor;
   private int timeSlot;
   
   
   /**
Constructor to properly assign the variables.
@param c the course name.
@param d the discipline name.
@param n the four-digit number identifying the level of the course.
@param b the four-character code identifying building where course is taught.
@param r the four-character code identifying room where course is taught.
@param i the last name of the instructor, followed by a space, followed by first initial
*/
   public CourseInfo(String c, String d, int n, String b, String r, String i){
      courseName = c;
      dept = d;
      number = n;
      building = b;
      room = r;
      instructor = i;
      timeSlot = 0;
   }
   
   /**
   This method returns the course name.
   @return Returns the course name.
   */ 
   public String courseName(){
      return courseName;
   }
   
   /**
   This method returns the discipline name.
   @return Returns the discipline name.
   */ 
   public String getDept(){
      return dept;
   }
   
   /**
   This method returns the course difficulty level.
   @return Returns the level of course.
   */ 
   public int getDeptNum(){
      return number;
   }
   
   /**
   This method returns the building name.
   @return Returns the building name.
   */ 
   public String getBuilding(){
      return building;
   }
   
   /**
   This method returns the room number.
   @return Returns the room number.
   */ 
   public String getRoom(){
      return room;
   }
   
   /**
   This method returns the name of instructor.
   @return Returns the name of the instructor.
   */ 
   public String getInstructor(){
      return instructor;
   }
   
   /**
   This method sets the time slot of the class.
   @param n the time of the class.
   */ 
   public void setTimeSlot(int n){
      timeSlot = n; 
   }
   
   /**
   This method returns the time of the class.
   @return Returns the time frame of the class.
   */ 
   public int timeSlot(){
      return timeSlot;
   }
   
   /**
   This methods returns the output in the desired format
   @return Returns the discipline name, course number, the building it is taught in, the room number and the name of the instructor.
   */
   public String toString(){
      return "\n   "+dept+" "+number+" "+ building+ " " +room.substring(1)+ " " + instructor;
   }  
} 