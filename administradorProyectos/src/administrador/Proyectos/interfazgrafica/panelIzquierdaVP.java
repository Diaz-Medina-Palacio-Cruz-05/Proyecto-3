package administrador.Proyectos.interfazgrafica;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class panelIzquierdaVP extends JPanel implements ActionListener
{
	private JLabel lblDueño;
	private JLabel lblUsuario;
	private JLabel lblLogo;
	private JTextField txtDueño;
	private JPanel panelDueño;
	private JPanel panelCargar;
	private JPanel panelImagen;
	private ImageIcon iconLogo;
	private ImageIcon iconUsuario;
	private JButton btnAbrir;
	private JButton btnCrear;
	private VentanaPrincipal principal;
	
	private static final String CREAR = "CREAR";
	private static final String ABRIR = "ABRIR";
	
	public panelIzquierdaVP(VentanaPrincipal principal) 
	{
		this.principal = principal;
		
		lblDueño = new JLabel("Dueño:");
		lblUsuario = new JLabel("");
		lblLogo = new JLabel("");
		panelDueño = new JPanel();
		panelCargar = new JPanel();
		panelImagen = new JPanel();
		iconLogo = new ImageIcon("./data/andes.png");
		iconUsuario = new ImageIcon("./data/usuario.png");
		txtDueño = new JTextField();
		btnAbrir = new JButton("Abrir Proyecto");
		btnCrear = new JButton("Crear Proyecto");
		
		//panelDueño
		panelDueño.setLayout(new GridLayout(2,3));
		panelDueño.setBorder( new TitledBorder( "Usuario" ) );;
		txtDueño.setEditable(false);
		lblUsuario.setIcon(iconUsuario);
		panelDueño.add(lblDueño);
		panelDueño.add(new JLabel());
		panelDueño.add(new JLabel());
		panelDueño.add(lblUsuario);
		panelDueño.add(txtDueño);
		panelDueño.add(new JLabel());
		
		//panelCargar
		panelCargar.setLayout(new GridLayout(2, 1));
		panelCargar.setBorder(new TitledBorder("Cargar Proyecto"));
		btnCrear.addActionListener(this);
		btnCrear.setActionCommand(CREAR);
		btnAbrir.addActionListener(this);
		btnAbrir.setActionCommand(ABRIR);
		panelCargar.add(btnCrear);
		panelCargar.add(btnAbrir);
		
		//panelImagen
		panelImagen.setLayout(new GridLayout(1, 1));
		lblLogo.setIcon(iconLogo);
		panelImagen.add(lblLogo);
		
		//PanelIzquiersaVP
		setLayout(new GridLayout(5, 1));
		add(panelDueño);
		add(panelCargar);
		add(new JLabel());
		add(panelImagen);
		add(new JLabel());
	}
	
	public void actualizar()
	{
		txtDueño.setText(principal.proyecto.getParticipantes().get(0).getNombre());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	 {
		String comando = e.getActionCommand();
		if (comando.equals(ABRIR)) 
		{
			dialogoCargarProyecto cargar = new dialogoCargarProyecto(principal, 1);
			cargar.setVisible(true);
		}
		else if (comando.equals(CREAR))
		{
			dialogoCargarProyecto cargar = new dialogoCargarProyecto(principal, 0);
			cargar.setVisible(true);
		}
		
	}

}
