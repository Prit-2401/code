import java.util.Date;
import java.util.Scanner;
import java.text.ParseException; 
import java.text.SimpleDateFormat;

public class Berkeley {
    public static void berkeleyAlgo(int numNodes, String[] nodes, String servertimeString) {
        System.out.println("Server Clock   = " + servertimeString);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        try {
            long serverTime = sdf.parse(servertimeString).getTime(); // Server time in milliseconds
            long totalDiff = 0;

            // Calculating total time differences w.r.t server
            for (String node : nodes) {
                long nodetime = sdf.parse(node).getTime(); // Client time in milliseconds
                long nodediff = nodetime - serverTime; // Time difference
                System.out.println("Time difference for Node: " + nodediff / 1000 + " seconds");
                totalDiff += nodediff;
            }

            // Fault tolerant Average
            long aveg = totalDiff / numNodes;
            System.out.println("Fault tolerant average = " + aveg / 1000 + " seconds");

            // Adjustment and synchronization
            System.out.println("Synchronized Server Clock  = " + sdf.format(new Date(serverTime + aveg)));
            for (String node : nodes) {
                long nodeTime = sdf.parse(node).getTime();
                long nodediff = nodeTime - serverTime;
                long newNodeTime= nodeTime + (aveg - nodediff);
                System.out.println("Synchronized Client Clock = " + sdf.format(new Date(newNodeTime)));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of nodes: ");
        int numNodes = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String[] nodeTimes = new String[numNodes];
        for (int i = 0; i < numNodes; i++) {
            System.out.print("Enter time for node " + (i + 1) + " (HH:mm:ss): ");
            nodeTimes[i] = scanner.nextLine();
        }

        System.out.print("Enter the server time (HH:mm:ss): ");
        String serverTime = scanner.nextLine();

        berkeleyAlgo(numNodes, nodeTimes, serverTime);

        scanner.close();
    }
}
