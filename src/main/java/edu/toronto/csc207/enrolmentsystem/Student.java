package edu.toronto.csc207.enrolmentsystem;

//import com.vladmihalcea.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
//import org.hibernate.annotations.TypeDef;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

/**
 * Represents a Student in the system. Notice that we've modified this only trivially to be able to map this entity to a table in a database.
 */
@Entity
@Table(name = "students")
// We've defined a custom type in order to save ArrayLists to the database - this is only possible with certain Databases, like Postgres.
//@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class Student {
    
    //private Class<? extends UserType<?>> customUserType = new CustomUserType();
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "student_info", columnDefinition = "text[]")
    //@Type(type = "list-array")
    private List<String> studentInfo;

    @Column(name = "courses", columnDefinition = "text[]")
    //@Type(type = "list-array")
    private List<String> courses;
 
    public Student() {}

    public Long getId() {
        return id;
    }

    @Deprecated(since = "we will not be manually setting an ID, as Spring Boot will manage these IDs to ensure they match the database")
    private void setId(Long id) {
        this.id = id;
    }

    /**
     * Constructs an instance of Student based on Strings of information
     * @param info all information in the Student's record
     */
    public Student(List<String> info) {
        studentInfo = info;
        courses = new ArrayList<>();
    }

    public List<String> getCourses() {
        return courses;
    }

    public List<String> getStudentInfo() {
        return studentInfo;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    /**
     * Adds the course code of the parameter to this Student's list of Courses
     * @param c course to add to this Student's list
     */
    public void enrol(Course c) {
        courses.add(c.getCourseCode());
    }

    /**
     * Represents the current Student by their name and student number
     * @return the name and student number separated by a comma
     */
    public String toString() {
        return studentInfo.get(0) + ", " + studentInfo.get(1);
    }
}

class CustomUserType implements UserType    
{

    @Override
    public int getSqlType() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Class returnedClass() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean equals(Object j,
            Object j1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int hashCode(Object j) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object nullSafeGet(ResultSet rs,
            int i,
            SharedSessionContractImplementor ssci,
            Object o)
            throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void nullSafeSet(PreparedStatement ps,
            Object j,
            int i,
            SharedSessionContractImplementor ssci)
            throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object deepCopy(Object j) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isMutable() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Serializable disassemble(Object j) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object assemble(Serializable srlzbl,
            Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}