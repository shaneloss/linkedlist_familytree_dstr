/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dstr_linkedlist;

import java.util.Scanner;
/**
 *
 * @author GP62 6QF
 */
public class DSTR_LinkedList 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Scanner inp=new Scanner(System.in);
        FamilyTree ft=new FamilyTree();
        Person First=null;
        String option;
        int opt;

        
        First=ft.readTextFile();
        if(First==null)
        {
            ft.createFamilyTree();
        }
        
        while(true)
        {
            First=ft.readTextFile();
            
            System.out.print("\nFAMILY TREE\n\n1. View Relationship\n2. Add Family Members\n0. Exit\nAnswer: ");
            option=inp.next();
            try
            {
                opt=Integer.parseInt(option);
                if(opt==0)
                {
                    System.out.println("\nExiting...");
                    break;
                }
                else if(opt==1)
                {
                    ft.choice1();
                }
                else if(opt==2)
                {
                    ft.choice2();
                }
                else
                {
                    System.out.println("\nInvalid Input\nPlease try again");
                }
            }
            catch(Exception e)
            {
                System.out.println("\nInvalid Input\nPlease try again");
            }
        }
    }
    
}
