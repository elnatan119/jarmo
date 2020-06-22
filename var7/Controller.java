/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package var7;

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
        Universalclick = Clicking.firstClick;
        view.lockeButtons(model.getSoldiersNoFinishers(turn), true);
        view.tell("strat to play it " + turn.getTurn(), turn.getColor());

    }

    private static void createGame() {
        model = new Model(DEFAULT_BOARD_LENGTH, DEFAULT_BOARD_WIDTH);
        view = new View(DEFAULT_BOARD_LENGTH, DEFAULT_BOARD_WIDTH);
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

    public static void firstClickMet(Location btnloc) 
    {
        // לא נלחץ עד עתה

        clickedLocation = btnloc;
        view.showPossibilities(model.GetPosiblePlace(clickedLocation, turn), clickedLocation, Clicking.secondClick, View.opColor);
        Universalclick = Clicking.secondClick;

    }

    private static void secondClickMet(Clicking click, Location btnloc) {

        if (click == Clicking.secondClick) 
        {
            view.ClickCancel(model.GetPosiblePlace(clickedLocation, turn), model.getSoldiersNoFinishers(turn), clickedLocation, view.Normal, Clicking.firstClick);

            Universalclick = Clicking.firstClick;

        } 
        else 
        {

            view.lockUndo(true);
            view.coloringList(model.GetPosiblePlace(clickedLocation, turn), view.Normal);

            model.applyMove(new Move(turn, NOturn, btnloc, clickedLocation));
            view.updateGUI();
            if (model.hasReleases(btnloc, turn)) {
                view.applyReleases(model.GetTheShvoim(turn), turn, clickedLocation);
                Universalclick = Clicking.reviveClick;
                return;
            }
            if (model.hasEnd(btnloc)) {
                model.Raisefinishing(turn);
            }

            if (model.isEndGame()) {
                view.endGame(model.getWin());
                return;
            }

            view.setClick(Clicking.firstClick, clickedLocation);
            Universalclick = Clicking.firstClick;
            ReplaceTurn(NOturn, turn);

        }

    }

    private static void reviveClickMet(Location btnloc) {
        clickedLocation = btnloc;
        view.showPossibilities(model.GetPosiblePlace(clickedLocation, turn), clickedLocation, Clicking.secondreviveClick, View.opColor);

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
            view.ClickCancel(model.GetPosiblePlace(clickedLocation, turn), model.GetTheShvoim(turn), clickedLocation, view.Normal, Clicking.reviveClick);
            Universalclick = Clicking.reviveClick;
            return;
        }
        view.lockUndo(true);
        view.coloringList(model.GetPosiblePlace(clickedLocation, turn), view.Normal);
        model.applyMove(new Move(turn, NOturn, btnloc, clickedLocation));
        view.updateGUI();
        if (model.hasEnd(btnloc)) {
            model.Raisefinishing(turn);
        }
        if (model.isEndGame()) {

            view.endGame(model.getWin());
        }
        view.setClick(Clicking.firstClick, clickedLocation);
        Universalclick = Clicking.firstClick;
        ReplaceTurn(NOturn, turn);

    }

    public static void ReplaceTurn(Turn turn, Turn Noturn) {
        Controller.turn = turn;
        Controller.NOturn = Noturn;
        view.tell("it " + turn.getTurn(), turn.getColor());

        view.lockAllButton(false);
        view.lockeButtons(model.getSoldiersNoFinishers(turn), true);
    }

    static ArrayList<Location> getEmtyPlaces() {
        return model.getSoldiers(turn.None);
    }

    static void Undo() {
        Universalclick = Clicking.firstClick;

        if (model.Undo() == true) {

            view.refershGUI();

        } 
        else 
        {
            view.refershGUI();

        }
    }

    public static Turn getNOturn() {
        return NOturn;
    }

    public static void setNOturn(Turn NOturn) {
        Controller.NOturn = NOturn;
    }

    public static Turn getTurn() {
        return turn;
    }

    public static void setTurn(Turn turn) {
        Controller.turn = turn;
    }

    static void lockUndo(boolean b) {
        view.lockUndo(b);
    }

    static PlaceModel getPlace(Location place) {
        return model.getPlace(place);
    }

}
