package PopulationParser;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PopulationDOMParser {
	public static void main(String args[]) {
		try {
			File inputFile = new File("D:\\Java_Programs\\XML Parser\\src\\population1.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("row");
			System.out.println("----------------------------");
			System.out.println("Total Number of States " + nList.getLength());

			int count = 0;

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String id = eElement.getAttribute("_id");
					int total_population = Integer
							.parseInt(eElement.getElementsByTagName("total_population").item(0).getTextContent());
					int total_males = Integer
							.parseInt(eElement.getElementsByTagName("total_males").item(0).getTextContent());
					int total_females = Integer
							.parseInt(eElement.getElementsByTagName("total_females").item(0).getTextContent());
					int total_households = Integer
							.parseInt(eElement.getElementsByTagName("total_households").item(0).getTextContent());
					int zip_code = Integer.parseInt(eElement.getElementsByTagName("zip_code").item(0).getTextContent());
					if (total_population >= 50000)
						count++;
				}
			}
			System.out.println("Total Number of States having Population above 50000 " + count);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
