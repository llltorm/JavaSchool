package game;

import board.Board;
import direction.Direction;
import key.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static direction.Direction.*;

public class Game2048 implements Game {

    GameHelper helper = new GameHelper();
    Board board;
    Random random = new Random();

    public Game2048(Board board) {
        this.board = board;
    }


    @Override
    public void init() {
        List<Integer> list = new ArrayList<>();
        board.fillBoard(list);
    }

    @Override
    public boolean canMove() {
        return !board.availableSpace().isEmpty();
    }

    @Override
    public boolean move(Direction direction) {
        if (canMove()) {
            if (direction == Left){
                for (int i = 0; i < board.getHeight(); i++) {
                    helper.moveAndMergeEqual(board.getValue(board.getRow(i)));
                }
                addItem();
            }
            else if (direction == Right){
                for (int i = board.getHeight() - 1; i >= 0 ; i--) {
                    helper.moveAndMergeEqual(board.getValue(board.getRow(i)));
                }
                addItem();
            }
            else if (direction == Up){
                for (int j = board.getWidth() - 1; j >= 0 ; j--) {
                    helper.moveAndMergeEqual(board.getValue(board.getColumn(j)));
                }
                addItem();
            }
            else if (direction == Down){
                for (int j = 0; j < board.getWidth(); j++) {
                    helper.moveAndMergeEqual(board.getValue(board.getColumn(j)));
                }
                addItem();
            }
            return true;
        }
        return false;
    }

    @Override
    public void addItem() {
        List<Key> available = board.availableSpace();
        if (!available.isEmpty()) {
            int randomCell = random.nextInt(available.size() - 1);
            board.addItem(available.get(randomCell), 2);
        }
    }

    @Override
    public Board getGameBoard() {
        return board;
    }

    @Override
    public boolean hasWin() {
        return board.hasValue(2048);
    }
}
