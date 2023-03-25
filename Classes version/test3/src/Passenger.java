public class Passenger {

    private String firstName;
    private String Surname;
    private int vehicleNumber;
    private float customerLitres;

    public Passenger(String firstName, String Surname, int vehicleNumber, float customerlitres){
        this.firstName = firstName;
        this.Surname = Surname;
        this.vehicleNumber = vehicleNumber;
        this.customerLitres = customerlitres;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return Surname;
    }

    public int getVehicleNumber() {
        return vehicleNumber;
    }

    public float getcustomerLitres() {
        return customerLitres;
    }
}
