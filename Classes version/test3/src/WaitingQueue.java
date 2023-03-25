public class WaitingQueue {
    Passenger[] queue = new Passenger[6];
    int front = -1;
    int rear = -1;

    public WaitingQueue() {
    }

    void addCustomer(Passenger passenger) {
        if (this.front == -1 && this.rear == -1) {
            this.front = 0;
            this.rear = 0;
            this.queue[this.rear] = passenger;
        } else if ((this.rear + 1) % 6 == this.front) {
            System.out.println("This Queue is Full");
        } else {
            this.rear = (this.rear + 1) % 6;
            this.queue[this.rear] = passenger;
        }

    }

    void sendCustomerToFuelQueue() {
        if (this.front == -1 && this.rear == -1) {
            System.out.println("This Queue is Empty");
        } else if (this.front == this.rear) {
            this.front = -1;
            this.rear = -1;
        } else {
            this.front = (this.front + 1) % 6;
        }

    }

    Passenger getFirstPassenger() {
        return this.front == -1 ? null : this.queue[this.front];
    }
}