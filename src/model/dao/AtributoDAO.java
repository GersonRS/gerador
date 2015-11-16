package model.dao;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import model.Atributo;
import model.Elemento;

public class AtributoDAO {
	
	public static ArrayList<Atributo> getAtributos(Element e){
		ArrayList<Atributo> attr = new ArrayList<Atributo>();
		NodeList atributos = e.getElementsByTagName("ownedAttribute");

		for (int j = 0; j < atributos.getLength(); j++) {
			Element atributo = (Element) atributos.item(j);
			Element value = (Element) atributo
					.getElementsByTagName("defaultValue").item(
							0);
			String valor = "";
			if(value!=null)
				valor = value.getAttribute("value")
					.replace("&quot;", "").replace("\"", "");
			
			Atributo a = new Atributo(
					atributo.getAttribute("name"),
					atributo.getAttribute("type"), valor);
			attr.add(a);
		}
		return attr;
	}
	
	public static void inserirAtributos(Document doc,Element packagedElement, Elemento elemento){
		for (Atributo atributo : elemento.getAtributos()) {

			Element ownedAttribute = doc.createElement("ownedAttribute");
			ownedAttribute.setAttribute("type", atributo.getTipo());
			ownedAttribute.setAttribute("name", atributo.getNome());

			if (!atributo.getValor().equalsIgnoreCase("")
					&& atributo.getValor() != null) {
				Element defaultValue = doc.createElement("defaultValue");
				defaultValue.setAttribute("xmi:type",
						tipo(atributo.getTipo()));
				defaultValue
						.setAttribute(
								"value",
								atributo.getTipo().equalsIgnoreCase(
										"String") ? "\""
										+ atributo.getValor() + "\""
										: atributo.getValor());

				ownedAttribute.appendChild(defaultValue);
			}

			packagedElement.appendChild(ownedAttribute);
		}
	}
	
	private static String tipo(String tipo) {
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
}
