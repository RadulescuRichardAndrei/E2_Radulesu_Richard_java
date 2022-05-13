package Data;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.flow.EdmondsKarpMFImpl;
import org.jgrapht.alg.interfaces.MaximumFlowAlgorithm;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

public class SocialNetwork {
    private static Graph<ClientData, DefaultEdge> Clients = new SimpleGraph<>(DefaultEdge.class);


    public static void initiate(String pathFile) {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(pathFile));

            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("User");
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) node;
                    byte[] bytes = new byte[16];
                    for (int j = 0; j < 16; j++)
                        bytes[j] = Byte.parseByte(el.getElementsByTagName("hash").item(j).getTextContent());

                    ClientData clientData = new ClientData(el.getElementsByTagName("username").item(0).getTextContent(),
                            bytes);

                    Clients.addVertex(clientData);
                }
            }

        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }


    }

    private static void addToXML(ClientData clData) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {


            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File("D:\\facultate\\E2_java_scripts\\GitHub\\Lab10\\src\\main\\resources\\users.xml"));

            doc.getDocumentElement().normalize();

            Element user = doc.createElement("User");

            Element username = doc.createElement("username");
            username.appendChild(doc.createTextNode(clData.getUsername()));
            user.appendChild(username);
            Element hashCode = doc.createElement("passwordHash");


            byte[] bytes = clData.getPasswordHash();
            for (int i = 0; i < 16; i++) {
                Element hash = doc.createElement("hash");
                hash.appendChild(doc.createTextNode(String.valueOf(bytes[i])));
                hashCode.appendChild(hash);
            }
            user.appendChild(hashCode);

            doc.getDocumentElement().appendChild(user);


            DOMSource source = new DOMSource(doc);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer tr = tf.newTransformer();


            StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);
            tr.transform(source, result);
            String xmlString = sw.toString();

            File file = new File("D:\\facultate\\E2_java_scripts\\GitHub\\Lab10\\src\\main\\resources\\users.xml");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(xmlString);
            bw.flush();
            bw.close();

        } catch (TransformerException | IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }

    }

    public static String addClientToSocialNetwork(ClientData clData) {
        if (Clients.vertexSet().stream().filter(clientData -> clientData.getUsername().equals(clData.getUsername())).findFirst().orElse(null) == null) {
            Clients.addVertex(clData);
            addToXML(clData);
            return "Inregistrare reusita";
        } else return "User dejar existent";
    }

    public static String authenticateSocialNetwork(ClientData clData) {
        if (Clients.vertexSet().stream().filter(clientData -> clientData.getUsername().equals(clData.getUsername())).findFirst().orElse(null) != null) {
            for (ClientData cl : Clients.vertexSet())
                if (cl.getUsername().equals(clData.getUsername()) && cl.authenticateByHash(clData.getPasswordHash())) {
                    return "Autentificare reusita";
                }

        } else return "User inexistent";
        return "Autentificare nereusita";
    }

    public static void addFriends(ClientData clData, List<String> friends) {
        ClientData lookFor = Clients.vertexSet().stream().filter(clientData -> clientData.getUsername().equals(clData.getUsername())).findFirst().orElse(null);
        if (lookFor != null) {
            for (int i = friends.size() - 1; i >= 0; i--) {
                String cl = friends.get(i);
                ClientData newFriend = Clients.vertexSet().stream().filter(clientData -> clientData.getUsername().equals(cl)).findFirst().orElse(null);
                if (newFriend != null)
                    Clients.addEdge(lookFor, newFriend);
                else
                    friends.remove(cl);
            }


        }

    }

    public static List<String> listFriends(String cl) {
        ClientData clData = Clients.vertexSet().stream().filter(clientData -> clientData.getUsername().equals(cl)).findFirst().orElse(null);

        if (clData != null) {

            List<ClientData> clientDataList = Graphs.neighborListOf(Clients, clData);
            List<String> friendsUsernames = new LinkedList<>();
            clientDataList.forEach(clientData -> friendsUsernames.add(clientData.getUsername()));
            return friendsUsernames;
        }
        return null;
    }

    public static int numberOfFriends(String cl) {
        ClientData clData = Clients.vertexSet().stream().filter(clientData -> clientData.getUsername().equals(cl)).findFirst().orElse(null);
        if (clData != null)
            return Graphs.neighborListOf(Clients, clData).size();
        return 0;
    }
    public static void propertiesSocialNetwork(){

        System.out.println("Density:");
        Clients.vertexSet().stream().forEach(clientData ->  System.out.println( clientData.getUsername() + ": " +
                numberOfFriends(clientData.getUsername())/Clients.vertexSet().size()));

        System.out.println("Centrality:");
        Clients.vertexSet().stream().sorted(Comparator.comparing(clientData -> numberOfFriends(clientData.getUsername()))).forEach( clientData ->  System.out.println(clientData.getUsername()));
    }
    public static void cohesionSocialNetwork(){
        Graph<String, DefaultWeightedEdge> graphFlow=new DefaultDirectedGraph<>(DefaultWeightedEdge.class);
        graphFlow.addVertex("S");
        graphFlow.addVertex("T");
        for (ClientData cl:Clients.vertexSet()){
            graphFlow.addVertex("Left_" + cl.getUsername());
            graphFlow.addEdge("S","Left_" + cl.getUsername());
            graphFlow.addVertex(cl.getUsername());
            graphFlow.addEdge(cl.getUsername(),"T");
        }
        for(ClientData cl:Clients.vertexSet()){
            Graphs.neighborSetOf(Clients,cl).stream().forEach( clientData -> graphFlow.addEdge("Left_"+cl.getUsername(),clientData.getUsername()));
        }
        MaximumFlowAlgorithm<String,DefaultWeightedEdge> mf=new EdmondsKarpMFImpl<>(graphFlow);
        System.out.println(mf.getMaximumFlow("S","T"));
    }

}
