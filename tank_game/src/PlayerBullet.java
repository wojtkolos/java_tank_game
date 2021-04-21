import java.awt.*;

public class PlayerBullet {

        private double x, y;
        private Color color;

        public PlayerBullet(double x, double y, Color color)
        {
            this.x = x;
            this.y = y;
            this.color = color;
        }

        public void move(String face)
        {
            if(face.equals("right"))
                x += 5;
            else if(face.equals("left"))
                x -= 5;
            else if(face.equals("up"))
                y -= 5;
            else
                y +=5;
        }

        public void draw(Graphics g)
        {
            g.setColor(color);
            g.fillOval((int) x, (int) y, 10, 10);
        }

        public int getX()
        {
            return (int)x;
        }
        public int getY()
        {
            return (int)y;
        }

}
