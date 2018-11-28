import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Implements the depth first search algorithm
 */
class DepthFirst extends Search {

    private Stack<Node> stack;

    /**
     * Set the root, nodes expanded to 0, instantiate the stack and print the name
     * @param root node of the tree to search
     */
    DepthFirst(Node root) {
        initialise(root);
        stack = new Stack<>();
        printName();
    }

    /**
     * Runs the depth first search algorithm
     * @return the goal node
     */
    @Override
    Node run() {
        // Push the root node to the stack
        stack.push(root);

        while (!stack.isEmpty()) {
            ArrayList<Node> children;
            Node current = stack.pop();

            if (current.isGoal()) {
                printNodesExpanded();
                printLevel(current);
                return current;
            }
            // System.out.println("Current:");
            // current.drawPuzzle();
            nodesExpanded ++;
            // Randomly shuffle the children - this avoids getting stuck
            children = current.generateChildren();
            Collections.shuffle(children);

            // System.out.println("Children:");
            // Iterate through the children and push them to the stack
            for (Node child : children) {
                if (child != null) {
                    // child.drawPuzzle();
                    stack.push(child);
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
        System.out.println("Depth First");
    }
}