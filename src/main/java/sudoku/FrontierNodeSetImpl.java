package sudoku;

import sudoku.interfaces.FrontierNodeSet;
import sudoku.interfaces.SearchNode;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class FrontierNodeSetImpl implements FrontierNodeSet {

    private Boolean isDfS = true;
    private Boolean isBfS = false;
    List<SearchNode> list = new LinkedList<SearchNode>();

    public Boolean getDfS() {
        return isDfS;
    }

    public void setDfS(Boolean dfS) {
        isDfS = dfS;
    }

    public Boolean getBfS() {
        return isBfS;
    }

    public void setBfS(Boolean bfS) {
        isBfS = bfS;
    }

    public boolean isEmpty() {
        if(list == null || list.size() == 0)
            return true;
        return false;
    }

    public void add(SearchNode searchNode) {
        SearchNodeImpl aNode = (SearchNodeImpl) searchNode;
        if(aNode == null || aNode.getMatrix() == null || aNode.getMatrix().length == 0){
            System.out.println("Aborted while trying to add null value to Frontier Node set ");
            return;
        }
        list.add(aNode);
    }

    public void add(List<? extends SearchNode> nodeList) {
        list.addAll((Collection<? extends SearchNodeImpl>) nodeList);
    }

    public SearchNode removeNextOne() {
        if (list == null || list.isEmpty()) {
            return null;
        }

        if (isBfS) {
            return list.remove(0);
        } else {
            return list.remove(list.size() - 1 );
        }
    }
}
