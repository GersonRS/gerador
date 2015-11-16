package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.RejectedExecutionException;

import model.dao.Conection;
import model.dao.GameDAO;

import org.eclipse.acceleo.engine.AcceleoRuntimeException;
import org.eclipse.acceleo.module.example.uml2java.helios.GenerateJava;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.w3c.dom.Document;

import exceptions.ExceptionArquivoGerado;
import exceptions.ExceptionArquivoNull;
import exceptions.ExceptionProcessoErro;

public class Gerador implements Runnable{
	
	private Game game;
	private File localGeneration;
	
	public Gerador(Game game) {
		this.game = game;
	}

	@Override
	public void run() {
		if (localGeneration != null) {
			try {
				gerador(localGeneration);
			} catch (ExceptionArquivoNull e) {
			} catch (ExceptionProcessoErro e) {
			} catch (ExceptionArquivoGerado e) {
			}
		} else {
			new ExceptionArquivoNull();
		}
		localGeneration = null;
	}
	
	public void generateXMLFile(File f) {
		Document doc = Conection.getConection().abrirDefault();
		GameDAO.setGame(doc);
		Conection.getConection().salvar(doc, f);
	}

	public void gerarCodigo(File f) {
		generateXMLFile(null);
		this.localGeneration = f;
		new Thread(this).start();
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

	public void gerador(File f) throws ExceptionArquivoNull,
			ExceptionProcessoErro, ExceptionArquivoGerado {
		URI modelURI = URI.createFileURI(new File("resource/model/model.uml")
				.getAbsolutePath());
		List<String> arguments = new ArrayList<String>();
		try {
			GenerateJava generator = new GenerateJava(modelURI, f, arguments);
			generator.doGenerate(new BasicMonitor());
			throw new ExceptionArquivoGerado();
		} catch (IOException e) {
			throw new ExceptionArquivoNull();
		} catch (AcceleoRuntimeException e) {
			throw new ExceptionProcessoErro();
		} catch (RejectedExecutionException e) {
			throw new ExceptionProcessoErro();
		} finally {
		}
	}

	public Game getGame() {
		return game;
	}
}
