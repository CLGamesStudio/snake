package snake.objects;

import com.wizylab.duck2d.Graph;

import java.awt.*;

import static snake.Run.SCALE;

public class Apple {
    public int posY;
    public int posX;

    public Apple() {
        setRandomPosition();
    }

    public Apple(int x, int y) {
        posX = x;
        posY = y;
    }

    public void setRandomPosition() {
        posX = Math.abs((int) (Math.random() * 40));
        posY = Math.abs((int) (Math.random() * 30));
    }

    public void draw(Graph g) {
        g.setColor(Color.red);//отрисовка яблока
        g.fillCircle(posX * SCALE + SCALE / 2, posY * SCALE + SCALE / 2, (SCALE - 8) / 2);
    }
}