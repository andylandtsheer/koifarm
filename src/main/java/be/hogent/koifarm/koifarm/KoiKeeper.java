package be.hogent.koifarm.koifarm;

import java.util.*;

public class KoiKeeper extends Person{
    private HashSet<Koi> kois = new HashSet<>();

    public KoiKeeper(String name) {
        super(name);
    }

    public Set<Koi> getKois() {
        return kois;
    }

    public void addKoi(Koi koi) {
        kois.add(koi);
    }

    public void addKois(Collection<Koi> koisToAdd) {
       kois.addAll(koisToAdd);
    }

    public SortedSet<Koi> getKoisSortedByOrigin(){
        return Collections.unmodifiableSortedSet(new TreeSet<>(kois));
    }
}
