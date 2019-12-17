package be.hogent.koifarm.koifarm;

import java.math.BigDecimal;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Sale {
    private final Set<SaleItem> saleItems;

    public Sale() {
        this.saleItems = new HashSet<>();
    }

    public Collection<SaleItem> getTransactions() {
        return saleItems;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (SaleItem saleItem : saleItems) {
            totalPrice = totalPrice.add(saleItem.getSellPrice());
        }
        return totalPrice;
    }

    public Collection<Koi> getSoldKois() {
        return saleItems.stream()
                .map(SaleItem::getKoi)
                .collect(Collectors.toList());
    }

    public void addSaleItems(Map<Koi, BigDecimal> koiMap) {
        for (Entry<Koi,BigDecimal> entry: koiMap.entrySet()) {
            saleItems.add(new SaleItem(entry.getKey(),entry.getValue()));
        }
    }

    public void addSaleItem(Koi koi, BigDecimal price) {
        saleItems.add(new SaleItem(koi,price));
    }

    public void addSaleItem(SaleItem saleItem) {
        saleItems.add(saleItem);
    }
}
