/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jarmo;

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
    private PlaceModel[] whiteCaptives;//הלבנים שורת השבויים
    private PlaceModel[] balckCaptives;//  שורת השבויים השחורים

    public Model(int bordLength, int bordWidth) {
        logicMat = new PlaceModel[bordLength][bordWidth];
        whiteCaptives = logicMat[0];// מצביע על שורה הראשונה
        balckCaptives = logicMat[bordLength - 1];// מצביע על שורה האחרונה
        for (int row = 0; row < bordLength; row++) {
            for (int col = 0; col < bordWidth; col++) {
                logicMat[row][col] = new PlaceModel(None, new Location(row, col));

            }

        }
        for (int i = 0; i < bordWidth; i++) // ממקם שחקנים לבנים 
        {
            logicMat[1][i].setValue(WHITE);

        }
        for (int i = 0; i < bordWidth; i++) // ממקם שחקנים שחורים 
        {
            logicMat[bordLength - 2][i].setValue(BLACK);

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
        ymia21.add(new Location(0, 1));
        ymia21.add(new Location(0, 2));
        ymia21.add(new Location(2, 2));
        ymia21.add(new Location(0, 3));
        ymia21.add(new Location(1, 3));
        ymia21.add(new Location(2, 3));
             
        ymia21.forEach((n) -> addValid(n, 2, 1));
        ////////////// אלכסון מתון ימינה
        ArrayList<Location> ymia12 = new ArrayList<>();
        ymia12.add(new Location(0, 0));
        ymia12.add(new Location(2, 0));
        ymia12.add(new Location(3, 0));
        ymia12.add(new Location(1, 1));
        ymia12.add(new Location(0, 2));
        ymia12.add(new Location(1, 2));
       
        ymia12.add(new Location(3, 2));
        ymia12.forEach((n) -> addValid(n, 1, 2));
        ////////////    אלכסון שמאלה עולה למעלה
        ArrayList<Location> leftM12 = new ArrayList<>();
        leftM12.add(new Location(1, 0));
        leftM12.add(new Location(3, 0));
        leftM12.add(new Location(4, 0));
        leftM12.add(new Location(4, 1));
        leftM12.add(new Location(1, 1));
       
         leftM12.add(new Location(1, 2));
        leftM12.forEach((n) -> addValid(n, -1, 2));
        ////////////////////// אלכסון שמאלה קיצוני 
        ArrayList<Location> leftM21 = new ArrayList<>();
        leftM21.add(new Location(2, 0));
        leftM21.add(new Location(3, 0));
        leftM21.add(new Location(4, 0));
        leftM21.add(new Location(3, 1));
        leftM21.add(new Location(2, 2));
        leftM21.add(new Location(3, 2));
        leftM21.add(new Location(4, 3));
        leftM21.add(new Location(3, 3));
        leftM21.add(new Location(2, 3));
        leftM21.add(new Location(1, 1));
        leftM21.forEach((n) -> addValid(n, -2, 1));
        /////////// ימינה
        ArrayList<Location> ymia10 = new ArrayList<>();
        ymia10.add(new Location(1, 1));
        ymia10.add(new Location(2, 1));
        ymia10.add(new Location(1, 3));
        ymia10.add(new Location(2, 3));
        ymia10.forEach((n) -> addValid(n, 1, 0));
        ///////////למעלה
        ArrayList<Location> lmala01 = new ArrayList<>();
        lmala01.add(new Location(1, 1));
        lmala01.add(new Location(2, 1));
        lmala01.add(new Location(1, 3));
        lmala01.add(new Location(2, 3));
        lmala01.forEach((n) -> addValid(n, 0, 1));
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
        logicMat[placeOfX + 1][placeofY].AddPossiblePlacesNOSvohim(endPlaceOfx + 1, endplaceofY);
        logicMat[endPlaceOfx + 1][endplaceofY].AddPossiblePlacesNOSvohim(placeOfX + 1, placeofY);

    }

    public ArrayList<Location> GetPosiblePlace(Location place) {
        return logicMat[place.getRow()][place.getCol()].getPossiblePlaces();
    }

    boolean exsitInGetPosiblePlace(Location clickedLocation, Location btnloc) {
        ArrayList<Location> Places = logicMat[clickedLocation.getRow()][clickedLocation.getCol()].getPossiblePlaces();
        for (int i = 0; i < Places.size(); i++) {
            if (Places.get(i).equal(btnloc)) {
                return true;
            }

        }
        return false;
    }

    void go(Location clickedLocation, Location btnloc) {

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

    void capture(Location clickedLocation, Location btnloc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
