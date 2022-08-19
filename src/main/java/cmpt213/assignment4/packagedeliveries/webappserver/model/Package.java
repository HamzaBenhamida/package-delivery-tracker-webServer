package cmpt213.assignment4.packagedeliveries.webappserver.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

/**
 * The Package class represents all the different properties of a package
 * such as name, weight, deliveryDate ect.. With also some getters and setters to manage them
 */
public abstract class Package {


    private String id;
    protected String type;
    private String name;  // name can not be empty
    private String notes; // notes can be empty
    private double priceInDollar;
    private double weightInKg;
    private boolean isDelivered;

    //@DateTimeFormat(iso = DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    //@JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
    private LocalDateTime deliveryDate;


    /**
     * Constructor of the Package class
     */
    public Package() {
        super();
    }

    public Package(String name, String notes, double priceInDollar, double weightInKg, boolean isDelivered, LocalDateTime deliveryDate) {
        this.name = name;
        this.notes = notes;
        this.priceInDollar = priceInDollar;
        this.weightInKg = weightInKg;
        this.isDelivered = isDelivered;
        this.deliveryDate = deliveryDate;
    }

    public void setup (String name, String notes, double price, double weightInKg, boolean isDelivered, LocalDateTime deliveryDate) {
        this.name = name;
        this.notes = notes;
        this.priceInDollar = price;
        this.weightInKg = weightInKg;
        this.isDelivered = isDelivered;
        this.deliveryDate = deliveryDate;
    }

    /**
     * Mark the package as delivered
     */
    public void packageDelivered() { this.isDelivered = true;   }

    /**
     * get deliveryDate field
     * @return
     */
    public LocalDateTime getDeliveryDate() { return deliveryDate; }



    /**
     * neatly presents all the properties of the package as a String format
     * @return
     */
    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String yesOrNo = (this.isDelivered) ? "Yes" : "No";

        String string = "Id: " + this.id + '\n' +
                "Package: " + this.name + '\n' +
                "Notes: " + this.notes + '\n' +
                "Price: $" + this.priceInDollar + '\n'+
                "Weight: " + this.weightInKg + "kg \n" +
                "Expected Delivery Date: " + dtf.format(this.deliveryDate) + '\n' +
                "Delivered? " + yesOrNo + "\n";

        String start = "";
        String end = "";
        switch (this.type) {
            case "book":
                start = "Package Type: Book \n";
                end = "Author: " + getAuthor() + "\n";
                break;

            case "perishable":
                start = "Package Type: Perishable \n";
                end = "Expiry Date: " + getExpiryDate() + "\n";
                break;

            case "electronic":
                start = "Package Type: Electronic \n";
                end = "Environmental Handling Fee: " + getEnvHandlingFee() + "\n";
                break;

        }

        return start + string + end;
    }

    public String getType() {
        return type;
    }
    public String getNotes() {
        return notes;
    }
    public double getPriceInDollar() {
        return priceInDollar;
    }
    public double getWeightInKg() {
        return weightInKg;
    }

    /**
     * gets name property of the package
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * gets deliveryDate property of the package
     * @return
     */
    public LocalDateTime getDate() {  return deliveryDate;    }

    /**
     * sets deliveryDate property equal to the boolean variable in parameters
     * @param delivered
     */
    public void setDelivered(boolean delivered) { isDelivered = delivered;   }

    /**
     * gets isDelivered property from the package
     * @return
     */
    public boolean isDelivered() {
        return isDelivered;
    };

    public void setId(String id) { this.id = id; }

    public String getId() {return id;}

    //-----------------------------------------------------
    // ABSTRACT METHODS
    //-----------------------------------------------------
    public abstract void setAuthor(String author);

    public abstract void setEnvHandlingFee(double fee);

    public abstract void setExpiryDate(LocalDateTime expiryDate);

    public abstract String getAuthor();

    public abstract double getEnvHandlingFee();

    public abstract LocalDateTime getExpiryDate();



    /**
    * Helper class implementing Comparable interface
    * compares packages delivery date with descending order from most recent to oldest
    */
    public static class sortPackages implements Comparator<Package> {
        public int compare(Package a, Package b)
    {
        return b.getDeliveryDate().compareTo(a.getDeliveryDate());
    }
    }

}
