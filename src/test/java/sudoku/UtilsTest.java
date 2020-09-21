package sudoku;

//import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Assert;
import org.junit.Test;

import static sudoku.SampleMetricsTest.puzzle1;

public class UtilsTest extends Search {

    Utils utils = new Utils();

    @Test
    public void testCopyBoard() {
        int[][] copyOfPuzzle = utils.copyMatrix(puzzle1);
        Assert.assertArrayEquals(copyOfPuzzle,puzzle1);
    }

    @Test
    public void testIsValid() {
        Boolean result = utils.isValid(puzzle1, 4, 0, 2);
        Assert.assertEquals(Boolean.TRUE, result);
    }
    @Test
    public void testIsValid_NegativeScenario() {
        Boolean result = utils.isValid(puzzle1, 5, 0, 2);
        Assert.assertEquals(Boolean.FALSE, result);
    }

    @Test
    public void testGridValidity() {
        Boolean result = utils.gridValidity(puzzle1, 4, 0, 2);
        Assert.assertEquals(Boolean.TRUE, result);
    }
    @Test
    public void testGridValidity_NegativeScenario() {
        Boolean result = utils.gridValidity(puzzle1, 5, 0, 2);
        Assert.assertEquals(Boolean.FALSE, result);
    }

    @Test
    public void testNextLocationToEdit() {
        SearchNode searchNode = new SearchNode(puzzle1,0,-1);
        searchNode = utils.nextLocationToEdit(searchNode);
        Assert.assertEquals(searchNode.getRow(), 0);
        Assert.assertEquals(searchNode.getCol(), 2);
    }
}