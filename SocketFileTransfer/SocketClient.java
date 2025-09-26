/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket2;
import java.net.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author Acer
 */
public class SocketClient {
    public static void main(String[] args) throws Exception {
        Scanner in=new Scanner(System.in);
        Socket clientSocket = new Socket("127.0.0.1", 4000);
        System.out.println("****Client Side****");
        System.out.println("Enter the filename to transfer");
        String fname=in.nextLine();
        
        OutputStream ostream = clientSocket.getOutputStream();
        PrintWriter pwrite = new PrintWriter(ostream, true);
        pwrite.println(fname);
        
        InputStream istream = clientSocket.getInputStream();
        BufferedReader socketRead=new BufferedReader(new InputStreamReader(istream));
        System.out.println("Contents of the file " + fname + " are ");
        
        String str;
        while((str = socketRead.readLine())!=null) {
            System.out.println(str);
        }
        pwrite.close();
        socketRead.close();
    }
}
