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
        int statingCredits = simulation.credits;
        for (int i = 0; i < roundsToSimulate; i++) {
            simulation.play();
        }
        simulation.printResults(statingCredits);
        simulation.checkSymbolsRewards();
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

    }

    public void printResults(int statingCredits){
        System.out.println("At the end of the " + roundsToSimulate + " rounds you have " + credits + " credits");
        System.out.println(totalGamePayout + " credits from base game " + totalBonusPayout + " credits from bonus game");
        System.out.println("You started with " + statingCredits + " credits");
        double returnToPlayer = (double) credits / (double) statingCredits * 100;
        double returnBaseGame = (double) totalGamePayout / (double) statingCredits * 100;
        double returnBonusGame = (double) totalBonusPayout / (double) statingCredits * 100;
        System.out.format("Which means you had a return of %f%% \n", returnToPlayer);
        System.out.format("%f%% from the base game \n", returnBaseGame);
        System.out.format("%f%% from the bonus game\n", returnBonusGame);
        System.out.println("The biggest payout was " + biggestPayout);
    }

    public void checkSymbolsRewards(){
        for (int i = 0; i < paylines.winPerSymbol.length; i++) {
            System.out.println("Symbol " + symbols[i] + " won " + paylines.winPerSymbol[i] + " credits");
            System.out.format("Returning %f%%\n", (double)paylines.winPerSymbol[i]/(double) 100000000 * 100);
        }
    }

}
