package pkgPokerBLL;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgPokerEnum.eHandStrength;
import pkgPokerEnum.eRank;
import pkgPokerEnum.eSuit;

public class TestHands {
	
	@Test
	public void TestRoyalFlush() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.TEN,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.JACK,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.QUEEN,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.KING,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.ACE,eSuit.CLUBS));
		h.EvaluateHand();
		
		//Test it's a royal flush
		assertEquals(eHandStrength.RoyalFlush.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		
		//Royal flushes don't have kickers
			assertEquals(0,h.getHandScore().getKickers().size());
	}
	
	@Test
	public void TestStraightFlush() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.SIX,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.SEVEN,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.NINE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.TEN,eSuit.CLUBS));
		h.EvaluateHand();
		
		//Test it's a straight flush
		assertEquals(eHandStrength.StraightFlush.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		
		//High hand(card) if multiple people have a straight flush
		assertEquals(eRank.TEN.getiRankNbr(),h.getHandScore().getHiHand()
				.getiRankNbr());
		
		//Straight flushes don't have kickers
		assertEquals(0,h.getHandScore().getKickers().size());
	}
	
	@Test
	public void TestFourOFAKind() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.NINE,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.NINE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.NINE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.NINE,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.TEN,eSuit.CLUBS));
		h.EvaluateHand();
		
		//Test it's a four of a kind
		assertEquals(eHandStrength.FourOfAKind.getHandStrength(),h.getHandScore()
				.getHandStrength().getHandStrength());
		
		//High hand if multiple people have a four of a kind
		assertEquals(eRank.NINE.getiRankNbr(),h.getHandScore().getHiHand()
				.getiRankNbr());
		
		/*Don't need a low test or kicker because two people can't have four 
		  of a kind with the same card number, so who ever has the higher four
		  of a kind wins*/	
	}
	
	@Test
	public void TestFourOFAKind2() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.CLUBS));
		h.EvaluateHand();
		
		//Test it's a four of a kind
		assertEquals(eHandStrength.FourOfAKind.getHandStrength(),h.getHandScore()
				.getHandStrength().getHandStrength());
		
		//High hand if multiple people have a four of a kind
		assertEquals(eRank.FIVE.getiRankNbr(),h.getHandScore().getHiHand()
				.getiRankNbr());
		
		/*Don't need a low test or kicker because two people can't have four 
		  of a kind with the same card number, so who ever has the higher four
		  of a kind wins*/	
	}
	
	@Test
	public void TestFullHouse() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.THREE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.SPADES));		
		h.EvaluateHand();
		
		//	Hand better be a full house
		assertEquals(eHandStrength.FullHouse.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		
		//	HI hand better be 'Four'
		assertEquals(eRank.FOUR.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
		
		//	LO hand better be 'Three'
		assertEquals(eRank.THREE.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
		
		//	Full House has no kickers.
		assertEquals(0,h.getHandScore().getKickers().size());
	}
	
	@Test
	public void TestFullHouse2() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.THREE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.SPADES));		
		h.EvaluateHand();
		
		//	Hand better be a full house
		assertEquals(eHandStrength.FullHouse.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		
		//	HI hand better be 'three' because first you test the three of a kind
		assertEquals(eRank.THREE.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
		
		//	LO hand better be four
		assertEquals(eRank.FOUR.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
		
		//	Full House has no kickers.
		assertEquals(0,h.getHandScore().getKickers().size());
	}
	
	@Test
	public void TestFlush() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.SIX,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.TEN,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.KING,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.ACE,eSuit.HEARTS));
		h.EvaluateHand();
		
		//Test it's a flush
		assertEquals(eHandStrength.Flush.getHandStrength(),h.getHandScore()
				.getHandStrength().getHandStrength());
		
		//High hand if multiple people have a flush
		assertEquals(eRank.ACE.getiRankNbr(),h.getHandScore().getHiHand()
				.getiRankNbr());
		
		//Kickers are the other 3 cards because you keep testing until different
		assertEquals(eRank.KING,h.getHandScore().getKickers().size());
		assertEquals(eRank.TEN,h.getHandScore().getKickers().size());
		assertEquals(eRank.EIGHT,h.getHandScore().getKickers().size());
		assertEquals(eRank.SIX,h.getHandScore().getKickers().size());
	}
	
	@Test
	public void TestStraight() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.SIX,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.SEVEN,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.SPADES));
		h.EvaluateHand();
		
		//Test it's a straight
		assertEquals(eHandStrength.Straight.getHandStrength(),h.getHandScore()
				.getHandStrength().getHandStrength());
		
		//High hand if multiple people have a straight
		assertEquals(eRank.EIGHT.getiRankNbr(),h.getHandScore().getHiHand()
				.getiRankNbr());
		
		//Straight's don't have kickers
		assertEquals(0,h.getHandScore().getKickers().size());
	}
	
	@Test
	public void TestThreeOfAKind() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.JACK,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.JACK,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.JACK,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.QUEEN,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.KING,eSuit.SPADES));
		h.EvaluateHand();
		
		//Test it's a three of a kind
		assertEquals(eHandStrength.ThreeOfAKind.getHandStrength(),h.getHandScore()
				.getHandStrength().getHandStrength());
		
		//High hand if multiple people have a three of a kind
		assertEquals(eRank.JACK.getiRankNbr(),h.getHandScore().getHiHand()
				.getiRankNbr());
		
		//Other 2 cards are kickers
		assertEquals(eRank.KING,h.getHandScore().getKickers().size());
		assertEquals(eRank.QUEEN,h.getHandScore().getKickers().size());
	}
	
	@Test
	public void TestThreeOfAKind2() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.TWO,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.JACK,eSuit.DIAMONDS));
		h.EvaluateHand();
		
		//Test it's a three of a kind
		assertEquals(eHandStrength.ThreeOfAKind.getHandStrength(),h.getHandScore()
				.getHandStrength().getHandStrength());
		
		//High hand if multiple people have a three of a kind
		assertEquals(eRank.FIVE.getiRankNbr(),h.getHandScore().getHiHand()
				.getiRankNbr());
		
		//Other 2 cards are kickers
		assertEquals(eRank.JACK,h.getHandScore().getKickers().size());
		assertEquals(eRank.TWO,h.getHandScore().getKickers().size());
	}
	
	@Test
	public void TestThreeOfAKind3() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.SIX,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.SEVEN,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.KING,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.KING,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.KING,eSuit.DIAMONDS));
		h.EvaluateHand();
		
		//Test it's a three of a kind
		assertEquals(eHandStrength.ThreeOfAKind.getHandStrength(),h.getHandScore()
				.getHandStrength().getHandStrength());
		
		//High hand if multiple people have a three of a kind
		assertEquals(eRank.KING.getiRankNbr(),h.getHandScore().getHiHand()
				.getiRankNbr());
		
		//Other 2 cards are kickers
		assertEquals(eRank.SEVEN,h.getHandScore().getKickers().size());
		assertEquals(eRank.SIX,h.getHandScore().getKickers().size());
	}
	
	@Test
	public void TestTwoPair() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.TWO,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.TWO,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.JACK,eSuit.CLUBS));
		h.EvaluateHand();
		
		//Test it's two pair
		assertEquals(eHandStrength.TwoPair.getHandStrength(),h.getHandScore()
				.getHandStrength().getHandStrength());
		
		//High hand should be the highest pair
		assertEquals(eRank.FOUR.getiRankNbr(),h.getHandScore().getHiHand()
				.getiRankNbr());
			
		//Low hand is the other pair
		assertEquals(eRank.TWO.getiRankNbr(),h.getHandScore().getLoHand()
				.getiRankNbr());
		
		//Kicker is leftover card
		assertEquals(eRank.JACK,h.getHandScore().getKickers().size());
	}
	
	@Test
	public void TestTwoPair2() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.SIX,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.SIX,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.JACK,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.JACK,eSuit.SPADES));
		h.EvaluateHand();
		
		//Test it's two pair
		assertEquals(eHandStrength.TwoPair.getHandStrength(),h.getHandScore()
				.getHandStrength().getHandStrength());
		
		//High hand should be the highest pair
		assertEquals(eRank.JACK.getiRankNbr(),h.getHandScore().getHiHand()
				.getiRankNbr());
			
		//Low hand is the other pair
		assertEquals(eRank.SIX.getiRankNbr(),h.getHandScore().getLoHand()
				.getiRankNbr());
		
		//Kicker is leftover card
		assertEquals(eRank.EIGHT,h.getHandScore().getKickers().size());
	}
	
	@Test
	public void TestTwoPair3() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.THREE,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.SEVEN,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.SEVEN,eSuit.DIAMONDS));
		h.EvaluateHand();
		
		//Test it's two pair
		assertEquals(eHandStrength.TwoPair.getHandStrength(),h.getHandScore()
				.getHandStrength().getHandStrength());
		
		//High hand should be the highest pair
		assertEquals(eRank.SEVEN.getiRankNbr(),h.getHandScore().getHiHand()
				.getiRankNbr());
			
		//Low hand is the other pair
		assertEquals(eRank.FIVE.getiRankNbr(),h.getHandScore().getLoHand()
				.getiRankNbr());
		
		//Kicker is leftover card
		assertEquals(eRank.THREE,h.getHandScore().getKickers().size());
	}
	
	@Test
	public void TestPair() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.TWO,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.TWO,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.TEN,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.ACE,eSuit.DIAMONDS));
		h.EvaluateHand();
		
		//Test it's a pair
		assertEquals(eHandStrength.Pair.getHandStrength(),h.getHandScore()
				.getHandStrength().getHandStrength());
		
		//High hand should be the pair
		assertEquals(eRank.TWO.getiRankNbr(),h.getHandScore().getHiHand()
				.getiRankNbr());
				
		//Kickers are leftover cards
		assertEquals(eRank.ACE,h.getHandScore().getKickers().size());
		assertEquals(eRank.TEN,h.getHandScore().getKickers().size());
		assertEquals(eRank.FIVE,h.getHandScore().getKickers().size());
	}
	
	@Test
	public void TestPair2() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.TWO,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.SEVEN,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.DIAMONDS));
		h.EvaluateHand();
		
		//Test it's a pair
		assertEquals(eHandStrength.Pair.getHandStrength(),h.getHandScore()
				.getHandStrength().getHandStrength());
		
		//High hand should be the pair
		assertEquals(eRank.THREE.getiRankNbr(),h.getHandScore().getHiHand()
				.getiRankNbr());
				
		//Kickers are leftover cards
		assertEquals(eRank.EIGHT,h.getHandScore().getKickers().size());
		assertEquals(eRank.SEVEN,h.getHandScore().getKickers().size());
		assertEquals(eRank.TWO,h.getHandScore().getKickers().size());
	}
	
	@Test
	public void TestPair3() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.TWO,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.NINE,eSuit.DIAMONDS));
		h.EvaluateHand();
		
		//Test it's a pair
		assertEquals(eHandStrength.Pair.getHandStrength(),h.getHandScore()
				.getHandStrength().getHandStrength());
		
		//High hand should be the pair
		assertEquals(eRank.FOUR.getiRankNbr(),h.getHandScore().getHiHand()
				.getiRankNbr());
				
		//Kickers are leftover cards
		assertEquals(eRank.NINE,h.getHandScore().getKickers().size());
		assertEquals(eRank.THREE,h.getHandScore().getKickers().size());
		assertEquals(eRank.TWO,h.getHandScore().getKickers().size());
	}
	
	@Test
	public void TestPair4() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.TWO,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.DIAMONDS));
		h.EvaluateHand();
		
		//Test it's a pair
		assertEquals(eHandStrength.Pair.getHandStrength(),h.getHandScore()
				.getHandStrength().getHandStrength());
		
		//High hand should be the pair
		assertEquals(eRank.FIVE.getiRankNbr(),h.getHandScore().getHiHand()
				.getiRankNbr());
				
		//Kickers are leftover cards
		assertEquals(eRank.FOUR,h.getHandScore().getKickers().size());
		assertEquals(eRank.THREE,h.getHandScore().getKickers().size());
		assertEquals(eRank.TWO,h.getHandScore().getKickers().size());
	}
	
	@Test
	public void TestHighCard() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.TWO,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.SEVEN,eSuit.DIAMONDS));
		h.EvaluateHand();
		
		//Test if it's a high card
		assertEquals(eHandStrength.HighCard.getHandStrength(),h.getHandScore()
				.getHandStrength().getHandStrength());
		
		//High hand should be the highest card
		assertEquals(eRank.SEVEN.getiRankNbr(),h.getHandScore().getHiHand()
				.getiRankNbr());
				
		//Kickers are leftover cards
		assertEquals(eRank.FIVE,h.getHandScore().getKickers().size());
		assertEquals(eRank.FOUR,h.getHandScore().getKickers().size());
		assertEquals(eRank.THREE,h.getHandScore().getKickers().size());
		assertEquals(eRank.TWO,h.getHandScore().getKickers().size());
	}
}
