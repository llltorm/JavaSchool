package game;

import java.util.ArrayList;
import java.util.List;

public class GameHelper {

    public List<Integer> moveAndMergeEqual(List<Integer> list) {
        ArrayList<Integer> listNotNull;
        ArrayList<Integer> newBoard;
        listNotNull = (ArrayList<Integer>) fillListNotNull(list);
        newBoard = (ArrayList<Integer>) fillNewBoard(listNotNull);
        while (newBoard.size() < list.size()) {
            newBoard.add(null);
        }
        return newBoard;
    }

    private List<Integer> fillListNotNull(List<Integer> list)
    {
        ArrayList<Integer> listNotNull = new ArrayList();
        for (Integer value : list) {
            if (value != null) {
                listNotNull.add(value);
            }
        }
        return listNotNull;
    }

    private List<Integer> fillNewBoard( ArrayList<Integer> listNotNull)
    {
        var newBoard = new ArrayList();
        for (int i = 0; i < listNotNull.size(); i++) {
            if ((i < listNotNull.size() - 1) && (listNotNull.get(i) == listNotNull.get(i + 1)))  {
                newBoard.add(listNotNull.get(i) * 2);
                if (i < listNotNull.size() - 1) {
                    i++;
                }
            } else {
                newBoard.add(listNotNull.get(i));
            }
        }
        return newBoard;
    }
}

