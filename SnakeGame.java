import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Random;
import javax.swing.*;

public class SnakeGame extends JPanel implements ActionListener {

    private int WIDTH = 600;
    private int HEIGHT = 600;
    private final int UNIT_SIZE = 50;
    private int GAME_UNITS;
    private int DELAY = 200;
    private final int MIN_DELAY = 50;

    private int[] x;
    private int[] y;

    private int bodyParts = 6;
    private int applesEaten;
    private int appleX;
    private int appleY;

    private int highScore = 0;
    private int gamePlayed = 0;

    private boolean appleVisible = false;
    private Timer appleTimer;
    private int appleLifetime = 3000;

    private char direction = 'R';
    private boolean running = false;
    private Timer timer;
    private Random random;

    private final String FILE_PATH = "C:\\Users\\Aditya\\Desktop\\Java Project\\HighScore.txt";

    public SnakeGame() {

        try {
            String widthInput = JOptionPane.showInputDialog(null,
                    "Default Width = 600\nEnter new Width:");
            String heightInput = JOptionPane.showInputDialog(null,
                    "Default Height = 600\nEnter new Height:");

            if (widthInput != null && !widthInput.isEmpty()) {
                WIDTH = Math.max(200, Integer.parseInt(widthInput));
            }
            if (heightInput != null && !heightInput.isEmpty()) {
                HEIGHT = Math.max(200, Integer.parseInt(heightInput));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid input! Using default 600x600.");
            WIDTH = 600;
            HEIGHT = 600;
        }

        GAME_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
        x = new int[GAME_UNITS];
        y = new int[GAME_UNITS];

        random = new Random();

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());

        loadData();
        startGame();
    }

    // ---------- FILE HANDLING ----------
    private void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Game Played")) {
                    gamePlayed = Integer.parseInt(line.split("=")[1].trim());
                } else if (line.startsWith("Highest Score")) {
                    highScore = Integer.parseInt(line.split("=")[1].trim());
                }
            }
        } catch (Exception e) {
            highScore = 0;
            gamePlayed = 0;
        }
    }

    // 🔥 MODIFIED SAVE METHOD (APPEND + USERNAME)
    private void saveData(String playerName, int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {

            writer.newLine();
            writer.write("--- New Record ---");
            writer.newLine();
            writer.write("Name: " + playerName);
            writer.newLine();
            writer.write("Score: " + score);
            writer.newLine();
            writer.write("Game No: " + gamePlayed);
            writer.newLine();
            writer.write("------------------");
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Error saving file");
        }
    }

    // ---------- GAME START ----------
    public void startGame() {
        bodyParts = 6;
        applesEaten = 0;
        direction = 'R';
        DELAY = 200;
        appleLifetime = 3000;

        for (int i = 0; i < x.length; i++) {
            x[i] = 0;
            y[i] = 0;
        }

        newApple();
        running = true;

        if (timer != null) timer.stop();
        timer = new Timer(DELAY, this);
        timer.start();
    }

    // ---------- APPLE ----------
    public void newApple() {
        appleX = random.nextInt(WIDTH / UNIT_SIZE) * UNIT_SIZE;
        appleY = random.nextInt(HEIGHT / UNIT_SIZE) * UNIT_SIZE;
        appleVisible = true;

        if (appleTimer != null && appleTimer.isRunning()) {
            appleTimer.stop();
        }

        appleTimer = new Timer(appleLifetime, e -> {
            appleVisible = false;
            newApple();
            repaint();
        });
        appleTimer.setRepeats(false);
        appleTimer.start();
    }

    // ---------- DRAW ----------
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {

            if (appleVisible) {
                g.setColor(Color.red);
                g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
            }

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) g.setColor(Color.blue);
                else g.setColor(new Color(45, 180, 0));
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }

            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Score: " + applesEaten, 10, 25);
            g.drawString("High Score: " + highScore, WIDTH - 180, 25);
            g.drawString("Game No: " + (gamePlayed + 1), WIDTH / 2 - 60, 25);

        } else {
            gameOver(g);
        }
    }

    // ---------- MOVE ----------
    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U' -> y[0] -= UNIT_SIZE;
            case 'D' -> y[0] += UNIT_SIZE;
            case 'L' -> x[0] -= UNIT_SIZE;
            case 'R' -> x[0] += UNIT_SIZE;
        }
    }

    // ---------- CHECK APPLE ----------
    public void checkApple() {
        if (appleVisible && x[0] == appleX && y[0] == appleY) {
            bodyParts++;
            applesEaten++;
            appleVisible = false;

            appleLifetime += 200;

            if (DELAY > MIN_DELAY) {
                DELAY = Math.max((int)(DELAY * 0.95), MIN_DELAY);
                timer.setDelay(DELAY);
            }

            if (appleTimer != null) appleTimer.stop();
            newApple();
        }
    }

    // ---------- COLLISION ----------
    public void checkCollisions() {
        for (int i = bodyParts; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
            }
        }

        if (x[0] < 0) x[0] = WIDTH - UNIT_SIZE;
        else if (x[0] >= WIDTH) x[0] = 0;

        if (y[0] < 0) y[0] = HEIGHT - UNIT_SIZE;
        else if (y[0] >= HEIGHT) y[0] = 0;

        if (!running) {
            gamePlayed++;

            if (applesEaten > highScore) {
                int choice = JOptionPane.showConfirmDialog(null,
                        "New High Score: " + applesEaten + "\nSave it?",
                        "High Score", JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {

                    String name = JOptionPane.showInputDialog(null, "Enter Your Name:");

                    if (name != null && !name.trim().isEmpty()) {
                        highScore = applesEaten;
                        saveData(name, applesEaten);
                    } else {
                        JOptionPane.showMessageDialog(null, "Name cannot be empty!");
                    }
                }
            }

            timer.stop();
        }
    }

    // ---------- GAME OVER ----------
    public void gameOver(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString("Game Over", WIDTH / 2 - 120, HEIGHT / 2 - 20);

        g.setFont(new Font("Arial", Font.PLAIN, 25));
        g.drawString("Score: " + applesEaten, WIDTH / 2 - 60, HEIGHT / 2 + 20);
        g.drawString("High Score: " + highScore, WIDTH / 2 - 80, HEIGHT / 2 + 50);
        g.drawString("Games Played: " + gamePlayed, WIDTH / 2 - 90, HEIGHT / 2 + 80);

        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Press ENTER to Restart", WIDTH / 2 - 120, HEIGHT / 2 + 120);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    // ---------- KEY CONTROL ----------
    public class MyKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            if (running) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> { if (direction != 'R') direction = 'L'; }
                    case KeyEvent.VK_RIGHT -> { if (direction != 'L') direction = 'R'; }
                    case KeyEvent.VK_UP -> { if (direction != 'D') direction = 'U'; }
                    case KeyEvent.VK_DOWN -> { if (direction != 'U') direction = 'D'; }
                }
            } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                startGame();
            }
        }
    }

    // ---------- MAIN ----------
    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game Final Version");
        SnakeGame game = new SnakeGame();

        frame.add(game);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}