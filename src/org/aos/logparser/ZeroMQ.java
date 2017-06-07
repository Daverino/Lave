package org.aos.logparser;

import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class ZeroMQ {


  public static final String SCHEMA_KEY = "FSDJump";
  public static final String RELAY = "tcp://eddn-relay.elite-markets.net:9500";
  
	public static void main(String[] args)  {
		System.out.println("Hello World");
    ZContext ctx = new ZContext();
    ZMQ.Socket client = ctx.createSocket(ZMQ.SUB);
    client.subscribe("".getBytes());
    client.setReceiveTimeOut(30000);

    client.connect(RELAY);
    System.out.println("EDDN Relay connected");
    ZMQ.Poller poller = new ZMQ.Poller(1);
    poller.register(client, ZMQ.Poller.POLLIN);
    byte[] output = new byte[256 * 1024];
    while (true) {
        int poll = poller.poll(10);
        if (poll == ZMQ.Poller.POLLIN) {
            ZMQ.PollItem item = poller.getItem(poll);

            if (poller.pollin(0)) {
                byte[] recv = client.recv(ZMQ.NOBLOCK);
                if (recv.length > 0) {
                    // decompress
                    Inflater inflater = new Inflater();
                    inflater.setInput(recv);
                    try {
                        int outlen = inflater.inflate(output);
                        String outputString = new String(output, 0, outlen, "UTF-8");
                        // outputString contains a json message

                        if (outputString.contains(SCHEMA_KEY) && !outputString.contains("government_None")) {
                            System.out.println(outputString);
                        }

                    } catch (DataFormatException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
	}
}