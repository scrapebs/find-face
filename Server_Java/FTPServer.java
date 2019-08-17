import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class FTPServer {
    public static void main(String[] args) throws Exception {
        // create socket
        int status = 0;
        ServerSocket servsock = new ServerSocket(13267);
        while (true) {
            if(status == 0){
            System.out.println("Waiting...");
            Socket sock = servsock.accept();
            System.out.println("Accepted connection : " + sock);
            FTPServer server = new FTPServer();
            InputStream is = sock.getInputStream();
            server.receiveFile(is);
            status = 1;
            }
            if(status == 1){
                System.out.println("Waiting...");
                HelloOpenCV CV = new HelloOpenCV();
                CV.main();
                Socket sock = servsock.accept();
                System.out.println("Accepted connection : " + sock);
                FTPServer server = new FTPServer();
            OutputStream os = sock.getOutputStream();
            server.send(os);
            sock.close();
            status = 0;
            }
        }
    }

    public void send(OutputStream os) throws Exception {
        // sendfile
        //File myFile = new File("user.json");
        File myFile = new File("/home/denis/Projects/ServerJAVACV/user.json");
        byte[] mybytearray = new byte[(int) myFile.length() + 1];
        FileInputStream fis = new FileInputStream(myFile);
        BufferedInputStream bis = new BufferedInputStream(fis);
        bis.read(mybytearray, 0, mybytearray.length);
        System.out.println("Sending...");
        os.write(mybytearray, 0, mybytearray.length);
        os.flush();
        os.close();
    }

    public void receiveFile(InputStream is) throws Exception {
        int filesize = 6022386;
        int bytesRead;
        int current = 0;
        byte[] mybytearray = new byte[filesize];

        FileOutputStream fos = new FileOutputStream("/home/denis/resources/123.jpg");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        bytesRead = is.read(mybytearray, 0, mybytearray.length);
        current = bytesRead;

        do {
            bytesRead = is.read(mybytearray, current,
                    (mybytearray.length - current));
            if (bytesRead >= 0)
                current += bytesRead;
        } while (bytesRead > -1);
        bos.write(mybytearray, 0, current);
        bos.flush();
        bos.close();
    }
}

