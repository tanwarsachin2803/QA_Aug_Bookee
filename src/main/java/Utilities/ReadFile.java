package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadFile {
    Properties prop;
    public Properties readConfig(String fileName) throws IOException {
        prop=new Properties();
        String file=System.getProperty("user.dir")+"/src//main/resources/"+fileName+".properties";
        FileInputStream fis=new FileInputStream(file);
        prop.load(fis);
        return prop;
    }

}