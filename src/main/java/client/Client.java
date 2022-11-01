package client;

import java.util.ArrayList;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.sparql.exec.http.QueryExecutionHTTP;

public class Client {
	private static final String ENDPOINT = "http://localhost:3330/valuemapontology";
	
    public static void main(String[] args) {
        ArrayList<SparqlQuery> queries = new ArrayList<>();
    	
        
        // load in the queries
    	try {        	
    		queries.add(new SparqlQuery("select_achievement_value_items.txt", QueryType.SELECT));
    		queries.add(new SparqlQuery("select_definitions.txt", QueryType.SELECT));
    		queries.add(new SparqlQuery("select_overall_direct_links.txt", QueryType.SELECT));
	        	
    		queries.add(new SparqlQuery("ask_explicit_link.txt", QueryType.ASK));
    		queries.add(new SparqlQuery("ask_implicit_link.txt", QueryType.ASK));
	        	
    		queries.add(new SparqlQuery("construct.txt", QueryType.CONSTRUCT));
    		queries.add(new SparqlQuery("describe.txt", QueryType.DESCRIBE));
        } catch (Exception ex) {
            System.out.println("Faild to load Data/Query: " + ex);
        }
    	
    	// attempt to process the queries
    	try {
    		for(SparqlQuery query : queries) { 
        		System.out.println("\n\n------------ Executing Query ------------\n\n" + query.getQuery().toString());
        		query_remote(query);
        	}
    	} catch (Exception ex) {
    		System.out.println("Faild to process query: " + ex);
    	}
    }

    public static void query_remote(SparqlQuery sparqlQuery) {
    	QueryExecution qexec = QueryExecutionHTTP.service(ENDPOINT, sparqlQuery.getQuery()); 
        
    	try {
        	switch ( sparqlQuery.getQueryType() ){
        	case SELECT: 
        		ResultSet results = qexec.execSelect();
        		ResultSetFormatter.out(System.out, results, sparqlQuery.getQuery());
        		break;
        	
        	case ASK: 
        		ResultSetFormatter.out(System.out, qexec.execAsk());
        		break;
        	
        	case CONSTRUCT: 
        		qexec.execConstruct().write(System.out);
        		break;
        	
        	case DESCRIBE:
        		qexec.execDescribe().write(System.out);
        		break;
        	}
        } finally {
            qexec.close();
        }
    }
}


enum QueryType {
	SELECT, ASK, CONSTRUCT, DESCRIBE
}

class SparqlQuery {
	private Query query;
	private QueryType qtype;
	
	public SparqlQuery(String filename, QueryType qtype) {
		this.query = QueryFactory.read(filename);
		System.out.println("added query from " + filename);
		this.qtype = qtype;
	}
	
	public Query getQuery() { return this.query; }
	
	public QueryType getQueryType() { return this.qtype; }
}
