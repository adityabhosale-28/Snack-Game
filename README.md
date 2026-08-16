🐍 Snake Game (Java Swing)

A classic Snake Game built in Java using Swing, featuring customizable window dimensions, progressive difficulty, timed apple spawning, and persistent per-player high score tracking.

🚀 Features
🖥️ Customizable game window – Set your own width and height at startup (minimum 200x200), or use the default 600x600.
⚡ Progressive difficulty – The snake speeds up gradually as you eat more apples.
🍎 Timed apple spawning – Apples disappear after a set duration if not eaten, and a new one spawns automatically. Apple lifetime increases slightly with each apple eaten.
🔁 Screen wraparound – The snake passes through walls and reappears on the opposite side instead of dying on contact.
💥 Self-collision detection – The game ends if the snake runs into its own body.
🏆 Persistent high scores – Player name, score, and game number are saved to a local text file, with the all-time high score loaded on startup.
📊 Live score tracking – Current score, high score, and game number are displayed on screen during play.
🔄 Restart on demand – Press Enter after Game Over to start a new round instantly.
🎮 Controls
Key	Action
↑	Move Up
↓	Move Down
←	Move Left
→	Move Right
Enter	Restart after Game Over
🚀 Getting Started

Follow the steps below to set up and run the project locally.

1️⃣ Requirements

Make sure Java (JDK 8 or later) is installed on your system.

2️⃣ Compile the Game

Open a terminal in the project directory and run:

bash
javac SnakeGame.java
3️⃣ Run the Game
bash
java SnakeGame

Enter your preferred width and height when prompted, or leave the fields blank to use the default 600x600.

💾 High Score Storage

High scores are saved locally in a text file (HighScore.txt), recording the player's name, score, and game number for every new high score achieved. The all-time high score is automatically loaded the next time the game runs.

⚠️ Note: The current version uses a hardcoded file path for saving scores. Update the FILE_PATH variable in SnakeGame.java to a location on your own system before running, or modify it to save relative to the project directory for better portability.

🛠️ Technologies Used
Language: Java
GUI: Java Swing
Graphics & Events: Java AWT
Data Storage: Local file I/O (.txt)
👨‍💻 Project

Snake Game (Java Swing) A Java desktop application project demonstrating practical implementation of GUI development, event handling, game logic, and file-based data persistence.

⭐ If you find this project useful, consider giving the repository a star!
