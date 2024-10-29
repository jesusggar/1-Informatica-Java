import rank.WebExtended;

public class MainRank2 {
    public static void main(String[] args) {
        String[] enlaces = {"I->C",
                "J->C",
                "A->C",
                "A->D",
                "B->C",
                "B->F",
                "D->F",
                "E->B",
                "E->H",
                "F->G",
                "F->H",
                "G->E",
                "G->H"};

        WebExtended webExtended = new WebExtended();
        for (String arc: enlaces) {
            webExtended.addLink(arc);
        }
        webExtended.switchSiteWithName("A");
        webExtended.switchSiteWithName("I");
        webExtended.switchSiteWithName("J");
        webExtended.simulateClick(4000);
        System.out.println(webExtended);
        System.out.println("Paginas ordenadas alfabeticamente");
        System.out.println(webExtended.getSitesByName());
        System.out.println("Paginas ordenadas por rank");
        System.out.println(webExtended.getSitesByRank());
    }
}
