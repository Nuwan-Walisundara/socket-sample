package com.nuwan.codechallenge;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConfigReader {
	
	private static ConfigReader instance;
	
	Log log = LogFactory.getLog(ConfigReader.class);

	private ConfigReader() {}
	
	public static ConfigReader getInstance() {
		if(instance==null) {
			instance = new ConfigReader();
		}
		return instance;
		
	}
	
	
	public <T> T read(String filePath, Class<T> classz)
			throws InstantiationException, IllegalAccessException, IOException {
		FileInputStream fileStream=null;
		try {
			fileStream = new FileInputStream(filePath);
			return map(fileStream, classz);
		} catch (InstantiationException |IllegalAccessException e) {
			log.error("", e);
			throw e;
		} catch (FileNotFoundException e) {
			log.error("", e);
			throw e;
		} catch (IOException e1) {
			log.error("", e1);
			throw e1;
		}finally{
        	if(fileStream!=null){
        		try {
        			fileStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	}
        }
	}

	public <T> T read(File filePath, Class<T> classz)
			throws InstantiationException, IllegalAccessException, IOException {
		FileInputStream fileStream=null;
		try {

			fileStream = new FileInputStream(filePath);
			return map(fileStream, classz);
		} catch (FileNotFoundException e) {
			log.error("", e);
			throw e;
		} catch (IOException e1) {
			log.error("", e1);
			throw e1;
		}finally{
        	if(fileStream!=null){
        		try {
        			fileStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	}
        }
	}

	private <T> T map(FileInputStream inputStream, Class<T> classz)
			throws InstantiationException, IllegalAccessException, IOException {
		T o;
		try {
			o = classz.newInstance();
			Properties prop = new Properties();
			InputStream input = null;

			input = new BufferedInputStream(inputStream);

			// load a properties file from class path, inside static method
			prop.load(input);
			BeanUtils bu = new BeanUtils();
			prop.stringPropertyNames().forEach((name) -> {
				try {
					bu.setProperty(o, name, prop.getProperty(name));
				} catch (IllegalAccessException e) {
					log.info("No setter found for " + name);
				} catch (InvocationTargetException e) {
					log.info("" + e);
				}
			});

			return o;

		} catch (InstantiationException | IllegalAccessException | IOException e1) {
			log.error("", e1);
			throw e1;
		}finally{
        	if(inputStream!=null){
        		try {
        			inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	}
        }

	}
}
