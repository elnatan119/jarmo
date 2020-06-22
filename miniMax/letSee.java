/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniMax;

/**
 *
 * @author לוי
 */
public class letSee 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    }
//
//    public Move miniMax(boolean boolPlayer, double alpha, double beta, int depth, int borderDepth, long timeMs) 
//    {
//        char player;
//        if (boolPlayer) 
//        {
//            player = Constant.BLACK_PLAYER;
//        } 
//        else 
//        {
//            player = Constant.WHITE_PLAYER;
//        }
//
//        double score = logic.getBoardScore(player);
//        if (logic.checkIfGameOver(player) || borderDepth == 0 || timeMs <= 0) 
//        {
//            if (timeMs <= 0) 
//            {
//                checkVar = false;
//            }
//            return new Move(score, depth);///איך אפשר להחזיר את זה 
//            
//        }
//
//        long t1, t2;
//        Move bestMove, tempMove;
//        ArrayList<Move> possibleMoves = GetAllCompMoves(player);
//
//        t1 = System.currentTimeMillis();
//
//        if (player == Constant.BLACK_PLAYER) 
//        {
//            bestMove = new Move(Integer.MIN_VALUE);// מזה בכלל איך אפשר לעשות את זה 
//            /// הפעולה מקבל ניקוד 
//        } 
//        else 
//        {
//            bestMove = new Move(Integer.MAX_VALUE);// למה הגדול
//        }
//
//        for (Move curMove : possibleMoves) // לכול אחד מזה
//        {
//            t2 = System.currentTimeMillis();
//            timeMs = timeMs - (t2 - t1);
//
//            logic.applyMove(curMove);
//            tempMove = miniMax(!boolPlayer, alpha, beta, depth + 1, borderDepth - 1, timeMs);
//            logic.undoMove(logic.getBoard(), curMove); /// מזה הפועלה עושה מחזירה האחורה או פעולת
//
//            if ((player == Constant.BLACK_PLAYER && tempMove.getScore() > bestMove.getScore())
//                    || (player == Constant.WHITE_PLAYER && tempMove.getScore() < bestMove.getScore())) 
//            {
//                bestMove.setScore(tempMove.getScore());
//                bestMove.setDepth(tempMove.getDepth());
//
//                //here you transfare all the data that you have in your move(curMove) into a new move(bestMove)
//                //this is GoGame properties' just example:
//           //     bestMove.setLocation(curMove.getLocation());
//            //    bestMove.setToolSign(curMove.getToolSign());
//             //   bestMove.setStupidMove(curMove.isItStupidMove());
//            //    bestMove.setDZones(curMove.getDZones());
//          //      bestMove.setCZones(curMove.getCZones());
//         //       bestMove.setEZones(curMove.getEZones());
//               score = bestMove.getScore();
//
//                if (player == Constant.BLACK_PLAYER) 
//                {
//                    alpha = Math.max(score, alpha);// יש ענפים שלא צרכים לקבוע 
//                } 
//                else 
//                {
//                    beta = Math.min(score, beta);
//                }
//            }
//            if (alpha >= beta || timeMs <= 0) 
//            {
//                break;
//            }
//        }
//        return bestMove;
//    }
}
