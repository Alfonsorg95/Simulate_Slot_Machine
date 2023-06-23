import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Paylines {
    private String[][] resultsMatrix;
    private final List<String[]> payLines;
    private int totalPayout = 0;

    public int[] winPerSymbol = {0,0,0,0,0,0,0,0};

    public Paylines() {
        payLines = new ArrayList<>();
    }

    public void setResultsMatrix(String[][] resultsMatrix) {
        this.resultsMatrix = resultsMatrix;
        this.totalPayout = 0;
        this.setPayLines();
        //getPayLines();
    }

    /*public void getPayLines(){

        for (int i = 0; i < this.payLines.size(); i++) {
            System.out.println("\nPayline" + i);
            String[] payLine = this.payLines.get(i);
            for (int j = 0; j < payLine.length; j++) {
                System.out.print(payLine[j]);
            }
            System.out.println("\n");
        }
    }*/

    public void setPayLines(){
        payLines.clear();
        for (int i = 0; i < 3; i++) {
            payLines.add(getHorizontalPayLine(i));
        }
        payLines.add(getDiagonalPayLine());
        payLines.add(getInverseDiagonalPayLine());
        for (String[] payLine : payLines) {
            this.evaluateResults(payLine);
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


        if (occurrenceMap.getOrDefault("L2", 0) == 3 ||
                occurrenceMap.getOrDefault("L2", 0) == 2 && occurrenceMap.getOrDefault("W1", 0) == 1
        ){
            totalPayout += 20;
            winPerSymbol[5]+= 20;
        } else if (occurrenceMap.getOrDefault("L3", 0) == 3 ||
                occurrenceMap.getOrDefault("L3", 0) == 2 && occurrenceMap.getOrDefault("W1", 0) == 1
        ){
            totalPayout += 16;
            winPerSymbol[6]+=16;
        }else if (occurrenceMap.getOrDefault("H3", 0) == 3 ||
                occurrenceMap.getOrDefault("H3", 0) == 2 && occurrenceMap.getOrDefault("W1", 0) == 1
        ){
            totalPayout += 80;
            winPerSymbol[3]+=80;
        }else if (occurrenceMap.getOrDefault("L4", 0) == 3 ||
                occurrenceMap.getOrDefault("L4", 0) == 2 && occurrenceMap.getOrDefault("W1", 0) == 1
        ){
            totalPayout += 12;
            winPerSymbol[7]+=12;
        }else if (occurrenceMap.getOrDefault("L1", 0) == 3 ||
                occurrenceMap.getOrDefault("L1", 0) == 2 && occurrenceMap.getOrDefault("W1", 0) == 1
        ){
            totalPayout += 60;
            winPerSymbol[4]+=60;
        }else if (occurrenceMap.getOrDefault("H2", 0) == 3 ||
                occurrenceMap.getOrDefault("H2", 0) == 2 && occurrenceMap.getOrDefault("W1", 0) == 1
        ){
            totalPayout += 400;
            winPerSymbol[2]+=400;
        }else if (occurrenceMap.getOrDefault("H1", 0) == 3 ||
                occurrenceMap.getOrDefault("H1", 0) == 2 && occurrenceMap.getOrDefault("W1", 0) == 1
        ){
            totalPayout += 800;
            winPerSymbol[1]+=800;
        }else if (occurrenceMap.getOrDefault("W1", 0) == 3){
            totalPayout += 2000;
            winPerSymbol[0]+=2000;
        }
    }

    public boolean bonusGameTrigger(){

        int count = 0;
        boolean activateBonus = false;
        String[][] resultsMatrix = this.resultsMatrix;

        for (String[] row : resultsMatrix) {
            for (String element : row) {
                if (element.equals("B1")) {
                    count++;
                }
            }
        }

        if (count > 2) {
            activateBonus = true;
        }
        return activateBonus;
    }

    public int getTotalPayout() {
        return totalPayout;
    }
}
