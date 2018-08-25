/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dstr_linkedlist;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @author GP62 6QF
 */
public class FamilyTree
{
    Person First=null; //First is always the same person
    Person Temp=null;  //Temp will change everytime
    Person Current=null;  //Current is to check if the person exist
    Person NewMember=null;     
    String NewMemberName="";
    String NewMemberGender="";
    Person Related=null;
    Scanner inp=new Scanner(System.in);
    
    String option;
    int opt;
    
    
    
    public Person readTextFile()
    {
        File fList=new File("all_member.txt");
        Scanner sc;
        File fRelation=new File("all_relationship.txt");
        try
        {
            FileWriter fwr=new FileWriter(fRelation,true);
            fwr=new FileWriter(fList, true);
        }
        catch(Exception e){}
        
        Scanner scan;

        try
        {
            sc=new Scanner(fList);
            String Line=sc.nextLine();
            String nameGender[]=Line.split(";");
            First=new Person(nameGender[0],nameGender[1]);
            Person Temp=First;
            
            Person Current=null;
            Person Related=null;

            while(sc.hasNextLine())
            {
                Line=sc.nextLine();
                String details[]=Line.split(";");
                
                Temp.Next=new Person(details[0],details[1]);
                
                Temp=Temp.Next;
            }
            sc.close();
            
            scan=new Scanner(fRelation);
            while(scan.hasNextLine())
            {                
                Line=scan.nextLine();
                String relation[]=Line.split(";");
                Current=findPerson(relation[0]);
                Related=findPerson(relation[2]);
                if(relation[1].equals("Father"))
                {
                    Current.Father=Related;
                }
                if(relation[1].equals("Mother"))
                {
                    Current.Mother=Related;
                }
                if(relation[1].equals("Partner"))
                {
                    Current.Partner=Related;
                }
                if(relation[1].equals("Child"))
                {
                    Current.Children.add(Related);
                }
                if(relation[1].equals("Sibling"))
                {
                    Current.Sibling.add(Related);
                }
            }
            return First;
        }
        catch(Exception e)
        {
            return null;
        }
    }
    
    
    public void createFamilyTree()
    {
        Person firstPerson;
        String firstPersonName;
        String firstPersonGender;
        while(true)
        {
            System.out.println("There is no Family Tree yet");
            System.out.print("\nCreating First Member of the Family Tree\nName: ");
            option=inp.next();
            firstPersonName=option;
            while(true)
            {
                System.out.print("\n"+firstPersonName+"'s Gender:\n1. Male\n2. Female\nAnswer: ");
                option=inp.next();
                try
                {
                    opt=Integer.parseInt(option);
                    if(opt==1)
                    {
                        firstPersonGender="male";
                        break;
                    }
                    else if(opt==2)
                    {
                        firstPersonGender="female";
                        break;
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
            while(true)
            {
                System.out.print("\nCreating "+firstPersonName+" ("+firstPersonGender+")\n1. Confirm\n2. Cancel\nAnswer: ");
                option=inp.next();
                try
                {
                    opt=Integer.parseInt(option);
                    if(opt==1||opt==2)
                    {
                        break;
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
            if(opt==1)
            {
                System.out.println("\n"+firstPersonName+" ("+firstPersonGender+") has been added");
                break;
            }
            if(opt==2)
            {
                System.out.println("\n"+firstPersonName+" ("+firstPersonGender+") wasn't added\n");
            }
            
        }
        
        firstPerson=new Person(firstPersonName,firstPersonGender);
        writeToMemberList(firstPerson);        
    }
    
    
    public void choice1()
    {
        while(true)
        {
            System.out.println("\nAll Family Member\n");
            showAllMember();
            System.out.print("\n1. View Parents\n2. View Children\n3. Check if Siblings?\n4. View Ancestors\n5. View all Relationship\n0. go Back\nAnswer: ");
            option=inp.next();
            if(option.equals("0"))
            {
                break;
            }

            else if(option.equals("1"))
            {
                while(true)
                {
                    System.out.print("\nEnter a Name to see the Parents \nor enter 0 to cancel\nName: ");
                    option=inp.next();
                    if(option.equals("0"))
                    {
                        break;
                    }
                    Current=findPerson(option);
                    if(Current==null)
                    {
                        System.out.println("\n"+option+" is not part of the Family Tree\nPlease try again");
                    }
                    else
                    {
                        showParent(Current);
                        break;
                    }   
                }
                 
            }  
            else if(option.equals("2"))
            {
                while(true)
                {
                    System.out.print("\nEnter a Name to see the Children \nor enter 0 to cancel\nName: ");
                    option=inp.next();
                    if(option.equals("0"))
                    {
                        break;
                    }
                    Current=findPerson(option);
                    if(Current==null)
                    {
                        System.out.println("\n"+option+" is not part of the Family Tree\nPlease try again");
                    }
                    else
                    {
                        showChildren(Current);
                        break;
                    } 
                }
                    
            }
            else if(option.equals("3"))
            {
                while(true)
                {
                    System.out.print("\nEnter 1st Person Name \nor enter 0 to cancel\nName: ");
                    option=inp.next();
                    if(option.equals("0"))
                    {
                        break;
                    }
                    Current=findPerson(option);
                    if(Current==null)
                    {
                        System.out.println("\n"+option+" is not part of the Family Tree\nPlease try again");
                    }
                    else
                    {
                        while(true)
                        {
                            System.out.print("\nEnter 2nd Person Name \nor enter 0 to cancel\nName: ");
                            option=inp.next();
                            if(option.equals("0"))
                            {
                                break;
                            }
                            Person Current2=findPerson(option);
                            if(Current2==null)
                            {
                                System.out.println("\n"+option+" is not part of the Family Tree\nPlease try again");
                            }
                            else
                            {
                                System.out.println("");
                                int exit=0;
                                if(!Current.Sibling.isEmpty())
                                {
                                    int siblingQty=Current.Sibling.size();
                                    for(int i=0;i<siblingQty;i++)
                                    {
                                        if(Current.Sibling.get(i)==Current2)
                                        {
                                            System.out.println(Current.Name+" and "+Current2.Name+" are Siblings");
                                            exit=1;
                                            break;
                                        }
                                    }
                                    if(exit==0)
                                    {
                                        System.out.println(Current.Name+" and "+Current2.Name+" are not Siblings");
                                    }

                                }
                                else
                                {
                                    System.out.println(Current.Name+" and "+Current2.Name+" are not Siblings");
                                }
                                break;
                            }
                        } 
                        break;
                    }  
                } 
            }
            else if(option.equals("4"))
            {
                while(true)
                {
                    System.out.print("\nEnter a Name to see the Ancestors \nor enter 0 to cancel\nName: ");
                    option=inp.next();
                    if(option.equals("0"))
                    {
                        break;
                    }
                    Current=findPerson(option);
                    if(Current==null)
                    {
                        System.out.println("\n"+option+" is not part of the Family Tree\nPlease try again");
                    }
                    else
                    {
                        viewAncestor(Current);
                        break;
                    }  
                }
                  
            }
            else if(option.equals("5"))
            {
                while(true)
                {
                    System.out.print("\nEnter a Name to see all Relationship \nor enter 0 to cancel\nName: ");
                    option=inp.next();
                    if(option.equals("0"))
                    {
                        break;
                    }
                    Current=findPerson(option);
                    if(Current==null)
                    {
                        System.out.println("\n"+option+" is not part of the Family Tree\nPlease try again");
                    }
                    else
                    {
                        showRelationship(Current);
                        break;
                    }  
                }
                    
            }
            else
            {
                System.out.println("\nInvalid Input\nPlease try again");
            }                           
        } 
    }
    
    
    public void choice2()
    {

        System.out.print("\nEnter the Name of the new Family Member\nor Enter 0 to go Back\nAnswer: ");
        option=inp.next();
        if(option.equals("0"))
        {

        }
        else
        {
            NewMember=findPerson(option);
            if(NewMember==null)
            {
                NewMemberName=option;
                while(true)
                {
                    System.out.print("\n"+NewMemberName+"'s Gender\n1. Male\n2. Female\nAnswer: ");
                    option=inp.next();
                    try
                    {
                        opt=Integer.parseInt(option);
                        if(opt==1||opt==2)
                        {
                            if(opt==1)
                            {
                                NewMemberGender="male";
                            }
                            else if(opt==2)
                            {
                                NewMemberGender="female";
                            }
                            NewMember=new Person(NewMemberName,NewMemberGender);
                            
                            while(true)
                            {
                                System.out.print("\n"+NewMember.Name+" is related to who?\nEnter (1) Name\nor Enter 0 to Cancel\nAnswer: ");
                                option=inp.next();
                                if(option.equals("0"))
                                {
                                    System.out.println("/n"+NewMember.Name+" wasn't add to the Family Tree");
                                    NewMember=null;
                                    break;
                                }
                                else
                                {
                                    Related=findPerson(option);
                                    if(Related==null)
                                    {
                                        System.out.println("\n"+option+" is not a Family Member\nPlease try again\n");
                                    }
                                    else
                                    {
                                        while(true)
                                        {
                                            System.out.println("\nWhat is the relationship between "+NewMember.Name+" and "+Related.Name+"\n"+NewMember.Name+" is the ____ of "+Related.Name);
                                            if(NewMember.Gender.equals("male"))
                                            {
                                                System.out.print("1. Father\n2. Husband\n3. Son\n4. Brother\nEnter 0 to Cancel\nAnswer: ");
                                            }
                                            else
                                            {
                                                System.out.print("1. Mother\n2. Wife\n3. Daughter\n4. Sister\nEnter 0 to Cancel\nAnswer: ");
                                            }
                                            option=inp.next();
                                            try
                                            {
                                                opt=Integer.parseInt(option);
                                                if(opt==0)
                                                {
                                                    break;
                                                }
                                                else if(opt==1||opt==2||opt==3||opt==4)
                                                {
                                                    if(NewMember.Gender.equals("male"))
                                                    {
                                                        if(opt==1)
                                                        {
                                                            //NewMember is the Father of Related
                                                            if(Related.Father==null)
                                                            {
                                                                NewMember.Children.add(Related);
                                                            }
                                                            else
                                                            {
                                                                System.out.println("\n"+Related.Name+" already have a Father ("+Related.Father.Name+")\n"+NewMember.Name+" wasn't added to the Family Tree\nPlease try again\n");
                                                                break;
                                                            }
                                                        }
                                                        
                                                        if(opt==2)
                                                        {
                                                            //NewMember is the Husband of Related
                                                            if(Related.Gender.equals("male"))
                                                            {
                                                                System.out.println("\n"+NewMember.Name+" and "+Related.Name+" are both Male\nThis Family Tree require 1 Father and 1 Mother\n"+NewMember.Name+" wasn't added to the Family Tree\nPlease try again\n");
                                                                break;
                                                            } 
                                                            else
                                                            {
                                                                if(Related.Partner==null)
                                                                {
                                                                    NewMember.Partner=Related;
                                                                }
                                                                else
                                                                {
                                                                    System.out.println("\n"+Related.Name+" already have a Husband ("+Related.Partner.Name+")\n"+NewMember.Name+" wasn't added to the Family Tree\nPlease try again\n");
                                                                    break;
                                                                }
                                                            }
                                                            
                                                                    
                                                        }
                                                        
                                                        if(opt==3)
                                                        {
                                                            //NewMember is the Son of Related
                                                            if(Related.Gender.equals("male"))
                                                            {
                                                                //Related is male
                                                                NewMember.Father=Related;
                                                            }
                                                            else
                                                            {
                                                                //Related is female
                                                                NewMember.Mother=Related;
                                                            }
                                                        }
                                                        
                                                        if(opt==4)
                                                        {
                                                            //NewMember is the Brother of Related
                                                            NewMember.Sibling.add(Related);
                                                        }
                                                        
                                                    }
                                                    else
                                                    {
                                                        if(opt==1)
                                                        {
                                                            //NewMember is the Mother of Related
                                                            if(Related.Mother==null)
                                                            {
                                                                NewMember.Children.add(Related);
                                                            }
                                                            else
                                                            {
                                                                System.out.println("\n"+Related.Name+" already have a Mother ("+Related.Mother.Name+")\n"+NewMember.Name+" wasn't added to the Family Tree\nPlease try again\n");
                                                                break;
                                                            }
                                                        }
                                                        if(opt==2)
                                                        {
                                                            //NewMember is the Wife of Related
                                                            if(Related.Gender.equals("female"))
                                                            {
                                                                System.out.println("\n"+NewMember.Name+" and "+Related.Name+" are both Female\nThis Family Tree require 1 Father and 1 Mother\n"+NewMember.Name+" wasn't added to the Family Tree\nPlease try again\n");
                                                                break;
                                                            } 
                                                            else
                                                            {
                                                                if(Related.Partner==null)
                                                                {
                                                                    NewMember.Partner=Related;
                                                                    
                                                                }
                                                                else
                                                                {
                                                                    System.out.println("\n"+Related.Name+" already have a Wife("+Related.Partner.Name+")\n"+NewMember.Name+" wasn't added to the Family Tree\nPlease try again\n");
                                                                    break;
                                                                }
                                                            }
         
                                                        }
                                                        
                                                        if(opt==3)
                                                        {
                                                            //NewMember is the Daughter of Related
                                                            if(Related.Gender.equals("male"))
                                                            {
                                                                //Related is male
                                                                NewMember.Father=Related;
                                                                
                                                            }
                                                            else
                                                            {
                                                                //Related is female
                                                                NewMember.Mother=Related;
                                                            }
                                                        }
                                                        
                                                        if(opt==4)
                                                        {
                                                            //NewMember is the Sister of Related
                                                            NewMember.Sibling.add(Related);
                                                        }
                                                        
                                                    }
                                                    System.out.println("");
                                                    
                                                    newMemberRelationship(NewMember);
                                                    showRelationship(NewMember);
                                                    
                                                    while(true)
                                                    {
                                                        System.out.print("\nDo you want to add "+NewMember.Name+" to the Family Tree (Family Member cannot be deleted)\n"
                                                                + "1. Add "+NewMember.Name+" to the Family Tree\n2. Cancel\nAnswer: ");
                                                        option=inp.next();
                                                        try
                                                        {
                                                            opt=Integer.parseInt(option);
                                                            if(opt==1)
                                                            {
                                                                //need to add the code to add the new member
                                                                addNewMember(NewMember);
                                                                System.out.println("\n"+NewMember.Name+" has been added to the Family Tree");
                                                                break;
                                                            }
                                                            else if(opt==2)
                                                            {
                                                                System.out.println("\n"+NewMember.Name+" wasn't added to the Family Tree");
                                                                break;
                                                            }
                                                            else
                                                            {
                                                                System.out.println("\nInvalis Input\nPlease try again");
                                                            }
                                                        }
                                                        catch(Exception e)
                                                        {
                                                            System.out.println("\nInvalid Input\nPlease try again");
                                                        }
                                                    }
                                                    break;
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

                                        break;
                                    }
                                }
                            }
                            break;
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
            else
            {
                System.out.println("\nSorry, "+NewMember.Name+" ("+NewMember.Gender+") is already part of the Family Tree\nPlease try again");
            }
        }            
    }
    
    
    public Person findPerson(String s)
    {
        String nameToSearch=s;
        Temp=First;
        
        while(Temp!=null)
        {
            if(Temp.Name.equals(nameToSearch))
            {
                return Temp;
            }
            else
            {
                Temp=Temp.Next;
            }
        }
        return null;
    }
    
    
    public void showAllMember()
    {
        Temp=First;
        while(Temp!=null)
        {
            System.out.println(Temp.Name);
            Temp=Temp.Next;
        }
    }
    
    public void showParent(Person p)
    {
        System.out.println("");
        Temp=p;
        if(Temp.Father!=null)
        {
            System.out.println(Temp.Name+"'s Father is "+Temp.Father.Name);
        }
        else
        {
            System.out.println(Temp.Name+"'s Father is not part of the Family Tree");
        }
        if(Temp.Mother!=null)
        {
            System.out.println(Temp.Name+"'s Mother is "+Temp.Mother.Name);
        }
        else
        {
            System.out.println(Temp.Name+"'s Mother is not part of the Family Tree");
        }
    }
    
    public void showChildren(Person p)
    {
        System.out.println("");
        Temp=p;
        if(!Temp.Children.isEmpty())
        {
            int childrenQty=Temp.Children.size();
            
            if(childrenQty==1)
            {
                System.out.println(Temp.Name+"'s Child is "+Temp.Children.get(0).Name);
            }
            else
            {
                System.out.print(Temp.Name+"'s Children are ");
                for(int i=0;i<childrenQty;i++)
                {
                    System.out.print(Temp.Children.get(i).Name);
                    if(i==childrenQty-2)
                    {
                        System.out.print(" and ");
                    }
                    else if(i<childrenQty-2)
                    {
                        System.out.print(", ");
                    }
                }
            }
        }
        else
        {
            System.out.println(Temp.Name+" doesn't have any child (in the Family Tree)");
        }
    }
    
    public void showSiblings(Person p)
    {
        System.out.println("");
        Temp=p;
        if(!Temp.Sibling.isEmpty())
        {
            int siblingQty=Temp.Sibling.size();
            
            if(siblingQty==1)
            {
                if(Temp.Sibling.get(0).Gender.equals("male"))
                {
                    System.out.println(Temp.Name+"'s Brother is "+Temp.Sibling.get(0).Name);
                }
                else
                {
                    System.out.println(Temp.Name+"'s Sister is "+Temp.Sibling.get(0).Name);
                }
            }
            else
            {
                System.out.print(Temp.Name+"'s Siblings are ");
                for(int i=0;i<siblingQty;i++)
                {
                    System.out.print(Temp.Sibling.get(i).Name);
                    if(i==siblingQty-2)
                    {
                        System.out.print(" and ");
                    }
                    else if(i<siblingQty-2)
                    {
                        System.out.print(", ");
                    }
                }
                System.out.println("");
            }
        }
        else
        {
            System.out.println(Temp.Name+" doesn't have any sibling (in the Family Tree)");
        }
    }
    
    public void showPartner(Person p)
    {
        System.out.println("");
        Temp=p;
        if(Temp.Partner!=null)
        {
            if(Temp.Partner.Gender.equals("male"))
            {
                System.out.println(Temp.Name+"'s Husband is "+Temp.Partner.Name);
            }
            else
            {
                System.out.println(Temp.Name+"'s Wife is "+Temp.Partner.Name);
            }                
        }
        else
        {
            System.out.println(Temp.Name+" is not married");
        }
    }
    
    public void showRelationship(Person p)
    {
        Temp=p;
        System.out.println("\n"+Temp.Name+"'s Relationship:");
        
        if(Temp==First)
        {
            if(Temp.Next==null)
            {
                System.out.println("No Relationship yet");
            }  
        }
        showParent(Temp);
        showPartner(Temp);
        showChildren(Temp);
        showSiblings(Temp);
        viewAncestor(Temp);
    }
    
    
    public void newMemberRelationship(Person p)
    {
        Person NewMember=p;
        if(NewMember.Father!=null)
        {
            NewMember.Mother=NewMember.Father.Partner;
            NewMember.Sibling=NewMember.Father.Children;
            //NewMember.Sibling.remove(NewMember); might not be need because father.children doesnt include the newmember yet
        }
        else if(NewMember.Mother!=null)
        {
            NewMember.Father=NewMember.Mother.Partner;
            NewMember.Sibling=NewMember.Mother.Children;
            //NewMember.Sibling.remove(NewMember);
        }
        else if(NewMember.Children.size()!=0)
        {
            Person chi=NewMember.Children.get(0);
            NewMember.Children=NewMember.Children.get(0).Sibling;
            NewMember.Children.add(chi);
            if(NewMember.Gender.equals("male"))
            {
                NewMember.Partner=NewMember.Children.get(0).Mother;
            }
            else
            {
                NewMember.Partner=NewMember.Children.get(0).Father;
            }
        }
        else if(NewMember.Partner!=null)
        {
            NewMember.Children=NewMember.Partner.Children;
        }
        else if(NewMember.Sibling.size()!=0)
        {
            NewMember.Father=NewMember.Sibling.get(0).Father;
            NewMember.Mother=NewMember.Sibling.get(0).Mother;
            Person s1=NewMember.Sibling.get(0);
            for(int i=0;i<s1.Sibling.size();i++)
            {
                NewMember.Sibling.add(s1.Sibling.get(i));
            }                       
        }
    }
    
    
    public void addNewMember(Person p)
    {
        NewMember=p;
        writeToMemberList(NewMember);
        //this need to be change (below)
        if(NewMember.Father!=null)
        {
            writeR(NewMember,NewMember.Father,"Father");
            writeR(NewMember.Father,NewMember,"Child");
        }
        if(NewMember.Mother!=null)
        {
            writeR(NewMember,NewMember.Mother,"Mother");
            writeR(NewMember.Mother,NewMember,"Child");
        }
        if(NewMember.Partner!=null)
        {
            writeR(NewMember,NewMember.Partner,"Partner");
            writeR(NewMember.Partner,NewMember,"Partner");
        }
        if(NewMember.Sibling.size()!=0)
        {
            int qtySibling=NewMember.Sibling.size();
            for(int i=0;i<qtySibling;i++)
            {
                writeR(NewMember,NewMember.Sibling.get(i),"Sibling");
                writeR(NewMember.Sibling.get(i),NewMember,"Sibling");
            }
        }
        if(NewMember.Children.size()!=0)
        {
            int qtyChildren=NewMember.Children.size();
            for(int i=0;i<qtyChildren;i++)
            {
                writeR(NewMember,NewMember.Children.get(i),"Child");
                if(NewMember.Gender=="male")
                {
                    writeR(NewMember.Children.get(i),NewMember,"Father");
                }
                else
                {
                    writeR(NewMember.Children.get(i),NewMember,"Mother");
                }
            }
        }          
    }
    
    public void writeToMemberList(Person p)
    {
        NewMember=p;
        File f=new File("all_member.txt");
 
        try
        {
            FileWriter fw=new FileWriter(f,true);
            fw.write(NewMember.Name+";"+NewMember.Gender+"\n");
            fw.close();
            
        }
        catch(Exception e)
        {
            
        }
    }
    
    public void writeR(Person c, Person r, String s)
    {
        Current=c;
        Related=r;
        String relationship=s;
        
        File f=new File("all_relationship.txt");
        try
        {
            FileWriter fw=new FileWriter(f,true);
            fw.write(Current.Name+";"+relationship+";"+Related.Name+"\n");
            fw.close();
        }
        catch(Exception e)
        {
            
        }
        
    }
    
    public void viewAncestor(Person p)
    {
        if(p.Father==null&&p.Mother==null)
        {
            System.out.println("\n"+p.Name+" doesn't have any Ancestors in the Family Tree");
        }
        else
        {
            System.out.println("\n"+p.Name+"'s Ancestors are:");
            viewParents(p);
        }
    }
    //this is a recusion to display the person's parents, grand parents, great grand parents...
    
    public void viewParents(Person p)
    {
        
        if(p.Father!=null)
        {
            System.out.println(p.Father.Name+" ("+p.Name+"'s Father)");
            viewParents(p.Father);
        }
        if(p.Mother!=null)
        {
            System.out.println(p.Mother.Name+" ("+p.Name+"'s Mother)");
            viewParents(p.Mother);
        }
  
    }
}
