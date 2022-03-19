package snake.objects;

import com.wizylab.duck2d.Graph;

import java.awt.*;

import static snake.Run.SCALE;

public class Snake {
    public int length;
    public int direction;

    public int sX[] = new int[300];
    public int sY[] = new int[300];

    public Snake(int x1, int x2, int y1, int y2) {
        start(x1, x2, y1, y2);
    }

    private void start(int x1, int x2, int y1, int y2) {
        sX[0] = x1;//head
        sX[1] = x2;//body
        sY[0] = y1;//head
        sY[1] = y2;//body
        length = 2;
        direction = 2;
    }

    public void move() {
        for (int l = length - 1; l > 0; l--) {
            sX[l] = sX[l - 1];
            sY[l] = sY[l - 1];
        }
        //up
        if (direction == 0) sY[0]--;
        //down
        if (direction == 2) sY[0]++;
        //right
        if (direction == 1) sX[0]++;
        //left
        if (direction == 3) sX[0]--;

        if (sX[0] < 0 || sX[0] >= 40 || sY[0] < 0 || sY[0] >= 30) {
            start(5, 6, 5, 5);
            return;
        }

        for (int i = 1; i < length; i++) {
            if (sX[0] == sX[i] && sY[0] == sY[i]) {
                start(5, 6, 5, 5);
                return;
            }
        }
    }

    public void inc() {
        if (length + 1 >= sX.length) return;
        length++;
        sX[length - 1] = sX[length - 2];
        sY[length - 1] = sY[length - 2];
    }

    public void draw(Graph g) {
        for (int i = 0; i < length; i++) {
            g.setColor(i == 0 ? Color.YELLOW : Color.GREEN);
            g.fillRect((int) (sX[i] * SCALE + 0.1 * SCALE), (int) (sY[i] * SCALE + 0.1 * SCALE),
                    (int) (sX[i] * SCALE + 0.9 * SCALE), (int) (sY[i] * SCALE + 0.9 * SCALE));
        }
    }
}
