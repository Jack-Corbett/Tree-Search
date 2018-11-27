import java.util.ArrayList;

/**
 *
 */
class Node {

    private Node parent;
    private ArrayList<Node> children = new ArrayList<>();
    private String[][] puzzle;
    //Store the location of the actor
    private int x, y;
    // The depth of the node
    private int level;

    Node(String[][] puzzle, int agentY, int agentX, Node parent, int level) {
        this.puzzle = puzzle;
        this.y = agentY;
        this.x = agentX;
        this.parent = parent;
        this.level = level;
    }

    private String[][] copyPuzzle(String[][] puzzle) {
        String[][] newPuzzle = new String[Main.SIZE][Main.SIZE];
        for (int i = 0; i < Main.SIZE; i++)
            newPuzzle[i] = puzzle[i].clone();
        return newPuzzle;
    }

    String[][] getPuzzle() {
        return puzzle;
    }

    int getLevel() {
        return level;
    }

    ArrayList<Node> getChildren() {
        return children;
    }

    Node getParent() {
        return parent;
    }

    void drawPuzzle() {
        for (String[] row : puzzle) {
            System.out.print("|");
            for (String field : row) {
                if (field != null) {
                    System.out.print(field);
                } else {
                    System.out.print(" ");
                }
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.println();
    }

    void generateChildren() {
        move('r');
        move('l');
        move('u');
        move('d');
    }

    private void move(char direction) {
        Node child;
        String[][] newPuzzle = copyPuzzle(puzzle);
        String temp = newPuzzle[y][x];

         if (direction == 'r' && x + 1 <= Main.SIZE - 1) {
             // Swap the tiles
             newPuzzle[y][x] = newPuzzle[y][x + 1];
             newPuzzle[y][x + 1] = temp;
             // Create the new node
             child = new Node(newPuzzle, y, x + 1, this, this.level + 1);
             children.add(child);

         } else if (direction == 'l' && x - 1 >= 0) {
             // Swap the tiles
             newPuzzle[y][x] = newPuzzle[y][x - 1];
             newPuzzle[y][x - 1] = temp;
             // Create the new node
             child = new Node(newPuzzle, y, x - 1, this, this.level + 1);
             children.add(child);

         } else if (direction == 'u' && y - 1 >= 0) {
            // Swap the tiles
             newPuzzle[y][x] = newPuzzle[y - 1][x];
             newPuzzle[y - 1][x] = temp;
             // Create the new node
             child = new Node(newPuzzle, y - 1, x, this, this.level + 1);
             children.add(child);

         } else if (direction == 'd' && y + 1 <= Main.SIZE - 1) {
            // Swap the tiles
             newPuzzle[y][x] = newPuzzle[y + 1][x];
             newPuzzle[y + 1][x] = temp;
             // Create the new node
             child = new Node(newPuzzle, y + 1, x, this, this.level + 1);
             children.add(child);
         }
    }

    /**
     * Checks if the puzzle is in the required state
     * @return True if the puzzle matches the goal state
     */
    boolean isGoal() {
        return "C".equals(puzzle[Main.Cy][Main.Cx]) && "B".equals(puzzle[Main.By][Main.Bx]) && "A".equals(puzzle[Main.Ay][Main.Ax]);
    }
}
