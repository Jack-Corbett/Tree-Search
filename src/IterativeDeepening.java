public class IterativeDeepening {

    private int nodesExpanded = 0;

    IterativeDeepening(Node root, int limit) {
        for (int i = 0; i <= limit; i++) {
            if (search(root, i)) {
                System.out.println(nodesExpanded);
                return;
            }
        }
    }

    private boolean search(Node current, int limit) {
        if (current.isGoal()) return true;

        if (limit <= 0) return false;

        current.drawPuzzle();

        current.generateChildren();

        for (Node child : current.getChildren()) {
            nodesExpanded ++;
            child.drawPuzzle();

            if (search(child, limit-1)) {
                return true;
            }
        }
        return false;
    }
}
