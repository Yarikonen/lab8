package server;



import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;

public class ResponsesHandler<V> {
    private final InetAddress host;
    private final int port;
    private final DatagramSocket ds;
    public ResponsesHandler(InetAddress host, int port, DatagramSocket ds){
        this.host=host;
        this.port=port;
        this.ds=ds;

    }
    public V get() throws IOException, ClassNotFoundException, SocketTimeoutException {
        byte[] arr2 = new byte[10000];
        DatagramPacket dp2 = new DatagramPacket(arr2, arr2.length, host, port);
        ds.receive(dp2);
        PackageMan<V> pm = new PackageMan<>();
        V v =pm.setCommand(ByteBuffer.wrap(dp2.getData()));
        return(v);
    }
    public void send(V v)throws IOException,SocketTimeoutException{
        PackageMan<V> pm = new PackageMan<>();
        byte[] arr = pm.packResponse(v);
        DatagramPacket dp = new DatagramPacket(arr, arr.length, host, port);
        ds.send(dp);
    }
}
