package snake;

import com.wizylab.duck2d.Window;
import com.wizylab.duck2d.*;
import snake.objects.Apple;
import snake.objects.Snake;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Run implements View {
    private static final int[] LENGTHS = {5, 15, 25, 35, 50, 100, 200, 230, 250, 1000};
    private static final int[] SPEEDS = {200, 100, 66, 40, 35, 28, 22, 20, 10, 5};
    public static final int SCALE = 20;
    Apple apple = new Apple();
    Snake s = new Snake(5, 6, 5, 5);
    //Scanner sc = new Scanner(System.in);
    Color background = Color.BLACK;
    long time = 0;

    public static void main(String[] args) {
        Game.start(new Run());
        Window.instance().setIconImage(Drawable.get("snakeico"));
        Window.instance().setName("snake");
    }

    @Override
    public void onShow() {

    }

    @Override
    public void onTimer(long t) {

        if (Keyboard.onKey(KeyEvent.VK_ESCAPE)) System.exit(0);
        if (Keyboard.onKey(KeyEvent.VK_UP) && (s.direction != 2)) s.direction = 0;
        if (Keyboard.onKey(KeyEvent.VK_DOWN) && (s.direction != 0)) s.direction = 2;
        if (Keyboard.onKey(KeyEvent.VK_RIGHT) && (s.direction != 3)) s.direction = 1;
        if (Keyboard.onKey(KeyEvent.VK_LEFT) && (s.direction != 1)) s.direction = 3;


        int speed = 333;
        for (int i = 0; i < LENGTHS.length; i++)
            if (s.length >= LENGTHS[i]) speed = SPEEDS[i];

        time += t;
        if (time < speed) return;
        s.move();
        for (int i = 0; i < s.length; i++) {
            if (s.sX[i] != apple.posX || s.sY[i] != apple.posY) continue;
            apple.setRandomPosition();
            if (i == 0) s.inc();
            break;
        }
        time = 0;
    }

    @Override
    public void onDraw(Graph g) {
        g.setBackground(background);
        //g.putImage("1", 0, -100, 800);
        if (s.length >= 3) g.setBackground(Color.BLACK);

        g.setColor(new Color(20, 20, 20, 255));
        for (int i = 0; i < 40; i++)
            g.line(i * 20, 0, i * 20, 600);
        for (int i = 0; i < 30; i++)
            g.line(0, i * 20, 800, i * 20);

        apple.draw(g);
        s.draw(g);

        g.setColor(Color.YELLOW);
        g.setTextStyle( 4, 1, 20);//4
        g.ctext(0, 0, 800, 100, "ОЧКИ: " + s.length);
        g.setTextStyle( 1, 1, 15);
        g.ctext(450, 0, 80, 30, "УПРАВЛЕНИЕ: → ↓ ← ↑ esc (→/← + ↓/↑ быстрый разворот)");
    }
}