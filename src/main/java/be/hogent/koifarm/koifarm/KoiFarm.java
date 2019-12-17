package be.hogent.koifarm.koifarm;

import java.math.BigDecimal;
import java.util.*;

public class KoiFarm {
    private final String name;
    private final String address;

    private final HashSet<KoiSeller> sellerCollection = new HashSet<>();
    private final HashSet<KoiKeeper> keeperCollection = new HashSet<>();
    private final HashSet<Koi> koiCollection = new HashSet<>();
    private final HashMap<Koi, BigDecimal> koiPriceMap = new HashMap<>();


    public KoiFarm(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void buy(Koi koi, BigDecimal price) {
        if (koiPriceMap.containsKey(koi)) {
            throw new IllegalStateException();
        }
        koiPriceMap.put(koi, price);
        koiCollection.add(koi);
    }

    public void addSeller(KoiSeller seller) {
        sellerCollection.add(seller);
    }

    public void addKeeper(KoiKeeper keeper) {
        keeperCollection.add(keeper);
    }

    public void sell(Koi koi, KoiSeller seller, KoiKeeper keeper, BigDecimal price) {
        HashMap<Koi, BigDecimal> koisMap = new HashMap<>();
        koisMap.put(koi, price);
        sell(koisMap, seller, keeper);
    }

    public void sell(Map<Koi, BigDecimal> koisMap, KoiSeller seller, KoiKeeper keeper) {
        if ( !(sellerCollection.contains(seller)) || !(koiCollection.containsAll(koisMap.keySet())) || !(keeperCollection.contains(keeper))) {
            throw new NoSuchElementException();
        }

        keeper.addKois(koisMap.keySet());
        Sale sale = new Sale();
        sale.addSaleItems(koisMap);
        seller.addSale(sale);
        koiCollection.removeAll(koisMap.keySet());
    }

    public Map<Koi, BigDecimal> getKoiPriceMap() {
        return koiPriceMap;
    }

    public List<Koi> getKoisSoldBySeller(KoiSeller seller) {
        return seller.getAllSoldKois();
    }

    public BigDecimal getBoughtPrice(Koi koi) {
        return koiPriceMap.get(koi);
    }

    public BigDecimal getBenefitsDoneBySeller(KoiSeller seller) {

        List<SaleItem> saleItems = getSaleItems(seller.getSales());

        return getBenefitsFrom(saleItems);
    }

    private BigDecimal getBenefitsFrom(List<SaleItem> saleItems) {
        BigDecimal benefits = new BigDecimal(0);

        for (SaleItem saleItem: saleItems) {
            benefits = benefits.add(getDifferenceBetweenSellPriceAndBuyPrice(saleItem));
        }

        return benefits;
    }

    private List<SaleItem> getSaleItems(List<Sale> sales) {
        List<SaleItem> saleItems = new ArrayList<>();

        for (Sale sale : sales) {
            saleItems.addAll(sale.getTransactions());
        }

        return saleItems;
    }

    private BigDecimal getDifferenceBetweenSellPriceAndBuyPrice(SaleItem saleItem) {
        return saleItem.getSellPrice().subtract(koiPriceMap.get(saleItem.getKoi()));
    }

    public Collection<KoiSeller> getSellerCollection() {
        return sellerCollection;
    }

    public Collection<KoiKeeper> getKeeperCollection() {
        return keeperCollection;
    }
}
