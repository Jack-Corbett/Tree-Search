import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

class IterativeDeepening extends Search {

    private int depthLimit;
    private Stack<Node> stack;

    IterativeDeepening(Node root) {
        initialise(root);
        depthLimit = 0;
        stack = new Stack<>();
        printName();
    }

    @Override
    Node run() {
        // Push the root node to the stack
        stack.push(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            if (current.isGoal()) {
                printNodesExpanded();
                printLevel(current);
                return current;
            } else if (current.getLevel() < depthLimit) {
                nodesExpanded ++;
                current.generateChildren();
                // Randomly shuffle the children - this avoids getting stuck
                ArrayList<Node> children = current.getChildren();
                Collections.shuffle(children);

                // Iterate through the children and push them to the stack
                for (Node child : children) {
                    stack.push(child);
                }
            }
            if (stack.isEmpty()) {
                stack.push(root);
                depthLimit++;
            }
        }
        // Return null if the goal state is not found in the tree
        return null;
    }

    @Override
    void printName() {
        System.out.println();
        System.out.println("--------------------------------");
        System.out.println("Iterative Deepening");
    }
}