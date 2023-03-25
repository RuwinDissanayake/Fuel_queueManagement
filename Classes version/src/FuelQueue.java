import java.io.PrintStream;

public class FuelQueue {
    private Passenger[] queue = new Passenger[6];
    private int noOfCustomers = 0;
    private static float fuelInStock = 6600;
    private final String name;
    private float totalLitresServed = 0;
    public FuelQueue(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean isFull() {
        return this.noOfCustomers == 6;
    }

    public boolean isEmpty() {
        return this.noOfCustomers == 0;
    }

    public void addto(Passenger customer) {
        if (!this.isFull()) {
            int i;
            for(i = 0; this.queue[i] != null; ++i) {
            }

            this.queue[i] = customer;
            ++this.noOfCustomers;
        } else {
            System.out.println("This Queue is Full");
        }

    }

    public boolean isStockLow() {
        return fuelInStock <= 500.0F;
    }

    public String serve(String storingData) {
        if (this.isEmpty()) {
            System.out.println("There are No Customers in the Queue");
        } else {
            storingData = storingData + "vehicleNumber: " + this.queue[0].getVehicleNumber() + " " + this.queue[0].getFirstName() + " " + this.queue[0].getSurname() + " was served " + this.queue[0].getcustomerLitres() + " Litres by : " + this.name + "\n";
            fuelInStock -= this.queue[0].getcustomerLitres();
            this.totalLitresServed += this.queue[0].getcustomerLitres();

            for(int i = 0; i < 5; ++i) {
                this.queue[i] = this.queue[i + 1];
            }

            --this.noOfCustomers;
            if (this.isStockLow()) {
                System.out.println("Available Stock is too low");
            }
        }

        return storingData;
    }

    public void removefrom(int customerNumber) {
        if (customerNumber > this.noOfCustomers) {
            System.out.println("Enter a valid Input!");
        } else {
            Passenger[] tempList = new Passenger[6];
            if (customerNumber - 1 >= 0) {
                System.arraycopy(this.queue, 0, tempList, 0, customerNumber - 1);
            }

            if (this.noOfCustomers - customerNumber >= 0) {
                System.arraycopy(this.queue, customerNumber, tempList, customerNumber - 1, this.noOfCustomers - customerNumber);
            }

            this.queue = tempList;
            --this.noOfCustomers;
        }

    }

    public void view() {
        Passenger[] tempList = this.queue;

        int i;
        for(i = 0; i < this.noOfCustomers; ++i) {
            for(int j = i + 1; j < this.noOfCustomers; ++j) {
                if (tempList[i].getFirstName().compareTo(tempList[j].getFirstName()) > 0) {
                    Passenger tempHolder = tempList[i];
                    tempList[i] = tempList[j];
                    tempList[j] = tempHolder;
                }
            }
        }

        for(i = 0; i < this.noOfCustomers; ++i) {
            PrintStream var10000 = System.out;
            String var10001 = tempList[i].getFirstName();
            var10000.println(var10001 + " " + tempList[i].getSurname());
        }

    }

    public static float viewFuelStock() {
        return fuelInStock;
    }

    public static void addFuelStock(float stock) {
        if (fuelInStock + stock > 6600) {
            System.out.println("Stock Limit Exceeded!");
        } else {
            fuelInStock += stock;
        }

    }

    public int getNoOfCustomers() {
        return this.noOfCustomers;
    }

    public float getTotalIncome() {
        return 430.0F * this.totalLitresServed;
    }
}