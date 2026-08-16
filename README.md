# Snake Game (Java Swing)

A classic Snake game implemented in Java using Swing and AWT. It includes customizable window dimensions, progressive difficulty, timed apple spawning, screen wraparound, self-collision detection, and persistent per-player high score tracking via a local text file.

---

## Table of contents

- [Features](#features)
- [Requirements](#requirements)
- [Build & run](#build--run)
- [Configuration](#configuration)
- [Gameplay & controls](#gameplay--controls)
- [High score storage](#high-score-storage)
- [Notes & known issues](#notes--known-issues)
- [Contributing](#contributing)
- [Credits](#credits)

---

## Features

- Customizable window size at startup (default: 600×600; minimum: 200×200).
- Progressive difficulty: the snake speeds up as more apples are eaten.
- Timed apple spawning: apples disappear after a configured lifetime; each eaten apple increases lifetime slightly.
- Screen wraparound: moving through a wall makes the snake reappear on the opposite side.
- Self-collision detection: game over when the snake collides with its body.
- Persistent high scores saved to a local text file (player name, score, and game number).
- Live on-screen display of current score, high score, and game number.
- Restart immediately after Game Over by pressing Enter.

## Requirements

- Java Development Kit (JDK) 8 or later.

## Build & run

1. Open a terminal in the project root (where `SnakeGame.java` is located).

2. Compile:

```bash
javac SnakeGame.java
```

3. Run:

```bash
java SnakeGame
```

You will be prompted to enter a preferred window width and height. Leave blank to use the default 600×600. The program enforces a minimum of 200×200.

## Configuration

- High scores are written to a local text file. The current implementation uses a hardcoded path (see the `FILE_PATH` variable in `SnakeGame.java`). Before running the game, update `FILE_PATH` to a writable location on your machine or change it to a relative path if you prefer the file to live next to the JAR/class files.

Suggested improvements:
- Use a relative path (e.g. `./HighScore.txt`) or store the file under the user's home directory (`System.getProperty("user.home")`).
- Use JSON or Java serialization for more robust score storage.

## Gameplay & controls

- Arrow keys: change the snake's direction (Up / Down / Left / Right).
- Enter: restart the game after Game Over.

Gameplay details:
- Apples spawn with a lifetime; if not eaten they disappear and a new apple spawns.
- The snake increases speed gradually as you collect apples.
- Passing through a wall wraps you to the opposite edge.

## High score storage

High scores are appended to a text file. Each saved record contains the player's name, the score, and the game number. On startup, the application attempts to load the all-time high score from this file and displays it in-game.

## Notes & known issues

- The default code saves scores using a hardcoded path. Update `FILE_PATH` before running or modify the implementation to use a configurable / relative path.
- If you plan to distribute this application, consider:
  - Handling file-not-found and I/O errors more gracefully.
  - Switching to a structured file format (JSON) or lightweight database for portability.

## Contributing

Contributions, bug reports and enhancements are welcome. Please open an issue or submit a pull request with a clear description of the change and why it is needed.

## Credits

- Implemented with Java Swing and AWT.

If you find this project helpful, a star is appreciated. Happy hacking!
