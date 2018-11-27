import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings("Duplicates")
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
            System.out.println("Removing:");
            current.drawPuzzle();
            nodesExpanded ++;
            current.generateChildren();

            // Add the children to the queue
            System.out.println("Expanding:");
            for (Node child : current.getChildren()) {
                child.drawPuzzle();
                queue.add(child);
            }
            //queue.addAll(current.getChildren());
        }
        // Return null if the goal state is not found in the tree
        return null;
    }

    @Override
    void printName() {
        super.printName();
        System.out.println("Breadth First");
    }
}