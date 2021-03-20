import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

public class SkockoFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	JButton btnSkocko, btnTref, btnPik, btnHerc, btnKaro, btnZvezda;
	ArrayList<JButton> displayButtons;
	ArrayList<JButton> resultButtons;
	ArrayList<JButton> finallySolutionButtons;
	ArrayList<JButton> currentRowButtons;
	JLabel lblTime;
	Timer t;
	int direct, indirect;
	int hitIndex;	
	int brojPokusaja;
	JCheckBox check;
	JButton btnReload;

	public SkockoFrame() {

		this.setSize(350, 590);
		this.setTitle("SKOČKO");
		this.setIconImage(new ImageIcon("..\\Skocko_Zavrsni\\Img\\smile.png").getImage());
		this.getContentPane().setBackground(new Color(69, 87, 125));
		this.centerFrame();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.currentRowButtons = new ArrayList<JButton>();
		this.direct = 0;
		this.indirect = 0;
		this.hitIndex = 0;		
		this.brojPokusaja = 0;

		Icon skocko = new ImageIcon("..\\Skocko_Zavrsni\\Img\\skocko.png");
		btnSkocko = new JButton(skocko);
		btnSkocko.setBackground(Color.white);
		btnSkocko.setBounds(10, 490, 40, 60);

		btnSkocko.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton currentButton = currentButton();
				setIcon(skocko, currentButton);
				setCurrentRow(currentButton);
			}
		});

		Icon tref = new ImageIcon("..\\Skocko_Zavrsni\\Img\\tref.png");
		btnTref = new JButton(tref);
		btnTref.setBackground(Color.white);
		btnTref.setBounds(55, 490, 40, 60);

		btnTref.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton currentButton = currentButton();
				setIcon(tref, currentButton());
				setCurrentRow(currentButton);
			}
		});

		Icon pik = new ImageIcon("..\\Skocko_Zavrsni\\Img\\pik.png");
		btnPik = new JButton(pik);
		btnPik.setBackground(Color.white);
		btnPik.setBounds(100, 490, 40, 60);

		btnPik.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton currentButton = currentButton();
				setIcon(pik, currentButton());
				setCurrentRow(currentButton);
			}
		});

		Icon herc = new ImageIcon("..\\Skocko_Zavrsni\\Img\\herc.png");
		btnHerc = new JButton(herc);
		btnHerc.setBackground(Color.white);
		btnHerc.setBounds(145, 490, 40, 60);

		btnHerc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton currentButton = currentButton();
				setIcon(herc, currentButton());
				setCurrentRow(currentButton);
			}
		});

		Icon karo = new ImageIcon("..\\Skocko_Zavrsni\\Img\\karo.png");
		btnKaro = new JButton(karo);
		btnKaro.setBackground(Color.white);
		btnKaro.setBounds(190, 490, 40, 60);

		btnKaro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton currentButton = currentButton();
				setIcon(karo, currentButton());
				setCurrentRow(currentButton);
			}
		});

		Icon zvezda = new ImageIcon("..\\Skocko_Zavrsni\\Img\\zvezda.png");
		btnZvezda = new JButton(zvezda);
		btnZvezda.setBackground(Color.white);
		btnZvezda.setBounds(235, 490, 40, 60);

		btnZvezda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton currentButton = currentButton();
				setIcon(zvezda, currentButton());
				setCurrentRow(currentButton);
			}
		});

		lblTime = new JLabel("0");
		lblTime.setBounds(300, 525, 30, 30);
		lblTime.setForeground(Color.yellow);
		lblTime.setFont(new Font("Courier New", Font.BOLD, 22));
		
		check = new JCheckBox();
		check.setText("Rešenje");
		check.setSelected(true);
		check.setBounds(250, 445, 80, 20);
		check.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(JButton b : finallySolutionButtons) {
					b.setVisible(!b.isVisible());
				}			
			}
		});
		
		btnReload = new JButton(new ImageIcon("..\\Skocko_Zavrsni\\Img\\reload.png"));
		btnReload.setBounds(290, 485, 40, 35);
		btnReload.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				reload();				
			}
		});

		t = new Timer(1000, new TimerListener());
		t.start();

		addDisplayButtons();
		addResultButtons();
		addFinallySolutionButtons();

		this.add(btnSkocko);
		this.add(btnTref);
		this.add(btnPik);
		this.add(btnHerc);
		this.add(btnKaro);
		this.add(btnZvezda);
		this.add(lblTime);
		this.add(check);
		this.add(btnReload);

		this.setLayout(null);
	}

	private void centerFrame() {
		Dimension windowSize = getSize();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Point centerPoint = ge.getCenterPoint();

		int dx = centerPoint.x - windowSize.width / 2;
		int dy = centerPoint.y - windowSize.height / 2;
		setLocation(dx, dy);
	}

	private void addDisplayButtons() {
		displayButtons = new ArrayList<JButton>();
		int x = 10, y = 10, width = 50, height = 50;

		for (int i = 1; i < 29; i++) {
			JButton b = new JButton();
			b.setBounds(x, y, width, height);
			displayButtons.add(b);
			this.add(b);
			if (i % 4 == 0) {
				y += 60;
				x = 10;
			} else {
				x += 60;
			}
		}
	}

	private void addResultButtons() {
		resultButtons = new ArrayList<JButton>();
		int x = 275, y = 15, width = 10, height = 10;

		for (int i = 1; i < 29; i++) {
			JButton b = new JButton();			
			b.setBounds(x, y, width, height);
			b.setBackground(new Color(52, 146, 235));
			resultButtons.add(b);
			this.add(b);
			if (i % 2 == 0) {
				y += 30;
				x = 275;
			} else {
				x += 20;
			}
		}
	}

	private void addFinallySolutionButtons() {
		finallySolutionButtons = new ArrayList<JButton>();
		int x = 10, y = 430, width = 50, height = 50;

		for (int i = 1; i < 5; i++) {
			
			JButton b = new JButton();
			b.setBounds(x, y, width, height);
			b.setBackground(Color.white);
			b.setBorder(new LineBorder(Color.red, 2));
			setSignFinallySolution(b);
			finallySolutionButtons.add(b);
			this.add(b);
			x += 60;
		}
	}

	private JButton currentButton() {
		for (JButton b : displayButtons) {
			if (b.getIcon() == null) {
				return b;
			}
		}
		return null;
	}

	private void setIcon(Icon icon, JButton b) {
		b.setIcon(icon);
		b.setBackground(Color.white);
	}

	private void disableSigns() {
		btnSkocko.setEnabled(false);
		btnTref.setEnabled(false);
		btnPik.setEnabled(false);
		btnHerc.setEnabled(false);
		btnKaro.setEnabled(false);
		btnZvezda.setEnabled(false);
	}
	
	private void enableableSigns() {
		btnSkocko.setEnabled(true);
		btnTref.setEnabled(true);
		btnPik.setEnabled(true);
		btnHerc.setEnabled(true);
		btnKaro.setEnabled(true);
		btnZvezda.setEnabled(true);
	}

	private boolean isSignsEquals(JButton inputButton, JButton finallyButon) {
		String input = inputButton.getIcon().toString();
		String fin = finallyButon.getIcon().toString();
		int r = input.compareTo(fin);

		return r == 0;
	}

	private void setSignFinallySolution(JButton btn) {
		int s = 0;

		while (s <= 0 || s > 6) { // za svaki slučaj
			Random random = new Random();
			s = random.nextInt((6 - 1 + 1) + 1);
		}

		switch (s) {

		case 1:
			btn.setIcon(new ImageIcon("..\\Skocko_Zavrsni\\Img\\skocko.png"));			
			break;
		case 2:
			btn.setIcon(new ImageIcon("..\\Skocko_Zavrsni\\Img\\tref.png"));
			break;
		case 3:
			btn.setIcon(new ImageIcon("..\\Skocko_Zavrsni\\Img\\pik.png"));
			break;
		case 4:
			btn.setIcon(new ImageIcon("..\\Skocko_Zavrsni\\Img\\herc.png"));
			break;
		case 5:
			btn.setIcon(new ImageIcon("..\\Skocko_Zavrsni\\Img\\karo.png"));
			break;
		case 6:
			btn.setIcon(new ImageIcon("..\\Skocko_Zavrsni\\Img\\zvezda.png"));
			break;

		default:
			btn.setIcon(new ImageIcon("..\\Skocko_Zavrsni\\Img\\skocko.png")); // za svaki slučaj
			break;
		}
	}

	private void setCurrentRow(JButton b) {
		currentRowButtons.add(b);
		if ((displayButtons.indexOf(b) + 1) % 4 == 0) {		
			
			brojPokusaja++;
			setHits();			
			display();
			
			if (checkFinnalyResult() == false) {
				currentRowButtons = new ArrayList<JButton>();
				if(brojPokusaja == 7) {
					JOptionPane.showMessageDialog(null, "Više sreće drugi put.");
					disableSigns();
					t.stop();
				}
			}
			else
				gameOver();
			
			direct = 0;
			indirect = 0;
		}
	}

	private void gameOver() {
		t.stop();
		JOptionPane.showMessageDialog(null, "Pobeda, " + brojPokusaja + " pokušaja, " + (brojPokusaja <=6? "30" : "20") + " poena. Čestitamo!");
		this.reload();
	}

	public void reload() {
		t.stop();
		currentRowButtons = new ArrayList<JButton>();

		for (JButton b : finallySolutionButtons) {
			setSignFinallySolution(b);
		}
		for (JButton b : displayButtons) {
			this.setIcon(null, b);
		}
		for (JButton b : resultButtons) {
			b.setBackground(new Color(52, 146, 235));
		}
		
		hitIndex = 0;
		brojPokusaja = 0;
		enableableSigns();

		t = new Timer(1000, new TimerListener());
		t.start();
	}

	private boolean checkFinnalyResult() {				
		return direct == 4;
	}

	private void setHits() {
		ArrayList<JButton> finallySolutionButtonsCopy = new ArrayList<JButton>();
		ArrayList<JButton> currentRowButtonsCopy = new ArrayList<JButton>();

		for (int i = 0; i < 4; i++) {
			if (isSignsEquals(currentRowButtons.get(i), finallySolutionButtons.get(i))) {
				direct++;				
			} else {
				currentRowButtonsCopy.add(currentRowButtons.get(i));
				finallySolutionButtonsCopy.add(finallySolutionButtons.get(i));
			}
		}
		for (int i = 0; i < currentRowButtonsCopy.size(); i++) {			
			for (int j = 0; j < finallySolutionButtonsCopy.size(); j++) {
				if (isSignsEquals(currentRowButtonsCopy.get(i), finallySolutionButtonsCopy.get(j))) {
					indirect++;					
					currentRowButtonsCopy.remove(i); 
					i--;
					finallySolutionButtonsCopy.remove(j);					
					break;
				} 
			}
		}
	}
	
	private class TimerListener implements ActionListener {
		int seconds = 0;

		@Override
		public void actionPerformed(ActionEvent arg0) {
			lblTime.setText(seconds + "");
			seconds++;
			if (seconds == 61) {
				JOptionPane.showMessageDialog(null, "Vreme je isteklo ...");
				t.stop();
				disableSigns();
			}
		}
	}
		
	private void display() {		
		for(int i=0; i<direct; i++) {
			resultButtons.get(hitIndex).setBackground(Color.RED);
			hitIndex++;
		}
		for(int i=0; i<indirect; i++) {
			resultButtons.get(hitIndex).setBackground(Color.YELLOW);
			hitIndex++;
		}
		int fail = 4-direct-indirect;		
		for(int i=0; i<fail; i++)
			hitIndex++;	
	}
}
