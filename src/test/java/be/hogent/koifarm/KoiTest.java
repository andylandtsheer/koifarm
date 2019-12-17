package be.hogent.koifarm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import be.hogent.koifarm.koifarm.Koi;
import be.hogent.koifarm.koifarm.KoiBranch;
import be.hogent.koifarm.koifarm.KoiOrigin;

public class KoiTest {
	private Koi matsuba1;
	private Koi matsuba2;
	private Koi chagoi1;

	@BeforeEach
	public void setUp() {
		matsuba1 = new Koi(KoiOrigin.EUROPA, KoiBranch.MATSUBA);
		matsuba2 = new Koi(KoiOrigin.EUROPA, KoiBranch.MATSUBA);

		chagoi1 = new Koi(KoiOrigin.EUROPA, KoiBranch.CHAGOI);

	}

	@Test
	public void testEquals() {
		assertNotEquals(matsuba1, matsuba2, "matsuba1 and matsuba2 shouldn't be equals");

		assertEquals(chagoi1, chagoi1, "chagoi1 and chagoi1 should be equals");
	}

	@Test
	public void testCompareTo() {
		assertEquals(0, chagoi1.compareTo(chagoi1), "matsuba1 and matsuba2 shouldn't be equals");

		assertTrue(chagoi1.compareTo(matsuba1) < 0, "matsuba1 shouldn't be before chagoi1");
	}
}

