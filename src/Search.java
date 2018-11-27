abstract class Search {

    int nodesExpanded;
    Node root;

    void initialise(Node root) {
        nodesExpanded = 0;
        this.root = root;
    }

    abstract Node run();

    void printName() {
        System.out.println();
        System.out.println("--------------------------------");
    }

    void printNodesExpanded() {
        System.out.println("Expanded: " + nodesExpanded + " nodes");
    }

    void printLevel(Node node) {
        System.out.println("Solution found at level: " + node.getLevel());
    }
}
