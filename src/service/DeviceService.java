package service;

import java.util.Scanner;
import models.Device;
import models.enums.DeviceCategory;
import models.enums.DeviceCondition;
import models.enums.DeviceStatus;

public class DeviceService {
    Scanner sc = new Scanner(System.in);
    public Device[] devices = new Device[100];
    public int deviceCount = 0;
    Registration reg;

    public DeviceService(Registration reg) {
        this.reg = reg;
    }

    public void addDevice(String loggedInUserId) 
    {
        System.out.print("Enter Device Name: ");
        String deviceName = sc.nextLine();

        DeviceCategory category = null;
        while (category == null) {
            try {
                System.out.println("\n===== Select Category =====");
                System.out.println("1 - LAPTOP");
                System.out.println("2 - CAMERA");
                System.out.println("3 - PROJECTOR");
                System.out.println("4 - TABLET");
                System.out.println("5 - SPEAKER");
                System.out.println("6 - OTHER");
                System.out.println("7 - Back");
                System.out.print("Enter Your Choice: ");
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) 
                {
                    case 1: category = DeviceCategory.LAPTOP; break;
                    case 2: category = DeviceCategory.CAMERA; break;
                    case 3: category = DeviceCategory.PROJECTOR; break;
                    case 4: category = DeviceCategory.TABLET; break;
                    case 5: category = DeviceCategory.SPEAKER; break;
                    case 6: category = DeviceCategory.OTHER; break;
                    case 7: return; // go back
                    default: System.out.println("❌ Invalid choice. Try again.");
                }
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("❌ Please enter a valid number.");
            }
        }

        DeviceCondition condition = null;
        while (condition == null) 
        {
            try 
            {
                System.out.println("\n===== Select Condition =====");
                System.out.println("1 - NEW");
                System.out.println("2 - GOOD");
                System.out.println("3 - FAIR");
                System.out.println("4 - Back");
                System.out.print("Enter Your Choice: ");
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) 
                {
                    case 1: condition = DeviceCondition.NEW; break;
                    case 2: condition = DeviceCondition.GOOD; break;
                    case 3: condition = DeviceCondition.FAIR; break;
                    case 4: return; // go back
                    default: System.out.println("❌ Invalid choice. Try again.");
                }
            }
            catch (NumberFormatException e) 
            {
                System.out.println("❌ Please enter a valid number.");
            }
        }

        DeviceStatus status = DeviceStatus.AVAILABLE;

        double rentPerDay = 0;
        boolean validRent = false;
        while (!validRent) {
            System.out.print("Enter Rent Per Day: ");
            try {
                rentPerDay = Double.parseDouble(sc.nextLine());
                if (rentPerDay > 0) 
                {
                    validRent = true;
                } 
                else 
                {
                    System.out.println("❌ Rent must be positive.");
                }
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("❌ Invalid input. Enter a number.");
            }
        }

        String deviceId = "D" + (deviceCount + 201);

        String ownerId = loggedInUserId;

        devices[deviceCount] = new Device(deviceId, ownerId, deviceName,
                                          category, condition, status, rentPerDay);
        deviceCount++;

        System.out.println("✅ Device Added Successfully! Device ID: " + deviceId);
    }

    public void displayAllDevices(String loggedInUserId)
    {
        if (deviceCount == 0) 
        {
            System.out.println("❌ No devices available.");
            return;
        }

        for (int i = 0; i < deviceCount; i++) 
        {
            devices[i].display();

  
            if (devices[i].getStatus() == DeviceStatus.BORROWED &&
                loggedInUserId.equalsIgnoreCase(devices[i].getRequestedBy())) 
            {
                for (int j = 0; j < reg.userCount; j++) 
                {
                    if (reg.users[j].getUserId().equalsIgnoreCase(devices[i].getOwnerId()))
                    {
                        System.out.println("📞 Contact Owner: " + reg.users[j].getPhNumber());
                        break;
                    }
                }
            }
            System.out.println("----------------------");
        }
    }
}
