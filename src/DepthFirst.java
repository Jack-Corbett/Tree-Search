import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

class DepthFirst {

    DepthFirst(Node root) {
        Random rand = new Random();
        int nodesExpanded = 0;
        Stack<Node> stack = new Stack<>();

        root.setVisited();
        // Push the root node to the stack
        stack.push(root);
        boolean goal = false;

        while (!stack.isEmpty() && !goal) {
            Node current = stack.pop();
            current.drawPuzzle();

            current.generateChildren();

            // Randomly shuffle the children - this avoids getting stuck
            ArrayList<Node> children = current.getChildren();
            Collections.shuffle(children, rand);

            // Iterate through the children
            for (Node child : children) {
                nodesExpanded ++;
                child.drawPuzzle();

                if (child.isGoal()) {
                    goal = true;
                    break;
                }

                if (!child.getVisited()) {
                    child.setVisited();
                    stack.push(child);
                }
            }
        }
        System.out.println("Expanded: " + nodesExpanded + " nodes");
    }
}