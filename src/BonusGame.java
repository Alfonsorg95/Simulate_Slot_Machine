import java.util.Random;

public class BonusGame {

    private final Random random;

    public BonusGame(){
        this.random = new Random();
    }

    public int doBonusRound(int bet){
        int coins = this.getRandomCoins();
        int die = this.getDieNumber();

        return coins*die*bet;
    }

    private int getRandomCoins(){

        int[] coins = {2, 3, 4, 5, 10, 20, 30, 50, 100};
        int[] weights = {350,300,260,220,151,50,40,20,10};
        int cumulativeWeight = 0;
        int random = this.random.nextInt(1401);

        for (int i = 0; i < coins.length; i++) {
            cumulativeWeight += weights[i];
            if (random < cumulativeWeight){
                return coins[i];
            }
        }
        return 0;
    }

    private int getDieNumber(){
        return random.nextInt(6) + 1;
    }
}
