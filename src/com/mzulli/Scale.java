import java.util.Scanner;
/*
 * Gets a note from the user and prints the chromatic scale.
 */
public class Scale {
   private int rows = 4;
   private int cols = 23;
   private String[][] notes;
   private int tonicRow = 0;
   private int tonicCol = 0;
   private String startNote;
   
   public Scale() {
      notes = new String[rows][cols];
        
      //fill array with initial note names
      //duplicate values across rows confuse the search, so dups in
      //different rows are represented with a pointer
      
      //populate naturals and sharps
      notes[0] = new String[] {"C","C#","D","D#","E","F","F#","G",
         "G#","A","A#","B","","","","","","","","","","","",""};
      
      //populate flats
      notes[1] = new String[] {"00","Db","02","Eb","04","05","Gb",
         "07","Ab","09","Bb","011","","","","","","","","","","","",
         ""};
      
      //populate alternate sharps
      notes[2] = new String[] {"B#","01","02","03","04","E#","06",
         "07","08","09","010","011","","","","","","","","","","","",
         ""};
      
      //populate alternate flats
      notes[3] = new String[] {"00","11","02","13","Fb","05","16",
         "07","18","09","110","Cb","","","","","","","","","","","",
         ""};
        
      //populate remaining notes
      int numNotes = 12;
      int i, j;
      for (i = 0; i < rows; i++){
         for (j = numNotes; j < cols; j++){
            notes[i][j] = notes[i][j % numNotes];
         }
      }
   }

   public static void main(String[] args) {
      Scale scale = new Scale();
      
      //ask the user for the starting note and set it as the tonic
      
      //prompt user for starting note
      Scanner inputStart = new Scanner(System.in);
      String prompt = "Enter the starting note (use \"#\" for " 
         + "sharp or \"b\" for flat): ";
      try {
         System.out.print(prompt);
         scale.startNote = inputStart.next();
         while(!scale.startNote.matches("^[A-Ga-g][#b]{0,2}")) {
            System.out.println(scale.startNote 
                                  + " isn't a valid note. "
                                  + "Please try again.");
            scale.startNote = inputStart.next();
         }
      }
      finally {
         inputStart.close();
      } 
        
      //capitalize first character of the user's starting note,
      //lowercase the second,
      //pass to setTonic
      scale.setTonic(scale.startNote.substring(0,1).toUpperCase() 
                  + scale.startNote.substring(1).toLowerCase());
      
      //print the chromatic scale
      scale.printChromatic();
   }
    
   //convert starting note to tonic with a row and col index
   public void setTonic(String target) {
      int i = 0;
      int j = 0;
        
      //if the startNote is C set tonic to notes[0][0]
      if(target.equals(notes[i][j])) {
         tonicRow = i;
         tonicCol = j;
      }
        
      //otherwise, find it in the array
      else {
         for(i = 0; i < rows; i++) {
            for(j = 0; j < cols; j++) {
               if(target.equals(notes[i][j])) {
                  tonicRow = i;
                  tonicCol = j;
                  break;
               }
            }
         }
      }
   }
            
   //print chromatic scale
   public void printChromatic() {
      String target = startNote.substring(0,1).toUpperCase() 
         + startNote.substring(1);
      int i = tonicRow;
      int j = 0;
      int resume = 0;
        
      //if the tonic is Cb, Fb, B#, E#, use natural enharmonic
      if(target.equals("Cb") || target.equals("Fb") 
            || target.equals("B#") || target.equals("E#")) {
         System.out.println(target + " is enharmonic with " 
                               + notes[0][tonicCol] + ".");
         System.out.println(notes[0][tonicCol] 
                               + " chromatic scale:");
         target = notes[0][tonicCol];
      }
      else {
         System.out.println(notes[tonicRow][tonicCol] 
                               + " chromatic scale:");
      }
        
      //if the tonic is a natural note, 
      //use sharps ascending and flats descending
      if(target.length() == 1) {
         printNatural();
      }
        
      else {
         //print ascending
         for(j = tonicCol; j < tonicCol + 12; j++) {
            printNote(i,j);
            System.out.print(" ");
         }
                
         //print the octave
         printNote(i,tonicCol);
         System.out.print(" ");
                
         //print descending
         resume = j;
         for(j = resume - 1; j >= tonicCol; j--) {
            printNote(i,j);
            System.out.print(" ");
         }
      }
      System.out.println();
   }   

   //print the chromatic scale for natural notes
   public void printNatural() {
      int i = 0;
      int j = 0;
      int resume = 0;
      
      //print ascending
      for (j = tonicCol; j < tonicCol + 12; j++) {
         printNote(0,j);
         System.out.print(" ");
      }
      
      //print the octave
      printNote(i,tonicCol);
      System.out.print(" ");
      
      //print descending
      resume = j;
      for(j = resume - 1; j >= tonicCol; j--) {
         printNote(1,j);
         System.out.print(" ");
      }
   }
   
   //print the full array with pointers converted to notes
   public void printArray() {
      for (int i = 0; i < rows; i++){
         for (int j = 0; j < cols; j++){
            printNote(i,j);
            System.out.print(" ");
         }
         System.out.println();
      }
   }
   
   //print note
   public void printNote(int noteRow, int noteCol) {
      String target = notes[noteRow][noteCol];
      int newRow = 0;
      int newCol = 0;
      int targetLength = target.length();
      
      //if index contains numbers, turn them into row and col index, 
      //otherwise use passed row and col
      if(target.matches("\\b\\d{2,3}\\b")) {
         newRow = Integer.parseInt(target.substring(0,1));
         
         //if target is more than 2 digits, use last 2 for newCol, 
         //otherwise use last 1
         if(targetLength > 2) {
            newCol = Integer.parseInt(target.substring(1,3));
         }
         else {
            newCol = Integer.parseInt(target.substring(1,2));
         }
         System.out.print(notes[newRow][newCol]);
      }
      else {
         System.out.print(notes[noteRow][noteCol]);
      }
   }
   
   //get start note
   public String getStartNote() {
      return startNote;
   }    
   
   //print tonic
   public void printTonic() {
      System.out.println("Your starting note, " + startNote 
                            + ", was found in row " + tonicRow 
                            + ", column " + tonicCol + ".");
   }
}