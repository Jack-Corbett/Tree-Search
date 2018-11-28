import java.util.ArrayList;
import java.util.Stack;

/**
 * Implements the iterative deepening search algorithm
 */
class IterativeDeepening extends Search {

    private int depthLimit;
    private Stack<Node> stack;

    /**
     * Set the root, nodes expanded to 0, depth limit to 0, instantiate the stack and print the name
     * @param root node of the tree to search
     */
    IterativeDeepening(Node root) {
        initialise(root);
        depthLimit = 0;
        stack = new Stack<>();
        printName();
    }

    /**
     * Runs the iterative deepening algorithm
     * @return the goal node
     */
    @Override
    Node run() {
        // Push the root node to the stack
        stack.push(root);

        while (!stack.isEmpty()) {
            ArrayList<Node> children;
            Node current = stack.pop();
            // System.out.println("Current:");
            // current.drawPuzzle();

            if (current.isGoal()) {
                printNodesExpanded();
                printLevel(current);
                return current;
            } else if (current.getLevel() < depthLimit) {
                nodesExpanded ++;
                children = current.generateChildren();
                // System.out.println("Children:");
                // Iterate through the children and push them to the stack
                for (Node child : children) {
                    if (child != null) {
                        // child.drawPuzzle();
                        stack.push(child);
                    }
                }
            }
            if (stack.isEmpty()) {
                // System.out.println("No solution for current depth: " + depthLimit);
                stack.push(root);
                depthLimit++;
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
        System.out.println("Iterative Deepening");
    }
}