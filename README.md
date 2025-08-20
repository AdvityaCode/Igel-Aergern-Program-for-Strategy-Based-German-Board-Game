# Igel Ärgern – Java Implementation  
This project is an **Object-Oriented software implementation in Java** for the German strategy board game *Igel Ärgern* (translated as *Hedgehogs in a Hurry*). The goal of the game is to move tokens forward and sideways until **three tokens of one color** reach the goal columns. The board contains **traps** that block movement, and the game enforces distinct rules for valid moves. 

Both **human and computer players** are supported in the overall design **(with implementations in Python and Java)**, but this repository contains only the Java implementation.  

---

## Project Structure  
- **Board** – Abstract data type used to create and manage the board structure.  
- **Cell** – Data type defined as a 2D grid (`Cell[][]`) representing individual spaces on the board.  
- **Driver** – Entry point for running the game. *(Run this file to play!)*  
- **IgelView** – Responsible for displaying the board in the console (`stdout`).  
- **IgelAergern** – Core game logic, including rules and movement validation.  
- **Player** – Defines each player and their corresponding colored tokens.  
- **Die** – Simulates the dice roll used for gameplay.  
- **Pseudocode** – Contains some of the initial planning and thought process for implementing the game logic.  

---
## Future Improvements
- Add a graphical user interface (GUI) for better player interaction.
- Introduce AI player logic for more competitive gameplay choices.
- Access across multiple devices for the multiplayer version of the game.
