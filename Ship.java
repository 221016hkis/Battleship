import java.util.*;

public class Ship {

    // creating instances variables
    private int[] row;
    private int[] column;
    private boolean sunken;
    private int length;
    private char symbol;


    public Ship(int length) {

        // initialise instance variables

        this.length = length;
        row = new int[length];
        column = new int[length];

        switch (length){ //gives the appropriate symbols for the ships
            case 2:
            symbol = 'D';
            break;

            case 3:
            symbol = 'C';
            break;

            case 4:
            symbol = 'B';
            break;

            case 5:
            symbol = 'A';
            break;

            default:
            throw new IllegalArgumentException("Invalid length given.");
        } 
    }

    public boolean isSunk(char[][] gb){ // checks if the ships are sunk
        for (int i = 0; i<length; i++){
            int x = row[i];
            int y = column[i];
            if (gb[x][y]== '-'){
                return false;
            }
        }
        return true;
    }

    public boolean turnSunk(char[][] gb, int x1, int y1){
        if (!isSunk(gb)){
            return false;
        }
        else {
            for (int i = 0; i<length; i++){
                int x = row[i];
                int y = column[i];
                if (x==x1 && y==y1) {
                    return true;
                }
            }
        }
        return false;

    }


    public char getSymbol(){ //gets the symbol
        return this.symbol;
    }

    public int getLength(){ //gets the length
        return this.length;
    }

    public int[] getRow(){ //gets the row
        return this.row;
    }

    public int[] getColumn(){ //gets the column
        return this.column;
    }

    public void placeShip(Gameboard gb) { //places the ships
        //starting coordinates
        Random r = new Random();
        int x = r.nextInt(9);
        int y = r.nextInt(9);
        boolean direction = getHor();

        while (!gb.isEmpty(x, y)) {
            x = r.nextInt(9); //places the ships at a random empty pot on the map
            y = r.nextInt(9);
        }

        while (true) {
            if (!direction){
                if (y>(9-length)){
                    //makes it go up since it can't go down
                    for (int i = 0; i<length; i++) {
                        if (!gb.isEmpty(x, y-i)) {
                            y -= (i+1);
                            break; //breaks the for loop
                        }
                        else if (i==length-1){
                            for (int j = 0; j<length; j++) {
                                gb.editGB(x, y-j, symbol);
                                row[j] = x;
                                column[j] = y-j;
                            }
                            return;
                        }
                    }
                }
                else{ //does the same thing but makes it go down
                    for (int i = 0; i<length; i++) {
                        if (!gb.isEmpty(x, y+i)) {
                            y += (i+1);
                            break;

                        }
                        else if (i==length-1){
                            for (int j = 0; j<length; j++) {
                                gb.editGB(x, y+j, symbol);
                                row[j] = x;
                                column[j] = y+j;
                            }
                            return;
                        }
                    }
                }
            }
            else {
                if (x>(9-length)){
                    //makes it go left since it can't go right
                    for (int i = 0; i<length; i++) {
                        if (!gb.isEmpty(x-i, y)) {
                            x -= (i+1);
                            break;
                        }
                        else if (i==length-1){
                            for (int j = 0; j<length; j++) {
                                gb.editGB(x-j, y, symbol);
                                row[j] = x-j;
                                column[j] = y;
                            }
                            return;
                        }
                    }
                }
                else{ //does the same thing but makes it go right
                    for (int i = 0; i<length; i++) {
                        if (!gb.isEmpty(x+i, y)) {
                            x += (i+1);
                            break;
                        }
                        else if (i==length-1){
                            for (int j = 0; j<length; j++) {
                                gb.editGB(x+j, y, symbol);
                                row[j] = x+j;
                                column[j] = y;
                            }
                            return;
                        }
                    }

                }

            }
            x = r.nextInt(9);
            y = r.nextInt(9);
        }

    }

    private boolean getHor() { //checks if the ship is vertical or horizontal
        Random r = new Random();  //if false, the ship is pointing horizontally
        return r.nextBoolean();   //if true, the ship is pointing vertically
    }




}
