/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package var6;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author לוי
 */
public class Model {

    private PlaceModel[][] logicMat; //מטריצה לוגית של לוח המשחק בכל שלב 

    private final int None = 0;
    private final int BLACK = -1;
    private final int WHITE = 1;
    private final int BLACKing = -2;
    private final int WHITEKING = 2;
    private int Blackconter;
    private int whiteconter;
    private int whiteCaptives = 0;// מספר הלבנים השבוים
    private int blackCaptives = 0;//  שורת השבויים השחורים
    PlaceModel[] arrwhiteCaptives;
    PlaceModel[] arrbalckCaptives;
    Stack<Move> StackMove;

    public Model(int bordLength, int bordWidth) {

        StackMove = new Stack<Move>();
        logicMat = new PlaceModel[bordLength][bordWidth];
        arrbalckCaptives = logicMat[0];
        arrwhiteCaptives = logicMat[logicMat.length - 1];
        for (int row = 0; row < bordLength; row++) {
            for (int col = 0; col < bordWidth; col++) {
                logicMat[row][col] = new PlaceModel(None, new Location(row, col));

            }

        }
        setValidPlace(logicMat);
    }

    PlayeColor retunType(Location place) {
        switch (logicMat[place.getRow()][place.getCol()].getValue()) {
            case BLACK:
                return PlayeColor.black;

            case WHITE:
                return PlayeColor.white;

            case WHITEKING:
                return PlayeColor.whiteKing;

            case BLACKing:
                return PlayeColor.blackKing;

            default:
                return PlayeColor.None;
        }
    }

    public ArrayList<Location> GetRedZone(Turn turn) {
        ArrayList<Location> Arr = new ArrayList<>();
        if (turn == Turn.black) {
            for (int i = 0; i < logicMat[0].length; i++) {
                Arr.add(new Location(0, i));

            }
        }
        if (turn == Turn.white) {
            for (int i = 0; i < logicMat[0].length; i++) {

                Arr.add(new Location(logicMat.length - 1, i));
            }
        }
        return Arr;
    }

    /**
     * ללא שבוים)5X5 שם את המקמות האפשרים ממקום אחד לשני ללוח
     *
     * @param logicMat
     */
    public void setValidPlace(PlaceModel[][] BordGame) {
        /////////////// אלכסון קיצוני ימינה  
        ArrayList<Location> ymia21 = new ArrayList<>();
        ymia21.add(new Location(0, 0));
        ymia21.add(new Location(1, 0));
        ymia21.add(new Location(2, 0));

        ymia21.add(new Location(0, 3));
        ymia21.add(new Location(0, 1));
        ymia21.add(new Location(2, 3));
        ymia21.forEach((n) -> addValid(n, 2, 1));
        ////////////// אלכסון מתון ימינה
        ArrayList<Location> ymia12 = new ArrayList<>();
        ymia12.add(new Location(0, 0));
        ymia12.add(new Location(3, 0));
        ymia12.add(new Location(1, 1));
        ymia12.add(new Location(0, 2));
        ymia12.add(new Location(3, 1));
        ymia12.add(new Location(3, 2));
        ymia12.add(new Location(2, 2));
        ymia12.add(new Location(2, 1));
        ymia12.add(new Location(0, 1));
        ymia12.forEach((n) -> addValid(n, 1, 2));
        ////////////    אלכסון  שמאלה קיצוני
        ArrayList<Location> leftM12 = new ArrayList<>();
        leftM12.add(new Location(1, 0));
        leftM12.add(new Location(4, 0));
        leftM12.add(new Location(4, 1));
        leftM12.add(new Location(4, 2));
        leftM12.add(new Location(1, 1));
        leftM12.add(new Location(3, 0));
        leftM12.add(new Location(2, 0));
        leftM12.add(new Location(2, 2));
        leftM12.add(new Location(1, 2));
        leftM12.forEach((n) -> addValid(n, -1, 2));
        ////////////////////// אלכסון   למעלה שמאלה
        ArrayList<Location> leftM21 = new ArrayList<>();
        // leftM21.add(new Location(2, 0));
        leftM21.add(new Location(4, 0));
        leftM21.add(new Location(2, 3));
        leftM21.add(new Location(3, 2));
        leftM21.add(new Location(4, 3));
        leftM21.add(new Location(2, 1));
        leftM21.add(new Location(4, 2));

        leftM21.forEach((n) -> addValid(n, -2, 1));
        /////////// ימינה
        ArrayList<Location> ymia10 = new ArrayList<>();
        ymia10.add(new Location(1, 1));

        ymia10.add(new Location(1, 2));
        ymia10.add(new Location(3, 2));
        ymia10.add(new Location(3, 1));
        //ymia10.add(new Location(3, 4));
        ymia10.forEach((n) -> addValid(n, 0, 1));
        ///////////למעלה
        ArrayList<Location> lmala01 = new ArrayList<>();
        lmala01.add(new Location(1, 1));
        // lmala01.add(new Location(2, 1));
        lmala01.add(new Location(1, 3));
        lmala01.add(new Location(2, 3));
        lmala01.add(new Location(2, 1));
        lmala01.forEach((n) -> addValid(n, 1, 0));
        //שבויים
        PlaceModel[] arrsho = logicMat[0];
        PlaceModel[] arrposible = logicMat[1];
        for (int m = 0; m < 2; m++) {
            for (int i = 0; i < arrsho.length; i++) {
                for (int j = 0; j < arrposible.length; j++) {
                    arrsho[i].AddPossiblePlaces(arrposible[j].getLocYesSvohim().getRow(), arrposible[j].getLocYesSvohim().getCol());
                }
            }
            if (m == 0) {
                arrsho = logicMat[logicMat.length - 1];
                arrposible = logicMat[logicMat.length - 2];
            }
        }

    }

    /**
     * פעולת עזר שמה במיקום של אחד את השני
     *
     * @param k - מיקום של אחד מעיגולים
     * @param x - בציר האיקס חיסור בין שני המקומות
     * @param y -בציר חיסור בין שני המקומות
     */
    public void addValid(Location k, int x, int y) {
        int endPlaceOfx = 0, endplaceofY = 0;
        int placeOfX = k.getRow();
        int placeofY = k.getCol();
        endPlaceOfx = placeOfX + x;
        endplaceofY = placeofY + y;

        logicMat[placeOfX + 1][placeofY].AddPossiblePlaces(endPlaceOfx + 1, endplaceofY);
        logicMat[endPlaceOfx + 1][endplaceofY].AddPossiblePlaces(placeOfX + 1, placeofY);

    }

    public ArrayList<Location> GetPosiblePlace(Location place) {
        return logicMat[place.getRow()][place.getCol()].getPossiblePlaces();
    }

    /**
     *
     * @param clickedLocation- מקום שממנו אתה רוצה למצוא
     * @param btnloc- מקום חדש
     * @return - אם אפשר לעביר לשם או לא
     */
    boolean exsitInGetPosiblePlace(Location clickedLocation, Location btnloc) {
        ArrayList<Location> Places = logicMat[clickedLocation.getRow()][clickedLocation.getCol()].getPossiblePlaces();
        for (int i = 0; i < Places.size(); i++) {
            if (Places.get(i).equal(btnloc)) {
                return true;
            }

        }
        return false;
    }

    /**
     *
     * העתקה ממקום אחד למקום השני ללא התחשבות במחשב
     *
     * @param clickedLocation-מקום לשעבר
     * @param btnloc - מקום חדש
     */
    void go(Location clickedLocation, Location btnloc) {
        UpdedeStack(clickedLocation, btnloc, Controller.getTurn(), Controller.getNOturn(), MoveEnume.go);
        logicMat[btnloc.getRow()][btnloc.getCol()].setValue(logicMat[clickedLocation.getRow()][clickedLocation.getCol()].getValue());
        logicMat[clickedLocation.getRow()][clickedLocation.getCol()].setValue(None);
        PrintStack(StackMove);

    }

    boolean hasEnemy(Turn turn, Location btnloc) {
        if (turn == Turn.white && (logicMat[btnloc.getRow()][btnloc.getCol()].getValue() == BLACK || logicMat[btnloc.getRow()][btnloc.getCol()].getValue() == BLACKing)) {
            return true;
        }
        if (turn == Turn.black && (logicMat[btnloc.getRow()][btnloc.getCol()].getValue() == WHITE || logicMat[btnloc.getRow()][btnloc.getCol()].getValue() == WHITEKING)) {
            return true;
        }
        return false;
    }

    void capture(Location player, Location enemy, Turn turn, Turn Noturn) {

        PlaceModel enemyPl = logicMat[enemy.getRow()][enemy.getCol()];
        PlaceModel playerPl = logicMat[player.getRow()][player.getCol()];
        PlaceModel playerHelper = new PlaceModel(playerPl, null);
        if (turn == Turn.black) {
            UpdedeStack(enemy, freePlace(arrwhiteCaptives), turn, Noturn, MoveEnume.cputred);
            shovimAdd(WHITE, arrwhiteCaptives);
            UpdedeStack(player, player, turn, Noturn, MoveEnume.beacomKing);
            playerPl.setValue(BLACKing);

            whiteCaptives++;
        } else {
            UpdedeStack(enemy, freePlace(arrbalckCaptives), turn, Noturn, MoveEnume.cputred);
            shovimAdd(BLACK, arrbalckCaptives);
            UpdedeStack(player, player, turn, Noturn, MoveEnume.beacomKing);
            playerPl.setValue(WHITEKING);
            blackCaptives++;
        }
        PrintStack(StackMove);
    }

    /**
     * פעולה שמביאה את מיקומם של חיילים מסוימים
     *
     * turn= None - מחזיר את כול המקמות הריקיים
     *
     * @param turn - מי החילים אתה רוצה להביא השחורים או הלבנים או אף אחד מהם
     * @return רשימה עם מיקום החילים
     */
    ArrayList<Location> getSoldiers(Turn turn) {
        ArrayList<Location> Arr = new ArrayList<>();
        int key = None;
        int keyKing = None;
        if (turn == Turn.white) {
            key = WHITE;
            keyKing = WHITEKING;

        } else if (turn == Turn.black) {
            key = BLACK;
            keyKing = BLACKing;
        }

        for (int row = 1; row < logicMat.length - 1; row++) {
            for (int col = 0; col < logicMat[0].length; col++) {
                if (logicMat[row][col].getValue() == key || logicMat[row][col].getValue() == keyKing) {
                    Arr.add(new Location(row, col));
                }
            }

        }
        return Arr;
    }

    /**
     * פעולה שמביאה את מיקומם של חיילים מסוימים ללא מי שסיים * turn= None -
     * שגיאה יש להתמש בפועלה הרגילה
     *
     * @param turn - מי החילים אתה רוצה להביא השחורים או הלבנים
     * @return רשימה עם מיקום החילים
     * @param turn
     * @return
     */
    ArrayList<Location> getSoldiersNoFinishers(Turn turn) {
        ArrayList<Location> Arr = new ArrayList<>();
        int key = None;
        int keyKing = None;
        if (turn == Turn.white) {
            key = WHITE;
            keyKing = WHITEKING;

        } else if (turn == Turn.black) {
            key = BLACK;
            keyKing = BLACKing;
        }
        if (turn == Turn.black) {
            for (int row = 1; row < logicMat.length - 2; row++) {
                for (int col = 0; col < logicMat[0].length; col++) {
                    if (logicMat[row][col].getValue() == key || logicMat[row][col].getValue() == keyKing) {
                        Arr.add(new Location(row, col));
                    }
                }

            }
        } else if (turn == Turn.white) {
            for (int row = 2; row < logicMat.length - 1; row++) {
                for (int col = 0; col < logicMat[0].length; col++) {
                    if (logicMat[row][col].getValue() == key || logicMat[row][col].getValue() == keyKing) {
                        Arr.add(new Location(row, col));
                    }
                }

            }

        }
        return Arr;

    }

    ArrayList<Location> GetTheShvoim(Turn turn) {
        if (turn == Turn.None) {
            throw new IllegalArgumentException("this not posisble to here None");
        }
        ArrayList<Location> arr = new ArrayList<Location>();
        PlaceModel[] arr2;
        if (turn == Turn.white) {
            arr2 = arrwhiteCaptives;
        } else {
            arr2 = arrbalckCaptives;
        }

        for (int i = 0; i < arr2.length; i++) {
            arr.add(arr2[i].getLocYesSvohim());
        }
        return arr;

    }

    /**
     *
     *
     * @param list - רשימת השבויים
     * @param arrsho -
     * @param arrposible
     */
    boolean itEmpty(Location btnloc) {
        return logicMat[btnloc.getRow()][btnloc.getCol()].getValue() == None;
    }

    /**
     * בדיקה האם הוא הגיע לסוף השורה
     *
     * @param btnloc - מיקום
     * @param turn - של מי התור
     * @return
     */
    boolean hasEnd(Location btnloc) {

        if (btnloc.getRow() == logicMat.length - 2 && (retunType(btnloc) == PlayeColor.black || retunType(btnloc) == PlayeColor.blackKing)) {
            return true;
        } else if (btnloc.getRow() == 1 && (retunType(btnloc) == PlayeColor.white || retunType(btnloc) == PlayeColor.whiteKing)) {
            return true;
        }

        return false;
    }

    /**
     * בדיקה אם הוא יכול לשחרר שבוי
     *
     * @param btnloc
     * @param turn
     * @return
     */
    boolean hasReleases(Location btnloc, Turn turn) {
        if (turn == Turn.black) {
            if (hasEnd(btnloc) && logicMat[btnloc.getRow()][btnloc.getCol()].getValue() == BLACKing && !CaptivesisEmpty(blackCaptives)) {
                return true;
            }
        } else if (turn == Turn.white) {
            if (hasEnd(btnloc) && logicMat[btnloc.getRow()][btnloc.getCol()].getValue() == WHITEKING && !CaptivesisEmpty(whiteCaptives)) {
                return true;
            }

        }
        return false;

    }

    void Raisefinishing(Turn turn) {
        switch (turn) {
            case black:
                Blackconter++;

                break;
            case white:
                whiteconter++;

                break;
            default:

        }
    }

    boolean isEndGame() {
        if (getSoldiersNoFinishers(Turn.black).isEmpty() || getSoldiersNoFinishers(Turn.white).isEmpty()) {
            return true;
        }
        return false;
    }

    Win getWin() {
        Win win = new Win();
        ArrayList<Location> black = getSoldiers(Turn.black);
        ArrayList<Location> white = getSoldiers(Turn.white);
        int blackScore = black.size() + Blackconter;
        int whiteScore = white.size() + whiteconter;
        if (blackScore > whiteScore) {
            win.setWiner(Turn.black);
            win.setLoser(Turn.white);
            win.setWinerScoring(blackScore);
            win.setLoserScoring(whiteScore);
        } else if (blackScore < whiteScore) {
            win.setWiner(Turn.white);
            win.setLoser(Turn.black);
            win.setWinerScoring(whiteScore);
            win.setLoserScoring(blackScore);

        } else {
            win.setWiner(Turn.None);
            win.setLoser(Turn.None);
            win.setWinerScoring(whiteScore);
            win.setLoserScoring(blackScore);

        }
        return win;
    }

    public void setupLogic() {
        Blackconter = 0;
        whiteconter = 0;

        for (int i = 0; i < logicMat.length; i++) {
            for (int j = 0; j < logicMat[0].length; j++) {
                logicMat[i][j].setValue(None);

            }

        }

        for (int i = 0; i < logicMat[0].length; i++) // ממקם שחקנים לבנים 
        {
            logicMat[1][i].setValue(BLACK);

        }
        for (int i = 0; i < logicMat[0].length; i++) // ממקם שחקנים שחורים 
        {
            logicMat[logicMat.length - 2][i].setValue(WHITE);

        }

    }

    /**
     *
     * @param value הערך של המיקום
     * @param arr - מערך שבוים
     */
    private void shovimAdd(int value, PlaceModel[] arr) {
        arr[freePlace(arr).getCol()].setValue(value);
    }

    /**
     * מחזיר את המקום הראשון הנקי
     *
     * @param arr
     * @return
     */
    private Location freePlace(PlaceModel[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getValue() == None) {
                return arr[i].getLocYesSvohim();
            }
        }
        return null;
    }

    private boolean CaptivesisEmpty(int Captives) {
        return Captives == 0;
    }

    void revive(Turn turn) {
        switch (turn) {
            case black:
                blackCaptives--;
                break;

            case white:

                whiteCaptives--;
                break;

            default:
            // code block
        }
    }

    void printAll() {
        for (int i = 0; i < logicMat.length; i++) {
            for (int j = 0; j < logicMat[0].length; j++) {
                System.out.print(logicMat[i][j].getValue() + ",");
            }
            System.out.println("");
        }
        System.out.println("------------------------------------------");
    }

    boolean Undo() {
        if (StackMove.isEmpty()) {
            return false;
        }
        for (int i = 0; i < 3; i++) {
            makeMove(StackMove.pop());

            if (StackMove.isEmpty() || StackMove.peek().getMove() == MoveEnume.go) {
                break;
            }
        }
        if (StackMove.isEmpty()) {
            Controller.lockUndo(false);
        }

        return true;
    }

    public void makeMove(Move move) {

        System.out.println(move);
        Blackconter = move.getBlackconter();
        blackCaptives = move.getBlackCaptives();
        whiteconter = move.getWhiteconter();
        whiteCaptives = move.getWhiteCaptives();
        printAll();
        logicMat[move.getNow().getLocYesSvohim().getRow()][move.getNow().getLocYesSvohim().getCol()].setValue(move.getNow().getValue());
        System.out.println("from" + logicMat[move.getNow().getLocYesSvohim().getRow()][move.getNow().getLocYesSvohim().getCol()].getValue() + "to" + move.getNow().getValue());
        logicMat[move.getToGo().getLocYesSvohim().getRow()][move.getToGo().getLocYesSvohim().getCol()].setValue(move.getToGo().getValue());
        System.out.println("from" + logicMat[move.getToGo().getLocYesSvohim().getRow()][move.getToGo().getLocYesSvohim().getCol()].getValue() + "to" + move.getToGo().getValue());
        printAll();
        Controller.ReplaceTurn(move.getTurn(), move.getNoturn());
    }

    void UpdedeStack(Location nowL, Location TOGoL, Turn turn, Turn Noturn, MoveEnume moveEnume) {
        PlaceModel ToGo, now;
        now = new PlaceModel(logicMat[nowL.getRow()][nowL.getCol()], null);
        ToGo = new PlaceModel(logicMat[TOGoL.getRow()][TOGoL.getCol()], null);
        Move move = new Move(Blackconter, whiteconter, blackCaptives, whiteCaptives, now, ToGo, turn, Noturn, moveEnume);

        StackMove.push(move);
    }

    static void PrintStack(Stack<Move> s) {
        System.out.println();
        System.out.println("\\\\\\\\\\\\\\\\");
        PrintStackHelper(s);
        System.out.println("\\\\\\\\\\\\\\\\");
        System.out.println();

    }

    static void PrintStackHelper(Stack<Move> s) {
        if (s.isEmpty()) {
            return;
        }

        Move x = s.peek();
        s.pop();
        PrintStack(s);
        System.out.println("--------------");
        System.out.println("| " + x + "  |");
        System.out.println("--------------");
        s.push(x);
    }

}
