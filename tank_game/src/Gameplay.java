import java.util.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.*;

import javax.swing.*;
import javax.swing.Timer;

public class Gameplay
		extends JPanel
		implements ActionListener
{
	//bricks
	private brick br;

	//players
	private Player player1;
	private Player player2;
	//timer
	private Timer timer;
	private int delay=8;
	//keys
	private Player1Listener player1Listener;
	private Player2Listener player2Listener;
	//actions
	BulletsAction bulletsAction;
	PlayersAction playersAction;
	//textScore
	TextScore textScore;
	
	private boolean play = true;
	
	public Gameplay()
	{				
		br = new brick();

		player1 = new Player(200, 550, 5,
				"Images/player1_tank_up.png", "Images/player1_tank_down.png",
				"Images/player1_tank_right.png", "Images/player1_tank_left.png",
				KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_A);
		player2 = new Player(400, 550, 5,
				"Images/player2_tank_up.png", "Images/player2_tank_down.png",
				"Images/player2_tank_right.png", "Images/player2_tank_left.png",
				KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT);

		bulletsAction = new BulletsAction();
		playersAction = new PlayersAction();

		player1Listener = new Player1Listener();
		player2Listener = new Player2Listener();
		setFocusable(true);

		addKeyListener(player1Listener);
		addKeyListener(player2Listener);
		setFocusTraversalKeysEnabled(false);

		textScore = new TextScore();

        timer=new Timer(delay,this);
		timer.start();
	}
	
	public void paint(Graphics g)
	{    		
		// play background
		g.setColor(Color.black);
		g.fillRect(0, 0, 650, 600);
		
		// right side background
		g.setColor(Color.DARK_GRAY);
		g.fillRect(660, 0, 140, 600);
		
		// draw solid bricks
		br.drawSolids(this, g);
		
		// draw Breakable bricks	
		br.draw(this, g);
		
		if(play)
		{
			player1.playerDraw();
			player1.getPlayerImageIcon().paintIcon(this, g, player1.getPlayerX(), player1.getPlayerY());

			player2.playerDraw();
			player2.getPlayerImageIcon().paintIcon(this, g, player2.getPlayerX(), player2.getPlayerY());

			bulletsAction.bulletsUpdate(g, br, player1, player2);

		}
	
		
		// the scores 		
		textScore.updateScore(g, player1, player2);
		
		if(player1.getPlayerLives() == 0)
		{
			textScore.playerWon(g, 2);
			play = false;
		}
		else if(player2.getPlayerLives() == 0)
		{
			textScore.playerWon(g, 1);
			play = false;
		}
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
	
		repaint();
	}

	private class Player1Listener implements KeyListener
	{
		public void keyTyped(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {}		
		public void keyPressed(KeyEvent e) {	
			if(e.getKeyCode()== KeyEvent.VK_SPACE && (player1.getPlayerLives() == 0 || player2.getPlayerLives() == 0))
			{
				br = new brick();
				player1.resetPlayer(200, 550, 5);
				player2.resetPlayer(400, 550, 5);

				play = true;
				repaint();
			}
			if(e.getKeyCode()== KeyEvent.VK_U)
			{
				if(!player1.isPlayerShoot())
				{
					bulletsAction.bulletShoot(1, player1);
					player1.setPlayerShoot(true);
				}
			}
			playersAction.setPlayerDirection(player1, e);
		}
	}

	public class Player2Listener implements KeyListener {


		public void keyTyped(KeyEvent e) {
		}

		public void keyReleased(KeyEvent e) {
		}

		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_M) {
				if(!player2.isPlayerShoot())
				{
					bulletsAction.bulletShoot(2, player2);
					player2.setPlayerShoot(true);
				}
			}
			playersAction.setPlayerDirection(player2, e);

		}
	}
}
