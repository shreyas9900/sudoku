package sudoku;

import org.junit.Assert;
import org.junit.Test;

import static sudoku.SampleMetricsTest.*;

public class SearchTest extends Search {

    Search search = new Search();

    @Test
    public void testPruneNode() {
        SearchNode searchNode = new SearchNode(puzzle1, 0 , 0);
        Boolean result = search.pruneNode(searchNode);
        Assert.assertEquals(Boolean.TRUE, result);
        Assert.assertEquals(2, searchNode.expand().size());
    }

    @Test
    public void testGetSolutionNodeNullCheck() {
        SearchNode solutionNode = search.getSolutionNode();
        Assert.assertNull(solutionNode);
    }

    @Test
    public void testGetSolutionNodeSuccess_And_Test_Solve() {
        SearchNode searchNode = utils.nextLocationToEdit(new SearchNode(puzzle1, 0, -1));
        FrontierNodeSet nodeSet = new FrontierNodeSet();
        nodeSet.add(searchNode);
        Search search = new Search();
        search.candidateList = nodeSet;
        SolveStatus status = search.solve();
        SearchNode solutionNode = search.getSolutionNode();

        Assert.assertEquals(SolveStatus.SOLUTION_FOUND, status);
        Assert.assertArrayEquals(solution1, solutionNode.getMatrix());
    }

    @Test
    public void testSolve_Invalid_Input() {
        SearchNode searchNode = utils.nextLocationToEdit(new SearchNode(null, 0, -1));
        FrontierNodeSet nodeSet = new FrontierNodeSet();
        nodeSet.add(searchNode);
        Search search = new Search();
        search.candidateList = nodeSet;
        SolveStatus status = search.solve();
        SearchNode solutionNode = search.getSolutionNode();

        Assert.assertEquals(SolveStatus.NO_SOLUTION, status);
        Assert.assertNull(solutionNode);
    }

    @Test
    public void testSolve() {
        SearchNode searchNode = utils.nextLocationToEdit(new SearchNode(puzzle2, 0, -1));
        FrontierNodeSet nodeSet = new FrontierNodeSet();
        nodeSet.add(searchNode);
        Search search = new Search();
        search.candidateList = nodeSet;
        SolveStatus status = search.solve();
        SearchNode solutionNode = search.getSolutionNode();

        Assert.assertEquals(SolveStatus.SOLUTION_FOUND, status);
        Assert.assertArrayEquals(solution2, solutionNode.getMatrix());
    }

    @Test
    public void testSolve_Faultymatrix() {
        SearchNode searchNode = utils.nextLocationToEdit(new SearchNode(faultyBoard, 0, -1));
        FrontierNodeSet nodeSet = new FrontierNodeSet();
        nodeSet.add(searchNode);
        Search search = new Search();
        search.candidateList = nodeSet;
        SolveStatus status = search.solve();

        Assert.assertEquals(SolveStatus.NO_SOLUTION, status);
    }

}