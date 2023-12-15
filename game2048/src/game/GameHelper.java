package game;

import java.util.ArrayList;
import java.util.List;

public class GameHelper {

    public List<Integer> moveAndMergeEqual(List<Integer> list) {
        List<Integer> listNotNull;
        List<Integer> newBoard;
        if (list.isEmpty()) {
            return list;
        }
        listNotNull =  fillListNotNull(list);
        newBoard =  fillNewBoard(listNotNull);
        while (newBoard.size() < list.size()) {
            newBoard.add(null);
        }
        return newBoard;
    }

    private List<Integer> fillListNotNull(List<Integer> list)
    {
        List<Integer> listNotNull = new ArrayList();
        for (var value : list) {
            if (value != null) {
                listNotNull.add(value);
            }
        }
        return listNotNull;
    }

    private List<Integer> fillNewBoard( List<Integer> listNotNull)
    {
        var newBoard = new ArrayList();
        for (int i = 0; i < listNotNull.size(); i++) {
            if ((i < listNotNull.size() - 1) && (listNotNull.get(i).equals(listNotNull.get(i + 1))))  {
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

