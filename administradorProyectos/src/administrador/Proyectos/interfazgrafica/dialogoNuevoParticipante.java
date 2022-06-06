package administrador.Proyectos.interfazgrafica;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class dialogoNuevoParticipante extends JDialog implements ActionListener
{
	private JLabel lblNombre;
	private JLabel lblCorreo;
	private JTextField txtNombre;
	private JTextField txtCorreo;
	private JButton btnAnadir;
	private VentanaPrincipal principal;
	private static final String CREAR = "CREAR";
	
	public dialogoNuevoParticipante(VentanaPrincipal principal)
	{
		this.principal = principal;
		setSize(520, 510);
		setLocationRelativeTo(null);
		setTitle("Nuevo Participante");
		setLayout(new GridLayout(4, 2));
		
		lblNombre = new JLabel("Nombre");
		lblCorreo = new JLabel("Correo");
		txtNombre = new JTextField();
		txtCorreo = new JTextField();
		btnAnadir = new JButton("Añadir Participante");
		
		add(new JLabel("Ingrese la información del participante"));
		add(new JLabel());
		add(lblNombre);
		add(txtNombre);
		add(lblCorreo);
		add(txtCorreo);
		add(new JLabel());
		add(btnAnadir);
		
		btnAnadir.addActionListener(this);
		btnAnadir.setActionCommand(CREAR);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String comando = e.getActionCommand();
		if (comando.equals(CREAR))
		{
			String nombre = txtNombre.getText();
			String correo = txtCorreo.getText();
			principal.nuevoParticipante(nombre, correo);
			dispose();
		}
		
	}
	
}
