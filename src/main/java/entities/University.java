package entities;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@javax.persistence.Entity
public class University extends Entity implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;

    @OneToMany(cascade= CascadeType.ALL)
    private List<Teacher> teacherList = new ArrayList();
    @OneToMany(cascade= CascadeType.ALL)
    private List<StudentsGroupImpl> studentsGroupImplList = new ArrayList();
    @OneToMany(cascade= CascadeType.ALL)
    private List<Course> courseList = new ArrayList();

    @Transient
    private StudentsGroupImpl selectedStudentsGroupImpl;

    public void setSelectedCourse(Object selectedCourse) {
        this.selectedCourse = (Course) selectedCourse;
    }

    @Transient
    private Course selectedCourse;

    public Course getSelectedCourse() {
        return selectedCourse;
    }

    public University(String name) {
        super(name);
    }

    public University() {
    }


    public void setSelectedStudentsGroupImpl(Object selectedStudentsGroupImpl) {
        this.selectedStudentsGroupImpl = (StudentsGroupImpl) selectedStudentsGroupImpl;
    }

    public StudentsGroupImpl getSelectedStudentsGroupImpl() {
        return selectedStudentsGroupImpl;
    }

    //Groups
    //region
    public List<StudentsGroupImpl> getStudentsGroupImplList() {
        return studentsGroupImplList;
    }

    public void addGroup(StudentsGroupImpl studentsGroupImpl) {
        studentsGroupImplList.add(studentsGroupImpl);
    }

    public void addGroup(String name) {
        StudentsGroupImpl studentsGroupImpl = new StudentsGroupImpl(name);
        studentsGroupImplList.add(studentsGroupImpl);
    }

    public StudentsGroupImpl getGroupByID(int id) {
        for (StudentsGroupImpl studentsGroupImpl : this.getStudentsGroupImplList()) {
            if (studentsGroupImpl.getId() == id) {
                return studentsGroupImpl;
            }
        }
        return null;
    }

    public void removeGroup(Object groupToRemove) {
        studentsGroupImplList.remove(groupToRemove);
    }

    //endregion
    //Courses
    //region
    public List<Course> getCourseList() {
        return courseList;
    }

    public void removeCourse(Object courseToRemove) {
        courseList.remove(courseToRemove);
        for (StudentsGroupImpl studentsGroupImpl : studentsGroupImplList) {
            studentsGroupImpl.getGroupCourses().remove(courseToRemove);
        }
        for (Teacher teacher : teacherList) {
            teacher.getTeacherCourses().remove(courseToRemove);
        }
    }

    public void addCourse(Course course) {
        courseList.add(course);
    }

    public Course getCourseByID(int id) {
        Course courseMatch = null;
        for (Course course : this.getCourseList()) {
            if (course.getId() == id) {
                courseMatch = course;
                break;
            }
        }
        return courseMatch;
    }

    //endregion
    //Teachers
    // region
    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void addTeacher(Teacher teacher) {
        this.teacherList.add(teacher);
    }

    public void removeTeacher(Object teacherToRemove) {
        System.out.println("remooving");
        teacherList.remove(teacherToRemove);
    }

    public Teacher getTeacherByID(int id) {
        Teacher teacherMatch = null;
        for (Teacher teacher : this.getTeacherList()) {
            if (teacher.getId() == id) {
                teacherMatch = teacher;
                return teacherMatch;
            }
        }
        //if (teacherMatch==null) throw new IllegalArgumentException(" !entities.Teacher with this ID does not exist!");
        return null;
    }

    public void addTeacher(String name, String surname) {
        Teacher teacher = new Teacher(name, surname);
        teacherList.add(teacher);
    }

    public void removeTeacher(Teacher teacherToRemove) {
        try {
            teacherList.remove(teacherToRemove);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(" !entities.Teacher does not exist!");
        }
    }

    //endregion
    //General
    public List<Student> getAllStudents() {
        List<Student> allStudents = new ArrayList<>();
        for (StudentsGroupImpl studentsGroupImpl : studentsGroupImplList) {
            allStudents.addAll(studentsGroupImpl.getGroupStudents());
        }
        return allStudents;
    }

    public Student getStudentById(int studentId) {
        List<Student> studentList = getAllStudents();
        for (Student student : studentList) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        throw new NoSuchElementException("Student not found");
    }
}
