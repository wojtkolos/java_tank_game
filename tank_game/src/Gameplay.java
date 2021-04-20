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
	private brick br;

	private Player player1 = new Player(200, 550, 5,
			"Images/player1_tank_up.png", "Images/player1_tank_down.png",
			"Images/player1_tank_right.png", "Images/player1_tank_left.png",
			KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_U);
	private Player player2 = new Player(400, 550, 5,
			"Images/player2_tank_up.png", "Images/player2_tank_down.png",
			"Images/player2_tank_right.png", "Images/player2_tank_left.png",
			KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT, KeyEvent.VK_M);
	
	private Timer timer;
	private int delay=8;
	
	private Player1Listener player1Listener;
	private Player2Listener player2Listener;
	
	private Player1Bullet player1Bullet = null;
	private Player2Bullet player2Bullet = null;
	
	private boolean play = true;
	
	public Gameplay()
	{				
		br = new brick();
		player1Listener = new Player1Listener();
		player2Listener = new Player2Listener();
		setFocusable(true);
		//addKeyListener(this);
		addKeyListener(player1Listener);
		addKeyListener(player2Listener);
		setFocusTraversalKeysEnabled(false);
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
			
			if(player1Bullet != null && player1.isPlayerShoot())
			{
				if(player1.getBulletShootDir().equals(""))
				{
					if(player1.isPlayerUp())
					{
						player1.setBulletShootDir("up");
					}
					else if(player1.isPlayerDown())
					{
						player1.setBulletShootDir("down");
					}
					else if(player1.isPlayerRight())
					{
						player1.setBulletShootDir("right");
					}
					else if(player1.isPlayerLeft())
					{
						player1.setBulletShootDir("left");
					}
				}
				else
				{
					player1Bullet.move(player1.getBulletShootDir());
					player1Bullet.draw(g);
				}
				
				
				if(new Rectangle(player1Bullet.getX(), player1Bullet.getY(), 10, 10)
				.intersects(new Rectangle(player2.getPlayerX(), player2.getPlayerY(), 50, 50)))
				{
					player1.setPlayerScore( player1.getPlayerScore() + 10);
					player2.setPlayerLives(player2.getPlayerLives() - 1);
					player1Bullet = null;
					player1.setPlayerShoot(false);
					player1.setBulletShootDir("");
				}
				
				if(br.checkCollision(player1Bullet.getX(), player1Bullet.getY())
						|| br.checkSolidCollision(player1Bullet.getX(), player1Bullet.getY()))
				{
					player1Bullet = null;
					player1.setPlayerShoot(false);
					player1.setBulletShootDir("");
				}
	
				if(player1Bullet.getY() < 1 
						|| player1Bullet.getY() > 580
						|| player1Bullet.getX() < 1
						|| player1Bullet.getX() > 630)
				{
					player1Bullet = null;
					player1.setPlayerShoot(false);
					player1.setBulletShootDir("");
				}
			}
			
			if(player2Bullet != null && player2.isPlayerShoot())
			{
				if(player2.getBulletShootDir().equals(""))
				{
					if(player2.isPlayerUp())
					{
						player2.setBulletShootDir("up");
					}
					else if(player2.isPlayerDown())
					{
						player2.setBulletShootDir("down");
					}
					else if(player2.isPlayerRight())
					{
						player2.setBulletShootDir("right");
					}
					else if(player2.isPlayerLeft())
					{
						player2.setBulletShootDir("left");
					}
				}
				else
				{
					player2Bullet.move(player2.getBulletShootDir());
					player2Bullet.draw(g);
				}
				
				
				if(new Rectangle(player2Bullet.getX(), player2Bullet.getY(), 10, 10)
				.intersects(new Rectangle(player1.getPlayerX(), player1.getPlayerY(), 50, 50)))
				{
					player2.setPlayerScore( player2.getPlayerScore() + 10);
					player1.setPlayerLives(player1.getPlayerLives() - 1);
					player2Bullet = null;
					player2.setPlayerShoot(false);
					player2.setBulletShootDir("");
				}
				
				if(br.checkCollision(player2Bullet.getX(), player2Bullet.getY())
						|| br.checkSolidCollision(player2Bullet.getX(), player2Bullet.getY()))
				{
					player2Bullet = null;
					player2.setPlayerShoot(false);
					player2.setBulletShootDir("");
				}
				
				if(player2Bullet.getY() < 1 
						|| player2Bullet.getY() > 580
						|| player2Bullet.getX() < 1
						|| player2Bullet.getX() > 630)
				{
					player2Bullet = null;
					player2.setPlayerShoot(false);
					player2.setBulletShootDir("");
				}
			}
		}
	
		
		// the scores 		
		g.setColor(Color.white);
		g.setFont(new Font("serif",Font.BOLD, 15));
		g.drawString("Scores", 700,30);
		g.drawString("Player 1:  "+player1.getPlayerScore(), 670,60);
		g.drawString("Player 2:  "+player2.getPlayerScore(), 670,90);
		
		g.drawString("Lives", 700,150);
		g.drawString("Player 1:  "+player1.getPlayerLives(), 670,180);
		g.drawString("Player 2:  "+player2.getPlayerLives(), 670,210);
		
		if(player1.getPlayerLives() == 0)
		{
			g.setColor(Color.white);
			g.setFont(new Font("serif",Font.BOLD, 60));
			g.drawString("Game Over", 200,300);
			g.drawString("Player 2 Won", 180,380);
			play = false;
			g.setColor(Color.white);
			g.setFont(new Font("serif",Font.BOLD, 30));
			g.drawString("(Space to Restart)", 230,430);
		}
		else if(player1.getPlayerLives() == 0)
		{
			g.setColor(Color.white);
			g.setFont(new Font("serif",Font.BOLD, 60));
			g.drawString("Game Over", 200,300);
			g.drawString("Player 1 Won", 180,380);
			g.setColor(Color.white);
			g.setFont(new Font("serif",Font.BOLD, 30));
			g.drawString("(Space to Restart)", 230,430);
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
			if(e.getKeyCode()== KeyEvent.VK_SPACE && (player1.getPlayerLives() == 0 || player1.getPlayerLives() == 0))
			{
				br = new brick();
				player1.resetPlayer(200, 550, 5);
				player1.resetPlayer(400, 550, 5);

				play = true;
				repaint();
			}
			if(e.getKeyCode()== KeyEvent.VK_U)
			{
				if(!player1Shoot)
				{
					if(player1up)
					{					
						player1Bullet = new Player1Bullet(player1X + 20, player1Y);
					}
					else if(player1down)
					{					
						player1Bullet = new Player1Bullet(player1X + 20, player1Y + 40);
					}
					else if(player1right)
					{				
						player1Bullet = new Player1Bullet(player1X + 40, player1Y + 20);
					}
					else if(player1left)
					{			
						player1Bullet = new Player1Bullet(player1X, player1Y + 20);
					}
					
					player1Shoot = true;
				}
			}
			player1.setPlayerDirection(e);
		}
	}

	public class Player2Listener implements KeyListener {


		public void keyTyped(KeyEvent e) {
		}

		public void keyReleased(KeyEvent e) {
		}

		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_M) {
				if (!player2Shoot) {
					if (player2up) {
						player2Bullet = new Player2Bullet(player2X + 20, player2Y);
					} else if (player2down) {
						player2Bullet = new Player2Bullet(player2X + 20, player2Y + 40);
					} else if (player2right) {
						player2Bullet = new Player2Bullet(player2X + 40, player2Y + 20);
					} else if (player2left) {
						player2Bullet = new Player2Bullet(player2X, player2Y + 20);
					}

					player2Shoot = true;
				}
			}
			player2.setPlayerDirection(e);

		}
	}
}
