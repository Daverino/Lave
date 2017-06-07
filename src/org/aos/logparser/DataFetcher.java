package org.aos.logparser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class DataFetcher {
	
	private final URL url;
	private final String fileName;
	
	public DataFetcher (String url, String fileName) throws MalformedURLException  {
		this.url = new URL(url);
		this.fileName = fileName;
	}

	public boolean fetch ()  {
		try  {
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			BufferedWriter outputWriter  = new BufferedWriter(new FileWriter(fileName));
			System.out.println("Writing " + url.getPath() + " to " + fileName);
			for (String line = in.readLine(); line != null; line = in.readLine())  {
				outputWriter.write(line);
				outputWriter.newLine();
			}
			in.close();
			outputWriter.close();
			System.out.println("Transfer complete");
		} catch (IOException ioe)  {
			System.out.println("Transfer failed");
			return false;
		}
		return true;
	}
	


}
