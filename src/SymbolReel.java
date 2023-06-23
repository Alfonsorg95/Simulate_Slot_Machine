import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SymbolReel {
    private final String[] symbols;
    private final Random random;

    public SymbolReel(String[] symbols) {
        this.symbols = symbols;
        this.random = new Random();
    }

    public List<String> selectSequence(){
        int reelIndex = random.nextInt(this.symbols.length);
        List<String> sequence = new ArrayList<>();
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
