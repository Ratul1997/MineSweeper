
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MineSweeper implements ActionListener {

    JFrame frame;

    JPanel p;
    JButton new_game, exit,music, b3, b5, back1, back2, mainmenu, restart, play;
    Timer timer;
    JLabel time, remain, test, second2;

    JButton buttons[][] = new JButton[2][2];
    Game game;
    JPanel pp;

    JLabel second;

    boolean lost = false;
    int sec = 0;
    int row, col, mine;

    MineSweeper() {
        timer = new Timer(1000, this);
        timer.setInitialDelay(10);
        init();
    }
    
    public void init() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if (!lost) {
            frame.setContentPane(new JLabel(new ImageIcon(getClass().getResource("mine.jpg"))));
        }
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        //frame.setSize(1000, 700);
        frame.setLayout(null);

        setbutton();
        frame.setVisible(true);

    }

    public void setbutton() {

        System.out.println("button1");
        new_game = new JButton("New Game");
        new_game.setFont(new Font("Sharif", Font.BOLD, 15));
        new_game.setLocation(550, 250);
        new_game.setSize(150, 50);
        new_game.addActionListener(new AB());
        frame.add(new_game);

        how_to_play();
        out(550, 450);
    }

    public void how_to_play() {
        play = new JButton("How to Play");
        play.setFont(new Font("Sharif", Font.BOLD, 15));
        play.setLocation(550, 350);
        play.setSize(150, 50);
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.remove(new_game);
                frame.remove(exit);
                frame.setContentPane(new JLabel(new ImageIcon(getClass().getResource("how.jpg"))));

                JButton b = new JButton("Back");
                b.setFont(new Font("Sharif", Font.BOLD, 15));
                b.setLocation(600, 610);
                b.setSize(150, 50);
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.getContentPane().removeAll();
                        frame.remove(b);
                        frame.setContentPane(new JLabel(new ImageIcon(getClass().getResource("mine.jpg"))));
                        setbutton();
                        frame.validate();
                        frame.repaint();
                    }

                });
                frame.add(b);
                frame.validate();
                frame.repaint();
            }
        });
        frame.add(play);

    }

    public void out(int x, int y) {
        exit = new JButton("Exit");
        exit.setFont(new Font("Sharif", Font.BOLD, 15));
        exit.setLocation(x, y);
        exit.setSize(150, 50);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                } else {
                    // no option
                }
            }
        });
        frame.add(exit);
        if (lost) {
            frame.validate();
            frame.repaint();
        }
        lost = false;
    }

    public static void main(String[] args) {
        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }

        new MineSweeper();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (game.lost) {
            timer.stop();
            frame.remove(game);
            frame.remove(back1);
            frame.remove(exit);
            frame.remove(time);
            frame.remove(second);
            frame.setContentPane(new JLabel(new ImageIcon(getClass().getResource("lost.jpg"))));

            String s1 = Integer.toString(sec);

            second2 = new JLabel(s1);
            //System.out.println(sec);

            second2.setLocation(750, 350);
            second2.setFont(new Font("Sharif", Font.ITALIC, 130));
            second2.setForeground(Color.WHITE);
            second2.setSize(200, 200);
            frame.add(second2);

            mainmenu = new JButton("Main Menu");
            mainmenu.setFont(new Font("Sharif", Font.BOLD, 20));
            mainmenu.setLocation(420, 610);
            mainmenu.setSize(150, 50);
            mainmenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.getContentPane().removeAll();
                    frame.setContentPane(new JLabel(new ImageIcon(getClass().getResource("mine.jpg"))));
                    frame.remove(mainmenu);
                    frame.remove(second2);
                    lost = true;
                    frame.validate();
                    frame.repaint();
                    setbutton();
                }
            });
            out(720, 610);
            frame.add(mainmenu);

            frame.validate();
            frame.repaint();

            sec = 0;
        } else if (game.win && !game.lost) {
            timer.stop();
            frame.remove(game);
            frame.remove(back1);
            frame.remove(exit);
            frame.remove(time);
            frame.remove(second);

            frame.setContentPane(new JLabel(new ImageIcon(getClass().getResource("win.jpg"))));

            String s1 = Integer.toString(sec);

            second2 = new JLabel(s1);
            //System.out.println(sec);

            second2.setLocation(750, 400);
            second2.setFont(new Font("Sharif", Font.ITALIC, 130));
            second2.setForeground(Color.WHITE);
            second2.setSize(200, 200);
            frame.add(second2);

            mainmenu = new JButton("Main Menu");
            mainmenu.setFont(new Font("Sharif", Font.BOLD, 20));
            mainmenu.setLocation(420, 610);
            mainmenu.setSize(150, 50);
            mainmenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.getContentPane().removeAll();
                    frame.setContentPane(new JLabel(new ImageIcon(getClass().getResource("mine.jpg"))));
                    frame.remove(mainmenu);
                    frame.remove(second2);
                    lost = true;
                    frame.validate();
                    frame.repaint();
                    setbutton();
                }

            });
            out(720, 610);
            frame.add(mainmenu);

            frame.validate();
            frame.repaint();
            sec=0;
        } else {
            ++sec;
            String s1 = Integer.toString(sec);
            if (sec != 1) {
                frame.remove(second);
            }

            second = new JLabel(s1);

            second.setLocation(1250, 200);
            second.setFont(new Font("Sharif", Font.BOLD, 30));
            second.setForeground(Color.WHITE);
            second.setSize(150, 50);
            frame.add(second);

            if (sec != 1) {
                frame.validate();
                frame.repaint();
            }

        }
    }

    private class ABC2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(back1);
            frame.remove(game);
            frame.remove(time);
            frame.remove(second);
            frame.remove(exit);
            timer.stop();
            get();
            sec = 0;
            frame.validate();
            frame.repaint();
        }
    }

    private class ABC implements ActionListener {

        public ABC() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttons[0][0] == e.getSource()) {
                frame.remove(p);
                frame.remove(back2);
                frame.remove(exit);
                timer.start();

                game = new Game(10, 10, 12);
                frame.add(game);
                getOptionButtonOnGame();
                out(1200, 100);
                time = new JLabel("TIme : ");
                time.setFont(new Font("Sharif", Font.BOLD, 30));
                time.setForeground(Color.WHITE);
                time.setLocation(1200, 150);
                time.setSize(150, 50);

                frame.add(time);
                frame.validate();
                frame.repaint();
            } else if (buttons[0][1] == e.getSource()) {
                frame.remove(p);
                frame.remove(exit);
                frame.remove(back2);
                timer.start();

                game = new Game(15, 10, 15);
                frame.add(game);

                getOptionButtonOnGame();
                time = new JLabel("TIme : ");
                time.setFont(new Font("Sharif", Font.BOLD, 30));
                time.setForeground(Color.WHITE);
                time.setLocation(1200, 150);
                time.setSize(150, 50);

                frame.add(time);

                out(1200, 100);
                frame.validate();
                frame.repaint();
            } else if (buttons[1][0] == e.getSource()) {
                frame.remove(p);
                frame.remove(back2);
                frame.remove(exit);
                timer.start();

                game = new Game(20, 20, 50);
                frame.add(game);
                time = new JLabel("TIme : ");
                time.setFont(new Font("Sharif", Font.BOLD, 30));
                time.setForeground(Color.WHITE);
                time.setLocation(1200, 150);
                time.setSize(150, 50);

                frame.add(time);

                getOptionButtonOnGame();
                out(1200, 100);
                frame.validate();
                frame.repaint();
            } else if (buttons[1][1] == e.getSource()) {

                if(getNewFrame()){
                frame.remove(p);
                frame.remove(back2);
                frame.remove(exit);
                timer.start();

                
                game = new Game(row, col, mine);
                time = new JLabel("TIme : ");
                time.setFont(new Font("Sharif", Font.BOLD, 30));
                time.setForeground(Color.WHITE);
                time.setLocation(1200, 150);
                time.setSize(150, 50);

                frame.add(time);
                out(1200, 100);
                frame.add(game);
                getOptionButtonOnGame();
                frame.validate();
                frame.repaint();
                }
            }
        }
    }

    public void getOptionButtonOnGame() {

        System.out.println("button2");
        back1 = new JButton("Back");
        back1.setFont(new Font("Sharif", Font.BOLD, 15));
        back1.setLocation(1200, 50);
        back1.setSize(150, 50);
        back1.addActionListener(new ABC2());
        frame.add(back1);

    }

    public boolean getNewFrame() {
        String rows = JOptionPane.showInputDialog("Rows : ");
        String cols = JOptionPane.showInputDialog("Cols : ");
        String mines = JOptionPane.showInputDialog("Mine : ");
        row = Integer.parseInt(rows);
        col = Integer.parseInt(cols);
        mine = Integer.parseInt(mines);
        if(row*col<mine || mine<1)
        {
            JOptionPane.showMessageDialog(null, "Invalid Input");
            return false;
        }
        return true;
    }

    private class AB implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("button3");
            frame.remove(new_game);
            frame.remove(exit);
            frame.getContentPane().removeAll();
            frame.setContentPane(new JLabel(new ImageIcon(getClass().getResource("pic.jpg"))));

            get();
            frame.validate();
            frame.repaint();
        }
    }

    public void get() {

        String[][] s = {{"<html>  10 X 10<br> <bt><b> Mine = 12</html>", "<html>  15 X 10<br> <bt><b> Mine = 15</html>"},
        {
            "<html>  20 X 20<br> <bt><b> Mine = 50</html>", "<html>Custom <br><br> </html>",}};
        p = new JPanel();
        p.setLocation(150, 50);
        p.setSize(1000, 700);
        p.setLayout(new GridLayout(2, 2));
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                buttons[i][j] = new JButton(s[i][j]);
                buttons[i][j].setFont(new Font("Sharif", Font.BOLD, 15));
                p.add(buttons[i][j]);
                buttons[i][j].addActionListener(new ABC());
            }
        }
        frame.add(p);
        back2 = new JButton("Back");
        back2.setFont(new Font("Sharif", Font.BOLD, 15));
        back2.setLocation(1200, 50);
        back2.setSize(150, 50);
        back2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(p);
                frame.remove(back2);
                frame.remove(exit);

                frame.getContentPane().removeAll();
                frame.setContentPane(new JLabel(new ImageIcon(getClass().getResource("mine.jpg"))));
                setbutton();
                frame.validate();
                frame.repaint();
            }
        });
        out(1200, 100);
        frame.add(back2);
    }
}
