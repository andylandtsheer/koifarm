package be.hogent.koifarm.koifarm;

import java.util.Objects;
import java.util.UUID;

public class Koi implements Comparable<Koi>{
    private final UUID id;
    private final KoiOrigin koiOrigin;
    private final KoiBranch koiBranch;


    public Koi(KoiOrigin koiOrigin, KoiBranch koiBranch) {
        this.koiOrigin = koiOrigin;
        this.koiBranch = koiBranch;
        this.id = UUID.randomUUID();
    }

    @Override
    public int compareTo(Koi koi) {
        if (this.koiOrigin.equals(koi.koiOrigin)){
            return this.koiBranch.compareTo(koi.koiBranch);
        }
        return this.koiOrigin.compareTo(koi.koiOrigin);
    }

    @Override
    public boolean equals(Object koi1) {
        if (this == koi1) return true;
        if (koi1 == null || getClass() != koi1.getClass()) return false;
        Koi koi = (Koi) koi1;
        return Objects.equals(id, koi.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
