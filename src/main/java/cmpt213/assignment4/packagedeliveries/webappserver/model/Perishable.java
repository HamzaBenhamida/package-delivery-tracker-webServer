package cmpt213.assignment4.packagedeliveries.webappserver.model;

import java.time.LocalDateTime;

public class Perishable extends Package {
    private LocalDateTime expiryDate;

    public Perishable() {
        this.type = "perishable";
    }

    @Override
    public void setAuthor(String author) {

    }

    @Override
    public void setEnvHandlingFee(double fee) {

    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String getAuthor() {
        return null;
    }

    @Override
    public double getEnvHandlingFee() {
        return 0;
    }

    public String getType() {
        return type;
    }
}
