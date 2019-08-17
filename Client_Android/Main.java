import java.io.*;
import java.net.Socket;


class FTPClient
{
    public static void main(String[] args) throws Exception {
        int status = 0;
        long start = System.currentTimeMillis();
        FTPClient client = new FTPClient();

        if(status == 0)
            {
                // localhost for testing
                Socket sock = new Socket("127.0.0.1", 13267);
                System.out.println("Connecting...");
                OutputStream os = sock.getOutputStream();
                client.send(os);;
                status = 1;
                sock.close();
            }

        if(status == 1){
            // localhost for testing
            Socket sock = new Socket("127.0.0.1", 13267);
            System.out.println("Connecting...");
            InputStream is = sock.getInputStream();
            System.out.println("Receiveing...");
            client.receiveFile(is);
            status = 0;
            sock.close();
        }

    }


    public void send(OutputStream os) throws Exception {
        // sendfile
        File myFile = new File("/home/hunk/pic1.jpg");
        byte[] mybytearray = new byte[(int) myFile.length() + 1];
        FileInputStream fis = new FileInputStream(myFile);
        BufferedInputStream bis = new BufferedInputStream(fis);
        bis.read(mybytearray, 0, mybytearray.length);
        System.out.println("Sending...");
        os.write(mybytearray, 0, mybytearray.length);
        os.flush();
    }

    public void receiveFile(InputStream is) throws Exception {
        int filesize = 6022386;
        int bytesRead;
        int current = 0;
        byte[] mybytearray = new byte[filesize-1];

        FileOutputStream fos = new FileOutputStream("src/text.json");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        bytesRead = is.read(mybytearray, 0, mybytearray.length);
        current = bytesRead;

        do {
            bytesRead = is.read(mybytearray, current,
                    (mybytearray.length - current));
            if (bytesRead >= 0)
                current += bytesRead;
        } while (bytesRead > -1);

        bos.write(mybytearray, 0, current-1);
        bos.flush();
        bos.close();

        ParseJson parseJson = new ParseJson();
        parseJson.parse();

    }
}
