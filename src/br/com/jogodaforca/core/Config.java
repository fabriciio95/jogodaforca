package br.com.jogodaforca.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
	
	private static Properties props = new Properties();
	
	static {
		try(FileInputStream fs = new FileInputStream("config.properties")) {
			props.load(fs);
		} catch(IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public static String get(String name) {
		return props.getProperty(name);
	}
	
	public static void setMaxErrors(String maxErrors) {
		props.setProperty("maxErrors", maxErrors);
	}

}
