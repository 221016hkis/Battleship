import java.util.*;


public class Game {
    
    
    static Gameboard gameboard = new Gameboard(); //creates a new board for the game 

    
    public static void main (String[] args){ //main method

        

        System.out.println("test");

        

        
      
        gameboard.printBoard(); //Prints the board
        Scanner scan = new Scanner(System.in);

        

        while (gameboard.haveIWon() == false) {
            String command = scan.next();
            /* made use of the switch function which is a little similar 
            to if statements but uses less syntax. Here it checks what the user inputs and then 
            perform the action according to that */
        
            switch (command){ 
                case "view":
                String subcommand = scan.next();
                switch (subcommand){ 
                    case "board":
                    gameboard.printBoard();
                    break; //breaks (exits) the while loop 

                    case "ships":
                    gameboard.printCheatBoard();
                    break;

                    default:
                    System.out.println("Can only view board or ships.");
                    break;
                }
                break;

                case "fire": 
                try { //tests this code first 
                    int row = scan.nextInt();
                    int col = scan.nextInt();
                    gameboard.fire(row, col);
                    gameboard.printBoard();
                }
                catch (InputMismatchException e){ //prints the error without terminating the program
                    scan.nextLine();
                    System.out.println("Illegal coordinates.");
                    gameboard.printBoard();
                }
                break;

                case "stats": //prints out stats
                stats();
                break;

                case "help": //prints out possible commands
                help();
                break;

                case "quit": // ends the game
                System.exit(0);
                break;

                default: //in case the user inputs a command that doesn't exist
                System.out.println("Illegal command");
                break;
            }

        }
        
        scan.close();
        if(gameboard.haveIWon() == true){
            System.out.println("Woohooo you won! Here are your stats:");
        }
        stats();
        System.exit(0);
    }

    private static void stats(){ //Calculates the user's stats according to how many shots they have fire and ships they have sunk
        
        int shots = gameboard.shots(); 
        int sunk = gameboard.shipsSunk(); 
        double percent = gameboard.percent();
        System.out.println("Number of missiles fired: " + shots); 
        System.out.println("Hit ratio: "+ percent +"%");
        System.out.println("Number of ships sunk: " + sunk);
    }

    private static void help(){ //Prints out the possible commandsfor the user if they need help
        System.out.println("Possible commands:");
        System.out.println("view board - displays the user's board");
        System.out.println("view ships - displays the placement of the ships");
        System.out.println("fire r c - fires a missile at the cell at [row,column]");
        System.out.println("stats - prints out the game statistics");
        System.out.println("quit - exits the game");
    }

}
