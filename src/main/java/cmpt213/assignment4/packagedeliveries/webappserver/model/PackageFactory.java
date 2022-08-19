package cmpt213.assignment4.packagedeliveries.webappserver.model;

public class PackageFactory {
    //use getPackage method to get object of type package
    public Package getPackage(String packageType){
        if(packageType == null){
            return null;
        }
        if(packageType.equalsIgnoreCase("PERISHABLE")){
            return new Perishable();

        } else if(packageType.equalsIgnoreCase("ELECTRONIC")){
            return new Electronic();

        } else if(packageType.equalsIgnoreCase("BOOK")){
            return new Book();
        }

        return null;
    }
}
