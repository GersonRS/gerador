package model;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.acceleo.engine.AcceleoRuntimeException;
import org.eclipse.acceleo.module.example.uml2java.helios.GenerateJava;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Gerador {
	private static Gerador instance = null;
	private Game game;

	private Gerador() {
		game = new Game();
	}

	public static synchronized Gerador getInstance() {
		if (instance == null)
			instance = new Gerador();
		return instance;
	}

	public Game getGame() {
		return game;
	}

	private void generateHeaderNodes(Document doc, Element root)
			throws Exception {

		ArrayList<Elemento> elementos = game.getListaElementos();

		for (Elemento elemento : elementos) {

			Element packagedElement = doc.createElement("packagedElement");
			packagedElement.setAttribute("xmi:type", "uml:Class");
			packagedElement.setAttribute("xmi:id", elemento.getNome());
			packagedElement.setAttribute("name", elemento.getNome());

			if (!elemento.getExtend().equalsIgnoreCase("")) {
				Element generalization = doc.createElement("generalization");
				generalization.setAttribute("general", elemento.getExtend());
				generalization.setAttribute("xmi:id", gerarId());
				packagedElement.appendChild(generalization);

				if (elemento.getExtend().equalsIgnoreCase("Personagem")
						|| elemento.getExtend().equalsIgnoreCase("Elemento")
						|| elemento.getExtend().equalsIgnoreCase("Obstaculo")) {
					Element ownedComment = doc.createElement("ownedComment");
					ownedComment.setAttribute("xmi:id", gerarId());
					Node node = doc.createElement("body");
					node.setTextContent("core." + elemento.getExtend());
					ownedComment.appendChild(node);
					packagedElement.appendChild(ownedComment);
					if (elemento.getExtend().equalsIgnoreCase("Personagem")
							|| elemento.getExtend()
									.equalsIgnoreCase("Elemento")) {
						Element ownedComment_1 = doc
								.createElement("ownedComment");
						ownedComment_1.setAttribute("xmi:id", gerarId());
						Node node_1 = doc.createElement("body");
						node_1.setTextContent("core.Interacao");
						ownedComment_1.appendChild(node_1);
						Element ownedComment_2 = doc
								.createElement("ownedComment");
						ownedComment_2.setAttribute("xmi:id", gerarId());
						Node node_2 = doc.createElement("body");
						node_2.setTextContent("java.awt.geom.Rectangle2D");
						ownedComment_2.appendChild(node_2);
						Element ownedComment_3 = doc
								.createElement("ownedComment");
						ownedComment_3.setAttribute("xmi:id", gerarId());
						Node node_3 = doc.createElement("body");
						node_3.setTextContent("java.awt.Graphics2D");
						ownedComment_3.appendChild(node_3);
						if (game.isAcoes_colisao())
							packagedElement.appendChild(ownedComment_2);
						if (game.isAcoes_movimento())
							packagedElement.appendChild(ownedComment_1);
						packagedElement.appendChild(ownedComment_3);
					}
				}
			}

			for (Atributo atributo : elemento.getAtributos()) {

				Element ownedAttribute = doc.createElement("ownedAttribute");
				ownedAttribute.setAttribute("type", atributo.getTipo());
				ownedAttribute.setAttribute("name", atributo.getNome());

				if (atributo.getValor() != "" && atributo.getValor() != null) {
					Element defaultValue = doc.createElement("defaultValue");
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

	private String tipo(String tipo) {
		switch (tipo) {
		case "Decimal": {
			return "uml:LiteralInteger";
		}
		case "Integer": {
			return "uml:LiteralInteger";
		}
		case "String": {
			return "uml:LiteralString";
		}
		case "Boolean": {
			return "uml:LiteralBoolean";
		}
		default:
			return tipo;
		}
	}

	public void generateXMLFile() {
		InputStream is = null;
		try {
			is = new FileInputStream(new File("resource/core.uml"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setValidating(false);
			DocumentBuilder builder = factory.newDocumentBuilder();
			builder.setEntityResolver(new EntityResolver() {
				public InputSource resolveEntity(String publicId,
						String systemId) throws SAXException, IOException {
					return new InputSource(
							new ByteArrayInputStream(new byte[0]));
				}
			});

			if (is != null) {

				Document doc = builder.parse(is);
				Element docElement = doc.getDocumentElement();

				Element packagedCore = null;
				Element packagedGame = null;
				Element interacao = null;
				Element acoes = null;
				Element jogo = null;

				NodeList pacotes = docElement
						.getElementsByTagName("packagedElement");
				for (int i = 0; i < pacotes.getLength(); i++) {
					Element current = (Element) pacotes.item(i);
					if (current.getAttribute("name").equalsIgnoreCase("core")) {
						packagedCore = current;
					}
					if (current.getAttribute("name").equalsIgnoreCase("game")) {
						packagedGame = current;
					}
				}

				NodeList classesCore = packagedCore
						.getElementsByTagName("packagedElement");

				for (int i = 0; i < classesCore.getLength(); i++) {
					Element current = (Element) classesCore.item(i);
					if (current.getAttribute("name").equalsIgnoreCase(
							"Interacao")) {
						interacao = current;
					}
					if (current.getAttribute("name").equalsIgnoreCase("Acoes")) {
						acoes = current;
					}
				}

				jogo = (Element) packagedGame.getElementsByTagName(
						"packagedElement").item(0);

				ArrayList<Cenario> cenarios = game.getListaCenarios();
				for (Cenario cenario : cenarios) {
					Element ownedOperation = doc
							.createElement("ownedOperation");
					ownedOperation.setAttribute("xmi:id", gerarId());
					ownedOperation.setAttribute("name", "loadCenario");

					Element ownedParameter = doc
							.createElement("ownedParameter");
					ownedParameter.setAttribute("xmi:id", gerarId());
					ownedParameter.setAttribute("name", cenario.getNome());
					ownedOperation.appendChild(ownedParameter);

					jogo.appendChild(ownedOperation);

					for (Entry<String, String> teleport : cenario
							.getTeleporte().entrySet()) {
						Element ownedOperation_teleport = doc
								.createElement("ownedOperation");
						ownedOperation_teleport.setAttribute("xmi:id",
								gerarId());
						ownedOperation_teleport.setAttribute("name",
								"addTeleport");

						Element ownedParameter_1 = doc
								.createElement("ownedParameter");
						ownedParameter_1.setAttribute("xmi:id", gerarId());
						ownedParameter_1
								.setAttribute("name", cenario.getNome());

						Element ownedParameter_2 = doc
								.createElement("ownedParameter");
						ownedParameter_2.setAttribute("xmi:id", gerarId());
						ownedParameter_2.setAttribute("name",
								teleport.getValue());

						Element ownedParameter_3 = doc
								.createElement("ownedParameter");
						ownedParameter_3.setAttribute("xmi:id", gerarId());
						ownedParameter_3
								.setAttribute("name", teleport.getKey());
						ownedParameter_3.setAttribute("direction", "return");

						ownedOperation_teleport.appendChild(ownedParameter_1);
						ownedOperation_teleport.appendChild(ownedParameter_2);
						ownedOperation_teleport.appendChild(ownedParameter_3);

						jogo.appendChild(ownedOperation_teleport);
					}

					for (Entry<String, String> layers : cenario.getLayers()
							.entrySet()) {
						if (layers.getValue().equalsIgnoreCase("superior")) {
							Element ownedOperation_layer = doc
									.createElement("ownedOperation");
							ownedOperation_layer.setAttribute("xmi:id",
									gerarId());
							ownedOperation_layer.setAttribute("name",
									"configLayerSuperior");

							Element ownedParameter_1 = doc
									.createElement("ownedParameter");
							ownedParameter_1.setAttribute("xmi:id", gerarId());
							ownedParameter_1.setAttribute("name",
									cenario.getNome());

							Element ownedParameter_2 = doc
									.createElement("ownedParameter");
							ownedParameter_2.setAttribute("xmi:id", gerarId());
							ownedParameter_2.setAttribute("name",
									layers.getKey());

							ownedOperation_layer.appendChild(ownedParameter_1);
							ownedOperation_layer.appendChild(ownedParameter_2);

							jogo.appendChild(ownedOperation_layer);
						} else if (layers.getValue().equalsIgnoreCase(
								"inferior")) {
							Element ownedOperation_layer = doc
									.createElement("ownedOperation");
							ownedOperation_layer.setAttribute("xmi:id",
									gerarId());
							ownedOperation_layer.setAttribute("name",
									"configLayerInferior");

							Element ownedParameter_1 = doc
									.createElement("ownedParameter");
							ownedParameter_1.setAttribute("xmi:id", gerarId());
							ownedParameter_1.setAttribute("name",
									cenario.getNome());

							Element ownedParameter_2 = doc
									.createElement("ownedParameter");
							ownedParameter_2.setAttribute("xmi:id", gerarId());
							ownedParameter_2.setAttribute("name",
									layers.getKey());

							ownedOperation_layer.appendChild(ownedParameter_1);
							ownedOperation_layer.appendChild(ownedParameter_2);

							jogo.appendChild(ownedOperation_layer);
						}
					}
				}

				if (game.isInteracoes_mouse()) {
					boolean tem = false;
					NodeList generalization = interacao
							.getElementsByTagName("generalization");
					for (int i = 0; i < generalization.getLength(); i++) {
						Element general = (Element) generalization.item(i);
						if (general.getAttribute("general").equalsIgnoreCase(
								"MouseInputListener")) {
							tem = true;
						}
					}
					if (!tem) {
						Element general = doc.createElement("generalization");
						general.setAttribute("xmi:id", gerarId());
						general.setAttribute("general", "MouseInputListener");
						interacao.appendChild(general);
					}
				} else {
					NodeList generalization = interacao
							.getElementsByTagName("generalization");
					for (int i = 0; i < generalization.getLength(); i++) {
						Element general = (Element) generalization.item(i);
						if (general.getAttribute("general").equalsIgnoreCase(
								"MouseInputListener")) {
							interacao.removeChild(general);
						}
					}
				}

				if (game.isInteracoes_teclado()) {
					boolean tem = false;
					NodeList generalization = interacao
							.getElementsByTagName("generalization");
					for (int i = 0; i < generalization.getLength(); i++) {
						Element general = (Element) generalization.item(i);
						if (general.getAttribute("general").equalsIgnoreCase(
								"KeyListener")) {
							tem = true;
						}
					}
					if (!tem) {
						Element general = doc.createElement("generalization");
						general.setAttribute("xmi:id", gerarId());
						general.setAttribute("general", "KeyListener");
						interacao.appendChild(general);
					}
				} else {
					NodeList generalization = interacao
							.getElementsByTagName("generalization");
					for (int i = 0; i < generalization.getLength(); i++) {
						Element general = (Element) generalization.item(i);
						if (general.getAttribute("general").equalsIgnoreCase(
								"KeyListener")) {
							interacao.removeChild(general);
						}
					}
				}

				if (game.isAcoes_colisao()) {
					boolean tem = false;
					NodeList generalization = acoes
							.getElementsByTagName("generalization");
					for (int i = 0; i < generalization.getLength(); i++) {
						Element general = (Element) generalization.item(i);
						if (general.getAttribute("general").equalsIgnoreCase(
								"Colisao")) {
							tem = true;
						}
					}
					if (!tem) {
						Element general = doc.createElement("generalization");
						general.setAttribute("xmi:id", gerarId());
						general.setAttribute("general", "Colisao");
						acoes.appendChild(general);
					}
				} else {
					NodeList generalization = acoes
							.getElementsByTagName("generalization");
					for (int i = 0; i < generalization.getLength(); i++) {
						Element general = (Element) generalization.item(i);
						if (general.getAttribute("general").equalsIgnoreCase(
								"Colisao")) {
							acoes.removeChild(general);
						}
					}
				}

				if (game.isAcoes_movimento()) {
					boolean tem = false;
					NodeList generalization = acoes
							.getElementsByTagName("generalization");
					for (int i = 0; i < generalization.getLength(); i++) {
						Element general = (Element) generalization.item(i);
						if (general.getAttribute("general").equalsIgnoreCase(
								"Movimento")) {
							tem = true;
						}
					}
					if (!tem) {
						Element general = doc.createElement("generalization");
						general.setAttribute("xmi:id", gerarId());
						general.setAttribute("general", "Movimento");
						acoes.appendChild(general);
					}
				} else {
					NodeList generalization = acoes
							.getElementsByTagName("generalization");
					for (int i = 0; i < generalization.getLength(); i++) {
						Element general = (Element) generalization.item(i);
						if (general.getAttribute("general").equalsIgnoreCase(
								"Movimento")) {
							acoes.removeChild(general);
						}
					}
				}

				generateHeaderNodes(doc, packagedGame);

				// Salva o documento XML no diretório passado como parâmetro.
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new FileOutputStream(
						new File("resource/model.uml").getAbsolutePath()));

				TransformerFactory transFactory = TransformerFactory
						.newInstance();
				Transformer transformer = transFactory.newTransformer();
				transformer.transform(source, result);

				gerarClasses();
				JOptionPane.showMessageDialog(null, "Você gerou com sucesso.");
			}else{
				JOptionPane.showMessageDialog(null, "o Arquivo não foi encontrado. Tente novamente");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

	}

	private String gerarId() {
		Random r = new Random();
		String retorno = "";
		String caracteres = "abcdefghijkmnopqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ123456789012345678901234567890_-";

		int len = caracteres.length();

		for (int i = 0; i < 23; i++) {
			retorno += caracteres.charAt(r.nextInt(len));
		}

		return retorno;
	}

	public void gerarClasses() {
		URI modelURI = URI.createFileURI(new File("resource/model.uml")
				.getAbsolutePath());
		File folder = new File("C:/Users/Gerson/workspaceMM/Generation/src");
		List<String> arguments = new ArrayList<String>();
		try {
			GenerateJava generator = new GenerateJava(modelURI, folder,
					arguments);
			generator.doGenerate(new BasicMonitor());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (AcceleoRuntimeException e) {
			JOptionPane
					.showMessageDialog(
							null,
							"a geração dos Arquivos anteriores ainda esta sendo processada. Espera um pouco");
		}
	}
}
