package sample;

import java.text.DateFormat;
import java.util.ArrayList;

public class Service {
    private String founderName;
    private String serviceName;
    private ArrayList<String> listOfDates;
    private double allTimeToSpend;
    private double timeOfServicing;
    private double timeStart;
    private double timeStop;
    private String renterName;
    int status;
    int id;
    int founderId;

    /**
     *
     * status:
     * 0 - new
     * 1 - reserved
     * 2 - unused
     * 3 - withdrawn
     */

    public Service(String founder, String service, double allTime, int id, int founderId, ArrayList<String> list)
    {
        this.id = id;
        this.founderName = founder;
        this.serviceName = service;
        this.allTimeToSpend = allTime;
        this.timeOfServicing = 0.0;
        this.listOfDates = list;
        this.renterName = new String();
        this.status = 0;
        this.founderId = founderId;

    }

    void rentAService(String renter)
    {
        this.status = 1;
        this.renterName = renter;
        this.timeStart = System.currentTimeMillis();
    }

    void releaseAService()
    {
        this.timeStop = System.currentTimeMillis();
        this.timeOfServicing = this.timeStop - this.timeStart;
        this.allTimeToSpend -= this.timeOfServicing;
        if(this.allTimeToSpend == 0.0)
        {
            this.withdrawAService();
        }else
            this.changeStatusToUnused();
    }

    /*void setDates ()
    {
        this.listOfDates = list;
    }*/

    void withdrawAService()
    {
        this.status = 3;
    }

    void changeStatusToUnused()
    {
        this.status = 2;
    }

    String getFounderName() { return this.founderName; }

    String getServiceName() { return this.serviceName; }

    ArrayList<String> getListOfDates() { return this.listOfDates; }

    double getAllTimeToSpend() { return this.allTimeToSpend; }

    double getTimeOfServicing() { return this.timeOfServicing; }

    String getStatus()
    {
        if(this.status == 0)
        {
            return "New";
        }else if(this.status == 1)
        {
            return "Reserved";
        }else if(this.status == 2)
        {
            return "Unused";
        }else
            return "Withdrawn";

    }

    int getNumericStatus() { return this.status; }

    String getRenterName() { return this.renterName; }

    int getId() { return this.id; }

    int getFounderId() { return this.founderId; }

    void printService()
    {
        Server.out.println("ID: " + this.id);
        Server.out.println("Name: " + this.serviceName);
        Server.out.println("Founder ID: " + this.founderId);
        Server.out.println("Founder name: " + this.founderName);
        Server.out.println("Time to spend:" + this.allTimeToSpend);
        Server.out.println("Time used: " + this.timeOfServicing);
        Server.out.println("Possible dates: ");
        for(int i=0 ; i<this.listOfDates.size();i++)
        {
            Server.out.println(i+1 + ") " + this.listOfDates.get(i));
        }
        Server.out.println("Renter name: " + this.renterName);
        Server.out.println("Status: " + this.getStatus());

    }
}
