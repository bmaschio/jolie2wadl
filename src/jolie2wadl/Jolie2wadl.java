/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jolie2wadl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jolie.lang.parse.ParserException;
import jolie.lang.parse.SemanticException;
import jolie.lang.parse.ast.Program;
import jolie.lang.parse.util.ParsingUtils;
import jolie.lang.parse.util.ProgramInspector;

/**
 *
 * @author maschio
 */
public class Jolie2wadl {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            try {
                // TODO code application logic here
                
                Jolie2WadlCommandLineParser cmdParser = Jolie2WadlCommandLineParser.create(args, Jolie2wadl.class.getClassLoader());
                args = cmdParser.arguments();
                
                Program program = ParsingUtils.parseProgram(
                        cmdParser.programStream(),
                        cmdParser.programFilepath().toURI(), cmdParser.charset(),
                        cmdParser.includePaths(), cmdParser.jolieClassLoader(), cmdParser.definedConstants());
                
                //Program program = parser.parse();
                ProgramInspector inspector = ParsingUtils.createInspector(program);
                
                WADLDocCreator document = new WADLDocCreator(inspector, program.context().source());
                
                    String outfile = cmdParser.getOutputFile();
                    
                    String portName = cmdParser.getPortName();
                    String address = cmdParser.getAddress();
                    
                    document.ConvertDocument(outfile, tns, portName, address );
              
            } catch (IOException ex) {
                Logger.getLogger(Jolie2wadl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserException ex) {
            Logger.getLogger(Jolie2wadl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SemanticException ex) {
            Logger.getLogger(Jolie2wadl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
