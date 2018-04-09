import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class MainWindow extends JFrame{
	public static void main(String[] args) {
		MainWindow mw = new MainWindow();
		mw.setVisible(true);
	}

	private ArrayList<Employee> emp;

	public MainWindow() {
		emp = new ArrayList<>();

		this.setSize(800, 600);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		JMenu eii = new JMenu("EmployeeInfoInput");
		JMenu seh = new JMenu("Search");
		JMenuItem ce = new JMenuItem("CommisionEmployee");
		JMenuItem bpce = new JMenuItem("BasePlusCommisionEmployee");
		JMenuItem aes = new JMenuItem("AverageEarningSearch");
		
		menuBar.add(eii);
		menuBar.add(seh);
		eii.add(ce);
		eii.add(bpce);
		seh.add(aes);
		this.setJMenuBar(menuBar);

		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == ce) {
					//JOptionPane.showMessageDialog(null, "ce", "ce", JOptionPane.ERROR_MESSAGE);
					CommisionEmployeeWindow cem = new CommisionEmployeeWindow();
					cem.setModal(true);
					cem.setVisible(true);
					System.out.println(cem.getReturnStatus());
					emp.add(cem.getReturnStatus());
				}
				if (e.getSource() == bpce) {
					BasePlusCommisionEmployeeWindow bpcew = new BasePlusCommisionEmployeeWindow();
					bpcew.setModal(true);
					bpcew.setVisible(true);
					System.out.println(bpcew.getReturnStatus());
					emp.add(bpcew.getReturnStatus());
				}
				if (e.getSource() == aes) {
					double sum = 0;

					for (Employee x : emp) {
						sum += x.earning();
					}
					JOptionPane.showMessageDialog(null, String.valueOf(sum/emp.size()), "Earning", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		};
		ce.addActionListener(al);
		bpce.addActionListener(al);
		aes.addActionListener(al);
	}
}