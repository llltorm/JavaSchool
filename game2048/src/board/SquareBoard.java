package board;

import key.Key;
import java.util.ArrayList;
import java.util.List;

public class SquareBoard<V> extends Board<Key,V> {

    private int size;

    public SquareBoard(int size) {
        super(size, size);
        this.size = size;
    }

    @Override
    public void fillBoard(List<V> list) {
        try {
            if (list.size() > 16) {
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            throw new ExceptionInInitializerError();
        }
        board.clear();
        var index = 0;
        for (var i = 0; i < size; i++) {
            for (var j = 0; j < size; j++) {
                addItem(new Key(i, j), list.get(index));
                index++;
            }
        }
    }

    @Override
    public List<Key> availableSpace() {
        var ListNullValues = new ArrayList<Key>();
        for (var i = 0; i < size; i++) {
            }
            for (var entry : board.entrySet()) {
                if (entry.getValue() == null) {
                    ListNullValues.add(entry.getKey());
                }
            }
        return ListNullValues;
    }

    @Override
    public void addItem(Key key, V value) {
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
    public V getValue(Key key) {
        return board.get(key);
    }

    @Override
    public List<Key> getColumn(int i) {
        var columnKey = new ArrayList<Key>();
        for (int j = 0; j < size; j++) {
            columnKey.add(getKey(j, i));
        }

        return columnKey;
    }

    @Override
    public List<Key> getRow(int j) {
        var rowKey = new ArrayList<Key>();
        for (int i = 0; i < size; i++) {
            rowKey.add(getKey(j, i));
        }
        return rowKey;
    }

    @Override
    public boolean hasValue(V value){
        return board.containsValue(value);
    }

    @Override
    public List<V> getValues(List<Key> keys) {
        var listValue = new ArrayList<V>();
        for (var key: keys) {
            for (var entry : board.entrySet()) {
                if (entry.getKey() == key) {
                    listValue.add(entry.getValue());
                }
            }
        }
        return listValue;
    }
}
