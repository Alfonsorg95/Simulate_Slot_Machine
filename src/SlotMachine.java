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
    public int finalAmountSpent;


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

        slotMachine.displayStartScreen();
    }

    public void displayStartScreen(){
        int roundsToPlay = 1;
        do {
            String wantMoreCredits;
            do {
                System.out.println("Each game cost " + defaultBet + " credits");
                System.out.println("You have " + credits + " credits");
                System.out.println("Do you want to add more credits?  \"y\": yes  \"n\": no");
                wantMoreCredits = scanner.nextLine();
                if (wantMoreCredits.equals("y")){
                    addCredits();
                } else if (!wantMoreCredits.equals("n")) {
                    System.out.println("Please write a valid answer");
                }
            }while (!wantMoreCredits.equals("n"));

            System.out.println("How many games you want to play?  \"0\": to exit the game");
            roundsToPlay = getIntegerInput();

            if (roundsToPlay == 0){
                break;
            }

            if (roundsToPlay * defaultBet <= credits){
                for (int i = 0; i < roundsToPlay; i++) {
                    play();
                }
            }else {
                System.out.println("Not enough credits, please add more. \nPress enter to continue.");
                scanner.nextLine();
            }

        }while (roundsToPlay > 0);
    }

    public void play() {

        credits -= defaultBet;
        String[][] resultsMatrix = spin();
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


        finalAmountSpent += defaultBet;
        System.out.println("Total credits " + credits);
        System.out.println("Press enter to continue");
        scanner.nextLine();
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

    public void addCredits(){
        credits +=100;
    }

    public int getIntegerInput(){
        boolean isInteger = false;
        int integer = 0;
        while (!isInteger) {
            try {
                integer = Integer.parseInt(scanner.nextLine());
                isInteger = true;
            } catch (Exception e) {
                System.out.println("Please enter an integer number");
            }
        }
        return integer;
    }


}
