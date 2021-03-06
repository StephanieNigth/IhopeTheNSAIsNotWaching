/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buiss;

import aquiantince.IBuilding;
import aquiantince.IBuss;
import aquiantince.IData;
import aquiantince.IReadings;
import aquiantince.IRoom;
import aquiantince.ISensor;
import aquiantince.SensorType;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
/**
 *
 * @author ulriksandberg
 */
public class BusinessFacade implements IBuss {
    
    
    private ArrayList<IBuilding> buildings = new ArrayList<>();
    private IData data;
    private Timer timer;
    private TimerTask task;
    int senserGroupeID = 0;
    //
    
    
    @Override
    public void addBuilding(String name, String address) {
        
        IBuilding building = new Building(name, address);
        
        this.buildings.add(building);
          
    }

    @Override
    public Boolean removeBuilding(IBuilding building) {
    
        if(buildings.contains(building)) {
            if(buildings.remove(building)) {
            return true;
            } else {
                return false;
            }
        }
        
        return false;
        
    }

    @Override
    public IBuilding[] getBuildings() {
        
        IBuilding[] buildingList = new IBuilding[this.buildings.size()];
        return this.buildings.toArray(buildingList);
            
    }
    
    public IRoom[] getRoomsForCurrentBuilding(IBuilding building) {
        
        System.out.println("Getting rooms for building: " + building.getName());
        return building.getRooms();
    }
    
    public ISensor[] getSensorsForCurrentRoom(IRoom room) {
        
        return room.getSensors();
        
    }
    
    public IReadings[] getReadingsForBuilding(IBuilding building) {
        
        IReadings[] list = null;
                
       return list;
        
    }
    
    
    @Override
    public void injectData(IData dataLayer) {
        this.data = dataLayer;
    }

    @Override
    public void addRoom(IBuilding building, String name) {
        
        System.out.println("Add Room to building");
        building.addRoom(name);
          
    }

    @Override
    public void removeRoom(IBuilding building, IRoom room) {
        
        building.removeRoom(room);
        
    }
    
    public void addSensor(IRoom room, SensorType type) {
        
        room.addSensor(type);
        
    }
    
    public void removeSensor(IRoom room, ISensor sensor) {
        
        room.removeSensor(sensor);
        
    }
    
    public void startMonitoring() {
        
        System.out.println("Start monitoring");
        this.timer = new Timer();
        this.task = new TimerTask() {
            public void run() {
                
                System.out.println("found "+ buildings.size() +" buildings");
                for(IBuilding building: buildings) {
                    System.out.println("found "+building.getRooms().length +" rooms");
                    for(IRoom room: building.getRooms()) {
                        System.out.println("found "+room.getSensors().length +" sensors");
                        for(ISensor sensor: room.getSensors()) {
                            sensor.makeReading(senserGroupeID);
                        }
                    }
                }
                senserGroupeID++;
            }
        };
        
        this.timer.scheduleAtFixedRate(this.task, 10000, 10000);
      
    }
    
    public void pauseTimer() {
        
        System.out.println("Pausing timers!");
        timer.cancel();
       
    }
    
    
}
