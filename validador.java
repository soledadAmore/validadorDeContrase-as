package validadorDeContrasenias;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;



class ArchivoContraseniasInvalidas{
	static String direccionDelArchivo = "C:\\Users\\Oberyn\\Desktop\\Java\\validadorDeContrasenias\\src\\validadorDeContrasenias\\Contrasenias10000.txt";
	
	public static boolean encontrar(String contraseniaInvalida) {
        boolean respuesta=false;
        File archivo = null;
        FileReader lectorCaracter = null; // lee caracter a caracter
        BufferedReader lectorPalabra = null; // lee una palabra al detectar un \n

          try {                                       
             archivo = new File(direccionDelArchivo);  
             lectorCaracter = new FileReader (archivo);           
             lectorPalabra = new BufferedReader(lectorCaracter);            
             String linea;
             while((linea=lectorPalabra.readLine())!=null)// mientras no lleguemos al final del archivo
                if(linea.contentEquals(contraseniaInvalida))
                {
                    return linea.contentEquals(contraseniaInvalida);
                }

          }
          catch(Exception e){
             e.printStackTrace();
          }finally{
             // cierra el archivo
             try{
                if( lectorCaracter !=  null ){
                   lectorCaracter.close();
                }
             }catch (Exception e2){ 
                e2.printStackTrace();
             }
          }
        return false;
       }
}
class ReguladorDeContrasenias{
	static String listaCararcteresValidos = " !\",#$%&()*+-,-./0123456789:;<=>ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`@abcdefghijklmnopqrstuvwxyz{}";
	static public boolean longitudValida(String contrasenia){
		return contrasenia.length() >= 8;
	}
	static public boolean caracteresValidos(String contrasenia){
		int i=0;
		for(i=0;i<contrasenia.length();i++) {
			if(!listaCararcteresValidos.contains(Character.toString(contrasenia.charAt(i)))) 
				return false;
		}
		return true;
	}
	static public boolean doblesEspacios(String contrasenia){
		int i=0;
		for(i=0;i<contrasenia.length()-1;i++) {
			if(Character.isWhitespace(contrasenia.charAt(i)) && Character.isWhitespace(contrasenia.charAt(i+1))){
				return true;
			}
		}
		return false;
	}
	static public boolean contraseniaFrecuente(String contrasenia){
		return ArchivoContraseniasInvalidas.encontrar(contrasenia);
	}
	
	
	static public void regular(String contrasenia){
		if(!longitudValida(contrasenia))
			System.out.println("Contraseña invalida: su contraseña es demasiado corta");
		if(!caracteresValidos(contrasenia))
			System.out.println("Contraseña invalida: utilice caracteres validos");
		if(doblesEspacios(contrasenia))
			System.out.println("Contraseña invalida: Los dobles espacios no son permitidos");
		if(contraseniaFrecuente(contrasenia))
			System.out.println("Contraseña invalida: su contraseña es demasiado frecuente ");
		if(longitudValida(contrasenia) && caracteresValidos(contrasenia) && !doblesEspacios(contrasenia) && !contraseniaFrecuente(contrasenia))
			System.out.println("Contraseña Valida");
	 }
}

public class Validador{
	public static void main(String[] args){
		@SuppressWarnings("resource")
		Scanner entrada = new Scanner(System.in);
		System.out.println("Ingrese su contraseña");
		String contrasenia = entrada.nextLine();
		ReguladorDeContrasenias.regular(contrasenia);	 
	}	
}
