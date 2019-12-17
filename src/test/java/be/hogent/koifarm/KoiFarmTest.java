package be.hogent.koifarm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import be.hogent.koifarm.koifarm.Koi;
import be.hogent.koifarm.koifarm.KoiBranch;
import be.hogent.koifarm.koifarm.KoiFarm;
import be.hogent.koifarm.koifarm.KoiKeeper;
import be.hogent.koifarm.koifarm.KoiOrigin;
import be.hogent.koifarm.koifarm.KoiSeller;

public class KoiFarmTest {

	private KoiFarm farm;

	@BeforeEach
	public void setUp() throws Exception {
		farm = new KoiFarm("MyKoiFarm", "Vissenstraat");
	}

	@Test
	public void testSellKoiKoiSellerKoiKeeperint() {

		Koi koi = new Koi(KoiOrigin.CHINA, KoiBranch.MATSUBA);
		KoiSeller seller = new KoiSeller("Pierre");
		farm.buy(koi, new BigDecimal(50));
		farm.addSeller(seller);
		KoiKeeper keeper = new KoiKeeper("Robert");
		farm.addKeeper(keeper);
		BigDecimal price = new BigDecimal(100);

		farm.sell(koi, seller, keeper, price);
		assertEquals(1, seller.getCompletedSales()
				.size(), "Test sell() 01 failed");
		assertEquals(1, keeper.getKois().size(), "Test sell() 02 failed");

		try {
			farm.sell(koi, seller, keeper, price);
			fail("error");
		} catch (NoSuchElementException e) {
			assertEquals(
					"java.util.NoSuchElementException", e.getClass().getName(), "Test sell() 03 failed");
		}
		assertEquals(1, seller.getCompletedSales()
				.size(), "Test sell() 04 failed");
		assertEquals(1, keeper.getKois().size(), "Test sell() 05 failed");

		KoiSeller seller2 = new KoiSeller("Ernest");
		try {
			farm.sell(koi, seller2, keeper, price);
			fail("error");
		} catch (NoSuchElementException e) {
			assertEquals(
					"java.util.NoSuchElementException", e.getClass().getName(), "Test sell() 06 failed");
		}
		assertEquals(0, seller2.getCompletedSales()
				.size(), "Test sell() 07 failed");
		assertEquals(1, keeper.getKois().size(), "Test sell() 08 failed");

		try {
			Koi koi2 = new Koi(KoiOrigin.CHINA, KoiBranch.CHAGOI);
			farm.sell(koi2, seller, keeper, price);
			fail("error");
		} catch (NoSuchElementException e) {
			assertEquals(
					"java.util.NoSuchElementException", e.getClass().getName(), "Test sell() 09 failed");
		}
		assertEquals(1, seller.getCompletedSales()
				.size(), "Test sell() 10 failed");
		assertEquals(1, keeper.getKois().size(), "Test sell() 11 failed");
	}

	@Test
	public void testSellMapKoiSellerKoiKeeper() {

		Koi koi = new Koi(KoiOrigin.CHINA, KoiBranch.MATSUBA);
		farm.buy(koi, new BigDecimal(50));
		KoiSeller seller = new KoiSeller("Pierre");
		farm.addSeller(seller);
		KoiKeeper keeper = new KoiKeeper("Robert");
		farm.addKeeper(keeper);
		Map<Koi, BigDecimal> koisMap = new HashMap<Koi, BigDecimal>();
		koisMap.put(koi, new BigDecimal(50));

		farm.sell(koisMap, seller, keeper);
		assertEquals(1, seller.getCompletedSales()
				.size(), "Test sell() 01 failed");
		assertEquals(1, keeper.getKois().size(), "Test sell() 02 failed");

		Koi koi2 = new Koi(KoiOrigin.EUROPA, KoiBranch.MATSUBA);
		koisMap.put(koi2, new BigDecimal(50));
		try {
			farm.sell(koisMap, seller, keeper);
			fail("error");
		} catch (NoSuchElementException e) {
			assertEquals(
					"java.util.NoSuchElementException", e.getClass().getName(), "Test sell() 03 failed");
		}
		assertEquals(1, seller.getCompletedSales()
				.size(), "Test sell() 04 failed");
		assertEquals(1, keeper.getKois().size(), "Test sell() 05 failed");

		farm.buy(koi2, new BigDecimal(50));
		KoiKeeper keeper2 = new KoiKeeper("Pierre");
		try {
			farm.sell(koisMap, seller, keeper2);
			fail("error");
		} catch (NoSuchElementException e) {
			assertEquals(
					"java.util.NoSuchElementException", e.getClass().getName(), "Test sell() 06 failed");
		}
		assertEquals(1, seller.getCompletedSales()
				.size(), "Test sell() 07 failed");
		assertEquals(0, keeper2.getKois().size(), "Test sell() 08 failed");

		farm.addKeeper(keeper2);
		KoiSeller seller2 = new KoiSeller("Pierre");
		try {
			farm.sell(koisMap, seller2, keeper2);
			fail("error");
		} catch (NoSuchElementException e) {
			assertEquals(
					"java.util.NoSuchElementException", e.getClass().getName(), "Test sell() 00 failed");
		}
		assertEquals(0, seller2.getCompletedSales()
				.size(), "Test sell() 10 failed");
		assertEquals(0, keeper2.getKois().size(), "Test sell() 11 failed");

	}

	@Test
	public void testBuy() {

		Koi koi = new Koi(KoiOrigin.CHINA, KoiBranch.MATSUBA);
		farm.buy(koi, new BigDecimal(50));
		assertEquals(1, farm.getKoiPriceMap().size(), "Test buy() 01 failed");

		try {
			farm.buy(koi, new BigDecimal(50));
			fail("error");
		} catch (Exception e) {
			assertEquals(
					"java.lang.IllegalStateException", e.getClass().getName(), "Test buy() 02 failed");
		}
		assertEquals(1, farm.getKoiPriceMap().size(), "Test buy() 03 failed");
	}

	@Test
	public void testGetKoisSoldBySeller() {

		Koi koi = new Koi(KoiOrigin.CHINA, KoiBranch.MATSUBA);
		Koi koi2 = new Koi(KoiOrigin.EUROPA, KoiBranch.KOHAKU);
		Koi koi3 = new Koi(KoiOrigin.ISRAEL, KoiBranch.CHAGOI);
		farm.buy(koi, new BigDecimal(50));
		farm.buy(koi2, new BigDecimal(100));
		farm.buy(koi3, new BigDecimal(150));
		KoiSeller seller = new KoiSeller("Pierre");
		farm.addSeller(seller);
		KoiKeeper keeper = new KoiKeeper("Albert");
		farm.addKeeper(keeper);
		farm.sell(koi, seller, keeper, new BigDecimal(100));
		farm.sell(koi2, seller, keeper, new BigDecimal(200));
		farm.sell(koi3, seller, keeper, new BigDecimal(300));
		assertEquals(3, farm
				.getKoisSoldBySeller(seller).size(), "Test getKoisSoldBySeller() 01 failed");
	}

	@Test
	public void testGetBoughtPrice() {

		Koi koi = new Koi(KoiOrigin.CHINA, KoiBranch.MATSUBA);
		Koi koi2 = new Koi(KoiOrigin.EUROPA, KoiBranch.KOHAKU);
		farm.buy(koi, new BigDecimal(50));
		farm.buy(koi2, new BigDecimal(100));
		assertEquals(new BigDecimal(50), farm
				.getBoughtPrice(koi), "Test getBoughtPrice() 01 failed");
		assertEquals(new BigDecimal(100), farm
				.getBoughtPrice(koi2), "Test getBoughtPrice() 02 failed");
	}

	@Test
	public void testGetBenefitsDoneBySeller() {

		Koi koi = new Koi(KoiOrigin.CHINA, KoiBranch.MATSUBA);
		Koi koi2 = new Koi(KoiOrigin.EUROPA, KoiBranch.KOHAKU);
		Koi koi3 = new Koi(KoiOrigin.ISRAEL, KoiBranch.CHAGOI);
		farm.buy(koi, new BigDecimal(50));
		farm.buy(koi2, new BigDecimal(100));
		farm.buy(koi3, new BigDecimal(150));
		KoiSeller seller = new KoiSeller("Pierre");
		farm.addSeller(seller);
		KoiKeeper keeper = new KoiKeeper("Albert");
		farm.addKeeper(keeper);
		farm.sell(koi, seller, keeper, new BigDecimal(100));
		farm.sell(koi2, seller, keeper, new BigDecimal(200));
		farm.sell(koi3, seller, keeper, new BigDecimal(300));

		assertEquals(new BigDecimal(300), farm
				.getBenefitsDoneBySeller(seller), "Test getBenefitsDoneBySeller() 01 failed");
	}

	@Test
	public void testAddSeller() {

		KoiSeller seller = new KoiSeller("Pierre");
		assertEquals(0, farm
				.getSellerCollection().size(), "Test addSeller() 01 failed");

		farm.addSeller(seller);
		assertEquals(1, farm
				.getSellerCollection().size(), "Test addSeller() 02 failed");

		farm.addSeller(seller);
		assertEquals(1, farm
				.getSellerCollection().size(), "Test addSeller() 03 failed");
	}

	@Test
	public void testAddKeeper() {

		KoiKeeper keeper = new KoiKeeper("Pierre");
		assertEquals(0, farm
				.getKeeperCollection().size(), "Test addKeeper() 01 failed");

		farm.addKeeper(keeper);
		assertEquals(1, farm
				.getKeeperCollection().size(), "Test addKeeper() 02 failed");

		farm.addKeeper(keeper);
		assertEquals(1, farm
				.getKeeperCollection().size(), "Test addKeeper() 03 failed");
	}
}