/**
 * *************************************************************************
 * Copyright (C) 2006-09-10 by Claudio Guidi and Francesco Bullini
 * <cguidi@italianasoftware.com> <fbullini@italianasoftware.com> * * This
 * program is free software; you can redistribute it and/or modify * it under
 * the terms of the GNU Library General Public License as * published by the
 * Free Software Foundation; either version 2 of the * License, or (at your
 * option) any later version. * * This program is distributed in the hope that
 * it will be useful, * but WITHOUT ANY WARRANTY; without even the implied
 * warranty of * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the *
 * GNU General Public License for more details. * * You should have received a
 * copy of the GNU Library General Public * License along with this program; if
 * not, write to the * Free Software Foundation, Inc., * 59 Temple Place - Suite
 * 330, Boston, MA 02111-1307, USA. * * For details about the authors of this
 * software, see the AUTHORS file. *
 * *************************************************************************
 */
package jolie2wadl;


import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import jolie.lang.NativeType;
import jolie.lang.parse.ast.InputPortInfo;
import jolie.lang.parse.ast.InterfaceDefinition;
import jolie.lang.parse.ast.OneWayOperationDeclaration;
import jolie.lang.parse.ast.OperationDeclaration;
import jolie.lang.parse.ast.OutputPortInfo;
import jolie.lang.parse.ast.RequestResponseOperationDeclaration;
import jolie.lang.parse.ast.types.TypeDefinition;
import jolie.lang.parse.ast.types.TypeDefinitionLink;
import jolie.lang.parse.ast.types.TypeInlineDefinition;
import jolie.lang.parse.util.ProgramInspector;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 *
 * @author Francesco Bullini and Claudio Guidi
 */
public class WADLDocCreator {
    // Schema


    private int MAX_CARD = 2147483647;
    private String tns;
    private String tns_schema;
    private String tns_schema_prefix = "sch";


    private Writer fw = null;
    private List<String> rootTypes = new ArrayList<String>();
    private ProgramInspector inspector;
    private URI originalFile;
    private Document doc;

    public WADLDocCreator(ProgramInspector inspector, URI originalFile) {

        this.inspector = inspector;
        this.originalFile = originalFile;

    }

    public void initWsdl(String serviceName, String filename) {
        try {
       		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                doc = docBuilder.newDocument();

                
                
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(WADLDocCreator.class.getName()).log(Level.SEVERE, null, ex);
        }


        
    }

    public void ConvertDocument(String filename, String tns, String portName, String location)  {
                
            InputPortInfo[] inputPortList = inspector.getInputPorts(originalFile);
            Element rootElement = doc.createElement("application");
            for (InputPortInfo inputPort : inputPortList) {
                
                if (inputPort.id().equals(portName)) {
                    addPort(inputPort, rootElement);
                }
            }
                
                doc.appendChild(rootElement);
    }
    private void addPort(InputPortInfo port, Element e){
        Element resources = doc.createElement("resources");
        String  baseString  = port.location().toASCIIString();
        resources.setAttribute("base", baseString);
       
    }
}
