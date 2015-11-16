package exceptions;

import javax.swing.JOptionPane;

public class ExceptionArquivoNull extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExceptionArquivoNull() {
		JOptionPane.showMessageDialog(null,
				"Erro o arquivo ou diretorio informado não existe.");
	}

	public ExceptionArquivoNull(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
