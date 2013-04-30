package com.mytree.utility;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Jeanma
 * Date: 13-4-29
 * Time: 下午2:16
 * To change this template use File | Settings | File Templates.
 */
public class XmlHelper {
    public static <T>T parse(String xml) throws ParserConfigurationException, SAXException {
        SAXParserFactory factory=SAXParserFactory.newInstance();
        SAXParser parse=factory.newSAXParser();
        return null;
    }

    public static <T>String toXml(T obj){
        return null;
    }
}

class XmlHandler<T> extends DefaultHandler{
    private T result ;
    private StringBuilder builder;

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();    //To change body of overridden methods use File | Settings | File Templates.
        builder=new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);    //To change body of overridden methods use File | Settings | File Templates.
        if(localName.equalsIgnoreCase("file")){
//            result=
        }
    }
}
