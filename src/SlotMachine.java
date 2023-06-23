import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SlotMachine {

    private final List<SymbolReel> reels;
    public Paylines paylines;
    public BonusGame bonusGame;
    public static Scanner scanner;
    public int credits;
    public int defaultBet;


    public SlotMachine() {
        reels = new ArrayList<>();
        paylines = new Paylines();
        bonusGame = new BonusGame();
        scanner = new Scanner(System.in);
        credits = 100;
        defaultBet = 10;
        reels.add(new SymbolReel(new String[]{"L4","L3","L2","B1","H2","H2","H2","L1","L1","L1","H3","H1","H2","L2","L2","L2","W1","L3","L3","L3","H3","H3","H3","L4","L4","L4","H3","L1","L2","L4","H3","L3","L3","L3","B1","L2","L2","L2","L2","H3","H3","H3","L4","L4","L4","B1","L1","L1","L1","H1","L2","L2","L2","B1","L3","L3","L3"}));
        reels.add(new SymbolReel(new String[]{"L4","L3","L2","B1","H2","H2","H2","L1","L1","L1","H3","H1","H2","L2","L2","L2","W1","L3","L3","L3","H3","H3","H3","L4","L4","L4","H3","L1","L2","L4","H3","L3","L3","L3","B1","L2","L4","H3","H3","H3","B1","L2","L2","L2","L2","B1","L3","L3","L3","H1","L4","L4","L4","L3"}));
        reels.add(new SymbolReel(new String[]{"L4","L3","L2","B1","H2","H2","H2","L1","L1","L1","H3","H1","H2","L2","L2","L2","W1","L3","L3","L3","H3","H3","H3","L4","L4","L4","H3","L1","L2","L4","H3","L3","L3","L3","B1","L2","H2","H3","H3","H3","L2","L2","L2","L1","L1","B1","L2","L2","L2","L1"}));
    }


    public static void main(String[] args) {
        SlotMachine slotMachine = new SlotMachine();
        slotMachine.play();
    }

    public void play() {

        if(credits > defaultBet){
            credits -= defaultBet;
            String[][] resultsMatrix = spin();
            /*String[][] resultsMatrix ={
                {"L1", "A", "W1"},
                {"L1", "L1", "H1"},
                {"L1", "A", "H1"}
            };*/
            printMatrix(resultsMatrix);
            paylines.setResultsMatrix(resultsMatrix);
            int gamePayout = 0;
            gamePayout += paylines.getTotalPayout();
            if (paylines.bonusGameTrigger()) {
                gamePayout += bonusGame.doBonusRound(defaultBet);
            }

            if (gamePayout > 0) {
                System.out.println("You won " + gamePayout + " credits");
            }

            credits += gamePayout;
        }else {
            System.out.println("Not enough credits");
        }

        System.out.println("Total credits " + credits);
        //scanner.nextLine();
    }

    public String[][] spin() {

        String[][] resultsMatrix = new String[3][3];


        for (int i = 0; i < 3; i++) {
            SymbolReel reel = reels.get(i);
            List<String> symbols = reel.selectSequence();
            resultsMatrix[i] = symbols.toArray(new String[0]);
        }

        return resultsMatrix;
    }

    public void printMatrix(String[][] resultsMatrix){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(resultsMatrix[j][i]);
            }
            System.out.println();
        }
    }


}
