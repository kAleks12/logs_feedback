package com.GUI;

import com.LOGIC.ActionBase;
import com.LOGIC.DataValidation;

import java.io.IOException;
import java.util.ArrayList;

public class Menu {
    private static void trendMenu(ActionBase dataBase) throws InterruptedException {
        IO.CLS();
        String Id;
        String interval;

        IO.promptUser("Enter worker ID; Input -> <number>: ");
        Id = IO.getDataInput();

        while(!DataValidation.isNumber(Id)) {
            IO.promptUser("\nInvalid value! Enter correct one:");
            Id = IO.getDataInput();
        }

        IO.promptUser("\nChoose interval; Input -> <'1' - week, '2' - month, '3' - quarter>: ");
        interval = IO.getDataInput();

        while(!DataValidation.isNumber(interval,1,3)) {
            IO.promptUser("\nInvalid value! Enter correct one:");
            interval = IO.getDataInput();
        }

        IO.CLS();
        float [] points;
        float ratio;

        switch(interval){
            case "1" ->  {
                points = dataBase.feedbackTrend(Id,"week");
                ratio = points[0]/points[1];

                if(ratio != 0) {
                    if (ratio == -1)
                        IO.promptUser("Worker " + Id + " has only negative feedback!");
                    else {
                        if (ratio == -2)
                            IO.promptUser("Worker " + Id + " has only positive feedback!");
                        else
                            IO.promptUser("Worker " + Id + "' results for last week [7 days]\n\nPositive points -> " + points[0] + "\nNegative points -> " + points[1] + "\nRatio (positive/negative notes) -> " + ratio);
                    }
                }
                else {
                    IO.promptUser("There is no feedback for this employee's id");
                    Thread.sleep(2000);
                    return;
                }
            }
            case "2" -> {
                points = dataBase.feedbackTrend(Id,"month");
                ratio = points[0]/points[1];
                if(ratio != 0) {
                    if (ratio == -1)
                        IO.promptUser("Worker " + Id + " has only negative feedback!");
                    else {
                        if (ratio == -2)
                            IO.promptUser("Worker " + Id + " has only positive feedback!");
                        else
                            IO.promptUser("Worker " + Id + "' results for last month [30 days]\n\nPositive points -> " + points[0] + "\nNegative points -> " + points[1] + "\nRatio (positive/negative notes) -> " + ratio);
                    }
                }
                else {
                    IO.promptUser("There is no feedback for this employee's id");
                    Thread.sleep(2000);
                    return;
                }
            }
            case "3" -> {
                points = dataBase.feedbackTrend(Id,"quarter");
                ratio = points[0]/points[1];
                if(ratio != 0) {
                    if (ratio == -1)
                        IO.promptUser("Worker " + Id + " has only negative feedback!");
                    else {
                        if (ratio == -2)
                            IO.promptUser("Worker " + Id + " has only positive feedback!");
                        else
                            IO.promptUser("Worker " + Id + "' results for last quarter [120 days]\n\nPositive points -> " + points[0] + "\nNegative points -> " + points[1] + "\nRatio (positive/negative notes) -> " + ratio);
                    }
                }
                else {
                    IO.promptUser("There is no feedback for this employee's id");
                    Thread.sleep(2000);
                    return;
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + interval);
        }
        IO.promptUser("\nPress enter to return to the previous menu");
        IO.getDataInput();
    }

    private static void discardMenu(ActionBase dataBase) throws InterruptedException {
        IO.CLS();

        String id;

        IO.promptUser("Enter ID of an employee whom regards the feedback to be discarded; Input -> <number>");
        id = IO.getDataInput();

        while (!DataValidation.isNumber(id)) {
            IO.promptUser("\nInvalid value! Enter correct one:");
            id = IO.getDecisionInput();
        }

        ArrayList <Integer> indexes = dataBase.findElements("feedback",2, id);

        if(indexes.size() == 0){
            IO.promptUser("\nThere is no feedback for this employee's  id");
            Thread.sleep(2000);
        }
        else{
            IO.CLS();
            dataBase.printElements("feedback", indexes);
            IO.promptUser("\n\nWhat feedback would you like to discard (Enter number of the element that should be discarded)");

            String discardIndex = IO.getDataInput();

            while(!DataValidation.isNumber(discardIndex, 0 , indexes.size()-1)){
                IO.promptUser("\nInvalid value! Enter correct one:");
                discardIndex = IO.getDecisionInput();
            }

            dataBase.discardFeedback(Integer.parseInt(discardIndex));
        }
    }

    private static void searchMenu(ActionBase dataBase){
        IO.CLS();

        String factor;
        String value;

        while (true) {
            IO.promptUser("Please choose factor by which you would like to search the database \n");
            IO.promptUser("['1'] date\n['2'] component\n['3'] author\n['4'] importance\n");
            factor = IO.getDecisionInput();

            if (factor.equals("1") || factor.equals("2") || factor.equals("3") || factor.equals("4"))
                break;
            else {
                IO.promptUser("\nInvalid value");
                IO.CLS();
            }
        }

        IO.CLS();

        switch(factor){
            case "1" -> {
                IO.promptUser("Enter desired value; Input -> <yyyy-mm-dd>:");
                value = IO.getDataInput();

                while(!DataValidation.isDate(value)){
                    IO.promptUser("\nInvalid value! Enter correct one:");
                    value = IO.getDataInput();
                }
            }
            case "2" -> {
                IO.promptUser("Enter desired value:");
                value = IO.getDataInput();
            }
            case "3" -> {
                IO.promptUser("Enter desired value; Input -> <Name Surname>");
                value = IO.getDataInput();
            }
            case "4" -> {
                IO.promptUser("Enter desired value; Input -> <low, medium, high>:");
                value = IO.getDataInput();

                while(!DataValidation.logImportance(value)){
                    IO.promptUser("\nInvalid value! Enter correct one:");
                    value = IO.getDataInput();
                }
            }
            default -> throw new IllegalStateException("Unexpected factor: " + factor);
        }
        IO.CLS();
        IO.promptUser("SEARCH RESULTS\n\n");
        if(!dataBase.printSelectedLogs(factor, value))
            IO.promptUser("No matches found. Press enter to return to the previous menu");
        else
            IO.promptUser("Press enter to return to the previous menu");
        IO.getDataInput();
    }

    private static void initComMenu(String dataType, ActionBase dataBase) throws IOException, InterruptedException {
        IO.CLS();
        switch(dataType) {
            case "log" -> {
                IO.promptUser("Would like to import previous logs from .txt file?\nYes ['Y'] No ['Other Key']\n");
                switch (IO.getDecisionInput()) {
                    case "Y", "y" -> logMenu(true, dataBase);
                    default -> logMenu(false, dataBase);
                }
            }
            case "feedback" -> {
                IO.promptUser("Would like to import previous feedback from .txt file?\nYes ['Y'] No ['Other Key']\n");
                switch (IO.getDecisionInput()) {
                    case "Y", "y" -> feedbackMenu(true, dataBase);
                    default -> feedbackMenu(false, dataBase);
                }
            }
            default -> throw new IllegalStateException("Unexpected value " + dataType);
        }
    }

    private static void addDataMenu(String dataType, ActionBase dataBase){
        String attr1;
        String attr2;
        String attr3;
        String attr4;

        IO.CLS();
        switch(dataType) {
            case "log" -> {
                IO.promptUser("Enter component - a decision which this log is about; Input -> <sentence/word>:");
                attr1 = IO.getDataInput();

                IO.promptUser("\nEnter author; Input ->  <Name Surname>:");
                attr2 = IO.getDataInput();

                IO.promptUser("\nEnter the importance; Input -> <low, medium, high>:");
                attr3 = IO.getDataInput();

                while (!DataValidation.logImportance(attr3)) {
                    IO.promptUser("Wrong importance! Enter correct one:");
                    attr3 = IO.getDataInput();
                }

                IO.promptUser("\nEnter description:");
                attr4 = IO.getDataInput();

                IO.CLS();
                dataBase.addData("log", attr1, attr2, attr3, attr4);
            }
            case "feedback" -> {
                IO.promptUser("Enter worker ID; Input -> <number>:");
                attr1 = IO.getDataInput();

                while(!DataValidation.isNumber(attr1)) {
                    IO.promptUser("\nInvalid value! Enter correct one:");
                    attr1 = IO.getDataInput();
                }

                IO.promptUser("\nEnter note; Input -> <positive '+'/negative '-'>:");
                attr2 = IO.getDataInput();
                while(!DataValidation.feedbackNote(attr2)) {
                    IO.promptUser("\nInvalid value! Enter correct one:");
                    attr2 = IO.getDataInput();
                }

                IO.promptUser("\nEnter importance; Input -> <number 1-10>:");
                attr3 = IO.getDataInput();
                while(!DataValidation.isNumber(attr3, 1, 10)) {
                    IO.promptUser("\nInvalid value! Enter correct one:");
                    attr3 = IO.getDataInput();
                }

                IO.promptUser("\nEnter description:");
                attr4 = IO.getDataInput();

                dataBase.addData("feedback", attr1, attr2, attr3, attr4);
            }
            default -> throw new IllegalStateException("Unexpected value " + dataType);
        }
    }

    private static void browseMenu(String dataType, ActionBase dataBase) throws InterruptedException {
        IO.CLS();
        switch(dataType) {
            case "log" -> {
                if(dataBase.isEmpty("log")) {
                    IO.promptUser("Database is empty");
                    Thread.sleep(2000);
                    return;
                }
                else {
                    IO.promptUser("LOG BASE\n\n");
                    dataBase.printBase("log");
                }
            }
            case "feedback" -> {
                if(dataBase.isEmpty("feedback")) {
                    IO.promptUser("Database is empty");
                    Thread.sleep(2000);
                    return;
                }
                else {
                    IO.promptUser("FEEDBACK BASE\n\n");
                    dataBase.printBase("feedback");
                }
            }
            default -> throw new IllegalStateException("Unexpected value " + dataType);
        }
        IO.promptUser("Press enter to return to the previous menu");
        IO.getDataInput();
    }

    private static boolean saveDataMenu(String dataType, ActionBase dataBase) throws IOException {
        IO.CLS();

        switch (dataType) {
            case "log" -> {
                IO.promptUser("Would like to save current logs to .txt file?\nYes ['Y'] No ['Other Key']\n\nPlease note that if current logs aren't identical as the one in the file, it will be overwritten!\n");
                switch (IO.getDecisionInput()) {
                    case "Y", "y" -> {
                        dataBase.saveData("log", "Logs");
                        dataBase.clean("log");
                        return true;
                    }
                    default -> {
                        dataBase.clean("log");
                        return false;
                    }
                }
            }
            case "feedback" -> {
                IO.promptUser("Would like to save current feedback to .txt file?\nYes ['Y'] No ['Other Key']\n\nPlease note that if current feedback isn't identical as the one in the file, it will be overwritten!\n");
                switch (IO.getDecisionInput()){
                    case "Y","y" -> {
                        dataBase.saveData("feedback", "Feedback");
                        dataBase.clean("feedback");
                        return true;
                    }
                    default -> {
                        dataBase.clean("feedback");
                        return false;
                    }
                }
            }
            default -> throw new IllegalStateException("Unexpected value " + dataType);
        }
    }

    private static void logMenu(boolean ifRetrieveData, ActionBase dataBase) throws IOException, InterruptedException {
        IO.CLS();
        if(ifRetrieveData) {
            if(dataBase.retrieveData("log", "Logs"))
                IO.promptUser("Previous data retrieved");
            else
                IO.promptUser("There is no file in the directory .jar file is located");
            Thread.sleep(2000);
        }

        while(true){
            IO.CLS();
            IO.promptUser("Please choose between available features\n\n['1'] Add new log\n['2'] Browse logs\n['3'] Search\n['4'] Return to the com.main menu\n");

            switch (IO.getDecisionInput()) {
                case "1" -> addDataMenu("log",dataBase);
                case "2" -> browseMenu("log", dataBase);
                case "3" -> searchMenu(dataBase);
                case "4" -> {
                    if(saveDataMenu("log", dataBase)) {
                        IO.promptUser("Data saved successfully");
                        Thread.sleep(2000);
                    }
                    return;
                }
                default -> IO.promptUser("\nInvalid input");

            }
        }
    }

    private static void feedbackMenu(boolean ifRetrieveData, ActionBase dataBase) throws IOException, InterruptedException {
        IO.CLS();
        if(ifRetrieveData) {
            if(dataBase.retrieveData("log", "Logs"))
                IO.promptUser("Previous data retrieved");
            else
                IO.promptUser("There is no file in the directory .jar file is located");
            Thread.sleep(2000);
        }

        while(true) {
            IO.CLS();
            IO.promptUser("Please choose between available features\n\n['1'] Add new feedback about an employee");
            IO.promptUser("['2'] Browse current feedback\n['3'] Discard previous feedback about an employee");
            IO.promptUser("['4'] Analise feedback trend of an employee\n['5'] Return to the com.main menu\n");

            switch (IO.getDecisionInput()) {
                case "1" -> addDataMenu("feedback",dataBase);
                case "2" -> browseMenu("feedback",dataBase);
                case "3" -> discardMenu(dataBase);
                case "4" -> trendMenu(dataBase);
                case "5" -> {
                    if(saveDataMenu("feedback", dataBase)) {
                        IO.promptUser("Data saved successfully");
                        Thread.sleep(2000);
                    }
                    return;
                }
                default -> IO.promptUser("\nInvalid input");
            }
        }
    }

    public static void startMenu(ActionBase actionBase) throws IOException, InterruptedException {
        while(true) {
            IO.CLS();
            IO.promptUser("Please choose component of the application\n\n['1'] Decisive log mode \n(in which you can add decisive logs, and browse/search through them)\n\n");
            IO.promptUser("['2'] Feedback about Employees mode \n(in which you can add feedback about an employee, and discard it/analyze feedback they received)\n\n\n['3'] Exit application\n");
            switch (IO.getDecisionInput()) {
                case "1" -> initComMenu("log",actionBase);
                case "2" -> initComMenu("feedback",actionBase);
                case "3" -> {
                    return;
                }
                default -> IO.promptUser("\nInvalid input");
            }
        }
    }
}