import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Implements the breadth first search algorithm
 */
class BreadthFirst extends Search {

    private Queue<Node> queue;

    /**
     * Set the root, nodes expanded to 0, instantiate the queue and print the name
     * @param root node of the tree to search
     */
    BreadthFirst(Node root) {
        initialise(root);
        queue = new LinkedList<>();
        printName();
    }

    /**
     * Runs the breadth first search algorithm
     * @return the goal node
     */
    @Override
    Node run() {
        // Push the root node to the queue
        queue.add(root);

        while (!queue.isEmpty()) {
            ArrayList<Node> children;
            Node current = queue.poll();

            if (current.isGoal()) {
                printNodesExpanded();
                printLevel(current);
                return current;
            }
            // System.out.println("Current:");
            // current.drawPuzzle();
            nodesExpanded ++;
            children = current.generateChildren();

            // Add the children to the queue
            // System.out.println("Children:");
            for (Node child : children) {
                if (child != null) {
                    // child.drawPuzzle();
                    queue.add(child);
                }
            }
        }
        // Return null if the goal state is not found in the tree
        return null;
    }

    /**
     * Print the formatted name to the console
     */
    @Override
    void printName() {
        super.printName();
        System.out.println("Breadth First");
    }
}