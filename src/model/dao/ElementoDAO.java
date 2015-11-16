package model.dao;

import java.util.ArrayList;

import model.Elemento;
import model.Facada;
import model.Gerador;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ElementoDAO {

	public static ArrayList<Elemento> getElementos(Element packagedGame) {
		ArrayList<Elemento> elementos = new ArrayList<Elemento>();

		NodeList classesGame = packagedGame
				.getElementsByTagName("packagedElement");

		for (int i = 0; i < classesGame.getLength(); i++) {
			Element e = (Element) classesGame.item(i);
			Element generalization = (Element) e.getElementsByTagName(
					"generalization").item(0);
			if (!e.getAttribute("name").equalsIgnoreCase("Jogo")) {

				String general = "";
				if (generalization != null)
					general = generalization.getAttribute("general");

				Elemento elemento = new Elemento(e.getAttribute("name"),
						general);

				elemento.setAtributos(AtributoDAO.getAtributos(e));
				elementos.add(elemento);
			}
		}
		return elementos;
	}

	public static void setElementos(Document doc, Element packagedGame) {
		
		ArrayList<Elemento> elementos = Facada.getInstance().getListaElementos();

		for (Elemento elemento : elementos) {

			Element packagedElement = doc.createElement("packagedElement");
			packagedElement.setAttribute("xmi:type", "uml:Class");
			packagedElement.setAttribute("xmi:id", elemento.getNome());
			packagedElement.setAttribute("name", elemento.getNome());

			if (!elemento.getExtend().equalsIgnoreCase("")) {
				Element generalization = doc.createElement("generalization");
				generalization.setAttribute("general", elemento.getExtend());
				generalization.setAttribute("xmi:id", Gerador.gerarId());
				packagedElement.appendChild(generalization);

				if (elemento.getExtend().equalsIgnoreCase("Personagem")
						|| elemento.getExtend().equalsIgnoreCase("Elemento")
						|| elemento.getExtend().equalsIgnoreCase("Obstaculo")) {
					Element ownedComment = doc.createElement("ownedComment");
					ownedComment.setAttribute("xmi:id", Gerador.gerarId());
					Node node = doc.createElement("body");
					node.setTextContent("core." + elemento.getExtend());
					ownedComment.appendChild(node);
					packagedElement.appendChild(ownedComment);
					if (elemento.getExtend().equalsIgnoreCase("Personagem")
							|| elemento.getExtend()
									.equalsIgnoreCase("Elemento")) {
						Element ownedComment_1 = doc
								.createElement("ownedComment");
						ownedComment_1.setAttribute("xmi:id", Gerador.gerarId());
						Node node_1 = doc.createElement("body");
						node_1.setTextContent("core.Interacao");
						ownedComment_1.appendChild(node_1);
						Element ownedComment_2 = doc
								.createElement("ownedComment");
						ownedComment_2.setAttribute("xmi:id", Gerador.gerarId());
						Node node_2 = doc.createElement("body");
						node_2.setTextContent("java.awt.geom.Rectangle2D");
						ownedComment_2.appendChild(node_2);
						Element ownedComment_3 = doc
								.createElement("ownedComment");
						ownedComment_3.setAttribute("xmi:id", Gerador.gerarId());
						Node node_3 = doc.createElement("body");
						node_3.setTextContent("java.awt.Graphics2D");
						ownedComment_3.appendChild(node_3);
						if (Facada.getInstance().isAcoes_colisao())
							packagedElement.appendChild(ownedComment_2);
						if (Facada.getInstance().isAcoes_movimento())
							packagedElement.appendChild(ownedComment_1);
						packagedElement.appendChild(ownedComment_3);
					}
				}
			}

			AtributoDAO.inserirAtributos(doc, packagedElement, elemento);

			packagedGame.appendChild(packagedElement);
		}

	}
}
