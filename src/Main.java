import java.io.Console;
import java.util.Scanner;

/**
 * Main class responsible for starting the tests
 */
class Main {

    private Scanner in = new Scanner(System.in);
    private Node startNode;

    /**
     * Start the program
     */
    public static void main(String[] args) {
        new Main();
    }

    private Main() {
        int choice = menu();
        setStartState();

        Node goal = null;
        switch (choice) {
            case 1:
                BreadthFirst bf = new BreadthFirst(startNode);
                goal = bf.run();
                break;
            case 2:
                DepthFirst df = new DepthFirst(startNode);
                goal = df.run();
                break;
            case 3:
                IterativeDeepening id = new IterativeDeepening(startNode, 100);
                goal = id.run();
                break;
            case 4:
                //
                break;
        }
        printTrace(goal);
    }

    /**
     * Sets initial board state and generates the root node
     */
    private void setStartState() {
        String[][] puzzle = new String[Node.SIZE][Node.SIZE];

        //Place the tower blocks
        puzzle[0][0] = "A";
        //puzzle[3][1] = "B";
        //puzzle[3][2] = "C";
        //Place the agent
        puzzle[1][1] = "*";

        startNode = new Node(puzzle, 1, 1, null);
    }

    /**
     * Displays the different search methods for the user to choose from
     * @return users menu choice
     */
    private int menu() {
        System.out.println("--------------------------------");
        System.out.println("          Blocks World          ");
        System.out.println("--------------------------------");
        System.out.println("          Jack Corbett          ");
        System.out.println();
        System.out.println("Which search do you want to use:");
        System.out.println("1. Breadth First");
        System.out.println("2. Depth First");
        System.out.println("3. Iterative Deepening");
        System.out.println("4. A*");
        System.out.print("Input: ");
        return in.nextInt();
    }

    /**
     * Prints the state of the board working up through the tree to the root
     * @param bottom The node we want to work back up the tree from
     */
    private void printTrace(Node bottom) {
        System.out.println();
        System.out.println("The moves (working back from the goal) were: ");
        System.out.println();
        Node node = bottom;
        node.drawPuzzle();
        Node parent;
        while (node.getParent() != null) {
            parent = node.getParent();
            parent.drawPuzzle();
            node = parent;
        }
    }
}