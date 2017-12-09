package sample;

import java.util.ArrayList;
import java.util.Scanner;

public class UserMenu {

    Customer customer;

    public UserMenu() { customer = new Customer(getCustomerName()); }

    String getCustomerName()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Input your name: ");
        String toReturn = in.next();
        in.close();
        return toReturn;

    }

    void createService()
    {
        System.out.println("Type the name of service: ");
        String serviceName;
        double time;
        String date;
        ArrayList<String> dates = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        serviceName = in.next();
        System.out.println("Give time: ");
        time = in.nextDouble();
        System.out.println("Give possible dates [empty = break]:");
        while((date = in.next()) == null)
        {
            dates.add(date);
        }
        Service toAdd = new Service(this.customer.getName(),serviceName,time, Bank.getNextServiceId(),customer.getId());
        toAdd.setDates(dates);
        Bank.addService(toAdd);
        in.close();
    }

    void rentService()
    {
        System.out.println("Type Service ID: ");
        Scanner in = new Scanner(System.in);
        int id = in.nextInt();
        if(Bank.rentService(id,customer.getName()) == true);
        {
            System.out.println("You have rented a service with ID = "+ id);
        }System.out.println("The problem with service occured. You haven't rent this service.");
        in.close();
    }

    void releaseService
    {
        System.out.println("Type Service ID: ");
        Scanner in = new Scanner(System.in);
        int id = in.nextInt();
        if(Bank.releaseService(id) == true);
        {
                System.out.println( "The service has been released successfully! ");
        }System.out.println("The problem has been occured during a releasing proccess!");
        in.close();

    }

    void deleteService
    {
        System.out.println("Type Service ID: ");
        Scanner in = new Scanner(System.in);
        int id = in.nextInt();
        if(Bank.deleteService(id, customer.getId())==true)
        {
            System.out.println( "The service has been deleted successfully! ");
        }System.out.println( "The problem has been occured. You aren't a former of this service or service had been deleted earlier!");
    }


}
