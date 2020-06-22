/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package var2;



import java.util.ArrayList;


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
    private static int Blackconter = 0;
    private static int whiteconter = 0;
    PlaceModel[] arrwhiteCaptives ;
    PlaceModel[] arrbalckCaptives;
    private ArrayList<PlaceModel> whiteCaptives;//הלבנים שורת השבויים
    private ArrayList<PlaceModel> blackCaptives;//  שורת השבויים השחורים

    public Model(int bordLength, int bordWidth) {
        whiteCaptives = new ArrayList<PlaceModel>();
        blackCaptives = new ArrayList<>();
        logicMat = new PlaceModel[bordLength][bordWidth];
        arrbalckCaptives = logicMat[0];
         arrwhiteCaptives = logicMat[logicMat.length - 1];
        for (int row = 0; row < bordLength; row++) {
            for (int col = 0; col < bordWidth; col++) {
                logicMat[row][col] = new PlaceModel(None, new Location(row, col));

            }

        }
        for (int i = 0; i < bordWidth; i++) // ממקם שחקנים לבנים 
        {
            logicMat[1][i].setValue(BLACK);

        }
        for (int i = 0; i < bordWidth; i++) // ממקם שחקנים שחורים 
        {
            logicMat[bordLength - 2][i].setValue(WHITE);

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

    public ArrayList<Location> GetRedZone() {
        ArrayList<Location> Arr = new ArrayList<>();
        for (int i = 0; i < logicMat[0].length; i++) {
            Arr.add(new Location(0, i));
            Arr.add(new Location(logicMat.length - 1, i));
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
        System.out.println("ymia21");
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
        System.out.println("ymia21");
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
                    arrsho[i].AddPossiblePlacesWITOSvohim(arrposible[j].getLocYesSvohim().getRow(), arrposible[j].getLocYesSvohim().getCol());
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
        System.out.println("var1.Model.addValid()");

        logicMat[placeOfX + 1][placeofY].AddPossiblePlacesNOSvohim(endPlaceOfx + 1, endplaceofY);
        logicMat[endPlaceOfx + 1][endplaceofY].AddPossiblePlacesNOSvohim(placeOfX + 1, placeofY);

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
        logicMat[btnloc.getRow()][btnloc.getCol()].setValue(logicMat[clickedLocation.getRow()][clickedLocation.getCol()].getValue());
        logicMat[clickedLocation.getRow()][clickedLocation.getCol()].setValue(None);
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

    void capture(Location player, Location enemy, Turn turn) {
        PlaceModel enemyPl = logicMat[enemy.getRow()][enemy.getCol()];
        PlaceModel playerPl = logicMat[player.getRow()][player.getCol()];
        if (turn == Turn.black) {
            blackCaptives.add(new PlaceModel(BLACK));
            updateCaptives(blackCaptives, arrbalckCaptives);
            playerPl.setValue(BLACKing);
        } else {
            whiteCaptives.add(new PlaceModel(WHITE));
            updateCaptives(whiteCaptives, arrwhiteCaptives);
            playerPl.setValue(WHITEKING);
        }

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

    ArrayList<Location> GetTheShvoim(Turn turn) 
    {
        if (turn == Turn.None) {
            throw new IllegalArgumentException("this not posisble to here None");
        }
        ArrayList<Location> arr = new ArrayList<Location>();
       PlaceModel[] arr2;
        if (turn == Turn.white) 
        {
            arr2 = arrwhiteCaptives;
        } 
        else 
        {
            arr2 = arrbalckCaptives;
        }

        for (int i = 0; i < arr2.length; i++) 
        {
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
    private void updateCaptives(ArrayList<PlaceModel> list, PlaceModel[] arr) {
        for (int i = 0; i < list.size(); i++) {

            arr[i].setValue(list.get(i).getValue());
            list.get(i).setLocYesSvohim(arr[i].getLocYesSvohim());
        }

    }

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
        } else if (btnloc.getRow() == 0 && (retunType(btnloc) == PlayeColor.white || retunType(btnloc) == PlayeColor.whiteKing)) {
            return true;
        }

        return false;
    }

    /**
     *
     * @return מחזיר את רשימה של אלה שאסור להם לזוז כי הם סיימו
     */
    ArrayList<Location> Endlist() {
        ArrayList<Location> arr = new ArrayList<>();
        Location a;
        for (int i = 0; i < logicMat[1].length; i++) {
            a = new Location(1, i);
            if (hasEnd(a)) {
                arr.add(a);
            }

        }
        for (int i = 0; i < logicMat[1].length; i++) {
            a = new Location(logicMat[1].length - 2, i);
            if (hasEnd(a)) {
                arr.add(a);
            }

        }
        return arr;
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
            if (hasEnd(btnloc) && logicMat[btnloc.getRow()][btnloc.getCol()].getValue() == BLACKing && blackCaptives.size() > 0) {
                return true;
            }
        } else if (turn == Turn.white) {
            if (hasEnd(btnloc) && logicMat[btnloc.getRow()][btnloc.getCol()].getValue() == WHITEKING && whiteCaptives.size() > 0) {
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
        if (whiteconter == getSoldiers(Turn.white).size() || Blackconter == getSoldiers(Turn.black).size()) {
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

    void revive(Location btnloc, Turn turn) {
        if (turn == Turn.black) {
            blackCaptives.remove(blackCaptives.size() - 1);
            logicMat[btnloc.getRow()][btnloc.getCol()].setValue(BLACK);
            updateCaptives(blackCaptives, arrbalckCaptives);
        } else {
            whiteCaptives.remove(whiteCaptives.size() - 1);
            logicMat[btnloc.getRow()][btnloc.getCol()].setValue(WHITE);
            updateCaptives(whiteCaptives, arrwhiteCaptives);
        }

    }

}
