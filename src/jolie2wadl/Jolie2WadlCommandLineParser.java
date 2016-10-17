package jolie2wadl;

import java.io.IOException;
import java.util.List;
import jolie.CommandLineException;
import jolie.CommandLineParser;

public class Jolie2WadlCommandLineParser extends CommandLineParser {

    private String portName;
    private String tns;
    private String address;
    private String outputFile;
    private String interfaceName;

    public String getPortName() {
        return portName;
    }

    public String getAddress() {
        return address;
    }
    
    public String getOutputFile() {
        return outputFile;
    }
    
    public String getInterfaceName() {
        return interfaceName;
    }

    private static class JolieDummyArgumentHandler implements CommandLineParser.ArgumentHandler {

        private String portName;
        private String address;
        private String outputFile;
        private String interfaceName;
            
        public int onUnrecognizedArgument(List< String> argumentsList, int index)
                throws CommandLineException {
            if ("--pN".equals(argumentsList.get(index))) {
                index++;
                portName = argumentsList.get(index);
            } else if ("--iN".equals(argumentsList.get(index))) {
                index++;
                interfaceName = argumentsList.get(index);
            } else if ("--addr".equals(argumentsList.get(index))) {
                index++;
                address = argumentsList.get(index);
            } else if ("--oF".equals(argumentsList.get(index))) {
                index++;
                outputFile = argumentsList.get(index);
            } else {
                throw new CommandLineException("Unrecognized command line option: " + argumentsList.get(index));
            }

            return index;
        }
    }

    public static Jolie2WadlCommandLineParser create(String[] args, ClassLoader parentClassLoader)
            throws CommandLineException, IOException {
        return new Jolie2WadlCommandLineParser(args, parentClassLoader, new JolieDummyArgumentHandler());
    }

    private Jolie2WadlCommandLineParser(String[] args, ClassLoader parentClassLoader, JolieDummyArgumentHandler argHandler)
            throws CommandLineException, IOException {
        super(args, parentClassLoader, argHandler);

        portName = argHandler.portName;
        address = argHandler.address;
        outputFile = argHandler.outputFile;
        interfaceName = argHandler.interfaceName;
    }
    @Override
    protected String getHelpString() {
        return "Usage: jolie2wadl --pN name_of_the_port --iN nameInterface --addr address_string --oF output_filename file.ol";
    }
}