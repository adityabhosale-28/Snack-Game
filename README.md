Snake Game (Java Swing)

A classic Snake game built in Java using Swing, featuring customizable window dimensions, progressive difficulty, timed apple spawning, and persistent per-player high score tracking.

Features
Customizable game window – Set your own width and height at startup (minimum 200x200), or use the default 600x600.
Progressive difficulty – The snake speeds up gradually as you eat more apples.
Timed apple spawning – Apples disappear after a set duration if not eaten, and a new one spawns automatically. The apple lifetime increases slightly with each apple eaten.
Screen wraparound – The snake passes through walls and reappears on the opposite side instead of dying on contact.
Self-collision detection – The game ends if the snake runs into its own body.
Persistent high scores – Player name, score, and game number are saved to a local text file, with the all-time high score loaded on startup.
Live score tracking – Current score, high score, and game number are displayed on screen during play.
Restart on demand – Press Enter after Game Over to start a new round instantly.
Controls
Key	Action
↑	Move Up
↓	Move Down
←	Move Left
→	Move Right
Enter	Restart after Game Over
How to Run
Make sure you have Java (JDK 8 or later) installed.
Compile the game:
   javac SnakeGame.java
Run it:
   java SnakeGame
Enter your preferred width and height when prompted, or leave blank for the default 600x600.
High Score Storage

High scores are saved locally in a text file (HighScore.txt), recording the player's name, score, and game number for every new high score achieved.

Note: The current version uses a hardcoded file path for saving scores. Update the FILE_PATH variable in SnakeGame.java to a location on your own system before running, or modify it to save relative to the project directory for portability.

Built With
Java
Java Swing (GUI)
Java AWT (Graphics & Event handling)
