/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketfiletransfer;

import java.net.*;
import java.io.*;
/**
 *
 * @author Acer
 */
public class SocketServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        ServerSocket servSocket = new ServerSocket(4000);
        System.out.println("****Server Side****");
        System.out.println("Server ready for conection");
        
        Socket connSock = servSocket.accept();
        System.out.println("Connection is successful and ready for file transfer");
        InputStream istream=connSock.getInputStream();
        BufferedReader fileRead = new BufferedReader(new InputStreamReader(istream));
        String fname = fileRead.readLine();
        File fileName = new File(fname);
        
        OutputStream ostream = connSock.getOutputStream();
        PrintWriter pwrite = new PrintWriter(ostream, true);
        
        if(fileName.exists()) {
            BufferedReader contentRead = new BufferedReader(new FileReader(fname));
            System.out.println("Writing file Contents to the socket");
            String str;
            while((str = contentRead.readLine())!=null) {
                pwrite.println(str);
            }
            contentRead.close();
        } else {
            System.out.println("Requested file does not exist");;
            String msg = "Requested file does not exist at server side";
            pwrite.println(msg);
        }
        connSock.close();
        servSocket.close();
        fileRead.close();
        pwrite.close();
    }
    
}
