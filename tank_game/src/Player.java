import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {

    private ImageIcon playerImageIcon;
    private int playerX;
    private int playerY;
    private boolean playerRight;
    private boolean playerLeft;
    private boolean playerDown;
    private boolean playerUp;
    private int playerScore;
    private int playerLives;
    private boolean playerShoot;
    private String bulletShootDir;

    private String textureUp;
    private String textureDown;
    private String textureRight;
    private String textureLeft;

    private int keyUp;
    private int keyDown;
    private int keyRight;
    private int keyLeft;
    private int keyShoot;

    public Player(int playerX, int playerY, int playerLives,
                  String textureUp, String textureDown, String textureRight, String textureLeft,
                  int keyUp, int keyDown, int keyRight, int keyLeft, int keyShoot)
    {
        this.playerX = playerX;
        this.playerY = playerY;
        this.playerRight = false;
        this.playerLeft = false;
        this.playerDown = false;
        this.playerUp = true;
        this.playerScore = 0;
        this.playerLives = playerLives;
        this.playerShoot = false;;
        this.bulletShootDir = "";
        //textures
        this.textureUp = textureUp;
        this.textureDown = textureDown;
        this.textureRight = textureRight;
        this.textureLeft = textureLeft;
        //keys
        this.keyUp = keyUp;
        this.keyDown = keyDown;
        this.keyRight = keyRight;
        this.keyLeft = keyLeft;
        this.keyShoot = keyShoot;
    }

    public ImageIcon getPlayerImageIcon() {
        return playerImageIcon;
    }

    public void setPlayerImageIcon(ImageIcon playerImageIcon) {
        this.playerImageIcon = playerImageIcon;
    }

    public int getPlayerX() {
        return playerX;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    public boolean isPlayerRight() {
        return playerRight;
    }

    public void setPlayerRight(boolean playerRight) {
        this.playerRight = playerRight;
    }

    public boolean isPlayerLeft() {
        return playerLeft;
    }

    public void setPlayerLeft(boolean playerLeft) {
        this.playerLeft = playerLeft;
    }

    public boolean isPlayerDown() {
        return playerDown;
    }

    public void setPlayerDown(boolean playerDown) {
        this.playerDown = playerDown;
    }

    public boolean isPlayerUp() {
        return playerUp;
    }

    public void setPlayerUp(boolean playerUp) {
        this.playerUp = playerUp;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public int getPlayerLives() {
        return playerLives;
    }

    public void setPlayerLives(int playerLives) {
        this.playerLives = playerLives;
    }

    public boolean isPlayerShoot() {
        return playerShoot;
    }

    public void setPlayerShoot(boolean playerShoot) {
        this.playerShoot = playerShoot;
    }

    public String getBulletShootDir() {
        return bulletShootDir;
    }

    public void setBulletShootDir(String bulletShootDir) {
        this.bulletShootDir = bulletShootDir;
    }

    public void resetPlayer(int playerX, int playerY, int playerLives)
    {
        this.playerX = playerX;
        this.playerY = playerY;
        this.playerRight = false;
        this.playerLeft = false;
        this.playerDown = false;
        this.playerUp = true;
        this.playerScore = 0;
        this.playerLives = playerLives;
    }

    public void playerDraw()
    {
        // draw player 1
        if(playerUp)
            playerImageIcon=new ImageIcon(textureUp);
        else if(playerDown)
            playerImageIcon=new ImageIcon(textureDown);
        else if(playerRight)
            playerImageIcon=new ImageIcon(textureRight);
        else if(playerLeft)
            playerImageIcon=new ImageIcon(textureLeft);
    }

    public void setPlayerDirection(KeyEvent e)
    {
        if(e.getKeyCode()== keyUp)
        {
            playerRight = false;
            playerLeft = false;
            playerDown = false;
            playerUp = true;

            if(!(playerY < 10))
                playerY-=10;

        }
        if(e.getKeyCode()== keyLeft)
        {
            playerRight = false;
            playerLeft = true;
            playerDown = false;
            playerUp = false;

            if(!(playerX < 10))
                playerX-=10;
        }
        if(e.getKeyCode()== keyDown)
        {
            playerRight = false;
            playerLeft = false;
            playerDown = true;
            playerUp = false;

            if(!(playerY > 540))
                playerY+=10;
        }
        if(e.getKeyCode()== keyRight)
        {
            playerRight = true;
            playerLeft = false;
            playerDown = false;
            playerUp = false;

            if(!(playerX > 590))
                playerX+=10;
        }
        if (e.getKeyCode() == keyShoot) {
            if (!playerShoot) {
                if (playerUp) {
                    player2Bullet = new Player2Bullet(playerX + 20, playerY);
                } else if (player2down) {
                    player2Bullet = new Player2Bullet(playerX + 20, playerY + 40);
                } else if (player2right) {
                    player2Bullet = new Player2Bullet(playerX + 40, playerY + 20);
                } else if (player2left) {
                    player2Bullet = new Player2Bullet(playerX, playerY + 20);
                }

                player2Shoot = true;
            }
        }
    }

}
