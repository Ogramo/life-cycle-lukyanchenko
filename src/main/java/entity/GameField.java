package entity;

public class GameField {
    private static GameField instance;
    private final Cell[][] cells;

    private GameField() {
        cells = new Cell[100][20];
    }

    public static GameField getInstance() {
        if (instance == null) {
            instance = new GameField();
            instance.iteration();
        }
        return instance;
    }

    public void iteration() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell();
                cells[i][j].cellGeneration(i, j);
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

}
