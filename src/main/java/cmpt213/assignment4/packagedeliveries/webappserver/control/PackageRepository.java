package cmpt213.assignment4.packagedeliveries.webappserver.control;

import cmpt213.assignment4.packagedeliveries.webappserver.gson.extras.RuntimeTypeAdapterFactory;
import cmpt213.assignment4.packagedeliveries.webappserver.model.*;
import cmpt213.assignment4.packagedeliveries.webappserver.model.Package;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Takes care of the logic of the rest api and also reads/writes to json file when starting and exiting server
 */
@Repository
public class PackageRepository {

    private List<Package> packages = new ArrayList<>();
    private AtomicLong nextId = new AtomicLong();

    /**
     * reads json when starting the server
     */
    public PackageRepository() {
        // reads json file
        Gson myGson = createGsonBuilder();
        packages = readJsonFile(myGson);

    }

    /**
     * returns all the packages
     * @return
     */
    public List<Package> listAll() {
        //System.out.println(packages);
        System.out.println("list before sending: " + packages);
        return packages;
    }

    /**
     * adds a new package to list
     * @param newPackage
     * @return
     */
    public List<Package> create(Package newPackage){
        // Set Package to have next ID
        newPackage.setId(String.valueOf(nextId.incrementAndGet()));
        packages.add(newPackage);
        return packages;
    }

    /**
     * removes package in list by id
     * @param id
     * @return
     */
    public List<Package> removePackageById(String id){
        packages.removeIf(p -> p.getId().equals(id));
        return packages;
    }

    /**
     * returns list of overdue packages to client
     * @return
     */
    public List<Package> listOverduePackages() {
        ArrayList<Package> overduePackages = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        for (final Package p : packages) {
            //System.out.println(p.getDate() + " is after " + now + ": " + p.getDate().isAfter(now));
            if (p.getDate().isBefore(now)
                    && p.isDelivered() == false)
            {
                overduePackages.add(p);
            }
        }

       return overduePackages;
    }

    /**
     * returns list of upcoming packages to client
     * @return
     */
    public List<Package> listUpcomingPackages() {
        ArrayList<Package> upcomingPackages = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        for (final Package p : packages) {
            //System.out.println(p.getDate() + " is after " + now + ": " + p.getDate().isAfter(now));
            if (p.getDate().isAfter(now) && p.isDelivered() == false)
            {
                upcomingPackages.add(p);
            }
        }

        return upcomingPackages;
    }

    /**
     * Updates isDelivered field of package with specified to id. If true then becomes false and vice versa.
     * @param id
     * @return
     */
    public List<Package> markPackageAsDelivered(String id){

        for (Package i : packages) {
            if (i.getId().equals(id)) {
                if ( i.isDelivered() == true) {
                    i.setDelivered(false);
                } else {
                    i.setDelivered(true);
                }
            }
        }
        System.out.println("package marked as delivered, list: " + packages);
        return packages;
    }

    /**
     * saves list of packages to json file for future uses.
     */
    public void save() {
        Gson myGson = createGsonBuilder();

        // Write the packages-list to JSON file
        try {
            // create a writer
            BufferedWriter writer = Files.newBufferedWriter(new File("src/main/java/cmpt213/assignment4/packagedeliveries/webappserver/list.json").toPath());

            //Serialize List of Objects To Json
            myGson.toJson(packages, writer);

            // close writer
            writer.close();
        } catch (IOException e) {
            e.getStackTrace();
            System.out.println("could not write list of packages to json file");
        }
    }

    //-------------------------------------------------------------------------------------------
    // GSON
    //-------------------------------------------------------------------------------------------

    /**
     * Read list of packages from Json File
     * @return ArrayList<Package>
     */
    public List<Package> readJsonFile(Gson myGson) {
        List<Package> list = new ArrayList<>();

        try {

            File file = new File("src/main/java/cmpt213/assignment4/packagedeliveries/webappserver/list.json");
            FileReader reader = new FileReader(file);
            Type packageListType = new TypeToken<ArrayList<Package>>(){}.getType();
            list = myGson.fromJson(reader, packageListType);

            // DEBUG
            //System.out.println("list:" + list);
            // DEBUG

        }  catch (IOException e) {
            e.getStackTrace();
            System.out.println("Error: could not read json file");
        }

        return list;
    }

    /**
     * Create Gson object using GsonBuilder
     * @return
     */
    public Gson createGsonBuilder() {
        // create runtimeTypeAdapterFactory
        RuntimeTypeAdapterFactory<Package> runtimeTypeAdapterFactory =
                RuntimeTypeAdapterFactory
                        .of(Package.class, "type") // typeFieldName
                        .registerSubtype(Book.class, "book")
                        .registerSubtype(Electronic.class, "electronic")
                        .registerSubtype(Perishable.class, "perishable");

        //Create Gson object using GsonBuilder
        return new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
                new TypeAdapter<LocalDateTime>() {
                    @Override
                    public void write(JsonWriter jsonWriter,
                                      LocalDateTime localDateTime) throws IOException {
                        jsonWriter.value(localDateTime.toString());
                    }
                    @Override
                    public LocalDateTime read(JsonReader jsonReader) throws IOException {
                        return LocalDateTime.parse(jsonReader.nextString());
                    }
                }).registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();
    }

}
