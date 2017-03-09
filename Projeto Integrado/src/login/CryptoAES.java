package login;

import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
 
import javax.crypto.Cipher;
 
public class CryptoAES {
       
       String IV = "AAAAAAAAAAAAAAAA";
       String textopuro;
       String chaveencriptacao = "AAAAAAAAAAAAAAAA";
       byte[] textoencriptado;
       
       public CryptoAES(String textopuro){
    	   /* 
    	   
             try {
            	 
                    System.out.println("Texto Puro: " + textopuro);
                  
                    textoencriptado = encrypt(textopuro);
                   
                    System.out.print("Texto Encriptado: ");
 
                     for (int i=0; i<textoencriptado.length; i++)
                           System.out.print(new Integer(textoencriptado[i])+" ");
                    
                    System.out.println("");
                     
                    String textodecriptado = decrypt(textoencriptado);
                     
                    System.out.println("Texto Decriptado: " + textodecriptado);
              
             } catch (Exception e) {
                    e.printStackTrace();
             }*/
       }

       public byte[] encrypt(String textopuro) throws Exception {
             Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
             SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
             encripta.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
             return encripta.doFinal(textopuro.getBytes("UTF-8"));
       }
 
       public  String decrypt(byte[] textoencriptado) throws Exception{
             Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
             SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
             decripta.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
             return new String(decripta.doFinal(textoencriptado),"UTF-8");
       }
       
}