/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aquiantince;

import java.util.UUID;

/**
 *
 * @author Stephanie
 */
public interface IBuilding {
    
    
    void setName(String name);
    void setAddress(String address);
    String getName();
    String getAddress();
    Boolean addRoom(String name);
    Boolean removeRoom(IRoom room);
    IRoom[] getRooms();
    @Override
    String toString();
    
    
    
}
