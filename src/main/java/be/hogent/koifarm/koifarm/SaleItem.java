package be.hogent.koifarm.koifarm;

import java.math.BigDecimal;

public class SaleItem {
    private final Koi koi;
    private final BigDecimal sellPrice;

    public SaleItem(Koi koi, BigDecimal sellPrice) {
        this.koi = koi;
        this.sellPrice = sellPrice;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public Koi getKoi() {
        return koi;
    }
}
