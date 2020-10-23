import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBar extends JPanel implements ActionListener{

        private JButton hellobtn;
        private JButton bybtn;
        private StringListener stringListener;

        public ToolBar(){

            setBorder(BorderFactory.createCompoundBorder());

            hellobtn = new JButton("Hello");
            bybtn = new JButton("Bye");

            setLayout(new FlowLayout(FlowLayout.LEFT));

            add(hellobtn);
            add(bybtn);

            hellobtn.addActionListener(this);
            bybtn.addActionListener(this);

        }

        public void setStringListener(StringListener listener){
            this.stringListener = listener;
        }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(stringListener!=null){
            if(e.getSource() == hellobtn)
                stringListener.stringEmitted("Heloo\n");
            else if(e.getSource() == bybtn)
                stringListener.stringEmitted("bye\n");
        }
    }
}
