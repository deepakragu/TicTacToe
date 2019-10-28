
/**
 * Write a description of class Player1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player1 implements Player
{
    public String token = " X ";
    // instance variables - replace the example below with your own
    public Player1 () {
        String token = " X ";
    }
    public void move (String n, String [][] arr) {
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[0].length; col++) {
                if (arr[row][col] == n) {
                    arr[row][col] = token;
                }
            }
        }
    }
    public String getToken () {return token;}
}
