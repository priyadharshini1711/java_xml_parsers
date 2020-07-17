import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomParserQuery {
	public static void main(String args[]) {
		try {
			File inputFile = new File("D:\\Java_Programs\\XML Parser\\src\\input.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("food");
			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					int calories = Integer
							.parseInt((eElement.getElementsByTagName("calories").item(0).getTextContent()));
					if (calories < 800) {
						System.out.println(eElement.getElementsByTagName("name").item(0).getTextContent());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
