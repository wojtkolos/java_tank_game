import java.awt.*;

public class BulletsAction
{
    PlayerBullet player1Bullet;
    PlayerBullet player2Bullet;

    BulletsAction()
    {
        player1Bullet = null;
        PlayerBullet player2Bullet= null;
    }

    public void bulletsUpdate(Graphics g, brick br, Player player1, Player player2)
    {
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

    public void bulletShoot(int a, Player player) {
        if (a == 1) {
            if (player.isPlayerUp()) {
                player1Bullet = new PlayerBullet(player.getPlayerX() + 20, player.getPlayerY(), Color.green);
            } else if (player.isPlayerDown()) {
                player1Bullet = new PlayerBullet(player.getPlayerX() + 20, player.getPlayerY() + 40, Color.green);
            } else if (player.isPlayerRight()) {
                player1Bullet = new PlayerBullet(player.getPlayerX() + 40, player.getPlayerY() + 20, Color.green);
            } else if (player.isPlayerLeft()) {
                player1Bullet = new PlayerBullet(player.getPlayerX(), player.getPlayerY() + 20, Color.green);
            }
        }
        else if(a == 2) {
            if (player.isPlayerUp()) {
                player2Bullet = new PlayerBullet(player.getPlayerX() + 20, player.getPlayerY(), Color.yellow);
            } else if (player.isPlayerDown()) {
                player2Bullet = new PlayerBullet(player.getPlayerX() + 20, player.getPlayerY() + 40, Color.yellow);
            } else if (player.isPlayerRight()) {
                player2Bullet = new PlayerBullet(player.getPlayerX() + 40, player.getPlayerY() + 20, Color.yellow);
            } else if (player.isPlayerLeft()) {
                player2Bullet = new PlayerBullet(player.getPlayerX(), player.getPlayerY() + 20, Color.yellow);
            }
        }
    }
}
