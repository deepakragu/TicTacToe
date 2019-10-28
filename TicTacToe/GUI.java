// ============================================================================
//     Taken From: http://programmingnotes.org/
// ============================================================================
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GUI extends JFrame implements ActionListener
{
    // setting up ALL the variables
    JFrame window = new JFrame("Deepak's Tic Tac Toe Game");

    JMenuBar mnuMain = new JMenuBar();
    JMenuItem   mnuNewGame = new JMenuItem("  New Game"), 
    mnuGameTitle = new JMenuItem("|Tic Tac Toe|  "),
    mnuStartingPlayer = new JMenuItem(" Starting Player"),
    mnuExit = new JMenuItem("    Quit");

    JButton btnEmpty[];

    JPanel  pnlNewGame = new JPanel(),
    pnlNorth = new JPanel(),
    pnlSouth = new JPanel(),
    pnlTop = new JPanel(),
    pnlBottom = new JPanel(),
    pnlPlayingField = new JPanel();
    JPanel radioPanel = new JPanel();

    private JRadioButton SelectX = new JRadioButton("User Plays X", false);
    private  JRadioButton SelectO = new JRadioButton("User Plays O", false);
    private ButtonGroup radioGroup;
    private  String startingPlayer= "";
    final int X = 800, Y = 480, color = 90; // size of the game window
    private boolean inGame = false;
    private boolean win = false;
    private boolean btnEmptyClicked = false;
    private boolean setTableEnabled = false;
    private String message;
    private Font font = new Font("Rufscript", Font.BOLD, 100);
    private int remainingMoves = 1;

    ArrayList <Integer> nums = new ArrayList <Integer>(0);

    boolean gameType;
    //===============================  GUI  ========================================//
    public GUI() //This is the constructor
    {
        //Setting window properties:
        window.setSize(X, Y);
        window.setLocation(300, 180);
        window.setResizable(true);
        window.setLayout(new BorderLayout());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  

        //------------  Sets up Panels and text fields  ------------------------//
        // setting Panel layouts and properties
        pnlNorth.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlSouth.setLayout(new FlowLayout(FlowLayout.CENTER));

        pnlNorth.setBackground(new Color(70, 70, 70));
        pnlSouth.setBackground(new Color(color, color, color));

        pnlTop.setBackground(new Color(color, color, color));
        pnlBottom.setBackground(new Color(color, color, color));

        pnlTop.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlBottom.setLayout(new FlowLayout(FlowLayout.CENTER));

        radioPanel.setBackground(new Color(color, color, color));
        pnlBottom.setBackground(new Color(color, color, color));
        radioPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Who Goes First?"));

        // adding menu items to menu bar
        mnuMain.add(mnuGameTitle);
        mnuGameTitle.setEnabled(false);
        mnuGameTitle.setFont(new Font("Rufscript",Font.BOLD,18));
        mnuMain.add(mnuNewGame);
        mnuNewGame.setFont(new Font("Rufscript",Font.BOLD,18));
        mnuMain.add(mnuStartingPlayer);
        mnuStartingPlayer.setFont(new Font("Rufscript",Font.BOLD,18));
        mnuMain.add(mnuExit);
        mnuExit.setFont(new Font("Rufscript",Font.BOLD,18));//---->Menu Bar Complete

        // adding X & O options to menu
        SelectX.setFont(new Font("Rufscript",Font.BOLD,18));
        SelectO.setFont(new Font("Rufscript",Font.BOLD,18));
        radioGroup = new ButtonGroup(); // create ButtonGroup
        radioGroup.add(SelectX); // add plain to group
        radioGroup.add(SelectO);
        radioPanel.add(SelectX);
        radioPanel.add(SelectO);

        // adding Action Listener to all the Buttons and Menu Items
        mnuNewGame.addActionListener(this);
        mnuExit.addActionListener(this);
        mnuStartingPlayer.addActionListener(this);

        // setting up the playing field

        Object[] options = {"1 vs 1",
                "CPU"};
        int o = JOptionPane.showOptionDialog(null,
                "Would you like to play 1 vs 1 or CPU??",
                "Welcome to Tic Tac Toe!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,     //do not use a custom Icon
                options,  //the titles of buttons
                options[0]); //default button title
        if (o == JOptionPane.NO_OPTION) {gameType = true;}

        int n = createArraySize();
        int i = n*n;
        i++;
        btnEmpty = new JButton[i];

        pnlPlayingField.setLayout(new GridLayout(n, n, 2, 2));
        pnlPlayingField.setBackground(Color.black);

        for(int x=1; x < btnEmpty.length; ++x)   
        {
            btnEmpty[x] = new JButton();
            btnEmpty[x].setBackground(new Color(220, 220, 220));
            btnEmpty[x].addActionListener(this);
            pnlPlayingField.add(btnEmpty[x]);
            btnEmpty[x].setEnabled(setTableEnabled);
        }

        // adding everything needed to pnlNorth and pnlSouth
        pnlNorth.add(mnuMain);
        BusinessLogic.ShowGame(pnlSouth,pnlPlayingField);

        // adding to window and Showing window
        window.add(pnlNorth, BorderLayout.NORTH);
        window.add(pnlSouth, BorderLayout.CENTER);
        window.setVisible(true);
    }// End GUI

    // ===========  Start Action Performed  ===============//
    public void actionPerformed(ActionEvent click)  
    {
        // get the mouse click from the user
        Object source = click.getSource();

        // check if a button was clicked on the gameboard
        for(int currentMove=1; currentMove < btnEmpty.length; ++currentMove) 
        {
            if(source == btnEmpty[currentMove] && remainingMoves < btnEmpty.length)  
            {
                btnEmptyClicked = true;
                BusinessLogic.GetMove(currentMove, remainingMoves, font, 
                    btnEmpty, startingPlayer);
                //if (nums.size() >= btnEmpty.length-1) {postGameTie();}
                btnEmpty[currentMove].setEnabled(false);
                nums.add(currentMove);
                pnlPlayingField.requestFocus();
                ++remainingMoves;
                if (nums.size() >= (btnEmpty.length-1)) {postGameTie();}
                if (gameType == true) {
                    cpuMove();
                    //CheckWin();

                }
            }
        }

        // if a button was clicked on the gameboard, check for a winner
        if(btnEmptyClicked) 
        {
            inGame = true;
            CheckWin();
            btnEmptyClicked = false;
        }

        // check if the user clicks on a menu item
        if(source == mnuNewGame)    
        {
            System.out.println(startingPlayer);
            BusinessLogic.ClearPanelSouth(pnlSouth,pnlTop,pnlNewGame,
                pnlPlayingField,pnlBottom,radioPanel);
            if(startingPlayer.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Please Select a Starting Player", 
                    "Oops..", JOptionPane.ERROR_MESSAGE);
                BusinessLogic.ShowGame(pnlSouth,pnlPlayingField);
            }
            else
            {
                if(inGame)  
                {
                    int option = JOptionPane.showConfirmDialog(null, "If you start a new game," +
                            " your current game will be lost..." + "n" +"Are you sure you want to continue?"
                        , "New Game?" ,JOptionPane.YES_NO_OPTION);
                    if(option == JOptionPane.YES_OPTION)    
                    {
                        inGame = false;
                        startingPlayer = "";
                        setTableEnabled = false;
                    }
                    else
                    {
                        BusinessLogic.ShowGame(pnlSouth,pnlPlayingField);
                    }
                }
                // redraw the gameboard to its initial state
                if(!inGame) 
                {
                    RedrawGameBoard();
                }
            }       
        }       
        // exit button
        else if(source == mnuExit)  
        {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", 
                    "Quit" ,JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
        }
        // select X or O player 
        else if(source == mnuStartingPlayer)  
        {
            if(inGame)  
            {
                JOptionPane.showMessageDialog(null, "Cannot select a new Starting "+
                    "Player at this time.nFinish the current game, or select a New Game "+
                    "to continue", "Game In Session..", JOptionPane.INFORMATION_MESSAGE);
                BusinessLogic.ShowGame(pnlSouth,pnlPlayingField);
            }
            else
            {
                setTableEnabled = true;
                BusinessLogic.ClearPanelSouth(pnlSouth,pnlTop,pnlNewGame,
                    pnlPlayingField,pnlBottom,radioPanel);

                SelectX.addActionListener(new RadioListener());
                SelectO.addActionListener(new RadioListener());
                radioPanel.setLayout(new GridLayout(2,1));

                radioPanel.add(SelectX);
                radioPanel.add(SelectO);
                pnlSouth.setLayout(new GridLayout(2, 1, 2, 1));
                pnlSouth.add(radioPanel);
                pnlSouth.add(pnlBottom);
            }
        }
        pnlSouth.setVisible(false); 
        pnlSouth.setVisible(true);  
    }// End Action Performed

    public void cpuMove() {
        //cpuRandomTurn();
        //CheckWin();
        double R = Math.sqrt(btnEmpty.length-1);
        int dim = (int) (R);
        int[] coordinates = checkAI();
        //System.out.println(coordinates[0] + "  " + coordinates[1]);
        int currentMove2;
        if (coordinates[0] != -1) {
            //System.out.println("triggered");

            currentMove2 = ((coordinates[0]) * dim) + coordinates[1] + 1;
            BusinessLogic.GetMove(currentMove2, remainingMoves, font, btnEmpty, startingPlayer);
            nums.add(currentMove2);
            btnEmpty[currentMove2].setEnabled(false);
            pnlPlayingField.requestFocus();
            ++remainingMoves;

            //System.out.println(currentMove2);

        } else {

            cpuRandomTurn();
            
            //System.out.println("yaa");
        }
        

    }

    public int[] checkAI() {
        double R = Math.sqrt(btnEmpty.length-1);
        int dim = (int) (R);    
        String[][] points = new String[dim][dim];
        int counter = 1;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (btnEmpty[counter].getText() != null) {
                    points[i][j] = btnEmpty[counter].getText();
                } else {
                    points[i][j] = " ";
                }
                counter++;
            }
        }
        int[] coordinates = { -1, -1 };
        int xcounter;
        int ocounter;
        xcounter = 0;
        ocounter = 0;
        int ansRow = -1;
        int ansCol = -1;
        for (int row = 0; row < dim; row++) {
            xcounter = 0;
            ocounter = 0;
            for (int col = 0; col < dim; col++) {
                if ((points[row][col].equals("O"))) {
                    ocounter++;
                    // System.out.println("Row: " + row + " Col: " + col);
                    // System.out.println("XCOUNT " + xcounter + " OCOUNT " + ocounter);
                }
                if ((points[row][col].equals("X"))) {
                    xcounter++;
                    // System.out.println("Row: " + row + " Col: " + col);
                    // System.out.println("XCOUNT " + xcounter + " OCOUNT " + ocounter);
                }
            }
            if (ocounter == dim - 1 && xcounter == 0) {
                // System.out.println("triggered");
                ansRow = row;
                // System.out.println("ansRow: " + ansRow);
            }
            else if (xcounter == dim - 1 && ocounter == 0) {
                // System.out.println("triggered");
                ansRow = row;
                // System.out.println("ansRow: " + ansRow);
            }
        }
        if (ansRow != -1) {
            // System.out.println("triggered 2");
            for (int col = 0; col < dim; col++) {
                // System.out.print(points[ansRow][col] + " ");
                if (!(points[ansRow][col].equals("X")) && !(points[ansRow][col].equals("O"))) {
                    coordinates[0] = ansRow;
                    coordinates[1] = col;
                    // System.out.println("coordinates: " + coordinates[0] + ", " + coordinates[1]);
                    return coordinates;
                }
            }
        }

        ansRow = -1;
        ansCol = -1;
        xcounter = 0;
        ocounter = 0;
        for (int col = 0; col < dim; col++) {
            xcounter = 0;
            ocounter = 0;
            for (int row = 0; row < dim; row++) {
                if ((points[row][col].equals("O"))) {
                    ocounter++;
                }
                if ((points[row][col].equals("X"))) {
                    xcounter++;
                }
            }
            if (ocounter == dim - 1 && xcounter == 0) {
                // System.out.println("triggered");
                ansCol = col;
                // System.out.println("ansRow: " + ansRow);
            }
            else if (xcounter == dim - 1 && ocounter == 0) {
                // System.out.println("triggered");
                ansCol = col;
                // System.out.println("ansRow: " + ansRow);
            }
        }

        if (ansCol != -1) {
            for (int row = 0; row < dim; row++) {
                if (!(points[row][ansCol].equals("X")) && !(points[row][ansCol].equals("O"))) {
                    {
                        coordinates[0] = row;
                        coordinates[1] = ansCol;
                        return coordinates;
                    }
                }
            }
        }

        ansRow = -1;
        ansCol = -1;
        xcounter = 0;
        ocounter = 0;
        for (int num = 0; num < dim; num++) {
            if ((points[num][num].equals("O"))) {
                ocounter++;
            }
            if ((points[num][num].equals("X"))) {
                xcounter++;
            }
        }
        if (ocounter == dim - 1 && xcounter == 0) {
            for (int num = 0; num < dim; num++) {
                if (!(points[num][num].equals("X")) && !(points[num][num].equals("O"))) {
                    coordinates[0] = num;
                    coordinates[1] = num;
                    return coordinates;
                }
            }
        }
        if (xcounter == dim - 1 && ocounter == 0) {
            for (int num = 0; num < dim; num++) {
                if (!(points[num][num].equals("X")) && !(points[num][num].equals("O"))) {
                    coordinates[0] = num;
                    coordinates[1] = num;
                    return coordinates;
                }
            }
        }

        ansRow = -1;
        ansCol = -1;
        xcounter = 0;
        ocounter = 0;
        for (int num = 0; num < dim; num++) {
            if ((points[num][dim - num - 1].equals("O"))) {
                ocounter++;
            }
            if ((points[num][dim - num - 1].equals("X"))) {
                xcounter++;
            }

        }
        if (ocounter == dim - 1 && xcounter == 0) {
            for (int num = 0; num < dim; num++) {
                if (!(points[num][dim - num-1].equals("X")) && !(points[num][dim - num-1].equals("O"))) {
                    {
                        coordinates[0] = num;
                        coordinates[1] = dim - num - 1;
                        return coordinates;
                    }
                }

            }
        }
        if (xcounter == dim - 1 && ocounter == 0) {
            for (int num = 0; num < dim; num++) {
                if (!(points[num][dim - num-1].equals("X")) && !(points[num][dim - num-1].equals("O"))) {
                    {
                        coordinates[0] = num;
                        coordinates[1] = dim - num - 1;
                        return coordinates;
                    }
                }

            }
        }

        return coordinates;

    }

    public boolean checkAlmostXAdd() {
        double R = Math.sqrt(btnEmpty.length-1);
        int r = (int) (R);
        int j = 0;
        int k = 0;
        int spot = 0;
        ArrayList <String> annoyance = new ArrayList <String>();
        if (nums.size()>=0) {
            j=0;
            k = 0;
            spot = 0;
            for (int add = 0; add < btnEmpty.length-1; add+=r) {
                j = 0;
                k=0;
                spot = 0;
                for (int i = 1; i < (r+1); i ++) {
                    if (btnEmpty[(i+add)].getText() == null) {spot = (i+add);}
                    if (btnEmpty[(i+add)].getText() != null) {
                        if (btnEmpty[(i+add)].getText().equals("X")) {j++;}
                        if (btnEmpty[(i+add)].getText().equals("O")) {k++;}
                        if ((j == (r-1)) && (k == 0)) {
                            BusinessLogic.GetMove(spot, remainingMoves, font,                     btnEmpty, startingPlayer);;
                            return true;
                        }
                    }
                }
            }
            j=0;
            k=0;
            spot = 0;
            for (int i = 1; i < (r+1); i ++) {// index out of bounds error
                j = 0;
                k=0;
                spot = 0;
                for (int add = 0; add < btnEmpty.length-1; add+=r) {
                    if (btnEmpty[(i+add)].getText() == null) {spot = (i+add);}
                    if (btnEmpty[(i+add)].getText() != null) {
                        if (btnEmpty[(i+add)].getText().equals("X")) {j++;}
                        if (btnEmpty[(i+add)].getText().equals("O")) {k++;}
                        if ((j == (r-1)) && (k == 0)) {
                            BusinessLogic.GetMove(spot, remainingMoves, font,                     btnEmpty, startingPlayer);;
                            return true;
                        }
                    }
                }
            }
            j = 0;
            k=0;
            spot = 0;
            for (int i = 1; i < btnEmpty.length; i+=(r+1)) {
                if (btnEmpty[(i)].getText() == null) {spot = (i);}
                if (btnEmpty[(i)].getText() != null) {
                    if (btnEmpty[i].getText().equals("X")) {j++;}
                    if (btnEmpty[(i)].getText().equals("O")) {k++;}
                    if ((j == (r-1)) && (k == 0)) {
                        BusinessLogic.GetMove(spot, remainingMoves, font,                     btnEmpty, startingPlayer);;
                        return true;
                    }
                }
            }
            j = 0;
            k=0;
            spot = 0;
            for (int i = r; i < ((r)*(r-1) + 2); i+=(r-1)) {
                if (btnEmpty[i].getText() == null) {spot = (i);}
                if (btnEmpty[(i)].getText() != null) {
                    if (btnEmpty[i].getText().equals("X")) {j++;}
                    if (btnEmpty[(i)].getText().equals("O")) {k++;}
                    if ((j == (r-1)) && (k == 0)) {
                        BusinessLogic.GetMove(spot, remainingMoves, font,                     btnEmpty, startingPlayer);;
                        return true;
                    }
                }
            }

        }
        return false;
    }

    public boolean checkAlmostOAdd() {
        double R = Math.sqrt(btnEmpty.length-1);
        int r = (int) (R);
        int j = 0;
        int k = 0;
        int spot = 0;
        ArrayList <String> annoyance = new ArrayList <String>();
        if (nums.size()>=0) {
            j=0;
            k = 0;
            spot = 0;
            for (int add = 0; add < btnEmpty.length-1; add+=r) {
                j = 0;
                k=0;
                spot = 0;
                for (int i = 1; i < (r+1); i ++) {
                    if (btnEmpty[(i+add)].getText() == null) {spot = (i+add);}
                    if (btnEmpty[(i+add)].getText() != null) {
                        if (btnEmpty[(i+add)].getText().equals("X")) {k++;}
                        if (btnEmpty[(i+add)].getText().equals("O")) {j++;}
                        if ((j == (r-1)) && (k == 0)) {
                            BusinessLogic.GetMove(spot, remainingMoves, font,                     btnEmpty, startingPlayer);;
                            return true;
                        }
                    }
                }
            }
            j=0;
            k=0;
            spot = 0;
            for (int i = 1; i < (r+1); i ++) {// index out of bounds error
                j = 0;
                k=0;
                spot = 0;
                for (int add = 0; add < btnEmpty.length-1; add+=r) {
                    if (btnEmpty[(i+add)].getText() == null) {spot = (i+add);}
                    if (btnEmpty[(i+add)].getText() != null) {
                        if (btnEmpty[(i+add)].getText().equals("X")) {k++;}
                        if (btnEmpty[(i+add)].getText().equals("O")) {j++;}
                        if ((j == (r-1)) && (k == 0)) {
                            BusinessLogic.GetMove(spot, remainingMoves, font,                     btnEmpty, startingPlayer);;
                            return true;
                        }
                    }
                }
            }
            j = 0;
            k=0;
            spot = 0;
            for (int i = 1; i < btnEmpty.length; i+=(r+1)) {
                if (btnEmpty[(i)].getText() == null) {spot = (i);}
                if (btnEmpty[(i)].getText() != null) {
                    if (btnEmpty[i].getText().equals("X")) {k++;}
                    if (btnEmpty[(i)].getText().equals("O")) {j++;}
                    if ((j == (r-1)) && (k == 0)) {
                        BusinessLogic.GetMove(spot, remainingMoves, font,                     btnEmpty, startingPlayer);;
                        return true;
                    }
                }
            }
            j = 0;
            k=0;
            spot = 0;
            for (int i = r; i < ((r)*(r-1) + 2); i+=(r-1)) {
                if (btnEmpty[i].getText() == null) {spot = (i);}
                if (btnEmpty[(i)].getText() != null) {
                    if (btnEmpty[i].getText().equals("X")) {k++;}
                    if (btnEmpty[(i)].getText().equals("O")) {j++;}
                    if ((j == (r-1)) && (k == 0)) {
                        BusinessLogic.GetMove(spot, remainingMoves, font,                     btnEmpty, startingPlayer);;
                        return true;
                    }
                }
            }

        }
        return false;
    }

    public boolean checkAlmostXBlock() {
        double R = Math.sqrt(btnEmpty.length-1);
        int r = (int) (R);
        int j = 0;
        int k = 0;
        int spot = 0;
        ArrayList <String> annoyance = new ArrayList <String>();
        if (nums.size()>=0) {
            j=0;
            k = 0;
            spot = 0;
            for (int add = 0; add < btnEmpty.length-1; add+=r) {
                j = 0;
                k=0;
                spot = 0;
                for (int i = 1; i < (r+1); i ++) {
                    if (btnEmpty[(i+add)].getText() == null) {spot = (i+add);}
                    if (btnEmpty[(i+add)].getText() != null) {
                        if (btnEmpty[(i+add)].getText().equals("X")) {j++;}
                        if (btnEmpty[(i+add)].getText().equals("O")) {k++;}
                        if ((j == (r-1)) && (k == 0)) {
                            BusinessLogic.GetMove(spot, remainingMoves, font,                     btnEmpty, startingPlayer);;
                            return true;
                        }
                    }
                }
            }
            j=0;
            k=0;
            spot = 0;
            for (int i = 1; i < (r+1); i ++) {// index out of bounds error
                j = 0;
                k=0;
                spot = 0;
                for (int add = 0; add < btnEmpty.length-1; add+=r) {
                    if (btnEmpty[(i+add)].getText() == null) {spot = (i+add);}
                    if (btnEmpty[(i+add)].getText() != null) {
                        if (btnEmpty[(i+add)].getText().equals("X")) {j++;}
                        if (btnEmpty[(i+add)].getText().equals("O")) {k++;}
                        if ((j == (r-1)) && (k == 0)) {
                            BusinessLogic.GetMove(spot, remainingMoves, font,                     btnEmpty, startingPlayer);;
                            return true;
                        }
                    }
                }
            }
            j = 0;
            k=0;
            spot = 0;
            for (int i = 1; i < btnEmpty.length; i+=(r+1)) {
                if (btnEmpty[(i)].getText() == null) {spot = (i);}
                if (btnEmpty[(i)].getText() != null) {
                    if (btnEmpty[i].getText().equals("X")) {j++;}
                    if (btnEmpty[(i)].getText().equals("O")) {k++;}
                    if ((j == (r-1)) && (k == 0)) {
                        BusinessLogic.GetMove(spot, remainingMoves, font,                     btnEmpty, startingPlayer);;
                        return true;
                    }
                }
            }
            j = 0;
            k=0;
            spot = 0;
            for (int i = r; i < ((r)*(r-1) + 2); i+=(r-1)) {
                if (btnEmpty[i].getText() == null) {spot = (i);}
                if (btnEmpty[(i)].getText() != null) {
                    if (btnEmpty[i].getText().equals("X")) {j++;}
                    if (btnEmpty[(i)].getText().equals("O")) {k++;}
                    if ((j == (r-1)) && (k == 0)) {
                        BusinessLogic.GetMove(spot, remainingMoves, font,                     btnEmpty, startingPlayer);;
                        return true;
                    }
                }
            }

        }
        return false;
    }

    public boolean checkAlmostOBlock() {
        double R = Math.sqrt(btnEmpty.length-1);
        int r = (int) (R);
        int j = 0;
        int k = 0;
        int spot = 0;
        ArrayList <String> annoyance = new ArrayList <String>();
        if (nums.size()>=0) {
            j=0;
            k = 0;
            spot = 0;
            for (int add = 0; add < btnEmpty.length-1; add+=r) {
                j = 0;
                k=0;
                spot = 0;
                for (int i = 1; i < (r+1); i ++) {
                    if (btnEmpty[(i+add)].getText() == null) {spot = (i+add);}
                    if (btnEmpty[(i+add)].getText() != null) {
                        if (btnEmpty[(i+add)].getText().equals("X")) {k++;}
                        if (btnEmpty[(i+add)].getText().equals("O")) {j++;}
                        if ((j == (r-1)) && (k == 0)) {
                            BusinessLogic.GetMove(spot, remainingMoves, font,                     btnEmpty, startingPlayer);;
                            return true;
                        }
                    }
                }
            }
            j=0;
            k=0;
            spot = 0;
            for (int i = 1; i < (r+1); i ++) {// index out of bounds error
                j = 0;
                k=0;
                spot = 0;
                for (int add = 0; add < btnEmpty.length-1; add+=r) {
                    if (btnEmpty[(i+add)].getText() == null) {spot = (i+add);}
                    if (btnEmpty[(i+add)].getText() != null) {
                        if (btnEmpty[(i+add)].getText().equals("X")) {k++;}
                        if (btnEmpty[(i+add)].getText().equals("O")) {j++;}
                        if ((j == (r-1)) && (k == 0)) {
                            BusinessLogic.GetMove(spot, remainingMoves, font,                     btnEmpty, startingPlayer);;
                            return true;
                        }
                    }
                }
            }
            j = 0;
            k=0;
            spot = 0;
            for (int i = 1; i < btnEmpty.length; i+=(r+1)) {
                if (btnEmpty[(i)].getText() == null) {spot = (i);}
                if (btnEmpty[(i)].getText() != null) {
                    if (btnEmpty[i].getText().equals("X")) {k++;}
                    if (btnEmpty[(i)].getText().equals("O")) {j++;}
                    if ((j == (r-1)) && (k == 0)) {
                        BusinessLogic.GetMove(spot, remainingMoves, font,                     btnEmpty, startingPlayer);;
                        return true;
                    }
                }
            }
            j = 0;
            k=0;
            spot = 0;
            for (int i = r; i < ((r)*(r-1) + 2); i+=(r-1)) {
                if (btnEmpty[i].getText() == null) {spot = (i);}
                if (btnEmpty[(i)].getText() != null) {
                    if (btnEmpty[i].getText().equals("X")) {k++;}
                    if (btnEmpty[(i)].getText().equals("O")) {j++;}
                    if ((j == (r-1)) && (k == 0)) {
                        BusinessLogic.GetMove(spot, remainingMoves, font,                     btnEmpty, startingPlayer);;
                        return true;
                    }
                }
            }

        }
        return false;
    }

    public void cpuRandomTurn() {
        ArrayList <Integer> emptySpots = new ArrayList <Integer>();        
        for (int j = 1; j < btnEmpty.length; j++) {
            if (btnEmpty[j].getText().equals("")) {emptySpots.add(j);}
        }
        double K = (Math.random()*emptySpots.size());
        int k = (int) K;
        int q = emptySpots.get(k);
        BusinessLogic.GetMove(q, remainingMoves, font, 
            btnEmpty, startingPlayer);
        btnEmpty[q].setEnabled(false);
        pnlPlayingField.requestFocus();
        ++remainingMoves;
        nums.add(q);

    }

    // ===========  Start RadioListener  ===============//  
    private class RadioListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent event) 
        {
            JRadioButton theButton = (JRadioButton)event.getSource();
            if(theButton.getText().equals("User Plays X")) 
            {
                startingPlayer = "X";
            }
            if(theButton.getText().equals("User Plays O"))
            {
                startingPlayer = "O";
            }

            // redisplay the gameboard to the screen
            pnlSouth.setVisible(false); 
            pnlSouth.setVisible(true);          
            RedrawGameBoard();
        }
    }// End RadioListener
    /*
    ----------------------------------
    Start of all the other methods. |
    ----------------------------------
     */
    private void RedrawGameBoard()  
    {
        BusinessLogic.ClearPanelSouth(pnlSouth,pnlTop,pnlNewGame,
            pnlPlayingField,pnlBottom,radioPanel);
        BusinessLogic.ShowGame(pnlSouth,pnlPlayingField);       

        remainingMoves = 1;

        for(int x=1; x < btnEmpty.length; ++x)   
        {
            btnEmpty[x].setText("");
            btnEmpty[x].setEnabled(setTableEnabled);
        }

        win = false;        
    }

    private void CheckWin() 
    {   
        double R = Math.sqrt(btnEmpty.length-1);
        int dim = (int) (R);

        String[][] points = new String[dim][dim];
        int counter = 1;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (btnEmpty[counter].getText() != null) {
                    points[i][j] = btnEmpty[counter].getText();
                } else {
                    points[i][j] = " ";
                }
                counter++;
            }
        }
        int ans = -1;
        boolean x;
        boolean o;
        for (int row = 0; row < dim; row++) {
            x = true;
            o = true;
            for (int col = 0; col < dim; col++) {
                if (!(points[row][col].equals("X"))) {
                    x = false;
                }
                if (!(points[row][col].equals("O"))) {
                    o = false;
                }
            }
            if (x) {
                postGameX();
            }
            if (o) {
                postGameO();
            }
        }

        for (int col = 0; col < dim; col++) {
            x = true;
            o = true;
            for (int row = 0; row < dim; row++) {
                if (!(points[row][col].equals("X"))) {
                    x = false;
                }
                if (!(points[row][col].equals("O"))) {
                    o = false;
                }
            }
            if (x) {
                postGameX();
            }
            if (o) {
                postGameO();
            }
        }

        x = true;
        o = true;
        for (int num = 0; num < dim; num++) {
            if (!(points[num][num].equals("X"))) {
                x = false;
            }
            if (!(points[num][num].equals("O"))) {
                o = false;
            }
        }
        if (x) {
            postGameX();
        }
        if (o) {
            postGameO();
        }
        x = true;
        o = true;
        for (int num = 0; num < dim; num++) {
            if (!(points[num][dim - num - 1].equals("X"))) {
                x = false;
            }
            if (!(points[num][dim - num - 1].equals("O"))) {
                o = false;
            }
        }
        if (x) {
            postGameX();
        }
        if (o) {
            postGameO();
        }



        
        // System.out.println some message, "ok" button, and reset board
        //postGameX();btnEmpty[nums.get(0)].getText().equals("X")

        /**
        int j = 0;
        ArrayList <String> annoyance = new ArrayList <String>();
        if (nums.size()>=r) {
        j=0;
        for (int add = 0; add < btnEmpty.length-1; add+=r) {
        j = 0;
        for (int i = 1; i < (r+1); i ++) {
        int ind = nums.indexOf(i+add);
        //if (ind == -1) {}
        if (ind != -1) {
        if (btnEmpty[nums.get(ind)].getText().equals("X")) {j++;}
        if (j == r) postGameX();
        }
        }
        }
        j=0;
        for (int i = 1; i < (r+1); i ++) {// index out of bounds error
        j = 0;
        for (int add = 0; add < btnEmpty.length-1; add+=r) {
        int ind = nums.indexOf(i+add);
        //if (ind == -1) {}
        if (ind != -1) {
        if (btnEmpty[nums.get(ind)].getText().equals("X")) {j++;}
        if (j == r) postGameX();
        }
        }
        }
        j = 0;
        for (int i = 1; i < btnEmpty.length; i+=(r+1)) {
        int ind = nums.indexOf(i);
        if (ind != -1) {
        if (btnEmpty[nums.get(ind)].getText().equals("X")) {j++;}
        if (j == r) postGameX();
        }
        }
        j = 0;
        for (int i = r; i < ((r)*(r-1) + 2); i+=(r-1)) {
        int ind = nums.indexOf(i);
        if (ind != -1) {
        if (btnEmpty[nums.get(ind)].getText().equals("X")) {j++;}
        if (j == r) postGameX();
        }
        }

        j=0;
        for (int add = 0; add < btnEmpty.length-1; add+=r) {
        j = 0;
        for (int i = 1; i < (r+1); i ++) {
        int ind = nums.indexOf(i+add);
        //if (ind == -1) {}
        if (ind != -1) {
        if (btnEmpty[nums.get(ind)].getText().equals("O")) {j++;}
        if (j == r) postGameO();
        }
        }
        }
        j=0;
        for (int i = 1; i < (r+1); i ++) {// index out of bounds error
        j = 0;
        for (int add = 0; add < btnEmpty.length-1; add+=r) {
        int ind = nums.indexOf(i+add);
        //if (ind == -1) {}
        if (ind != -1) {
        if (btnEmpty[nums.get(ind)].getText().equals("O")) {j++;}
        if (j == r) postGameO();
        }
        }
        }
        j = 0;
        for (int i = 1; i < btnEmpty.length; i+=(r+1)) {
        int ind = nums.indexOf(i);
        if (ind != -1) {
        if (btnEmpty[nums.get(ind)].getText().equals("O")) {j++;}
        if (j == r) postGameO();
        }
        }
        j = 0;
        for (int i = r; i < ((r)*(r-1) + 2); i+=(r-1)) {
        int ind = nums.indexOf(i);
        if (ind != -1) {
        if (btnEmpty[nums.get(ind)].getText().equals("O")) {j++;}
        if (j == r) postGameO();
        }
        }

        }*/
        

    }
    private void postGameX() {
        int option = JOptionPane.showConfirmDialog(null, "Game over: Player X wins! New Game?"
            , "New Game?" ,JOptionPane.YES_NO_OPTION);
        if(option == JOptionPane.YES_OPTION)    
        {
            //RedrawGameBoard();
            restartGame();
        }
        else
        {
            System.exit(0);
        }
    }

    private void postGameO() {
        int option = JOptionPane.showConfirmDialog(null, "Game over: Player O wins! New Game?"
            , "New Game?" ,JOptionPane.YES_NO_OPTION);
        if(option == JOptionPane.YES_OPTION)    
        {
            //RedrawGameBoard();
            restartGame();
        }
        else
        {
            System.exit(0);
        }
    }

    private void postGameTie() {
        int option = JOptionPane.showConfirmDialog(null, "Game over: Tie Game! New Game?"
            , "New Game?" ,JOptionPane.YES_NO_OPTION);
        if(option == JOptionPane.YES_OPTION)    
        {
            //RedrawGameBoard();
            restartGame();
        }
        else
        {
            System.exit(0);
        }
    }

    public int createArraySize() {
        String inputValue = JOptionPane.showInputDialog("How many rows?");
        int n = Integer.parseInt(inputValue);
        return n;
    }

    public void restartGame() {
        new GUI();
    }
}   
