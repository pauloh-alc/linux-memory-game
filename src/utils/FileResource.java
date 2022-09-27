package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileResource {
	
	private final String PATH;
	
	public FileResource(String path) {
		this.PATH = path;
	}
	
	public String getPath() {
		return PATH;
	}
	
	public List<String> getLines() {
		List<String> list = new ArrayList<>();
		
		try (BufferedReader bf = new BufferedReader(new FileReader(PATH))) {
			String line = bf.readLine();
			list.add(line);
			while (line != null) {
				line = bf.readLine();
				if (line != null) list.add(line);
			}
		}
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return list;
	}
}
