package service;

import java.util.Scanner;
import models.User;

public class Registration {
    Scanner sc = new Scanner(System.in);
    public User[] users = new User[100];
    public int userCount = 0;

    public void register() 
    {
        String name = "";
        while (name.isEmpty()) 
        {
            System.out.print("Enter Your Name: ");
            name = sc.nextLine();
            if (name.isEmpty()) 
            	System.out.println("❌ Name cannot be empty.");
        }

        String email = "";
        boolean validEmail = false;
        while (!validEmail) 
        {
            System.out.print("Enter Your Email: ");
            email = sc.nextLine();
            if (email.endsWith("@gmail.com")) 
            {
                boolean duplicate = false;
                for (int i = 0; i < userCount; i++) 
                {
                    if (users[i].getEmail().equalsIgnoreCase(email)) 
                    {
                        duplicate = true;
                        break;
                    }
                }
                if (!duplicate) validEmail = true;
                else System.out.println("❌ Email already exists.");
            } 
            else 
            {
                System.out.println("❌ Invalid Email format.");
            }
        }

        long phNumber = 0;
        boolean validPhone = false;
        while (!validPhone) 
        {
            System.out.print("Enter Mobile Number: ");
            String s = sc.nextLine();
            if (s.length() == 10 && "6789".indexOf(s.charAt(0)) != -1) 
            {
                try
                {
                    phNumber = Long.parseLong(s);
                    validPhone = true;
                } catch (NumberFormatException e)
                {
                    System.out.println("❌ Must be digits only.");
                }
            } 
            else 
            {
                System.out.println("❌ Invalid Phone Number.");
            }
        }

        String password = "";
        boolean validPassword = false;
        while (!validPassword)
        {
            System.out.print("Enter Password: ");
            password = sc.nextLine();

            boolean checkLength = password.length() >= 8;
            boolean hasCapital = false, hasSmall = false, hasNumber = false, hasSpecial = false;

            for (char ch : password.toCharArray())
            {
                if (Character.isUpperCase(ch)) hasCapital = true;
                if (Character.isLowerCase(ch)) hasSmall = true;
                if (Character.isDigit(ch)) hasNumber = true;
                if ("@#$%&*!?_".indexOf(ch) != -1) hasSpecial = true;
            }

            if (checkLength && hasCapital && hasSmall && hasNumber && hasSpecial) 
            {
                validPassword = true;
            } 
            else 
            {
                System.out.println("❌ Invalid Password. Must contain:");
                System.out.println("- Min 8 characters");
                System.out.println("- Uppercase, lowercase, number, special char");
            }
        }

        String userId = "U" + (userCount + 101);

        users[userCount] = new User(userId, name, email, phNumber, password);
        userCount++;

        System.out.println("✅ Registration Successful! User ID: " + userId);
    }

    public void displayAllUsers() 
    {
        for (int i = 0; i < userCount; i++) 
        {
            users[i].display();
            System.out.println("----------------------");
        }
    }
}
