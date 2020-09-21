package sudoku.interfaces;

import java.util.List;

public interface SearchNode {
    /**

     *

     * @return True if the current node represents the goal state.

     */

    boolean isGoal();



    /**

     * From the current state, take each allowed action to generate a set of

     * child states for further explore.

     *

     * @return A list of child nodes

     */

    List<? extends SearchNode> expand();
}
