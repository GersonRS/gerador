package model.dao;

import model.Facada;
import model.Game;
import model.Gerador;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class GameDAO {

	public static Game getGame(Document doc) {

		Game game = new Game();

		Element docElement = doc.getDocumentElement();

		Element packagedCore = null;
		Element packagedGame = null;
		Element interacao = null;
		Element acoes = null;
		Element jogo = null;

		NodeList pacotes = docElement.getElementsByTagName("packagedElement");
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
			if (current.getAttribute("name").equalsIgnoreCase("Interacao")) {
				interacao = current;
			}
			if (current.getAttribute("name").equalsIgnoreCase("Acoes")) {
				acoes = current;
			}
		}

		jogo = (Element) packagedGame.getElementsByTagName("packagedElement")
				.item(0);

		game.setListaCenarios(CenarioDAO.getCenarios(jogo));

		NodeList generalization_interacao = interacao
				.getElementsByTagName("generalization");

		for (int i = 0; i < generalization_interacao.getLength(); i++) {
			Element general = (Element) generalization_interacao.item(i);
			if (general.getAttribute("general").equalsIgnoreCase(
					"MouseInputListener")) {
				game.setInteracoes_mouse(true);
			}
			if (general.getAttribute("general").equalsIgnoreCase("KeyListener")) {
				game.setInteracoes_teclado(true);
			}
		}

		NodeList generalization_acoes = acoes
				.getElementsByTagName("generalization");
		for (int i = 0; i < generalization_acoes.getLength(); i++) {
			Element general = (Element) generalization_acoes.item(i);
			if (general.getAttribute("general").equalsIgnoreCase("Colisao")) {
				game.setAcoes_colisao(true);
			}
			if (general.getAttribute("general").equalsIgnoreCase("Movimento")) {
				game.setAcoes_movimento(true);
			}
		}

		game.setListaElementos(ElementoDAO.getElementos(packagedGame));

		return game;
	}

	public static void setGame(Document doc) {

		Element docElement = doc.getDocumentElement();

		Element packagedCore = null;
		Element packagedGame = null;
		Element interacao = null;
		Element acoes = null;
		Element jogo = null;

		NodeList pacotes = docElement.getElementsByTagName("packagedElement");
		for (int i = 0; i < pacotes.getLength(); i++) {
			Element current = (Element) pacotes.item(i);
			if (current.getAttribute("name").equalsIgnoreCase("core")) {
				packagedCore = current;
			}
			if (current.getAttribute("name").equalsIgnoreCase("game")) {
				packagedGame = current;
			}
		}
		
		jogo = (Element) packagedGame.getElementsByTagName(
				"packagedElement").item(0);
		
		CenarioDAO.inserirCenarios(doc, jogo);

		NodeList classesCore = packagedCore
				.getElementsByTagName("packagedElement");

		for (int i = 0; i < classesCore.getLength(); i++) {
			Element current = (Element) classesCore.item(i);
			if (current.getAttribute("name").equalsIgnoreCase("Interacao")) {
				interacao = current;
			}
			if (current.getAttribute("name").equalsIgnoreCase("Acoes")) {
				acoes = current;
			}
		}

		if (Facada.getInstance().isInteracoes_mouse()) {
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
				general.setAttribute("xmi:id", Gerador.gerarId());
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

		if (Facada.getInstance().isInteracoes_teclado()) {
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
				general.setAttribute("xmi:id", Gerador.gerarId());
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

		if (Facada.getInstance().isAcoes_colisao()) {
			boolean tem = false;
			NodeList generalization = acoes
					.getElementsByTagName("generalization");
			for (int i = 0; i < generalization.getLength(); i++) {
				Element general = (Element) generalization.item(i);
				if (general.getAttribute("general").equalsIgnoreCase("Colisao")) {
					tem = true;
				}
			}
			if (!tem) {
				Element general = doc.createElement("generalization");
				general.setAttribute("xmi:id", Gerador.gerarId());
				general.setAttribute("general", "Colisao");
				acoes.appendChild(general);
			}
		} else {
			NodeList generalization = acoes
					.getElementsByTagName("generalization");
			for (int i = 0; i < generalization.getLength(); i++) {
				Element general = (Element) generalization.item(i);
				if (general.getAttribute("general").equalsIgnoreCase("Colisao")) {
					acoes.removeChild(general);
				}
			}
		}

		if (Facada.getInstance().isAcoes_movimento()) {
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
				general.setAttribute("xmi:id", Gerador.gerarId());
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

		ElementoDAO.setElementos(doc, packagedGame);

	}

}
