/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.util.Scanner;
/**
 *
 * @author Acer
 */
public class RSA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String msg;
        int pt[]=new int[100];
        int ct[]=new int[100];
        int z,n,d,e,p,q,mlen;
        
        Scanner in = new Scanner(System.in);
        do {
        System.out.println("Enter the two large prime numbers for p and q");
        p=in.nextInt();
        q=in.nextInt();
        } while(prime(p)==0||prime(q)==0);
        n=p*q;
        z=(p-1)*(q-1);
        
        System.out.println("Value of n "+n+"\nValue of z is : "+z);
        
        for(e=2;e<z;e++)
        {
        if(gcd(e,z)==1)
            break;
        }
        
        System.out.println("Encryption key e is: "+e);
        System.out.println("Public key is (e,n): "+e+","+n);
        
        for(d=2;d<z;d++)
        {
        if((e*d)%z == 1)
            break;
        }
        
        System.out.println("Decryption ley d is: " + d);
        System.out.println("Private key is (d,n) => " + d+ "," + n);
        
        in.nextLine();
        
        System.out.println("Enter the message for encryption");
        msg = in.nextLine();
        mlen = msg.length();
        
        for(int i=0;i<mlen;i++)
            pt[i]=msg.charAt(i);
        
        System.out.println("ASCII Values of PT array is");
        for(int i=0;i<mlen;i++)
            System.out.println(pt[i]);
        
        System.out.println("Encryption: Cipher Text Obtained: ");
        for(int i=0;i<mlen;i++)
            ct[i]=mult(pt[i],e,n);
        
        
        for(int i=0;i<mlen;i++)
            System.out.println(ct[i]+"\t");
        
        System.out.println("\nDecryption: Plain Text Obtained: ");
        for(int i=0;i<mlen;i++)
            pt[i]=mult(ct[i],d,n);
        
        for(int i=0;i<mlen;i++)
        {
            System.out.println(pt[i]+" : "+(char)pt[i]);
        }
    }
    
    public static int gcd(int x, int y)
    {
    if(y==0)
        return x;
    else
        return gcd(y, x%y);
    }
    
    public static int prime(int num) {
        int i;
        for(i=2;i<=num/2;i++) {
            if(num%i == 0)
                return 0;
        }
        return 1;
    }
    
    public static int mult(int base, int exp, int n){
        int res=1,j;
        for(j=1;j<=exp;j++)
            res=((res*base)%n);
        return res;
    }
}
