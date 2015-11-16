package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import model.dao.Conection;
import model.dao.GameDAO;

import org.eclipse.acceleo.engine.AcceleoRuntimeException;
import org.eclipse.acceleo.module.example.uml2java.helios.GenerateJava;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.w3c.dom.Document;

public class Gerador implements Runnable {
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

	public void generateXMLFile(File f){
		Document doc = Conection.getConection().abrirDefault();
		GameDAO.inserirGame(doc);
		Conection.getConection().salvar(doc, f);
	}

	public void loadXMLFile(File f) {
		Document doc = Conection.getConection().abrir(f);
		this.game = GameDAO.getGame(doc);
	}

	public static String gerarId() {
		Random r = new Random();
		String retorno = "";
		String caracteres = "abcdefghijkmnopqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ123456789012345678901234567890_-";

		int len = caracteres.length();

		for (int i = 0; i < 23; i++) {
			retorno += caracteres.charAt(r.nextInt(len));
		}

		return retorno;
	}

	@Override
	public void run() {
		URI modelURI = URI.createFileURI(new File("resource/model/model.uml")
				.getAbsolutePath());
		File folder = new File("C:/Users/Gerson/workspaceMM/Generation/src");
		List<String> arguments = new ArrayList<String>();
		try {
			GenerateJava generator = new GenerateJava(modelURI, folder,
					arguments);
			generator.doGenerate(new BasicMonitor());

			JOptionPane.showMessageDialog(null, "Você gerou com sucesso.");
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
