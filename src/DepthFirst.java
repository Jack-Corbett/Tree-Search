import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

class DepthFirst extends Search {

    private Stack<Node> stack;

    DepthFirst(Node root) {
        initialise(root);
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
            }
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
        // Return null if the goal state is not found in the tree
        return null;
    }

    @Override
    void printName() {
        super.printName();
        System.out.println("Depth First");
    }
}