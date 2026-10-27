package main;

import java.util.Scanner;
import service.Registration;
import service.Login;
import service.DeviceService;
import service.RequestService;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Registration reg = new Registration();
        Login login = new Login();
        DeviceService deviceService = new DeviceService(reg);
        RequestService requestService = new RequestService(deviceService, reg);

        String loggedInUserId = null;
        boolean exit = false;
        boolean start = false;
        if(!start)
        {
        		System.out.println("============================================================");
        	    System.out.println("============================================================");
        	    System.out.println("	██████╗ ███████╗██╗   ██╗██╗ ██████╗███████╗");
        	    System.out.println("	██╔══██╗██╔════╝██║   ██║██║██╔════╝██╔════╝");
        	    System.out.println("	██║  ██║█████╗  ██║   ██║██║██║     █████╗");
        	    System.out.println("	██║  ██║██╔══╝  ╚██╗ ██╔╝██║██║     ██╔══╝");
        	    System.out.println("	██████╔╝███████╗ ╚████╔╝ ██║╚██████╗███████╗");
        	    System.out.println("	╚═════╝ ╚══════╝  ╚═══╝  ╚═╝ ╚═════╝╚══════╝");

        	    System.out.println();

        	    System.out.println("        ███████╗██╗  ██╗ █████╗ ██████╗ ███████╗");
        	    System.out.println("        ██╔════╝██║  ██║██╔══██╗██╔══██╗██╔════╝");
        	    System.out.println("        ███████╗███████║███████║██████╔╝█████╗");
        	    System.out.println("        ╚════██║██╔══██║██╔══██║██╔══██╗██╔══╝");
        	    System.out.println("        ███████║██║  ██║██║  ██║██║  ██║███████╗");
        	    System.out.println("        ╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝");

        	    System.out.println();
        	    System.out.println("============================================================");
        	    System.out.println("===============   DEVICE SHARE SYSTEM   ====================");
        	    System.out.println("============================================================");
        	    start = true;
        }

        while (!exit) {
            try {
                if (loggedInUserId == null) {
                    System.out.println("\n===== DEVICE SHARE MENU =====");
                    System.out.println("1 - Register");
                    System.out.println("2 - Login");
                    System.out.println("3 - Exit");
                    System.out.print("Enter choice: ");

                    int choice = Integer.parseInt(sc.nextLine());

                    switch (choice) {
                        case 1:
                            reg.register();
                            break;

                        case 2:
                            loggedInUserId = login.login(reg);
                            break;

                        case 3:
                            exit = true;
                            System.out.println();
                            System.out.println("███████╗██╗  ██╗██╗████████╗");
                            System.out.println("██╔════╝╚██╗██╔╝██║╚══██╔══╝");
                            System.out.println("█████╗   ╚███╔╝ ██║   ██║   ");
                            System.out.println("██╔══╝   ██╔██╗ ██║   ██║   ");
                            System.out.println("███████╗██╔╝ ██╗██║   ██║   ");
                            System.out.println("╚══════╝╚═╝  ╚═╝╚═╝   ╚═╝   ");
                            System.out.println();
                            break;

                        default:
                            System.out.println("❌ Invalid choice. Try again.");
                    }
                } else {
                    System.out.println("\n===== DEVICE OPERATIONS =====");
                    System.out.println("1 - Add Device");
                    System.out.println("2 - Display All Devices");
                    System.out.println("3 - Request Device");
                    System.out.println("4 - View Requests");
                    System.out.println("5 - Handle Request");
                    System.out.println("6 - Return Device");
                    System.out.println("7 - Logout");
                    System.out.println("8 - Exit");
                    System.out.print("Enter choice: ");

                    int choice = Integer.parseInt(sc.nextLine());

                    switch (choice) {
                        case 1:
                            deviceService.addDevice(loggedInUserId);
                            break;

                        case 2:
                            deviceService.displayAllDevices(loggedInUserId);
                            break;

                        case 3:
                            requestService.requestDevice(loggedInUserId);
                            break;

                        case 4:
                            requestService.viewRequests(loggedInUserId);
                            break;

                        case 5:
                            requestService.handleRequest(loggedInUserId);
                            break;

                        case 6:
                            requestService.returnDevice(loggedInUserId);
                            break;

                        case 7:
                            loggedInUserId = null;
                            System.out.println("✅ Logged out successfully.");
                            break;

                        case 8:
                            exit = true;
                            System.out.println();
                            System.out.println("███████╗██╗  ██╗██╗████████╗");
                            System.out.println("██╔════╝╚██╗██╔╝██║╚══██╔══╝");
                            System.out.println("█████╗   ╚███╔╝ ██║   ██║   ");
                            System.out.println("██╔══╝   ██╔██╗ ██║   ██║   ");
                            System.out.println("███████╗██╔╝ ██╗██║   ██║   ");
                            System.out.println("╚══════╝╚═╝  ╚═╝╚═╝   ╚═╝   ");
                            System.out.println();
                            break;

                        default:
                            System.out.println("❌ Invalid choice. Try again.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid number.");
            }
        }

        sc.close();
    }
}
