import java.util.*;

public class Gameboard
{
    //Creating instance variables
    private char[][] gameboard; 
    private char[][] publicGB; 
    private Ship ship1;
    private Ship ship2;
    private Ship ship3;
    private Ship ship4;
    private Ship ship5;
    private Ship[] ships;
    private ArrayList<Ship> sunkenShips = new ArrayList<Ship>();
    private int hits = 0;
    private int missed = 0;
        
    

    
    public Gameboard()
    {
        //initialise instance variables
        gameboard = new char[9][9];
        publicGB = new char[9][9];

        //Fills the board up
        for (int i = 0; i<9; i++) {
            for (int j = 0; j<9; j++) {
                gameboard[i][j] = '-';
                publicGB[i][j] = '-';
            }
        }
        
        //generates the ships but doesn't place them yet
        ship1 = new Ship(5); // the number after Ship() is the size of the ship
        ship2 = new Ship(4);
        ship3 = new Ship(3);
        ship4 = new Ship(2);
        ship5 = new Ship(2);
        ships = new Ship[]{ship1, ship2, ship3, ship4, ship5};
        this.placeShips();
    }

    public int shots(){ //Records the number of shots
        return hits + missed;
    }

    public double percent() { //calculates the percentage to later on print the stats
        int shots = hits+missed;
        double percent;
        if (shots>0) {
            percent = (hits*100.0/shots);
        }
        else {
            percent = 0.0;
        }
        return percent;
    }


    public boolean isEmpty(int x, int y) { 
        return (gameboard[x][y] == '.');
    }
    
    public boolean publicEmpty(int x, int y) {
        return (publicGB[x][y] == '.');
    }
    
    public void editGB(int x, int y, char symbol){
        gameboard[x][y] = symbol;
    }
    
    public boolean haveIWon(){ //Checks if all of the ships have been sunk to see if the user wins
        if (sunkenShips.size()!=5){
            return false;
        }
        return true;
    }

    private void placeShips() { //calls the placeship method in Ship
        for (int i = 0; i<ships.length; i++) {
            ships[i].placeShip(this);
        }
    }
    
 

    public void fire(int x, int y) {
        if (x>8 || x<0 || y>8 || y<0) { //makes sure that the shot fire is within the board
            System.out.println("Illegal coordinates."); //if it's outside it will print out an error
        }
        else if (!this.publicEmpty(x, y)) { // notifies the user if they are trying to fire on a previously fired upon coordinate
            System.out.println("Coordinates previously fired upon.");
        }
        else if (this.isEmpty(x, y)){ //Prints out an O for when they fire upon an empty coordinate
            publicGB[x][y] = 'O';
            System.out.println("Miss!");
            missed++; // keeps track of the number of missed shots
        }
        else {
            publicGB[x][y] = 'X'; // other option is that they hit a boat so the game will print out an X at the coordinate
            hits++; // keeps track of the number of shots on target
            System.out.println("Hit!"); 
            for (int i = 0; i<ships.length; i++) { 
                Ship shipx = ships[i];
                if (shipx.turnSunk(this.publicGB, x, y)) { //once the boat sunk it will print out a message to say so
                    System.out.println("Sunk!");
                    this.sunkenShips.add(shipx);
                    int[] row = shipx.getRow();
                    int[] column = shipx.getColumn();
                    char sym = shipx.getSymbol(); // fetches the row and column of the boat to know which boat it was and then prints out the coresponding symbol for it
                    for (int j = 0; j<shipx.getLength(); j++) {
                        int x1 = row[j];
                        int y1 = column[j];
                        publicGB[x1][y1] = sym;
                        
                    }
                }
            }
        }
        
    }

    public int shipsSunk() {
        return this.sunkenShips.size();
    }

    
    public void printBoard() { // prints the board 
        System.out.println("  0 1 2 3 4 5 6 7 8");
        for (int i = 0; i<9; i++) {
            String out = Integer.toString(i);
            for (int j = 0; j<9; j++) {
                out+= " ";
                out+= publicGB[i][j];
            }
            System.out.println(out);
        }
    }
    
    public void printCheatBoard() { 
        System.out.println("  0 1 2 3 4 5 6 7 8");
        for (int i = 0; i<9; i++) {
            String out = Integer.toString(i);
            for (int j = 0; j<9; j++) {
                out+= " ";
                out+= gameboard[i][j];
            }
            System.out.println(out);
        }
    }

}
