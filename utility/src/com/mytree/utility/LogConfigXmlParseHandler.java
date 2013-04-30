package com.mytree.utility;

import com.mytree.utility.modal.LogConfig;
import com.mytree.utility.modal.LogConfigForFile;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

public class LogConfigXmlParseHandler extends DefaultHandler {
    private List<LogConfig> result;
    private LogConfigForFile file;
    private String value;
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();    //To change body of overridden methods use File | Settings | File Templates.
        result=new ArrayList<LogConfig>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);    //To change body of overridden methods use File | Settings | File Templates.
        if(localName.equalsIgnoreCase("file")){
            file=new LogConfigForFile();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);    //To change body of overridden methods use File | Settings | File Templates.
        if(localName.equalsIgnoreCase("file")){
            result.add(file);
        }
        if(localName.equalsIgnoreCase("dir")){
            file.setDir(value);
        }
        if(localName.equalsIgnoreCase("name")){
            file.setFileName(value);
        }
        if(localName.equalsIgnoreCase("level")){
            file.setLevelText(value);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);    //To change body of overridden methods use File | Settings | File Templates.
        value=ch.toString();
        L.i(value);
    }

    public List<LogConfig> getLogConfig(){
        return result;
    }
}
