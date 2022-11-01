package endpoint;

import org.apache.jena.fuseki.main.FusekiServer;
import org.apache.jena.fuseki.system.FusekiLogging;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.rdf.model.ModelFactory;

public class Endpoint {
    public static void main(String[] args) {
    	final String DB_NAME = "/valuemapontology";
    	final String OWL_FILE = "ValuesMapOntology.owl";

    	// set logging to log everything to console
        FusekiLogging.setLogging();

        // load the value map ontology into model and the into the dataset for the endpoint
        OntModel valueMapOnt = ModelFactory.createOntologyModel();
        
        try {
	        valueMapOnt.read(OWL_FILE, "RDF/XML");
	        Dataset ds = DatasetFactory.create(valueMapOnt);

	        // create the endpoint with defaults and add the ontology
	        FusekiServer.create().add(DB_NAME, ds).start();
        } catch (Exception e) {
        	System.out.println("Could not start endpoint. " + e);
        }


    }
}