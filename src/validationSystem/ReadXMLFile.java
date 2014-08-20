package validationSystem;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.*;
public class ReadXMLFile {
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
               ArrayList<Double> orequantity = new ArrayList<Double>();
 
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
                                             orequantity.add(Double.parseDouble(new String(ch, start, length).trim()));
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