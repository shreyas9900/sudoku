package sudoku;

import java.util.List;

/**

 * A interfaces.SearchNode represents one state of the state space we are searching.

 * The data needed in each node depends on the search strategy employed.

 * Immutable part of the data can be shared across multiple nodes.

 *

 */
public class SearchNodeImpl implements sudoku.interfaces.SearchNode {

    private static final int MATRIX_SIZE = 9;
    private  int[][] matrix;
    private int row;
    private int col;
    private List<SearchNodeImpl> searchNodeList = null;


    public SearchNodeImpl(int[][] matrix, int row, int col){
        if(validateMatrix(matrix)) {
            return;
        }

        this.matrix = matrix;
        this.row = row;
        this.col = col;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix.clone();
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    /**
     * @return True if the current node represents the last position in Matrix.
     */
    public boolean isLastNodeInMatrix(){
        if (row == matrix.length - 1 && col == matrix[0].length - 1)
            return true;
        return false;
    }
    /**
     * @return True if the current node represents the goal state.
     */
    public boolean isGoal(){
        if (row == matrix.length - 1 && col == matrix[0].length - 1 && matrix[row][col] != 0)
            return true;
        return false;
    }

    /**
     * Update Child decision nodes for given SearchNode
     * @param searchNodeList
     */
    public void setChildDecisions(List<SearchNodeImpl> searchNodeList){
        this.searchNodeList = searchNodeList;
    }

    /**
     * From the current state, take each allowed action to generate a set of
     * child states for further explore.
     *
     * @return A list of child nodes
     */

    public List<SearchNodeImpl> expand(){
        return searchNodeList;
    }

    private boolean validateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix.length != MATRIX_SIZE - 1) {
            return false;
        }
        for (int i = 0; i < MATRIX_SIZE - 1; i++) {
            if (matrix[i].length != MATRIX_SIZE) {
                return false;
            }
        }
        return true;
    }

}
