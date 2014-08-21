package validationSystem;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;
public class ReadXMLFile {
	
	List<Person> peopleList = new ArrayList<Person>();
	
   public static void main(String argv[]) {
    try {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser saxParser = factory.newSAXParser();
               DefaultHandler handler = new DefaultHandler() {
               boolean btname = false;
               boolean bcash = false;
               boolean boname = false;
               boolean boquantity = false;
               String tradername= "";
               double cash = 0.0;
               ArrayList<String> orename = new ArrayList<String>();
               ArrayList<Integer> orequantity = new ArrayList<Integer>();
               HashMap<String,Integer> orelist = new HashMap<String,Integer>();
 
               public void startElement(String uri, String localName,String qName,
                Attributes attributes) throws SAXException {
                              System.out.println("Start Element :" + qName);
                              if (qName.equalsIgnoreCase("tradername")) {
                                             btname = true;
                              }
                              if (qName.equalsIgnoreCase("cash")) {
                                             bcash = true;
                              }
                              if (qName.equalsIgnoreCase("orename")) {
                                             boname = true;
                              }
                              if (qName.equalsIgnoreCase("orequantity")) {
                                             boquantity = true;
                              }
               }
               public void endElement(String uri, String localName,
                              String qName) throws SAXException {
                              System.out.println("End Element :" + qName);
 
                              if(qName.equalsIgnoreCase("traderinfo")) {
                                             //create new object
                            	  for(int i = 0; i < orename.size(); i++){
                            		  orelist.put(orename.get(i),orequantity.get(i));
                            	  }
                            	  Person p = new Person(tradername, cash, orelist);
                            	  
            System.out.println("End Element :" + qName);
                                             //dump arraylist and clear instances
            orename.clear();
            orequantity.clear();
           
                              }
               }
               public void characters(char ch[], int start, int length) throws SAXException {
                              if (btname) {
                                             System.out.println("Trader: " + new String(ch, start, length).trim());
                                             tradername = new String(ch, start, length).trim();
                                             btname = false;
                              }
                              if (bcash) {
                                             System.out.println("Cash: " + new String(ch, start, length).trim());
                                             cash = Double.parseDouble(new String(ch, start, length).trim());
                                             bcash = false;
                              }
                              if (boname) {
                                             System.out.println("Ore Name: " + new String(ch, start, length).trim());
                                             orename.add(new String(ch, start, length).trim());
                                             boname = false;
                              }
                              if (boquantity) {
                                             System.out.println("Ore Quantity: " + new String(ch, start, length).trim());
                                             orequantity.add(Integer.parseInt((new String(ch, start, length).trim())));
                                             boquantity = false;
                              }
               }
     };
       saxParser.parse("input.txt", handler);
     } catch (Exception e) {
       e.printStackTrace();
     }
   }
}