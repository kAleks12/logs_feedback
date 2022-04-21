package com.LOGIC;

import com.GUI.IO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class ActionBase {
    private final ArrayList<Log> logBase = new ArrayList<>();
    private final ArrayList<Feedback> feedbackBase = new ArrayList<>();


    public boolean isEmpty(String dataType){
        switch (dataType) {
            case "log" -> {
                return logBase.size() == 0;
            }
            case "feedback" -> {
                return feedbackBase.size() == 0;
            }
            default -> throw new IllegalStateException("Unexpected value: " + dataType);
        }
    }

    public  ArrayList <Integer> findElements(String dataType, int attrNum, String value){
        ArrayList <Integer> indexes = new ArrayList<>();
        switch (dataType) {
            case "log" -> {
                switch(attrNum) {
                    case 1 -> {
                        for (Log currLog : logBase)
                           if(currLog.date.equals(value))
                               indexes.add(logBase.indexOf(currLog));
                    }
                    case 2 -> {
                        for (Log currLog : logBase)
                            if(currLog.component.equals(value))
                                indexes.add(logBase.indexOf(currLog));
                    }
                    case 3 -> {
                        for (Log currLog : logBase)
                            if(currLog.author.equals(value))
                                indexes.add(logBase.indexOf(currLog));
                    }
                    case 4 -> {
                        for (Log currLog : logBase)
                            if(currLog.importance.equals(value))
                                indexes.add(logBase.indexOf(currLog));
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + attrNum);
                }
                return indexes;
            }
            case "feedback" -> {
                switch(attrNum) {
                    case 1 -> {
                        for (Feedback currFeedback : feedbackBase)
                            if(currFeedback.date.equals(value))
                                indexes.add(feedbackBase.indexOf(currFeedback));
                    }
                    case 2 -> {
                        for (Feedback currFeedback : feedbackBase)
                            if(currFeedback.workerId.equals(value))
                                indexes.add(feedbackBase.indexOf(currFeedback));
                    }
                    case 3 -> {
                        for (Feedback currFeedback : feedbackBase)
                            if(currFeedback.note.equals(value))
                                indexes.add(feedbackBase.indexOf(currFeedback));
                    }
                    case 4 -> {
                        for (Feedback currFeedback : feedbackBase)
                            if(currFeedback.importance.equals(Integer.parseInt(value)))
                                indexes.add(feedbackBase.indexOf(currFeedback));
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + attrNum);
                }
                return indexes;
            }
            default -> throw new IllegalStateException("Unexpected value: " + dataType);
        }
    }

    public void printElements (String dataType, ArrayList <Integer> indexes){
        switch (dataType) {
            case "log" -> {
                for (Integer index : indexes) {
                    IO.promptUser("Element no. " + indexes.indexOf(index) + "\n\n");
                    logBase.get(index).show();
                }
            }
            case "feedback" -> {
                for (Integer index : indexes) {
                    IO.promptUser("Element no. " + indexes.indexOf(index) + "\n\n");
                    feedbackBase.get(index).show();
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + dataType);
        }
    }

    public boolean retrieveData (String dataType, String fileName) throws FileNotFoundException {
        Scanner dataFile = FileOp.openFileRead(fileName + ".txt");

        String attr1, attr2, attr3, attr4, attr5;

        if (dataFile != null) {
            while(true) {
                attr1 = FileOp.readLines(dataFile);
                attr2 = FileOp.readLines(dataFile);
                attr3 = FileOp.readLines(dataFile);
                attr4 = FileOp.readLines(dataFile);
                attr5 = FileOp.readChunk(dataFile, "+           END           +");

                if (!attr1.equals("") && !attr2.equals("") && !attr3.equals("") && !attr4.equals("")) {
                    if(attr1.equals("!~~^^~~!") || attr2.equals("!~~^^~~!") || attr3.equals("!~~^^~~!") || attr4.equals("!~~^^~~!") || attr5.equals("!~~^^~~!")){
                        dataFile.close();
                        return true;
                    }
                    else {
                        addData(dataType, attr1, attr2, attr3, attr4, attr5);
                    }
                }
            }
        }
        else return false;
    }

    public void saveData(String dataType, String fileName) throws IOException {
        PrintWriter dataFile = FileOp.openFileWrite( fileName + ".txt");

        switch (dataType) {
            case "log" -> {
                for (Log currLog : logBase)
                    dataFile.print(currLog.toString());
            }
            case "feedback" -> {
                for (Feedback currFeedback : feedbackBase)
                    dataFile.print(currFeedback.toString());
            }
            default -> throw new IllegalStateException("Unexpected value: " + dataType);
        }
        dataFile.close();
    }

    public void addData(String dataType, String attr1, String attr2, String attr3, String attr4) {
        switch (dataType) {
            case "log" -> logBase.add(new Log(attr1, attr2, attr3, attr4));
            case "feedback" -> feedbackBase.add(new Feedback(attr1, attr2, Integer.parseInt(attr3), attr4));
            default -> throw new IllegalStateException("Unexpected value: " + dataType);
        }
    }

    public void addData(String dataType, String attr1, String attr2, String attr3, String attr4, String attr5) {
        switch (dataType) {
            case "log" -> logBase.add(new Log(attr1, attr2, attr3, attr4, attr5));
            case "feedback" -> feedbackBase.add(new Feedback(attr1, attr2, attr3, Integer.parseInt(attr4), attr5));
            default -> throw new IllegalStateException("Unexpected value: " + dataType);
        }
    }

    public void clean (String dataType){
        switch (dataType) {
            case "log" -> logBase.clear();
            case "feedback" -> feedbackBase.clear();
            default -> throw new IllegalStateException("Unexpected value: " + dataType);
        }
    }

    public void printBase(String dataType){
        switch (dataType) {
            case "log" -> {
                for (Log currLog : logBase)
                    currLog.show();
            }
            case "feedback" -> {
                for (Feedback currFeedback : feedbackBase)
                    currFeedback.show();
            }
            default -> throw new IllegalStateException("Unexpected value: " + dataType);
        }
    }

    public boolean printSelectedLogs(String factor, String desiredValue){
        boolean results = false;

        switch(factor) {
            case "1"-> {
                for (Log currLog : logBase) {
                    if (currLog.date.equals(desiredValue)) {
                        currLog.show();
                        results = true;
                    }
                }
            }
            case "2"-> {
                for (Log currLog : logBase) {
                    if (currLog.component.equals(desiredValue)) {
                        currLog.show();
                        results = true;
                    }
                }
            }
            case "3"-> {
                for (Log currLog : logBase) {
                    if (currLog.author.equals(desiredValue)){
                        currLog.show();
                        results = true;
                    }
                }
            }
            case "4" -> {
                for (Log currLog : logBase) {
                    if (currLog.importance.equals(desiredValue)){
                        currLog.show();
                        results = true;
                    }
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + factor);
        }
        return results;
    }

    public void discardFeedback(Integer index) throws InterruptedException {
        feedbackBase.remove(feedbackBase.get(index));
    }

    public float[] feedbackTrend(String workerId, String interval){
        float[] points = new float[2];

        if(findElements("feedback",2,workerId).size() != 0) {
            String currDate = java.time.LocalDate.now().toString();
            Calendar rightNow = Calendar.getInstance();


            switch (interval) {
                case "week" -> rightNow.add(Calendar.DAY_OF_MONTH, -7);
                case "month" -> rightNow.add(Calendar.DAY_OF_MONTH, -30);
                case "quarter" -> rightNow.add(Calendar.DAY_OF_MONTH, -120);
                default -> throw new IllegalStateException("Unexpected value: " + interval);
            }
            String startDate = rightNow.get(Calendar.YEAR) + "-" + (rightNow.get(Calendar.MONTH) + 1) + "-" + rightNow.get(Calendar.DAY_OF_MONTH);

            float positive = 0;
            float negative = 0;
            for (Feedback currFeedback : feedbackBase) {
                if (currFeedback.workerId.equals(workerId) && dateFits(currFeedback.date, startDate, currDate)) {
                    if (currFeedback.note.equals("+"))
                        positive += currFeedback.importance;
                    else negative += currFeedback.importance;
                }
            }

            if(positive == 0 && negative != 0) {
                points[0] = -1;
                points[1] = 1;
            }
            else {
                if (negative == 0 && positive != 0) {
                    points[0] = -2;
                    points[1] = 1;
                }
                else {
                    points[0] = positive;
                    points[1] = negative;
                }
            }
        }
        else {
            points[0] = 0;
            points[1] = 1;
        }
        return points;
    }

    private String extractPart(String date, int part){
        String[] splitDate= date.split("-");
        switch(part){
            case 0 -> {
                return splitDate[0];
            }
            case 1 -> {
                return splitDate[1];
            }
            case 2 -> {
                return splitDate[2];
            }
            default -> throw new IllegalStateException("Unexpected value: " + part);
        }
    }

    private boolean dateFits(String date, String startDate,String endDate) {
        int startDateDay = Integer.parseInt(extractPart(startDate, 2));
        int startDateMonth = Integer.parseInt(extractPart(startDate, 1));
        int startDateYear = Integer.parseInt(extractPart(startDate, 0));

        int endDateDay = Integer.parseInt(extractPart(startDate, 2));
        int endDateMonth = Integer.parseInt(extractPart(startDate, 1));
        int endDateYear = Integer.parseInt(extractPart(startDate, 0));

        int dateDay = Integer.parseInt(extractPart(startDate, 2));
        int dateMonth = Integer.parseInt(extractPart(startDate, 1));
        int dateYear = Integer.parseInt(extractPart(startDate, 0));



        if(dateYear >= startDateYear && startDateYear <= endDateYear){
            if(startDateYear  == endDateYear){
                if(dateMonth >= startDateMonth && dateMonth <= endDateMonth){
                    if(startDateMonth == endDateMonth){
                        return dateDay >= startDateDay && dateDay <= endDateDay;
                    }
                    else return true;
                }
                else return false;
            }
            return true;
        }
        else return false;
   }
}