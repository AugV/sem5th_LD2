public class ElDienynas {

    //private University university;

    public static void main(String[] args) {

        University university = new University();

//region
        university.setTeacherList(1,"Tadas", "jablinksis");
        university.setTeacherList(2,"Laimonas", "Stanislovsksi");

        university.addGroup(1,"GRUPE1");
        university.addGroup(2,"GRUPE2");

        university.getGroupByID(1).addGroupStudents(new Student(1, "Petras", "Studentauskas"));
        university.getGroupByID(2).addGroupStudents(new Student(2, "Studenis", "Studavicius"));

        university.addCourse(new Course(
                1,"matematika", "Matematikos kursas", university.getTeacherByID(1), university.getGroupByID(2)));
        university.addCourse(new Course(
                2,"darbaiXD", "Darbuko salalai", university.getTeacherByID(2), university.getGroupByID(1)));
        university.addCourse(new Course(
                3,"darkoks velnias", "dar vienas niekam nereikalingas kursas", university.getTeacherByID(1), university.getGroupByID(2)));

        university.getCourseByID(1).addCourseTask(new Task(1,"primasTaskas", "ejozaselse per ezereli", "08-09", "5" ));
        university.getCourseByID(2).addCourseTask(new Task(2,"antrasTaskas", "ezerelis ejo per zaselse", "12-12", "1"));

        university.getCourseByID(1).getCourseTaskByID(1).addTaskCompletedTask(new CompletedTask(
                1, "teisingas atsakymas, kolegos","1008" ));
        university.getCourseByID(2).getCourseTaskByID(2).addTaskCompletedTask(new CompletedTask(
                2, "neteisingas atsakymas, biciuli","9999" ));

        //endregion



        new BasicInterface(university).frontPageInteraction();

        /*try {
            System.out.println(getTeacherByID(14, university).getName());
        }
        catch(NullPointerException e){
            System.out.println("No such teacher, my man");
        }*/
    }
}
