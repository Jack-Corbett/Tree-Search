import java.util.Scanner;

/**
 * Main class responsible for running the search algorithms on blocks world
 * @author Jack Corbett
 */
class Main {

    // Configuration Settings
    static final int SIZE = 4;
    // Goal coordinates of A, B and C
    static final int Ax = 1;
    static final int Ay = 1;
    static final int Bx = 1;
    static final int By = 2;
    static final int Cx = 1;
    static final int Cy = 3;
    // Initial agent position
    private static final int agentX = 1;
    private static final int agentY = 1;

    private Scanner in;
    private Node startNode;

    /**
     * Start the program
     */
    public static void main(String[] args) {
        new Main();
    }

    private Main() {
        in = new Scanner(System.in);
        while (true) {
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
                    IterativeDeepening id = new IterativeDeepening(startNode);
                    goal = id.run();
                    break;
                case 4:
                    AStar as = new AStar(startNode);
                    goal = as.run();
                    break;
                case 5:
                    System.exit(0);
            }
            printTrace(goal);
        }
    }

    /**
     * Sets initial board state and generates the root node
     */
    private void setStartState() {
        String[][] puzzle = new String[SIZE][SIZE];

        //Place the tower blocks
        puzzle[0][1] = "A";
        puzzle[2][1] = "B";
        puzzle[3][1] = "C";
        //Place the agent
        puzzle[agentY][agentX] = "*";

        startNode = new Node(puzzle, agentY, agentX, null, 0);
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
        System.out.println("5. Quit");
        System.out.print("Input: ");
        return in.nextInt();
    }

    /**
     * Prints the state of the board working up through the tree to the root to a maximum of 10
     * @param bottom The node we want to work back up the tree from
     */
    private void printTrace(Node bottom) {
        int count = 0;
        System.out.println();
        System.out.println("The moves (working back from the goal) were: ");
        System.out.println();
        Node node = bottom;
        node.drawPuzzle();
        Node parent;
        while (node.getParent() != null) {
            count++;
            if (count > 10) {
                System.out.println("Only showing the first 10 moves...");
                return;
            }
            parent = node.getParent();
            parent.drawPuzzle();
            node = parent;
        }
    }
}