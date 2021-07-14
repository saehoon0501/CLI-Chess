
import java.io.*;
import java.lang.*;
import java.util.*;



public class Controller
{
	private static String [] promoPiece = { "r","rook","룩","p","pawn","폰","b","bishop","비숍","q","queen","퀸","k","king","킹","n","knight","나이트"};
    //Method to create the gameboard
    public static Board createBoard(){
        Board gameBoard = new Board();
        for(int i = 0;i < 8;i++){
            for(int j = 0; j < 8; j++){
                gameBoard.GBoard[i][j] = null;
            }
        }

        int i = 1;

        for(int j = 0; j<8; j++){

            Gamepiece temp = new Gamepiece();
            temp.setPiece("P");
            temp.setPlayer("w");
            String pos = Controller.indToPos(i, j);
            temp.setPosition(pos);
            gameBoard.GBoard[i][j] = temp;
        }

        i = 0;

        for(int j = 0; j<8; j++){
            Gamepiece temp = new Gamepiece();
            temp.setPlayer("w");
            String pos = Controller.indToPos(i, j);
            temp.setPosition(pos);

            if(j == 0 || j == 7){
                temp.setPiece("R");
            }
            if(j == 1 || j == 6){
                temp.setPiece("N");
            }
            if(j == 2 || j == 5){
                temp.setPiece("B");
            }
            if(j == 3){
                temp.setPiece("Q");
            }
            if(j == 4){
                temp.setPiece("K");
            }
            gameBoard.GBoard[i][j] = temp;
        }

        i = 6;

        for(int j = 0; j<8; j++){
            Gamepiece temp = new Gamepiece();
            temp.setPiece("P");
            temp.setPlayer("b");
            String pos = Controller.indToPos(i, j);
            temp.setPosition(pos);
            gameBoard.GBoard[i][j] = temp;
        }

        i = 7;

        for(int j = 0; j<8; j++){
            Gamepiece temp = new Gamepiece();
            temp.setPlayer("b");
            String pos = Controller.indToPos(i, j);
            temp.setPosition(pos);

            if(j == 0 || j == 7){
                temp.setPiece("R");
            }
            if(j == 1 || j == 6){
                temp.setPiece("N");
            }
            if(j == 2 || j == 5){
                temp.setPiece("B");
            }
            if(j == 3){
                temp.setPiece("Q");
            }
            if(j == 4){
                temp.setPiece("K");
            }
            gameBoard.GBoard[i][j] = temp;
        }

        i = 1;

        for(int x = 0; x<8; x++){
            gameBoard.GBoard[i][x].createMoveSet(gameBoard);
        }

        i = 6;

        for(int x = 0; x<8; x++){
            gameBoard.GBoard[i][x].createMoveSet(gameBoard);
        }

        gameBoard.GBoard[0][0].createMoveSet(gameBoard);
        gameBoard.GBoard[0][7].createMoveSet(gameBoard);
        gameBoard.GBoard[7][0].createMoveSet(gameBoard);
        gameBoard.GBoard[7][7].createMoveSet(gameBoard);

        gameBoard.GBoard[0][1].createMoveSet(gameBoard);
        gameBoard.GBoard[0][6].createMoveSet(gameBoard);
        gameBoard.GBoard[7][1].createMoveSet(gameBoard);
        gameBoard.GBoard[7][6].createMoveSet(gameBoard);

        gameBoard.GBoard[0][2].createMoveSet(gameBoard);
        gameBoard.GBoard[0][5].createMoveSet(gameBoard);
        gameBoard.GBoard[7][2].createMoveSet(gameBoard);
        gameBoard.GBoard[7][5].createMoveSet(gameBoard);

        gameBoard.GBoard[0][3].createMoveSet(gameBoard);
        gameBoard.GBoard[7][3].createMoveSet(gameBoard);

        gameBoard.GBoard[0][4].createMoveSet(gameBoard);
        gameBoard.GBoard[7][4].createMoveSet(gameBoard);

        gameBoard.makePieceList();

        return gameBoard;
    }

    //Method to convert gameboard coordinates into FileRank
    public static String indToPos(int row, int col){
        String pos;
        int posRank = row +1;
        String posFile = "";

        if(col == 0){
            posFile = "a";
        }
        else if(col == 1){
            posFile = "b";
        }
        else if(col == 2){
            posFile = "c";
        }
        else if(col == 3){
            posFile = "d";
        }
        else if(col == 4){
            posFile = "e";
        }
        else if(col == 5){
            posFile = "f";
        }
        else if(col == 6){
            posFile = "g";
        }
        else if(col == 7){
            posFile = "h";
        }

        pos = posFile + Integer.toString(posRank);

        return pos;
    }

    //Covert file to J coordinate

    public static int fileToInd(String pos){
        int fileInd = -1;
        String posFile = pos.substring(0,1);

        if(posFile.equalsIgnoreCase("a")){
            fileInd = 0;
        }
        else if(posFile.equalsIgnoreCase("b")){
            fileInd = 1;
        }
        else if(posFile.equalsIgnoreCase("c")){
            fileInd = 2;
        }
        else if(posFile.equalsIgnoreCase("d")){
            fileInd = 3;
        }
        else if(posFile.equalsIgnoreCase("e")){
            fileInd = 4;
        }
        else if(posFile.equalsIgnoreCase("f")){
            fileInd = 5;
        }
        else if(posFile.equalsIgnoreCase("g")){
            fileInd = 6;
        }
        else if(posFile.equalsIgnoreCase("h")){
            fileInd = 7;
        }

        return fileInd;
    }

    //Convert rank to I coordinate

    public static int rankToInd(String pos){
        int rankInd = -1;
        String posRank = pos.substring(1,2);

        if(posRank.equalsIgnoreCase("1")){
            rankInd = 0;
        }
        else if(posRank.equalsIgnoreCase("2")){
            rankInd = 1;
        }
        else if(posRank.equalsIgnoreCase("3")){
            rankInd = 2;
        }
        else if(posRank.equalsIgnoreCase("4")){
            rankInd = 3;
        }
        else if(posRank.equalsIgnoreCase("5")){
            rankInd = 4;
        }
        else if(posRank.equalsIgnoreCase("6")){
            rankInd = 5;
        }
        else if(posRank.equalsIgnoreCase("7")){
            rankInd = 6;
        }
        else if(posRank.equalsIgnoreCase("8")){
            rankInd = 7;
        }

        return rankInd;
    }

    //Gatekeeper method to test if entered move is valid
    public static boolean isValidMove(String fromPos, String toPos, Board gameBoard, boolean isWhiteTurn){

        if((fromPos.length() != 2) || (toPos.length() != 2)){
            System.out.println("잘못된 형식입니다. 다시 시도 하세요.");
            return false;
        }

        if((!Character.isLetter(fromPos.charAt(0))) || (fromPos.substring(0,1).compareToIgnoreCase("h") > 0)){
            System.out.println("잘못된 형식입니다. 다시 시도 하세요.");
            return false;
        }

        if((!Character.isLetter(toPos.charAt(0))) || (toPos.substring(0,1).compareToIgnoreCase("h") > 0)){
            System.out.println("잘못된 형식입니다. 다시 시도 하세요.");
            return false;
        }

        if((!Character.isDigit(fromPos.charAt(1))) ||
                (Integer.parseInt(fromPos.substring(1,2)) > 8) ||
                (Integer.parseInt(fromPos.substring(1,2)) < 1)) {
            System.out.println("잘못된 형식입니다. 다시 시도 하세요.");
            return false;
        }

        if((!Character.isDigit(toPos.charAt(1))) ||
                (Integer.parseInt(toPos.substring(1,2)) > 8) ||
                (Integer.parseInt(toPos.substring(1,2)) < 1)){
            System.out.println("잘못된 형식입니다. 다시 시도 하세요.");
            return false;
        }

        int fromI = Controller.rankToInd(fromPos);
        int fromJ = Controller.fileToInd(fromPos);
        int toI = Controller.rankToInd(toPos);
        int toJ = Controller.fileToInd(toPos);

        if(gameBoard.GBoard[fromI][fromJ] == null){
            System.out.println("좌표에 기물이 존재하지 않습니다. 다시 시도 하세요.");
            return false;
        }

        if(isWhiteTurn && gameBoard.GBoard[fromI][fromJ].getPlayer().equalsIgnoreCase("b")){
            System.out.println("백 기물을 선택해 움직이세요.");
            return false;
        }

        if(!isWhiteTurn && gameBoard.GBoard[fromI][fromJ].getPlayer().equalsIgnoreCase("w")){
            System.out.println("흑 기물을 선택해 움직이세요.");
            return false;
        }

        gameBoard.updateMoves();

        if(gameBoard.GBoard[fromI][fromJ].getMoves().contains(toPos)){
            return true;
        }
        else{
            System.out.println("해당 기물에 맞지 않는 좌표이동 입니다.");
            System.out.println("유효한 움직임은 " + gameBoard.GBoard[fromI][fromJ].getPlayer() + gameBoard.GBoard[fromI][fromJ].getPiece() + " at " + gameBoard.GBoard[fromI][fromJ].getPosition() + " are:");
            for(int i = 0;i<gameBoard.GBoard[fromI][fromJ].getMoves().size();i++){
                System.out.print(gameBoard.GBoard[fromI][fromJ].getMoves().get(i) + " ");
            }
            System.out.println("\n");
            System.out.println("다시 시도 하세요.");
            return false;
        }
    }

    //Method to actually make the move on the gameboard
    public static Board processMove(String fromPos, String toPos, Board gameBoard, boolean isWhiteTurn){
        Board newBoard = new Board();
        newBoard = gameBoard;

        int fromI = Controller.rankToInd(fromPos);
        int fromJ = Controller.fileToInd(fromPos);
        int toI = Controller.rankToInd(toPos);
        int toJ = Controller.fileToInd(toPos);

        if(isWhiteTurn){
            if(!newBoard.GBoard[fromI][fromJ].getPiece().equalsIgnoreCase("♟") &&
                    !newBoard.GBoard[fromI][fromJ].getPiece().equalsIgnoreCase("♚")){
                Gamepiece movingPiece = newBoard.GBoard[fromI][fromJ];
                int newMC = movingPiece.getMoveCount() + 1;
                movingPiece.setMoveCount(newMC);
                movingPiece.setPosition(toPos.toLowerCase());

                if(newBoard.GBoard[toI][toJ] == null){
                    newBoard.GBoard[toI][toJ] = movingPiece;
                    newBoard.GBoard[fromI][fromJ] = null;
                    int pieceIndex = -1;
                    for(int i = 0; i<newBoard.getwhitePieceList().size(); i++){
                        if(newBoard.getwhitePieceList().get(i).equalsIgnoreCase(fromPos)){
                            pieceIndex = i;
                            break;
                        }
                    }
                    newBoard.getwhitePieceList().remove(pieceIndex);
                    newBoard.getwhitePieceList().add(movingPiece.getPosition());
                    newBoard.lastPieceMoved = movingPiece;
                    newBoard.updateMoves();

                    return newBoard;
                }
                else{

                    int blackPieceInd = -1;
                    for(int i = 0; i<newBoard.getblackPiecelist().size(); i++){
                        if(newBoard.getblackPiecelist().get(i).equalsIgnoreCase(toPos)){
                            blackPieceInd = i;
                            break;
                        }
                    }
                    newBoard.getblackPiecelist().remove(blackPieceInd);

                    newBoard.GBoard[toI][toJ] = movingPiece;
                    newBoard.GBoard[fromI][fromJ] = null;
                    int pieceIndex = -1;
                    for(int i = 0; i<newBoard.getwhitePieceList().size(); i++){
                        if(newBoard.getwhitePieceList().get(i).equalsIgnoreCase(fromPos)){
                            pieceIndex = i;
                            break;
                        }
                    }
                    newBoard.getwhitePieceList().remove(pieceIndex);
                    newBoard.getwhitePieceList().add(movingPiece.getPosition());
                    newBoard.lastPieceMoved = movingPiece;
                    newBoard.updateMoves();

                    return newBoard;
                }
            }

            if(newBoard.GBoard[fromI][fromJ].getPiece().equalsIgnoreCase("♟")){
                //en passant
                if((fromI == 4) && (toI == 5) && (toJ != fromJ) && (newBoard.GBoard[toI][toJ] == null)){
                    Gamepiece movingPiece = newBoard.GBoard[fromI][fromJ];
                    int newMC = movingPiece.getMoveCount() + 1;
                    movingPiece.setMoveCount(newMC);
                    movingPiece.setPosition(toPos.toLowerCase());

                    newBoard.GBoard[toI][toJ] = movingPiece;
                    newBoard.GBoard[fromI][fromJ] = null;
                    int pieceIndex = -1;
                    for(int i = 0; i<newBoard.getwhitePieceList().size(); i++){
                        if(newBoard.getwhitePieceList().get(i).equalsIgnoreCase(fromPos)){
                            pieceIndex = i;
                            break;
                        }
                    }
                    newBoard.getwhitePieceList().remove(pieceIndex);
                    newBoard.getwhitePieceList().add(movingPiece.getPosition());

                    newBoard.lastPieceMoved = movingPiece;

                    String EPPos = Controller.indToPos(toI - 1, toJ);

                    int blackPieceInd = -1;
                    for(int i = 0; i<newBoard.getblackPiecelist().size(); i++){
                        if(newBoard.getblackPiecelist().get(i).equalsIgnoreCase(EPPos)){
                            blackPieceInd = i;
                            break;
                        }
                    }
                    newBoard.getblackPiecelist().remove(blackPieceInd);

                    newBoard.GBoard[toI - 1][toJ] = null;
                    newBoard.updateMoves();

                    return newBoard;
                }
                else if(toI == 7){

                    if(newBoard.GBoard[toI][toJ] == null){
                        Gamepiece movingPiece = newBoard.GBoard[fromI][fromJ];
                        int newMC = movingPiece.getMoveCount() + 1;
                        movingPiece.setMoveCount(newMC);
                        movingPiece.setPosition(toPos.toLowerCase());
                        movingPiece.setPiece("♛");

                        newBoard.GBoard[toI][toJ] = movingPiece;
                        newBoard.GBoard[fromI][fromJ] = null;
                        int pieceIndex = -1;
                        for(int i = 0; i<newBoard.getwhitePieceList().size(); i++){
                            if(newBoard.getwhitePieceList().get(i).equalsIgnoreCase(fromPos)){
                                pieceIndex = i;
                                break;
                            }
                        }
                        newBoard.getwhitePieceList().remove(pieceIndex);
                        newBoard.getwhitePieceList().add(movingPiece.getPosition());

                        newBoard.lastPieceMoved = movingPiece;
                        newBoard.updateMoves();

                        return newBoard;
                    }
                    else{
                        int blackPieceInd = -1;
                        for(int i = 0; i<newBoard.getblackPiecelist().size(); i++){
                            if(newBoard.getblackPiecelist().get(i).equalsIgnoreCase(toPos)){
                                blackPieceInd = i;
                                break;
                            }
                        }
                        newBoard.getblackPiecelist().remove(blackPieceInd);

                        Gamepiece movingPiece = newBoard.GBoard[fromI][fromJ];
                        int newMC = movingPiece.getMoveCount() + 1;
                        movingPiece.setMoveCount(newMC);
                        movingPiece.setPosition(toPos.toLowerCase());
                        movingPiece.setPiece("♛");

                        newBoard.GBoard[toI][toJ] = movingPiece;
                        newBoard.GBoard[fromI][fromJ] = null;
                        int pieceIndex = -1;
                        for(int i = 0; i<newBoard.getwhitePieceList().size(); i++){
                            if(newBoard.getwhitePieceList().get(i).equalsIgnoreCase(fromPos)){
                                pieceIndex = i;
                                break;
                            }
                        }
                        newBoard.getwhitePieceList().remove(pieceIndex);
                        newBoard.getwhitePieceList().add(movingPiece.getPosition());

                        newBoard.lastPieceMoved = movingPiece;
                        newBoard.updateMoves();

                        return newBoard;
                    }

                }
                else{
                    Gamepiece movingPiece = newBoard.GBoard[fromI][fromJ];
                    int newMC = movingPiece.getMoveCount() + 1;
                    movingPiece.setMoveCount(newMC);
                    movingPiece.setPosition(toPos.toLowerCase());

                    if(newBoard.GBoard[toI][toJ] == null){
                        newBoard.GBoard[toI][toJ] = movingPiece;
                        newBoard.GBoard[fromI][fromJ] = null;
                        int pieceIndex = -1;
                        for(int i = 0; i<newBoard.getwhitePieceList().size(); i++){
                            if(newBoard.getwhitePieceList().get(i).equalsIgnoreCase(fromPos)){
                                pieceIndex = i;
                                break;
                            }
                        }
                        newBoard.getwhitePieceList().remove(pieceIndex);
                        newBoard.getwhitePieceList().add(movingPiece.getPosition());
                        newBoard.lastPieceMoved = movingPiece;
                        newBoard.updateMoves();

                        return newBoard;
                    }
                    else{

                        int blackPieceInd = -1;
                        for(int i = 0; i<newBoard.getblackPiecelist().size(); i++){
                            if(newBoard.getblackPiecelist().get(i).equalsIgnoreCase(toPos)){
                                blackPieceInd = i;
                                break;
                            }
                        }
                        newBoard.getblackPiecelist().remove(blackPieceInd);

                        newBoard.GBoard[toI][toJ] = movingPiece;
                        newBoard.GBoard[fromI][fromJ] = null;
                        int pieceIndex = -1;
                        for(int i = 0; i<newBoard.getwhitePieceList().size(); i++){
                            if(newBoard.getwhitePieceList().get(i).equalsIgnoreCase(fromPos)){
                                pieceIndex = i;
                                break;
                            }
                        }
                        newBoard.getwhitePieceList().remove(pieceIndex);
                        newBoard.getwhitePieceList().add(movingPiece.getPosition());
                        newBoard.lastPieceMoved = movingPiece;
                        newBoard.updateMoves();

                        return newBoard;
                    }
                }
            }

            if(newBoard.GBoard[fromI][fromJ].getPiece().equalsIgnoreCase("♚")){
                if((toI == 0) && (toJ == 6) && (newBoard.GBoard[fromI][fromJ].getMoveCount() == 0)){
                    Gamepiece movingPiece = newBoard.GBoard[fromI][fromJ];
                    int newMC = movingPiece.getMoveCount() + 1;
                    movingPiece.setMoveCount(newMC);
                    movingPiece.setPosition(toPos.toLowerCase());

                    newBoard.GBoard[toI][toJ] = movingPiece;
                    newBoard.GBoard[fromI][fromJ] = null;
                    int pieceIndex = -1;
                    for(int i = 0; i<newBoard.getwhitePieceList().size(); i++){
                        if(newBoard.getwhitePieceList().get(i).equalsIgnoreCase(fromPos)){
                            pieceIndex = i;
                            break;
                        }
                    }
                    newBoard.getwhitePieceList().remove(pieceIndex);
                    newBoard.getwhitePieceList().add(movingPiece.getPosition());
                    newBoard.lastPieceMoved = movingPiece;

                    //QUESTIONABLE SUCCESS
                    newBoard = Controller.processMove(Controller.indToPos(0, 7), Controller.indToPos(0, 5), newBoard, isWhiteTurn);
                    newBoard.updateMoves();

                    return newBoard;

                }
                else if((toI == 0) && (toJ == 2) && (newBoard.GBoard[fromI][fromJ].getMoveCount() == 0)){
                    Gamepiece movingPiece = newBoard.GBoard[fromI][fromJ];
                    int newMC = movingPiece.getMoveCount() + 1;
                    movingPiece.setMoveCount(newMC);
                    movingPiece.setPosition(toPos.toLowerCase());

                    newBoard.GBoard[toI][toJ] = movingPiece;
                    newBoard.GBoard[fromI][fromJ] = null;
                    int pieceIndex = -1;
                    for(int i = 0; i<newBoard.getwhitePieceList().size(); i++){
                        if(newBoard.getwhitePieceList().get(i).equalsIgnoreCase(fromPos)){
                            pieceIndex = i;
                            break;
                        }
                    }
                    newBoard.getwhitePieceList().remove(pieceIndex);
                    newBoard.getwhitePieceList().add(movingPiece.getPosition());
                    newBoard.lastPieceMoved = movingPiece;

                    newBoard = Controller.processMove(Controller.indToPos(0, 0), Controller.indToPos(0, 3), newBoard, isWhiteTurn);
                    newBoard.updateMoves();

                    return newBoard;
                }
                else{
                    Gamepiece movingPiece = newBoard.GBoard[fromI][fromJ];
                    int newMC = movingPiece.getMoveCount() + 1;
                    movingPiece.setMoveCount(newMC);
                    movingPiece.setPosition(toPos.toLowerCase());

                    if(newBoard.GBoard[toI][toJ] == null){
                        newBoard.GBoard[toI][toJ] = movingPiece;
                        newBoard.GBoard[fromI][fromJ] = null;
                        int pieceIndex = -1;
                        for(int i = 0; i<newBoard.getwhitePieceList().size(); i++){
                            if(newBoard.getwhitePieceList().get(i).equalsIgnoreCase(fromPos)){
                                pieceIndex = i;
                                break;
                            }
                        }
                        newBoard.getwhitePieceList().remove(pieceIndex);
                        newBoard.getwhitePieceList().add(movingPiece.getPosition());
                        newBoard.lastPieceMoved = movingPiece;
                        newBoard.updateMoves();

                        return newBoard;
                    }
                    else{

                        int blackPieceInd = -1;
                        for(int i = 0; i<newBoard.getblackPiecelist().size(); i++){
                            if(newBoard.getblackPiecelist().get(i).equalsIgnoreCase(toPos)){
                                blackPieceInd = i;
                                break;
                            }
                        }
                        newBoard.getblackPiecelist().remove(blackPieceInd);

                        newBoard.GBoard[toI][toJ] = movingPiece;
                        newBoard.GBoard[fromI][fromJ] = null;
                        int pieceIndex = -1;
                        for(int i = 0; i<newBoard.getwhitePieceList().size(); i++){
                            if(newBoard.getwhitePieceList().get(i).equalsIgnoreCase(fromPos)){
                                pieceIndex = i;
                                break;
                            }
                        }
                        newBoard.getwhitePieceList().remove(pieceIndex);
                        newBoard.getwhitePieceList().add(movingPiece.getPosition());
                        newBoard.lastPieceMoved = movingPiece;
                        newBoard.updateMoves();
                        return newBoard;
                    }
                }
            }
        }
        else{

            if(!newBoard.GBoard[fromI][fromJ].getPiece().equalsIgnoreCase("♟") &&
                    !newBoard.GBoard[fromI][fromJ].getPiece().equalsIgnoreCase("♚")){
                Gamepiece movingPiece = newBoard.GBoard[fromI][fromJ];
                int newMC = movingPiece.getMoveCount() + 1;
                movingPiece.setMoveCount(newMC);
                movingPiece.setPosition(toPos.toLowerCase());

                if(newBoard.GBoard[toI][toJ] == null){
                    newBoard.GBoard[toI][toJ] = movingPiece;
                    newBoard.GBoard[fromI][fromJ] = null;
                    int pieceIndex = -1;
                    for(int i = 0; i<newBoard.getblackPiecelist().size(); i++){
                        if(newBoard.getblackPiecelist().get(i).equalsIgnoreCase(fromPos)){
                            pieceIndex = i;
                            break;
                        }
                    }
                    newBoard.getblackPiecelist().remove(pieceIndex);
                    newBoard.getblackPiecelist().add(movingPiece.getPosition());
                    newBoard.lastPieceMoved = movingPiece;
                    newBoard.updateMoves();

                    return newBoard;
                }
                else{

                    int whitePieceInd = -1;
                    for(int i = 0; i<newBoard.getwhitePieceList().size(); i++){
                        if(newBoard.getwhitePieceList().get(i).equalsIgnoreCase(toPos)){
                            whitePieceInd = i;
                            break;
                        }
                    }
                    newBoard.getwhitePieceList().remove(whitePieceInd);

                    newBoard.GBoard[toI][toJ] = movingPiece;
                    newBoard.GBoard[fromI][fromJ] = null;
                    int pieceIndex = -1;
                    for(int i = 0; i<newBoard.getblackPiecelist().size(); i++){
                        if(newBoard.getblackPiecelist().get(i).equalsIgnoreCase(fromPos)){
                            pieceIndex = i;
                            break;
                        }
                    }
                    newBoard.getblackPiecelist().remove(pieceIndex);
                    newBoard.getblackPiecelist().add(movingPiece.getPosition());
                    newBoard.lastPieceMoved = movingPiece;
                    newBoard.updateMoves();

                    return newBoard;
                }
            }

            if(newBoard.GBoard[fromI][fromJ].getPiece().equalsIgnoreCase("♟")){
                //en passant
                if((fromI == 3) && (toI == 2) && (toJ != fromJ) && (newBoard.GBoard[toI][toJ] == null)){

                    Gamepiece movingPiece = newBoard.GBoard[fromI][fromJ];
                    int newMC = movingPiece.getMoveCount() + 1;
                    movingPiece.setMoveCount(newMC);
                    movingPiece.setPosition(toPos.toLowerCase());

                    newBoard.GBoard[toI][toJ] = movingPiece;
                    newBoard.GBoard[fromI][fromJ] = null;
                    int pieceIndex = -1;
                    for(int i = 0; i<newBoard.getblackPiecelist().size(); i++){
                        if(newBoard.getblackPiecelist().get(i).equalsIgnoreCase(fromPos)){
                            pieceIndex = i;
                            break;
                        }
                    }
                    newBoard.getblackPiecelist().remove(pieceIndex);
                    newBoard.getblackPiecelist().add(movingPiece.getPosition());

                    newBoard.lastPieceMoved = movingPiece;

                    String EPPos = Controller.indToPos(toI + 1, toJ);

                    int whitePieceInd = -1;
                    for(int i = 0; i<newBoard.getwhitePieceList().size(); i++){
                        if(newBoard.getwhitePieceList().get(i).equalsIgnoreCase(EPPos)){
                            whitePieceInd = i;
                            break;
                        }
                    }
                    newBoard.getwhitePieceList().remove(whitePieceInd);

                    newBoard.GBoard[toI + 1][toJ] = null;
                    newBoard.updateMoves();

                    return newBoard;
                }
                else if(toI == 7){

                    if(newBoard.GBoard[toI][toJ] == null){
                        Gamepiece movingPiece = newBoard.GBoard[fromI][fromJ];
                        int newMC = movingPiece.getMoveCount() + 1;
                        movingPiece.setMoveCount(newMC);
                        movingPiece.setPosition(toPos.toLowerCase());
                        movingPiece.setPiece("♛");

                        newBoard.GBoard[toI][toJ] = movingPiece;
                        newBoard.GBoard[fromI][fromJ] = null;
                        int pieceIndex = -1;
                        for(int i = 0; i<newBoard.getblackPiecelist().size(); i++){
                            if(newBoard.getblackPiecelist().get(i).equalsIgnoreCase(fromPos)){
                                pieceIndex = i;
                                break;
                            }
                        }
                        newBoard.getblackPiecelist().remove(pieceIndex);
                        newBoard.getblackPiecelist().add(movingPiece.getPosition());

                        newBoard.lastPieceMoved = movingPiece;
                        newBoard.updateMoves();

                        return newBoard;
                    }
                    else{
                        int whitePieceInd = -1;
                        for(int i = 0; i<newBoard.getwhitePieceList().size(); i++){
                            if(newBoard.getwhitePieceList().get(i).equalsIgnoreCase(toPos)){
                                whitePieceInd = i;
                                break;
                            }
                        }
                        newBoard.getwhitePieceList().remove(whitePieceInd);

                        Gamepiece movingPiece = newBoard.GBoard[fromI][fromJ];
                        int newMC = movingPiece.getMoveCount() + 1;
                        movingPiece.setMoveCount(newMC);
                        movingPiece.setPosition(toPos.toLowerCase());
                        movingPiece.setPiece("♛");

                        newBoard.GBoard[toI][toJ] = movingPiece;
                        newBoard.GBoard[fromI][fromJ] = null;
                        int pieceIndex = -1;
                        for(int i = 0; i<newBoard.getblackPiecelist().size(); i++){
                            if(newBoard.getblackPiecelist().get(i).equalsIgnoreCase(fromPos)){
                                pieceIndex = i;
                                break;
                            }
                        }
                        newBoard.getblackPiecelist().remove(pieceIndex);
                        newBoard.getblackPiecelist().add(movingPiece.getPosition());

                        newBoard.lastPieceMoved = movingPiece;
                        newBoard.updateMoves();

                        return newBoard;
                    }

                }
                else{
                    Gamepiece movingPiece = newBoard.GBoard[fromI][fromJ];
                    int newMC = movingPiece.getMoveCount() + 1;
                    movingPiece.setMoveCount(newMC);
                    movingPiece.setPosition(toPos.toLowerCase());

                    if(newBoard.GBoard[toI][toJ] == null){
                        newBoard.GBoard[toI][toJ] = movingPiece;
                        newBoard.GBoard[fromI][fromJ] = null;
                        int pieceIndex = -1;
                        for(int i = 0; i<newBoard.getblackPiecelist().size(); i++){
                            if(newBoard.getblackPiecelist().get(i).equalsIgnoreCase(fromPos)){
                                pieceIndex = i;
                                break;
                            }
                        }
                        newBoard.getblackPiecelist().remove(pieceIndex);
                        newBoard.getblackPiecelist().add(movingPiece.getPosition());
                        newBoard.lastPieceMoved = movingPiece;
                        newBoard.updateMoves();

                        return newBoard;
                    }
                    else{

                        int whitePieceInd = -1;
                        for(int i = 0; i<newBoard.getwhitePieceList().size(); i++){
                            if(newBoard.getwhitePieceList().get(i).equalsIgnoreCase(toPos)){
                                whitePieceInd = i;
                                break;
                            }
                        }
                        newBoard.getwhitePieceList().remove(whitePieceInd);

                        newBoard.GBoard[toI][toJ] = movingPiece;
                        newBoard.GBoard[fromI][fromJ] = null;
                        int pieceIndex = -1;
                        for(int i = 0; i<newBoard.getblackPiecelist().size(); i++){
                            if(newBoard.getblackPiecelist().get(i).equalsIgnoreCase(fromPos)){
                                pieceIndex = i;
                                break;
                            }
                        }
                        newBoard.getblackPiecelist().remove(pieceIndex);
                        newBoard.getblackPiecelist().add(movingPiece.getPosition());
                        newBoard.lastPieceMoved = movingPiece;
                        newBoard.updateMoves();

                        return newBoard;
                    }
                }
            }

            if(newBoard.GBoard[fromI][fromJ].getPiece().equalsIgnoreCase("♚")){
                if((toI == 7) && (toJ == 6) && (newBoard.GBoard[fromI][fromJ].getMoveCount() == 0)){
                    Gamepiece movingPiece = newBoard.GBoard[fromI][fromJ];
                    int newMC = movingPiece.getMoveCount() + 1;
                    movingPiece.setMoveCount(newMC);
                    movingPiece.setPosition(toPos.toLowerCase());

                    newBoard.GBoard[toI][toJ] = movingPiece;
                    newBoard.GBoard[fromI][fromJ] = null;
                    int pieceIndex = -1;
                    for(int i = 0; i<newBoard.getblackPiecelist().size(); i++){
                        if(newBoard.getblackPiecelist().get(i).equalsIgnoreCase(fromPos)){
                            pieceIndex = i;
                            break;
                        }
                    }
                    newBoard.getblackPiecelist().remove(pieceIndex);
                    newBoard.getblackPiecelist().add(movingPiece.getPosition());


                    //QUESTIONABLE SUCCESS
                    newBoard = Controller.processMove(Controller.indToPos(7, 7), Controller.indToPos(7, 5), newBoard, isWhiteTurn);
                    newBoard.lastPieceMoved = movingPiece;
                    newBoard.updateMoves();

                    return newBoard;

                }
                else if((toI == 7) && (toJ == 2) && (newBoard.GBoard[fromI][fromJ].getMoveCount() == 0)){
                    Gamepiece movingPiece = newBoard.GBoard[fromI][fromJ];
                    int newMC = movingPiece.getMoveCount() + 1;
                    movingPiece.setMoveCount(newMC);
                    movingPiece.setPosition(toPos.toLowerCase());

                    newBoard.GBoard[toI][toJ] = movingPiece;
                    newBoard.GBoard[fromI][fromJ] = null;
                    int pieceIndex = -1;
                    for(int i = 0; i<newBoard.getblackPiecelist().size(); i++){
                        if(newBoard.getblackPiecelist().get(i).equalsIgnoreCase(fromPos)){
                            pieceIndex = i;
                            break;
                        }
                    }
                    newBoard.getblackPiecelist().remove(pieceIndex);
                    newBoard.getblackPiecelist().add(movingPiece.getPosition());

                    newBoard = Controller.processMove(Controller.indToPos(7, 0), Controller.indToPos(7, 3), newBoard, isWhiteTurn);
                    newBoard.lastPieceMoved = movingPiece;
                    newBoard.updateMoves();
                    return newBoard;
                }
                else{
                    Gamepiece movingPiece = newBoard.GBoard[fromI][fromJ];
                    int newMC = movingPiece.getMoveCount() + 1;
                    movingPiece.setMoveCount(newMC);
                    movingPiece.setPosition(toPos.toLowerCase());

                    if(newBoard.GBoard[toI][toJ] == null){
                        newBoard.GBoard[toI][toJ] = movingPiece;
                        newBoard.GBoard[fromI][fromJ] = null;
                        int pieceIndex = -1;
                        for(int i = 0; i<newBoard.getblackPiecelist().size(); i++){
                            if(newBoard.getblackPiecelist().get(i).equalsIgnoreCase(fromPos)){
                                pieceIndex = i;
                                break;
                            }
                        }
                        newBoard.getblackPiecelist().remove(pieceIndex);
                        newBoard.getblackPiecelist().add(movingPiece.getPosition());
                        newBoard.lastPieceMoved = movingPiece;
                        newBoard.updateMoves();

                        return newBoard;
                    }
                    else{

                        int whitePieceInd = -1;
                        for(int i = 0; i<newBoard.getwhitePieceList().size(); i++){
                            if(newBoard.getwhitePieceList().get(i).equalsIgnoreCase(toPos)){
                                whitePieceInd = i;
                                break;
                            }
                        }
                        newBoard.getwhitePieceList().remove(whitePieceInd);

                        newBoard.GBoard[toI][toJ] = movingPiece;
                        newBoard.GBoard[fromI][fromJ] = null;
                        int pieceIndex = -1;
                        for(int i = 0; i<newBoard.getblackPiecelist().size(); i++){
                            if(newBoard.getblackPiecelist().get(i).equalsIgnoreCase(fromPos)){
                                pieceIndex = i;
                                break;
                            }
                        }
                        newBoard.getblackPiecelist().remove(pieceIndex);
                        newBoard.getblackPiecelist().add(movingPiece.getPosition());
                        newBoard.lastPieceMoved = movingPiece;
                        newBoard.updateMoves();
                        return newBoard;
                    }
                }
            }

        }
        return newBoard;
    }
    public static boolean satisfiesPromo(Gamepiece piece, String toPos)
    {
    	if(piece.getPlayer().toLowerCase().equals("w"))
    	{
    		if(toPos.substring(1,2).toLowerCase().equals("8"))
    			return true;
    		return false;
    	}
    	if(toPos.substring(1,2).toLowerCase().equals("1"))
			return true;
		return false;
    }
    public static void processPromo(Gamepiece piece, Board currentBoard)
    {
    	Scanner keyboard= new Scanner(System.in);
    	System.out.println("변경하고 싶은 말을 선택해주세요: ");
    	System.out.println("예)p, r, rook, Knight...");
    	String input = keyboard.nextLine();
        String flag = input.trim().replaceAll("\\s+","").toLowerCase();
    	while(!(Arrays.asList(promoPiece).contains(flag))||(flag.equals("k")||flag.equals("king")||flag.equals("킹")))
    	{
    		System.err.println("해당 기물로는 프로모션할 수 없습니다");
        	System.out.println("변경하고 싶은 말을 선택해주세요: ");
        	System.out.println("예)p, r, rook, Knight...");
    		input = keyboard.nextLine();
    		flag = input.trim().replaceAll("\\s+","").toLowerCase();
    	}
    	if(Arrays.asList(Arrays.copyOfRange(promoPiece, 0, 3)).contains(flag))
    	{
    		piece.setPiece("♜");
    		if(piece.getPlayer().toLowerCase().equals("w"))
    			piece.setPiece("♖");
    	}
    	else if(Arrays.asList(Arrays.copyOfRange(promoPiece, 3, 6)).contains(flag))
    	{
      		piece.setPiece("♟");
    		if(piece.getPlayer().toLowerCase().equals("w"))
    			piece.setPiece("♙");
    	}
    	else if(Arrays.asList(Arrays.copyOfRange(promoPiece, 6, 9)).contains(flag))
    	{
      		piece.setPiece("︎︎♝");
    		if(piece.getPlayer().toLowerCase().equals("w"))
    			piece.setPiece("♗");
    	}
    	else if(Arrays.asList(Arrays.copyOfRange(promoPiece, 9, 12)).contains(flag))
    	{
      		piece.setPiece("︎︎♕");
    		if(piece.getPlayer().toLowerCase().equals("w"))
    			piece.setPiece("♛");
    	}
    	else if(Arrays.asList(Arrays.copyOfRange(promoPiece, 12, 15)).contains(flag))
    	{
      		piece.setPiece("︎︎♚");
    		if(piece.getPlayer().toLowerCase().equals("w"))
    			piece.setPiece("♔");
    	}
    	else if(Arrays.asList(Arrays.copyOfRange(promoPiece, 15, 18)).contains(flag))
    	{
      		piece.setPiece("︎︎♞");
    		if(piece.getPlayer().toLowerCase().equals("w"))
    			piece.setPiece("♘");
    	}
    	//creeate mvoeset??
    	piece.createMoveSet(currentBoard);
    }
    //Method to make special pawn promotion move
//    public static Board processPromo(String fromPos, String toPos, Board gameBoard, boolean isWhiteTurn, String promo){
//        Board newBoard = new Board();
//        newBoard = gameBoard;
//        int fromI = Controller.rankToInd(fromPos);
//        int fromJ = Controller.fileToInd(fromPos);
//        int toI = Controller.rankToInd(toPos);
//        int toJ = Controller.fileToInd(toPos);
//
//        if(isWhiteTurn){
//            if(newBoard.GBoard[toI][toJ] == null){
//                Gamepiece movingPiece = newBoard.GBoard[fromI][fromJ];
//                int newMC = movingPiece.getMoveCount() + 1;
//                movingPiece.setMoveCount(newMC);
//                movingPiece.setPosition(toPos.toLowerCase());
//                movingPiece.setPiece(promo);
//
//                newBoard.GBoard[toI][toJ] = movingPiece;
//                newBoard.GBoard[fromI][fromJ] = null;
//                int pieceIndex = -1;
//                for(int i = 0; i<newBoard.getwhitePieceList().size(); i++){
//                    if(newBoard.getwhitePieceList().get(i).equalsIgnoreCase(fromPos)){
//                        pieceIndex = i;
//                        break;
//                    }
//                }
//                newBoard.getwhitePieceList().remove(pieceIndex);
//                newBoard.getwhitePieceList().add(movingPiece.getPosition());
//
//                newBoard.lastPieceMoved = movingPiece;
//                newBoard.updateMoves();
//
//                return newBoard;
//            }
//            else{
//                int blackPieceInd = -1;
//                for(int i = 0; i<newBoard.getblackPiecelist().size(); i++){
//                    if(newBoard.getblackPiecelist().get(i).equalsIgnoreCase(toPos)){
//                        blackPieceInd = i;
//                        break;
//                    }
//                }
//                newBoard.getblackPiecelist().remove(blackPieceInd);
//
//                Gamepiece movingPiece = newBoard.GBoard[fromI][fromJ];
//                int newMC = movingPiece.getMoveCount() + 1;
//                movingPiece.setMoveCount(newMC);
//                movingPiece.setPosition(toPos.toLowerCase());
//                movingPiece.setPiece(promo);
//
//                newBoard.GBoard[toI][toJ] = movingPiece;
//                newBoard.GBoard[fromI][fromJ] = null;
//                int pieceIndex = -1;
//                for(int i = 0; i<newBoard.getwhitePieceList().size(); i++){
//                    if(newBoard.getwhitePieceList().get(i).equalsIgnoreCase(fromPos)){
//                        pieceIndex = i;
//                        break;
//                    }
//                }
//                newBoard.getwhitePieceList().remove(pieceIndex);
//                newBoard.getwhitePieceList().add(movingPiece.getPosition());
//
//                newBoard.lastPieceMoved = movingPiece;
//                newBoard.updateMoves();
//
//                return newBoard;
//            }
//        }
//        else{
//            if(newBoard.GBoard[toI][toJ] == null){
//                Gamepiece movingPiece = newBoard.GBoard[fromI][fromJ];
//                int newMC = movingPiece.getMoveCount() + 1;
//                movingPiece.setMoveCount(newMC);
//                movingPiece.setPosition(toPos.toLowerCase());
//                movingPiece.setPiece(promo);
//
//                newBoard.GBoard[toI][toJ] = movingPiece;
//                newBoard.GBoard[fromI][fromJ] = null;
//                int pieceIndex = -1;
//                for(int i = 0; i<newBoard.getblackPiecelist().size(); i++){
//                    if(newBoard.getblackPiecelist().get(i).equalsIgnoreCase(fromPos)){
//                        pieceIndex = i;
//                        break;
//                    }
//                }
//                newBoard.getblackPiecelist().remove(pieceIndex);
//                newBoard.getblackPiecelist().add(movingPiece.getPosition());
//
//                newBoard.lastPieceMoved = movingPiece;
//                newBoard.updateMoves();
//
//                return newBoard;
//            }
//            else{
//                int whitePieceInd = -1;
//                for(int i = 0; i<newBoard.getwhitePieceList().size(); i++){
//                    if(newBoard.getwhitePieceList().get(i).equalsIgnoreCase(toPos)){
//                        whitePieceInd = i;
//                        break;
//                    }
//                }
//                newBoard.getwhitePieceList().remove(whitePieceInd);
//
//                Gamepiece movingPiece = newBoard.GBoard[fromI][fromJ];
//                int newMC = movingPiece.getMoveCount() + 1;
//                movingPiece.setMoveCount(newMC);
//                movingPiece.setPosition(toPos.toLowerCase());
//                movingPiece.setPiece(promo);
//
//                newBoard.GBoard[toI][toJ] = movingPiece;
//                newBoard.GBoard[fromI][fromJ] = null;
//                int pieceIndex = -1;
//                for(int i = 0; i<newBoard.getblackPiecelist().size(); i++){
//                    if(newBoard.getblackPiecelist().get(i).equalsIgnoreCase(fromPos)){
//                        pieceIndex = i;
//                        break;
//                    }
//                }
//                newBoard.getblackPiecelist().remove(pieceIndex);
//                newBoard.getblackPiecelist().add(movingPiece.getPosition());
//
//                newBoard.lastPieceMoved = movingPiece;
//                newBoard.updateMoves();
//
//                return newBoard;
//            }
//        }
//    }

    public static void getRecommendation(Board gameBoard, boolean isWhite){

        ArrayList<Gamepiece> allPieces = new ArrayList<Gamepiece>();

        for(int i = 7; i>=0; i--){
            for(int j = 0; j<8; j++){
                if(gameBoard.GBoard[i][j] == null){
                    continue;
                }
                else{
                    if(gameBoard.GBoard[i][j].getPlayer().equalsIgnoreCase("w")){
                        allPieces.add(gameBoard.GBoard[i][j]);
                    }
                }
            }
        }

        for(Gamepiece i: allPieces){
            System.out.println(i.getMoves());
        }

    }

    public static void randomMove(Board gameBoard, boolean playerIsWhite){
        if(playerIsWhite){
            ArrayList<Gamepiece> allPieces = new ArrayList<Gamepiece>();

            for(int i = 7; i>=0; i--){
                for(int j = 0; j<8; j++){
                    if(gameBoard.GBoard[i][j] == null){
                        continue;
                    }
                    else{
                        if(gameBoard.GBoard[i][j].getPlayer().equalsIgnoreCase("b")){
                            allPieces.add(gameBoard.GBoard[i][j]);
                        }
                    }
                }
            }

            for(Gamepiece i: allPieces){
                double temp = Math.random();
                if(temp >= 0.5){
                    if(!i.moves.isEmpty()){
                        processMove(i.getPosition(),i.moves.get(0), gameBoard,false);
                    }
                    else{
                        continue;
                    }

                }
                else{
                    continue;
                }
            }
        }
        else
        {
            ArrayList<Gamepiece> allPieces = new ArrayList<Gamepiece>();

            for(int i = 7; i>=0; i--){
                for(int j = 0; j<8; j++){
                    if(gameBoard.GBoard[i][j] == null){
                        continue;
                    }
                    else{
                        if(gameBoard.GBoard[i][j].getPlayer().equalsIgnoreCase("w")){
                            allPieces.add(gameBoard.GBoard[i][j]);
                        }
                    }
                }
            }
            for(Gamepiece i: allPieces){
                double temp = Math.random();
                if(temp >= 0.5){
                    if(!i.moves.isEmpty()){
                        processMove(i.getPosition(),i.moves.get(0),gameBoard,true);
                    }
                    else{
                        continue;
                    }

                }
                else{
                    continue;
                }
            }

        }
    }

}