package administrador.Proyectos.interfazgrafica;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class dialWarning extends JDialog implements ActionListener
{
	private JLabel textoWarning;
	private JButton btnCerrar;
	private JPanel juntar;
	private static final String CERRAR = "CERRAR";

	public dialWarning(String infoError)
	{
		setSize(400, 100);
		setTitle("WARNING");
		setLocationRelativeTo(null);
		
		textoWarning = new JLabel(infoError);
		btnCerrar = new JButton("OK");
		juntar = new JPanel();
		
		btnCerrar.addActionListener(this);
		btnCerrar.setActionCommand(CERRAR);
		
		juntar.setLayout(new BorderLayout());
		juntar.add(btnCerrar, BorderLayout.CENTER);
		juntar.add(textoWarning, BorderLayout.NORTH);
		add(juntar);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String comando = e.getActionCommand();
		if (comando.equals(comando))
		{
			dispose();
		}
		
	}

}
