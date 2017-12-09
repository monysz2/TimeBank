package sample;

public class Customer {
    private int id;
    private String name;

    Customer(String name)
    {
        this.id = Bank.getNextId();
        this.name = name;
    }

    String getName()
    {
        return this.name;
    }

    int getId() { return this.id; }

}
