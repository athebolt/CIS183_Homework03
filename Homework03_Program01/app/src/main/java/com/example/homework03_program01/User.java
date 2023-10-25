package com.example.homework03_program01;

import java.io.Serializable;

public class User implements Serializable
{
    String fName;
    String lName;
    String email;
    String uName;
    String pass;
    String age;

    User()
    {

    }

    User(String f, String l, String e, String u, String p, String a)
    {
        fName = f;
        lName = l;
        email = e;
        uName = u;
        pass = p;
        age = a;
    }



    public String getfName()
    {
        return fName;
    }

    public void setfName(String f)
    {
        fName = f;
    }

    public String getlName()
    {
        return lName;
    }

    public void setlName(String l)
    {
        lName = l;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String e)
    {
        email = e;
    }

    public String getuName()
    {
        return uName;
    }

    public void setuName(String u)
    {
        uName = u;
    }

    public String getPass()
    {
        return pass;
    }

    public void setPass(String p)
    {
        pass = p;
    }

    public String getAge()
    {
        return age;
    }

    public void setAge(String a)
    {
        age = a;
    }
}
