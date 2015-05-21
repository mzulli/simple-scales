package com.mzulli.scales;

import java.util.Scanner;

public class Scale {
    private int rows = 4;
    private int cols = 23;
    private String[][] notes;
    private int tonicRow = 0;
    private int tonicCol = 0;
    private String startNote;
    private Note tonic;
  
    public Scale() {
        notes = new String[rows][cols];
        
        //fill array with initial note names
        //duplicate values across rows confuse the search, so dups in different rows are represented with a pointer to the correct note
        notes[0] = new String[] {"C","C#","D","D#","E","F","F#","G","G#","A","A#","B","","","","","","","","","","","",""}; //naturals and sharps
        notes[1] = new String[] {"00","Db","02","Eb","04","05","Gb","07","Ab","09","Bb","011","","","","","","","","","","","",""}; //flats
        notes[2] = new String[] {"B#","01","02","03","04","E#","06","07","08","09","010","011","","","","","","","","","","","",""}; //alternate sharps
        notes[3] = new String[] {"00","11","02","13","Fb","05","16","07","18","09","110","Cb","","","","","","","","","","","",""}; //alternate flats
        
        // populate remaining notes
        int numNotes = 12;
        int i, j;
        for (i = 0; i < rows; i++){
            for (j = numNotes; j < cols; j++){
                notes[i][j] = notes[i][j % numNotes];
            }
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
        
        //if index contains numbers, turn the numbers into row and column index, otherwise use passed row and col
        if(target.matches("\\b\\d{2,3}\\b")) {
            newRow = Integer.parseInt(target.substring(0,1));
            
            //if target is more than 2 digits, use last 2 for newCol, otherwise use last 1
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
    

    //ask the user for the starting note and set it as the tonic
    public void setStartNote() {
        //prompt user for starting note
        Scanner inputStart = new Scanner(System.in);
        String prompt = "Enter the starting note (use \"#\" for sharp or \"b\" for flat): ";
        try {
            System.out.print(prompt);
            startNote = inputStart.next();
            while(!startNote.matches("^[A-Ga-g][#b]{0,2}")) {
                System.out.println(startNote + " is not a valid note. Please try again.");
                startNote = inputStart.next();
            }
        }
        finally {
            inputStart.close();
        } 
        
        //capitalize the first character of the user's starting note,
        //lowercase the second,
        //pass to setTonic
        setTonic(startNote.substring(0,1).toUpperCase() + startNote.substring(1).toLowerCase());
    }
    
    //get start note
    public String getStartNote() {
      return startNote;
    }
    
    //set the tonic
    //convert the user's starting note to a Note with a row and col index
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
        
        tonic = new Note(tonicRow, tonicCol);
    }
    
    //get tonic from Note class
    public Note getTonic() {
      tonic.getNote();
      return tonic;
    }

    //HERE 2015.05.20
    //get tonic row from Note
    public int getTonicRow() {
      tonic.getRow();
      return
    }
    
    //print tonic
    public void printTonic() {
      
      //System.out.println("Your starting note, " + startNote.substring(0,1).toUpperCase() + startNote.substring(1) 
      System.out.println("Your starting note, " + tonic 
                           + ", was found in row " + tonicRow + ", column " + tonicCol + ".");
    }
    
    //print chromatic scale
    public void printChromatic() {
        String target = startNote.substring(0,1).toUpperCase() + startNote.substring(1);
        int i = tonicRow;
        int j = 0;
        int resume = 0;
        
        //if the tonic is Cb, Fb, B#, E#, use natural enharmonic
        if(target.equals("Cb") || target.equals("Fb") || target.equals("B#") || target.equals("E#")) {
            System.out.println(target + " is enharmonic with " + notes[0][tonicCol] + ".");
            System.out.println(notes[0][tonicCol] + " chromatic scale:");
            i = 0;
        }
        else {
            System.out.println(notes[tonicRow][tonicCol] + " chromatic scale:");
        }
        
        //if the tonic is a natural note, use sharps ascending and flats descending
        if(target.length() == 1) {
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
    
    //print major scale
    public void printMajor() {
        String target = startNote.substring(0,1).toUpperCase() + startNote.substring(1);
        String lastChar = target.substring(target.length() - 1);
        String separator = " ";
        int myRow = tonicRow;
        int myCol = tonicCol;
        int j;
        
        //intro
        System.out.println(target + " major scale:");
        
        //if the tonic is C, G, D, A, or E, use row 0
        if(target.equals("[CGDAE]")) {
            myRow = 0;
        }
        
        //if the tonic is F, Bb, Eb, or Ab, use row 1
        if(target.equals("F") || target.equals("[BEA]b{2}")) {
            myRow = 1;
        }
        
        //if the tonic is C#, or F#, use row 2
        if(target.equals("C#") || target.equals("F#")) {
            myRow = 2;
        }
        
        //if the tonic is D#, E#, or G#, add sharps to the natural major scale
        //allows for double sharps
        if(target.equals("D#") || target.equals("E#") || target.equals("G#")) {
            myRow = 2;
            myCol = myCol - 1;
            separator = "# ";
        }
        
        //if the tonic is Fb, add flats to the natural major scale
        //allows for double flats
        if(target.equals("Fb")) {
            myRow = 3;
            myCol = myCol + 1;
            separator = "b ";
        }
        
        //if the tonic is Cb, use row 3
        if(target.equals("Cb")) {
            myRow = 3;
        }
        
        //print tonic and next two whole steps up
        for(j = myCol; j <= myCol + 4; j = j + 2) {
            printNote(myRow,j);
            System.out.print(separator);
        }
            
        //print two and a half steps up from tonic
        j = myCol + 5;
        printNote(myRow,j);
        System.out.print(separator);
            
        //print seven steps up from tonic and next two whole steps
        for(j = myCol + 7; j <= myCol + 11; j = j + 2) {
            printNote(myRow,j);
            System.out.print(separator);
        }
            
        //print last note
        j = tonicCol;
        printNote(myRow,j);
        System.out.println();
    }
}