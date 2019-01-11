package com.example.imoocsdk.module;

import com.example.imoocsdk.module.monitor.Monitor;
import com.example.imoocsdk.module.monitor.emevent.EMEvent;

import java.util.ArrayList;

public class AdValue {
    public String resourceID;
    public String adid;
    public String resource;
    public String thumb;
    public ArrayList<Monitor> startMonitor;
    public ArrayList<Monitor> middleMonitor;
    public ArrayList<Monitor> endMonitor;
    public String clickUrl;
    public ArrayList<Monitor> clickMonitor;
    public EMEvent event;
    public String type;
}
