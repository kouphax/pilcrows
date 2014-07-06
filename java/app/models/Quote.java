package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "quotes")
public class Quote extends Model {

    @Id
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

    public static Finder<Long, Quote> find = new Finder<Long, Quote>(Long.class, Quote.class);
}
