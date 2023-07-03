package stratergies.WinningStratergies;

import models.Board;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColumnWinningStratergy implements WinningStratergy{
//    private List<Map<Symbol,Integer>>symbolCount;
    private Map<Integer,HashMap<Symbol,Integer>> counts = new HashMap<>();
    @Override

    public boolean checkWinner(Board board, Move move) {
//        HashMap
        int col = move.getCell().getColumn();
        Symbol symbol = move.getPlayer().getSymbol();
        if (!counts.containsKey(col)){
            counts.put(col,new HashMap<>());
        }
        Map<Symbol,Integer> colMap = counts.get(col);
        if(!colMap.containsKey(symbol)){
            colMap.put(symbol,0);

        }
        colMap.put(symbol,colMap.get(symbol)+1);

        if (colMap.get(symbol)==(board.getSize())){
            return true;
        }
        return false;
    }

    @Override
    public void handleundo(Board board, Move move) {
        int col = move.getCell().getColumn();
        Symbol symbol = move.getPlayer().getSymbol();
//        if (!counts.containsKey(col)){
//            counts.put(col,new HashMap<>());
//        }
        Map<Symbol,Integer> colMap = counts.get(col);
//        if(!colMap.containsKey(symbol)){
//            colMap.put(symbol,0);
        colMap.put(symbol,colMap.get(symbol)-1);

//        if (colMap.get(symbol)==(board.getSize())){
//            return true;
//        }
//        return false;

    }
}
