# **Strategic Snake Game**

## **Authors and Contributors**
- **Reagan Zhang, Edward Wang, John Chen, Yilin Liu** - Gameplay Design and Testing

---

## **Summary**
The **Strategic Snake Game** is a modernized version of the classic Snake game that introduces unique mechanics to enhance gameplay. Players must navigate their snake to collect apples while avoiding collisions with **enemy snakes**, which actively introduce strategic elements and dynamic challenges.

The project was designed to modernize a classic favorite and offer a deeper, more engaging experience that challenges players' reflexes, planning, and adaptability.

This game is perfect for anyone looking for a fun and strategic gaming experience or a showcase of advanced gameplay design and software architecture.

---

## **Table of Contents**
1. [Features](#features)
2. [Installation Instructions](#installation-instructions)
3. [Usage Guide](#usage-guide)
4. [License](#license)
5. [Feedback](#feedback)
6. [Contributions](#contributions)

---

## **Features**
### **Core Features**
- **Enemy Snakes**:  
  Enemy snakes actively challenge players by moving unpredictably, chasing apples, or targeting the player directly.
 - **RandomSnake**: Moves randomly for unpredictability.
 - **AppleChasingSnake**: Competes with the player for apples.
 - **PlayerChasingSnake**: Strategically tracks the player to increase tension.

- **Custom Apples**:  
  Different apple types with unique effects:
 - **Green Apple**: Standard point increase.
 - **Red Apple**: Temporarily speeds up movement.
 - **Purple Apple**: Spawns a temporary shield.

- **Dynamic Gameplay**:  
  The combination of random spawns and unique challenges ensures every game session is unique.

- **Leaderboard**:  
  Tracks scores across sessions, motivating players to compete with others or themselves.

### **Visual Examples**
#### **UML Diagram of Game Architecture**
![exported_from_idea 的副本 drawio](https://github.com/user-attachments/assets/fc02e245-928d-4d3b-b849-66b74a644c3d)


#### **Gameplay Screenshot**
![7f48a072d70e7737cfe54d012f9ad16](https://github.com/user-attachments/assets/c4811a65-b630-4cc3-985c-afcd08edfcf1)


---

## **Installation Instructions**
### **Prerequisites**
- **Java Development Kit (JDK)** (version 17 or above)
- **IntelliJ IDEA** (Community or Ultimate Edition)

### **Steps to Install and Run the Game**
1. Download and install **IntelliJ IDEA**:
 - From [IntelliJ IDEA Downloads](https://www.jetbrains.com/idea/download/).
 - Ensure the Java Development Kit (JDK) is also installed. You can download it from [Oracle's JDK Download](https://www.oracle.com/java/technologies/javase-downloads.html).

2. Clone this repository:
   ```bash
   [git clone https://github.com/your-repo-name/snake-game.git](https://github.com/rainsong13/Snake_Group53.git)
3. Open the Project in IntelliJ IDEA

 - Open IntelliJ IDEA and select **"Open or Import"** from the welcome screen.
 - Navigate to the project folder and select it.

4. Configure the JDK for the Project
 - In IntelliJ, go to **File > Project Structure > SDKs**.
 - Add the JDK you installed earlier.

5. Run the Game
 - Locate the `main` method in the `Main.java` file.
 - Right-click and choose **"Run"** to start the game.

### Common Issues and Fixes

- **Issue**: Missing JDK setup.  
  **Fix**: Ensure JDK is installed and properly configured in IntelliJ (see step 4).
- **Issue**: Project doesn't build.  
  **Fix**: Rebuild the project using **Build > Rebuild Project** in IntelliJ.

### Usage Guide

#### Starting the Game
1. Run the `main` method in `Main.java` using IntelliJ.
2. Use the arrow keys to control your snake.

#### Objective
- Collect apples to score points.
- Avoid collisions with enemy snakes or the board edges.

#### Gameplay Tips
- **Green Apples** are safe and standard.
- **Enemy Snakes** follow distinct strategies. Watch their movement patterns.

### License

This project is licensed under the **MIT License**.

### Feedback

We value your feedback to improve the game!

#### How to Provide Feedback
- Submit feedback via this [Google Form](https://forms.google.com)
- Alternatively, create an issue on our GitHub repository: [Issue Tracker](https://github.com/rainsong13/Snake_Group53/issues).

#### Feedback Guidelines
- Be constructive and detailed.
- Allow **1-2 business days** for a response.

### Contributions

#### Contributions to the Project Are Welcome!

#### How to Contribute
1. Fork the repository.
2. Create a new branch for your feature or bug fix:
   ```bash
   git checkout -b feature-name
3. Make changes and test thoroughly.
4. Submit a pull request with detailed descriptions of your changes.

#### Contribution Guidelines
 - Ensure your code follows Java's best practices.
 - Test all changes locally before submitting.
 - Contributions will be reviewed within 2-3 business days.
