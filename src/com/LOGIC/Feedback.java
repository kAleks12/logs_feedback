package com.LOGIC;


public class Feedback {
    protected
    final String date;
    final String workerId;
    final String note;
    final Integer importance;
    final String description;

    Feedback(String date, String workerId, String note,Integer importance,String description){
        this.date = date;
        this.workerId = workerId;
        this.note = note;
        this.importance = importance;
        this.description = description;
    }
    Feedback(String workerId, String note,Integer importance,String description){
        date = java.time.LocalDate.now().toString();
        this.workerId = workerId;
        this.note = note;
        this.importance = importance;
        this.description = description;
    }


    @Override
    public String toString(){
        return date + "\n" + workerId + "\n" + note + "\n" + importance + "\n" + description + "\n+           END           +\n";
    }

    public void show(){
        System.out.println("[*] Date -> " + date + ";\n[*] Worker ID -> " + workerId + ";\n[*] Note -> " +  note + ";\n[*] Importance -> " + importance + ";\n[*] Description -> "  + description + ";\n\n+           END           +\n\n");
    }
}