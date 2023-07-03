package models;

import stratergies.BotPlayingStratergies.BotPlayingStratergy;
import stratergies.BotPlayingStratergies.BotPlayingStratergyFactory;

public class Bot extends Player {
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStratergy botPlayingStratergy;

    public Bot(Symbol symbol,String name,long id,BotDifficultyLevel botDifficultyLevel){
        super(symbol, name, id, PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStratergy = BotPlayingStratergyFactory.getBotPlayingStratergyForDifficultyLevel(botDifficultyLevel);
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    @Override
    public Move makeMove(Board board) {
        Move move = botPlayingStratergy.makeMove(board);
        move.setPlayer(this);
        return move;
//        super.makeMove();
    }
}
