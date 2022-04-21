package com.LOGIC;

import com.GUI.IO;

public class Log {
    protected
    final String date;
    final String component;
    final String author;
    final String importance;
    final String description;

    Log(String date, String component, String author,String importance,String description){
        this.date = date;
        this.component = component;
        this.author = author;
        this.importance = importance;
        this.description = description;
    }
    Log(String component, String author,String importance,String description){
        date = java.time.LocalDate.now().toString();
        this.component = component;
        this.author = author;
        this.importance = importance;
        this.description = description;
    }

    @Override
    public String toString(){
        return date + "\n" + component + "\n" + author + "\n" + importance + "\n" + description + "\n+           END           +\n";
    }

    public void show(){
        IO.promptUser("[*] Date -> " + date + ";\n[*] Component -> " + component + ";\n[*] Author -> " + author + ";\n[*] Importance -> " + importance + ";\n[*] Description -> " + description + ";\n\n+           END           +\n\n");
    }
}