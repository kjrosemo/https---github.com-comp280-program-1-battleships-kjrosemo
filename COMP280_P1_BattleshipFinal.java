/*
* Author: Katrina Rosemond   
* Due Date: 8/27/2017
* Assignment: Program #1 Battleships
* File: COMP280_P1_ Battleship.java
* Description: Program is a 1-Player vs Computer Battleship game.
*/

import java.util.*;
import java.lang.*;

public class COMP280_P1_BattleshipFinal {

   public static void main (String [] args) {

   char[][] userBoard = new char [10][10];
   char[][] oppBoard = new char [10][10];
   char[][] userTrackBoard = new char [10][10];
   char[][] shipCache = new char [5][6];
   char [] letArray = {'A','B','C','D','E','F','G','H','I','J'};
   int []  numArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
   String [] orientationArray = { "horizontal", "vertical"};
   
   int letInd, numInd, shipNum, userNum, oppNumInd, shipDir;
   char userLet, oppLetInd;
   String dir, userDir;
   
   boolean out = false;
   boolean occupied = false;
   
   // Loop will create an array for the ships.
      int count = 6;
      for ( int i = 0; i < shipCache.length; i++)  {
            for ( int j = 0; j < count; j++)  {
                  shipCache[i][j] = '#';
                  
            }
            count--;
      }
      count = 6;
       for ( int i = 0; i < shipCache.length; i++)  {
            for ( int j = 0; j < count; j++)  {
            
             if ( shipCache[i][j] == '\u0000' ) {
               shipCache[i][j] = '~';
             }
       }
       }
      
      System.out.println ("Welcome to Battleship!!! \n Here are your boards: " );
      setBlankBoards(userBoard);
      setBlankBoards(oppBoard);
      setBlankBoards(userTrackBoard);
         
      System.out.println("Your Ocean:");
      printBoard(userBoard, numArray, letArray); 
      System.out.println();   
      System.out.println("Your Opponent's: ");
      printBoard(userTrackBoard, numArray, letArray);
      System.out.println();
      
      System.out.println("Now let's select your ship locations.");
      
      count = 0;
ShipPlacement: 

   do {
      
      shipNum = shipSelection();
      System.out.println("Enter a location you would like to place your ship in this foramt(A3): ");
      Scanner keyboard = new Scanner(System.in);
      String location = keyboard.nextLine();
      String[] localArray = location.split("(?<=\\D)(?=\\d)");
      userLet = localArray[0].charAt(0);
      userNum = Integer.parseInt(localArray[1]);
      letInd = setLetInd(userLet, letArray);
      numInd = setNumInd(userNum, numArray);
      System.out.println ("What direction would you like to place the ship?");
      String direction = keyboard.nextLine();
      shipDir = setDirInd (direction,orientationArray);
      out = isOutOfBounds (letArray, numArray, userLet, userNum);
      occupied = isItOccupied (userBoard, letInd, numInd, occupied);
         
      if ( out || occupied ) {
      
         break ShipPlacement;         
      }
      
      setShipPlacement (userBoard, shipCache, letInd, numInd, shipNum, shipDir);
      System.out.println("Your Ocean:");
      printBoard(userBoard, numArray, letArray); 
      System.out.println();   

      
      count++;
   } while (count < 7);
      

      



}

                                    //METHODS
                        
      // Method will set all game boards to be a blank board or empty ocean.        
      public static void setBlankBoards (char [][] Board)  {
         for (int r = 0; r < Board.length; r++) {
            for (int c = 0; c < Board[0].length; c++) {
               Board[r][c] = '~';   
            }
         }
      }
      
      // Method will print game boards.
      public static void printBoard (char [][] board, int [] numArray, char[] letArray)  {
         for (int i = 0; i < numArray.length; i++) {
            System.out.print(" ");
            System.out.print( numArray[i]);  
         }
         System.out.println();
         for ( int j = 0; j < letArray.length; j++)   {
            System.out.print ( letArray[j] + " " );   
              for (int c = 0; c < board[0].length; c++) {
                System.out.print( board[j][c] + " ") ;
               }
                 System.out.println();  
               }
         }
      
      // Method will determine what row the user selected.
      public static int setLetInd (char let, char[] letArray)  {
         int letInd = 0;
         for (int i = 0; i < letArray.length; i++) {
            if (let == letArray[i] )  {
               letInd = i;
            }
         }
         return letInd;
      }
     
      // Method will determine what column the user selected.
      public static int setNumInd (int num, int [] numArray)  {
         int numInd = 0;
         for (int i = 0; i < numArray.length; i++) {
            if ( num == numArray[i] )   {
               numInd = i;
            }
         }
         return numInd;
      }
      
      // Method will determine what row the user selected.
      public static int setDirInd (String dir, String[] orientationArray)  {
         int dirInd = 0;
         for (int i = 0; i < orientationArray.length; i++) {
            if (dir == orientationArray[i] )  {
               dirInd = i;
            }
         }
         return dirInd;
      }

      
      // Method will prompt the user to enter which ship they would like to place
       public static int shipSelection () {
        
         System.out.println("Please press the number that corresponds to the ship you wish to place: \n 1.Aircraft Carrier \n 2.Battleship \n 3.Cruiser \n 4.Destroyer \n 5.Submarine");
         
         Scanner keyboard = new Scanner(System.in);
         
         int shipNum = keyboard.nextInt();
            
         return shipNum;
       }       
       
       // Method will prompt the user to place their ships on their board.
       public static void setShipPlacement (char [][] Board, char [][] shipCache, int letIndNum, int numIndNum, int shipNum, int dir)  {
        
            if (dir == 1)   {    //horizontal
            
                     for (int j = 0; j < shipCache[shipNum-1].length; j++) {
                        Board[letIndNum][numIndNum] = shipCache[shipNum-1][j];
                        numIndNum++;
                     }
                  }
            
            else if (dir == 2) {    //vertical
            
                     for (int j = 0; j < shipCache[shipNum-1].length; j++) {
                        Board[letIndNum][numIndNum] = shipCache[shipNum-1][j];
                        letIndNum++;
                     }
                  }   
               
         }
 
         // Method will check to see if the user specified location is out of bounds.
         public static boolean isOutOfBounds ( char [] letArray, int [] numArray, char letter, int number)  {
            boolean outBound = true;
            
            for (int i = 0; i < letArray.length; i++)  {
               
               if ( letter == letArray[i])  {
                  outBound = false;
               }
               else if (number == numArray[i] ) { 
                  outBound = false;
               } 
            }   
            if (outBound == true) {  
               System.out.println(" You have to be on the board to be in the game, get it together! \n Please select a location on the map.");
            }
          return outBound;
         }
         
         // Method will check to see if there is a ship already placed at the user specified location.
          public static boolean isItOccupied ( char [][] board, int letInd, int numInd, boolean spaceOccupied)  {
               
               if ( board[letInd][numInd]== '#' )  {         
                  spaceOccupied = true;
                  System.out.println(" A ship is already at that location. \n Please select a different location on the map.");
               }
               return spaceOccupied;
          }

      
}