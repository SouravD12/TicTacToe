package stratergies.WinningStratergies;

import models.Board;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStratergy implements WinningStratergy {
    private Map<Symbol, Integer> leftDiagcounts = new HashMap<>();
    private Map<Symbol, Integer> rightDiagcounts = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        Symbol symbol = move.getPlayer().getSymbol();
        int row = move.getCell().getRow();
        int col = move.getCell().getColumn();
        if (row == col) {
            if (!leftDiagcounts.containsKey(symbol)) {
                leftDiagcounts.put(symbol, 0);

            }
            leftDiagcounts.put(symbol, leftDiagcounts.get(symbol) + 1);
        }
//            if (leftDiagcounts.get(symbol)
        if (row + col == (board.getSize()) - 1) { //right diagonal check
            if (!rightDiagcounts.containsKey(symbol)) {
                rightDiagcounts.put(symbol, 0);

            }
            rightDiagcounts.put(symbol, rightDiagcounts.get(symbol) + 1);
        }
        if (row == col) {
            if (leftDiagcounts.get(symbol) == (board.getSize())) {
                return true;
            }
        }
        if (row + col == (board.getSize()) - 1) {
            if (rightDiagcounts.get(symbol) == (board.getSize())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void handleundo(Board board, Move move) {
        Symbol symbol = move.getPlayer().getSymbol();
        int row = move.getCell().getRow();
        int col = move.getCell().getColumn();
        if (row == col) {
//            if (!leftDiagcounts.containsKey(symbol)) {
//                leftDiagcounts.put(symbol, 0);
//
//            }
            leftDiagcounts.put(symbol, leftDiagcounts.get(symbol) - 1);
        }

//            if (leftDiagcounts.get(symbol)
        if (row + col == (board.getSize()) - 1) { //right diagonal check
//            if (!rightDiagcounts.containsKey(symbol)) {
//                rightDiagcounts.put(symbol, 0);
//
//            }
            rightDiagcounts.put(symbol, rightDiagcounts.get(symbol) + 1);
        }

//        if (row==col){
//            if (leftDiagcounts.get(symbol)==(board.getSize())) {
//                return true;
//            }
//        }
//        if (row+col==(board.getSize())-1) {
//            if (rightDiagcounts.get(symbol) == (board.getSize())) {
//                return true;
//            }
//        }
//        return false;

    }
}
