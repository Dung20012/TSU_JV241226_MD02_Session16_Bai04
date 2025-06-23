package BTVN.Exericse02;

public class Student {
    private int id;
    private String fullName;
    private  String dateOfBirth;

    public Student() {
    }

    public Student(int id, String fullName, String dateOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId(){
        return  id;
    }
    public String getFullName(){
        return fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
}
