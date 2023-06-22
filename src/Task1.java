import java.util.Random;

public class Task1 {

    public static Random random = new Random();

    public static void main(String[] args) {

        double bet = 1;
        double payout = 1;
        double gamesQuantity = 1000000;

        System.out.println("GAME 1");
        testGame1(bet, payout, gamesQuantity);
        System.out.println("------------------------------------------------------------------------");
        System.out.println("GAME 2");
        testGame2(bet, payout, gamesQuantity);

    }

    public static void testGame1(double bet, double payout, double gamesQuantity){
        int winCount = 0;
        int loseCount = 0;
        for (int i = 0; i < gamesQuantity; i++) {
            if (throwDieFourTimes()) {
                winCount++;
            } else {
                loseCount++;
            }
        }
        results(winCount, loseCount,payout, bet, gamesQuantity);
    }

    public static void testGame2(double bet, double payout, double gamesQuantity){
        int winCount = 0;
        int loseCount = 0;
        for (int i = 0; i < gamesQuantity; i++) {
            if (throwDiceTwentyFourTimes()) {
                winCount++;
            } else {
                loseCount++;
            }
        }
        results(winCount, loseCount,payout, bet, gamesQuantity);
    }

    public static void results(int winCount, int loseCount, double payout, double bet, double gamesQuantity){
        double totalPayout = winCount*payout - loseCount*bet;
        double mean = totalPayout/gamesQuantity;
        double variance = (winCount*Math.pow((payout - mean), 2) + loseCount*Math.pow((-bet - mean), 2))/gamesQuantity;
        double standardDeviation = Math.sqrt(variance);

        System.out.println("Total profit: " + totalPayout);
        System.out.println("Wins: " + winCount + "  Loses: " + loseCount);
        System.out.format("Win percentage of %.4f%% \n", (winCount/gamesQuantity)*100);
        System.out.println("In average your profit is: " + mean + "$ per game");
        System.out.println("with a variance of: " + variance);
        System.out.println("and standard deviation of: " + standardDeviation);
    }

    public static boolean throwDieFourTimes () {
        boolean win = false;

        for (int i = 0; i < 4; i++) {
            if (rollDie() == 6){
                win = true;
                break;
            }
        }

        return win;
    }

    public static boolean throwDiceTwentyFourTimes() {
        boolean win = false;

        for (int i = 0; i < 24; i++) {
            int die0 = rollDie();
            int die1 = rollDie();
            if (die0 == 6 && die1 == 6){
                win = true;
                break;
            }
        }

        return win;
    }

    public static int rollDie(){
        return random.nextInt(6) + 1;
    }
}



