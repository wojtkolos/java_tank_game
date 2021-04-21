import java.awt.*;

public class TextScore {

        public void playerWon(Graphics g, int playerId)
        {
            g.setColor(Color.white);
            g.setFont(new Font("serif",Font.BOLD, 60));
            g.drawString("Game Over", 200,300);
            g.drawString((playerId == 1) ? "Player 1 Won" : "Player 2 Won", 180,380);
            g.setColor(Color.white);
            g.setFont(new Font("serif",Font.BOLD, 30));
            g.drawString("(Space to Restart)", 230,430);
        }
        public void updateScore(Graphics g, Player player1, Player player2)
        {
            // the scores
            g.setColor(Color.white);
            g.setFont(new Font("serif",Font.BOLD, 15));
            g.drawString("Scores", 700,30);
            g.drawString("Player 1:  "+player1.getPlayerScore(), 670,60);
            g.drawString("Player 2:  "+player2.getPlayerScore(), 670,90);

            g.drawString("Lives", 700,150);
            g.drawString("Player 1:  "+player1.getPlayerLives(), 670,180);
            g.drawString("Player 2:  "+player2.getPlayerLives(), 670,210);
        }
}
