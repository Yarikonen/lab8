package server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class User {
    private String userName;
    private String password;
    public User(String userName,String password) throws IOException,ClassNotFoundException, SocketTimeoutException {
        this.userName=userName;
        this.password=password;

    }






    public String login(ResponsesHandler<String> rh,ResponsesHandler<Response> rp){
        Response r = null;
        try {
            r = new Response(true, null, "login " + userName+" " + hashing(password,"aboba"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            rp.send(r);
            String ans = rh.get();
            return(ans);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return(null);

    }
    private String hashing(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes(StandardCharsets.UTF_8));
        byte[] bytes =md.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++){
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
       return(sb.toString());
    }
    public String getUserName(){
        return(this.userName);
    }


}
