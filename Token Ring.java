import java.io.*;
import java.net.*;

public class ClientOne {
    public static void main(String args[]) throws IOException {
        Socket s = new Socket("localhost", 7000);
        PrintStream out = new PrintStream(s.getOutputStream());
        ServerSocket ss = new ServerSocket(7001);
        Socket s1 = ss.accept();
        BufferedReader in1 = new BufferedReader(new InputStreamReader(s1.getInputStream()));
        PrintStream out1 = new PrintStream(s1.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "Token";
        while (true) {
            if (str.equalsIgnoreCase("Token")) {
                System.out.println("Enter Data :");
                str = br.readLine();
                if (str.equalsIgnoreCase("quit")) {
                    break;
                }
                out.println("ClientOne:"+str);
                out1.println(str);
                out1.println("Token");
            }
            else
            {
                System.out.println("ClientTwo: "+str);
            }
            System.out.println("Waiting for Token");
            str = in1.readLine();
        }
        s.close();
        ss.close();
    }
}

*******************************************************************************************************

import java.io.*;
import java.net.*;

public class ClientTwo {
    public static void main(String args[]) throws IOException {
        Socket s = new Socket("localhost", 7000);
        PrintStream out = new PrintStream(s.getOutputStream());
        Socket s2 = new Socket("localhost", 7001);
        BufferedReader in2 = new BufferedReader(new InputStreamReader(s2.getInputStream()));
        PrintStream out2 = new PrintStream(s2.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while (true) {
            System.out.println("Waiting for Token");
            str = in2.readLine();

            if (str.equalsIgnoreCase("Token")) {
                System.out.println("Enter data: ");
                str = br.readLine();
                if (str.equalsIgnoreCase("quit")) {
                    break;
                }
                else{
                    out.println("ClientTwo:"+str);
                }
                out2.println(str);
                out2.println("Token");
            }
            else{
                System.out.println("ClintOne: "+str);
            }
        }
        s.close();
        s2.close();
    }
}

************************************************************************************************

import java.io.*;
import java.net.*;

public class MutualServer implements Runnable {
    Socket socket = null;
    static ServerSocket ss;

    MutualServer(Socket newSocket) {
        this.socket = newSocket;
    }

    public static void main(String args[]) throws IOException {
        ss = new ServerSocket(7000);
        System.out.println("Server Started");
        while (true) {
            Socket s = ss.accept();
            MutualServer es = new MutualServer(s);
            Thread t = new Thread(es);
            t.start();
        }
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                System.out.println(in.readLine());
            }
        } catch (Exception e) {
        }
    }
}
