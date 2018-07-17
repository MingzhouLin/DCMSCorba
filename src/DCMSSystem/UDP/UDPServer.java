package DCMSSystem.UDP;

import DCMSSystem.ByteUtility;
import DCMSSystem.CenterServer;
import DCMSSystem.Record.Records;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class UDPServer implements Runnable {
    private int portNumber;
    private CenterServer centerServer;
    private boolean stop = true;
    private DatagramSocket datagramSocket = null;

    public UDPServer(int portNumber, CenterServer centerServer) {
        this.portNumber = portNumber;
        this.centerServer = centerServer;
    }

    @Override
    public void run() {
        try {
            datagramSocket = new DatagramSocket(portNumber);
            String reply = "";
            byte[] buffer = new byte[1024];
            byte[] sendBuffer = new byte[1024];
            while (stop) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                try {
                    Object object = null;
                    datagramSocket.receive(request);
                    System.out.printf("some data was recevied via udp");
                    try {
                        object = ByteUtility.toObject(request.getData());

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    if (object instanceof String) {
                        String receiveData = (String) object;
                        System.out.printf(receiveData);
                        if (receiveData.equals("getCount")) {
                            reply += centerServer.getLocalRecordCount();
                        } else if (receiveData.split(":")[0].equals("hb")) {
                            if (centerServer.servers.get(receiveData.split(":")[1]) != null) {
                                centerServer.servers.get(receiveData.split(":")[1]).lastHB = new Date();
                                centerServer.servers.get(receiveData.split(":")[1]).pid = Integer.parseInt(receiveData.split(":")[2]);
                            } else {
                                System.out.println("non-existent server");
                            }
                        } else if (receiveData.equals("elect")){
                            reply = "ok";
                        } else if (receiveData.split(":")[0].equals("leader")){
                            // this is questionable, if we need to know who is leader at all. This should be interested only to FE
                            if(!receiveData.split(":")[1].equals(centerServer.getCenterName())){
                                centerServer.servers.get(receiveData.split(":")[1]).status=1;
                            }
                        }
                    } else if (object instanceof Records) {
                        Records record = (Records) object;
                        HashMap<Character, ArrayList<Records>> centerdata = centerServer.database;
                        synchronized (centerdata) {
                            if (centerdata.get(record.getLastName().charAt(0)) != null) {
                                centerdata.get(record.getLastName().charAt(0)).add(record);

                            } else {
                                ArrayList<Records> newArray = new ArrayList<>();
                                newArray.add(record);
                                centerdata.put(record.getLastName().charAt(0), newArray);
                                System.out.println(record.getRecordID());
                            }
                        }
                        reply = record.getRecordID() + " is stored in the " + centerServer.getCenterName() + " | ";

                    }
                    if (reply.length() > 0) {
                        sendBuffer = reply.getBytes();
                        DatagramPacket send = new DatagramPacket(sendBuffer, sendBuffer.length, request.getAddress(), request.getPort());
                        datagramSocket.send(send);
                    }

                } catch (IOException e) {
                    System.out.println("UDP Server socket is closed!");
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        System.out.println("UDP Server is closed!");

    }

    public void stopServer() {
        datagramSocket.close();
        stop = false;

    }
}