public class Board {  
    private static String[] tileValues = {
        "lion", "lion",
        "penguin", "penguin",
        "dolphin", "dolphin",
        "fox", "fox",
        "monkey", "monkey",
        "turtle", "turtle"
    }; 

    private Tile[][] gameboard = new Tile[3][4];

    public Board() {
        shuffleArray(tileValues); 

        int index = 0;
        for (int row = 0; row < gameboard.length; row++) {
            for (int col = 0; col < gameboard[row].length; col++) {
                gameboard[row][col] = new Tile(tileValues[index]);
                System.out.println("Placed " + tileValues[index] + " at (" + row + "," + col + ")");
                index++;
            }
        }
    }

    private void shuffleArray(String[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1)); 
            String temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public String toString() {
        String boardString = "";
        for (Tile[] row : gameboard) {
            for (Tile tile : row) {
                if (tile.isShowingValue()) {
                    boardString += tile.getValue() + "\t";
                } else {
                    boardString += tile.getHidden() + "\t";
                }
            }
            boardString += "\n";
        }
        return boardString;
    }

    public boolean allTilesMatch() {
        for (Tile[] row : gameboard) {
            for (Tile tile : row) {
                if (!tile.matched()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void showValue(int row, int column) {
        if (row >= 0 && row < gameboard.length && column >= 0 && column < gameboard[0].length) {
            gameboard[row][column].show();
        }
    }

    public String checkForMatch(int row1, int col1, int row2, int col2) {
        Tile tile1 = gameboard[row1][col1];
        Tile tile2 = gameboard[row2][col2];

        if (tile1.getValue().equals(tile2.getValue())) {
            tile1.foundMatch();
            tile2.foundMatch();
            return "Match found!";
        } else {
            tile1.hide();
            tile2.hide();
            return "No match. Try again!";
        }
    }

    public boolean validateSelection(int row, int col) {
        return row >= 0 && row < gameboard.length && col >= 0 && col < gameboard[row].length && !gameboard[row][col].matched();
    }
}