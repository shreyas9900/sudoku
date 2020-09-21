package sudoku;

import java.util.ArrayList;
import java.util.List;

public class Search {

    protected Utils utils;
    protected int counter;
    protected FrontierNodeSet candidateList;
    protected SearchNode solutionLeafNode;

    public enum SolveStatus {
        SOLUTION_FOUND, NO_SOLUTION, TIME_OUT, REACHED_RESOURCE_LIMIT
    }

    public Search() {
        this.solutionLeafNode = null;
        utils = new Utils();
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     *
     * @return The leaf solution node. It can be null if a solution has not
     *     been found yet.
     */
    public SearchNode getSolutionNode() {
        return this.solutionLeafNode;
    }

    /**
     * Default implementation of a generate state space search. Many search
     * strategies can be achieved by varying the sorting order of the
     * "candidateList" or by implementing how global metrics is updated
     * and how a search branch is pruned.
     *
     * @return SolveStatus.SOLUTION_FOUND when a solution is found
     *                   SolveStatus.NO_SOLUTION when there is no feasible solution
     *                   Other solve status if time out and resource limit is
     *                   implemented
     */
    public SolveStatus solve() {
        while (!candidateList.isEmpty()) {
            SearchNode theNode = candidateList.removeNextOne();
            if (theNode.isLastNodeInMatrix()) {
                boolean result = pruneNode(theNode);
                if(result) {
                    return SolveStatus.SOLUTION_FOUND;
                }
            }
            else {
                updateGlobalMetrics();
                if (pruneNode(theNode)) {
                    List<SearchNode> nodeList = theNode.expand();
                    if(nodeList != null && nodeList.size()>0){
                        candidateList.add(nodeList);
                    }
                }
            }
        }
        if(solutionLeafNode != null)
            return SolveStatus.SOLUTION_FOUND;
        return SolveStatus.NO_SOLUTION;
    }

    /**
     * Updates counter which is later used to assess benchmark while distinguishing
     * between DFS and BFS performance
     */

    public void updateGlobalMetrics() {
        counter++;
    }

    /**
     * Default implementation of not pruning branch after a note evaluation.
     * @param theNode - the node just evaluated, potentially to be pruned
     * @return True if the branch starting at "theNode" is pruned,
     *                   false otherwise.
     */

    public boolean pruneNode(SearchNode theNode) {
        int[][] currMatrix = theNode.getMatrix();
        int row = theNode.getRow();
        int col = theNode.getCol();

        List<SearchNode> curChildDecisions =  new ArrayList<SearchNode>();
        boolean status = false;
        for(int i = 1 ; i<=9 ; i++){
            if(utils.isValid(currMatrix, i, row , col)){
                  int[][] currMatrixClone = utils.copyMatrix(currMatrix);
                    currMatrixClone[row][col] = i;
                    SearchNode searchNode = new SearchNode(currMatrixClone,row,col);
                    if (row <= 8 && col <= 8) {
                        searchNode = utils.nextLocationToEdit(searchNode);
                    }
                    if (searchNode != null) {
                        if(searchNode.isGoal()) {
                            solutionLeafNode = searchNode;
                            return true;
                        }
                        curChildDecisions.add(searchNode);
                        status=true;
                    }
                    else{
                        System.out.println("Search Node Was null after ["+row+"]["+col+"]");
                    }
            }
        }

        if( curChildDecisions.size() != 0){
            theNode.setChildDecisions(curChildDecisions);
        }

        return status;
    }
}
