package board;

import key.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SquareBoard extends Board {

    private int size;

    public SquareBoard(int size) {
        super(size, size);
        this.size = size;
    }

    @Override
    public void fillBoard(List<Integer> list) {
        board.clear();
        int index = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                addItem(new Key(i, j), list.get(index));
                index++;
            }
        }
    }

    @Override
    public List<Key> availableSpace() {
        List<Key> ListNullValues = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                var value = board.get(getKey(i, j));
                if (value == null) {
                    ListNullValues.add(getKey(i, j));
                }
            }
        }
        return ListNullValues;
    }

    @Override
    public void addItem(Key key, Integer value) {
        board.put(key, value);
    }

    @Override
    public Key getKey(int i, int j) {
        for (var entry : board.entrySet()) {
            if (entry.getKey().equals(new Key(i, j))) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public Integer getValue(Key key) {
        return board.get(key);
    }

    @Override
    public List<Key> getColumn(int i) {
        List<Key> columnKey = new ArrayList<>();
        for (var entry : board.entrySet()) {
            if (entry.getKey().getJ() == i) {
                columnKey.add(entry.getKey());
            }
        }
        return columnKey;
    }

    @Override
    public List<Key> getRow(int i) {
        List<Key> rowKey = new ArrayList<>();
        for (var entry : board.entrySet()) {
            if (entry.getKey().getI() == i) {
                rowKey.add(entry.getKey());
            }
        }
        return rowKey;
    }

    @Override
    public boolean hasValue(Integer value){
        for (var entry : board.entrySet()) {
            if (Objects.equals(entry.getValue(), value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Integer> getValue(List<Key> keys) {
        List<Integer> listValue = new ArrayList<>();
        for (var key: keys) {
            if (board.containsKey(key)) {
                listValue.add(board.get(key));
            }
        }
        return listValue;
    }
}
