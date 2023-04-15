package endec;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class SubstitutionEncrypterDecrypter implements Encrypter, Decrypter{
    private final static Map<Character, Character> encryptMap;
    private final static Map<Character, Character> decryptMap;

    static{
        encryptMap = new HashMap<>();
        decryptMap = new HashMap<>();
        List<Character> randomCharacters = new ArrayList<>();
        for (char c=32; c<=126; c++){
            randomCharacters.add(c);
        }
        Collections.shuffle(randomCharacters);

        for (int i=0; i<randomCharacters.size(); i++){
            char c1 = (char)(i+32);
            char c2 = randomCharacters.get(i);
            encryptMap.put(c1,c2);
            decryptMap.put(c2,c1);
        }
    }

    @Override
    public String encrypt(String plainText){
        String encryptedText = "";
        for (int i=0; i<plainText.length(); i++){
            encryptedText += encryptMap.get(plainText.charAt(i));
        }
        return encryptedText;
    }

    @Override
    public String decrypt(String encryptedText){
        String plainText = "";
        for (int i=0; i<encryptedText.length(); i++){
            plainText += decryptMap.get(encryptedText.charAt(i));
        }
        return plainText;
    }
}