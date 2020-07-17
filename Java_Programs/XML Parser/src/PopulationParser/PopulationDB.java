package PopulationParser;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PopulationDB {
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

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample", "root", "root");
			PreparedStatement preparedStatement = con.prepareStatement("insert into population value(?,?,?,?,?,?)");

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
					preparedStatement.setString(1, id);
					preparedStatement.setInt(2, zip_code);
					preparedStatement.setInt(3, total_population);
					preparedStatement.setInt(4, total_males);
					preparedStatement.setInt(5, total_females);
					preparedStatement.setInt(6, total_households);
					preparedStatement.executeQuery();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
