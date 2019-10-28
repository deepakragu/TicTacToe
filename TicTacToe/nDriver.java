import java.util.*;
/**
 * Write a description of class Driver here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class nDriver
{
    public static void main(String [] args)
    {
        List <Player> players = new ArrayList <Player> ();
        Scanner console = new Scanner(System.in);
        //add players to game
        players.add(new Player1());
        players.add(new Player2());
        //add players.add(new ______________) for your code
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("Player vs Player (1) or Player vs CPU (2)?");
        System.out.print("Choice ---> ");
        String choice = console.next() + " ";
        if (choice.charAt(0) == '1') {
            nGame newGame = new nGame(players);
            System.out.println("How many Rows/Cols?");
            System.out.print("Number ---> ");
            int num = console.nextInt();
            boolean i = newGame.play(new Player1(), new Player2(), num);
        }
        if (choice.charAt(0) == '2') {
            nGame newGame = new nGame(players);
            System.out.println("How many Rows/Cols?");
            System.out.print("Number ---> ");
            int num = console.nextInt();
            boolean i = newGame.playCPU(new Player1(), num);
        }
        //if (newGame.checkX()) {System.out.println("Game Over: Player 1 wins!");}
    }
}
