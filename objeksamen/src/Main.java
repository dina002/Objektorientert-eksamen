import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



import gui.SjakkGUI;


public class Main {

	public static void main(String[] args)  {
		String ip = null;
		int port = 0;
		String typeSpill = "Demo"; // hvis det ikke blir satt i config fila så kjøres demo program hvor begge kjøres lokalt  
		try { // for å hente inn config fil, her er alt satt for å kunne bruke programet på en maskin nå
			File fXmlFile = new File("src/config/Config.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();			
			NodeList nList = doc.getElementsByTagName("settings");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);						
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					port = Integer.parseInt(eElement.getElementsByTagName("port").item(0).getTextContent());
					ip = eElement.getElementsByTagName("ip").item(0).getTextContent();
					typeSpill = eElement.getElementsByTagName("typeSpill").item(0).getTextContent();
				}
			}
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		switch (typeSpill){
			case "Demo" :
				new SjakkGUI("User one",ip,port);
				new SjakkGUI("User two",ip,port);
				break;
			case "client":
				new SjakkGUI("User one",ip,port);
				break;
			default: 
				new SjakkGUI("TypeSpill",ip,port); // hvis man har satt en brukernavn i config filen
				break;
			
		}
		
		
		
		
	}

}
