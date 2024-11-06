package carstore;



public class Customer extends Person{

    public Customer() {
    }

    public Customer(int id, String name, String email, String password, String contactNumber, Location address) {
        super(id, name, email, password, contactNumber, address);
    }

    @Override
    public void showDetails(){
        super.showDetails();
    }

    @Override
    public String toString(){
        return super.toString();
    }

}
