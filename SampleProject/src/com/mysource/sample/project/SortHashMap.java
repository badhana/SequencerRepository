package com.mysource.sample.project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/*
 * Sort HashMap that contains Student object
 */

public class SortHashMap implements Comparator<Student>
{
    public static void main(String[] args)
    {
        Map<String,Student> map = new HashMap<String,Student>();
        map.put("s1", new Student(5,"utpal"));
        map.put("s2", new Student(4,"ramesh"));
        map.put("s3", new Student(10,"tushar"));
        map.put("s4", new Student(2,"anindya"));
        Collection<Student> students = map.values();
        List<Student> list = new ArrayList<Student>(students);
        Collections.sort(list,new SortHashMap());

        for (Iterator<Student> it = list.iterator(); it.hasNext();) 
        {         
            Student stdn = (Student)it.next();             
            System.out.println("Student id : "+stdn.id);
            System.out.println("Student Name : "+stdn.name);            
        } 
    }
    @Override
    public int compare(Student s1, Student s2) 
    {
        return s1.name.compareTo(s2.name);
    }
}

class Student 
{    
    int id;
    String name;
    Student(int id,String name)
    {
        this.id = id;
        this.name = name;
    }    
}