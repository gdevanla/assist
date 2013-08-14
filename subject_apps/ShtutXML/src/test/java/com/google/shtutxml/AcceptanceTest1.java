package com.google.shtutxml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class AcceptanceTest1 {
	 public static void printDoc(Node e) {
	        new DocumentPrinter().visit(e, DepthDocumentVisitor.VisitOrder.DEPTH_FIRST);
	    }

	    public static Document parse(InputStream in) throws ParserConfigurationException, SAXException, IOException {
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();

	        return db.parse(in);
	    }
	public static void AcceptanceTesting1()
	{
		try {
        Document xmlDocument = parse(Example.class.getResourceAsStream("AcceptanceTest1.xml"));
        StrXML demo = new StrXML(xmlDocument);

        System.out.println("************* BASE DOCUMENT *****************");
        printDoc(xmlDocument);

        System.out.println("\n*** Text ***");
        System.out.println("\"" + demo.getText() + "\"");

        System.out.println("\n*** Node of each text segment ***");
        System.out.println(demo.toString());

        System.out.println("\n*** Offset testing ***");
        System.out.println("offset " + 0 + " is at " + demo.getOffset(0));
        //System.out.println("offset " + 40 + " is at " + demo.getOffset(40));
        //System.out.println("offset " + 80 + " is at " + demo.getOffset(80));

        System.out.println();

        System.out.println("*********** Manipulation *****************");
        
        // demo.splitSectionUpTo(demo.GetOffset(2), demo.GetOffset(2).getParentNode().getParentNode());
        demo.insertAndSplitInserted(xmlDocument.createElement("Des1"), demo.getOffset(14), demo.getOffset(19));
       // demo.insertAndSplitInserted(xmlDocument.createElement("Des2"), demo.getOffset(20), demo.getOffset(28));
       
        
        
        //demo.insertAndSplitInserted(xmlDocument.createElement("k"), demo.getOffset(1), demo.getOffset(4));

        printDoc(xmlDocument);

        demo.insertAndSplitParent(xmlDocument.createElement("Unwanted"), demo.getOffset(19), demo.getOffset(20));

        try
        {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(xmlDocument);
        StreamResult streamResult =  new StreamResult(new File("D:\\AcceptanceTest1.xml"));
        transformer.transform(source, streamResult);
        }
        catch(Exception ex)
        {
        	
        }
        
        printDoc(xmlDocument);
        System.out.println("\n*** Text ***");
        System.out.println("\"" + demo.getText() + "\"");

        System.out.println("\n*** Node of each text segment ***");
        System.out.println(demo.toString());

        
    } catch (ParserConfigurationException ex) {
        Logger.getLogger(Example.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SAXException ex) {
        Logger.getLogger(Example.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(Example.class.getName()).log(Level.SEVERE, null, ex);
    }
	}
	
	public static void AcceptanceTest2()
	{
try {
        	
        	//AcceptanceTest1.AcceptanceTesting1();
        	Node n1,n2;
        	String nodeText;
        	int i=0;
        	int splitLen,startPos,endPos,splitPos,prevLen;
            Document xmlDocument = parse(Example.class.getResourceAsStream("test.xml"));
            StrXML demo = new StrXML(xmlDocument);

            System.out.println("************* BASE DOCUMENT *****************");
            printDoc(xmlDocument);

            System.out.println("\n*** Text ***");
            System.out.println("\"" + demo.getText() + "\"");

            System.out.println("\n*** Node of each text segment ***");
            System.out.println(demo.toString());

            System.out.println("\n*** Offset testing ***");
            System.out.println("offset " + 0 + " is at " + demo.getOffset(0));
            //System.out.println("offset " + 40 + " is at " + demo.getOffset(40));
            //System.out.println("offset " + 80 + " is at " + demo.getOffset(80));

            System.out.println();

            System.out.println("*********** Manipulation *****************");
            // demo.GetOffset(3).textSection.wrapWith(demo.XMLDocument.createElement("u"));
            // demo.splitSectionUpTo(demo.GetOffset(2), demo.GetOffset(2).getParentNode().getParentNode());
            do
            {
            	n1=demo.getOffset(i).getParentNode();
            	nodeText=demo.getOffset(i).getTextContent();
            	if(n1.getNodeName().compareTo("Description")==0)
            	{
            		//splitLen=nodeText.indexOf(",");
            		System.out.println("Globaloffset" + demo.getOffset(i).textGlobalOffset);
            		System.out.println("Globaloffsetinsidenode" + demo.getOffset(i).textOffsetInsideNode);
            		System.out.println("Sectionoffset" + demo.getOffset(i).textSectionOffset);
            		int len[]=demo.getSpan(demo.getOffset(i).getParentNode());
            		startPos=len[0];
            		endPos=len[1];
            		System.out.println("Start pos:" + len[0] + "End pos:" + len[1]);
            		break;
            	}
            	i++;
            }while(true);
            splitLen=0;
            splitPos=i;
            prevLen=0;
            for(int j=0;j<=endPos-startPos;j++)
            {
            	splitLen=nodeText.indexOf(",", splitLen+1);
            	//j=splitLen;
            	if(splitLen>0)
            	{
            			
            	demo.insertAndSplitInserted(xmlDocument.createElement("SubDesc"), demo.getOffset(splitPos), demo.getOffset(splitPos+(splitLen-prevLen)));
            	demo.insertAndSplitParent(xmlDocument.createElement("Unwanted"), demo.getOffset(splitPos+splitLen-prevLen), demo.getOffset(splitPos+splitLen-prevLen+1));
               // demo.insertAndSplitInserted(xmlDocument.createElement("SubDesc"), demo.getOffset(i+splitLen), demo.getOffset(i+1+splitLen));
                
            	splitPos=splitLen+splitPos;
            	prevLen=prevLen+splitLen;
            	}
            	else
            		
            	{
            		 
            	
            		break;
            	}
            }
            demo.getOffset(endPos-1).textSection.wrapWith(xmlDocument.createElement("SubDesc"));
           
            printDoc(xmlDocument);

            

            try
            {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(xmlDocument);
            StreamResult streamResult =  new StreamResult(new File("D:\\AcceptanceTest2.xml"));
            transformer.transform(source, streamResult);
            }
            catch(Exception ex)
            {
            	
            }
            
            printDoc(xmlDocument);
            System.out.println("\n*** Text ***");
            System.out.println("\"" + demo.getText() + "\"");

            System.out.println("\n*** Node of each text segment ***");
            System.out.println(demo.toString());

            System.out.println("\n*** Offset testing ***");
            System.out.println("offset " + 0 + " is at " + demo.getOffset(0));
            System.out.println("offset " + 1 + " is at " + demo.getOffset(1));
            System.out.println("offset " + 2 + " is at " + demo.getOffset(2));
            System.out.println("offset " + 10 + " is at " + demo.getOffset(10));
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Example.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Example.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Example.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
}
