package example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.nio.ByteBuffer;


public class SocketClient {

    public static void main( String[] args ) throws IOException {
        // Check arguments
        if (args.length < 3) {
            System.err.println("Argument(s) missing!");
            System.err.printf("Usage: java %s host port file%n", SocketClient.class.getName());
            return;
        }

        String host = args[0];
        // Convert port from String to int
        int port = Integer.parseInt(args[1]);
        // Concatenate arguments using a string builder
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < args.length; i++) {
            sb.append(args[i]);
            if (i < args.length-1) {
                sb.append(" ");
            }
        }
        String text = sb.toString();

        // Create client socket
        try {
            Socket socket = new Socket(host, port);
            System.out.printf("Connected to server %s on port %d %n", host, port);

            // Create stream to send data to server
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            /*// Send text to server as bytes
            out.writeBytes(text);
            out.writeBytes("\n");
            System.out.println("Sent text: " + text);*/
            BufferedImage image = ImageIO.read(new File("./walkietalkies.jpg"));

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", byteArrayOutputStream);

            byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
            out.write(size);
            out.write(byteArrayOutputStream.toByteArray());
            System.out.println("Sent image");

            // Close client socket
            socket.close();
            System.out.println("Connection closed");
        } catch (Exception e) {
            System.out.println("Servidor indisponivel, verifique se o servidor esta ligado");
        }

    }
}
