package game;

import board.Board;
import direction.Direction;
import exception.NotEnoughSpace;

public interface Game {
    void init();
    boolean canMove();
    boolean move(Direction direction);
    void addItem() throws NotEnoughSpace;
    Board getGameBoard();
    boolean hasWin();
}
