import org.intermine.neo4j.Neo4jLoaderProperties;
import org.intermine.neo4j.metadata.Model;
import org.intermine.neo4j.metadata.Neo4jSchemaGenerator;

import org.neo4j.driver.v1.Driver;

import java.io.IOException;

/**
 * Tests various Model methods
 *
 * @author Sam Hokin
 */
public class TestModel {

    public static void main(String[] args) throws IOException {

        if (args.length!=2) {
            System.out.println("Usage: TestModel <node label> <property | relationship label>");
            System.exit(0);
        }

        String nodeLabel = args[0];
        String propertyOrRelationship = args[1];
        
        // get the properties from the default file
        Neo4jLoaderProperties props = new Neo4jLoaderProperties();

        // Neo4j setup
        Driver driver = props.getGraphDatabaseDriver();

        // Create schema and get model
        Model model = Neo4jSchemaGenerator.getModel(driver);

        // Does node exist?
        boolean nodeExists = model.hasNodeLabel(nodeLabel);
        if (!nodeExists) {
            System.out.println("Node label "+nodeLabel+" DOES NOT exist in the graph.");
            System.exit(0);
        }
        System.out.println("Node label "+nodeLabel+" DOES exist in the graph.");


        // test if the given property exists (and there could be a relationship as well!)
        if (model.labelHasProperty(nodeLabel, propertyOrRelationship)) {
            System.out.println("Node label "+nodeLabel+" HAS property "+propertyOrRelationship);
        } else {
            System.out.println("Node label "+nodeLabel+" DOES NOT HAVE property "+propertyOrRelationship);
        }

        // test if the given relationship exists
        if (model.hasNodeRelationship(nodeLabel, propertyOrRelationship)) {
            System.out.println("Node label "+nodeLabel+" HAS relationship "+propertyOrRelationship);
            System.out.println("Relationship "+propertyOrRelationship+" has properties:"+model.getRelationshipProperties(propertyOrRelationship));
        } else {
            System.out.println("Node label "+nodeLabel+" DOES NOT HAVE relationship "+propertyOrRelationship);
        }

        // close out 
        driver.close();
        
    }
}
