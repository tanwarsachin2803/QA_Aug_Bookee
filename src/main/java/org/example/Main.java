package org.example;

import Utilities.BaseClass;
import Utilities.ReadFile;

import java.io.IOException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        Properties prop;
        ReadFile readFile=new ReadFile();
        prop=readFile.readConfig("config");
        System.out.println(prop.get("name"));
        BaseClass bs=new BaseClass();
        bs.browserSetup();
        bs.getUrl("https://f10boxing.weebly.com/reserve.html#/login");

    }
}

