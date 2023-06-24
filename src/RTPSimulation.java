import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class RTPSimulation extends SlotMachine {

    public static final int roundsToSimulate = 10000000;
    public int gamePayout;
    public int bonusPayout;
    public int totalGamePayout;
    public int totalBonusPayout;
    public int biggestPayout;

    public final String [] symbols = {"W1","H1","H2","H3","L1","L2","L3","L4"};
    public RTPSimulation(){
        credits = 100000000;
        gamePayout = 0;
        bonusPayout = 0;
        totalGamePayout = 0;
        biggestPayout = 0;
    }

    public static void main(String[] args) {
        RTPSimulation simulation = new RTPSimulation();
        for (int i = 0; i < roundsToSimulate; i++) {
            simulation.play();
        }
        simulation.printResults();
        simulation.createDocument();
        simulation.fillReport();
    }

    public void play() {
        if(credits > defaultBet){
            credits -= defaultBet;
            String[][] resultsMatrix = spin();
            paylines.setResultsMatrix(resultsMatrix);
            gamePayout = 0;
            bonusPayout = 0;
            gamePayout += paylines.getTotalPayout();
            totalGamePayout += gamePayout;
            if (paylines.bonusGameTrigger()) {
                bonusPayout += bonusGame.doBonusRound(defaultBet);
                totalBonusPayout += bonusPayout;
            }

            credits += gamePayout + bonusPayout;

            if(gamePayout + bonusPayout > biggestPayout){
                biggestPayout = gamePayout + bonusPayout;
            }

        }else {
            System.out.println("Not enough credits");
        }

        finalAmountSpent += defaultBet;

    }

    public void printResults(){
        System.out.println("At the end of the " + roundsToSimulate + " rounds you have " + credits + " credits");
        System.out.println(totalGamePayout + " credits from base game " + totalBonusPayout + " credits from bonus game");
        System.out.println("Your total bet was " + finalAmountSpent + " credits");
        double returnToPlayer = (double) credits / (double) finalAmountSpent * 100;
        double returnBaseGame = (double) totalGamePayout / (double) finalAmountSpent * 100;
        double returnBonusGame = (double) totalBonusPayout / (double) finalAmountSpent * 100;
        System.out.format("Which means you had a return of %f%% \n", returnToPlayer);
        System.out.format("%f%% from the base game \n", returnBaseGame);
        System.out.format("%f%% from the bonus game\n", returnBonusGame);
        System.out.println("The biggest payout was " + biggestPayout);
    }

    public void createDocument(){
        try {
            File file = new File("final_report.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred creating the file.");
            e.printStackTrace();
        }
    }

    public void fillReport(){
        double returnToPlayer = (double) credits / (double) finalAmountSpent * 100;
        double returnBaseGame = (double) totalGamePayout / (double) finalAmountSpent * 100;
        double returnBonusGame = (double) totalBonusPayout / (double) finalAmountSpent * 100;
        try {
            FileWriter myWriter = new FileWriter("final_report.txt");
            myWriter.write("At the end of the " + roundsToSimulate + " rounds, the total amount of credits is: " + credits + "\n");
            myWriter.write("\n" + totalGamePayout + " credits were won from the base game and " + totalBonusPayout + " credits were won from the bonus game\n");
            myWriter.write("\nThe total amount of credits spent was: " + finalAmountSpent + "\n");
            myWriter.write(String.format("Which means you had a return of %f%% \n", returnToPlayer));
            myWriter.write(String.format("%f%% was returned from the base game \n", returnBaseGame));
            myWriter.write(String.format("%f%% was returned from the bonus game\n", returnBonusGame));
            myWriter.write("\nThe biggest payout was " + biggestPayout + "\n");

            for (int i = 0; i < paylines.winPerSymbol.length; i++) {
                myWriter.write(String.format("\nThe symbol " + symbols[i] + " won " + paylines.winPerSymbol[i] + " credits\n"));
                myWriter.write(String.format("Returning %f%%\n", (double)paylines.winPerSymbol[i]/(double) finalAmountSpent * 100));
            }

            myWriter.close();
            System.out.println("The report was successfully created");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
