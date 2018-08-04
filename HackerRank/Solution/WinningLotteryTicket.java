import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the winningLotteryTicket function below.
    static long winningLotteryTicket(String[] tickets) {
        long sol=0;
        int unique[]=new int[tickets.length];
        long ticketBitsAll[]=new long[1024];
        int k=0;
        for (String ticket : tickets) {
            int length = ticket.length();
            int ticketBits=0;
            for (int j = 0; j<length; j++) {
                int num = ticket.charAt(j) - 48;
                int ticketBit=0;
                if(((ticketBits>>num)&1)==0){
                    ticketBit=1<<num;
                }
                ticketBits+=ticketBit;                
            }
            ticketBitsAll[ticketBits]++;
            if(ticketBitsAll[ticketBits]==1){
                unique[k++]=ticketBits;
            }
        }
        for(int i=0;i<k;i++){
            if(unique[i]==1023){
                sol+=(ticketBitsAll[unique[i]]*(ticketBitsAll[unique[i]]-1))/2;
            }
            for(int j=i+1;j<k;j++){
                if((unique[i]|unique[j])==1023){
                    sol+=ticketBitsAll[unique[i]]*ticketBitsAll[unique[j]];
                }
            }
        }
        return sol;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] tickets = new String[n];

        for (int i = 0; i < n; i++) {
            String ticketsItem = scanner.nextLine();
            tickets[i] = ticketsItem;
        }

        long result = winningLotteryTicket(tickets);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
