package game;

import board.Board;
import board.SquareBoard;
import direction.Direction;
import exception.NotEnoughSpace;
import key.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;
import static direction.Direction.*;
import static java.util.Collections.reverse;

public class Game2048 implements Game {

    public static final int GAME_SIZE = 4;
    private static final int BASIC_NUMBER_ELEMENT = 2;
    GameHelper helper = new GameHelper();
    Board<Key, Integer> board = new SquareBoard<>(4);
    Random random = new Random();

    public Game2048() {
    }

    @Override
    public void init() {
        new Game2048();
        board.fillBoard(asList(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null));
        try {
            for(var i = 0; i < BASIC_NUMBER_ELEMENT; i++)
            {
                addItem();
            }
        } catch (NotEnoughSpace e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean canMove() {
        if (!board.availableSpace().isEmpty()) {
            return true;
        }
        for (int i = 0; i < board.getHeight(); i++) {
            var listRow = board.getValues(board.getRow(i));
            var listColumn = board.getValues(board.getColumn(i));
            for (var j = 0; j < listRow.size() - 1; j++) {
                if (listRow.get(j).equals(listRow.get(j + 1))) {
                    return true;
                }
            }
            for (var j = 0; j < listColumn.size() - 1; j++) {
                if (listColumn.get(j).equals(listColumn.get(j + 1))) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean move(Direction direction) {
        var newBoard = new ArrayList<Integer>();
        List<Key> listKeyBuffer;
        if (canMove()) {
            if (direction == LEFT){
                for (int i = 0; i < board.getWidth(); i++) {
                    newBoard.addAll(helper.moveAndMergeEqual(board.getValues(board.getRow(i))));
                }
                board.fillBoard(newBoard);
                try {
                    addItem();
                } catch (NotEnoughSpace e) {
                    System.out.println(e.getMessage());
                }
            }
            else if (direction == RIGHT){
                for (int i = 0; i < board.getWidth(); i++) {
                    reverse(listKeyBuffer = board.getRow(i));
                    var reverseListKeyBuffer = helper.moveAndMergeEqual(board.getValues(listKeyBuffer));
                    reverse(reverseListKeyBuffer);
                    newBoard.addAll(reverseListKeyBuffer);
                }
                board.fillBoard(newBoard);
                try {
                    addItem();
                } catch (NotEnoughSpace e) {
                    System.out.println(e.getMessage());
                }
            }
            else if (direction == UP){
                for (int j = 0; j < board.getHeight(); j++) {
                    newBoard.addAll(helper.moveAndMergeEqual(board.getValues(board.getColumn(j))));
                }
                newBoard = quarterTurn(newBoard);
                board.fillBoard(newBoard);
                try {
                    addItem();
                } catch (NotEnoughSpace e) {
                    System.out.println(e.getMessage());
                }
            }
            else if (direction == DOWN){
                for (int j = 0; j < board.getHeight(); j++) {
                    reverse(listKeyBuffer = board.getColumn(j));
                    var reverseListKeyBuffer = helper.moveAndMergeEqual(board.getValues(listKeyBuffer));
                    reverse(reverseListKeyBuffer);
                    newBoard.addAll(reverseListKeyBuffer);
                }
                newBoard = quarterTurn(newBoard);
                board.fillBoard(newBoard);
                try {
                    addItem();
                } catch (NotEnoughSpace e) {
                    System.out.println(e.getMessage());
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void addItem() throws NotEnoughSpace {
        try {
            List<Key> available = board.availableSpace();
            if (!available.isEmpty()) {
                int randomCell = random.nextInt(available.size() - 1);
                board.addItem(available.get(randomCell), randomElementTwoOrFour());
            } else {
                throw new NotEnoughSpace("На поле закончилось свободное пространство");
            }
        }
        catch (NotEnoughSpace e){
            throw new NotEnoughSpace("Не удалось добавить новый элемент");
        }
    }

    private int randomElementTwoOrFour(){
        return random.nextDouble() > 0.2? 2 : 4;
    }

    @Override
    public Board getGameBoard() {
        return board;
    }

    @Override
    public boolean hasWin() {
        return board.hasValue(2048);
    }

    private ArrayList<Integer> quarterTurn(List<Integer> list) {
        var newList = new ArrayList<Integer>();
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                newList.add(list.get(j * 4 + i));
            }
        }
        return newList;
    }

}
