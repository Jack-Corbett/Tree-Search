import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

@SuppressWarnings("Duplicates")
class AStar extends Search {

    private Queue<Node> pqueue;

    AStar(Node root) {
        initialise(root);
        pqueue = new PriorityQueue<>(Comparator.comparingInt(this::f));
        printName();
    }

    @Override
    Node run() {
        // Add the root node to the queue
        pqueue.add(root);

        while (!pqueue.isEmpty()) {
            Node current = pqueue.poll();

            if (current.isGoal()) {
                printNodesExpanded();
                printLevel(current);
                return current;
            }

            nodesExpanded++;
            current.generateChildren();

            // Add all of the children to the priority queue
            pqueue.addAll(current.getChildren());
        }
        return null;
    }

    private int f(Node node) {
        return h(node) + node.getLevel();
    }

    // Manhattan distance calculate the distance of A B and C from their goal
    private int h(Node node) {
        int manhattanDistance = 0;
        String[][] puzzle = node.getPuzzle();
        // Store the distance in the x and y directions
        int difX;
        int difY;

        for (int x = 0; x < Main.SIZE; x++) {
            for (int y = 0; y < Main.SIZE; y++) {
                String value = puzzle[x][y];

                if (value != null) {
                    switch (value) {
                        case "A":
                            difX = x - 1;
                            difY = y - 1;
                            manhattanDistance += Math.abs(difX) + Math.abs(difY);
                            break;
                        case "B":
                            difX = x - 2;
                            difY = y - 1;
                            manhattanDistance += Math.abs(difX) + Math.abs(difY);
                            break;
                        case "C":
                            difX = x - 3;
                            difY = y - 1;
                            manhattanDistance += Math.abs(difX) + Math.abs(difY);
                            break;
                    }
                }
            }
        }
        return manhattanDistance;
    }

    @Override
    void printName() {
        System.out.println();
        System.out.println("--------------------------------");
        System.out.println("A*");
    }
}
