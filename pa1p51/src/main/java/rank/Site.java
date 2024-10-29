package rank;

import com.sun.management.UnixOperatingSystemMXBean;

import java.util.Objects;

public class Site implements Comparable<Site>{
    private String name;
    private double rank;

    public Site(String name) {
        this.name = name;
        rank = 0;
    }

    public String getName() {
        return name;
    }

    public double getRank() {
        return rank;
    }

    public void addRank(double r) {
        rank += r;
    }

    @Override
    public boolean equals(Object o) {
        Site site = (Site) o;
        return name.equalsIgnoreCase(site.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }

    public int compareTo(Site st) {
        return this.name.compareToIgnoreCase(st.name);
    }

    // Puede que falte ponerle mas decimales a rank
    @Override
    public String toString() {
        return name + "(" + rank + ")";
    }
}
