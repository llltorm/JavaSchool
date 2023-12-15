package test;

import board.Board;
import board.SquareBoard;
import game.Game;
import game.Game2048;

public class TestClass {
    public static void main(String[] args) {
        Board board = new SquareBoard(4);
        Game game2048 = new Game2048();
        System.out.println(game2048.canMove());
    }
}
