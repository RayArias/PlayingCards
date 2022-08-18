// FinalProject by Ray Arias
// Program PlayingCards
// Revised version 1.11
// Completed 06 August 2022
// Originally submitted
// 03 August 2022
// Post submission revision
// 14 August 2022

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    
    // Show Title Card
    TextUI.displayln("A card is placed on the table in front");
    TextUI.display("of you. It is ");
    Card cardT = new Card(3, 4); // the Title card
    TextUI.displayln(cardT.toString());
    TextUI.newln();
    
    // Display note on user responses
    TextUI.displayln("Please note that only the first charac-");
    TextUI.displayln("ter of your responses counts as your");
    TextUI.displayln("choice.");
    
    // Open Scanner object
    Scanner input = TextUI.openUI();
    int intChoice = 0;
    char choice = TextUI.chr(intChoice);

    // Ask user's input for Jokers and
    // obtain it
    TextUI.displayln("How many Jokers would you like in your");
    TextUI.displayln("deck? (0, 1, or 2)");
    choice = TextUI.userChoice(TextUI.readln(input));
    intChoice = TextUI.char2int(choice);
    
    // While choice is out of range, ask
    // for and get choice again
    while (intChoice > 2) {
      TextUI.displayln("I do not understand your choice. Please");
      TextUI.displayln("choose again.");
      choice = TextUI.userChoice(TextUI.readln(input));
      intChoice = TextUI.char2int(choice);
     }
    
    // Create new, ordered DeckOfCards
    // object
    TextUI.newln();
    TextUI.display("Making a deck of cards... ");
    DeckOfCards deck = new DeckOfCards(intChoice);
    TextUI.displayln("Done.");
    TextUI.newln();
    
    // Ask user's input on how many
    // times to process DeckOfCards ob-
    // ject and obtain it.
    TextUI.displayln("How many times do you want to process");
    TextUI.displayln("(cut and shuffle) the deck of cards?");
    TextUI.displayln("(0 - 9)");
    choice = TextUI.userChoice(TextUI.readln(input));
    intChoice = TextUI.char2int(choice);
    
    // While choice is out of range, ask
    // for and get choice again.
    while (intChoice > 9) {
      TextUI.displayln("I do not understand your choice. Please");
      TextUI.displayln("choose again.");
      choice = TextUI.userChoice(TextUI.readln(input));
      intChoice = TextUI.char2int(choice);
    }
    int process = intChoice;
    TextUI.newln();
    
    // Ask user for input on techquique
    // and obtain it.
    TextUI.displayln("What method do you want to use to");
    TextUI.displayln("process the cards? [C]utting and uncut-");
    TextUI.displayln("ting only, cutting and shuffling");
    TextUI.displayln("[D]ownward, cutting and shuffling [U]p-");
    TextUI.displayln("ward, or a combination of [A]ll of");
    TextUI.displayln("these techniques? (C, D, U, or A)");
    choice = TextUI.userChoice(TextUI.readln(input));
    
    // While choice is out of range, ask
    // for and get choice again.
    while ((choice != 'C') && (choice != 'D') && (choice != 'U') && (choice != 'A')) {
      TextUI.displayln("I do not understand your choice. Please");
      TextUI.displayln("choose again.");
      choice = TextUI.userChoice(TextUI.readln(input));
    }
    TextUI.closeUI(input);
    char technique = choice;
    TextUI.newln();
    
    // switch according to technique
    // chosen and display appropriate
    // message
    switch (technique) {
      case 'C':
        TextUI.displayln("Cutting and uncutting the deck of cards");
        TextUI.displayln("(one of the cut piles is cut and uncut");
        TextUI.displayln("again before being uncut with the origi-");
        TextUI.displayln("nal pile to achieve more randomness) " + process);
        TextUI.display("times... ");
        break;
      case 'D':
        TextUI.displayln("Cutting the deck of cards and shuffling");
        TextUI.display("it downward " + process + " times... ");
        break;
      case 'U':
        TextUI.displayln("Cutting the deck of cards and shuffling it");
        TextUI.display("upward " + process + " times... ");
        break;
      case 'A':
        TextUI.displayln("Cutting and uncutting the deck of cards,");
        TextUI.displayln("then cutting it again, shuffling it up-");
        TextUI.displayln("ward, then cutting it one more time,");
        TextUI.display("and shuffling it downward " + process + " times...");
        break;
      }
    
    // Create and initialize two
    // Shuffleable objects as
    // PileofCards
    Shuffleable poc1 = new PileOfCards();
    Shuffleable poc2 = new PileOfCards();
    
    // Process deck as many times as
    // requested by user
    for (int i = 0; i < process; i++) {
      
      switch (technique) {
        case 'C':
          // If cutting and uncutting is
          // requested cut deck, cut 1st
          // pile, uncut 1st pile, then
          // uncut deck. This achieves
          // more randomness in the deck.
          poc1 = deck.cut();
          poc2 = poc1.cut();
          poc1.uncut(poc2);
          deck.uncut(poc1);
          break;
        case 'D':
          // If downward shuffling is re-
          // quested, cut the deck and
          // shuffle it downward.
          poc1 = deck.cut();
          deck.shuffleDown(poc1);
          break;
        case 'U':
          // If upward shuffling is re-
          // quested, cut the deck and
          // shuffle it upward.
          poc1 = deck.cut();
          deck.shuffleUp(poc1);
          break;
        case 'A':
          // If a combination of all
          // is requested, cut the deck,
          // shuffle it upward, cut it
          // again, and shuffle it
          // downward.
          poc1 = deck.cut(); 
          // 14 Aug 2022: Inserted extra
          poc2 = poc1.cut();
          poc1.uncut(poc2);
          // cut and uncut here
          deck.uncut(poc1);
          poc1 = deck.cut();
          deck.shuffleUp(poc1);
          poc1 = deck.cut();
          deck.shuffleDown(poc1);
          break;
      }
    }
    
    TextUI.displayln("Done.");
    TextUI.newln();
    
    // Display quantity of deck and show
    // all the cards it holds.
    int q = deck.getQuantity();
    TextUI.displayln("The deck has " + q + " cards, and consists of:");
    deck.show();
    
    TextUI.newln();
    TextUI.displayln("Thank you for using Program");
    TextUI.displayln("PlayingCards!");
    TextUI.newln();
  }
}