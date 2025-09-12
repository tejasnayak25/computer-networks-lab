/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slidingwindowprotocol;

/**
 *
 * @author Acer
 */
public class SlidingWindowProtocol {
    private int windowSize;
    private int[] frames;
    private boolean[] ack;
    
    public SlidingWindowProtocol(int windowSize, int frameCount) {
        this.windowSize = windowSize;
        this.frames = new int[frameCount];
        this.ack = new boolean[frameCount];
        
        for(int i=0;i<frameCount;i++) {
            frames[i]=i;
            ack[i]=false;
        }
    }
    
    public void sendFrames() {
        int sendIndex = 0;
        
        while(sendIndex < frames.length) {
            for(int i=0;i<windowSize && (sendIndex+i)<frames.length;i++) {
                System.out.println("Sending frame: "+frames[sendIndex+i]);
            }
            
            for(int i=0;i<windowSize && (sendIndex+i)<frames.length;i++) {
                ack[sendIndex+i]=receiveAck(sendIndex+i);
            }
            while(sendIndex<frames.length && ack[sendIndex]) {
                sendIndex++;
            }
        }
    }
    
    public boolean receiveAck(int frame) {
        System.out.println("Receiving ack for frame: " + frame);
        return true;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int windowSize = 4;
        int frameCount = 10;
        
        SlidingWindowProtocol swp = new SlidingWindowProtocol(windowSize, frameCount);
        swp.sendFrames();
    }
    
}
