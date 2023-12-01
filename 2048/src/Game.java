public interface Game {

    void init();

    boolean canMove();

    void addItem();

    Board getGameBoard();

    boolean hasWin();
}
