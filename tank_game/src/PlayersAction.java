import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayersAction
{

    public void setPlayerDirection(Player player, KeyEvent e)
    {
        if(e.getKeyCode()== player.getKeyUp())
        {
            player.setPlayerDirection(false, false, false, true);

            if(!(player.getPlayerY() < 10))
                player.setPlayerY(player.getPlayerY() - 10);



        }
        if(e.getKeyCode()== player.getKeyLeft())
        {
            player.setPlayerDirection(false, true, false, false);

            if(!(player.getPlayerX() < 10))
                player.setPlayerX(player.getPlayerX() - 10);
        }
        if(e.getKeyCode()== player.getKeyDown())
        {
            player.setPlayerDirection(false, false, true, false);

            if(!(player.getPlayerY() > 540))
                player.setPlayerY(player.getPlayerY() + 10);
        }
        if(e.getKeyCode()== player.getKeyRight()) {
            player.setPlayerDirection(true, false, false, false);

            if (!(player.getPlayerX() > 590))
                player.setPlayerX(player.getPlayerX() + 10);
        }
    }
}
