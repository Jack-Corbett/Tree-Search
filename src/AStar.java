import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Implements the A* search algorithm
 */
class AStar extends Search {

    private Queue<Node> pqueue;

    /**
     * Set the root, nodes expanded to 0, instantiate the priority queue and print the name
     * @param root node of the tree to search
     */
    AStar(Node root) {
        initialise(root);
        pqueue = new PriorityQueue<>();
        printName();
    }

    /**
     * Runs the A* algorithm
     * @return the goal node
     */
    @Override
    Node run() {
        // Add the root node to the queue
        pqueue.add(root);

        while (!pqueue.isEmpty()) {
            ArrayList<Node> children;
            Node current = pqueue.poll();

            if (current.isGoal()) {
                printNodesExpanded();
                printLevel(current);
                return current;
            }
            // System.out.println("Current:");
            // current.drawPuzzle();
            nodesExpanded++;
            children = current.generateChildren();

            // Add all of the children to the priority queue
            // System.out.println("Children:");
            for (Node child : children) {
                if (child != null) {
                    child.heuristic();
                    // child.drawPuzzle();
                    pqueue.add(child);
                }
            }
        }
        return null;
    }

    /**
     * Print the formatted name to the console
     */
    @Override
    void printName() {
        super.printName();
        System.out.println("A*");
    }
}
