package edu.buffalo.cse;

import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.IndexColorModel;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import edu.buffalo.fractal.ComputePool;
import edu.buffalo.fractal.FractalPanel;

public class GUI implements Runnable {

	/**
	 * an instance of JFrame which going to be the basic window of the whole GUI
	 */
	private JFrame _jf; 

	/**
	 * a JMenuBar which is going to hold all menu items in it on GUI
	 */
	private JMenuBar _jmb;

	/**
	 * an instance of FractalPanel which creates the actual images of fractals
	 */
	private FractalPanel _fp;

	/**
	 * an instance of IndexColorModel which helps to generate colors
	 */
	private IndexColorModel _icm;

	/**
	 * an instance of the model, which is going to connect all other methods to
	 * GUI
	 */
	private Model _model = new Model();

	/**
	 * an instance of int that remembers the x coordinate where the user pressed
	 */
	private int x = 0;
	
	/**
	 * an instance of int that remembers the y coordinate where the user pressed
	 */
	private int y = 0;
	
	/**
	 * an instance of ComputePool that helps to access ComputePool's methods
	 */
	private ComputePool _pool = new ComputePool();
	
	/**
	 * a constructor which intializes IndexColorModel and calls addObserver to
	 * connect ui and model together
	 */
	public GUI() {
		_icm = ColorModelFactory.createAwsomeColorModel(255);
		_model.addObserver(this);

	}

	/**
	 * the method that implements GUI
	 */
	@Override
	public void run() {
		_jf = new JFrame(); // initalize a new JFrame
		_fp = new FractalPanel(new Dimension(2048,2048), _icm); // instantize a new FractalPanel

		BoxLayout bxlt = new BoxLayout(_jf.getContentPane(), BoxLayout.Y_AXIS);
		// create a new BoxLayout for the frame
		_jf.setLayout(bxlt);

		// a new JMenu called "File"
		JMenu file = new JMenu("File");

		// a new JMenu called "Fractal" to hold all fractals
		JMenu fractal = new JMenu("Fractal");

		// a new JMenu called "Color" to hold all colors
		JMenu color = new JMenu("Color");

		// a new JMenu called "Escape Distance" to provide a way to change
		// escape distance and escape time
		JMenu escapeDistance = new JMenu("Choose Your Input");
		
		// a new JMenu called "Threads" to provide a way to increase workers
		// to calculate the fractal
		JMenu worker = new JMenu("Workers");
		
		// a new JLabel to tell user mouse event instruction
		final JLabel label = new JLabel();
		_fp.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) { // do nothing
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				x = e.getX(); // store the x
				y = e.getY(); // store the y
				_model.currentXY(e.getX(),e.getY()); // set the point that user pressed as the first point
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if(x == e.getX() && y == e.getY()){ // clicking 
					// do nothing
				}
				else if(e.getX() < 0 ||  e.getY() < 0 || e.getX() >2047 || e.getY() >2047){ // out of bounds
					label.setText("You got an IndexOutOfBoundsException(), please fix the bug yourself.");
				}
				else{ // if the final point is in bounds
					_model.finalXY(e.getX(), e.getY()); // remember the second point
					_model.zoomIn(); // use new range to recalculate the fractal
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				label.setText("You entered the area that can be zoomed in.");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				label.setText("Here cannot be zoomed in.");
			}
		});
		
		_fp.addMouseMotionListener(new MouseMotionListener(){
			@Override
			public void mouseDragged(MouseEvent e) { // visual feedback
				label.setText(String.format("Staring Coordinates: %d,%d  ", x ,y) + String.format("Releasing at : %d,%d", e.getX(),e.getY()));
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				label.setText(String.format("Coordinates: %d,%d", e.getX(),e.getY()));
			}
			
		});
		
		// initalize a main JMenuBar to hold all JMenu
		_jmb = new JMenuBar();
		_jmb.add(file);
		_jmb.add(fractal);
		_jmb.add(color);
		_jmb.add(escapeDistance);	
		_jmb.add(worker);
		
		// a JMenuItem that called "Quit the Program"
		JMenuItem quit = new JMenuItem("Quit the Program");
		file.add(quit); // add quit to file menu

		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);// ask when click on quit, the program quits.
			}
		});
		
		// a JMenuItem called "Reset"
		JMenuItem re = new JMenuItem("Reset");
		file.add(re); // add reset to file menu
		re.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				_model.reset(); // reset back to the original fractal
			}
			
		});

		// a JMenuItem called "Julia Set"
		JMenuItem julia = new JMenuItem("Julia Set");
		fractal.add(julia);
		// when click on this item, the image of Julia Set performs
		julia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_model.JuliaSet();
				// JuliaSet method written in Model has the Array or Passes, and
				// the default color
			}
		});

		// a JMenuItem called "BurningShip Set"
		JMenuItem burning = new JMenuItem("BurningShip Set");
		fractal.add(burning);
		// when click on this item, the image of Burning Ship performs
		burning.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_model.BurningShipSet();
			}
		});

		// a JMenuItem called "MultiBrot Set"
		JMenuItem multibrot = new JMenuItem("MultiBrot Set");
		fractal.add(multibrot);
		// when click on this item, the image of Multibrot performs
		multibrot.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_model.MultibrotSet();
			}
		});

		// a JMenuItem called "MandelBrot Set"
		JMenuItem mandelbrot = new JMenuItem("MandelBrot Set");
		fractal.add(mandelbrot);
		// when click on this item, the image of Mandelbrot performs
		mandelbrot.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_model.MandelBrotSet();
			}
		});

		// a JMenuItem called "Rainbow"
		JMenuItem rainbow = new JMenuItem("Rainbow");
		color.add(rainbow);
		// when click on this item, color of all fractals becomes rainbow
		rainbow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_model.Rainbow();
			}
		});

		// a JMenuItem called "Awesome"
		JMenuItem awesome = new JMenuItem("Awesome");
		color.add(awesome);
		// when click on this item, the color that our group member most proud
		// of will take place
		awesome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_model.Awsome();
			}
		});

		// a JMenuItem called "Red"
		JMenuItem red = new JMenuItem("Red");
		color.add(red);
		// when click on this item, color of all fractals becomes red
		red.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_model.Red();
			}
		});
		

		// a JMenuItem called "Light Green"
		JMenuItem lightgreen = new JMenuItem("Light Green");
		color.add(lightgreen);
		// when click on this item, color of all fractals becomes light green
		lightgreen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_model.LightGreen();
			}
		});

		// a JMenuItem called "White"
		JMenuItem white = new JMenuItem("White");
		color.add(white);
		// when click on this item, color of all fractals becomes white
		white.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_model.White();
			}
		});

		// a JMenuItem called "Metal Gray"
		JMenuItem metalgray = new JMenuItem("Metal Gray");
		color.add(metalgray);
		// when click on this item, color of all fractals becomes gray-yellow
		metalgray.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_model.MetalGray();
			}
		});

		// a JMenuItem called "Better Gray"
		JMenuItem bettergray = new JMenuItem("Better Gray");
		color.add(bettergray);
		// when click on this item, color of all fractals becomes gray
		bettergray.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_model.BetterGray();
			}
		});

		// a JMenuItem called "Cool"
		JMenuItem cool = new JMenuItem("Cool");
		color.add(cool);
		// when click on this item, color of all fractals becomes blue-yellow
		cool.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_model.Cool();
			}
		});

		// a JMenuItem called "Gray with Red"
		JMenuItem graywithred = new JMenuItem("Gray With Red");
		color.add(graywithred);
		// when click on this item, color of all fractals becomes gray-red
		graywithred.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_model.GrayWithRed();
			}
		});

		// a JMenuItem called "Enter Escape Distance"
		JMenuItem distance = new JMenuItem("Enter Escape Distance");
		escapeDistance.add(distance);
		// when click the item, there would be a window that gives user chance
		// to change escape distance
		distance.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createTextboxForDistance();
			}
		});

		// a JMenuItem called "Enter Escape Distance"
		JMenuItem time = new JMenuItem("Enter Escape Time");
		escapeDistance.add(time);
		// when click the item, there would be a window that gives user chance
		// to change escape distance
		time.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createTextboxForTime();
			}
		});
		
		//a JMenuItem that can change the number of workers regarding user input
		JMenuItem thread = new JMenuItem("Change the number of threads");
		worker.add(thread);
		
		thread.addActionListener(new ActionListener(){
		// a window that gives user the chance to enter the number of workers he/she wants
			@Override
			public void actionPerformed(ActionEvent e) {
				createTextboxForThread();
			}	
		});
		
		
		_jf.add(_fp);
		_jf.add(label);
		update(_icm);
		_jf.setJMenuBar(_jmb);
		_jf.setVisible(true);
		_jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_jf.setResizable(false); // so the GUI can be as pretty as Dr.Hertz wants :)
		_jf.pack();
	}
	
	/**
	 * update the program to a new image of fractal
	 * @param icm = the fractal user wants to display
	 */
	public void update(IndexColorModel icm) {
		_pool.clearPool();
		_fp.setIndexColorModel(icm);
		_pool.changePanel(_fp);
		_pool.generateFractal(2048, _model.getSwingWorkers());
	}

	/**
	 * this method is to create a popup window which allows user to input a
	 * value if the value is valid then it will be the new escape distance
	 */
	public void createTextboxForDistance() {
		JOptionPane box = new JOptionPane();
		box.setVisible(true);
		String inputValue = JOptionPane.showInputDialog("Enter New Escape Distance");
		if (inputValue != null) {
			_model.checkInput(inputValue); // check if input is valid to be
											// escape distance
		}
	}

	/**
	 * this method is to create a popup window which allows user to input a
	 * value if the value is valid then it will be the new escape time
	 */
	public void createTextboxForTime() {
		JOptionPane box = new JOptionPane();
		box.setVisible(true);
		String inputValue = JOptionPane.showInputDialog("Enter New Escape (<=255)");
		if (inputValue != null) {
			_model.checkInputForTime(inputValue); // check if input is valid to be
													// escape time
		}
	}
	
	/**
	 * this method is to create a popup window which allows user to input a
	 * value if the value is valid then it will be the new escape time
	 */
	public void createTextboxForThread() {
		JOptionPane box = new JOptionPane();
		box.setVisible(true);
		String inputValue = JOptionPane.showInputDialog("Enter the number of threads you want (1~128)");
		if (inputValue != null) {
			_model.checkInputForThread(inputValue); // check if input is valid to be 
														// the number of threads
		}
	}

	/**
	 * This method is to declare what error message would be output when the
	 * user input is not valid
	 */
	public void ErrorMessage() {

		JOptionPane.showMessageDialog(null, "I think you need a pair of eyeglasses. \nEnter a valid input please.",
				"Error", JOptionPane.ERROR_MESSAGE);

	}

}
