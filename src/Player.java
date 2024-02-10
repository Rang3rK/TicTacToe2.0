import java.util.Scanner;
public class Player {
    Scanner myObj = new Scanner(System.in);
    public static int no_of_players;
    /**player array*/
    public static Player[] players;
    private static int num_winning_row;
    private char player_icon;


    //function to ask each player's character choice
    public void player_char()
    {
        System.out.print("Please enter winning row (3-" + (no_of_players+1) + "): ");

        while(!myObj.hasNextInt())
        {
            System.out.print("Invalid. Choose from (3-" + (no_of_players+1) + "): ");
            myObj.next();
        }

        num_winning_row = myObj.nextInt();
        //Restricting the choice
        while((num_winning_row > no_of_players + 1) || (num_winning_row < 3) )
        {
            System.out.print("Invalid. Choose from (3-" + (no_of_players+1) + "): ");
            num_winning_row = myObj.nextInt();
        }

        //iterate player array, asking each player to select a character of choice
        //if it is taken, ask user to enter again.
        for(int i=0; i< no_of_players; i++)
        {
            System.out.print("Enter player " + i + "'s character: ");
            players[i].player_icon = myObj.next().charAt(0);

            for(int j=0; j< i; j++)
            {
                if (players[i].player_icon == Player.players[j].player_icon)
                {
                    System.out.println("Character taken. Try again.");
                    i--;
                    break;
                }
            }
        }
    }

    //accessor, getter method
    public static int get_no_of_players()
    {
        return no_of_players;
    }

    public static int get_num_winning_rows()
    {
        return num_winning_row;
    }

    public char get_player_char()
    {
        return player_icon;
    }
}


