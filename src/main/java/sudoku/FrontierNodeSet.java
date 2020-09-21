package sudoku;

import java.util.LinkedList;
import java.util.List;

/**
 * A collection of nodes to be explored. It should be stored in a way
 * that is effective for the search strategy used.
 *
 */
public class FrontierNodeSet {
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

    /**
     * @return True if no more node to explore.
     */
    boolean isEmpty(){
        if(list == null || list.size() == 0)
            return true;
        return false;
    }


    /**
     * Add one interfaces.SearchNode to the collection.
     * @param aNode - a node to be added
     */
    void add(SearchNode aNode){
        if(aNode == null || aNode.getMatrix() == null || aNode.getMatrix().length == 0){
            System.out.println("Aborted while trying to add null value to Frontier Node set ");
            return;
        }
        list.add(aNode);
    }

    /**
     * Add a list of SearchNodes to the collection.
     * @param nodeList - a list of nodes to be added
     */

    public void add(List<SearchNode> nodeList){
        list.addAll(nodeList);
    }

    /**
     * Removed one node from the collection to further evaluate/explore.
     * If the collection is sorted, it will remove node according to
     * sorting order.
     *
     * The next node is returned based on BFS vs DFS. Default approach being used in
     * DFS unless explicitly specified to describe  BFS. This feature is used for
     * benchmarking performance
     *
     * @return The next search node to evaluate/explore.
     */
    SearchNode removeNextOne(){
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
