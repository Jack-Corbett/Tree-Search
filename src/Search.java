/**
 * Abstract class to provide basic elements each search requires
 */
abstract class Search {

    int nodesExpanded;
    Node root;

    /**
     * Sets the nodes expanded to 0 and sets the root
     * @param root of the graph
     */
    void initialise(Node root) {
        nodesExpanded = 0;
        this.root = root;
    }

    /**
     * Runs the actual search algorithm - implemented by each child class
     * @return the goal nodes
     */
    abstract Node run();

    /**
     * Format the printed search name
     */
    void printName() {
        System.out.println();
        System.out.println("--------------------------------");
    }

    /**
     * Print the number of nodes expanded by the search
     */
    void printNodesExpanded() {
        System.out.println("Expanded: " + nodesExpanded + " node(s)");
    }

    /**
     * Print the depth of the solution
     * @param node in the goal state
     */
    void printLevel(Node node) {
        System.out.println("Solution found at level: " + node.getLevel());
    }
}
