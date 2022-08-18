// Class Card for Program PlayingCards
// FinalProject by Ray Arias

/* Cards are set by (rank, suit) int
 * pairs where rank is between 1 and 13
 * inclusive (1 is the Ace, 11 is the
 * Jack, 12 is the Queen, 13 is the
 * King, and 2 through 10 are their
 * respective number ranks) and suit is
 * one of 0 for Spades, 1 for Hearts, 2
 * for Clubs, or 3 for Diamonds. The on-
 * ly exceptions to these rules are the
 * pairs (0, 4) for a blank card, (1, 4)
 * for the Minor Joker, (2, 4) for the
 * Major Joker, and (3, 4) for the Title
 * card. Any other int pairs are inva-
 * lid, will cause a BadRankException, a
 * BadSuitException, or a
 * BadSuitlessException in the case of a
 * card with suit 4, but rank that is
 * not one of 0, 1, 2, or 3, to be
 * thrown, and if an instance of Card
 * with such an int pair calls its
 * toString() method, it will return
 * "an erroneous card".
 */

public class Card {
  
  private int rank;
  private int suit;

  
  // Parameterized Constructor that
  // uses void setCard(int, int)
  // method below to encode a new
  // instance of a Card object.
  public Card(int rank, int suit) {
    this.setCard(rank, suit);
  }

  
  // This static method makes sure a
  // card's parameters (int rank and
  // int suit) are valid. If they are
  // not, one of three Exceptions (a
  // BadRankException, a
  // BadSuitException, or a
  // BadSuitlessException) is thrown.
  private static boolean checkCardParams(int rank, int suit) throws BadRankException, BadSuitException, BadSuitlessException {
    boolean error = false;
    if ((suit == 4) && ((rank < 0) || (rank > 3))) {
      error = true;
      throw new BadSuitlessException("Value for int rank (" + rank
        + ") is out of range for a suitless card! (0, 1, 2, or 3)");
    } else if ((suit != 4) && ((rank < 1) || (rank > 13))) {
      error = true;
      throw new BadRankException("Value for int rank (" + rank
        + ") is out of range! (1 to 13)");
    } else if ((suit < 0) || (suit > 4)) {
      error = true;
      throw new BadSuitException("Value for int suit (" + suit
        + ") is out of range! (0, 1, 2, 3, or 4)");
    } else error = false;
    return error;
  }

  
  // This method is used to encode a
  // Card object's rank and suit
  // according to the specifications
  // at the beginning of this class.
  public void setCard(int rank, int suit) {
    boolean error = false;
    try {
      error = Card.checkCardParams(rank, suit);
    } catch (BadRankException r) {
      r.printStackTrace();
    } catch (BadSuitException s) {
      s.printStackTrace();
    } catch (BadSuitlessException t) {
      t.printStackTrace();
    } finally {
      if (!error) {
        this.rank = rank;
        this.suit = suit;
      }
    }
  }

  
  // This method returns a Card object's
  // suit as a string. It also returns
  // "Suitless" for a Joker, a blank
  // card, or the title card, and it
  // returns "Error" if the suit of the
  // instance exceeds the suit
  // specifications given at the begin-
  // ning of this class.
  public String getSuit() {
    String result; 
    switch (this.suit) {
      case 0:
        result = "Spades";
        break;
      case 1:
        result = "Hearts";
        break;
      case 2:
        result = "Clubs";
        break;
      case 3:
        result = "Diamonds";
        break;
      case 4:
        result = "Suitless";
        break;
      default:
        result = "Error";
        break;
    }
    return result;
  }

  
  // This method returns a Card object's
  // rank as a String. The boolean
  // parameter suitless is required
  // because a suitless card is evalu-
  // ated different from one with a tra-
  // ditional suit. Value 1 for int
  // this.rank returns "Ace", 11 returns
  // "Jack", 12 returns "Queen", 13
  // returns "King", any value 2 to 10
  // returns the String equivalent of
  // this value ("2" for 2, etc.), and
  // any other returns "Error".
  public String getRank(boolean suitless) {
    String result;
    if (suitless) {
      if (this.rank == 0) result = "Blank";
      else if (this.rank == 1) result = "joker";
      else if (this.rank == 2) result = "Joker";
      else if (this.rank == 3) result = "Title";
      else result = "Error";
    }
    else if ((this.rank < 0) || (this.rank > 13)) result = "Error";
    else {
      switch (this.rank) {
        case 1:
          result = "Ace";
          break;
        case 11:
          result = "Jack";
          break;
        case 12:
          result = "Queen";
          break;
        case 13:
          result = "King";
          break;
        default:
          result = String.valueOf(this.rank);
          break;
      }
    }
    return result;
  }

  // This method uses the String
  // getRank(boolean) and String
  // getSuit() methods to convert the
  // information stored in the Card ob-
  // ject into a String readable to the
  // user.
  @Override
  public String toString() {
    boolean suitless = (this.suit == 4);
    String rankString = this.getRank(suitless);
    String suitString = this.getSuit();
    String result;
    if ((rankString == "Error") || (suitString == "Error")) result = "an erroneous card";
    else if ((rankString == "Blank") && (suitless)) result = "a blank card";
    else if ((rankString == "joker") && (suitless)) result = "the Minor Joker";
    else if ((rankString == "Joker") && (suitless)) result = "the Major Joker";
    else if ((rankString == "Title") && (suitless)) result = "the Title Card that says:\n"
        + "    Welcome to PlayingCards by Ray\n"
        + "    Arias! Designed & Coded July &\n"
        + "    August 2022. Turned in as Final\n"
        + "    Project for RevUp on 03 & 06\n"
        + "    August 2022. Post-submission re-\n"
        + "    vision 14 August 2022. Revised\n"
        + "    version 1.11";
    else result = "the " + rankString + " of " + suitString;
    return result;
  }
}