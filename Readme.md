This java application Implements sudoku solver.

- Software Requirements:

    - Java 8
    - Intellij/Eclipse IDE
    - Maven
    
- Application Setup:

    * Download the application and unzip in local repository
    * Open Intellij and click on File -> Open and navigate to project folder and <br/> double click on sudoku project's pom.xml
    * Run Maven clean install
    * Now the project is ready to be evaluated by running Unit tests

- To test run the application, open SearchTest.java under test directory and run the tests.<br/>
There are tests that have the puzzles supplied as part of questionnaire. 

- The solution has the following two variants:

    * DFS(Default)
    * BFS(Use FrontierNodeSet's setBfs() method to enable BFS)

- BenchMarkTest.java has a test that benchmarks the performance of the application.

- I have made an assumption that this is a 9 X 9 sudoku board. But the same solution works for 16 X 16

- At every index, validation is made to see what are the possible values that can be placed at that index and only after the validation is passed, SearchNode is being created and added to the current node's children collection. This is automatically helping in pruning branches that are not valid.
