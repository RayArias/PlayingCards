// Class TextUI for FinalProject
// Program PlayingCards by Ray Arias

import java.util.Scanner;

/* This is a class of static methods for
 * obtaining user input, displaying out-
 * put, and converting one datatype to
 * another.
 */
public class TextUI {
  
  // This method creates and initializes
  // a Scanner object in from
  // java.init.Scanner and returns its
  // reference.
  public static Scanner openUI() {
    Scanner result = new Scanner(System.in);
    return result;
  }
  
  // This method reads a String of input
  // from the user using the Scanner object
  // and returns it.
  public static String readln(Scanner scanner) {
    String result = scanner.nextLine();
    return result;
  }
  
  // This method closes the Scanner
  // object.
  public static void closeUI(Scanner scanner) {
    scanner.close();
  }
  
  // This method uses the above methods
  // to quickly create an instance of
  // Scanner class, use it to read a
  // line of user input, close the Scan-
  // ner instance, and return the input
  // as a String.
  public static String quickReadln() {
    Scanner scanner = TextUI.openUI();
    String result = TextUI.readln(scanner);
    TextUI.closeUI(scanner);
    return result;
  }
  
  // This method returns the numerical
  // value of a char as an int.
  public static int char2int(char c) {
    return Character.getNumericValue(c);
  }
  
  // This method returns the ANSI char
  // associated with the int given as a
  // parameter.
  public static char chr(int i) {
    return (char) i;
  }
  
  // Displays char (no newline, unless)
  // c is '\n'.
  public static void chr(char c) {
  System.out.print(c);
  }	
 
// Stub for future development:
// method for displaying control
// codes by passing only the
// letter associated with them
// without the backward slash
// before it.
/*  public static void ctlchr(char c) {
    System.out.print('\n' + c);
} */
  
  // Display newline
  public static void newln() {
    System.out.println();
  }
  
  // Display String specified in
  // parameter without going to a
  // new line at the end.
  public static void display(String text) {
    System.out.print(text);
  }

  // Display String specified in
  // patameter and then go to a
  // new line afterward.
  public static void displayln(String text) {
    System.out.println(text);
  }
  
  // This method displays an int and a
  // String in the format expressed in
  // the first String.
  public static void dispf(String format, int n, String description) {
    System.out.printf(format, n, description);
  }
  
  // Extract the user's choice as the
  // first character in the String typed
  // in by the user.
  public static char userChoice(String inputString) {
    char result = inputString.toUpperCase().charAt(0);
    return result;
  }

}

