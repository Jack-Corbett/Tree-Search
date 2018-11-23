import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

class DepthFirst extends Search {

    private Stack<Node> stack;
    private Random rand;

    DepthFirst(Node root) {
        initialise(root);
        stack = new Stack<>();
        rand = new Random();
    }

    @Override
    Node run() {
        // Push the root node to the stack
        stack.push(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            current.generateChildren();

            // Randomly shuffle the children - this avoids getting stuck
            ArrayList<Node> children = current.getChildren();
            Collections.shuffle(children, rand);

            // Iterate through the children
            for (Node child : children) {
                nodesExpanded ++;
                if (child.isGoal()) {
                    printNodesExpanded();
                    return child;
                }

                if (!child.getVisited()) {
                    child.setVisited();
                    stack.push(child);
                }
            }
        }
        // Return null if the goal state is not found in the tree
        return null;
    }
}