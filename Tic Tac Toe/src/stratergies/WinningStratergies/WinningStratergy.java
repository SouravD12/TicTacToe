package stratergies.WinningStratergies;

import models.Board;
import models.Move;

public interface WinningStratergy {
    boolean checkWinner(Board board, Move move);
    void handleundo(Board baord , Move move);

}
