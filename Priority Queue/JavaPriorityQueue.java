/* There are a number of students in a school who wait to be served. Two types of events, 
ENTER and SERVED, can take place which are described below.
ENTER: A student with some priority enters the queue to be served.
SERVED: The student with the highest priority is served (removed) from the queue.

A unique id is assigned to each student entering the queue. The queue serves the students 
based on the following criteria (priority criteria):
1. The student having the highest Cumulative Grade Point Average (CGPA) is served first.
2. Any students having the same CGPA will be served by name in ascending case-sensitive alphabetical order.
3. Any students having the same CGPA and name will be served in ascending order of the id. */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Comparator;


class Student {
    private String name;
    private double cgpa;
    private int id; 

    public Student(String name, double cgpa, int id) {
        this.name = name;
        this.cgpa = cgpa; 
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public double getCGPA() {
        return cgpa;
    }

    public int getID() {
        return id;
    }

}

class StudentComparator implements Comparator<Student>{ 
              
    // override compare method of Comparator for descending order of cgpa 
    public int compare(Student a, Student b) { 
        if (a.getCGPA() < b.getCGPA()) 
            return 1; 
        else if (a.getCGPA() > b.getCGPA()) 
            return -1; 
        else
            return 0;
    } 
} 


class Priorities {
    
    public List<Student> getStudents(List<String> events) {
        
        // check if number of events meets constraints
        if (events.size() < 2 || events.size() > 1000) return null;
        
        PriorityQueue<Student> pq = new PriorityQueue<Student>(11, new StudentComparator());

        // loop over events in list
        for (int i = 0; i < events.size(); i++) { 
            String s = events.get(i);
            String [] arrOfStrings = s.split(" ");
            // store student details in student object 
            if (arrOfStrings[0].equals("ENTER")) {
                String name = arrOfStrings[1];
                double cgpa = Double.parseDouble(arrOfStrings[2]);
                int id = Integer.parseInt(arrOfStrings[3]);
                
                // check input conditions for constraints
                if (name.length() < 2 || name.length() > 30) return null;
                if (cgpa < 0.0 || cgpa > 4.0) return null;
                if (id < 1 || id > 100000) return null;
                
                // store students in priority queue 
                Student student = new Student(name, cgpa, id);  
                pq.add(student);
            } else if (arrOfStrings[0].equals("SERVED")) {
                pq.poll();
            }
        }

        // put values of priority queue into array in sorted order
        List<Student> studentList = new ArrayList<Student>();
        int i = 0;
        while (!pq.isEmpty()) {
            studentList.add(i, pq.poll());
            i++;
        }

        return studentList;
    }
}


public class JavaPriorityQueue {

    private final static Scanner scan = new Scanner(System.in);
    private final static Priorities priorities = new Priorities();
    
    public static void main(String[] args) {
        int totalEvents = Integer.parseInt(scan.nextLine()); // number of events 
        List<String> events = new ArrayList<>();
        
        while (totalEvents-- != 0) {
            String event = scan.nextLine();
            events.add(event);
        }
        
        List<Student> students = priorities.getStudents(events);
        
        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student st: students) {
                System.out.println(st.getName());
            }
        }
    }
}
