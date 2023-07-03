package Controllers;

import exceptions.DuplicateSymbolException;
import exceptions.MorethanOneBotException;
import exceptions.PlayersCountDimensionsMismatchException;
import models.Board;
import models.Game;
import models.GameState;
import models.Player;
import stratergies.WinningStratergies.WinningStratergy;

import java.util.List;

public class GameController {

    public Game startGame(int dimensionOfBoard , List<Player>players, List<WinningStratergy>winningStratergies) throws PlayersCountDimensionsMismatchException, DuplicateSymbolException, MorethanOneBotException {
        return Game.getBuilder().setPlayers(players).setWinningStratergies(winningStratergies).setDimensions(dimensionOfBoard).build();

    }
    public void makeMove(Game game){
        game.makeMove();

    }
//    void undo(Game game){
//
//    }
    public GameState checkState(Game game){
        return game.getGameState();

    }
    public Player GetWinner(Game game){
        return game.getWinner();

    }
    public void printBoard(Game game){
        game.printBoard();


    }
    public void undo(Game game){
        game.undo();
    }


}
