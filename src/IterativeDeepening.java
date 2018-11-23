class IterativeDeepening extends Search {

    private int maxDepth;

    IterativeDeepening(Node root, int maxDepth) {
        initialise(root);
        this.maxDepth = maxDepth;
    }

    @Override
    Node run() {
        for (int i = 0; i <= maxDepth; i++) {
            Node node = search(root, i);
            if (node != null) {
                printNodesExpanded();
                node.drawPuzzle();
                return node;
            }
        }
        return null;
    }

    private Node search(Node current, int limit) {
        if (current.isGoal()) return current;

        if (limit <= 0) return null;

        current.generateChildren();

        for (Node child : current.getChildren()) {
            nodesExpanded ++;

            Node node = search(child, limit - 1);
            if (node != null) {
                return node;
            }
        }
        return null;
    }
}