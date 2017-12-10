package sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class UserMenu {
    
    private Customer customer;


    UserMenu() throws IOException { customer = new Customer(getCustomerName()); }

    synchronized private String getCustomerName() throws IOException {
        synchronized (Server.out) {
            Scanner in = new Scanner(System.in);
            Server.out.println("Input your name: ");
            Server.out.println("> ");
            String toReturn = Server.inBuff.readLine();
            //in.close();
            return toReturn;
        }

    }

    synchronized void createService() throws IOException {
        synchronized (Server.out) {
            Server.out.println("Type the name of service: ");
            Server.out.println("> ");
            String serviceName;
            double time;
            String date;
            ArrayList<String> dates = new ArrayList<>();
            Scanner in = new Scanner(System.in);
            serviceName = Server.inBuff.readLine();
            Server.out.println("Give time: ");
            Server.out.println("> ");
            time = Double.parseDouble(Server.inBuff.readLine());
            Server.out.println("Give possible dates [empty = break]:");
            Server.out.println("> ");
            date = Server.inBuff.readLine();
            while (date.length() > 9) {
                Server.out.println("> ");
                dates.add(date);
                date = Server.inBuff.readLine();
            }
            Service toAdd = new Service(this.customer.getName(), serviceName, time, Bank.getNextServiceId(), customer.getId(), dates);
            //toAdd.setDates(dates);
            Bank.addService(toAdd);
            //in.close();
        }
    }

    synchronized void rentService() throws IOException {
        synchronized (Server.out) {
            Server.out.println("Type Service ID: ");
            Server.out.println("> ");
            Scanner in = new Scanner(System.in);
            int id = Integer.parseInt(Server.inBuff.readLine());
            if (Bank.rentService(id, customer.getName()) == true) {
                Server.out.println("You have rented a service with ID = " + id);
            } else
                Server.out.println("The problem with service occured. You haven't rent this service.");
            //in.close();
        }
    }

    synchronized void releaseService() throws IOException {
        Server.out.println("Type Service ID: ");
        Server.out.println("> ");
        Scanner in = new Scanner(System.in);
        int id = Integer.parseInt(Server.inBuff.readLine());
        if(Bank.releaseService(id) == true)
        {
                Server.out.println( "The service has been released successfully! ");
        }else
            Server.out.println("The problem has been occured during a releasing proccess!");
        //in.close();

    }

    synchronized void deleteService() throws IOException {
        Server.out.println("Type Service ID: ");
        Server.out.println("> ");
        Scanner in = new Scanner(System.in);
        int id = Integer.parseInt(Server.inBuff.readLine());
        if(Bank.deleteService(id, customer.getId())==true)
        {
            Server.out.println( "The service has been deleted successfully! ");
        }else
            Server.out.println( "The problem has been occured. You aren't a former of this service or service had been deleted earlier!");
    }



}
