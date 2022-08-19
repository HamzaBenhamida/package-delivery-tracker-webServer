package cmpt213.assignment4.packagedeliveries.webappserver.controllers;

import cmpt213.assignment4.packagedeliveries.webappserver.control.PackageRepository;
import cmpt213.assignment4.packagedeliveries.webappserver.model.Book;
import cmpt213.assignment4.packagedeliveries.webappserver.model.Electronic;
import cmpt213.assignment4.packagedeliveries.webappserver.model.Package;
import cmpt213.assignment4.packagedeliveries.webappserver.model.Perishable;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    PackageRepository repository;

    public RestController(PackageRepository repository) {
        this.repository = repository;
    }

    /**
     * Greets User
     * @return
     */
    @GetMapping("/ping")
    public String greetUser() {
        return "System is up!";
    }

    /**
     * Return all packages as a JSON object.
     * @return
     */
    @GetMapping("/listAll")
    public List<Package> getAllPackages() {
        return repository.listAll();
    }

    /**
     * Adds a new package. Body of request is a JSON object representing
     * the package
     * @param newPackage
     * @return
     */
    @PostMapping("/addPackage")
    public List<Package> addNewPackage(@RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Book newPackage) {
      return repository.create(newPackage);
    }

    /**
     * Adds a new book package to list of packages. Body of request is a JSON object representing
     * the package
     * @param newPackage
     * @return
     */
    @PostMapping("/addPackage/book")
    public List<Package> addBook(@RequestBody Book newPackage) {
        return repository.create(newPackage);
    }

    /**
     * Add a new electronic package to list of packages. Body of request is a JSON object representing
     * the package
     * @param newPackage
     * @return
     */
    @PostMapping("/addPackage/electronic")
    public List<Package> addElectronic(@RequestBody Electronic newPackage) {
        return repository.create(newPackage);
    }

    /**
     * Add a new perishable package to list of packages. Body of request is a JSON object representing
     * the package
     * @param newPackage
     * @return
     */
    @PostMapping("/addPackage/perishable")
    public List<Package> addPerishable(@RequestBody Perishable newPackage) {
        return repository.create(newPackage);

    }

    /**
     * Removes package with specified id in list of packages
     * @param id
     * @return
     */
    @PostMapping("/removePackage/{id}")
    public List<Package> removePackageById(@PathVariable String id){
        return repository.removePackageById(id);
    }

    /**
     * returns list overdue packages
     * @return
     */
    @GetMapping("/listOverduePackage")
    public List<Package> listOverduePackages() {
        return repository.listOverduePackages();
    }

    /**
     * returns list upcoming packages
     * @return
     */
    @GetMapping("/listUpcomingPackage")
    public List<Package> listUpcomingPackages() {
        return repository.listUpcomingPackages();
    }

    /**
     * Updates isDelivered field of package with specified to id. If true then becomes false and vice versa.
     * @param id
     * @return
     */
    @PostMapping("/markPackageAsDelivered/{id}")
    public List<Package> markPackageAsDelivered(@PathVariable String id) {
        return repository.markPackageAsDelivered(id);
    }

    /**
     * saves list of packages to json file for future uses
     */
    @GetMapping("/exit")
    public void exit() {
        repository.save();
    }

}