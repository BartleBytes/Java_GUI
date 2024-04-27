package cscorner;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DemoFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textName;
    private JTextField textDOB;
    private JLabel lblInfo;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

  
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DemoFrame frame = new DemoFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public DemoFrame() {
        setTitle("Demo Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Enter Name");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setBounds(33, 43, 118, 27);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Enter date of birth (MM-DD-YYYY)");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1.setBounds(33, 108, 262, 27);
        contentPane.add(lblNewLabel_1);
        
        textName = new JTextField(); 
        textName.setFont(new Font("Tahoma", Font.BOLD, 14));
        textName.setBounds(364, 43, 101, 27);
        contentPane.add(textName);
        
        textDOB = new JTextField(); 
        textDOB.setFont(new Font("Tahoma", Font.BOLD, 14));
        textDOB.setBounds(364, 108, 120, 27);
        contentPane.add(textDOB);
        
        JButton btnOk = new JButton("Calculate Age");
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String n = textName.getText();
                String dobText = textDOB.getText();
                try {
                    LocalDate dob = LocalDate.parse(dobText, dateFormatter);
                    int age = calculateAge(dob);
                    lblInfo.setText(n + ", you are " + age + " years old.");
                } catch (DateTimeException ex) {
                    lblInfo.setText("Incorrect date format. Please enter in MM-DD-YYYY.");
                }
            }
        });
        btnOk.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnOk.setBounds(86, 216, 150, 29);
        contentPane.add(btnOk);
        
        JButton btnReset = new JButton("Reset");
        btnReset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textName.setText("");
                textDOB.setText("");
                lblInfo.setText("");
            }
        });
        btnReset.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnReset.setBounds(321, 216, 100, 29);
        contentPane.add(btnReset);
        
        lblInfo = new JLabel("");
        lblInfo.setBounds(57, 229, 364, 16);
        contentPane.add(lblInfo);
    }
    
    public static int calculateAge(LocalDate dob) {
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        
        // Calculate the period between the DOB and current date
        Period period = Period.between(dob, currentDate);
        
        // Get the difference in years from the period
        int age = period.getYears();
        
        return age;
    }
}

