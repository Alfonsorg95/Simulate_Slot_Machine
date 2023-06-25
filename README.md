# Simulate Slot Machine

## Slot Machine Game
This repository contains a simple slot machine game implemented in Java. It was developed as part of the assignment for the Junior Game Engine Developer role. The game simulates a slot machine with various previously defined reels and payouts.

## Features
- Simulates 10M rounds to give a little report of the Slot Machine game.
- Players can add credits to their balance; by default, you start with 100 credits.
- Players can choose the number of games to play.
- The game evaluates the results of each game and determines the payouts based on symbol combinations.
- The game keeps track of the total payout and individual symbol wins.
- Bonus game trigger: If three or more bonus symbols appear, a bonus game is activated.
- The Task 1 program simulates two games, each one calculates the probability of winning the game, and assuming your profit is $1 per win, it provides the mean, variance, and standard deviation of this profit.
  - The first game consists of rolling a die four times; if at least one six appears, you win.
  - The second game consists of rolling two dice twenty-four times; if at least one double-six appears, you win.


## Prerequisites

- Java Development Kit (JDK) 11 or above
- IntelliJ IDEA (recommended) or any Java IDE
- Git (optional)

## Getting Started
- Clone the repository using the following command:
``` shell
git clone https://github.com/your-username/slot-machine-game.git
```

- Open the project in your Java IDE (e.g., IntelliJ IDEA).

- Build and run the project.

- Run Task1 to get the results for the two games explained above

- Run SlotMachine to play the game

- Follow the on-screen instructions to interact with the slot machine game.

- Run RTPSimulation to get the report of 10M rounds, this report includes:
  - Total amount of credits at the end of the game.
  - Total amount of credits spent.
  - RTP.
  - Totalamount earned by the base game and by the bonus game.
  - RTP by base game and bonus game.
  - Max reward obtained.
  - Rewards and RTP for each Symbol of the game.

## Gameplay Instructions
- Start the game by launching the application.

- Add credits to your balance if desired.

- Choose the number of games to play.

- Wait for the game to simulate the spinning reels and evaluate the results.

- Check the payout and individual symbol wins after each game.

- If the bonus game is triggered, enjoy big reward.

- Continue playing or exit the game as desired.

## Development

The game was developed using Java and follows an object-oriented design approach. The core components of the game include:

- SlotMachine class: Represents the main game engine that handles the game flow and interactions with the player.
- Paylines class: Manages the evaluation of symbol combinations and payouts for each game.
- BonudGame class: Handles the bonus game.
- SymbolReel class: Handles the predefined reels and generates the sequence for the game.
- Task1 class: Handles all related to the dice game.
- RTPSimulation class: Simulate 10M rounds of the game and makes a report.
  
Feel free to explore the source code and make modifications or enhancements as per your requirements.


## Acknowledgements
This game was developed as part of an assignment for the Junior Game Engine Developer role. Special thanks to the team for their support and guidance throughout the development process.

## Contributing
Contributions to this project are welcome. If you have any ideas, improvements, or bug fixes, please submit a pull request.

## Feedback
If you have any feedback, suggestions, or issues related to the game, please open an issue on the GitHub repository.
