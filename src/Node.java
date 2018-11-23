import java.util.ArrayList;

/**
 *
 */
class Node {

    static final int SIZE = 2;
    private ArrayList<Node> children = new ArrayList<>();
    private String[][] puzzle;
    //Store the location of the actor
    private int x, y;
    private Boolean visited;

    Node(String[][] puzzle, int agentX, int agentY) {
        this.puzzle = puzzle;
        this.x = agentX;
        this.y = agentY;
        visited = false;
    }

    private String[][] copyPuzzle(String[][] puzzle) {
        String[][] newPuzzle = new String[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            newPuzzle[i] = puzzle[i].clone();
        return newPuzzle;
    }

    ArrayList<Node> getChildren() {
        return children;
    }

    void setVisited() {
        visited = true;
    }

    Boolean getVisited() {
        return visited;
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

    void move(char direction) {
        Node child;
        String[][] newPuzzle = copyPuzzle(puzzle);
        String temp = newPuzzle[x][y];

         if (direction == 'r' && x + 1 <= SIZE - 1) {
             // Swap the tiles
             newPuzzle[x][y] = newPuzzle[x + 1][y];
             newPuzzle[x + 1][y] = temp;
             // Create the new node
             child = new Node(newPuzzle, x + 1, y);
             children.add(child);

         } else if (direction == 'l' && x - 1 >= 0) {
             // Swap the tiles
             newPuzzle[x][y] = newPuzzle[x - 1][y];
             newPuzzle[x - 1][y] = temp;
             // Create the new node
             child = new Node(newPuzzle, x - 1, y);
             children.add(child);

         } else if (direction == 'u' && y - 1 >= 0) {
            // Swap the tiles
             newPuzzle[x][y] = newPuzzle[x][y - 1];
             newPuzzle[x][y - 1] = temp;
             // Create the new node
             child = new Node(newPuzzle, x, y - 1);
             children.add(child);

         } else if (direction == 'd' && y + 1 <= SIZE - 1) {
            // Swap the tiles
             newPuzzle[x][y] = newPuzzle[x][y + 1];
             newPuzzle[x][y + 1] = temp;
             // Create the new node
             child = new Node(newPuzzle, x, y + 1);
             children.add(child);
         }
    }

    /**
     * Checks if the puzzle is in the required state
     * @return True if the puzzle matches the goal state
     */
    boolean isGoal() {
        //return "C".equals(puzzle[3][1]) && "B".equals(puzzle[2][1]) && "A".equals(puzzle[1][1]);
        return "A".equals(puzzle[1][0]);
    }
}
