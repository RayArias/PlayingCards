// Class DeckOfCards for FinalProject
// Program PlayingCards by Ray Arias

class DeckOfCards extends PileOfCards {

  // Default Constructor for Class DeckOfCards
  // Creates a full deck of cards in order with
  // no Jokers
  public DeckOfCards() {
    this(0);
  }

  // Parameterized Constructor for Class DeckOfCards
  // Creates a full deck of cards in order with
  // the number of Jokers at the end of it (0, 1,
  // or 2) given by the int parameter.
  public DeckOfCards(int jokers) {
        
    try {
      this.makeNewDeck(jokers);
    } catch (NumberOutOfRangeException noor) {
      noor.printStackTrace();
    }
  }
 
  // This method is the private method that
  // carries out the creation of a full ordered
  // deck with the number of Jokers indicated
  private void makeNewDeck(int jokers) throws NumberOutOfRangeException {
        
      if ((jokers < 0) || (jokers > 2))
        throw new NumberOutOfRangeException("The value of int jokers (" + jokers
          + ") is out of range! (0, 1, or 2)");
      else {
        
        for (int suit = 0; suit < 4; suit++) {
          for (int rank = 1; rank <= 13; rank++) {
            Card card = new Card(rank, suit);
            this.addCardToBottom(card);
          }
        }
        
        if (jokers >= 1) {
          Card card = new Card(2, 4);
          this.addCardToBottom(card);
        }
        if (jokers == 2) {
          Card card = new Card(1, 4);
          this.addCardToBottom(card);
        }
     }
   }
 }