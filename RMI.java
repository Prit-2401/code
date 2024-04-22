//Client

import java.rmi.*;
import java.util.Scanner;

public class Client {
    public static void main(String args[]) {
        try {
            Scanner s = new Scanner(System.in);
            ServerInterface si = (ServerInterface) Naming.lookup("localhost");
            String x,y;
            System.out.println("Enter first string : ");
            x = s.nextLine();
            System.out.println("Enter second string : ");
            y = s.nextLine();
            System.out.println(si.concat(x, y));
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

****************************************************************************************
//Server

import java.rmi.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            Servant s = new Servant();
            Naming.rebind("localhost", s);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

*****************************************************************************************
//Servant

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class Servant extends UnicastRemoteObject implements ServerInterface {
     Servant() throws RemoteException {
        super();
    }

    @Override
    public String concat(String x, String y) throws RemoteException {
        return x + y;
    }
}

*****************************************************************************************
//ServerInterface

import java.rmi.*;

public interface ServerInterface extends Remote {
    String concat(String x, String y) throws RemoteException;
}
