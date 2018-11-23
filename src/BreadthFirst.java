import java.util.LinkedList;
import java.util.Queue;

class BreadthFirst {

    BreadthFirst(Node root) {
        int nodesExpanded = 0;
        Queue<Node> queue = new LinkedList<>();

        root.setVisited();
        queue.add(root);
        boolean goal = false;

        while (!queue.isEmpty() && !goal) {
            Node current = queue.poll();
            current.drawPuzzle();

            current.generateChildren();

            // Iterate through the children
            for (Node child : current.getChildren()) {
                nodesExpanded ++;
                child.drawPuzzle();

                if (child.isGoal()) {
                    goal = true;
                    break;
                }

                if (!child.getVisited()) {
                    child.setVisited();
                    queue.add(child);
                }
            }
        }
        System.out.println("Expanded: " + nodesExpanded + " nodes");
    }
}