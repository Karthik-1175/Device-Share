package service;

import java.util.Scanner;
import models.User;

public class Login 
{
    Scanner sc = new Scanner(System.in);

    public String login(Registration reg) 
    {
        if (reg.userCount == 0) 
        {
            System.out.println("❌ No users registered yet. Please register first.");
            return null;
        }

        String loggedInUserId = null;
        boolean loggedIn = false;

        while (!loggedIn) 
        {
            System.out.print("Enter Email: ");
            String email = sc.nextLine();
            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            for (int i = 0; i < reg.userCount; i++) {
                User u = reg.users[i];
                if (u.getEmail().equalsIgnoreCase(email) && u.getPassword().equals(password)) 
                {
                    loggedInUserId = u.getUserId();
                    loggedIn = true;
                    System.out.println("✅ Login Successful! Welcome " + u.getName());
                    break;
                }
            }

            if (!loggedIn) 
            {
                System.out.println("❌ Invalid Email or Password. Try again.");
            }
        }
        return loggedInUserId;
    }
}
