package com.LOGIC;

public class DataValidation {
    public static boolean isDate(String data){
        if(data.length() != 10)
            return false;

        if(data.charAt(4) == '-' && data.charAt(7) == '-'){

            String[] splitData = data.split("-");
            if(splitData[0].length() == 4 && splitData[1].length() == 2 && splitData[2].length() == 2){
                if(isNumber(splitData[0]) && isNumber(splitData[1]) && isNumber(splitData[2])){
                    int year = Integer.parseInt(splitData[0]);
                    int month = Integer.parseInt(splitData[1]);
                    int day = Integer.parseInt(splitData[2]);

                    if(month < 13 && month > 0){
                        if(month % 2 == 1) {
                            return day < 32 && day > 0;
                        }
                        else{
                            if(month == 2){
                                if(year % 4 == 0)
                                    return  day < 30 && day > 0;
                                else
                                    return day < 29 && day > 0;
                            }
                            else return day < 31 && day > 0;
                        }
                    }
                    else return false;

                }
                else return false;
            }
            else return false;
        }
        else return false;
    }

    public static boolean isNumber(String data){
        if(data.length() == 0) return false;
        boolean error = false;

        for(char character: data.toCharArray()){
            if(character != '0' && character != '1' && character != '2' && character != '3'&& character != '4' && character != '5' && character != '6' && character != '7' && character != '8' && character != '9') {
                error = true;
                break;
            }
        }
        return !error;
    }

    public static boolean isNumber(String data, int minValue, int maxValue){
        if (isNumber(data)) {
            int intData = Integer.parseInt(data);
            return intData >= minValue && intData <= maxValue;
        }
        else return false;
    }

    public static boolean logImportance(String data){
        return data.equals("low") || data.equals("medium") || data.equals("high");
    }

    public static boolean feedbackNote (String data){
        return data.equals("+") || data.equals("-");
    }

    public static boolean trendInterval (String data){
        return data.equals("week") || data.equals("month") || data.equals("quarter");
    }
}