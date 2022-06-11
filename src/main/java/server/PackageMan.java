package server;

import commands.Command;
import commands.CommandMan;

import java.io.*;
import java.nio.ByteBuffer;

public class PackageMan<V> {
    public V setCommand(ByteBuffer buf) throws IOException, ClassNotFoundException{
        ObjectInputStream obj = new ObjectInputStream(new ByteArrayInputStream(buf.array()));
        V v = (V) obj.readObject();
        return(v);



    }
    public byte[] packResponse(V v) throws IOException{
        ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
        ObjectOutputStream obj = new ObjectOutputStream(bos);
        obj.writeObject(v);
        return((bos.toByteArray()));
    }

}
