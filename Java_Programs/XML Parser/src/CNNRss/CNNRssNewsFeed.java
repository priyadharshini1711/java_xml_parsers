package CNNRss;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CNNRssNewsFeed {
	public static void main(String args[]) {
		try {
			File inputFile = new File("D:\\Java_Programs\\XML Parser\\src\\CNNRss.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("item");
			System.out.println("----------------------------");
			System.out.println("Total Number of States " + nList.getLength());

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("Title " + eElement.getElementsByTagName("title").item(0).getTextContent());
					// String pubDate =
					// eElement.getElementsByTagName("pubDate").item(0).getTextContent().toString();
//					if (!("").equals(pubDate) && pubDate != null)
//						System.out.println(
//								"Pub Date " + eElement.getElementsByTagName("pubDate").item(0).getTextContent());

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
