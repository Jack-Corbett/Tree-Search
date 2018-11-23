import java.util.LinkedList;
import java.util.Queue;

class BreadthFirst extends Search {

    private Queue<Node> queue;

    BreadthFirst(Node root) {
        initialise(root);
        queue = new LinkedList<>();
    }

    @Override
    Node run() {
        // Push the root node to the queue
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            current.generateChildren();

            // Iterate through the children
            for (Node child : current.getChildren()) {
                nodesExpanded ++;
                if (child.isGoal()) {
                    printNodesExpanded();
                    return child;
                }

                if (!child.getVisited()) {
                    child.setVisited();
                    queue.add(child);
                }
            }
        }
        // Return null if the goal state is not found in the tree
        return null;
    }
}