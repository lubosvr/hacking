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
        cookieList.sort(Comparator.comparingInt(o -> o.order));
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
        final int order;
        final int sweetness;

        Cookie(int order, int sweetness) {
            this.order = order;
            this.sweetness = sweetness;
        }

        int getOrder() {
            return order;
        }

        int getSweetness() {
            return sweetness;
        }

        @Override
        public int compareTo(Cookie o) {
            final int i = Integer.compare(getSweetness(), o.getSweetness());
            return i == 0 ? Integer.compare(getOrder(), o.getOrder()) : i;
        }

        @Override
        public int hashCode() {
            return getOrder() + getSweetness() * 10^6;
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj) ||
                    obj instanceof Cookie c &&
                            c.getOrder() == getOrder() && c.getSweetness() == getSweetness();
        }

        @Override
        public String toString() {
            return "Cookie[" + getSweetness() + ", " + getOrder() + "]";
        }
    }
}
