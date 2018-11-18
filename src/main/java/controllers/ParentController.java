package controllers;

import cells.CourseCell;
import cells.GroupCell;
import cells.StudentCell;
import cells.TeacherCell;
import entities.University;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Stage;
import utilities.ArrayListConverter;


import java.io.IOException;

public class ParentController extends TabPane {
    private University university;
    public Stage primaryStage;
    private String addTeacherFXML = "/TeacherAddWindow.fxml";
    private String saveTeacherFXML = "/saveTeacherWindow.fxml";
    private String addCourseFXML = "/courseAddWindow.fxml";
    private String addGroupFXML = "/groupAddWindow.fxml";
    private String addStudentFXML = "/studentAddWindow.fxml";
    private String parentFile = "/ApplicationInterface.fxml";

    @FXML
    private Button btAddTeacher;
    @FXML
    private Button btSave;
    @FXML
    private ListView teacherListView;

    @FXML
    private ListView courseListView;
    @FXML
    private Button btAddCourse;

    @FXML
    private Button btAddGroup;
    @FXML
    private ListView groupListView;
    @FXML
    private ListView studentListView;

    public ParentController(University university, Stage primaryStage) {
        this.primaryStage=primaryStage;
        this.university = university;
        setUpTheLoader();
    }

    @FXML
    private void initialize() {
        setUpTeacherWindow();
        setUpCourseWindow();

        groupListView.getItems().addAll(university.getStudentsGroupList());
        groupListView.setCellFactory(param -> {
            GroupCell cell = new GroupCell(groupListView, this, university);
            cell.createSelections();
            return cell.getCell();
        });

        studentListView.getItems().addAll(university.getAllStudents());
        studentListView.setCellFactory(param -> {
            StudentCell cell = new StudentCell(groupListView, this, university);
            cell.createSelections();
            return cell.getCell();
        });
        btAddGroup.setOnAction(event -> createGroupAddWindowController());
    }

    private void setUpCourseWindow() {
        courseListView.getItems().addAll(university.getCourseList());
        courseListView.setCellFactory(lv -> {
            CourseCell cell = new CourseCell(courseListView, this, university);
            cell.createSelections();
            return cell.getCell();
        });
        btAddCourse.setOnAction(event -> createCourseAddWindowController());
    }

    private void setUpTeacherWindow() {
        teacherListView.getItems().addAll(university.getTeacherList());
        teacherListView.setCellFactory(lv -> {
            TeacherCell cell = new TeacherCell(teacherListView, this, university);
            cell.makeDeleteOption();
            return cell.getCell();
        });
        btAddTeacher.setOnAction(event -> {
                    createTeacherAddWindowController();
                }
        );
        btSave.setOnAction(event -> {
            createTeacherSaveWindowController();
        });
    }

    private void setUpTheLoader() {
        System.out.println(getClass().getResource(parentFile));
        FXMLLoader loader = new FXMLLoader(getClass().getResource(parentFile));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createCourseAddWindowController() {
        new AddCourseController(university, this, addCourseFXML);
    }

    private void createGroupAddWindowController() {
        new AddGroupController(university, this, addGroupFXML);
    }

    private void createTeacherSaveWindowController() {
        new SaveTeachersController(university, this, saveTeacherFXML);
    }

    private void createTeacherAddWindowController() {
        new AddTeacherControler(university, this, addTeacherFXML);

    }

    public void createStudentAddWindowController() {
        new AddStudentControler(university, this, addStudentFXML);
    }

    public void updateTeacherListView() {
        teacherListView.setItems(new ArrayListConverter().objectListToObservableList(university.getTeacherList()));
        System.out.println("TeacherList updated");
    }

    public void updateStudentListView() {
        studentListView.setItems(new ArrayListConverter().objectListToObservableList(university.getSelectedStudentsGroup().getGroupStudents()));
        System.out.println("StudentList updated");
    }

    public void updateGroupListView() {
        groupListView.setItems(new ArrayListConverter().objectListToObservableList(university.getStudentsGroupList()));
        System.out.println("GroupList updated");
    }

    public void updateCourseListView() {
        courseListView.setItems(new ArrayListConverter().objectListToObservableList(university.getCourseList()));
        System.out.println("CourseList updated");
    }

}
