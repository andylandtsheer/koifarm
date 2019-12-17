package be.hogent.koifarm;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import be.hogent.koifarm.koifarm.Koi;
import be.hogent.koifarm.koifarm.KoiBranch;
import be.hogent.koifarm.koifarm.KoiOrigin;
import be.hogent.koifarm.koifarm.Sale;
import be.hogent.koifarm.koifarm.SaleItem;

public class SaleTest {
	private Sale sale;
	private SaleItem item1, item2, item3, item4;

	@BeforeEach
	public void setUp() {

		sale = new Sale();

		Koi koi1 = new Koi(KoiOrigin.CHINA, KoiBranch.MATSUBA);
		Koi koi2 = new Koi(KoiOrigin.EUROPA, KoiBranch.CHAGOI);
		Koi koi3 = new Koi(KoiOrigin.ISRAEL, KoiBranch.KOHAKU);
		Koi koi4 = new Koi(KoiOrigin.JAPAN, KoiBranch.TANSHO_SANKE);

		item1 = new SaleItem(koi1, new BigDecimal(100));
		item2 = new SaleItem(koi2, new BigDecimal(100));
		item3 = new SaleItem(koi3, new BigDecimal(100));
		item4 = new SaleItem(koi4, new BigDecimal(100));

	}

	@Test
	public void testAddSaleItem() {

		sale.addSaleItem(item1);
		assertEquals(1, sale.getTransactions().size(), "Test addSaleItem() 01 failed");
		sale.addSaleItem(item1);
		assertEquals(1, sale.getTransactions().size(), "Test addSaleItem() 01 failed");
	}

	@Test
	public void testGetTotalPrice() {

		sale.addSaleItem(item1);
		sale.addSaleItem(item2);
		sale.addSaleItem(item3);
		sale.addSaleItem(item4);

		assertEquals(new BigDecimal(400), sale.getTotalPrice(), "Test getTotalPrice() 01 failed");
	}

	@Test
	public void testGetSoldKois() {

		sale.addSaleItem(item4);
		sale.addSaleItem(item3);
		sale.addSaleItem(item2);
		sale.addSaleItem(item1);

		assertEquals(4, sale.getSoldKois().size(), "Test getSoldKois() 02 failed");
	}

}