import java.util.LinkedList;
import java.util.Queue;

class BreadthFirst extends Search {

    private Queue<Node> queue;

    BreadthFirst(Node root) {
        initialise(root);
        queue = new LinkedList<>();
        printName();
    }

    @Override
    Node run() {
        // Push the root node to the queue
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.isGoal()) {
                printNodesExpanded();
                printLevel(current);
                return current;
            }
            nodesExpanded ++;
            current.generateChildren();

            // Add the children to the queue
            queue.addAll(current.getChildren());
        }
        // Return null if the goal state is not found in the tree
        return null;
    }

    @Override
    void printName() {
        System.out.println();
        System.out.println("--------------------------------");
        System.out.println("Breadth First");
    }
}