package validadorDeContrasenias;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JOptionPane;


class ArchivoContraseniasInvalidas{
		
	public static boolean encontrar(String contraseniaInvalida) {
        boolean respuesta=false;
        File archivo = null;
        FileReader lectorCaracter = null; // lee caracter a caracter
        BufferedReader lectorPalabra = null; // lee una palabra al detectar un \n

          try {                                       
             archivo = new File("contrasenias10000.txt");  
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
	static String mensaje= "Contraseña invalida:\n";
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
			mensaje += "su contraseña es demasiado corta\n";
		if(!caracteresValidos(contrasenia))
			mensaje += "utilice caracteres validos\n";
		if(doblesEspacios(contrasenia))
			mensaje += "Los dobles espacios no son permitidos\n";
		if(contraseniaFrecuente(contrasenia))
			mensaje += "su contraseña es demasiado frecuente\n ";
		if(longitudValida(contrasenia) && caracteresValidos(contrasenia) && !doblesEspacios(contrasenia) && !contraseniaFrecuente(contrasenia))
			mensaje = "Contraseña Valida";
		JOptionPane.showMessageDialog(null,mensaje);	
		
			
	}
}

public class Validador{
	public static void main(String[] args){
		String contrasenia = JOptionPane.showInputDialog("Ingrese su contraseña");
		ReguladorDeContrasenias.regular(contrasenia);	 
	}	
}

