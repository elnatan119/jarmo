/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package var4;

import java.awt.Color;

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
    private static Turn turn;
    private static Turn NOturn;

    public static void main(String[] args) {
        sonudCheck = true;
        // טעינת כל הקבצי העזר בשימוש המשחק

        createGame();
        setupGame();
    }

    private static void loadAssets() {

    }

    private static void showSplash() {

    }

    static void setupGame() {

        model.setupLogic();
        view.setupGUI();
        view.lockAllButton(false);
        turn = Turn.white;
        NOturn = Turn.black;
        Universalclick= Clicking.firstClick;
        view.lockeButtons(model.getSoldiersNoFinishers(turn), true);
        view.tell("strat to play it "+turn.getTurn(),turn.getColor());

    }

    private static void createGame() {
        model = new Model(DEFAULT_BOARD_LENGTH, DEFAULT_BOARD_WIDTH);
        view = new View(DEFAULT_BOARD_LENGTH, DEFAULT_BOARD_WIDTH,Color.BLUE);
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
            case secondreviveClick:
                secondreviveClickMet(click, btnloc);
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
            view.lockAllButton(false);
            view.lockeButtons(model.getSoldiersNoFinishers(turn), true);

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
                        view.coloringList(model.GetPosiblePlace(clickedLocation), view.sondBtn.getBackground());

                        view.setClick(Clicking.firstClick, clickedLocation);
                        Universalclick = Clicking.firstClick;
                        view.lockAllButton(false);
                        view.lockeButtons(model.getSoldiersNoFinishers(turn), true);
                        return;

                    }
                }

                view.updateGUI();
                if (model.hasReleases(btnloc, turn)) 
                {
                    view.lockAllButton(false);
                    view.lockeButtons(model.GetTheShvoim(turn), true);
                    view.setClick(Clicking.firstClick, clickedLocation);
                    Universalclick = Clicking.reviveClick;
                    view.tell( turn.getTurnName()+"can Releases 1 player ", turn.getColor());
                    return;
                }
                if (model.hasEnd(btnloc)) {
                    model.Raisefinishing(turn);
                    if (model.isEndGame()) {

                        view.endGame(model.getWin());
                        return;
                    }
                }
                view.setClick(Clicking.firstClick, clickedLocation);
                Universalclick = Clicking.firstClick;
                if (turn == Turn.black) {
                    turn = Turn.white;
                    NOturn = Turn.black;
                } else {
                    turn = Turn.black;
                    NOturn = Turn.white;
                }
                    view.tell("it "+turn.getTurn(),turn.getColor());
                if (btnloc.getRow() == 1 || btnloc.getRow() == 5) {
                    System.out.println("");
                }
                view.lockAllButton(false);
                view.lockeButtons(model.getSoldiersNoFinishers(turn), true);

            }
        }

    }

    private static void reviveClickMet(Location btnloc) {
        view.coloringList(model.GetPosiblePlace(btnloc), Color.yellow);
        view.lockeButtons(model.GetPosiblePlace(btnloc), true);
        clickedLocation = btnloc;

        view.setClick(Clicking.secondreviveClick, clickedLocation);
        Universalclick = Clicking.secondreviveClick;
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

    public static ArrayList<Location> GetRedZone(Turn turn) {
        return model.GetRedZone(turn);
    }

    private static void secondreviveClickMet(Clicking click, Location btnloc) {
        if (click == Clicking.secondreviveClick) {
            view.coloringList(model.GetPosiblePlace(clickedLocation), NOturn.getColor());

            view.setClick(Clicking.reviveClick, clickedLocation);
            Universalclick = Clicking.reviveClick;
            view.lockAllButton(false);
            view.lockeButtons(model.GetTheShvoim(turn), true);
            return;
        }
        if (model.exsitInGetPosiblePlace(clickedLocation, btnloc)) {
            view.coloringList(model.GetPosiblePlace(clickedLocation), view.sondBtn.getBackground());
            if (model.hasEnemy(turn, btnloc)) {
                model.capture(clickedLocation, btnloc, turn);
                model.revive(clickedLocation, turn);
                model.go(clickedLocation, btnloc);
            } else {
                if (model.itEmpty(btnloc)) {
                    model.go(clickedLocation, btnloc);

                } else //יש חבר שמה
                {
                    view.coloringList(model.GetPosiblePlace(clickedLocation), view.sondBtn.getBackground());

                    view.setClick(Clicking.reviveClick, clickedLocation);
                    Universalclick = Clicking.reviveClick;
                    view.lockAllButton(false);
                    view.lockeButtons(model.GetTheShvoim(turn), true);
                    return;

                }
            }

            view.updateGUI();

            if (model.hasEnd(btnloc)) {
                model.Raisefinishing(turn);
                if (model.isEndGame()) {

                    view.endGame(model.getWin());
                }
            }
            view.setClick(Clicking.firstClick, clickedLocation);
            Universalclick = Clicking.firstClick;
            if (turn == Turn.black) {
                turn = Turn.white;
                NOturn = Turn.black;
            } else {
                turn = Turn.black;
                NOturn = Turn.white;
            }
             view.tell("it "+turn.getTurn(),turn.getColor());
           
            view.lockAllButton(false);
            view.lockeButtons(model.getSoldiersNoFinishers(turn), true);

        }
    }

    static ArrayList<Location> getEmtyPlaces() {
        return model.getSoldiers(turn.None);
    }
}
