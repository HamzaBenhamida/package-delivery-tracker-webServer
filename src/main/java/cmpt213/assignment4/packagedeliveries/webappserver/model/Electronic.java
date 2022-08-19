package cmpt213.assignment4.packagedeliveries.webappserver.model;

import java.time.LocalDateTime;

public class Electronic extends Package{
    private double envHandlingFee;

    public Electronic() {
        this.type = "electronic";
    }

    @Override
    public void setAuthor(String author) { return;   }

    public double getEnvHandlingFee() {
        return envHandlingFee;
    }

    @Override
    public LocalDateTime getExpiryDate() {
        return null;
    }

    public void setEnvHandlingFee(double envHandlingFee) {
        this.envHandlingFee = envHandlingFee;
    }

    @Override
    public void setExpiryDate(LocalDateTime expiryDate) {return;    }

    @Override
    public String getAuthor() {
        return null;
    }

    public String getType() {
        return type;
    }
}
