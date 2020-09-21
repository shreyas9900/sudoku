package sudoku;

import java.util.Arrays;

public class Utils {

    public int[][] copyMatrix(int[][] matrix) {

        int[][] copy = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            copy[i] = matrix[i].clone();
        }
        return copy;

    }

    boolean isValid(int[][] matrix, int val,  int row, int column ){
        for(int i = 0 ; i < 9 ; i++) {
            if (matrix[row][i] == val) {
                return false;
            }
        }
        for (int i = 0 ; i < 9 ; i++) {
            if (matrix[i][column] == val) {
                return false;
            }
        }
        return  gridValidity(matrix, val, row, column );
    }

    boolean gridValidity(int[][] matrix, int val, int row, int column) {
        int microGridSize = (int)Math.sqrt(matrix.length);
        int rowPosition = row/microGridSize;
        int colPosition = column/microGridSize;
        int topLeftRow = rowPosition * microGridSize;
        int topLeftColumn = colPosition * microGridSize;

        for (int i = 0; i < microGridSize; i++) {
            for (int j = 0; j < microGridSize; j++) {
                if (matrix[topLeftRow+i][topLeftColumn+j] == val) {
                    return false;
                }
            }
        }
        return true;
    }

    public SearchNode nextLocationToEdit(SearchNode searchNode){
        if(searchNode.getMatrix() == null || searchNode.getMatrix().length == 0) {
            return searchNode;
        }

        int nextRow = 0;
        int nextCol = 0;
        if(searchNode.getCol() < 8){
            nextRow = searchNode.getRow();
            nextCol = searchNode.getCol() + 1;
        } else if (searchNode.getCol() == 8 && searchNode.getRow() < 8){
            nextRow = searchNode.getRow()+ 1;
            nextCol = 0;
        } else if (searchNode.getRow() == 8 && searchNode.getCol() == 8){
            return searchNode;
        }

        if (searchNode.getMatrix()[nextRow][nextCol] != 0){
            searchNode.setCol(nextCol);
            searchNode.setRow(nextRow);
            return nextLocationToEdit(searchNode);
        }
        searchNode.setCol(nextCol);
        searchNode.setRow(nextRow);
        return searchNode;
    }
}
