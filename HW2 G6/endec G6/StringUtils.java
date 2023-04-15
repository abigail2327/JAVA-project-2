package endec;

public class StringUtils{
    public static String transposeChars(String text){
        String newText = "";
        for(int i=0; i<text.length()-1; i++){
            if (i/2==0){
                newText += text.charAt(i+1);
                newText += text.charAt(i);
            }
        }
        if (text.length()%2!=0){
            newText += text.charAt(text.length()-1);
        }
        return newText;
    }
}