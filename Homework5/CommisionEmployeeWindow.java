import javax.swing.*;
import java.awt.event.*;

public class CommisionEmployeeWindow extends JDialog{
	private CommissionEmployee ce;
	
	public CommisionEmployeeWindow() {
		this.setSize(450, 300);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		this.add(panel);

		// First Name
		JLabel firstNameLabel = new JLabel("First Name:");
		firstNameLabel.setBounds(10, 20, 150, 25);
		panel.add(firstNameLabel);

		JTextField firstNameText = new JTextField(20);
		firstNameText.setBounds(150, 20, 265, 25);
		panel.add(firstNameText);

		// Last Name
		JLabel lastNameLabel = new JLabel("Last Name:");
		lastNameLabel.setBounds(10, 50, 150, 25);
		panel.add(lastNameLabel);

		JTextField lastNameText = new JTextField(20);
		lastNameText.setBounds(150, 50, 265, 25);
		panel.add(lastNameText);

		// Social Security Number
		JLabel ssnLabel = new JLabel("Social Security Number:");
		ssnLabel.setBounds(10, 80, 150, 25);
		panel.add(ssnLabel);

		JTextField ssnText = new JTextField(20);
		ssnText.setBounds(150, 80, 265, 25);
		panel.add(ssnText);

		// Gross Sales
		JLabel gsLabel = new JLabel("Gross Sales:");
		gsLabel.setBounds(10, 110, 150, 25);
		panel.add(gsLabel);

		JTextField gsText = new JTextField(20);
		gsText.setBounds(150, 110, 265, 25);
		panel.add(gsText);

		// Commision Rate
		JLabel crLabel = new JLabel("Commision Rate:");
		crLabel.setBounds(10, 140, 150, 25);
		panel.add(crLabel);

		JTextField crText = new JTextField(20);
		crText.setBounds(150, 140, 265, 25);
		panel.add(crText);

		// Submit Button
		JButton submitButton = new JButton("Submit");
		submitButton.setBounds(175, 170, 100, 25);
		panel.add(submitButton);

		JDialog dial = this;

		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ce = new CommissionEmployee(firstNameText.getText(), lastNameText.getText(), Integer.parseInt(ssnText.getText()));
					ce.setGrossSales(Integer.parseInt(gsText.getText()));
					ce.setCommissionRate(Double.parseDouble(crText.getText()));
					dial.dispose();
				} catch (Exception exc) {
					// Exception
					JOptionPane.showMessageDialog(dial, exc.toString(), "Exception", JOptionPane.ERROR_MESSAGE);
				}
			}
		};

		submitButton.addActionListener(al);
	}

	public CommissionEmployee getReturnStatus(){
		return ce;
	}
}