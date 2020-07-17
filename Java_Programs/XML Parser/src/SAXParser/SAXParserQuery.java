package SAXParser;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXParserQuery {

	public static void main(String[] args) {

		try {
			File inputFile = new File("D:\\Java_Programs\\XML Parser\\src\\student.xml");
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			Handler userhandler = new Handler();
			saxParser.parse(inputFile, userhandler);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Handler extends DefaultHandler {

	boolean bFirstName = false;
	boolean bLastName = false;
	boolean bNickName = false;
	boolean bMarks = false;
	String rollNo = null;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase("student")) {
			rollNo = attributes.getValue("rollno");
		}
		if (("393").equals(rollNo) && qName.equalsIgnoreCase("student")) {
			System.out.println("Start Element :" + qName);
		}
		if (qName.equalsIgnoreCase("firstname")) {
			bFirstName = true;
		} else if (qName.equalsIgnoreCase("lastname")) {
			bLastName = true;
		} else if (qName.equalsIgnoreCase("nickname")) {
			bNickName = true;
		} else if (qName.equalsIgnoreCase("marks")) {
			bMarks = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if (qName.equalsIgnoreCase("student")) {

			if (("393").equals(rollNo) && qName.equalsIgnoreCase("student"))
				System.out.println("End Element :" + qName);
		}
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {

		if (bFirstName && ("393").equals(rollNo)) {
			// age element, set Employee age
			System.out.println("First Name: " + new String(ch, start, length));
			bFirstName = false;
		} else if (bLastName && ("393").equals(rollNo)) {
			System.out.println("Last Name: " + new String(ch, start, length));
			bLastName = false;
		} else if (bNickName && ("393").equals(rollNo)) {
			System.out.println("Nick Name: " + new String(ch, start, length));
			bNickName = false;
		} else if (bMarks && ("393").equals(rollNo)) {
			System.out.println("Marks: " + new String(ch, start, length));
			bMarks = false;
		}
	}
}