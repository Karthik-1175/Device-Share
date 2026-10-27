package models;

import interfaces.Displayable;
import models.enums.DeviceCategory;
import models.enums.DeviceCondition;
import models.enums.DeviceStatus;

public class Device implements Displayable 
{
    private String deviceId;
    private String ownerId;
    private String deviceName;
    private DeviceCategory category;
    private DeviceCondition condition;
    private DeviceStatus status;
    private String requestedBy;
    private double rentPerDay;

    public Device(String deviceId, String ownerId, String deviceName,
                  DeviceCategory category, DeviceCondition condition,
                  DeviceStatus status, double rentPerDay)
    {
        this.deviceId = deviceId;
        this.ownerId = ownerId;
        this.deviceName = deviceName;
        this.category = category;
        this.condition = condition;
        this.status = status;
        this.rentPerDay = rentPerDay;
        this.requestedBy = null;
    }

    public String getDeviceId() 
    {
    	return deviceId; 
    }
    public String getOwnerId()
    { 
    	return ownerId; 
    }
    public String getDeviceName()
    {
    	return deviceName;
    }
    public DeviceCategory getCategory()
    {
    	return category;
    }
    public DeviceCondition getCondition()
    {
    	return condition;
    }
    public DeviceStatus getStatus()
    { 
    	return status;
    }
    public String getRequestedBy()
    {
    	return requestedBy; 
    }
    public double getRentPerDay()
    {
    	return rentPerDay;
    }

    public void setStatus(DeviceStatus status)
    {
    	this.status = status;
    }
    public void setRequestedBy(String requestedBy) 
    {
    	this.requestedBy = requestedBy;
    }

    @Override
    public void display()
    {
        System.out.println("========DEVICE DETAILS=========");
        System.out.printf("%-15s : %s%n", "Device ID", deviceId);
        System.out.printf("%-15s : %s%n", "Owner ID", ownerId);
        System.out.printf("%-15s : %s%n", "Device Name", deviceName);
        System.out.printf("%-15s : %s%n", "Category", category);
        System.out.printf("%-15s : %s%n", "Condition", condition);
        System.out.printf("%-15s : %s%n", "Status", status);
        System.out.printf("%-15s : %s%n", "Rent Per Day", rentPerDay);
    }
}
