package administrador.Proyectos.interfazgrafica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import administradorProyectos.modelo.Actividad;
import administradorProyectos.modelo.Participante;

public class VentanaActividades extends JFrame implements ActionListener
{
	private VentanaPrincipal principal;
	
	private ArrayList<Actividad> actividades;
	
	private JTextField txtBusqueda;
	
	private JButton btnBuscar;
	
	private JLabel lblBusqueda;
	private JLabel lblTipo;
	private JLabel lblParticipante;
	private JLabel lblTitulo;
	private JLabel lblLogo;
	private JLabel lblPaquete;
	
	private JRadioButton radioTipo;
	private JRadioButton radioTitulo;
	private JRadioButton radioParticipante;
	private JRadioButton radioPaquetes;
	
	private JPanel panelBusqueda;
	private JPanel subPanelBusqueda;
	private JPanel panelUnionBusqueda;
	
	private panelInfoActividad panelActividades;
	
	private ImageIcon iconLogo;
	
	private static final String BUSCAR = "BUSCAR";
	
	private Integer contador;
	
	public VentanaActividades(VentanaPrincipal principal)
	{
		setSize(800, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Buscador de Actividades");
		setLayout(new BorderLayout());
		
		this.principal = principal;
		actividades = null;
		
		ButtonGroup j = new ButtonGroup();
		
		txtBusqueda = new JTextField();
		
		btnBuscar = new JButton("Buscar");
		
		lblBusqueda = new JLabel("Busqueda por parametro"); 
		lblParticipante = new JLabel("Participante");
		lblTipo = new JLabel("Tipo");
		lblTitulo = new JLabel("Titulo");
		lblLogo = new JLabel();
		lblPaquete = new JLabel("Paquete");
		
		radioParticipante = new JRadioButton();
		radioTipo = new JRadioButton();
		radioTitulo = new JRadioButton();
		radioPaquetes = new JRadioButton();
		
		j.add(radioParticipante);
		j.add(radioTipo);
		j.add(radioTitulo);
		j.add(radioPaquetes);
		
		btnBuscar.addActionListener(this);
		btnBuscar.setActionCommand(BUSCAR);
		
		panelBusqueda = new JPanel();
		subPanelBusqueda = new JPanel();
		panelUnionBusqueda = new JPanel();
		panelActividades = new panelInfoActividad(this);
		
		iconLogo = new ImageIcon("./data/andes.png");
		lblLogo.setIcon(iconLogo);
		
		subPanelBusqueda.setLayout(new FlowLayout());
		subPanelBusqueda.add(lblParticipante);
		subPanelBusqueda.add(radioParticipante);
		subPanelBusqueda.add(lblTipo);
		subPanelBusqueda.add(radioTipo);
		subPanelBusqueda.add(lblTitulo);
		subPanelBusqueda.add(radioTitulo);
		subPanelBusqueda.add(lblPaquete);
		subPanelBusqueda.add(radioPaquetes);
		
		panelBusqueda.setLayout(new GridLayout(2,2));
		panelBusqueda.add(lblBusqueda);
		panelBusqueda.add(new JPanel());
		panelBusqueda.add(txtBusqueda);
		panelBusqueda.add(btnBuscar);
		
		panelUnionBusqueda.setLayout(new GridLayout(4,1));
		panelUnionBusqueda.setBorder(new TitledBorder("Buscador"));
		panelUnionBusqueda.add(panelBusqueda);
		panelUnionBusqueda.add(subPanelBusqueda);
		panelUnionBusqueda.add(lblLogo);
		
		add(panelUnionBusqueda, BorderLayout.WEST);
		add(panelActividades, BorderLayout.CENTER);
		
		
		
	}
	
	public void actualizarAnterior() 
	{
		if (contador == 0)
		{
			dialWarning nope = new dialWarning("ESTAS EN LA PRIMERA ACTIVIDAD");
			nope.setVisible(true);
		}
		else
		{
			contador--;
			Actividad a = actividades.get(contador);
			panelActividades.actualizar(a);
		}
		
	}

	public void editarActividad(Integer tipoEdicion, String edicion) throws Exception 
	{
		Actividad a = actividades.get(contador);
		principal.proyecto.aEditar(a, tipoEdicion, edicion);
		panelActividades.actualizar(a);
	}

	public void actualizarSiguiente() 
	{
		Integer tamañoActividades = actividades.size();
		if (contador == (tamañoActividades - 1))
		{
			dialWarning nope = new dialWarning("ESTAS EN LA ULTIMA ACTIVIDAD");
			nope.setVisible(true);
		}
		else
		{
			contador++;
			Actividad a = actividades.get(contador);
			panelActividades.actualizar(a);
		}
	}

	public void finalizarActividad(Date fechaFinalizada) 
	{
		Actividad a = actividades.get(contador);
		a.setTerminado(fechaFinalizada);
		panelActividades.actualizar(a);
	}

	public void estadoCronometro(Integer estado, Date fechaFinalizada) 
	{
		Actividad a = actividades.get(contador);
		a.estadoCronometro(estado, fechaFinalizada);
		panelActividades.actualizar(a);
	}
	
	private void cargarActividadesEncontradas() 
	{
		contador = 0; 
		panelActividades.actualizar(actividades.get(0));
	}
	
	public VentanaPrincipal getPrincipal()
	{
		return principal;
	}
	
	public Actividad getCurrentActivity()
	{
		return actividades.get(contador);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String comando = e.getActionCommand();
		if (comando.equals(BUSCAR))
		{
			String actividadABuscar = txtBusqueda.getText();
			if (actividadABuscar == "")
			{
				dialWarning nuevo = new dialWarning("No hay nada en el buscador");
				nuevo.setVisible(true);
			}
			else
			{
				if(radioParticipante.isSelected())
				{
					actividades = principal.proyecto.buscarActividad2("participante", actividadABuscar);
				}
				else if(radioTipo.isSelected())
				{
					actividades = principal.proyecto.buscarActividad2("tipo", actividadABuscar);
				}
				else if(radioTitulo.isSelected())
				{
					actividades = principal.proyecto.buscarActividad2("titulo", actividadABuscar);
				}
				else if (radioPaquetes.isSelected())
				{
					actividades = principal.proyecto.buscarActividad2("paquete", actividadABuscar);
				}
			}
			
			if (actividades.size() == 0) 
			{
				dialWarning advertencia = new dialWarning("No se encontraron actividades con esos parametros");
				advertencia.setVisible(true);
			}
			else
			{
				cargarActividadesEncontradas();
			}
		}
		
	}
}
