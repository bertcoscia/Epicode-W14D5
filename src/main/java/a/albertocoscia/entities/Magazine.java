package a.albertocoscia.entities;

import a.albertocoscia.Periodicity;

public class Magazine extends WrittenMedium {
    protected Periodicity periodicity;

    public Magazine(String title, Periodicity periodicity) {
        super(title);
        this.periodicity = periodicity;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }


    @Override
    public String toString() {
        return "Magazine{" +
                " title='" + title + '\'' +
                ", periodicity=" + periodicity +
                ", isbn='" + isbn + '\'' +
                ", publicationDate=" + publicationDate +
                ", numPages=" + numPages +
                '}';
    }
}
