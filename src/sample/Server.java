package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Server{
    static PrintWriter out;
    static ServerSocket clientSocket;
    static BufferedReader inBuff;

    Server(PrintWriter wr, ServerSocket so, BufferedReader buff)
    {
        this.out = wr;
        this.clientSocket = so;
        this.inBuff = buff;
    }



    void serve() throws IOException {

        Bank bank = new Bank();
        Scanner in = new Scanner(System.in);
        UserMenu menu = new UserMenu();
        out.println("Welcome in The TimeBank");
        printMenu();
        Integer choice;
        choice = Integer.parseInt(inBuff.readLine());
        while (choice != 0)
            switch (choice) {
                case 1:
                    menu.createService();
                    printMenu();
                    choice = Integer.parseInt(inBuff.readLine());
                    break;
                case 2:
                    bank.printAllServices();
                    printMenu();
                    choice = Integer.parseInt(inBuff.readLine());
                    break;

                case 3:
                    menu.rentService();
                    printMenu();
                    choice = Integer.parseInt(inBuff.readLine());
                    break;
                case 4:
                    menu.releaseService();
                    printMenu();
                    choice = Integer.parseInt(inBuff.readLine());
                    break;
                case 5:
                    menu.deleteService();
                    printMenu();
                    choice = Integer.parseInt(inBuff.readLine());
                    break;
                case 0:
                    out.println("Thank you for using our bank. Have a nice day!");
                    clientSocket.close();
                    break;

                default:
                    out.println("Wrong choice, try again!");
                    printMenu();
                    choice = Integer.parseInt(inBuff.readLine());
                    break;
            }
    }

        public static void printMenu()
        {

            out.println("What do you want to do:");
            out.println("1) Create a service");
            out.println("2) Show all services");
            out.println("3) Rent a service");
            out.println("4) Release a service");
            out.println("5) Remove a service");
            out.println("0) Exit");
            out.println("> ");
        }
    }
