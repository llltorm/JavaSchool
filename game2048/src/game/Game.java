package game;

import board.Board;
import direction.Direction;

public interface Game {
    void init();
    boolean canMove();
    boolean move(Direction direction);
    void addItem();
    Board getGameBoard();
    boolean hasWin();
}
