/**
 * Main class responsible for cold starting the tests
 */
class Main {
    public static void main(String[] args) {
        System.out.println("Blocksworld");

        String[][] puzzle = new String[Node.SIZE][Node.SIZE];

        //Place the tower blocks
        puzzle[0][0] = "A";
        //puzzle[3][1] = "B";
        //puzzle[3][2] = "C";
        //Place the agent
        puzzle[1][1] = "*";

        Node startNode = new Node(puzzle, 1, 1);
        //BreadthFirst bf = new BreadthFirst(startNode);
        //DepthFirst df = new DepthFirst(startNode);
        IterativeDeepening id = new IterativeDeepening(startNode, 100);
    }
}