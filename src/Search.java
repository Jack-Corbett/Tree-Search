abstract class Search {

    int nodesExpanded;
    Node root;

    void initialise(Node root) {
        nodesExpanded = 0;
        this.root = root;
        root.setVisited();
    }

    abstract Node run();

    void printNodesExpanded() {
        System.out.println();
        System.out.println("--------------------------------");
        System.out.println("Expanded: " + nodesExpanded + " nodes");
    }
}
