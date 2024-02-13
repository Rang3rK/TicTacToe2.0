import java.util.InputMismatchException;
import java.util.Scanner;

public class Board {
    private static int row, col;
    public static char[][] ttt_board;

    static Scanner in = new Scanner(System.in);

    /**Board constructor to build Board object with row and col initialized*/
    public Board(int row, int col) {
        Board.row = row + 1;
        Board.col = col + 1;
        ttt_board = new char[row + 1][col + 1];
    }

    /**Designing board with '-' in two-dimensional array*/
    public void init_board() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                ttt_board[i][j] = '-';
            }
        }
    }

    /**printing the board along with numbered rows and columns*/
    public static void print_board() {
        for (int j = 0; j < col; j++) {
            System.out.print("   " + j + " ");
        }
        System.out.println();

        for (int i = 0; i < row; i++) {
            System.out.print(i);
            for (int j = 0; j < col; j++) {
                System.out.print("| " + ttt_board[i][j] + " |");
            }
            System.out.println();
            System.out.println();
        }
    }

    /** Get number of players, determine the board size and initialize the board object
     * */
    public static void customize_board() {
        try {
            System.out.print("Enter number of players (3-10): ");
            Player.no_of_players = in.nextInt();
            //error handling when the user enters a non-integer

            //Bounds for userInput of number of players
            //error handling when the user enters an integer outside of range
            while ((Player.no_of_players < 3 || Player.no_of_players > 10)) {
                System.out.print("Invalid. Out of Bounds. Choose from (3-10): ");
                Player.no_of_players = in.nextInt();
            }

            //Initialize players
            Player.players = new Player[Player.no_of_players];
            for (int i = 0; i < Player.no_of_players; i++) {
                Player.players[i] = new Player();
            }

            //Initialize the board
            Board t_board = new Board(Board.row = Player.no_of_players, Board.col = Player.no_of_players);
            t_board.init_board();
            //print it after each move
            print_board();
        } catch (InputMismatchException e)  //catch if user input non-integer
        {
            System.out.println("Error Catch : Enter integers only.");
            in.next();
            customize_board(); //recursive call
        }
    }

    //accessors, getter method
    public static int getRow()
    {
        return row;
    }

    //accessors, getter method
    public static int getCol()
    {
        return col;
    }
}

