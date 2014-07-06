package models;

public class Quote {

    private long id;
    private String quote;
    private String attributedTo;
    private int year;

    public Quote(long id, String quote, String attributedTo, int year) {
        this.id = id;
        this.quote = quote;
        this.attributedTo = attributedTo;
        this.year = year;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAttributedTo() {
        return attributedTo;
    }

    public void setAttributedTo(String attributedTo) {
        this.attributedTo = attributedTo;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
