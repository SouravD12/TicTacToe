package models;

import exceptions.DuplicateSymbolException;
import exceptions.MorethanOneBotException;
import exceptions.PlayersCountDimensionsMismatchException;
import stratergies.WinningStratergies.WinningStratergy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private List<Player>players;
    private Board board;
    private List<Move>moves;
    private Player Winner;
    private GameState gameState;
    private int nextPlayerIndex;
    private List<WinningStratergy> winningStratergies;

//    public Game() {
//
//    }

    public static Builder getBuilder(){
        return new Builder();
    }
    private Game(int dimensions,List<Player>players,List<WinningStratergy>winningStratergies){
        this.players = players;
        this.winningStratergies = winningStratergies;
        this.nextPlayerIndex = 0;
        this.board = new Board(dimensions);
        this.gameState= GameState.IN_PROGRESS;
        this.moves = new ArrayList<>();



    }
    public static class Builder{
        private List<Player>players;
        private List<WinningStratergy> winningStratergies;
        private int dimensions;
        private Builder(){
            this.players = new ArrayList<>();
            this.winningStratergies = new ArrayList<>();
            this.dimensions = 0;
        }
        public Builder setDimensions(int dimensions) {
            this.dimensions = dimensions;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder addPlayer(Player player){
            this.players.add(player);
            return this;
        }
        public Builder addWinningStratergies(WinningStratergy winningStratergy){
            this.winningStratergies.add(winningStratergy);
            return this;
        }
        public Builder setWinningStratergies(List<WinningStratergy> winningStratergies) {
            this.winningStratergies = winningStratergies;
            return this;
        }
        private void  validateBotCounts() throws MorethanOneBotException,DuplicateSymbolException {
            int botCount = 0;
            for (Player player:players){
                if (player.getPlayerType().equals(PlayerType.BOT)){
                    botCount+=1;
                }
            }
            if (botCount >1){
                throw new MorethanOneBotException();
            }

//            return true;
        }

        private void  validateDimensionsandPlayerscount() throws PlayersCountDimensionsMismatchException {
                if (players.size() !=dimensions -1){
                    throw new PlayersCountDimensionsMismatchException();

                }
//            return true;

            }
        private void validateUniqueSymbols() throws DuplicateSymbolException {

            Map<Character,Integer>symbolCounts = new HashMap<>();
            for (Player player:players){
                if (!symbolCounts.containsKey(player.getSymbol().getaChar())){
                    symbolCounts.put(player.getSymbol().getaChar(),0);

                }
                symbolCounts.put(player.getSymbol().getaChar(),symbolCounts.get(player.getSymbol().getaChar())+1);
                if (symbolCounts.get(player.getSymbol().getaChar()) > 1){
                    throw new DuplicateSymbolException();

                }

            }
//            return true;

//
        }
        private void validate() throws PlayersCountDimensionsMismatchException, DuplicateSymbolException, MorethanOneBotException {
            try {
                validateBotCounts();
                validateUniqueSymbols();
                validateDimensionsandPlayerscount();
            } catch (Exception e){
            throw e;
            }
//            return true;

        }
        public Game build() throws PlayersCountDimensionsMismatchException, DuplicateSymbolException, MorethanOneBotException {
            validate();
//            catch (Exception e){
//                throw e;
//            }
            return new Game(dimensions,players,winningStratergies);
        }
    }
    private boolean validateMove(Move move){
        int row = move.getCell().getRow();
        int col = move.getCell().getColumn();
        if (row>=board.getSize()){
            return false;
        }
        if (col>=board.getSize()){
            return false;
        }
        if (board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)) {
            return true;
        }
        return false;
    }
    private boolean checkWinner(Board board, Move move){
        for (WinningStratergy winningStratergy: winningStratergies){
            if(winningStratergy.checkWinner(board,move)){
                return true;
            }
        }
        return false;
    }
    public void makeMove(){
        Player currentMovePlayer = players.get(nextPlayerIndex);
        System.out.println("It is " +currentMovePlayer.getName()+"'s turn .Please make your move");
        Move move = currentMovePlayer.makeMove(board);
        if(!validateMove(move)){
//            return; // if move is not valid try again .
            System.out.println("Invalid move . Please Try again");
        }
        int row = move.getCell().getRow();
        int col = move.getCell().getColumn();
        Cell cellToChange = board.getBoard().get(row).get(col);
        cellToChange.setCellState(CellState.FILLED);
        cellToChange.setPlayer(currentMovePlayer);

        Move finalMoveObject = new Move(cellToChange,currentMovePlayer);
        moves.add(finalMoveObject);
        nextPlayerIndex+=1;
        nextPlayerIndex%=players.size();

        if(checkWinner(board,finalMoveObject)){
            gameState = GameState.WIN;
            Winner = currentMovePlayer;
            return;
        }
        if (moves.size()==this.board.getSize()*this.board.getSize()){
            gameState = GameState.DRAW;

        }

    }

    public void undo(){
        if (moves.size()==0){
            System.out.println("No moves to undo");
            return;
        }
        Move Lastmove = moves.get(moves.size()-1);
        moves.remove(Lastmove);
        Cell cell = Lastmove.getCell();
        cell.setPlayer(null);
        cell.setCellState(CellState.EMPTY);
        for (WinningStratergy winningStratergy : winningStratergies){
            winningStratergy.handleundo(board,Lastmove);
        }
        nextPlayerIndex-=1;
        nextPlayerIndex = (nextPlayerIndex - players.size())%players.size();

    }


    public void printBoard(){
        board.printBoard();
    }
    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return Winner;
    }

    public void setWinner(Player winner) {
        Winner = winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public List<WinningStratergy> getWinningStratergies() {
        return winningStratergies;
    }

    public void setWinningStratergies(List<WinningStratergy> winningStratergies) {
        this.winningStratergies = winningStratergies;
    }
}
