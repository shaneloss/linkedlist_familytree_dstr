/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dstr_linkedlist;

import java.util.ArrayList;

/**
 *
 * @author GP62 6QF
 */
public class Person 
{
    String Name;
    String Gender;
    
    Person Partner;
    Person Father;
    Person Mother;
    
    ArrayList<Person> Children=new ArrayList<>();
    ArrayList<Person> Sibling=new ArrayList<>();
    
    Person Next;
    
    public Person(String n, String g)
    {
        Name=n;
        Gender=g;
    }
}
