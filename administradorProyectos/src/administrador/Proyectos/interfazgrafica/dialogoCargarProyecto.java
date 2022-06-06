package administrador.Proyectos.interfazgrafica;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class dialogoCargarProyecto extends JDialog implements ActionListener
{
	private VentanaPrincipal principal;
	
	private JLabel lbltitulo;
	private JLabel lbldescripcion;
	private JLabel lblfechaFinal;
	private JLabel lblduenoNombre;
	private JLabel lblduenoCorreo;
	private JLabel lbltipos;
	private JLabel lblArchivo;
	
	private JTextField txttitulo;
	private JTextField txtdescripcion;
	private JTextField txtfechaFinal;
	private JTextField txtduenoNombre;
	private JTextField txtduenoCorreo;
	private JTextField txttipos;
	private JTextField txtArchivo;
	
	private JPanel seccion;
	
	private JButton btnCrear;
	private JButton btnAbrir;
	
	private Integer uso;
	
	private static final String CREAR = "CREAR";
	private static final String ABRIR = "ABRIR";
	
	public dialogoCargarProyecto(VentanaPrincipal principal, Integer uso)
	{
		this.principal = principal;
		this.uso = uso;
		
		setSize(520, 510);
		setLocationRelativeTo(null);
		
		
		lbltitulo = new JLabel("Título");
		lbldescripcion = new JLabel("Descripcion del proyecto");
		lblfechaFinal = new JLabel("Fecha Final Aprox del Proyecto (dd-mm-aa)");
		lblduenoNombre = new JLabel("Nombre del Dueño");
		lblduenoCorreo = new JLabel("Correo del Dueño");
		lbltipos = new JLabel("Tipos del Proyecto(separados por coma)");
		lblArchivo = new JLabel("Titulo del Proyecto");
		
		txttitulo= new JTextField();
		txtdescripcion= new JTextField();
		txtfechaFinal= new JTextField();
		txtduenoNombre= new JTextField();
		txtduenoCorreo= new JTextField();
		txttipos = new JTextField();
		txtArchivo= new JTextField();
		
		seccion = new JPanel();
		
		btnAbrir = new JButton("Abrir Proyecto");
		btnCrear = new JButton("Crear Proyecto");
		
		btnAbrir.addActionListener(this);
		btnAbrir.setActionCommand(ABRIR);
		
		btnCrear.addActionListener(this);
		btnCrear.setActionCommand(CREAR);
		
		if (uso == 0)
		{
			setTitle("Nuevo Proyecto");
			setLayout(new GridLayout(2, 1));
			
			seccion.setLayout(new GridLayout(14,2));
			
			seccion.add(new JLabel("Ingrese los datos del nuevo proyecto"));
			seccion.add(new JLabel());
			seccion.add(lbltitulo);
			seccion.add(txttitulo);
			seccion.add(lblfechaFinal);
			seccion.add(txtfechaFinal);
			seccion.add(lblduenoNombre);
			seccion.add(txtduenoNombre);
			seccion.add(lblduenoCorreo);
			seccion.add(txtduenoCorreo);
			seccion.add(lbldescripcion);
			seccion.add(txtdescripcion);
			seccion.add(lbltipos);
			seccion.add(txttipos);
			
			add(seccion);
			add(btnCrear);			
			
		}
		else if(uso == 1)
		{
			setTitle("Abrir Proyecto");
			setLayout(new GridLayout(2, 1));
			
			seccion.setLayout(new GridLayout(1,2));
			
			seccion.add(lblArchivo);
			seccion.add(txtArchivo);
			
			add(seccion);
			add(btnAbrir);
		}
		else
		{
			System.out.println("ERROR SIN FORMATO DE USO");
		}
		 
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String comando = e.getActionCommand();
		if (comando.equals(ABRIR))
		{
			String archivo = txtArchivo.getText();
			try {
				principal.cargarProyecto(archivo);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			dispose();
		}
		else if (comando.equals(CREAR))
		{
			String titulo = txttitulo.getText();
			String descripcion = txtdescripcion.getText();
			String fechaFinal = txtfechaFinal.getText();
			String duenoNombre = txtduenoNombre.getText();
			String duenocorreo = txtduenoCorreo.getText();
			String tipos = txttipos.getText();
			principal.crearProyecto(titulo, descripcion, fechaFinal, duenoNombre, duenocorreo, tipos);
			dispose();
		}
	}
	
}
