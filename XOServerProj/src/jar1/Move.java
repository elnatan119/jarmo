package jar1;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author לוי
 */
public class Move {

    private int Blackconter;
    private int whiteconter;
    private int blackCaptives;
    private int whiteCaptives;
    private PlaceModel Now;
    private PlaceModel ToGo;
    private Turn turn;
    private Turn Noturn;
    private MoveEnume move;
    private double score;
    private int depth;

    public Move(int Blackconter, int whiteconter, int blackCaptives, int whiteCaptives, PlaceModel Now, PlaceModel ToGo, Turn turn, Turn Noturn, MoveEnume move) {
        this.Blackconter = Blackconter;
        this.whiteconter = whiteconter;
        this.blackCaptives = blackCaptives;
        this.whiteCaptives = whiteCaptives;
        this.Now = Now;
        this.ToGo = ToGo;
        this.turn = turn;
        this.Noturn = Noturn;
        this.move = move;
    }

    Move(double score, int depth) {
        this.score = score;
        this.depth = depth;
    }

     Move(Turn turn, Turn NOturn, Location ToGo, Location Now) 
    {
        this.Now = Game.getPlace(Now);
        this.ToGo =Game.getPlace(ToGo) ;
        this.turn = turn;
        this.Noturn = NOturn;
    }
    Move(Turn turn, Turn NOturn, Location ToGo, Location Now, MoveEnume move) 
    {
        this.Now = Game.getPlace(Now);
        this.ToGo =Game.getPlace(ToGo) ;
        this.turn = turn;
        this.Noturn = NOturn;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    Move(double score) {
        this.score = score;
    }

    public int getBlackconter() {
        return Blackconter;
    }

    public void setBlackconter(int Blackconter) {
        this.Blackconter = Blackconter;
    }

    public int getWhiteconter() {
        return whiteconter;
    }

    public void setWhiteconter(int whiteconter) {
        this.whiteconter = whiteconter;
    }

    public int getBlackCaptives() {
        return blackCaptives;
    }

    public void setBlackCaptives(int blackCaptives) {
        this.blackCaptives = blackCaptives;
    }

    public int getWhiteCaptives() {
        return whiteCaptives;
    }

    public void setWhiteCaptives(int whiteCaptives) {
        this.whiteCaptives = whiteCaptives;
    }

    public PlaceModel getNow() {
        return Now;
    }

    public void setNow(PlaceModel Now) {
        this.Now = Now;
    }

    public PlaceModel getToGo() {
        return ToGo;
    }

    public void setToGo(PlaceModel ToGo) {
        this.ToGo = ToGo;
    }

    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }

    public Turn getNoturn() {
        return Noturn;
    }

    public void setNoturn(Turn Noturn) {
        this.Noturn = Noturn;
    }

    public MoveEnume getMove() {
        return move;
    }

    public void setMove(MoveEnume move) {
        this.move = move;
    }

    @Override
    public String toString() 
    {
        return "";
    }

    void printD() 
    {
//        System.out.println("Now "+Now.getLocYesSvohim().toString()+"TOGO"+ToGo.getLocYesSvohim().toString());  
    }

}
