
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Game extends JPanel implements ActionListener, MouseListener {

    
    JButton button[][];
    
    int[][] counts;
    boolean[][] reveal;
    public boolean lost=false;
    public boolean win= false;

    
    int row;
    int col,mine;
    
    public int remaining;

    public Timer timer;
    Color c[] = {Color.BLACK, Color.blue, Color.green,Color.yellow, Color.darkGray, Color.cyan, Color.magenta, Color.orange, };

    Game(int n, int m, int mines) {
        //setBackground(Color.WHITE);
        //grid
        setLocation(100, 50);
        setSize(1000, 700);
        row = n;
        col = m;
        JButton buttons[][] = new JButton[n][m];
        button = buttons;
        int[][] countss = new int[n][m];
        boolean[][] reveals = new boolean[n][m];
        counts=countss;
        reveal=reveals;

        setLayout(new GridLayout(row, col));
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                button[i][j] = new JButton();
                button[i][j].addMouseListener(this);

                add(button[i][j]);
            }
        }
        
         mine=mines;
        createMines(mines);
       

    }

    private int countNeighbours(int i, int j) {
        int neighbour = 0;
        int dx[] = {1, 1, 1, -1, -1, -1, 0, 0};
        int dy[] = {0, 1, -1, 0, 1, -1, 1, -1};
        for (int k = 0; k < 8; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            if (x >= 0 && x < row && y >= 0 && y < col) {
                if (counts[x][y] == -1) {
                    neighbour++;
                }
            }
        }

        return neighbour;
    }

    private void createMines(int mines) {
    for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                counts[i][j] = 1;
                reveal[i][j] = false;
            }

        }

        for (int i = 0; i < mines; i++) {

            int n = (int) (Math.random() * row);
            int m = (int) (Math.random() * col);

            if (counts[n][m] == 1) {
                counts[n][m] = -1;

            } else {
                i--;
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (counts[i][j] == 1) {
                    int neighbour = countNeighbours(i, j);
                    counts[i][j] = neighbour;
                }
            }
        }
    }

    public void revealZeros(int i, int j) {
        for (int y = j; y < col; y++) {///right and down
            int empty = 0;
            if (counts[i][y] == -1 || counts[i][y] != 0 || reveal[i][y] == true) {
                if (counts[i][y] != 0) {
                    button[i][y].setEnabled(false);
                    button[i][y].setFont(new Font("Serif", Font.BOLD, 17));
                    button[i][y].setText(counts[i][y] + "");
                    button[i][y].setForeground(c[counts[i][y] - 1]);
                }
                break;
            }
            for (int x = i; x < row; x++) {
                if (counts[x][y] == -1 || empty == 5 || reveal[x][y] == true) {
                    break;
                } else {

                    button[x][y].setEnabled(false);
                    if (counts[x][y] != 0) {

                        button[x][y].setEnabled(false);
                        button[x][y].setFont(new Font("Serif", Font.BOLD, 17));
                        button[x][y].setText(counts[x][y] + "");
                        button[x][y].setForeground(c[counts[x][y] - 1]);
                        break;
                    } else {
                        empty++;
                    }

                }
            }
        }
        for (int y = j; y < col; y++) {///right and up
            int empty = 0;
            if (counts[i][y] == -1 || counts[i][y] != 0 || reveal[i][y] == true) {
                if (counts[i][y] != 0) {

                    button[i][y].setEnabled(false);
                    button[i][y].setFont(new Font("Serif", Font.BOLD, 17));
                    button[i][y].setText(counts[i][y] + "");
                    button[i][y].setForeground(c[counts[i][y] - 1]);
                }
                break;
            }
            for (int x = i - 1; x >= 0; x--) {
                if (counts[x][y] == -1 || empty == 5 || reveal[x][y] == true) {
                    break;
                } else {

                    button[x][y].setEnabled(false);

                    if (counts[x][y] != 0) {
                        button[x][y].setText(counts[x][y] + "");
                        button[x][y].setFont(new Font("Serif", Font.BOLD, 17));
                        button[x][y].setText(counts[x][y] + "");
                        button[x][y].setForeground(c[counts[x][y] - 1]);

                        break;
                    } else {
                        empty++;
                    }
                }
            }
        }

        for (int y = j - 1; y >= 0; y--) {///left and up
            int empty = 0;
            if (counts[i][y] == -1 || counts[i][y] != 0 || reveal[i][y] == true) {
                if (counts[i][y] != 0) {

                    button[i][y].setEnabled(false);
                    button[i][y].setFont(new Font("Serif", Font.BOLD, 17));
                    button[i][y].setText(counts[i][y] + "");
                    button[i][y].setForeground(c[counts[i][y] - 1]);
                }
                break;
            }
            for (int x = i - 1; x >= 0; x--) {
                if (counts[x][y] == -1 || empty == 5 || reveal[x][y] == true) {
                    break;
                } else {

                    button[x][y].setEnabled(false);
                    if (counts[x][y] != 0) {
                        button[x][y].setFont(new Font("Serif", Font.BOLD, 17));
                        button[x][y].setText(counts[x][y] + "");
                        button[x][y].setForeground(c[counts[x][y] - 1]);

                        break;
                    } else {
                        empty++;
                    }

                }
            }
        }

        for (int y = j - 1; y >= 0; y--) {///left and down
            int empty = 0;
            if (counts[i][y] == -1 || counts[i][y] != 0 || reveal[i][y] == true) {
                if (counts[i][y] != 0) {
                    button[i][y].setEnabled(false);
                    button[i][y].setFont(new Font("Serif", Font.BOLD, 17));
                    button[i][y].setText(counts[i][y] + "");
                    button[i][y].setForeground(c[counts[i][y] - 1]);

                }
                break;
            }
            for (int x = i; x < row; x++) {
                if (counts[x][y] == -1 || empty == 5 || reveal[x][y] == true) {
                    break;
                } else {
                    button[x][y].setEnabled(false);
                    if (counts[x][y] != 0) {
                        button[x][y].setFont(new Font("Serif", Font.BOLD, 17));
                        button[x][y].setText(counts[x][y] + "");
                        button[x][y].setForeground(c[counts[x][y] - 1]);

                        break;
                    } else {
                        empty++;
                    }

                }
            }
        }

    }

    public void pushMine() {

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (button[i][j].isEnabled()) {
                    if (counts[i][j] == -1) {
                        button[i][j].setFont(new Font("Serif", Font.BOLD, 10));
                        button[i][j].setText("XX");
                        button[i][j].setForeground(Color.red);
                    } else {
                        if (counts[i][j] != 0) {
                            button[i][j].setFont(new Font("Serif", Font.BOLD, 17));
                            button[i][j].setText(counts[i][j] + "");
                            button[i][j].setForeground(c[counts[i][j] - 1]);
                        } else {
                            button[i][j].setText("");
                        }
                        button[i][j].setEnabled(false);
                    }
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int modifiers = e.getModifiers();

        //System.out.println(modifiers);
        if ((modifiers & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (e.getSource().equals(button[i][j])) {

                        
                        if (reveal[i][j] == false) {
                            if (counts[i][j] == -1) {

                                button[i][j].setFont(new Font("Serif", Font.BOLD, 10));
                                button[i][j].setText("XX");
                                lost=true;
                                button[i][j].setForeground(Color.red);
                                pushMine();
                            } else {
                                if (counts[i][j] == 0) {
                                    revealZeros(i, j);
                                } else {
                                    button[i][j].setEnabled(false);
                                    button[i][j].setFont(new Font("Serif", Font.BOLD, 15));
                                    button[i][j].setText(counts[i][j] + "");
                                    button[i][j].setForeground(c[counts[i][j] - 1]);
                                }
                            }
                        }
                    }
                }
            }
        }

        if ((modifiers & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (e.getSource().equals(button[i][j])) {
                        if(!button[i][j].isEnabled()){}
                        else{
                        
                        if (reveal[i][j] == true) {
                            reveal[i][j] = false;
                            button[i][j].setText("");
                            mine++;

                        } else {
                            button[i][j].setText("M");
                            button[i][j].setFont(new Font("Serif", Font.BOLD, 15));
                            reveal[i][j] = true;
                            if(counts[i][j]==-1)
                            {
                                mine--;
                                remaining=mine;
                                System.out.println("remain "+""+remaining+" "+mine);
                                if(mine==0)win=true;
                            }
                        }
                        }
                    }
                }
            }
        }

    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
