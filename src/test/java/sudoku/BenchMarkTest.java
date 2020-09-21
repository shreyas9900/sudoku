package sudoku;

import org.junit.Test;

import java.text.NumberFormat;
import java.util.Locale;

import static sudoku.TestData.*;

public class BenchMarkTest extends Search {
    @Test
    public void testGetSolutionNodeSuccess_And_Test_Solve() {
        SearchNodeImpl searchNodeDFS = utils.nextLocationToEdit(new SearchNodeImpl(puzzle2, 0, -1));
        FrontierNodeSetImpl nodeSetDFS = new FrontierNodeSetImpl();
        nodeSetDFS.setDfS(true);
        nodeSetDFS.add(searchNodeDFS);
        Search searchDFS = new Search();
        searchDFS.candidateList = nodeSetDFS;
        SolveStatus statusDFS = searchDFS.solve();
        float dfs  = searchDFS.getCounter();

        SearchNodeImpl searchNodeBFS = utils.nextLocationToEdit(new SearchNodeImpl(puzzle2, 0, -1));
        FrontierNodeSetImpl nodeSetBFS = new FrontierNodeSetImpl();
        nodeSetBFS.setBfS(true);
        nodeSetBFS.add(searchNodeBFS);
        Search searchBFS = new Search();
        searchBFS.candidateList = nodeSetBFS;
        SolveStatus statusBFS = searchBFS.solve();
        float bfs = searchBFS.getCounter();

        float perfGainFactor = bfs/dfs * 100;
        System.out.println("================  Benchmark numbers :  DFS VS BFS ==================================");
        System.out.println("BFS Prune Iterations : " + NumberFormat.getNumberInstance(Locale.US).format(bfs));
        System.out.println("DFS Prune Iterations :   " + NumberFormat.getNumberInstance(Locale.US).format(dfs));
        System.out.println("Iterations reduced with DFS :   " + NumberFormat.getNumberInstance(Locale.US).format(bfs - dfs));
        System.out.println("====================================================================================");

    }

}