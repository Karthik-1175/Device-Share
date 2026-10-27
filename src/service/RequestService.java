package service;

import java.util.Scanner;
import models.Device;
import models.enums.DeviceStatus;

public class RequestService 
{
    Scanner sc = new Scanner(System.in);
    DeviceService deviceService;
    Registration reg;

    public RequestService(DeviceService deviceService, Registration reg) 
    {
        this.deviceService = deviceService;
        this.reg = reg;
    }

    public void requestDevice(String loggedInUserId)
    {
        if (deviceService.deviceCount == 0)
        {
            System.out.println("❌ No devices available to request.");
            return;
        }

        System.out.print("Enter Device ID to request: ");
        String deviceId = sc.nextLine();

        boolean found = false;
        for (int i = 0; i < deviceService.deviceCount; i++)
        {
            Device d = deviceService.devices[i];
            if (d.getDeviceId().equalsIgnoreCase(deviceId))
            {
                found = true;

                if (d.getOwnerId().equalsIgnoreCase(loggedInUserId))
                {
                    System.out.println("❌ You cannot request your own device.");
                    break;
                }

                if (d.getStatus() == DeviceStatus.AVAILABLE)
                {
                    d.setStatus(DeviceStatus.REQUESTED);
                    d.setRequestedBy(loggedInUserId);
                    System.out.println("✅ Request placed successfully for Device ID: " + deviceId);
                } 
                else
                {
                    System.out.println("❌ Device is not available. Current status: " + d.getStatus());
                }
                break;
            }
        }
        if (!found) 
        {
            System.out.println("❌ Device ID not found.");
        }
    }

    public void viewRequests(String loggedInUserId)
    {
        boolean found = false;
        System.out.println("\n===== Pending Requests =====");
        for (int i = 0; i < deviceService.deviceCount; i++)
        {
            Device d = deviceService.devices[i];
            if (d.getOwnerId().equalsIgnoreCase(loggedInUserId) &&
                d.getStatus() == DeviceStatus.REQUESTED) {
                found = true;
                System.out.println("Device ID: " + d.getDeviceId() +
                                   " | Name: " + d.getDeviceName() +
                                   " | Requested By: " + d.getRequestedBy());
            }
        }
        if (!found) {
            System.out.println("❌ No pending requests for your devices.");
        }
    }

    public void handleRequest(String loggedInUserId) {
        boolean hasRequests = false;

        System.out.println("\n===== Pending Requests =====");
        for (int i = 0; i < deviceService.deviceCount; i++) {
            Device d = deviceService.devices[i];
            if (d.getOwnerId().equalsIgnoreCase(loggedInUserId) &&
                d.getStatus() == DeviceStatus.REQUESTED) {
                hasRequests = true;
                System.out.println("Device ID: " + d.getDeviceId() +
                                   " | Name: " + d.getDeviceName() +
                                   " | Requested By: " + d.getRequestedBy());
            }
        }

        if (!hasRequests) {
            System.out.println("❌ No pending requests for your devices.");
            return;
        }

        System.out.print("\nEnter Device ID to approve/reject: ");
        String deviceId = sc.nextLine();

        boolean found = false;
        for (int i = 0; i < deviceService.deviceCount; i++) {
            Device d = deviceService.devices[i];
            if (d.getOwnerId().equalsIgnoreCase(loggedInUserId) &&
                d.getDeviceId().equalsIgnoreCase(deviceId) &&
                d.getStatus() == DeviceStatus.REQUESTED) {
                found = true;

                System.out.println("1 - Approve");
                System.out.println("2 - Reject");
                System.out.print("Enter choice: ");
                int choice = Integer.parseInt(sc.nextLine());

                if (choice == 1) {
                    d.setStatus(DeviceStatus.BORROWED);
                    System.out.println("✅ Request approved for Device ID: " + deviceId);
                } else if (choice == 2) {
                    d.setStatus(DeviceStatus.AVAILABLE);
                    d.setRequestedBy(null);
                    System.out.println("❌ Request rejected for Device ID: " + deviceId);
                } else {
                    System.out.println("❌ Invalid choice.");
                }
                break;
            }
        }
        if (!found) {
            System.out.println("❌ No matching request found.");
        }
    }


    public void returnDevice(String loggedInUserId) {
        boolean found = false;
        for (int i = 0; i < deviceService.deviceCount; i++) {
            Device d = deviceService.devices[i];
            if (d.getRequestedBy() != null &&
                d.getRequestedBy().equalsIgnoreCase(loggedInUserId) &&
                d.getStatus() == DeviceStatus.BORROWED) {
                found = true;
                d.setStatus(DeviceStatus.AVAILABLE);
                d.setRequestedBy(null);
                System.out.println("✅ Device returned successfully: " + d.getDeviceId());
            }
        }
        if (!found) {
            System.out.println("❌ No borrowed devices found for you.");
        }
    }
}
