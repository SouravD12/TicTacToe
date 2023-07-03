import Controllers.GameController;
import exceptions.DuplicateSymbolException;
import exceptions.MorethanOneBotException;
import exceptions.PlayersCountDimensionsMismatchException;
import models.*;
import stratergies.WinningStratergies.ColumnWinningStratergy;
import stratergies.WinningStratergies.DiagonalWinningStratergy;
import stratergies.WinningStratergies.RowWinningStratergy;
import stratergies.WinningStratergies.WinningStratergy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws PlayersCountDimensionsMismatchException, DuplicateSymbolException, MorethanOneBotException {
        Scanner scanner = new Scanner(System.in);
        GameController gameController = new GameController();
        try{
            int dimensionofGame = 3;
            List<Player>players = new ArrayList<>();

            players.add(new Player(new Symbol('X'),"Sourav",1, PlayerType.HUMAN));


            players.add(new Bot(new Symbol('O'),"Robot",2, BotDifficultyLevel.HARD));
//            players.add(new Bot());
            List<WinningStratergy>winningStratergies = List.of(new RowWinningStratergy(), new
                    ColumnWinningStratergy(), new DiagonalWinningStratergy());
//            winningStratergies.add(new )
            Game game = gameController.startGame(dimensionofGame,players,winningStratergies);
            while (gameController.checkState(game).equals(GameState.IN_PROGRESS)){
//                System.out.println();
                gameController.printBoard(game);
                System.out.println("Does anyone want to undo? (Y/N)");
                String undoAnswer = scanner.next();
                if (undoAnswer.equalsIgnoreCase("y")){
                    gameController.undo(game);
                    continue;
                }
                gameController.makeMove(game);

            }
            System.out.println("Game is finished");
            GameState state = gameController.checkState(game);
            if (state.equals(GameState.DRAW)){
                System.out.println("Game is draw");
            }
            else{
                System.out.println("Winner is " + gameController.GetWinner(game).getName());
            }

        }catch (Exception e){
            System.out.println("Something is Wrong");
        }


    }
}
