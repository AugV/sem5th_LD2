package input;

import entities.Teacher;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class InputFile {
    private Scanner scanner;

    public InputFile(String fileName){
        try {
            scanner = new Scanner(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            System.out.println(" !Bad file name!");
        }
    }
//OUT OF DATE
//    public Teacher getTeacherFromFile(){
//        //file data format: {ID: [id], Name: [name],Surname: [surname]}
//        String[] teacherInfoArray = scanner.nextLine().split(",");
//        return new Teacher(Integer.parseInt(teacherInfoArray[0].substring(teacherInfoArray[0].indexOf(':')+1).trim()),
//                teacherInfoArray[1].substring(teacherInfoArray[1].indexOf(':')+1).trim(),
//                teacherInfoArray[2].substring(teacherInfoArray[2].indexOf(':')+1).trim());
//    }



}