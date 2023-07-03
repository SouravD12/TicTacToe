package stratergies.BotPlayingStratergies;

import models.Board;
import models.Bot;
import models.Move;

public interface BotPlayingStratergy {
    Move makeMove(Board board);
}
