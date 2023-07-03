package models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int size;

    public Board(int dimensions){
        size = dimensions;
        board = new ArrayList<>();
        for(int i = 0 ; i<dimensions ;i++){
            board.add(new ArrayList<>()); //First array [[] [] []]
            for (int j = 0 ; j<dimensions ; j++){
                board.get(i).add(new Cell(i,j)); // Adding cells [[o] [o] [o]]
            }
        }
    };

    public void printBoard(){
        for (List<Cell>row:board){
            for(Cell cell:row){
                if (cell.getPlayer() == null) {
                    System.out.print("| - |");
                }
                else{
                    System.out.print("| "+cell.getPlayer().getSymbol().getaChar()+ "|");
                }

            }
            System.out.print("\n");
        }

    }
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }

    List<List<Cell>>board;
}
