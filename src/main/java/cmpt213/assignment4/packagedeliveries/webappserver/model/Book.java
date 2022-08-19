package cmpt213.assignment4.packagedeliveries.webappserver.model;

import java.time.LocalDateTime;

public class Book extends Package{
    private String author;

    public Book() {
        this.type = "book";
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public double getEnvHandlingFee() {
        return 0;
    }

    @Override
    public LocalDateTime getExpiryDate() {
        return null;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public void setEnvHandlingFee(double fee) {

    }

    @Override
    public void setExpiryDate(LocalDateTime expiryDate) {

    }

    public String getType() {
        return type;
    }
}
