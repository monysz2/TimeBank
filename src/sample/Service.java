package sample;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.ArrayList;

public class Service {
    private String founderName;
    PrintWriter out;
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

    public Service(String founder, String service, double allTime, int id, int founderId, ArrayList<String> list, PrintWriter out)
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
        this.out = out;

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


    
}
