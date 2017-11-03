package uni.ocad.bcp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uni.ocad.bcp.dao.BcpDao;
import java.io.IOException;
import java.nio.charset.CoderMalfunctionError;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Matchers.intThat;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;


@Service
public class BcpService {
	
	@Autowired
	private BcpDao bcpDao;
	
	public String gettestpago() {
		String sql="";
		
		sql=bcpDao.getTipoSImunbyCOd();
		return sql;
	}
	
	
	public void setProcesoFile() {
		String fileName = "C://CVDI4823.txt";
		List<String> list = new ArrayList<>();
		
		
	
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			//1. filter line 3
			//2. convert all content to upper case
			//3. convert it into a List
			list = stream.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		int lineas=0;
		
		lineas=list.size();
		System.out.println(lineas);
		String cabecera=list.get(0);
		System.out.println(cabecera);
		validarCabezera(cabecera);
		
		
	}
	
	public String validarCabezera(String cabecera) {
		String resultado="";
		String cab=cabecera;
		//0 DESCONOCIDO
		//1 PAGO BCP
		
		String tipoRegis="CC"; //Posicion 1,2
		String codCuende="";  //Posicion 3-5
		String codModena="";   //Posicion 6
		
		String numCueAfil="";  //Posicion 7-13
		String tipoVali="";    //Posicion 14
		String fechProc="";  //Posicion 15-22
		
		
		String cantRegis=""; //Posicion 23-31
		String monTotal=""; //Posicion 32-46 ("CUIDADO CON LOS DECIMALES) VER DOCUMENTACION
		String codBcp=""; //Posicion 47-50
		
		
		String casilla=""; //Posicion 51-56
		String horainfo="";  //Posicion 57-62
		String codServicio=""; //Posicion 63-68
		
		
		String libre=""; //posicion 69-300;
		
		
		
		System.out.println(cabecera.length());
		
				
		tipoRegis=cab.substring(0, 2);
		codCuende=cab.substring(2, 5);
		codModena=cab.substring(5, 6);
		
		numCueAfil=cab.substring(6, 13);
		tipoVali=cab.substring(13, 14);
		fechProc=cab.substring(14, 22);
		
		
		cantRegis=cab.substring(22, 31);
		monTotal=cab.substring(31, 46);
		codBcp=cab.substring(47, 50);
		
		
		
		
		System.out.println(tipoRegis);
		System.out.println(codCuende);
		System.out.println(codModena);
		
		
		System.out.println(numCueAfil);
		System.out.println(tipoVali);
		System.out.println(fechProc);

		System.out.println(cantRegis);
		System.out.println(monTotal);
		System.out.println(codBcp);
		
		
		
		
		
		return resultado;
	}
	
}
