import java.util.InputMismatchException;
import java.util.Scanner;

public class GameLogic {

    Scanner in = new Scanner(System.in);
    private int x_coord, y_coord;
    private int totalMoves = 0;
    private int max_moves =0;

    //bool flag
    boolean winner_found = false;
    /** ask each player to enter coordinates until all the slots are filled.
     * If the slot is taken, ask again
     * Else, place
     * Check winning conditions after each move, incrementing totalMove
     * If a winner is found, return
     * Else, repeat until the board is filled
     * If the board is filled and no more moves available and no winner is found, GAME OVER DRAW!
     * */
    public void place() {
        //maximum moves players can make depending on grid size
        max_moves = Board.getRow() * Board.getCol();

        boolean is_empty = false;

        //while the board is not filled
        while (totalMoves < max_moves) {
            for (int k = 0; k < Player.get_no_of_players(); k++) {
                //user have to re-enter until the chosen coordinate is true (empty).
                do {
                    if (totalMoves < max_moves) {
                        try {
                            System.out.println("Player" + k + "'s turn.");
                            System.out.print("Enter X coordinate (" + Player.players[k].get_player_char() + "): ");
                            x_coord = in.nextInt();

                            System.out.print("Enter Y coordinate (" + Player.players[k].get_player_char() + "): ");
                            y_coord = in.nextInt();

                            if (Board.ttt_board[x_coord][y_coord] != '-') {
                                is_empty = false;
                                System.out.println("Already occupied. Try again.");
                                //break;
                            } else {
                                is_empty = true;
                                Board.ttt_board[x_coord][y_coord] = Player.players[k].get_player_char();
                                totalMoves++;
                                Board.print_board();
                                is_horizontal();
                                is_vertical();
                                is_diagonal();
                                is_antidiagonal();
                                if (winner_found) {
                                    System.out.println("GAME OVER! Player " + k + " (" + Player.players[k].get_player_char() + ") Win!");
                                    return;
                                }
                            }
                        } catch (InputMismatchException err) {
                            System.out.println("Error Catch: Invalid. Enter integer only.");
                            in.nextLine(); //consume input
                            is_empty = false; //flag forced to break the do-while loop
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Error Catch : Invalid. Enter coordinates within the board size.");
                            in.nextLine(); //consume
                            is_empty = false; //flag forced to break the do-while loop
                        }
                    } else {
                        System.out.println("GameOver! Draw!");
                        return;
                    }
                } while (!is_empty);
            }
        }
    }

    /**
     * Check horizontal winning condition
     * iterate through checking each player's character
     * boolean flag will determine whether a winner is found or not
     */
    public void is_horizontal() {
        for (int k = 0; k < Player.get_no_of_players(); k++) {
            for (int i = 0; i < Board.getRow(); i++) {
                for (int j = 0; j < Board.getCol() - Player.get_num_winning_rows() + 1; j++) {
                    boolean horizontal_condition = true;

                    for (int h = 0; h < Player.get_num_winning_rows(); h++) {
                        if (Board.ttt_board[i][j + h] != Player.players[k].get_player_char()) {
                            horizontal_condition = false;
                            break;
                        }
                    }
                    if (horizontal_condition) {
                        winner_found = true;
                        break;
                    }
                }
            }
        }
    }

    /**
     * Check vertical winning condition
     */
    public void is_vertical()
    {
        for (int k = 0; k < Player.get_no_of_players(); k++)
        {
            for (int i = 0; i < Board.getRow() - Player.get_num_winning_rows() + 1; i++)
            {
                for (int j = 0; j < Board.getCol(); j++)
                {
                    boolean vertical_condition = true;

                    for (int h = 0; h < Player.get_num_winning_rows(); h++)
                    {
                        if (Board.ttt_board[i + h][j] != Player.players[k].get_player_char())
                        {
                            vertical_condition = false;
                            break;
                        }
                    }
                    if (vertical_condition) {
                        winner_found = true;
                        break;
                    }
                }
            }
        }
    }

    /**
     * Check diagonal winning condition from top left to bottom right
     */
    public void is_diagonal() {
        for (int k = 0; k < Player.get_no_of_players(); k++) {
            for (int i = 0; i < Board.getRow() - Player.get_num_winning_rows() + 1; i++) {
                for (int j = 0; j < Board.getCol() - Player.get_num_winning_rows() + 1; j++) {
                    boolean diagonal_condition = true;
                    for (int h = 0; h < Player.get_num_winning_rows(); h++) {
                        if (Board.ttt_board[i + h][j + h] != Player.players[k].get_player_char()) {
                            diagonal_condition = false;
                            break;
                        }
                    }

                    if (diagonal_condition) {
                        winner_found = true;
                        return;
                    }
                }
            }
        }
    }

    /**
     * Check anti-diagonal winning condition from top right to bottom left
     */
    public void is_antidiagonal()
    {
        for (int k = 0; k < Player.get_no_of_players(); k++) {

            for (int i = 0; i < Board.getRow() - Player.get_num_winning_rows() + 1; i++)
            {
                for (int j = 0; j < Board.getCol() - Player.get_num_winning_rows() + 1; j++)
                {
                    boolean diagonal_condition = true;
                    for (int h = 0; h < Player.get_num_winning_rows(); h++) {
                        if (Board.ttt_board[i + h][Board.getCol() - 1 - j - h] != Player.players[k].get_player_char()) {
                            diagonal_condition = false;
                            break;
                        }
                    }

                    if (diagonal_condition) {
                        winner_found = true;
                        return;
                    }
                }
            }
        }
    }

    /** If (y/Y) true, restart the game
     *  Any key will exit the game
     * */
    public static boolean restart()
    {
        String str;
        Scanner re = new Scanner(System.in);
        System.out.println("Continue? (y/n): ");
        str = re.next();
        return str.equalsIgnoreCase("y");
    }

}
