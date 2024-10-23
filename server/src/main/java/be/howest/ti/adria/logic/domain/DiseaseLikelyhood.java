package be.howest.ti.adria.logic.domain;

public class DiseaseLikelyhood implements Comparable<DiseaseLikelyhood>{
    private int likelyhood;
    private String illness;

    public DiseaseLikelyhood(int likelyhood, String illness) {
        this.likelyhood = likelyhood;
        this.illness = illness;
    }

    public int getLikelyhood() {
        return likelyhood;
    }

    public String getIllness() {
        return illness;
    }

    @Override
    public int compareTo(DiseaseLikelyhood other) {
        return Integer.compare(other.likelyhood, this.likelyhood);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + likelyhood;
        result = prime * result + ((illness == null) ? 0 : illness.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DiseaseLikelyhood other = (DiseaseLikelyhood) obj;
        if (likelyhood != other.likelyhood)
            return false;
        if (illness == null) {
            if (other.illness != null)
                return false;
        } else if (!illness.equals(other.illness))
            return false;
        return true;
    }
}
