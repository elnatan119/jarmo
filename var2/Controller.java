/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package var2;


import jarmo.*;
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
    private static Clicking Universalclick = Clicking.firstClick;
    private static Location clickedLocation = null;
    private static Turn turn = Turn.white;
    private static Turn NOturn = Turn.black;

    public static void main(String[] args) {
       
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

        model = new Model(DEFAULT_BOARD_LENGTH, DEFAULT_BOARD_WIDTH);

        // creat the GUI
        view = new View(DEFAULT_BOARD_LENGTH, DEFAULT_BOARD_WIDTH);
        view.lockeButtons(model.getSoldiers(NOturn), false);
        view.lockeButtons(model.GetTheShvoim(turn), false);
        view.lockeButtons(model.GetTheShvoim(NOturn), false);
        
        // view.lockeButtons(model.getSoldiers(Turn.None), false);
    }

    private static void createGame() {

    }

    static boolean getSonudCheck() {
        return false;
    }

    static void setSonud(boolean b) {

    }

    static void buttonClicked(Location btnloc, Clicking click) {
        switch (Universalclick) {
            case firstClick:
                firstClickMet(btnloc);
                break;
            case secondClick:
                secondClickMet(click, btnloc);

                break;
            case reviveClick:
                reviveClickMet(btnloc);
                break;
            default:
            // code block
        }

      }

    public static void firstClickMet(Location btnloc) {
        // לא נלחץ עד עתה

        clickedLocation = btnloc;
        System.out.println(clickedLocation);
        System.out.println(model.GetPosiblePlace(clickedLocation));
          view.lockeButtons(model.Endlist(), false);
        view.lockeButtons(model.GetPosiblePlace(clickedLocation), true);

        view.coloringList(model.GetPosiblePlace(clickedLocation), Color.yellow);
        view.setClick(Clicking.secondClick, clickedLocation);
        Universalclick = Clicking.secondClick;
    }

    private static void secondClickMet(Clicking click, Location btnloc) {
        if (click == Clicking.secondClick) {
            view.coloringList(model.GetPosiblePlace(clickedLocation), view.sondBtn.getBackground());
            System.out.println(view.sondBtn.getBackground());
            view.setClick(Clicking.firstClick, clickedLocation);
            Universalclick = Clicking.firstClick;
        } else {
            if (model.exsitInGetPosiblePlace(clickedLocation, btnloc)) {
                view.coloringList(model.GetPosiblePlace(clickedLocation), view.sondBtn.getBackground());
                if (model.hasEnemy(turn, btnloc)) {
                    model.capture(clickedLocation, btnloc, turn);
                    model.go(clickedLocation, btnloc);
                } else {
                    if (model.itEmpty(btnloc)) {
                        model.go(clickedLocation, btnloc);
                    } else //יש חבר שמה
                    {
                        return;
                    }
                }
                if (model.hasEnd(btnloc)) 
                {
                    model.Raisefinishing(turn);
                    if (model.isEndGame()) {
                     
                        view.endGame(model.getWin());
                    }
                }
                view.updateGUI();
                if (model.hasReleases(btnloc, turn)) 
                {
                    view.coloringList(model.GetPosiblePlace(model.GetTheShvoim(turn).get(1)), Color.yellow);
                    Universalclick = Clicking.reviveClick;
                    return;
                }
                view.setClick(Clicking.firstClick, clickedLocation);
                Universalclick = Clicking.firstClick;
                if (turn == Turn.black) 
                {
                    turn = Turn.white;
                    NOturn = Turn.black;
                } else 
                {
                    turn = Turn.black;
                    NOturn = Turn.white;
                }
                if(btnloc.getRow()==1||btnloc.getRow()==5)
                {
                    System.out.println("");
                }
                view.lockeButtons(model.getSoldiers(turn), true);
                view.lockeButtons(model.getSoldiers(NOturn), false);
                  view.lockeButtons(model.Endlist(), false);
                  view.lockeButtons(model.GetTheShvoim(turn), false);
                     view.lockeButtons(model.GetTheShvoim(NOturn), false);
            }
        }

    }

    private static void reviveClickMet(Location btnloc) 
    {
        
        view.coloringList(model.GetPosiblePlace(model.GetTheShvoim(turn).get(1)), view.sondBtn.getBackground());

        if (model.itEmpty(btnloc)) {
            model.revive(btnloc, turn);
        } else //יש חבר או איוב  שמה
        {
            return;
        }
        view.updateGUI();
        if (model.hasEnd(btnloc)) 
        {
            model.Raisefinishing(turn);
            if (model.isEndGame()) {

                view.endGame(model.getWin());
            }
        }
        view.setClick(Clicking.firstClick, clickedLocation);
        Universalclick = Clicking.firstClick;
        if (turn == Turn.black) 
        {
            turn = Turn.white;
            NOturn = Turn.black;
        } else 
        {
            turn = Turn.black;
            NOturn = Turn.white;
        }
        view.lockeButtons(model.getSoldiers(turn), true);
        view.lockeButtons(model.getSoldiers(NOturn), false);
        view.lockeButtons(model.Endlist(), false);
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

    public ArrayList<Location> GetPosiblePlace(Location place) 
    {
        return model.GetPosiblePlace(place);
    }

}
