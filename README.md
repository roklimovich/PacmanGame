## Project Title

Pacman-style Game Application

## Description

This project is a Java application that implements a game inspired by Pacman. The game introduces a board
where the player moves, avoiding enemies and collecting upgrades that spawn every few seconds.  
The game features multiple custom upgrades, dynamic board sizes, and various graphical elements to visualize the game
world and track player progress.

The application adheres to the **Model-View-Controller (MVC)** architecture and uses **JTable** for board rendering.
Threading is handled via the ``Thread`` class, ensuring proper synchronization without the use of ``Timer`` or
``Executor`` classes.

## Features

* **Main Menu:**

    * New Game

    * High Scores

    * Exit

* **Customizable Game Board:**

    * Player selects a board size ranging from 10x10 to 30x30 rows/columns.

* **Game Elements:**

    * Score counter, time counter, and life counter are visible and continuously updated during the game.

    * Graphical files are used to represent the game elements (characters, upgrades, etc.).

    * Movement and actions are animated (e.g., character movement, food animation).

* **Thread-based Timing:**

    * All timing-related features are handled via the ``Thread`` class.

    * Proper synchronization of threads is enforced, with no mixed functionalities in the same thread.

* **High Scores:**

    * After a game ends, the player is prompted to save their score with a name.
    * High scores are persisted using the ``Serializable`` interface, ensuring records are retained even after the
      application is closed.
    * High scores are displayed using the JList component, with scrollbars for larger lists.

* **Keyboard Shortcuts:**

    * ``Ctrl + Shift + Q``: Interrupts the game and returns the player to the main menu.

## Installation

1. Clone or download the repository.
2. Ensure Java is installed on your machine (JDK 11 or higher is recommended).
3. Compile the project using a Java IDE (e.g., IntelliJ IDEA, Eclipse) or command line.

## Requirements

* **Java Development Kit (JDK) 11** or higher
* No WYSIWYG tools (e.g., Window Builder) are allowed for generating windows.
* **JTable** and **JList** components must be used for board and high score functionalities.

## How to Run

1. Open the project in an IDE like IntelliJ IDEA or Eclipse.
2. Ensure Java is properly configured.
3. Run the main class to start the application.

## Usage

1. Launch the application and choose one of the main menu options:
    * New Game: Start a new game. Select the board size and begin playing.

    * High Scores: View the top scores from previous games.

    * Exit: Close the application.

2. During the game, use the arrow keys to move the player character and avoid enemies.
3. Collect upgrades as they appear on the board to improve performance.
4. After the game, save your score and view it in the high scores menu.

## License

This project is for educational purposes and is subject to academic integrity rules. Plagiarism will result in
disciplinary action.

## Author:

Roman Klimovich

## Lecturer:

Sławomir Aleksander Dańczak, M.Sc. PJAIT
