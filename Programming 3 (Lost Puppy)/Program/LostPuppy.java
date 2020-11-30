/*
 * LostPuppy.java
 *
 * TCSS 143 - Fall  -  2019
 * Assignment
 */

import java.util.Random; //used for random puppy location

/**
 * Class used alongside PuppyPlay.java driver
 * program to play a guessing game to find
 * a puppy in a building.
 * 
 * @author Kenneth Ahrens kenahren@uw.edu
 * @version 10/13/2019
 */
 
public class LostPuppy{
   
   //array for all the rooms/floors
   private char[][] myHidingPlaces;
   //floor puppy is lost on
   private int myFloorLocation;
   //room puppy is lost on
   private int myRoomLocation;
   //player who won (1 or 2)
   private char myWinner;
   //boolean to see if puppy was found
   private boolean myFound;
   
   /**
    * Constructor for LostPuppy class.
    * Initializes all the fields like rooms and floors
    *
    * @param theFloors number of floors from user
    * @param theRooms number of rooms from user
    */
   
   public LostPuppy(int theFloors, int theRooms){
      Random rand = new Random();
      this.myHidingPlaces = new char [theFloors][theRooms];
      for(int i = 0; i < theFloors; i++){
         for(int j = 0; j < theRooms; j++){
            this.myHidingPlaces[i][j] = ' ';
         }
      }
      this.myFloorLocation = rand.nextInt(theFloors);
      this.myRoomLocation = rand.nextInt(theRooms);
      this.myHidingPlaces[myFloorLocation][myRoomLocation] = 'P';
      this.myWinner = ' ';
      this.myFound = false;
      
   }
   
   /**
    * Checks to see if array has already been given a
    * player number which means it has been checked.
    *
    * @param theFloor floor number to check
    * @param theRoom room number to check
    * @return boolean true/false if room/floor was already searched
    */
   
   public boolean roomSearchedAlready(int theFloor, int theRoom){
      boolean searchResult = true;
      if(myHidingPlaces[theFloor][theRoom] != 1 &&
         myHidingPlaces[theFloor][theRoom] != 2){
         searchResult = false;
      }
      return searchResult;
   }
   
   /**
    * Check array location for the puppy char "P".
    *
    * @param theFloor floor number to check
    * @param theRoom room number to check
    * @return boolean true/false if the room/floor has puppy
    */
   
   public boolean puppyLocation(int theFloor, int theRoom){
      boolean puppyResult = false;
      if(myHidingPlaces[theFloor][theRoom] == 'P'){
         puppyResult = true;
      }
      return puppyResult;
   }
   
   /**
    * Checks to see if user input
    * has indices inside the array.
    *
    * @param theFloor floor number to check
    * @param theRoom room number to check
    * @return boolean true/false if the indices are possible
    */
   
   public boolean indicesOK(int theFloor, int theRoom){
      boolean inRange = false;
      if(theFloor < myHidingPlaces.length
         && theRoom < myHidingPlaces[0].length){
         inRange = true;  
      }
      return inRange;
      
   }
   
   /**
    * getter method to get the number of floors (rows)
    * 
    * @return int ammount of floors
    */
   
   public int numberOfFloors(){
      return myHidingPlaces.length;
   }
   
   /**
    * getter method to get the number of rooms (columns)
    * 
    * @return int ammount of rooms
    */
    
   public int numberOfRooms(){
      return myHidingPlaces[0].length;
   }
   
   /**
    * Searches the floor/room to see if the puppy
    * is there. Marks the array location with the player number.
    * If puppy is there the myFound/myWinner field is set to true,
    * and the array is marked with Player# and "P"
    *
    * @param theGuessedFloor floor number to check
    * @param theGuessedFloor room number to check
    * @param thePlayer number of player to insert into array
    * @return boolean true/false if the puppy was in the location
    */
    
   public boolean searchRoom(int theGuessedFloor, int theGuessedRoom,
                             char thePlayer){
      boolean puppyFound = false;
      int location = myHidingPlaces[theGuessedFloor][theGuessedRoom];
      
      if(location == 'P'){
         this.myWinner = thePlayer;
         this.myFound = true;
         puppyFound = true;
      }
      else if(location == ' '){
         myHidingPlaces[theGuessedFloor][theGuessedRoom] = thePlayer;
      }
      return puppyFound;
   }
   
   /**
    * Formatting method to output building
    * drawing to console of what called it.
    *
    * @return string of building drawing
    */
   
   @Override
   public String toString(){
   
      int totFloors = myHidingPlaces.length; //rows
      int totRooms = myHidingPlaces[0].length; //cols
      String drawnBuilding = ""; //gets concatenated through method
                                
                
      //makes the roof. There are 4 "_" per column (room)
      for(int i = 0; i <= totRooms * 4; i++) {
         drawnBuilding += "_";
      }
      
      //first loop goes through each floor
      //floors done in reverse major order
      for(int i = totFloors - 1; i >= 0; i--) {
         drawnBuilding += "\n|"; //start of new floor (row)
         
         //cycles every element in array with 2 loops
         //prints the rightbound segment of each room 
         for(int j = 0; j < totRooms; j++) {  
            //if puppy is found at location display
            //with the player number and "P" leftbound
            if(puppyLocation(i,j) && myFound) {
               drawnBuilding += "" + myWinner + "P" + " |";
               
            //if puppy hasn't been found yet hide in drawing
            } else if (puppyLocation(i,j) && !myFound){
               drawnBuilding += "   |"; 
               
            } else { //no puppy in location just put player number
               drawnBuilding += " " + myHidingPlaces[i][j] + " |";
               
            }                                     
        }
        //finish room for that floor loop by drawing the bottom of it
        //fencepost problem so start with first line
        drawnBuilding += "\n|";
            for(int botRow = 0; botRow <= (totRooms - 1); botRow++) {
            drawnBuilding += "___|"; //right side of room
        }
    }
    return drawnBuilding;                       
                         
   }
}
