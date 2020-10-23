import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame{
    private JButton btn;
    private TextPanel textArea;
    private ToolBar toolBar;
    private FormPanel formPanel;
    private JFileChooser fileChooser;


    public MainFrame(){
        super("Main App :)");

        setLayout(new BorderLayout());

        toolBar = new ToolBar();
        add(toolBar, BorderLayout.SOUTH);

        formPanel = new FormPanel();
        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new PersonFileFilter());

        setJMenuBar(createMenuBar());

        add(formPanel, BorderLayout.WEST);
        formPanel.setFormEventListener(new FormEventListener() {
            @Override
            public void formEventFired(FormEvent e) {
                String name = e.getName();
                String occupation = e.getOccupation();
                int ageCat = e.getAgeCat();
                String empCat = e.getEmpCat();
                String test = e.getTaxId();
                System.out.println("|"+test+"|"+ test.length());
                String taxId = e.getTaxId().length() == 0 ? "None" : e.getTaxId();
                textArea.appendText(name + ": "+ occupation + ",  "+
                        ageCat + " "+empCat+ " "+ taxId+"\n");
            }
        });


        textArea = new TextPanel();
        add(textArea, BorderLayout.CENTER);

        toolBar.setStringListener(new StringListener() {
            @Override
            public void stringEmitted(String text) {
                textArea.appendText(text);
            }
        });

        setMinimumSize(new Dimension(500,400));
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JMenuBar createMenuBar(){
        JMenuBar menubar= new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu windowMenu = new JMenu("Window");

        JMenuItem exportDataItem = new JMenuItem("Export Data..");
        JMenuItem importDataItem = new JMenuItem("Import Data..");
        JMenuItem exititem = new JMenuItem("Exit");

        fileMenu.add(exportDataItem);
        fileMenu.add(importDataItem);
        fileMenu.addSeparator();
        fileMenu.add(exititem);

        JMenu showMenu = new JMenu("Show");
        JMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
        showFormItem.setSelected(true);
        windowMenu.add(showMenu);
        showMenu.add(showFormItem);

        menubar.add(fileMenu);
        menubar.add(windowMenu);

        showFormItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBoxMenuItem checkBoxMenuItem =(JCheckBoxMenuItem)e.getSource();
                formPanel.setVisible(checkBoxMenuItem.isSelected());
            }

        });

        fileMenu.setMnemonic(KeyEvent.VK_F);
        exititem.setMnemonic(KeyEvent.VK_X);
        exititem.setEnabled(true);

        exititem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int action = JOptionPane.showConfirmDialog(MainFrame.this,
                        "Do you want to exit",
                        "Yes", JOptionPane.OK_CANCEL_OPTION);
                if(action == JOptionPane.OK_OPTION)
                    System.exit(0);
            }
        });

        exititem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        importDataItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showOpenDialog(MainFrame.this)== JFileChooser.APPROVE_OPTION)
                    System.out.println(fileChooser.getSelectedFile());
            }
        });

        return menubar;

    }
}
