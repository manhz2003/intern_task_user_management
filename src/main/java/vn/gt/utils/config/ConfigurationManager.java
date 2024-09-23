package vn.gt.utils.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import lombok.extern.slf4j.Slf4j;
import vn.gt.portlet.kehoach.ConvertUtil;

/**
 * @date: 3 Jul 2009
 */
@Slf4j
public class ConfigurationManager {
	
	public static final String fileNameAll = File.separator + "gt_config.properties";
	private static final ConfigurationManager instance = new ConfigurationManager();
	private ConcurrentHashMap<String, Properties> cachedProperties = new ConcurrentHashMap<String, Properties>();
	private volatile ConcurrentHashMap<String, ConcurrentProperties<String, Object>> concurrentProperties =
		new ConcurrentHashMap<String, ConcurrentProperties<String, Object>>();
	
	private ConfigurationManager() {
	}
	
	public static ConfigurationManager getInstance() {
		return instance;
	}
	
	@Deprecated()
	public Properties getProperties(String filename) {
		Properties properties = cachedProperties.get(filename);
		if (properties != null) return properties;
		Properties loaded = loadProperties(filename);
		if (loaded != null) {
			cachedProperties.put(filename, loaded);
			return loaded;
		}
		return null;
	}
	
	public ConcurrentProperties<String, Object> getConcurrentProperties(String fileName) {
		if (!concurrentProperties.containsKey(fileName)) {
			System.out.println("========LOAD FILE CAU HINH NAY==============" + fileName);
			ConcurrentProperties<String, Object> properties = new ConcurrentProperties<String, Object>(1000);
			Properties temp = loadProperties(fileName);
			for (Iterator<Object> iter = temp.keySet().iterator(); iter.hasNext();) {
				String key = String.class.cast(iter.next());
				properties.put(key, temp.get(key));
			}
			concurrentProperties.putIfAbsent(fileName, properties);
			
			return properties;
		}
		return concurrentProperties.get(fileName);
	}
	
	public void reloadConfiguration() {
		cachedProperties.clear();
		concurrentProperties.clear();
	}
	
	public void reloadConfiguration(String module) {
		cachedProperties.remove(module);
	}
	
	private Properties loadProperties(String module1) {
		Properties props = null;
		props = new java.util.Properties();
		try {
			InputStream pin = ConfigurationManager.class.getResourceAsStream(module1);
			props.load(pin);
			return props;
		} catch (FileNotFoundException e) {
			System.err.println("Can not find external properties file1: " + module1);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Can not find external properties file2: " + module1);
		}
		
		try {
			InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileNameAll);
			props.load(inputStream);
			return props;
		} catch (Exception e) {
			System.err.println("Can not find external properties file3: " + fileNameAll);
		}
		return null;
	}
	
	public static String getStrProp(String key) {
		return getStrProp(key, "");
	}
	
	public static String getStrProp(String key, String defaultVal) {
		try {
			Object result = readFileProperties(key);
			if (result != null && result.toString().trim().length() > 0) { return result.toString(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defaultVal;
	}
	
	public static int getIntProp(String key) {
		return getIntProp(key, 0);
	}
	
	public static int getIntProp(String key, int defaultVal) {
		try {
			Object result = readFileProperties(key);
			if (result != null && result.toString().trim().length() > 0) { return ConvertUtil.convertToInt(result.toString()); }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defaultVal;
	}
	
	public static long getLongProp(String key, long defaultVal) {
		try {
			Object result = readFileProperties(key);
			if (result != null && result.toString().trim().length() > 0) { return ConvertUtil.convertToLong(result.toString()); }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defaultVal;
	}
	
	public static Double getDoubleProp(String key, Double defaultVal) {
		try {
			Object result = readFileProperties(key);
			if (result != null && result.toString().trim().length() > 0) { return ConvertUtil.convertToDouble(result.toString()); }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defaultVal;
	}
	
	private static Object readFileProperties(String key) {
		Object result = null;
		try {
			ConcurrentProperties<String, Object> properties = instance.getConcurrentProperties(fileNameAll);
			result = properties.get(key);
			if (result != null && result.toString().trim().length() > 0) { return result; }
		} catch (Exception e) {
			log.error("readFileProperties fail: ",e);
//			e.printStackTrace();
		}
		return result;
	}
}
