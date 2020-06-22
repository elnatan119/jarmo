/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jarmo;

import java.awt.Color;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *
 * @author לוי
 */
public class Controller {

    /**
     * @param args the command line arguments
     */
    /// קבועים
    public static final int DEFAULT_BOARD_WIDTH = 5;
    public static final int DEFAULT_BOARD_LENGTH = 7;

    //משתנים 
    private static Model model;
    private static View view;
    private static boolean sonudCheck;
    private static Clicking Universalclick = Clicking.None;
    private static Location clickedLocation = null;
    private static Turn turn = Turn.white;
    public static void main(String[] args) {
        sonudCheck = true;
        // טעינת כל הקבצי העזר בשימוש המשחק
        //  loadAssets();

        // הצגת מסך הפתיחה של המשחק
        //   showSplash();
        // create game LOGIC & GUI - ONEs!
        createGame();

        // setup game LOGIC & GUI - evry New Game
        setupGame();
    }

    private static void loadAssets() {

    }

    private static void showSplash() {

    }

    static void setupGame() {

    }

    private static void createGame() {
        model = new Model(DEFAULT_BOARD_LENGTH, DEFAULT_BOARD_WIDTH);

        // creat the GUI
        view = new View(DEFAULT_BOARD_LENGTH, DEFAULT_BOARD_WIDTH);
    }

    static boolean getSonudCheck() {
        return false;
    }

    static void setSonud(boolean b) {

    }

    static void buttonClicked(Location btnloc, Clicking click) {
        switch (Universalclick) {
            case None:// לא נלחץ עד עתה
                clickedLocation = btnloc;
                System.out.println(clickedLocation);
                System.out.println(model.GetPosiblePlace(clickedLocation));
                view.coloringList(model.GetPosiblePlace(clickedLocation), Color.yellow);
                view.setClick(Clicking.firstClick, clickedLocation);
                Universalclick= Clicking.firstClick;
                break;
            case firstClick:
                if (click == Clicking.firstClick) 
                {
                    view.coloringList(model.GetPosiblePlace(clickedLocation), view.sondBtn.getBackground());
                        view.setClick(Clicking.None, clickedLocation);
                         Universalclick= Clicking.None;
                }
                else
                {
                    if(model.exsitInGetPosiblePlace(clickedLocation,btnloc))
                    {
                        if(model.hasEnemy(turn,btnloc))
                        {
                            model.capture(clickedLocation,btnloc);
                        }
                   
                    }
                }
                break;
            default:
            // code block
        }

    }

    /**
     *
     * @param row- מיקום הנצרך
     * @param col- מיקום הנצרך
     * @return
     */
    public static PlayeColor getType(Location place) {
        return model.retunType(place);
    }

    public static ArrayList<Location> GetRedZone() {
        return model.GetRedZone();
    }

    public ArrayList<Location> GetPosiblePlace(Location place) {
        return model.GetPosiblePlace(place);
    }

}
