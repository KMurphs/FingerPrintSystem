/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fingerprint.system;

/**
 *
 * @author stephane.kibonge
 */
public class ParserConcatenator {

    
    public static String Concatenator(String[] inData){
        return Concatenator(inData, "");
    }
                   
    public static String Concatenator(String[] inData, String someSeparator){
        String result = "";
        String tempSeparator = someSeparator;
        
        if(inData.length > 0){
            if("".equals(tempSeparator)){
                tempSeparator = "::";
            }
            
            for (String inData1 : inData) {
                result = result + tempSeparator + inData1;
            }           
            
            result = result.substring(tempSeparator.length());
        }
        
        return result;
    }
    
    
    public static String[] Parser(String inData){
        return Parser(inData, "");
    }
                   
    public static String[] Parser(String inData, String someSeparator){
        String[] result = new String[]{""};
        String tempSeparator = someSeparator;
        String temp = inData;
        
        if(inData != null){
            if(inData.length() > 0){
                if("".equals(tempSeparator)){
                    tempSeparator = "::";
                }

                result = temp.split(tempSeparator);
            }           
        }
        
        return result;
    }
    
    
}
