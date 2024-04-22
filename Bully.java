import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Bully {
    static boolean[] state = new boolean[5];//[ture,true,true,ture,false] 
    int coordinator;

    public static void up(int up) {//2
        if (state[up - 1]) {
            System.out.println("Process " + up + " is already up");

        } else {

            Bully.state[up - 1] = true;
            System.out.println("Process " + up + " is Up");

        }
    }

    public static void down(int down) {
        if (!state[down - 1]) {
            System.out.println("Process " + down + " is already dowm.");
        } else {
            Bully.state[down - 1] = false;
        }
    }

    public static void mess(int mess) { // 2
        if (state[mess - 1]) { // 1
            if (state[4]) {
                System.out.println("OK");
            } else if (!state[4]) {
                int i;
                System.out.println("Process " + mess + " election");
                // 2  ; 2 < 5 : ++ // 2 3 4   //3 4 5
                for (i = mess; i < 5; ++i) {
                    if (!state[i]) continue;
                    System.out.println("Election send from process " + mess + " to process " + (i + 1));
                }

                for (i = mess; i < 5; ++i) {
                    if (!state[i]) continue;
                    mess(mess+1); return;
                }

                for (i = 5; i >= mess; --i) {
                    if (!state[i - 1]) continue;
                    System.out.println("Coordinator message send from process " + i + " to all");
                    break;
                }
            }
        } else {
            System.out.println("Process " + mess + " is down");
        }
    }

    public static void main(String[] args) {
        int choice;
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 5; ++i) {
            Bully.state[i] = true;
        }
        System.out.println("5 active process are:");
        System.out.println("Process up  = p1 p2 p3 p4 p5");
        System.out.println("Process 5 is coordinator");
        do {
            System.out.println(".........");
            System.out.println("1) Up a process.");
            System.out.println("2) Down a process");
            System.out.println("3) Send a message");
            System.out.println("4) Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("Bring proces up");
                    int up = sc.nextInt();
                    if (up == 5) {
                        System.out.println("Process 5 is co-ordinator");
                        Bully.state[4] = true;
                        break;
                    }
                    Bully.up(up);
                    break;
                }
                case 2: {
                    System.out.println("Bring down any process.");
                    int down = sc.nextInt();
                    Bully.down(down);
                    break;
                }
                case 3: {
                    System.out.println("Which process will send message");
                    int mess = sc.nextInt();
                    Bully.mess(mess);
                }
            }
        } while (choice != 4);
        sc.close();    
    }
}

