package com.example.analysisxml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("TAG", "=======================pull方式解析xml==============");
        pullParseXml();
        Log.d("TAG", "===================================================");

        Log.d("TAG", "=========================SAX方式解析xml=============");
        saxParseXml();
        Log.d("TAG", "===================================================");

        Log.d("TAG", "=========================DOM方式解析xml=============");
        domParseXml();
        Log.d("TAG", "===================================================");

    }

    /**
     * 获取xml文件
     *
     * @return
     */
    private String getXml() {
        StringBuilder builder = new StringBuilder();
        try {
            InputStream is = getResources().getAssets().open("app.xml");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);
            String text = null;
            while ((text = reader.readLine()) != null) {
                builder.append(text);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    /**
     * pull方式解析xml
     * 解析结果
     * =======================pull方式解析xml==================
     * eventType====>0
     * nodeName====>null
     * eventType==============>2
     * nodeName====>apps
     * eventType==============>4
     * nodeName====>null
     * eventType==============>2
     * nodeName====>app
     * eventType==============>4
     * nodeName====>null
     * eventType==============>2
     * nodeName====>id
     * eventType==============>4
     * nodeName====>null
     * eventType==============>2
     * nodeName====>name
     * eventType==============>4
     * nodeName====>null
     * eventType==============>2
     * nodeName====>version
     * eventType==============>4
     * nodeName====>null
     * eventType==============>3
     * nodeName====>app
     * id===>1
     * name===>apple
     * version===>6.0
     * eventType==============>4
     * nodeName====>null
     * eventType==============>2
     * nodeName====>app
     * eventType==============>4
     * nodeName====>null
     * eventType==============>2
     * nodeName====>id
     * eventType==============>4
     * nodeName====>null
     * eventType==============>2
     * nodeName====>name
     * eventType==============>4
     * nodeName====>null
     * eventType==============>2
     * nodeName====>version
     * eventType==============>4
     * nodeName====>null
     * eventType==============>3
     * nodeName====>app
     * id===>2
     * name===>google
     * version===>8.0
     * eventType==============>4
     * nodeName====>null
     * eventType==============>2
     * nodeName====>app
     * eventType==============>4
     * nodeName====>null
     * eventType==============>2
     * nodeName====>id
     * eventType==============>4
     * nodeName====>null
     * eventType==============>2
     * nodeName====>name
     * eventType==============>4
     * nodeName====>null
     * eventType==============>2
     * nodeName====>version
     * eventType==============>4
     * nodeName====>null
     * eventType==============>3
     * nodeName====>app
     * id===>3
     * name===>android
     * version===>9.0
     * eventType==============>3
     * nodeName====>apps
     * eventType==============>1
     * ===================================================
     */
    private void pullParseXml() {
        try {
            String xmlText = getXml();
            Log.d("TAG", "xml: " + xmlText);
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlText));
            int eventType = xmlPullParser.getEventType();
            Log.d("TAG", "eventType====>" + eventType);
            String id = "";
            String name = "";
            String version = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                Log.d("TAG", "nodeName====>" + nodeName);
                switch (eventType) {

                    case XmlPullParser.START_TAG: {
                        if ("id".equals(nodeName)) {
                            id = xmlPullParser.nextText();
                        }
                        if ("name".equals(nodeName)) {
                            name = xmlPullParser.nextText();
                        }
                        if ("version".equals(nodeName)) {
                            version = xmlPullParser.nextText();
                        }
                        break;
                    }

                    case XmlPullParser.END_TAG: {
                        if ("app".equals(nodeName)) {
                            Log.d("TAG", "id===>" + id);
                            Log.d("TAG", "name===>" + name);
                            Log.d("TAG", "version===>" + version);
                        }
                        break;
                    }

                    default:
                        break;
                }
                eventType = xmlPullParser.next();
                Log.d("TAG", "eventType==============>" + eventType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * SAX方式解析xml
     * <p>
     * startDocument: 开始解析xml
     * startElement: 开始解析节点apps
     * startElement: uri===>|||||qName====>apps|||||attributes===>org.apache.harmony.xml.ExpatParser$CurrentAttributes@776b1d
     * characters: ch===>[C@ae9a192||||start====>0|||||length=====>4
     * startElement: 开始解析节点app
     * startElement: uri===>|||||qName====>app|||||attributes===>org.apache.harmony.xml.ExpatParser$CurrentAttributes@776b1d
     * characters: ch===>[C@1da07763||||start====>0|||||length=====>8
     * startElement: 开始解析节点id
     * startElement: uri===>|||||qName====>id|||||attributes===>org.apache.harmony.xml.ExpatParser$CurrentAttributes@776b1d
     * characters: ch===>[C@1da07763||||start====>0|||||length=====>1
     * characters: ch===>[C@1da07763||||start====>0|||||length=====>8
     * startElement: 开始解析节点name
     * startElement: uri===>|||||qName====>name|||||attributes===>org.apache.harmony.xml.ExpatParser$CurrentAttributes@776b1d
     * characters: ch===>[C@1da07763||||start====>0|||||length=====>5
     * characters: ch===>[C@1da07763||||start====>0|||||length=====>8
     * startElement: 开始解析节点version
     * startElement: uri===>|||||qName====>version|||||attributes===>org.apache.harmony.xml.ExpatParser$CurrentAttributes@776b1d
     * characters: ch===>[C@1da07763||||start====>0|||||length=====>3
     * characters: ch===>[C@1da07763||||start====>0|||||length=====>4
     * endElement: id is 1
     * endElement: name is apple
     * endElement: version is 6.0
     * characters: ch===>[C@1da07763||||start====>0|||||length=====>4
     * startElement: 开始解析节点app
     * startElement: uri===>|||||qName====>app|||||attributes===>org.apache.harmony.xml.ExpatParser$CurrentAttributes@776b1d
     * characters: ch===>[C@1da07763||||start====>0|||||length=====>8
     * startElement: 开始解析节点id
     * startElement: uri===>|||||qName====>id|||||attributes===>org.apache.harmony.xml.ExpatParser$CurrentAttributes@776b1d
     * characters: ch===>[C@1da07763||||start====>0|||||length=====>1
     * characters: ch===>[C@1da07763||||start====>0|||||length=====>8
     * startElement: 开始解析节点name
     * startElement: uri===>|||||qName====>name|||||attributes===>org.apache.harmony.xml.ExpatParser$CurrentAttributes@776b1d
     * characters: ch===>[C@1da07763||||start====>0|||||length=====>6
     * characters: ch===>[C@1da07763||||start====>0|||||length=====>8
     * startElement: 开始解析节点version
     * startElement: uri===>|||||qName====>version|||||attributes===>org.apache.harmony.xml.ExpatParser$CurrentAttributes@776b1d
     * characters: ch===>[C@1da07763||||start====>0|||||length=====>3
     * characters: ch===>[C@1da07763||||start====>0|||||length=====>4
     * endElement: id is 2
     * endElement: name is google
     * endElement: version is 8.0
     * characters: ch===>[C@1da07763||||start====>0|||||length=====>4
     * startElement: 开始解析节点app
     * startElement: uri===>|||||qName====>app|||||attributes===>org.apache.harmony.xml.ExpatParser$CurrentAttributes@776b1d
     * characters: ch===>[C@1da07763||||start====>0|||||length=====>8
     * startElement: 开始解析节点id
     * startElement: uri===>|||||qName====>id|||||attributes===>org.apache.harmony.xml.ExpatParser$CurrentAttributes@776b1d
     * characters: ch===>[C@1da07763||||start====>0|||||length=====>1
     * characters: ch===>[C@1da07763||||start====>0|||||length=====>8
     * startElement: 开始解析节点name
     * startElement: uri===>|||||qName====>name|||||attributes===>org.apache.harmony.xml.ExpatParser$CurrentAttributes@776b1d
     * characters: ch===>[C@1da07763||||start====>0|||||length=====>7
     * characters: ch===>[C@1da07763||||start====>0|||||length=====>8
     * startElement: 开始解析节点version
     * startElement: uri===>|||||qName====>version|||||attributes===>org.apache.harmony.xml.ExpatParser$CurrentAttributes@776b1d
     * characters: ch===>[C@1da07763||||start====>0|||||length=====>3
     * characters: ch===>[C@1da07763||||start====>0|||||length=====>4
     * endElement: id is 3
     * endElement: name is android
     * endElement: version is 9.0
     */
    class ContentHandler extends DefaultHandler {

        private String nodeName;
        private StringBuilder id;
        private StringBuilder name;
        private StringBuilder version;

        @Override
        public void startDocument() throws SAXException {
            id = new StringBuilder();
            name = new StringBuilder();
            version = new StringBuilder();
            Log.d("TAG", "startDocument: 开始解析xml");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            nodeName = localName;
            Log.d("TAG", "startElement: 开始解析节点" + nodeName);
            Log.d("TAG", "startElement: uri===>" + uri + "|||||qName====>" + qName + "|||||attributes===>" + attributes);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if ("id".equals(nodeName)) {
                id.append(ch, start, length);
            } else if ("name".equals(nodeName)) {
                name.append(ch, start, length);
            } else if ("version".equals(nodeName)) {
                version.append(ch, start, length);
            }
            Log.d("TAG", "characters: ch===>" + ch + "||||start====>" + start + "|||||length=====>" + length);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ("app".equals(localName)) {
                Log.d("TAG", "endElement: id is " + id.toString().trim());
                Log.d("TAG", "endElement: name is " + name.toString().trim());
                Log.d("TAG", "endElement: version is " + version.toString().trim());
                id.setLength(0);
                name.setLength(0);
                version.setLength(0);
            }
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }
    }

    private void saxParseXml() {

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            ContentHandler contentHandler = new ContentHandler();
            xmlReader.setContentHandler(contentHandler);
            String xml = getXml();
            Log.d("TAG", "saxParseXml: " + xml);
            xmlReader.parse(new InputSource(new StringReader(xml)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * DOM方式解析xml
     */
    private void domParseXml() {
        try {
            InputStream is = getResources().getAssets().open("app.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(is);
            Element element = (Element) document.getDocumentElement();
            NodeList nodeList = element.getElementsByTagName("app");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element app = (Element) nodeList.item(i);
                String id = app.getElementsByTagName("id").item(0).getTextContent();
                String name = app.getElementsByTagName("name").item(0).getTextContent();
                String version = app.getElementsByTagName("version").item(0).getTextContent();
                Log.d("TAG", "domParseXml: id is " + id);
                Log.d("TAG", "domParseXml: name is " + name);
                Log.d("TAG", "domParseXml: version is " + version);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}