package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Server extends Thread{
    static PrintWriter out;
    static Socket clientSocket;
    static BufferedReader inBuff;


    Server(Socket so) throws IOException {
        this.clientSocket = so;

        this.out = new PrintWriter(so.getOutputStream(),true);

        this.inBuff = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }



    synchronized public void run() {
        synchronized (out) {

            Scanner in = new Scanner(System.in);
            UserMenu menu = null;
            try {
                menu = new UserMenu();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.out.println("Welcome in The TimeBank");
            printMenu();
            Integer choice = null;
            try {
                choice = Integer.parseInt(this.inBuff.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                while (choice != 0)
                    switch (choice) {
                        case 1:
                            menu.createService();
                            printMenu();
                            choice = Integer.parseInt(this.inBuff.readLine());
                            break;
                        case 2:
                            Main.bank.printAllServices();
                            printMenu();
                            choice = Integer.parseInt(this.inBuff.readLine());
                            break;

                        case 3:
                            menu.rentService();
                            printMenu();
                            choice = Integer.parseInt(this.inBuff.readLine());
                            break;
                        case 4:
                            menu.releaseService();
                            printMenu();
                            choice = Integer.parseInt(this.inBuff.readLine());
                            break;
                        case 5:
                            menu.deleteService();
                            printMenu();
                            choice = Integer.parseInt(this.inBuff.readLine());
                            break;
                        case 0:
                            this.out.println("Thank you for using our bank. Have a nice day!");
                            clientSocket.close();
                            break;

                        default:
                            this.out.println("Wrong choice, try again!");
                            printMenu();
                            choice = Integer.parseInt(this.inBuff.readLine());
                            break;
                    }
            } catch (Exception ex) {
            }
        }
    }

        synchronized public void printMenu()
        {

            this.out.println("What do you want to do:");
            this.out.println("1) Create a service");
            this.out.println("2) Show all services");
            this.out.println("3) Rent a service");
            this.out.println("4) Release a service");
            this.out.println("5) Remove a service");
            this.out.println("0) Exit");
            this.out.println("> ");
        }
    }
