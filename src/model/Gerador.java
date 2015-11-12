package model;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.acceleo.module.example.uml2java.helios.GenerateJava;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;

public class Gerador {
	private static Gerador instance = null;
	private ArrayList<Elemento> listaElementos;

	private Gerador() {
		listaElementos = new ArrayList<Elemento>();
	}

	public static synchronized Gerador getInstance() {
		if (instance == null)
			instance = new Gerador();
		return instance;
	}

	public ArrayList<Elemento> getListaElementos() {
		return listaElementos;
	}

	public Elemento getElementoPorNome(String nome) {
		for (Elemento elemento : listaElementos) {
			if (elemento.getNome().equalsIgnoreCase(nome))
				return elemento;
		}
		return null;
	}

	public void generateXMLFile(String savePath) throws IOException, Exception {
		// Criar uma String no formato XML para o inicio da criação do arquivo.
		String xmlHeader;
		xmlHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		xmlHeader += "<uml:Model xmi:version=\"2.1\" xmlns:xmi=\"http://schema.omg.org/spec/XMI/2.1\" xmlns:uml=\"http://www.eclipse.org/uml2/3.0.0/UML\" xmi:id=\"_Vg4sIISOEeWtOdWQEGeBEQ\" name=\"Model\">";
		xmlHeader += "<packageImport>";
		xmlHeader += "<importedPackage xmi:type=\"uml:Model\" href=\"pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.uml#_0\"/>";
		xmlHeader += "</packageImport>";
		xmlHeader += "<packagedElement xmi:type=\"uml:PrimitiveType\" xmi:id=\"String\" name=\"String\"/>";
		xmlHeader += "<packagedElement xmi:type=\"uml:PrimitiveType\" xmi:id=\"Integer\" name=\"Integer\"/>";
		xmlHeader += "<packagedElement xmi:type=\"uml:PrimitiveType\" xmi:id=\"int\" name=\"int\"/>";
		xmlHeader += "</uml:Model>";

		/*
		 * Cria um InputStream a partir do string "xmlHeader". Se você for
		 * parsiar("abrir") o XML de um arquivo .xml já existente então pegue o
		 * InputStream do File (arquivo) do qual você esta lendo.
		 */
		ByteArrayInputStream xml = new ByteArrayInputStream(new String(
				xmlHeader.getBytes(), "UTF-8").getBytes());
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		org.w3c.dom.Document doc = builder.parse(xml);

		/*
         * 
         */

		// Pega o nó raís da árvore do XML.
		org.w3c.dom.Element rootNode = doc.getDocumentElement();

		// Cria e adiciona o nó base da NFe no nó raís do XML.
		org.w3c.dom.Element packagedElement = doc
				.createElement("packagedElement");
		packagedElement.setAttribute("xmi:type", "uml:Package");
		packagedElement.setAttribute("name", "game");

		org.w3c.dom.Element element = doc.createElement("packagedElement");
		element.setAttribute("xmi:type", "uml:Class");
		element.setAttribute("xmi:id", "Personagem");
		element.setAttribute("name", "Personagem");
		packagedElement.appendChild(element);

		rootNode.appendChild(packagedElement);

		// Inicia a adição dos demais nós que devem conter o XML de acordo com a
		// NFe.
		generateHeaderNodes(doc, packagedElement);

		// Salva o documento XML no diretório passado como parâmetro.
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new FileOutputStream(savePath
				+ File.separator +"model1.uml"));

		TransformerFactory transFactory = TransformerFactory.newInstance();
		Transformer transformer = transFactory.newTransformer();
		transformer.transform(source, result);

		gerarClasses();
		JOptionPane.showMessageDialog(null, "Você gerou com sucesso.");
	}

	public void generateHeaderNodes(org.w3c.dom.Document doc,
			org.w3c.dom.Element root) throws Exception {

		ArrayList<Elemento> elementos = Gerador.getInstance()
				.getListaElementos();

		for (Elemento elemento : elementos) {

			org.w3c.dom.Element packagedElement = doc
					.createElement("packagedElement");
			packagedElement.setAttribute("xmi:type", "uml:Class");
			packagedElement.setAttribute("xmi:id", elemento.getNome());
			packagedElement.setAttribute("name", elemento.getNome());

			org.w3c.dom.Element generalization = doc
					.createElement("generalization");
			generalization.setAttribute("general", elemento.getExtend());

			packagedElement.appendChild(generalization);

			for (Atributo atributo : elemento.getAtributos()) {

				org.w3c.dom.Element ownedAttribute = doc
						.createElement("ownedAttribute");
				ownedAttribute.setAttribute("type", atributo.getTipo());
				ownedAttribute.setAttribute("name", atributo.getNome());

				if (atributo.getValor() != "" && atributo.getValor() != null) {
					org.w3c.dom.Element defaultValue = doc
							.createElement("defaultValue");
					defaultValue.setAttribute("xmi:type",
							tipo(atributo.getTipo()));
					defaultValue
							.setAttribute("value", atributo.getTipo()
									.equalsIgnoreCase("String") ? "\""
									+ atributo.getValor() + "\"" : "0"
									+ atributo.getValor());

					ownedAttribute.appendChild(defaultValue);
				}

				packagedElement.appendChild(ownedAttribute);
			}

			root.appendChild(packagedElement);
		}

	}

	public void gerarClasses() {
		try {
			URI modelURI = URI
					.createFileURI(new File("resource/model1.uml").getAbsolutePath());
			File folder = new File("C:/Users/Gerson/workspaceMM/Generation/src");

			List<String> arguments = new ArrayList<String>();

			/*
			 * If you want to change the content of this method, do NOT forget
			 * to change the "@generated" tag in the Javadoc of this method to
			 * "@generated NOT". Without this new tag, any compilation of the
			 * Acceleo module with the main template that has caused the
			 * creation of this class will revert your modifications.
			 */

			/*
			 * Add in this list all the arguments used by the starting point of
			 * the generation If your main template is called on an element of
			 * your model and a String, you can add in "arguments" this "String"
			 * attribute.
			 */

			GenerateJava generator = new GenerateJava(modelURI, folder,
					arguments);

			/*
			 * Add the properties from the launch arguments. If you want to
			 * programmatically add new properties, add them in
			 * "propertiesFiles" You can add the absolute path of a properties
			 * files, or even a project relative path. If you want to add
			 * another "protocol" for your properties files, please override
			 * "getPropertiesLoaderService(AcceleoService)" in order to return a
			 * new property loader. The behavior of the properties loader
			 * service is explained in the Acceleo documentation (Help -> Help
			 * Contents).
			 */
			//
			// for (int i = 2; i < args.length; i++) {
			// generator.addPropertiesFile(args[i]);
			// }

			generator.doGenerate(new BasicMonitor());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String tipo(String tipo) {
		switch (tipo) {
		case "int": {
			return "uml:LiteralInteger";
		}
		case "Integer": {
			return "uml:LiteralInteger";
		}
		case "String": {
			return "uml:LiteralString";
		}
		default:
			break;
		}
		return null;
	}
}
