package rank;

import java.util.NoSuchElementException;

public class WebExtended extends Web{
    @Override
    protected void addSiteWithName(String name) {
        SiteExtended siteExtended = new SiteExtended(name);
        super.addSite(siteExtended);
    }

    @Override
    protected void distribute(Site site, double prize) {
        try {
            SiteExtended siteExtended = (SiteExtended) site;
            if (siteExtended.isValid()) {
                super.distribute(site, prize);
            }
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    // A lo mejor no coinciden los tipos o no estoy buscando en la lista
    public void switchSiteWithName(String name) {
        try {
            Site site = super.getSite(name);
            SiteExtended siteExtended = (SiteExtended) site;
            if (siteExtended.isValid()) {
                siteExtended.setValid(false);
            } else {
                siteExtended.setValid(true);
            }
        } catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
    }


}
