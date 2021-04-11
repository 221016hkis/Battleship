import java.util.*;
import java.util.Random;


public class BattleShip {

    static Maze myBattle = new Maze();
    static Scanner input = new Scanner(System.in);

    
    public static void main(String[] args){
        
        myBattle.printMap();
        myBattle.generateShips();
    }


}

 
