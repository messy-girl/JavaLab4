import java.awt.*;
import javax.swing.*;


public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> { //Вызывает асинхронное выполнение doRun. run() в потоке диспетчеризации событий AWT. Это произойдет после обработки всех ожидающих событий AWT. Этот метод следует использовать, когда потоку приложения необходимо обновить графический интерфейс
            JFrame frame = new JFrame("Вращающийся отрезок");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            frame.add(new RotatingLinePanel());
            frame.setVisible(true);
        });
    }
}

class RotatingLinePanel extends JPanel {
    private double angle = 0;
    private Timer timer;
    private Color lineColor = Color.RED;

    /**
     * Конструктор панели, инициализирующий таймер и анимацию.
     */
    public RotatingLinePanel() {
        // будем обновлять угол и цвет линии каждые 50 миллисекунд
        timer = new Timer(50, e -> {
            angle += 0.05;
            // Генерируем случайный цвет для линии на каждой итерации
            lineColor = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
            repaint();
        });
        timer.start();
    }


    @Override
    protected void paintComponent(Graphics g) { //Переопределяем метод paintComponent для рисования вращающегося отрезка.
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int x1 = getWidth() / 2;
        int y1 = getHeight() / 2;
        // Вычисляем конечные координаты отрезка с использованием тригонометрических функций и текущего угла
        int x2 = (int) (x1 + 100 * Math.cos(angle));
        int y2 = (int) (y1 + 100 * Math.sin(angle));

        g2d.setColor(lineColor);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(x1, y1, x2, y2);
    }
}