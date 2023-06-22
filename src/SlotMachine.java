import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class SlotMachine {

    private List<SymbolReel> reels;


    public SlotMachine() {
        reels = new ArrayList<>();
        reels.add(new SymbolReel(new String[]{"L4","L3","L2","B1","H2","H2","H2","L1","L1","L1","H3","H1","H2","L2","L2","L2","W1","L3","L3","L3","H3","H3","H3","L4","L4","L4","H3","L1","L2","L4","H3","L3","L3","L3","B1","L2","L2","L2","L2","H3","H3","H3","L4","L4","L4","B1","L1","L1","L1","H1","L2","L2","L2","B1","L3","L3","L3"}));
        reels.add(new SymbolReel(new String[]{"L4","L3","L2","B1","H2","H2","H2","L1","L1","L1","H3","H1","H2","L2","L2","L2","W1","L3","L3","L3","H3","H3","H3","L4","L4","L4","H3","L1","L2","L4","H3","L3","L3","L3","B1","L2","L4","H3","H3","H3","B1","L2","L2","L2","L2","B1","L3","L3","L3","H1","L4","L4","L4","L3"}));
        reels.add(new SymbolReel(new String[]{"L4","L3","L2","B1","H2","H2","H2","L1","L1","L1","H3","H1","H2","L2","L2","L2","W1","L3","L3","L3","H3","H3","H3","L4","L4","L4","H3","L1","L2","L4","H3","L3","L3","L3","B1","L2","H2","H3","H3","H3","L2","L2","L2","L1","L1","B1","L2","L2","L2","L1"}));
    }


    public static void main(String[] args) {
        SlotMachine slotMachine = new SlotMachine();
        slotMachine.play();
    }

    public void play(){

        Paylines paylines = new Paylines();
        String[][] resultsMatrix = this.spin();
        paylines.setResultsMatrix(resultsMatrix);

        int gamePayout = paylines.getTotalPayout();
        System.out.println(gamePayout);
    }

    public String[][] spin() {

        String[][] resultsMatrix = new String[3][3];



        for (int i = 0; i < 3; i++) {
            SymbolReel reel = reels.get(i);
            List<String> symbols = reel.selectSequence();
            resultsMatrix[i] = symbols.toArray(new String[0]);
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(resultsMatrix[j][i]);
            }
            System.out.println();
        };

        return resultsMatrix;
    }


    private class Paylines{

        private String[][] resultsMatrix;
        private List<String[]> payLines;
        private int totalPayout = 0;

        public Paylines() {
            payLines = new ArrayList<>();
        }

        public void setResultsMatrix(String[][] resultsMatrix) {
            this.resultsMatrix = resultsMatrix;
            this.setPayLines();
        }

        public void getPayLines(){
            for (int i = 0; i < this.payLines.size(); i++) {
                String[] payLine = this.payLines.get(i);
                System.out.println("PayLine " + i);
                for (int j = 0; j < payLine.length; j++) {
                    System.out.print(payLine[j]);
                }
                System.out.println("\n");
            }
        }

        public void setPayLines(){
            for (int i = 0; i < 3; i++) {
                payLines.add(getHorizontalPayLine(i));
            }
            payLines.add(getDiagonalPayLine());
            payLines.add(getInverseDiagonalPayLine());
            this.getPayLines();
            for (int i = 0; i < payLines.size(); i++) {
                this.evaluateResults(payLines.get(i));
            }
        }


        private String[] getHorizontalPayLine(int row){
            String[] horizontalPayLine = new String[this.resultsMatrix.length];
            for (int i = 0; i < this.resultsMatrix.length; i++) {
                horizontalPayLine[i] = resultsMatrix[i][row];
            }
            return horizontalPayLine;
        }

        private String[] getDiagonalPayLine(){
            String[] diagonalPayLine = new String[this.resultsMatrix.length];
            for (int i = 0; i < this.resultsMatrix.length; i++) {
                diagonalPayLine[i] = resultsMatrix[i][i];
            }
            return diagonalPayLine;
        }

        private String[] getInverseDiagonalPayLine(){
            String[] inverseDiagonalPayLine = new String[this.resultsMatrix.length];
            for (int i = 0; i < this.resultsMatrix.length; i++) {
                inverseDiagonalPayLine[i] = resultsMatrix[this.resultsMatrix.length - 1 - i][i];
            }
            return inverseDiagonalPayLine;
        }

        private void evaluateResults(String[] payLine){
            Map<String, Integer> occurrenceMap = new HashMap<>();

            for (String element : payLine) {
                occurrenceMap.put(element, occurrenceMap.getOrDefault(element, 0) + 1);
            }


            if (occurrenceMap.getOrDefault(
                    "L2", 0) == 3 ||
                    occurrenceMap.getOrDefault("L2", 0) == 2 && occurrenceMap.getOrDefault("W1", 0) == 1
            ){
                this.totalPayout += 20;
            } else if (occurrenceMap.getOrDefault(
                    "L3", 0) == 3 ||
                    occurrenceMap.getOrDefault("L3", 0) == 2 && occurrenceMap.getOrDefault("W1", 0) == 1
            ){
                this.totalPayout += 16;
            }else if (occurrenceMap.getOrDefault(
                    "H3", 0) == 3 ||
                    occurrenceMap.getOrDefault("H3", 0) == 2 && occurrenceMap.getOrDefault("W1", 0) == 1
            ){
                this.totalPayout += 80;
            }else if (occurrenceMap.getOrDefault(
                    "L4", 0) == 3 ||
                    occurrenceMap.getOrDefault("L4", 0) == 2 && occurrenceMap.getOrDefault("W1", 0) == 1
            ){
                this.totalPayout += 12;
            }else if (occurrenceMap.getOrDefault(
                    "L1", 0) == 3 ||
                    occurrenceMap.getOrDefault("L1", 0) == 2 && occurrenceMap.getOrDefault("W1", 0) == 1
            ){
                this.totalPayout += 60;
            }else if (occurrenceMap.getOrDefault(
                    "H2", 0) == 3 ||
                    occurrenceMap.getOrDefault("H2", 0) == 2 && occurrenceMap.getOrDefault("W1", 0) == 1
            ){
                this.totalPayout += 400;
            }else if (occurrenceMap.getOrDefault(
                    "H1", 0) == 3 ||
                    occurrenceMap.getOrDefault("H1", 0) == 2 && occurrenceMap.getOrDefault("W1", 0) == 1
            ){
                this.totalPayout += 800;
            }else if (occurrenceMap.getOrDefault("W1", 0) == 3){
                this.totalPayout += 2000;
            }
        }

        public int getTotalPayout() {
            return totalPayout;
        }
    }




    private static class SymbolReel {
        private String[] symbols;
        private Random random;

        public SymbolReel(String[] symbols) {
            this.symbols = symbols;
            this.random = new Random();
        }

        public List<String> selectSequence(){
            int reelIndex = random.nextInt(this.symbols.length);
            System.out.println(reelIndex);
            List<String> sequence = new ArrayList<>();;
            for (int i = 0; i < 3; i++) {
                sequence.add(symbols[reelIndex]);
                reelIndex++;
                if (reelIndex == this.symbols.length){
                    reelIndex = 0;
                }
            }

            return sequence;
        }

    }
}
