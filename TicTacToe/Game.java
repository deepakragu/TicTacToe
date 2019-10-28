import java.util.*;
/**
 * Write a description of class Game here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Game
{
    private Scanner console = new Scanner(System.in);
    String [][] board = {{"___", "___", "___"},
            {"___", "___", "___"},
            {"___", "___","___"}};
    
    String [][] empty = {{"_1_", "_2_", "_3_"},
            {"_4_", "_5_", "_6_"},
            {"_7_", "_8_","_9_"}};
    
    private List <Player> players;

    public Game(List <Player> players)
    {
        // initialise instance variables
        this.players = players;
    }

    public boolean play(Player player1, Player player2) {
        int steps = 0;
        do {
            if ((steps%2) == 0) {
                playerTurn(player1);
                steps++;
                if (checkX()) {
                    makeBoard(board);
                    System.out.println("Game Over: Player 1 wins!"); 
                    System.out.println("Play again (Player vs Player (1) or Player vs CPU (2)) or Quit (Q)?");
                    System.out.print("Choice ---> ");
                    String choice = console.next() + " ";
                    if (choice.charAt(0) == '1'){resetArray(); return play(player1, player2);}
                    else if (choice.charAt(0) == '2'){resetArray(); return playCPU(player1);}
                    else return true;}
                if (checkO()) {
                    makeBoard(board);
                    System.out.println("Game Over: Player 2 wins!"); 
                    System.out.println("Play again (Player vs Player (1) or Player vs CPU (2)) or Quit (Q)?");
                    System.out.print("Choice ---> ");
                    String choice = console.next() + " ";
                    if (choice.charAt(0) == '1'){resetArray(); return play(player1, player2);}
                    else if (choice.charAt(0) == '2'){resetArray(); return playCPU(player1);}
                    else return true;}
                if (checkFull()) {
                    makeBoard(board);
                    System.out.println("Game Over: Tie Game!");
                    System.out.println("Play again (Player vs Player (1) or Player vs CPU (2)) or Quit (Q)?");
                    System.out.print("Choice ---> ");
                    String choice = console.next() + " ";
                    if (choice.charAt(0) == '1'){resetArray(); return play(player1, player2);}
                    else if (choice.charAt(0) == '2'){resetArray(); return playCPU(player1);}
                    else return true;}
            }
            else if ((steps%2) == 1) {
                playerTurn(player2);
                steps++;
                if (checkX()) {
                    makeBoard(board);
                    System.out.println("Game Over: Player 1 wins!"); 
                    System.out.println("Play again (Player vs Player (1) or Player vs CPU (2)) or Quit (Q)?");
                    System.out.print("Choice ---> ");
                    String choice = console.next() + " ";
                    if (choice.charAt(0) == '1'){resetArray(); return play(player1, player2);}
                    else if (choice.charAt(0) == '2'){resetArray(); return playCPU(player1);}
                    else return true;}
                if (checkO()) {
                    makeBoard(board);
                    System.out.println("Game Over: Player 2 wins!"); 
                    System.out.println("Play again (Player vs Player (1) or Player vs CPU (2)) or Quit (Q)?");
                    System.out.print("Choice ---> ");
                    String choice = console.next() + " ";
                    if (choice.charAt(0) == '1'){resetArray(); return play(player1, player2);}
                    else if (choice.charAt(0) == '2'){resetArray(); return playCPU(player1);}
                    else return true;}
                if (checkFull()) {
                    makeBoard(board);
                    System.out.println("Game Over: Tie Game!");
                    System.out.println("Play again (Player vs Player (1) or Player vs CPU (2)) or Quit (Q)?");
                    System.out.print("Choice ---> ");
                    String choice = console.next() + " ";
                    if (choice.charAt(0) == '1'){resetArray(); return play(player1, player2);}
                    else if (choice.charAt(0) == '2'){resetArray(); return playCPU(player1);}
                    else return true;}            
            }
        } while (steps < 9);
        return false;
    }

    public boolean playCPU(Player player1) {
        //Player CPU = new CPU();
        int steps = 0;
        do {
            if ((steps%2) == 0) {
                playerTurn(player1);
                steps++;
                if (checkX()) {
                    makeBoard(board);
                    System.out.println("Game Over: Player 1 wins!"); 
                    System.out.println("Play again (Player vs Player (1) or Player vs CPU (2)) or Quit (Q)?");
                    System.out.print("Choice ---> ");
                    String choice = console.next() + " ";
                    if (choice.charAt(0) == '1'){resetArray(); return play(player1, new Player2());}
                    else if (choice.charAt(0) == '2'){resetArray(); return playCPU(player1);}
                    else return true;}
                if (checkO()) {
                    makeBoard(board);
                    System.out.println("Game Over: Player 2 wins!"); 
                    System.out.println("Play again (Player vs Player (1) or Player vs CPU (2)) or Quit (Q)?");
                    System.out.print("Choice ---> ");
                    String choice = console.next() + " ";
                    if (choice.charAt(0) == '1'){resetArray(); return play(player1, new Player2());}
                    else if (choice.charAt(0) == '2'){resetArray(); return playCPU(player1);}
                    else return true;}
                if (checkFull()) {
                    makeBoard(board);
                    System.out.println("Game Over: Tie Game!");
                    System.out.println("Play again (Player vs Player (1) or Player vs CPU (2)) or Quit (Q)?");
                    System.out.print("Choice ---> ");
                    String choice = console.next() + " ";
                    if (choice.charAt(0) == '1'){resetArray(); return play(player1, new Player2());}
                    else if (choice.charAt(0) == '2'){resetArray(); return playCPU(player1);}
                    else return true;}            
            }
            else if ((steps%2) == 1) {
                int counter = 0;
                ArrayList <Integer> rows = new ArrayList <Integer>();
                ArrayList <Integer> cols = new ArrayList <Integer>();                
                for (int row = 0; row < empty.length; row++) {
                    for (int col = 0; col < empty[0].length; col++) {
                        if (board[row][col] == ("___")) {
                            rows.add(row);
                            cols.add(col);
                        }
                    }
                }
                double k = Math.random()*rows.size();
                int K = (int) k;
                board[rows.get(K)][cols.get(K)] = " O ";
                steps++;
                if (checkX()) {
                    makeBoard(board);
                    System.out.println("Game Over: Player 1 wins!"); 
                    System.out.println("Play again (Player vs Player (1) or Player vs CPU (2)) or Quit (Q)?");
                    System.out.print("Choice ---> ");
                    String choice = console.next() + " ";
                    if (choice.charAt(0) == '1'){resetArray(); return play(player1, new Player2());}
                    else if (choice.charAt(0) == '2'){resetArray(); return playCPU(player1);}
                    else return true;}
                if (checkO()) {
                    makeBoard(board);
                    System.out.println("Game Over: Player 2 wins!"); 
                    System.out.println("Play again (Player vs Player (1) or Player vs CPU (2)) or Quit (Q)?");
                    System.out.print("Choice ---> ");
                    String choice = console.next() + " ";
                    if (choice.charAt(0) == '1'){resetArray(); return play(player1, new Player2());}
                    else if (choice.charAt(0) == '2'){resetArray(); return playCPU(player1);}
                    else return true;}
                if (checkFull()) {
                    makeBoard(board);
                    System.out.println("Game Over: Tie Game!");
                    System.out.println("Play again (Player vs Player (1) or Player vs CPU (2)) or Quit (Q)?");
                    System.out.print("Choice ---> ");
                    String choice = console.next() + " ";
                    if (choice.charAt(0) == '1'){resetArray(); return play(player1, new Player2());}
                    else if (choice.charAt(0) == '2'){resetArray(); return playCPU(player1);}
                    else return true;}            
            }
        } while (steps < 9);
        return false;
    }

    public void playerTurn(Player player1) {
        String n = player1.getToken();
        choiceMenu(n);
        //player1.move(n, board);
    }

    public boolean checkX() {
        if ((board[0][0] == " X ") && (board[0][1] == " X ") && (board[0][2] == " X ")) {return true;}
        if ((board[1][0] == " X ") && (board[1][1] == " X ") && (board[1][2] == " X ")) {return true;}
        if ((board[2][0] == " X ") && (board[2][1] == " X ") && (board[2][2] == " X ")) {return true;}
        if ((board[0][0] == " X ") && (board[1][0] == " X ") && (board[2][0] == " X ")) {return true;}
        if ((board[0][1] == " X ") && (board[1][1] == " X ") && (board[2][1] == " X ")) {return true;}
        if ((board[0][2] == " X ") && (board[1][2] == " X ") && (board[2][2] == " X ")) {return true;}
        if ((board[0][0] == " X ") && (board[1][1] == " X ") && (board[2][2] == " X ")) {return true;}
        if ((board[2][0] == " X ") && (board[1][1] == " X ") && (board[0][2] == " X ")) {return true;}
        return false;
    }

    public boolean checkO() {
        if ((board[0][0] == " O ") && (board[0][1] == " O ") && (board[0][2] == " O ")) {return true;}
        if ((board[1][0] == " O ") && (board[1][1] == " O ") && (board[1][2] == " O ")) {return true;}
        if ((board[2][0] == " O ") && (board[2][1] == " O ") && (board[2][2] == " O ")) {return true;}
        if ((board[0][0] == " O ") && (board[1][0] == " O ") && (board[2][0] == " O ")) {return true;}
        if ((board[0][1] == " O ") && (board[1][1] == " O ") && (board[2][1] == " O ")) {return true;}
        if ((board[0][2] == " O ") && (board[1][2] == " O ") && (board[2][2] == " O ")) {return true;}
        if ((board[0][0] == " O ") && (board[1][1] == " O ") && (board[2][2] == " O ")) {return true;}
        if ((board[2][0] == " O ") && (board[1][1] == " O ") && (board[0][2] == " O ")) {return true;}
        return false;
    }

    public boolean checkFull() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == "___") return false;
            }
        }
        return true;
    }
    
    public void makeBoard(String[][]arr) {
        for (int row = 0; row < arr.length; row++) {
            System.out.println();
            for (int col = 0; col < arr[0].length; col++) {
                System.out.print(arr[row][col]);
                if (((row==0) && (col==0)) || ((row==0) && (col==1)) || 
                ((row==1) && (col==0)) || ((row==1) && (col==1)) || ((row==2) && (col==0)) || ((row==2) && (col==1))) {
                    System.out.print("|");
                }
            }
        }
        System.out.println();
    }
    
    public void printEmptyArray () {
        for (int row = 0; row < empty.length; row++) {
            System.out.println();
            for (int col = 0; col < empty[0].length; col++) {
                System.out.print(empty[row][col]);
                if (((row==0) && (col==0)) || ((row==0) && (col==1)) || 
                ((row==1) && (col==0)) || ((row==1) && (col==1)) || ((row==2) && (col==0)) || ((row==2) && (col==1))) {
                    System.out.print("|");
                }
            }
        }
    }

    public void resetArray() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col] = "___";
            }
        }
    }

    public void choiceMenu(String n){
        String choice;
        String print;

        System.out.println();
        makeBoard(board);
        printEmptyArray();
        System.out.println();
        System.out.println("Which square would you like to select");
        System.out.println();
        System.out.println("(1)");
        System.out.println("(2)");
        System.out.println("(3)");
        System.out.println("(4)");
        System.out.println("(5)");
        System.out.println("(6)");
        System.out.println("(7)");
        System.out.println("(8)");
        System.out.println("(9)");
        System.out.println();
        System.out.print("Choice ---> ");
        choice = console.next() + " ";
        if ('1' <= choice.charAt(0) && choice.charAt(0) <= '9'){
            System.out.println();

            switch (choice.charAt(0)){
                case '1':
                if (board[0][0] != "___") {
                    System.out.println("Error: That square is taken. Choose another square");
                    choiceMenu(n);
                    break;
                }
                if (board[0][0] == "___") {
                    board[0][0] = n;
                    break;
                }
                case '2':
                if (board[0][1] != "___") {
                    System.out.println("Error: That square is taken. Choose another square");
                    choiceMenu(n);
                    break;
                }
                if (board[0][1] == "___") {
                    board[0][1] = n;
                    break;
                }
                case '3':
                if (board[0][2] != "___") {
                    System.out.println("Error: That square is taken. Choose another square");
                    choiceMenu(n);
                    break;
                }
                if (board[0][2] == "___") {
                    board[0][2] = n;
                    break;
                }
                case '4':
                if (board[1][0] != "___") {
                    System.out.println("Error: That square is taken. Choose another square");
                    choiceMenu(n);
                    break;
                }
                if (board[1][0] == "___") {
                    board[1][0] = n;
                    break;
                }
                case '5':
                if (board[1][1] != "___") {
                    System.out.println("Error: That square is taken. Choose another square");
                    choiceMenu(n);
                    break;
                }
                if (board[1][1] == "___") {
                    board[1][1] = n;
                    break;
                }
                case '6':
                if (board[1][2] != "___") {
                    System.out.println("Error: That square is taken. Choose another square");
                    choiceMenu(n);
                    break;
                }
                if (board[1][2] == "___") {
                    board[1][2] = n;
                    break;
                }
                case '7':
                if (board[2][0] != "___") {
                    System.out.println("Error: That square is taken. Choose another square");
                    choiceMenu(n);
                    break;
                }
                if (board[2][0] == "___") {
                    board[2][0] = n;
                    break;
                }
                case '8':
                if (board[2][1] != "___") {
                    System.out.println("Error: That square is taken. Choose another square");
                    choiceMenu(n);
                    break;
                }
                if (board[2][1] == "___") {
                    board[2][1] = n;
                    break;
                }
                case '9':
                if (board[2][2] != "___") {
                    System.out.println("Error: That square is taken. Choose another square");
                    choiceMenu(n);
                    break;
                }
                if (board[2][2] == "___") {
                    board[2][2] = n;
                    break;
                }
            }

        }

    }
}
