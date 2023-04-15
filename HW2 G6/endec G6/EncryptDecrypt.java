package endec;

import java.util.Base64;

public class EncryptDecrypt {

    public static Encrypter getTransposeEncrypter() {
       return StringUtils::transposeChars;
    }

    public static Decrypter getTransposeDecrypter() {
        return StringUtils::transposeChars;
    }

    public static Encrypter getBase64Encrypter() {
        Encrypter myEncrypter = new Encrypter(){
            @Override
            public String encrypt(String plainText){
                Base64.Encoder encoder = Base64.getEncoder();
                String encryptedText = encoder.encodeToString(plainText.getBytes());
                return encryptedText;
            }
        };
        return myEncrypter;
    }

    public static Decrypter getBase64Decrypter() {
        Decrypter myDecrypter = new Decrypter(){
            @Override
            public String decrypt(String encryptedText){
                Base64.Decoder decoder = Base64.getDecoder();
                byte[] bytes = decoder.decode(encryptedText);
                String plainText = new String(bytes);
                return plainText;
            }
        };
        return myDecrypter;
    }

    public static Encrypter getSubstitutionEncrypter() {
        Encrypter myEncrypter = (String text) -> {
            SubstitutionEncrypterDecrypter substitutionEncrypter = new SubstitutionEncrypterDecrypter();
            return substitutionEncrypter.encrypt(text);
        };
        return myEncrypter;
    }

    public static Decrypter getSubstitutionDecrypter() {
       Decrypter myDecrypter = (String text) -> {
        SubstitutionEncrypterDecrypter substitutionDecrypter = new SubstitutionEncrypterDecrypter();
        return substitutionDecrypter.decrypt(text);
       };
       return myDecrypter;
    }

    public static void EncryptDecryptTest(Encrypter encrypter,Decrypter decrypter) {
        try {
            String plainText = "You miss 100 percent of the shots you never take.";
            String encryptedText = encrypter.encrypt(plainText);
            String decryptedText = decrypter.decrypt(encryptedText);
            System.out.println("Plain Text: " + plainText);
            System.out.println("Encrypted Text:" + encryptedText);
            System.out.println("Decrypted Text:" + decryptedText);
        } catch (NullPointerException npe) {
            System.out.println(npe.getMessage());
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Transpose");
        EncryptDecryptTest(getTransposeEncrypter(),getTransposeDecrypter());

        System.out.println("\nBase64");
        EncryptDecryptTest(getBase64Encrypter(),getBase64Decrypter());

        System.out.println("\nSubstitution");
        EncryptDecryptTest(getSubstitutionEncrypter(),getSubstitutionDecrypter());
    }
}
