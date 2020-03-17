/* You are given a list of student information: ID, FirstName, and CGPA. 
Your task is to rearrange them according to their CGPA in decreasing order. 
If two student have the same CGPA, then arrange them according to their 
first name in alphabetical order. If those two students also have the same 
first name, then order them according to their ID. No two students have 
the same ID. */


import java.util.*;

class Student{

	private int id;
	private String fname;
	private double cgpa;

	public Student(int id, String fname, double cgpa) {
		super();
		this.id = id;
		this.fname = fname;
		this.cgpa = cgpa;
	}

	public int getId() {
		return id;
	}

	public String getFname() {
		return fname;
	}

	public double getCgpa() {
		return cgpa;
	}
}

//Complete the code
class CompareStudents implements Comparator<Student> {

	public int compare(Student a, Student b) {
		if (a.getCgpa() < b.getCgpa()) return +1;
		if (a.getCgpa() > b.getCgpa()) return -1;
		else {
			if (a.getFname().equals(b.getFname())) {
				return a.getId() - b.getId();
			} else {
				return a.getFname().compareTo(b.getFname()); 
			}
		}    
	}
}

public class JavaSort {

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int testCases = Integer.parseInt(in.nextLine()); // number of students
		
		List<Student> studentList = new ArrayList<Student>();
		while(testCases>0){
			int id = in.nextInt(); // read in ID
			String fname = in.next(); // read in name
			double cgpa = in.nextDouble(); // read in CGPA
			
			Student st = new Student(id, fname, cgpa);
			studentList.add(st);
			
			testCases--;
		}
		CompareStudents cs = new CompareStudents();
		Collections.sort(studentList, cs);
	  
	  	for(Student st: studentList){
			System.out.println(st.getFname());
		}
	}
}
