package model.dao;

import java.util.ArrayList;
import java.util.Map.Entry;

import model.Cenario;
import model.Facada;
import model.Gerador;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class CenarioDAO {

	public static ArrayList<Cenario> getCenarios(Element jogo) {
		ArrayList<Cenario> cenarios = new ArrayList<Cenario>();

		NodeList scenerys = jogo.getElementsByTagName("ownedOperation");

		for (int i = 0; i < scenerys.getLength(); i++) {
			Element e = (Element) scenerys.item(i);
			if (e.getAttribute("name").equalsIgnoreCase("loadCenario")) {
				Element p = (Element) e.getElementsByTagName("ownedParameter")
						.item(0);
				cenarios.add(new Cenario(p.getAttribute("name")));
			}
		}

		for (int i = 0; i < scenerys.getLength(); i++) {
			Element e = (Element) scenerys.item(i);
			if (e.getAttribute("name").equalsIgnoreCase("addTeleport")) {
				Element parametro_1 = (Element) e.getElementsByTagName(
						"ownedParameter").item(0);
				Element parametro_2 = (Element) e.getElementsByTagName(
						"ownedParameter").item(1);
				Element parametro_3 = (Element) e.getElementsByTagName(
						"ownedParameter").item(2);

				for (Cenario scene : cenarios) {
					if (scene.getNome().equalsIgnoreCase(
							(parametro_1.getAttribute("name")))) {
						scene.getTeleporte().put(
								parametro_3.getAttribute("name"),
								parametro_2.getAttribute("name"));
					}
				}
			}
			if (e.getAttribute("name").equalsIgnoreCase("configLayerSuperior")) {
				Element parametro_1 = (Element) e.getElementsByTagName(
						"ownedParameter").item(0);
				Element parametro_2 = (Element) e.getElementsByTagName(
						"ownedParameter").item(1);

				for (Cenario scene : cenarios) {
					if (scene.getNome().equalsIgnoreCase(
							(parametro_1.getAttribute("name")))) {
						scene.getLayers().put(parametro_2.getAttribute("name"),
								"superior");
					}
				}
			}
			if (e.getAttribute("name").equalsIgnoreCase("configLayerInferior")) {
				Element parametro_1 = (Element) e.getElementsByTagName(
						"ownedParameter").item(0);
				Element parametro_2 = (Element) e.getElementsByTagName(
						"ownedParameter").item(1);

				for (Cenario scene : cenarios) {
					if (scene.getNome().equalsIgnoreCase(
							(parametro_1.getAttribute("name")))) {
						scene.getLayers().put(parametro_2.getAttribute("name"),
								"inferior");
					}
				}
			}
		}
		return cenarios;
	}

	public static void inserirCenarios(Document doc, Element jogo) {

		ArrayList<Cenario> cenarios = Facada.getInstance()
				.getListaCenarios();

		for (Cenario cenario : cenarios) {
			Element ownedOperation = doc.createElement("ownedOperation");
			ownedOperation.setAttribute("xmi:id", Gerador.gerarId());
			ownedOperation.setAttribute("name", "loadCenario");

			Element ownedParameter = doc.createElement("ownedParameter");
			ownedParameter.setAttribute("xmi:id", Gerador.gerarId());
			ownedParameter.setAttribute("name", cenario.getNome());
			ownedOperation.appendChild(ownedParameter);

			jogo.appendChild(ownedOperation);

			for (Entry<String, String> teleport : cenario.getTeleporte()
					.entrySet()) {
				Element ownedOperation_teleport = doc
						.createElement("ownedOperation");
				ownedOperation_teleport.setAttribute("xmi:id",
						Gerador.gerarId());
				ownedOperation_teleport.setAttribute("name", "addTeleport");

				Element ownedParameter_1 = doc.createElement("ownedParameter");
				ownedParameter_1.setAttribute("xmi:id", Gerador.gerarId());
				ownedParameter_1.setAttribute("name", cenario.getNome());

				Element ownedParameter_2 = doc.createElement("ownedParameter");
				ownedParameter_2.setAttribute("xmi:id", Gerador.gerarId());
				ownedParameter_2.setAttribute("name", teleport.getValue());

				Element ownedParameter_3 = doc.createElement("ownedParameter");
				ownedParameter_3.setAttribute("xmi:id", Gerador.gerarId());
				ownedParameter_3.setAttribute("name", teleport.getKey());
				ownedParameter_3.setAttribute("direction", "return");

				ownedOperation_teleport.appendChild(ownedParameter_1);
				ownedOperation_teleport.appendChild(ownedParameter_2);
				ownedOperation_teleport.appendChild(ownedParameter_3);

				jogo.appendChild(ownedOperation_teleport);
			}

			for (Entry<String, String> layers : cenario.getLayers().entrySet()) {
				if (layers.getValue().equalsIgnoreCase("superior")) {
					Element ownedOperation_layer = doc
							.createElement("ownedOperation");
					ownedOperation_layer.setAttribute("xmi:id",
							Gerador.gerarId());
					ownedOperation_layer.setAttribute("name",
							"configLayerSuperior");

					Element ownedParameter_1 = doc
							.createElement("ownedParameter");
					ownedParameter_1.setAttribute("xmi:id", Gerador.gerarId());
					ownedParameter_1.setAttribute("name", cenario.getNome());

					Element ownedParameter_2 = doc
							.createElement("ownedParameter");
					ownedParameter_2.setAttribute("xmi:id", Gerador.gerarId());
					ownedParameter_2.setAttribute("name", layers.getKey());

					ownedOperation_layer.appendChild(ownedParameter_1);
					ownedOperation_layer.appendChild(ownedParameter_2);

					jogo.appendChild(ownedOperation_layer);
				} else if (layers.getValue().equalsIgnoreCase("inferior")) {
					Element ownedOperation_layer = doc
							.createElement("ownedOperation");
					ownedOperation_layer.setAttribute("xmi:id",
							Gerador.gerarId());
					ownedOperation_layer.setAttribute("name",
							"configLayerInferior");

					Element ownedParameter_1 = doc
							.createElement("ownedParameter");
					ownedParameter_1.setAttribute("xmi:id", Gerador.gerarId());
					ownedParameter_1.setAttribute("name", cenario.getNome());

					Element ownedParameter_2 = doc
							.createElement("ownedParameter");
					ownedParameter_2.setAttribute("xmi:id", Gerador.gerarId());
					ownedParameter_2.setAttribute("name", layers.getKey());

					ownedOperation_layer.appendChild(ownedParameter_1);
					ownedOperation_layer.appendChild(ownedParameter_2);

					jogo.appendChild(ownedOperation_layer);
				}
			}
		}
	}
}