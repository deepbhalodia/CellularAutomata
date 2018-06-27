package Fluid;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * 
 * @author MMUNSON
 */
public class App extends FDApp{

	private static Logger log = Logger.getLogger(App.class.getName());
	public static int rule = 0;
	//public static int maxSteps = 30;

	protected JPanel mainPanel;
	protected JPanel northPanel;
	
	protected JButton startBtn;
	protected JButton stopBtn;
	protected JButton pauseBtn;
	protected Canvas Panel;
	protected JComboBox<String> rules= new JComboBox<>();
	
   
	
    /**
     * Sample app constructor
     */
    FluidFrameSim sim = null;
    Thread thread = null;

	
    
    public App() {
    		frame.setSize(600, 400); // initial Frame size
		frame.setTitle("FluidApp");
		
		menuMgr.createDefaultActions(); // Set up default menu items
		sim = new FluidFrameSim();
		sim.addObserver(Panel);
		
    	showUI(); // Cause the Swing Dispatch thread to display the JFrame
    }
   
    /**
     * Create a main panel that will hold the bulk of our application display
     */
	@Override
	public JPanel getMainPanel() {
	
	mainPanel = new JPanel();
    	mainPanel.setLayout(new BorderLayout());
    	mainPanel.add(BorderLayout.NORTH, getNorthPanel());
    	
    	Panel = new Canvas();
    	mainPanel.add(BorderLayout.CENTER, Panel);
    	
    	return mainPanel;
	}
    
	/**
	 * Create a top panel that will hold control buttons
	 * @return
	 */
    public JPanel getNorthPanel() {
    	northPanel = new JPanel();
    	northPanel.setLayout(new FlowLayout());
    	
    	JLabel Name = new JLabel("Max Steps:");
    JTextField FName = new JTextField(5);
    FName.setText("30");
    Name.setLabelFor(FName);
    FName.getText();
    //System.out.println("The entered text is: " + FName.getText());
    
    
   // maxSteps = Integer.parseInt(FName.getText());
    FName.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            System.out.println("The entered text is: " + FName.getText());
           FluidFrameSim.MAX_GENERATION = Integer.parseInt(FName.getText());
           
        }
    });
    
    startBtn = new JButton("Start");
    	
    	startBtn.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e) {
    		startSim();
    		}
    	});
    	
    	//Pause Button
    	
    	pauseBtn = new JButton("Pause/Resume"); // Allow the app to hear about button pushes
    pauseBtn.setEnabled(false);
    	pauseBtn.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			
    			pauseSim();
    		}
    	});
    	
    	
    	stopBtn = new JButton("Stop"); // Allow the app to hear about button pushes
    stopBtn.setEnabled(false);
    	stopBtn.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			stopSim();
    		}
    	});
    	
    	northPanel.add(Name);
    	northPanel.add(FName);
    
    	stopBtn.addActionListener(this);
    	northPanel.add(stopBtn);
    	
    	startBtn.addActionListener(this);
    	northPanel.add(startBtn);
    	
    	pauseBtn.addActionListener(this);
    	northPanel.add(pauseBtn);
    	
    	rules= new JComboBox<>(new String[]{"BasicRule","Rule1", "Rule2","Rule3","Rule4"});
    	
    	rules.addItemListener(new ItemListener() {
			
    		public void itemStateChanged(ItemEvent event) {
    		       if (event.getStateChange() == ItemEvent.SELECTED) {
    		          Object item = event.getItem();
    		       if(item.equals("BasicRule")) rule= 0;
    		       if(item.equals("Rule1")) rule= 1;
    		       if(item.equals("Rule2")) rule= 2;
    		       if(item.equals("Rule3")) rule= 3;
    		       if(item.equals("Rule4")) rule= 4;
    		       
    		       }
    		    }    
		});
    
    	rules.addActionListener(this);
    	northPanel.add(rules);

    	return northPanel;
    }
   
    
	@Override
	public void actionPerformed(ActionEvent ae) {
		log.info("We received an ActionEvent " + ae);
		if (ae.getSource() == startBtn)
			System.out.println("Start pressed"+'\n');
		else if (ae.getSource() == stopBtn)
			System.out.println("Stop pressed");
		
	}
	
	private void startSim() {
		
		if(sim.isrunning()) {
			return;
		}
		System.out.println("Starting Thread" );
		
		if(thread==null) {
			//sim = new FluidFrameSim();
			thread = new Thread(sim);
		}
		thread.start();
		sim.setRunning(true);
		resetButtons();
		startBtn.setEnabled(false);
		
	}
	
	private void resetButtons() {
		stopBtn.setEnabled(true);
		pauseBtn.setEnabled(true);
		
	}

	private void pauseSim() {
		if(!sim.isrunning()) return;	
		sim.setPaused(!sim.isPaused());
		System.out.println("Changing Pause State: "+ sim.isPaused());
		resetButtons();
		
		}
	
	private void stopSim() {
		System.out.println("Stop Pressed");
		sim.setDone(true);
		sim.setRunning(false);
		pauseBtn.setEnabled(false);;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		log.info("Window opened");
	}

	@Override
	public void windowClosing(WindowEvent e) {	
		log.info("Window closing");
	}



	@Override
	public void windowClosed(WindowEvent e) {
		log.info("Window closed");
	}



	@Override
	public void windowIconified(WindowEvent e) {
		log.info("Window iconified");
	}



	@Override
	public void windowDeiconified(WindowEvent e) {	
		log.info("Window deiconified");
	}



	@Override
	public void windowActivated(WindowEvent e) {
		log.info("Window activated");
	}



	@Override
	public void windowDeactivated(WindowEvent e) {	
		log.info("Window deactivated");
	}
	
	
	public static void main(String[] args) {
		App wapp = new App();
		log.info("App started");
	}


}
