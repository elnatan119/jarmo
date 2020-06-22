/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package var6;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author לוי
 */
public class View extends JFrame {

    private JarmoJButton[][] btnMat; // מטריצת כפתורי לוח המשחק
    private JLabel lblMoves, lblStatus;  //תויות לסטטוס ולמונה הזזות 
    private JMenuBar jMenuBar;
    JButton sondBtn;
    JButton btnNewGame;
    JButton Undo;

    public static final Color Captives = Color.RED;

    /**
     * view - בנאי למחלקה יוצר את הלוח
     *
     * @param boradSize - גודל הלוח
     */
    public View(int bordLength, int bordWidth) {
        super();
        // יצירת החלון
        this.setJMenuBar(jMenuBar);
        this.setSize(bordLength * 100, bordWidth * 100);
        this.setTitle("Jarmo " + (bordLength * bordWidth - 1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       

        // GAME COMMANDS & INFO
        // =========================================================================
        JPanel pnlCommandsInfo = new JPanel(new GridLayout(1, 1, 10, 5));
        pnlCommandsInfo.setBorder(BorderFactory.createEmptyBorder(10, 6, 10, 6));

        // create New Game Button
        btnNewGame = new JButton("Start New Game");
        sondBtn = new JButton();
        Undo = new JButton("Undo");
         Undo.setEnabled(false);
        if (Controller.getSonudCheck()) {
            sondBtn.setText("Sond on ");
        } else {
            sondBtn.setText("Sond off ");
        }
        sondBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Controller.getSonudCheck() == true) {
                    Controller.setSonud(false);
                } else {
                    Controller.setSonud(true);
                }
                if (Controller.getSonudCheck() == true) {
                    sondBtn.setText("Sond on ");
                }
                if (Controller.getSonudCheck() == false) {
                    sondBtn.setText("Sond off ");
                }
            }
        });
        sondBtn.setFocusable(false);
        btnNewGame.setFocusable(false);
        btnNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.setupGame();
            }
        });
        Undo.setFocusable(false);
        Undo.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                Controller.Undo();
            }
        });

        // create Moves Lable
        lblMoves = new JLabel();
        lblMoves.setHorizontalAlignment(SwingConstants.RIGHT);
        lblMoves.setFont(new Font(null, Font.BOLD, 17));
        lblStatus = new JLabel();
        lblStatus.setFont(new Font(null, Font.BOLD, 12));

        pnlCommandsInfo.add(sondBtn);
        pnlCommandsInfo.add(btnNewGame);
        pnlCommandsInfo.add(lblMoves);
           pnlCommandsInfo.add(Undo);
        // GAME BOARD
        //יצירת הפנאל לכפתורי לוח המשחק 
        // =========================================================================
        JPanel pnlBoardButtons = new JPanel(new GridLayout(bordLength, bordWidth, 4, 4));
        pnlBoardButtons.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // יצירת מטריצה של כפתורי המשחק
        btnMat = new JarmoJButton[bordLength][bordWidth];

        // יצירת הכפתורים והוספתם לפאנל
        for (int row = 0; row < btnMat.length; row++) {
            for (int col = 0; col < btnMat[0].length; col++) {
                btnMat[row][col] = new JarmoJButton(new Location(row, col));

                btnMat[row][col].setFocusable(false);

                // הצמדת אירוע לחיצה
                btnMat[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JarmoJButton btn = (JarmoJButton) e.getSource();

                        Controller.buttonClicked(btn.getLoc(), btn.getClick());
                    }
                });
                setPlayer(btnMat[row][col], Controller.getType(new Location(row, col)));
                pnlBoardButtons.add(btnMat[row][col]);
            }
        }

        // GAME STATUS
        // ========================================================================
        // add all pannels to win
        JPanel pnlStatus = new JPanel(new GridLayout(1, 1));
        pnlStatus.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 3));
        lblStatus = new JLabel();
        lblStatus.setFont(new Font(null, Font.BOLD, 18));
        pnlStatus.add(lblStatus);

        // add all pannels to win
        this.add(pnlCommandsInfo, BorderLayout.NORTH);
        this.add(pnlBoardButtons, BorderLayout.CENTER);
        this.add(pnlStatus, BorderLayout.SOUTH);

        // מיקום החלון במרכז המסך
        this.setLocationRelativeTo(null);
        // הצגת החלון
        this.setVisible(true);

    }

    /**
     * שם על הכפתור את הסוג שלו
     *
     * @param button- כפתור שאתה רוצה להשים אליו את המראה שלו
     * @param type- סוג שחור או לבן
     */
    public void setPlayer(JarmoJButton button, PlayeColor type) {

        button.setText(type.getASCItype());

        button.setFont(new Font(button.getFont().getFamily(), button.getFont().getStyle(), 30));

    }

    public void coloringList(ArrayList<Location> arr, Color color) {
        for (int i = 0; i < arr.size(); i++) {
            Location helper = arr.get(i);
            btnMat[helper.getRow()][helper.getCol()].setBackground(color);
        }
    }

    void setClick(Clicking clicking, Location clickedLocation) {
        btnMat[clickedLocation.getRow()][clickedLocation.getCol()].setClick(clicking);
    }

    /**
     * נועל את כול הכפתורים שבמיקום שלהם נמצא שם
     *
     * @param soldiers -מיקומי הנעילה
     */
    void lockeButtons(ArrayList<Location> soldiers, boolean lock) {
        soldiers.forEach(n -> lockButton(n, lock));
    }

    /**
     * n נועל את הכפתור במיקום
     *
     * @param n - מיקום הניעילה
     */
    private void lockButton(Location n, boolean lock) {
        btnMat[n.getRow()][n.getCol()].setEnabled(lock);

    }

    public void lockAllButton(boolean lock) {
        for (int i = 0; i < btnMat.length; i++) {
            for (int j = 0; j < btnMat[0].length; j++) {
                btnMat[i][j].setEnabled(lock);
            }

        }
    }

    public void restClick() {
        for (int i = 0; i < btnMat.length; i++) {
            for (int j = 0; j < btnMat[0].length; j++) {
                btnMat[i][j].setClick(Clicking.firstClick);
            }

        }
    }

    public void updateGUI() {
        for (int row = 0; row < btnMat.length; row++) {
            for (int col = 0; col < btnMat[0].length; col++) {
                setPlayer(btnMat[row][col], Controller.getType(new Location(row, col)));

            }
        }
    }

    void endGame(Win win) {
        lockAllButton(false);

        tell(win.getResulet(), win.getResuletColor());
    }

    void setupGUI() {
        updateGUI();
        coloringList(Controller.GetRedZone(Turn.black), Turn.white.getColor());//צביעת המקום האסור
        coloringList(Controller.GetRedZone(Turn.white), Turn.black.getColor());
        coloringList(Controller.getEmtyPlaces(), sondBtn.getBackground());

        restClick();
    }

    void tell(String tell, Color color) {
        lblStatus.setText(tell);
        lblStatus.setForeground(color);
    }

    void lockUndo(boolean b) 
    {
        Undo.setEnabled(b);
    }

    

}
