public class TicTacToe {
    public static void main (String[] args)
    {
        do {
            Board.customize_board();
            Player p = new Player();
            p.player_char();
            GameLogic g = new GameLogic();
            g.place();
        } while (GameLogic.restart());
    }
}
