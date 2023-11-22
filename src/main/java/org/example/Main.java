package org.example;

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

    }
}

