import java.util.random;
import java.util.scanner;
import java.util.arraylist;
import java.io.filenotfoundexception;
import java.io.file;

public class WordSearch{
    private char[][]data;
    private int seed;
    private Random randgen;
    private ArrayList<String>wordsToAdd;
    private ArrayList<String>wordsAdded;

    public WordSearch(int rows,int cols){
      data = new char[rows][cols];
      for (int i = 0; i < rows; i ++){
        for (int x = 0; x < cols; x ++){
          data[i][x] = '_';
        }
      }
    }

    /**Set all values in the WordSearch to underscores'_'*/
    private void clear(){
      for (int i = 0; i < data.length; i ++){
        for (int x = 0; x < data[i].length; x ++){
          data[i][x] = '_';
         }
      }
    }

    /**Each row is a new line, there is a space between each letter
     *@return a String with each character separated by spaces, and rows
     *separated by newlines.
     */
    public String toString(){
      String y = "";
      for (int i = 0; i < data.length; i ++){
        for (int x = 0; x < data[i].length; x ++){
          y += data[i][x] + " ";
        }
        y += "\n";
      }
      return y;
    }


    /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from left to right, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     * or there are overlapping letters that do not match, then false is returned

     * and the board is NOT modified.

     */
    public boolean addWordHorizontal(String word,int row, int col){
      if (word.length() + col > data[0].length){
        return false;
      }
      for (int i = 0; i < word.length(); i ++){
        if (Character.isLetter(data[row][col + i]) && (! (data[row][col + i] == word.charAt(i)))){
          return false;
        }
      }
      for (int i = 0; i < word.length(); i ++){
        data[row][col + i] = word.charAt(i);
      }
      return true;
    }


   /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from top to bottom, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     *or there are overlapping letters that do not match, then false is returned.
     *and the board is NOT modified.

     */
    public boolean addWordVertical(String word,int row, int col){
      if (word.length() + row > data.length){
        return false;
      }
      for (int i = 0; i < word.length(); i ++){
        if (Character.isLetter(data[row + i][col]) && (! (data[row + i][col] == word.charAt(i)))){
          return false;
        }
      }
      for (int i = 0; i < word.length(); i ++){
        data[row + i][col] = word.charAt(i);
      }
      return true;
    }
    public boolean addWordDiagonal(String word, int row, int col){
      if (word.length() + row > data.length || word.length() + col > data[0].length){
        return false;
      }
      for (int i = 0; i < word.length(); i ++){
        if (Character.isLetter(data[row + i][col + i]) && (! (data[row + i][col + i] == word.charAt(i)))){
          return false;
        }
      }
      for (int i = 0; i < word.length(); i ++){
        data[row + i][col + i] = word.charAt(i);
      }
      return true;
    }
  private boolean addWord(String word, int r, int c, int rowIncrement, int colIncrement){
    if (c + word.length() * colIncrement > data[0].length || c + word.length() * colIncrement < 0 && r + word.length() * rowIncrement > data.length || r + word.length() * rowIncrement < 0){
      return false;
    }
    if (rowIncrement == 0 && colIncrement == 0){
      return false;
    }
    for (int i = 0; i < word.length(); i ++){
      if (Character.isLetter(data[r + rowIncrement * i][c + colIncrement * i]) && (! (data[r + rowIncrement * i][c + colIncrement * i] == word.charAt(i)))){
        return false;
      }
    }
    for (int i = 0; i < word.length(); i ++){
      data[r + rowIncrement * i][c + colIncrement * i] = word.charAt(i);
    }
    return true;
  }
  public String toString(){
    String newstr = "";
    for (int i = 0; i < data.length; i ++){
      newstr += "|";
      for (int x = 0; x < data[i].length; x ++){
        newstr += data[i][x];
      }
      newstr += "|\n";
    }
    newstr += "Words: " +
    return newstr
  }
}
