// Class Shuffleable for FinalProject
// Program PlayingCards by Ray Arias

import java.util.LinkedList; // This class is used to create a doubly linked
                             // instance consisting of Card objects
import java.util.OptionalInt;      // These classes are used in the
import java.util.Random;           // private static int getRandomInt(boolean,
import java.util.stream.IntStream; // int, int) method below.
                            

public abstract class Shuffleable {
  
  // Variables for Class Shuffleable and its subclasses
  protected int quantity;
  protected LinkedList<Card> pile;

  // Default Constructor for Class Shuffleable
  public Shuffleable() {
    this.quantity = 0;
    this.pile = new LinkedList<Card>();
  }
  
  // Deep Copy Constructor for Class Shuffleable
  public Shuffleable(Shuffleable copy) {
    try { this.deepClone(copy);
    } catch (NumberOutOfRangeException noor) {
      noor.printStackTrace();
    }
  }
  
  // This method creates a deep clone of a Shuffleable object
  protected void deepClone(Shuffleable original) throws NumberOutOfRangeException {
    
    int q = original.quantity;
    this.dispose();
    if (q < 0) throw new NumberOutOfRangeException("Quantity of cards (" + q
      + ") in original deck is negative!");
    else if (q == 0) this.quantity = 0;
    else {
      for (int i = 0; i < q; i++) {
      this.addCardToBottom(original.pile.get(i));
      }
    }
  }

  // Returns quantity of cards held in
  // current Shuffleable instance.
  public int getQuantity() {
    return this.quantity;
  }

  // Updates this.quantity by checking
  // it against this.pile.size() and
  // fixing it and returning true if an
  // update is needed, otherwise false
  // is returned.
  public boolean updateQuantity() {
    boolean updNeeded = (this.quantity != this.pile.size());
    if (updNeeded) this.quantity = this.pile.size();
    return updNeeded;
  }

  // Adds a new Card, given by card
  // onto the top of pile of the current
  // instance of Shuffleable and incre-
  // ments its quantity.
  public void addCardToTop(Card card) {
    this.pile.push(card);
    this.quantity++;
  }

  // Adds a new Card, given by card
  // onto the bottom of pile of the cur-
  // current instance of Shuffleable and
  // increments its quantity.
  public void addCardToBottom(Card card) {
    this.pile.offer(card);
    this.quantity++;
  }

  // Removes a Card from the top of
  // this.pile decrements this.quantity
  // and returns the Card.
  public Card takeTopCard() throws TakingFromEmptyPileException {
    
    int q = this.quantity;
    if (q <= 0) throw new TakingFromEmptyPileException
      ("A card cannot be taken from an empty pile!");
    else { // q > 0
      
      this.quantity = --q;
      return this.pile.pop();  
    }
  }

  // Removes a Card from the bottom of
  // this.pile decrements this.quantity
  // and returns the Card.
  public Card takeBottomCard() throws TakingFromEmptyPileException {
    
    Card card = new Card(0, 4); // Blank card being returned is
                                // secondary indicator that an
                                // Exception condition exists
    int q = this.quantity;
    if (q <= 0) throw new TakingFromEmptyPileException
      ("A card cannot be taken from an empty pile!");
    else { // q > 0
      this.quantity = --q;
      card = this.pile.get(q);
      this.pile.remove(q);
    }
    return card;
  }

  // Removes a Card from the nth posi-
  // tion (given by the parameter int
  // n) of this.pile, decrements
  // this.quantity and returns the
  // Card.
  public Card takeNthCard(int n) throws TakingFromEmptyPileException, ReachingBeyondBottomOfPileException {
    
    Card card = new Card(0, 4); // Blank card being returned is
                                // secondary indicator that an
                                // Exception condition exists
    int q = this.quantity;
    int m = n - 1;
    if ((q > 0) && (m < q)) {
      card = this.pile.get(m);
      this.pile.remove(m);
      this.quantity = --q;
    } else {
      if (q <= 0) throw new TakingFromEmptyPileException
        ("A card cannot be taken from an empty pile!");
      if (m >= q) throw new ReachingBeyondBottomOfPileException
        ("The ordinal number of the card exceeds number of cards in the pile!");
    }
    return card;
  }
  
  // Sets this.quantity to 0 and clears
  // this.pile.
  public void dispose() {
    this.quantity = 0;
    this.pile.clear();
  }
  
  // Uses int
  // Shuffleable.getRandomInt(int, int)
  // to get a obtain a random int and
  // between 1 and int maximum inclu-
  // sive.
  public static int getRandomInt(int maximum) {
    int result = Shuffleable.getRandomInt(1, maximum);
    return result;
  }
  
  // Uses private method int
  // Shuffleable.getRandomInt(boolean,
  // int, int) to obtain a random int
  // between int minimum and int
  // maximum inclusive.
  public static int getRandomInt(int minimum, int maximum) {
    boolean throwAway = true; // Create boolean to call private form of method
                              // getRandomInt(boolean, int, int)
    int result = -1; // Create int to return; result >= 0 indicates no Exception,
                     // result == -1 is a seconary indication of an Exception
    try {
      result = Shuffleable.getRandomInt(throwAway, minimum, maximum);
    } catch (NumberOutOfRangeException noor) {
      noor.printStackTrace();
    }
    return result;
  }
  
  // Uses java.util classes Random and
  // OptionalInt, plus java.util.stream
  // class IntStream to obtain a random
  // int between int minimum and int
  // maximum inclusive. There is a bool-
  // ean throwaway parameter so that a
  // new private method could be coded
  // that took in the same parameters as
  // one the public ones and the previ-
  // ous method could be overloaded and
  // not coded with a different name.
  private static int getRandomInt(boolean throwAway, int minimum, int maximum)
    throws NumberOutOfRangeException {
    final long one = 1L;
    final int max = maximum + 1;
    
    if (minimum < 0)
      throw new NumberOutOfRangeException("Value given for int minimum ("
        + minimum + ") is less than zero!");
    else if (maximum < 0)
      throw new NumberOutOfRangeException("Value given for int maximum ("
        + maximum + ") is less than zero!");
    else if (minimum > maximum)
      throw new NumberOutOfRangeException("Value given for int minimum ("
        + minimum + ") is more than value given for int maximum (" + maximum + ")!");
    
    else { // ((mimimum >= 0) && (maximum >= 0) && (minimum <= maximum))
    
      Random rand = new Random(); // Create new Random object from java.util.Random
      IntStream is = rand.ints(one, minimum, max); // Get IntStream from
                                                   // java.util.stream.IntStream and
                                                   // Random instance's
                                                   // ints(long, int, int) method
      OptionalInt oi = is.findFirst(); // Get OptionalInt object from IntStream via
                                       // IntStream instance's findFirst() method
      int result = oi.getAsInt(); // Get int from OptionalInt using its getAsInt
                                  // method
      return result;
    }
  }

  
  // These methods are not implemented
  // here and are implemented in Class
  // PileOfCards
  
  public abstract Shuffleable cut();
  
  public abstract Shuffleable cut(int location) throws NumberOutOfRangeException;
  
  public abstract void uncut(Shuffleable secondPile);

  public abstract void shuffleDown(Shuffleable secondPile);
  
  public abstract void shuffleUp(Shuffleable secondPile);
  
  public abstract void show();

}