abstract class Search {

    int nodesExpanded;
    Node root;

    void initialise(Node root) {
        nodesExpanded = 0;
        this.root = root;
    }

    abstract Node run();

    abstract void printName();

    void printNodesExpanded() {
        System.out.println("Expanded: " + nodesExpanded + " nodes");
    }

    void printLevel(Node node) {
        System.out.println("Solution found at level: " + node.getLevel());
    }
}
