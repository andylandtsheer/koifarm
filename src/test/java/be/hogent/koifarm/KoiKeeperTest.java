package be.hogent.koifarm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import be.hogent.koifarm.koifarm.Koi;
import be.hogent.koifarm.koifarm.KoiBranch;
import be.hogent.koifarm.koifarm.KoiKeeper;
import be.hogent.koifarm.koifarm.KoiOrigin;

public class KoiKeeperTest {

	private KoiKeeper keeper;

	@BeforeEach
	public void setUp() throws Exception {
		keeper = new KoiKeeper("Pierre");
	}

	@Test
	public void testAddKoiKoi() {

		Koi koi1 = new Koi(KoiOrigin.CHINA, KoiBranch.MATSUBA);

		assertEquals(0, keeper.getKois().size(), "Test addKoi() 01 failed");

		keeper.addKoi(koi1);
		assertEquals(1, keeper.getKois().size(), "Test addKoi() 02 failed");

		keeper.addKoi(koi1);
		assertEquals(1, keeper.getKois().size(), "Test addKoi() 03 failed");
	}

	@Test
	public void testAddKoiCollection() {

		Koi koi1 = new Koi(KoiOrigin.CHINA, KoiBranch.MATSUBA);
		Koi koi2 = new Koi(KoiOrigin.CHINA, KoiBranch.MATSUBA);
		Koi koi3 = new Koi(KoiOrigin.EUROPA, KoiBranch.CHAGOI);

		Collection<Koi> c = new ArrayList<Koi>();
		c.add(koi1);
		c.add(koi2);
		c.add(koi3);

		keeper.addKoi(koi1);
		assertEquals(1, keeper.getKois().size(), "Test addKoi() 01 failed");

		keeper.addKois(c);
		assertEquals(3, keeper.getKois().size(), "Test addKoi() 02 failed");
	}

	@Test
	public void testGetKoisSortedByOrigin() {

		Koi koi1 = new Koi(KoiOrigin.CHINA, KoiBranch.MATSUBA);
		Koi koi2 = new Koi(KoiOrigin.EUROPA, KoiBranch.CHAGOI);
		Koi koi3 = new Koi(KoiOrigin.ISRAEL, KoiBranch.KOHAKU);
		Koi koi4 = new Koi(KoiOrigin.JAPAN, KoiBranch.TANSHO_SANKE);
		Koi koi5 = new Koi(KoiOrigin.EUROPA, KoiBranch.KOHAKU);
		Koi koi6 = new Koi(KoiOrigin.EUROPA, KoiBranch.KOHAKU);
		keeper.addKoi(koi1);
		keeper.addKoi(koi2);
		keeper.addKoi(koi3);
		keeper.addKoi(koi4);
		keeper.addKoi(koi5);
		keeper.addKoi(koi6);

	}

}


