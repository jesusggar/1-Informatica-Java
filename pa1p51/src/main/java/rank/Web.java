package rank;


import java.util.*;

public class Web {
    protected Set<Site> sites;
    protected Set<Link> links;
    private double THRESHOLD =  1E-5;
    Random alea;

    public Web() {
        sites = new HashSet<>();
        links = new HashSet<>();
    }

    protected void addSite(Site site) {
        sites.add(site);
    }

    protected void addSiteWithName(String name) {
        Site site = new Site(name);
        addSite(site);
    }

    public void addLink(String dataLink) {
        try {
            String[] separar = dataLink.split("->");
            if (separar.length != 2) {
                throw new IllegalArgumentException();
            }
            addSiteWithName(separar[0]);
            addSiteWithName(separar[1]);
            Link link = new Link(separar[0], separar[1]);
            links.add(link);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    // El mensaje del error
    public Site getSite(String name) {
        for (Site site : sites) {
            if (site.getName().equalsIgnoreCase(name)) {
                return site;
            }
        }
        throw new NoSuchElementException("No existe la página");
    }

    public Set<String> getNames() {
        Set<String> names = new HashSet<>();
        for (Site site: sites) {
            names.add(site.getName());
        }
        return names;
    }

    private Set<Site> getSitesLinkedFrom(Site pagina) {
        Set<Site> enlazadas = new HashSet<>();
        for (Link link: links) {
            if (link.getOrigin().equalsIgnoreCase(pagina.getName())) {
                Site site = getSite(link.getLinked());
                enlazadas.add(site);
            }
        }
        return enlazadas;
    }

    protected void distribute(Site site, double prize) {
        if (prize >= THRESHOLD) {
            double prizeHalf = prize / 2;
            site.addRank(prizeHalf);
            Set<Site> linkedSites = getSitesLinkedFrom(site);
            int numLinks = linkedSites.size();
            if (numLinks > 0) {
                double prizeDistribution = prize/(2 * numLinks);
                for (Site linkedSite: linkedSites) {
                    distribute(linkedSite, prizeDistribution);
                }
            }
        }
    }

    // Puede no encontrar el site
    public void click(String name) {
        try {
            Site site = getSite(name);
            distribute(site, 1);
        } catch (NoSuchElementException e){
        }
    }

    public void simulateClick(int numClick) {
        if (!sites.isEmpty()) {
            List<Site> siteList = new ArrayList<>(sites);
            alea = new Random(1);
            for (int i = 0; i < numClick; ++i) {
                click(siteList.get(alea.nextInt(siteList.size())).getName());
            }
        }
    }

    public SortedSet<Site> getSitesByName() {
        return new TreeSet<>(sites);
    }

    public SortedSet<Site> getSitesByRank() {
        SortedSet<Site> sortedSites = new TreeSet<>(new Comparator<Site>() {
            @Override
            public int compare(Site o1, Site o2) {
                int rankComp = Double.compare(o2.getRank(), o1.getRank());
                if (rankComp != 0) {
                    return rankComp;
                }
                return o1.compareTo(o2);
            }
        });
        sortedSites.addAll(sites);
        return sortedSites;
    }

    @Override
    public String toString() {
        return "Web("+sites+", "+links+")";
    }
}
