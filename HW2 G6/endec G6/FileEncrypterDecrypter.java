package endec;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;

public class FileEncrypterDecrypter {
    public static List<String> getLines(String name) throws IOException{
        List<String> lines = new ArrayList<>();
        try (FileReader fr = new FileReader(name); BufferedReader reader = new BufferedReader(fr)){
            String line = reader.readLine();
            while (line!=null){
                lines.add(line);
                line = reader.readLine();
            }
        }
        return lines;
    }

    public static void main(String[] args) throws IOException {
        List<String> plainTextList = getLines("data/alice_small.txt");
        plainTextList.stream().filter(line -> !line.isEmpty()).forEach(line ->{
            Encrypter myEncrypter = EncryptDecrypt.getSubstitutionEncrypter();
            System.out.println(myEncrypter.encrypt(line));
        });

        System.out.println();

        List<String> encryptedTextList1 = getLines("data/alice_small_base64.txt");
        encryptedTextList1.stream().filter(line -> !line.isEmpty()).forEach(line -> {
            Decrypter myDecrypter = EncryptDecrypt.getBase64Decrypter();
            System.out.println(myDecrypter.decrypt(line));
        });
        
        System.out.println();

        List<String> encryptedTextList2 = getLines("data/alice_small_transpose.txt");
        encryptedTextList2.stream().filter(line -> !line.isEmpty()).forEach(line -> {
            Decrypter mDecrypter2 = EncryptDecrypt.getTransposeDecrypter();
            System.out.println(mDecrypter2.decrypt(line));
        });
    }
}