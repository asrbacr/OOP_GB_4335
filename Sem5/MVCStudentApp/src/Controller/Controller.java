package Controller;

import java.util.ArrayList;
import java.util.List;

import Model.Model;
import Model.Student;
import View.View;
import View.ViewEng;

public class Controller {
    private iGetView view;
    private iGetView viewEng;
    private iGetModel model;
    private List<Student> students;

    // public Controller(iGetView view, iGetModel model) {
    public Controller(iGetModel model) {
        // this.view = view;
        this.model = model;
        this.students = new ArrayList<Student>();
    }

    public void getAllStudents() {
        students = model.getAllStudents();
    }

    public boolean testData() {
        if (students.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void update() {
        // MVP
        getAllStudents();
        if (testData()) {
            view.printAllStudents(students);
        } else {
            System.out.println("Список студентов пуст!");
        }

        // MVC
        // view.printAllStudents(model.getAllStudents());
    }

    /**
     * Русский язык
     */
    private void run() {
        View view = new View();
        Commands com = Commands.NONE;
        boolean getNewIteration = true;
        while (getNewIteration) {
            String command = view.prompt("Введите команду: ");
            com = Commands.valueOf(command.toUpperCase());
            switch (com) {
                case EXIT:
                    getNewIteration = false;
                    System.out.println("Выход из программы!");
                    break;
                case LIST:
                    getAllStudents();
                    view.printAllStudents(students);
                    break;
                case DELETE:
                    String idDel = view.prompt("Введите номер студента, которого хотите удалить: ");
                    Long removeID = Long.parseLong(idDel);
                    removeStudent(removeID);
                    break;
                    
            }
        }
    }
    
    private void removeStudent(Long removeID) {
    }

    /**
     * English language
     */
    private void runEng() {
        ViewEng view = new ViewEng();
        Commands com = Commands.NONE;
        boolean getNewIteration = true;
        while (getNewIteration) {
            String command = view.prompt("Input command: ");
            com = Commands.valueOf(command.toUpperCase());
            switch (com) {
                case EXIT:
                    getNewIteration = false;
                    System.out.println("Exiting the programme!");
                    break;
                case LIST:
                    getAllStudents();
                    view.printAllStudents(students);
                    break;
                case DELETE:
                    String idDel = view.prompt("Enter the number of the student you wish to delete: ");
                    Long removeID = Long.parseLong(idDel);
                    removeStudent(removeID);
                    break;
            }
        }
    }
    
    private void errorInputLanguage() {
        System.out.println("Ошибка: возможно ошиблись при вводе, повторите запуск приложения");
    }

    /**
     * Команда для запуска программы
     */
    public void startProgramm() {
        LangProgramm lang = LangProgramm.NONE;
        View view = new View();
        String command = view.prompt("Введите язык (RUS или ENG) / Input language (RUS or ENG): ");
        lang = LangProgramm.valueOf(command.toUpperCase());
        switch (lang) {
            case RUS:
                run();
                break; 
            case ENG:
                runEng();
                break;
        }

        
    }
}