package org.example;

import org.jetbrains.annotations.VisibleForTesting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.TreeSet;

public class CookiesClass {

    private final TreeSet<Cookie> a;
    private final int k;

    public CookiesClass(final List<Integer> list, final int k) {
        a = new TreeSet<>();
        for (int i = 0; i < list.size(); i++) {
            a.add(new Cookie(i, list.get(i)));
        }
        this.k = k;
    }

    public int merge() {
        int iteration = 0;
        try {
            while (!finished()) {
                nextIteration(++iteration);
            }
        } catch (final NoSuchElementException e) {
            iteration = -1;
        }
        return iteration;
    }

    public List<Integer> toList() {
        final List<Cookie> cookieList = new ArrayList<>(a);
        cookieList.sort(Comparator.comparingInt(o -> o.ordner));
        return cookieList.stream()
                         .map(Cookie::getSweetness)
                         .toList();
    }

    @VisibleForTesting
    boolean finished() {
        return a.first().getSweetness() >= k;
    }

    @VisibleForTesting
    void nextIteration(final int iteration) {
        final var first = Optional.ofNullable(a.pollFirst()).orElseThrow().getSweetness();
        final var second = Optional.ofNullable(a.pollFirst()).orElseThrow().getSweetness();
        final var mixedSweetness = first + 2 * second;
        a.add(new Cookie(-iteration, mixedSweetness));
    }

    static class Cookie implements Comparable<Cookie> {
        final int ordner;
        final int sweetness;

        Cookie(int ordner, int sweetness) {
            this.ordner = ordner;
            this.sweetness = sweetness;
        }

        int getOrdner() {
            return ordner;
        }

        int getSweetness() {
            return sweetness;
        }

        @Override
        public int compareTo(Cookie o) {
            final int i = Integer.compare(getSweetness(), o.getSweetness());
            return i == 0 ? Integer.compare(getOrdner(), o.getOrdner()) : i;
        }

        @Override
        public int hashCode() {
            return getOrdner() + getSweetness() * 10^6;
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj) ||
                    obj instanceof Cookie c &&
                            c.getOrdner() == getOrdner() && c.getSweetness() == getSweetness();
        }

        @Override
        public String toString() {
            return "Cookie[" + getSweetness() + ", " + getOrdner() + "]";
        }
    }
}
