import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class FormPanel extends JPanel {

    private JLabel nameLabel;
    private JLabel occupationLabel;
    private JTextField nameField;
    private JTextField occupationField;
    private JButton okBtn;
    private FormEventListener formEventListener;
    private JList ageList;
    private JComboBox comboBox;
    private JCheckBox citizenCheck;
    private JTextField taxField;
    private JLabel taxLabel;

    public FormPanel(){
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);

        nameLabel = new JLabel("Name: ");
        occupationLabel = new JLabel("Occupation: ");
        nameField = new JTextField(10);
        occupationField = new JTextField(10);
        ageList = new JList();
        comboBox = new JComboBox();
        citizenCheck = new JCheckBox();
        taxField = new JTextField(10);
        taxLabel = new JLabel("Tax ID");
        taxField.setEnabled(false);
        taxLabel.setEnabled(false);

        citizenCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean enabled = citizenCheck.isSelected();
                taxLabel.setEnabled(enabled);
                taxField.setEnabled(enabled);

            }
        });



        //Set up list box
        DefaultListModel ageModel = new DefaultListModel();
        ageModel.addElement(new AgeCategory(0,"Under 18"));
        ageModel.addElement(new AgeCategory(1, "18 to 65"));
        ageModel.addElement(new AgeCategory(2, "65 and over"));
        ageList.setModel(ageModel);
        ageList.setSelectedIndex(0);
        ageList.setBorder(BorderFactory.createEtchedBorder());
        ageList.setPreferredSize(new Dimension(110, 66));

        //set up combo box;
        DefaultComboBoxModel empModel = new DefaultComboBoxModel();
        empModel.addElement("employed");
        empModel.addElement("self-employed");
        empModel.addElement("unemployed");
        comboBox.setModel(empModel);
        comboBox.setSelectedIndex(0);
        comboBox.setEditable(true);


        okBtn = new JButton("Ok");

        //set mnumonics
        okBtn.setMnemonic(KeyEvent.VK_O);
        nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
        nameLabel.setLabelFor(nameField);

        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(formEventListener != null) {
                    AgeCategory ageCat = (AgeCategory) ageList.getSelectedValue();
                    String empCat = (String) comboBox.getSelectedItem();
                    String taxId = (String)taxField.getText();
                    boolean citizen = citizenCheck.isSelected();
                    formEventListener.formEventFired(new FormEvent(this, (String) nameField.getText(),
                            (String) occupationField.getText(), ageCat.getId(),
                            empCat, taxId, citizen));
                }

            }
        });

        Border innerBorder = BorderFactory.createTitledBorder("Add Person");
        Border outterBorder = BorderFactory.createEmptyBorder(15,5,25,5);
        setBorder(BorderFactory.createCompoundBorder(outterBorder, innerBorder));

        setLayoutComponents();

    }

    private void setLayoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 1;
        gc.weighty = 0.1;

        ///////////First Row///////////////////
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,5);
        add(nameLabel, gc);

        gc.gridx =1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        add(nameField, gc);

        /////Second Row/////////////////

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx =0;
        gc.gridy = 1;
        gc.insets = new Insets(0,0,0,5);
        add(occupationLabel, gc);

        gc.gridx =1;
        gc.gridy = 1;
        add(occupationField, gc);

        ///////Button//////
        gc.weightx = 1;
        gc.weighty = 0.2;
        gc.gridx =1;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(ageList, gc);

        gc.gridx =-00;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(new JLabel("Age: " ), gc);

        ///////Next row//////
        gc.weightx = 1;
        gc.weighty = 0.2;
        gc.gridx =1;
        gc.gridy = 3;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(comboBox, gc);
        gc.gridx =0;
        gc.gridy = 3;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(new JLabel("Employment: "), gc);

        ///////Next row//////
        gc.weightx = 1;
        gc.weighty = 0.2;
        gc.gridx =0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(taxLabel, gc);
        gc.gridx = 1;
        add(taxField, gc);

        gc.gridx = 3;
        add(citizenCheck, gc);

        ///////btn row//////
        gc.weightx = 1;
        gc.weighty = 1.5;
        gc.gridx =1;
        gc.gridy++;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(okBtn, gc);
    }

    public void setFormEventListener(FormEventListener listener){
        this.formEventListener = listener;
    }
}

class AgeCategory{
    private int id;
    private String text;
    public AgeCategory(int id, String text){
        this.id = id;
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }

    public int getId(){
        return this.id;
    }
}
