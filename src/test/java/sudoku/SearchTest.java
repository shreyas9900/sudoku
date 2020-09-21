package sudoku;

import org.junit.Assert;
import org.junit.Test;

import static sudoku.TestData.*;

public class SearchTest extends Search {

    Search search = new Search();

    @Test
    public void testPruneNode() {
        SearchNodeImpl searchNode = new SearchNodeImpl(puzzle1, 0 , 0);
        Boolean result = search.pruneNode(searchNode);
        Assert.assertEquals(Boolean.TRUE, result);
        Assert.assertEquals(2, searchNode.expand().size());
    }

    @Test
    public void testGetSolutionNodeNullCheck() {
        SearchNodeImpl solutionNode = search.getSolutionNode();
        Assert.assertNull(solutionNode);
    }

    @Test
    public void testGetSolutionNodeSuccess_And_Test_Solve() {
        SearchNodeImpl searchNode = utils.nextLocationToEdit(new SearchNodeImpl(puzzle1, 0, -1));
        FrontierNodeSetImpl nodeSet = new FrontierNodeSetImpl();
        nodeSet.add(searchNode);
        Search search = new Search();
        search.candidateList = nodeSet;
        SolveStatus status = search.solve();
        SearchNodeImpl solutionNode = search.getSolutionNode();

        Assert.assertEquals(SolveStatus.SOLUTION_FOUND, status);
        Assert.assertArrayEquals(solution1, solutionNode.getMatrix());
    }

    @Test
    public void testSolve_Invalid_Input() {
        SearchNodeImpl searchNode = utils.nextLocationToEdit(new SearchNodeImpl(null, 0, -1));
        FrontierNodeSetImpl nodeSet = new FrontierNodeSetImpl();
        nodeSet.add(searchNode);
        Search search = new Search();
        search.candidateList = nodeSet;
        SolveStatus status = search.solve();
        SearchNodeImpl solutionNode = search.getSolutionNode();

        Assert.assertEquals(SolveStatus.NO_SOLUTION, status);
        Assert.assertNull(solutionNode);
    }

    @Test
    public void testSolve() {
        SearchNodeImpl searchNode = utils.nextLocationToEdit(new SearchNodeImpl(puzzle2, 0, -1));
        FrontierNodeSetImpl nodeSet = new FrontierNodeSetImpl();
        nodeSet.add(searchNode);
        Search search = new Search();
        search.candidateList = nodeSet;
        SolveStatus status = search.solve();
        SearchNodeImpl solutionNode = search.getSolutionNode();

        Assert.assertEquals(SolveStatus.SOLUTION_FOUND, status);
        Assert.assertArrayEquals(solution2, solutionNode.getMatrix());
    }

    @Test
    public void testSolve_Faultymatrix() {
        SearchNodeImpl searchNode = utils.nextLocationToEdit(new SearchNodeImpl(faultyMatrix, 0, -1));
        FrontierNodeSetImpl nodeSet = new FrontierNodeSetImpl();
        nodeSet.add(searchNode);
        Search search = new Search();
        search.candidateList = nodeSet;
        SolveStatus status = search.solve();

        Assert.assertEquals(SolveStatus.NO_SOLUTION, status);
    }

}