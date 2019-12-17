package be.hogent.koifarm.koifarm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class KoiSeller extends Person {
    private final ArrayList<Sale> sales;


    public KoiSeller(String name) {
        super(name);
        sales = new ArrayList<>();
    }

    public void addSale(Sale sale) {
        sales.add(sale);
    }

    public List<Sale> getCompletedSales() {
        return sales;
    }

    public List<Koi> getAllSoldKois() {
        List<Koi> soldKois = new ArrayList<>();
        for (Sale sale : sales) {
            soldKois.addAll(sale.getSoldKois());
        }
        return soldKois;
    }

    public List<Sale> getSales() {
        return sales;
    }
}
