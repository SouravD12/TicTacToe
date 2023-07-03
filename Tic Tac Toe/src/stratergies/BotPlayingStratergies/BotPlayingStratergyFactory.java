package stratergies.BotPlayingStratergies;

import models.BotDifficultyLevel;

public class BotPlayingStratergyFactory {
    public static BotPlayingStratergy getBotPlayingStratergyForDifficultyLevel(BotDifficultyLevel level){
        return new EasyBotPlayingStratergy();
    }
}
