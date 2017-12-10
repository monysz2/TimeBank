package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static Bank bank = new Bank();
    static ArrayList<Server> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(6666);
        while(true)
        {
            Socket clientSocket = serverSocket.accept();

            Server server = new Server(clientSocket);
            clients.add(server);
            server.start();

        }





    }
}
