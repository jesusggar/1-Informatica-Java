package rank;

public class SiteExtended extends Site{
    boolean valid;

    public SiteExtended(String name) {
        super(name);
        valid = true;
    }

    public void setValid(boolean b) {
        valid = b;
    }

    public boolean isValid() {
        return valid;
    }

    @Override
    public String toString() {
        if (valid) {
            return this.getName()+"("+this.getRank()+")";
        }else {
            return this.getName()+"("+this.getRank()+")*";
        }
    }
}
