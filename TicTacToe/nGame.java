import java.util.*;
/**
 * Write a description of class nGame here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class nGame
{
    String [][] newBoard;
    String [][] newEmptyBoard;
    private List <Player> players;
    private Scanner console = new Scanner (System.in);

    /////////////////////////////////////////////////////////
    public nGame(List <Player> players)
    {
        // initialise instance variables
        this.players = players;
    }

    public void playerTurn(Player player1) {
        String n = player1.getToken();
        choiceMenu(n);

    }

    public void cpuTurn() {
        if (checkAlmostO()) {}
        else if (checkAlmostX()) {}
        else cpuRandomTurn();
    }

    public void cpuRandomTurn() {
        ArrayList <Integer> rows = new ArrayList <Integer>();
        ArrayList <Integer> cols = new ArrayList <Integer>();                
        for (int row = 0; row < newEmptyBoard.length; row++) {
            for (int col = 0; col < newEmptyBoard[0].length; col++) {
                if (newBoard[row][col] == ("___")) {
                    rows.add(row);
                    cols.add(col);
                }
            }
        }
        double k = Math.random()*rows.size();
        int K = (int) k;
        newBoard[rows.get(K)][cols.get(K)] = " O ";
    }

    public boolean play(Player player1, Player player2, int n) {
        int steps = 0;
        newBoard = new String[n][n];
        newEmptyBoard = new String[n][n];
        newBoard(n);
        do {
            if ((steps%2) == 0) {
                playerTurn(player1);
                steps++;
                if (checkX()) {
                    makeNewBoard();System.out.println();
                    System.out.println("Game Over: Player 1 wins!"); 
                    System.out.println("Play again (Player vs Player (1) or Player vs CPU (2)) or Quit (Q)?");
                    System.out.print("Choice ---> ");
                    String choice = console.next() + " ";
                    if (choice.charAt(0) == '1'){resetArray(); return play(player1, player2, n);}
                    else if (choice.charAt(0) == '2'){resetArray(); return playCPU(player1, n);}
                    else return true;}
                if (checkO()) {
                    makeNewBoard();System.out.println();
                    System.out.println("Game Over: Player 2 wins!"); 
                    System.out.println("Play again (Player vs Player (1) or Player vs CPU (2)) or Quit (Q)?");
                    System.out.print("Choice ---> ");
                    String choice = console.next() + " ";
                    if (choice.charAt(0) == '1'){resetArray(); return play(player1, player2, n);}
                    else if (choice.charAt(0) == '2'){resetArray(); return playCPU(player1, n);}
                    else return true;}
                if (checkFull()) {
                    makeNewBoard();System.out.println();
                    System.out.println("Game Over: Tie Game!");
                    System.out.println("Play again (Player vs Player (1) or Player vs CPU (2)) or Quit (Q)?");
                    System.out.print("Choice ---> ");
                    String choice = console.next() + " ";
                    if (choice.charAt(0) == '1'){resetArray(); return play(player1, player2, n);}
                    else if (choice.charAt(0) == '2'){resetArray(); return playCPU(player1, n);}
                    else return true;}
            }
            else if ((steps%2) == 1) {
                playerTurn(player2);
                steps++;
                if (checkX()) {
                    makeNewBoard();System.out.println();
                    System.out.println("Game Over: Player 1 wins!"); 
                    System.out.println("Play again (Player vs Player (1) or Player vs CPU (2)) or Quit (Q)?");
                    System.out.print("Choice ---> ");
                    String choice = console.next() + " ";
                    if (choice.charAt(0) == '1'){resetArray(); return play(player1, player2, n);}
                    else if (choice.charAt(0) == '2'){resetArray(); return playCPU(player1, n);}
                    else return true;}
                if (checkO()) {
                    makeNewBoard();System.out.println();
                    System.out.println("Game Over: Player 2 wins!"); 
                    System.out.println("Play again (Player vs Player (1) or Player vs CPU (2)) or Quit (Q)?");
                    System.out.print("Choice ---> ");
                    String choice = console.next() + " ";
                    if (choice.charAt(0) == '1'){resetArray(); return play(player1, player2, n);}
                    else if (choice.charAt(0) == '2'){resetArray(); return playCPU(player1, n);}
                    else return true;}
                if (checkFull()) {
                    makeNewBoard();System.out.println();
                    System.out.println("Game Over: Tie Game!");
                    System.out.println("Play again (Player vs Player (1) or Player vs CPU (2)) or Quit (Q)?");
                    System.out.print("Choice ---> ");
                    String choice = console.next() + " ";
                    if (choice.charAt(0) == '1'){resetArray(); return play(player1, player2, n);}
                    else if (choice.charAt(0) == '2'){resetArray(); return playCPU(player1, n);}
                    else return true;}            
            }
        } while (steps < (n*n));
        return false;
    }

    public boolean playCPU(Player player1, int n) {
        int steps = 0;
        newBoard = new String[n][n];
        newEmptyBoard = new String[n][n];
        newBoard(n);
        do {
            if ((steps%2) == 0) {
                playerTurn(player1);
                steps++;
                if (checkX()) {
                    makeNewBoard();System.out.println();
                    System.out.println("Game Over: Player 1 wins!"); 
                    System.out.println("Play again (Player vs Player (1) or Player vs CPU (2)) or Quit (Q)?");
                    System.out.print("Choice ---> ");
                    String choice = console.next() + " ";
                    if (choice.charAt(0) == '1'){resetArray(); return play(player1, new Player2(), n);}
                    else if (choice.charAt(0) == '2'){resetArray(); return playCPU(player1, n);}
                    else return true;}
                if (checkO()) {
                    makeNewBoard();System.out.println();
                    System.out.println("Game Over: CPU wins!"); 
                    System.out.println("Play again (Player vs Player (1) or Player vs CPU (2)) or Quit (Q)?");
                    System.out.print("Choice ---> ");
                    String choice = console.next() + " ";
                    if (choice.charAt(0) == '1'){resetArray(); return play(player1, new Player2(), n);}
                    else if (choice.charAt(0) == '2'){resetArray(); return playCPU(player1, n);}
                    else return true;}
                if (checkFull()) {
                    makeNewBoard();System.out.println();
                    System.out.println("Game Over: Tie Game!");
                    System.out.println("Play again (Player vs Player (1) or Player vs CPU (2)) or Quit (Q)?");
                    System.out.print("Choice ---> ");
                    String choice = console.next() + " ";
                    if (choice.charAt(0) == '1'){resetArray(); return play(player1, new Player2(), n);}
                    else if (choice.charAt(0) == '2'){resetArray(); return playCPU(player1, n);}
                    else return true;}            
            }
            else if ((steps%2) == 1) {
                cpuTurn();
                steps++;
                if (checkX()) {
                    makeNewBoard();System.out.println();
                    System.out.println("Game Over: Player 1 wins!"); 
                    System.out.println("Play again (Player vs Player (1) or Player vs CPU (2)) or Quit (Q)?");
                    System.out.print("Choice ---> ");
                    String choice = console.next() + " ";
                    if (choice.charAt(0) == '1'){resetArray(); return play(player1, new Player2(), n);}
                    else if (choice.charAt(0) == '2'){resetArray(); return playCPU(player1, n);}
                    else return true;}
                if (checkO()) {
                    makeNewBoard();System.out.println();
                    System.out.println("Game Over: CPU wins!"); 
                    System.out.println("Play again (Player vs Player (1) or Player vs CPU (2)) or Quit (Q)?");
                    System.out.print("Choice ---> ");
                    String choice = console.next() + " ";
                    if (choice.charAt(0) == '1'){resetArray(); return play(player1, new Player2(), n);}
                    else if (choice.charAt(0) == '2'){resetArray(); return playCPU(player1, n);}
                    else return true;}
                if (checkFull()) {
                    makeNewBoard();System.out.println();
                    System.out.println("Game Over: Tie Game!");
                    System.out.println("Play again (Player vs Player (1) or Player vs CPU (2)) or Quit (Q)?");
                    System.out.print("Choice ---> ");
                    String choice = console.next() + " ";
                    if (choice.charAt(0) == '1'){resetArray(); return play(player1, new Player2(), n);}
                    else if (choice.charAt(0) == '2'){resetArray(); return playCPU(player1, n);}
                    else return true;}            

            } 
        }while (steps < (n*n));
        return false;
    }

    public boolean checkX() {
        for (int col = 0; col < newBoard[0].length; col++) {
            int i = 0;
            for (int row = 0; row < newBoard.length; row++) {
                if (newBoard[row][col] == " X ") {i++;}
                if (i == newBoard.length) return true;
            }
        }
        for (int row = 0; row < newBoard.length; row++) {
            int i = 0;
            for (int col = 0; col < newBoard[0].length; col++) {
                if (newBoard[row][col] == " X ") i++;
                if (i == newBoard.length) return true;
            }
        }
        int i = 0;
        for (int row = 0; row < newBoard.length; row++) {

            if (newBoard[row][row] == " X ") i++;
            if (i == newBoard.length) return true;
        }
        i = 0;
        for (int row = 0; row < newBoard.length; row++) {

            if (newBoard[row][newBoard.length-row-1] == " X ") i++;
            if (i == newBoard.length) return true;
        }
        return false;
    }

    public boolean checkO() {
        for (int col = 0; col < newBoard[0].length; col++) {
            int i = 0;
            for (int row = 0; row < newBoard.length; row++) {
                if (newBoard[row][col] == " O ") i++;
                if (i == newBoard.length) return true;
            }
        }
        for (int row = 0; row < newBoard.length; row++) {
            int i = 0;
            for (int col = 0; col < newBoard[0].length; col++) {
                if (newBoard[row][col] == " O ") i++;
                if (i == newBoard.length) return true;
            }
        }
        int i = 0;
        for (int row = 0; row < newBoard.length; row++) {

            if (newBoard[row][row] == " O ") i++;
            if (i == newBoard.length) return true;
        }
        i = 0;
        for (int row = 0; row < newBoard.length; row++) {

            if (newBoard[row][newBoard.length-row-1] == " O ") i++;
            if (i == newBoard.length) return true;
        }
        return false;
    }

    public boolean checkAlmostX() {
        Player one = new Player1();
        String n = one.getToken();
        int rOw = 0; int cOl = 0;
        for (int col = 0; col < newBoard[0].length; col++) {
            int i = 0;
            for (int row = 0; row < newBoard.length; row++) {
                if (newBoard[row][col] == n) i++;
                if (newBoard[row][col] != n) {rOw = row; cOl = col;}
                if ((i == newBoard.length-1) && (newBoard[rOw][cOl] == "___")) {
                    newBoard[rOw][cOl] = " O ";
                    return true;
                }
            }
        }
        for (int row = 0; row < newBoard.length; row++) {
            int i = 0;
            for (int col = 0; col < newBoard[0].length; col++) {
                if (newBoard[row][col] == n) i++;
                if (newBoard[row][col] != n) {rOw = row; cOl = col;}
                if ((i == newBoard.length-1) && (newBoard[rOw][cOl] == "___")) {
                    newBoard[rOw][cOl] = " O ";
                    return true;
                }
            }
        }
        int i = 0;
        for (int row = 0; row < newBoard.length; row++) {

            if (newBoard[row][row] == n) i++;
            if (newBoard[row][row] != n) {rOw = row; cOl = row;}
            if ((i == newBoard.length-1) && (newBoard[rOw][cOl] == "___")) {
                newBoard[rOw][cOl] = " O ";
                return true;
            }
        }
        i = 0;
        for (int row = 0; row < newBoard.length; row++) {

            if (newBoard[row][newBoard.length-row-1] == n) i++;
            if (newBoard[row][newBoard.length-row-1] != n) {rOw = row; cOl = newBoard.length-row-1;}
            if ((i == newBoard.length-1) && (newBoard[rOw][cOl] == "___")) {
                newBoard[rOw][cOl] = " O ";
                return true;
            }
        }
        return false;
    }

    public boolean checkAlmostO() {
        int rOw = 0; int cOl = 0;
        for (int col = 0; col < newBoard[0].length; col++) {
            int i = 0;
            for (int row = 0; row < newBoard.length; row++) {
                if (newBoard[row][col] == " O ") i++;
                if (newBoard[row][col] != " O ") {rOw = row; cOl = col;}
                if ((i == newBoard.length-1) && (newBoard[rOw][cOl] == "___")) {
                    newBoard[rOw][cOl] = " O ";
                    return true;
                }
            }
        }
        for (int row = 0; row < newBoard.length; row++) {
            int i = 0;
            for (int col = 0; col < newBoard[0].length; col++) {
                if (newBoard[row][col] == " O ") i++;
                if (newBoard[row][col] != " O ") {rOw = row; cOl = col;}
                if ((i == newBoard.length-1) && (newBoard[rOw][cOl] == "___")) {
                    newBoard[rOw][cOl] = " O ";
                    return true;
                }
            }
        }
        int i = 0;
        for (int row = 0; row < newBoard.length; row++) {

            if (newBoard[row][row] == " O ") i++;
            if (newBoard[row][row] != " O ") {rOw = row; cOl = row;}
            if ((i == newBoard.length-1) && (newBoard[rOw][cOl] == "___")) {
                newBoard[rOw][cOl] = " O ";
                return true;
            }
        }
        i = 0;
        for (int row = 0; row < newBoard.length; row++) {

            if (newBoard[row][newBoard.length-row-1] == " O ") i++;
            if (newBoard[row][newBoard.length-row-1] != " O ") {rOw = row; cOl = newBoard.length-row-1;}
            if ((i == newBoard.length-1) && (newBoard[rOw][cOl] == "___")) {
                newBoard[rOw][cOl] = " O ";
                return true;
            }
        }
        return false;
    }

    public boolean checkFull() {
        for (int row = 0; row < newBoard.length; row++) {
            for (int col = 0; col < newBoard[0].length; col++) {
                if (newBoard[row][col] == "___") return false;
            }
        }
        return true;
    }

    public void newBoard(int n) {

        for (int row = 0; row < newBoard.length; row++) {
            for (int col = 0; col < newBoard[0].length; col++) {
                newBoard[row][col] = "___";
            }
        }
        makeNewEmptyBoard(n);
    }

    public void makeNewBoard() {
        for (int row = 0; row < newBoard.length; row++) {
            System.out.println();
            for (int col = 0; col < newBoard[0].length; col++) {
                System.out.print(newBoard[row][col]);
                if (col < newBoard[0].length-1) {
                    System.out.print("|");
                }
            }
        }
    }

    public void makeNewEmptyBoard(int n) {

        int i = 1;
        for (int row = 0; row < newEmptyBoard.length; row++) {
            for (int col = 0; col < newEmptyBoard[0].length; col++) {
                if (i < 10) {newEmptyBoard[row][col] = "_" + i + "_";}
                else if (i < 100) {newEmptyBoard[row][col] = "_" + i;}
                else {newEmptyBoard[row][col] = "" + i;}
                i++;
            }
        }
    }

    public void print_n_EmptyArray () {
        for (int row = 0; row < newEmptyBoard.length; row++) {
            System.out.println();
            for (int col = 0; col < newEmptyBoard[0].length; col++) {
                System.out.print(newEmptyBoard[row][col]);
                if (col < newEmptyBoard[0].length-1) {
                    System.out.print("|");
                }
            }
        }
    }

    public void resetArray() {
        for (int row = 0; row < newBoard.length; row++) {
            for (int col = 0; col < newBoard[0].length; col++) {
                newBoard[row][col] = "___";
            }
        }
    }

    public void choiceMenu(String n){
        int choice;
        String print;
        int i = 1;

        System.out.println();
        makeNewBoard();
        System.out.println();
        print_n_EmptyArray();
        System.out.println();
        System.out.println("Which square would you like to select");
        System.out.println();
        for (int row = 0; row < newEmptyBoard.length; row++) {
            System.out.println();
            for (int col = 0; col < newEmptyBoard[0].length; col++) {
                if (i < 10) {System.out.print("(" + i + ")     ");}
                else if (i > 9 && i < 100) {System.out.print("(" + i + ")    ");}
                else if (i > 99 && i < 1000) {System.out.print("(" + i + ")    ");}
                else {System.out.print("(" + i + ")   ");}
                i++;
            }
        }
        System.out.println();
        System.out.print("Choice ---> ");
        choice = console.nextInt();
        if (1 <= choice && choice <= (newEmptyBoard.length)*(newEmptyBoard.length)){
            System.out.println();
            int rOw = 0;
            int cOl = 0;

            outer: for (int row = 0; row < newEmptyBoard.length; row++) {
                for (int col = 0; col < newEmptyBoard[0].length; col++) {
                    if (newEmptyBoard[row][col].equals("_" + choice + "_")) {
                        rOw = row;
                        cOl = col;
                        break outer;
                    }
                    else if (newEmptyBoard[row][col].equals("_" + choice)) {
                        rOw = row;
                        cOl = col;
                        break outer;
                    }
                    else if (newEmptyBoard[row][col].equals("" + choice)) {
                        rOw = row;
                        cOl = col;
                        break outer;
                    }

                }

            }
            switch (choice) {
                default:
                if (newBoard[rOw][cOl] != "___") {
                    System.out.println("Error: That square is taken. Choose another square");
                    choiceMenu(n);
                    break;
                }
                else if (newBoard[rOw][cOl] == "___") {
                    newBoard[rOw][cOl] = n;
                    break;
                }
            }

        }
    }

}
