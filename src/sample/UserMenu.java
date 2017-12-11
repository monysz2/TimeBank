package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

class UserMenu {
    
    private Customer customer;
    PrintWriter out;
    BufferedReader inBuff;


    UserMenu(PrintWriter o, BufferedReader i) throws IOException {
        out = o;
        inBuff = i;
        customer = new Customer(getCustomerName());
    }

    synchronized private String getCustomerName() throws IOException {
        synchronized (this.out) {
            Scanner in = new Scanner(System.in);
            this.out.println("Input your name: ");
            this.out.println("> ");
            String toReturn = this.inBuff.readLine();
            //in.close();
            return toReturn;
        }

    }

    synchronized void createService() throws IOException {
        synchronized (this.out) {
            this.out.println("Type the name of service: ");
            this.out.println("> ");
            String serviceName;
            double time;
            String date;
            ArrayList<String> dates = new ArrayList<>();
            Scanner in = new Scanner(System.in);
            serviceName = this.inBuff.readLine();
            this.out.println("Give time: ");
            this.out.println("> ");
            time = Double.parseDouble(this.inBuff.readLine());
            this.out.println("Give possible dates [empty = break]:");
            this.out.println("> ");
            date = this.inBuff.readLine();
            while (date.length() > 9) {
                this.out.println("> ");
                dates.add(date);
                date = this.inBuff.readLine();
            }
            Service toAdd = new Service(this.customer.getName(), serviceName, time, Bank.getNextServiceId(), customer.getId(), dates, this.out);
            //toAdd.setDates(dates);
            Bank.addService(toAdd);
            //in.close();
        }
    }

    synchronized void rentService() throws IOException {
        synchronized (this.out) {
            this.out.println("Type Service ID: ");
            this.out.println("> ");
            Scanner in = new Scanner(System.in);
            int id = Integer.parseInt(this.inBuff.readLine());
            if (Bank.rentService(id, customer.getName()) == true) {
                this.out.println("You have rented a service with ID = " + id);
            } else
                this.out.println("The problem with service occured. You haven't rent this service.");
            //in.close();
        }
    }

    synchronized void releaseService() throws IOException {
        this.out.println("Type Service ID: ");
        this.out.println("> ");
        Scanner in = new Scanner(System.in);
        int id = Integer.parseInt(this.inBuff.readLine());
        if(Bank.releaseService(id) == true)
        {
                this.out.println( "The service has been released successfully! ");
        }else
            this.out.println("The problem has been occured during a releasing proccess!");
        //in.close();

    }

    synchronized void deleteService() throws IOException {
        this.out.println("Type Service ID: ");
        this.out.println("> ");
        Scanner in = new Scanner(System.in);
        int id = Integer.parseInt(this.inBuff.readLine());
        if(Bank.deleteService(id, customer.getId())==true)
        {
            this.out.println( "The service has been deleted successfully! ");
        }else
            this.out.println( "The problem has been occured. You aren't a former of this service or service had been deleted earlier!");
    }



}
