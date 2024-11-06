package carstore;
/**
 * author: lyhieunghia
 */

public class Location {
    private String houseNumber, street, district, ward, city;

    //Contructor
    public Location() {
    }

    public Location(final String houseNumber, final String street, final String district, final String ward, final String city) {
        this.houseNumber = houseNumber;
        this.street = street;
        this.district = district;
        this.ward = ward;
        this.city = city;
    }

    //Getter and setter
    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(final String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(final String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(final String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(final String ward) {
        this.ward = ward;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    //convert into text for storing.
    public String toString(){
        return houseNumber+"\t"+street+"\t"+ward+"\t"+district+"\t"+city;
    }

    //show address 
    public void showDetails(){
        System.out.println("Address: "+houseNumber+", "+street+", "+ward+", "+district+", "+city);
    }

    
}
