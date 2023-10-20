package GraphicalUserInterface;

import javax.swing.*;

public class GetUsername extends JDialog{
    private JTextField Username;
    private JPanel username;
    private JButton button1;

    protected String finalUsername;

    public GetUsername(){
        setContentPane(username);
        pack();
        setLocationRelativeTo(null);
        setModal(true);
        setTitle("Enter your username");
        button1.addActionListener((e)->{
            if(Username.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Enter nickname correctly", "Fail", JOptionPane.ERROR_MESSAGE);
                return;
            }
            dispose();
            setVisible(false);
        });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }
    public String name(){
        return Username.getText();
    }
}
