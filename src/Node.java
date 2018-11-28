import java.util.ArrayList;

/**
 * Represents a node in the search tree, holding the coordinates of each block, a pointer to it's parent, the
 * level (depth) of the node and the cost to reach it which is used by A*
 */
class Node implements Comparable<Node> {

    // Pointer to the parent node
    private Node parent;
    //Store the location of the actor
    private int x, y, Ax, Ay, Bx, By, Cx, Cy;
    // The depth of the node
    private int level;
    // The cost of reaching the node - used by A*
    private int cost;

    /**
     * Instantiates a new node and sets all of it's properties
     * @param agentX x position of the agent
     * @param agentY y position of the agent
     * @param Ax x position of the A block
     * @param Ay y position of the A block
     * @param Bx x position of the B block
     * @param By y position of the B block
     * @param Cx x position of the C block
     * @param Cy y position of the C block
     * @param parent parent of the node
     * @param level depth of the node
     * @param cost cost of reaching the node
     */
    Node(int agentX, int agentY, int Ax, int Ay, int Bx, int By, int Cx, int Cy, Node parent, int level, int cost) {
        this.x = agentX;
        this.y = agentY;
        this.Ax = Ax;
        this.Ay = Ay;
        this.Bx = Bx;
        this.By = By;
        this.Cx = Cx;
        this.Cy = Cy;
        this.parent = parent;
        this.level = level;
        this.cost = cost;
    }

    /**
     * @return parent of this node
     */
    Node getParent() {
        return parent;
    }

    /**
     * @return the level of the node (depth)
     */
    int getLevel() {
        return level;
    }

    /**
     * @return the cost of reaching the node
     */
    private int getCost() {
        return cost;
    }

    /**
     * Print the current world state to the console
     */
    void drawPuzzle() {
        for (int y = 0; y < Main.SIZE; y++) {
            System.out.print("|");
            for (int x = 0; x < Main.SIZE; x++) {
                if (x == Ax && y == Ay) {
                    System.out.print("A");
                } else if (x == Bx && y == By) {
                    System.out.print("B");
                } else if (x == Cx && y == Cy) {
                    System.out.print("C");
                } else if (x == this.x && y == this.y) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Generates nodes for moving in every possible direction from the current state of the world
     * @return an array list of the child nodes
     */
    ArrayList<Node> generateChildren() {
        ArrayList<Node> children = new ArrayList<>();
        children.add(move('r'));
        children.add(move('l'));
        children.add(move('u'));
        children.add(move('d'));
        return children;
    }

    /**
     * Generates a new node from moving the agent in a given direction
     * @param direction to move the agent
     * @return The new node
     */
    private Node move(char direction) {
        Node child;
        int[] newPositions;

         if (direction == 'r' && x + 1 <= Main.SIZE - 1) {
             newPositions = generateNewPositions(1, 0);
             // Create the new node
             child = new Node(x + 1, y, newPositions[0], newPositions[1], newPositions[2], newPositions[3],
                     newPositions[4], newPositions[5], this, level + 1, cost + 1);
             return child;

         } else if (direction == 'l' && x - 1 >= 0) {
             newPositions = generateNewPositions(-1, 0);
             // Create the new node
             child = new Node(x - 1, y, newPositions[0], newPositions[1], newPositions[2], newPositions[3],
                     newPositions[4], newPositions[5], this, level + 1, cost + 1);
             return child;

         } else if (direction == 'u' && y - 1 >= 0) {
             newPositions = generateNewPositions(0, -1);
             // Create the new node
             child = new Node(x, y - 1, newPositions[0], newPositions[1], newPositions[2], newPositions[3],
                     newPositions[4], newPositions[5], this, level + 1, cost + 1);
             return child;

         } else if (direction == 'd' && y + 1 <= Main.SIZE - 1) {
             newPositions = generateNewPositions(0, 1);
             // Create the new node
             child = new Node(x, y + 1, newPositions[0], newPositions[1], newPositions[2], newPositions[3],
                     newPositions[4], newPositions[5], this, level + 1, cost + 1);
             return child;
         }
         return null;
    }

    /**
     * Generate the new coordinates of each block, moving the agent and if this conflicts with A, B or C also moving them
     * @param xMov Distance to move in the x direction
     * @param yMov Distance to move in the y direction
     * @return An array containing the new coordinates of each block
     */
    private int[] generateNewPositions(int xMov, int yMov) {
        int newAx = Ax;
        int newAy = Ay;
        int newBx = Bx;
        int newBy = By;
        int newCx = Cx;
        int newCy = Cy;

        if (x + xMov == Ax && y + yMov == Ay) {
            newAx = x;
            newAy = y;
        } else if (x + xMov == Bx && y + yMov == By) {
            newBx = x;
            newBy = y;
        } else if (x + xMov == Cx && y + yMov == Cy) {
            newCx = x;
            newCy = y;
        }
        return new int[]{newAx, newAy, newBx, newBy, newCx, newCy};
    }

    /**
     * Checks if the puzzle is in the required state
     * @return True if the puzzle matches the goal state
     */
    boolean isGoal() {
        return Main.Ax == Ax && Main.Ay == Ay && Main.Bx == Bx && Main.By == By && Main.Cx == Cx && Main.Cy == Cy;
    }

    /**
     * Calculate the heuristic for this node by adding the depth of the node to the Manhattan distance A B and C
     * from their goal positions
     */
    void heuristic() {
        cost = level + (Math.abs(Ax - Main.Ax) + Math.abs(Ay - Main.Ay)) +
                (Math.abs(Bx - Main.Bx) + Math.abs(By - Main.By)) +
                (Math.abs(Cx - Main.Cx) + Math.abs(Cy - Main.Cy));
    }

    /**
     * Compares two nodes to sort the priority queue for A*
     * @param node the node to compare to this
     * @return the cost of this node - the comparing node OR if they are the same the level of the node
     */
    @Override
    public int compareTo(Node node) {
        int cost = this.cost - node.getCost();
        // If they have the same heuristic prioritise by level
        if (cost == 0) {
            return Integer.compare(node.getLevel(), this.getLevel());
        } else {
            return cost;
        }
    }
}