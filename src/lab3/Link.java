package lab3;

public class Link{
    public String ref;
    // in the future there will be more fields
    public Link(String ref) {
        this.ref=ref;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        return ((Link)obj).ref.equals(this.ref);
    }
    @Override
    public String toString() {
        return this.ref;
    }
}