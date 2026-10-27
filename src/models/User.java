package models;

import interfaces.Displayable;

public class User implements Displayable {
    private String userId;
    private String name;
    private String email;
    private String password;
    private long phNumber;

    public User(String userId, String name, String email, long phNumber, String password) 
    {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phNumber = phNumber;
        this.password = password;
    }

    public String getUserId() 
    {
    	return userId; 
    }
    public String getName()
    {
    	return name;
    }
    public String getEmail()
    {
    	return email;
    }
    public String getPassword()
    {
    	return password; 
    }
    public long getPhNumber() 
    {
    	return phNumber;
    }

    public void setEmail(String email)
    {
        if (email.endsWith("@gmail.com"))
        {
            this.email = email;
        } 
        else
        {
            System.out.println("❌ Invalid Email format.");
        }
    }

    public void setPhNumber(long phNumber) 
    {
        String s = Long.toString(phNumber);
        if (s.length() == 10 && "6789".indexOf(s.charAt(0)) != -1)
        {
            this.phNumber = phNumber;
        } 
        else
        {
            System.out.println("❌ Invalid Phone Number.");
        }
    }

    @Override
    public void display() {
        System.out.println("========USER DETAILS=========");
        System.out.printf("%-12s : %s%n", "User ID", userId);
        System.out.printf("%-12s : %s%n", "Name", name);
        System.out.printf("%-12s : %s%n", "Email", email);
        System.out.printf("%-12s : %s%n", "Phone", phNumber);
        System.out.printf("%-12s : %s%n", "Password", password);
    }
}
