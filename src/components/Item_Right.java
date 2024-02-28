package components;
import java.awt.Font;

public class Item_Right extends javax.swing.JLayeredPane {

	private javax.swing.JLabel jLabel1;
    private components.Chat_Text txt;
	
    public Item_Right(String text) {
        initComponents();
        txt.setText(text);
    }

    private void initComponents() {

        txt = new components.Chat_Text();
        txt.setFont(new Font("Tahoma", Font.PLAIN, 20));
        jLabel1 = new javax.swing.JLabel();

        txt.setEditable(false);
        txt.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        txt.setBgColor(new java.awt.Color(222, 221, 255));
        txt.setBorderColor(new java.awt.Color(6, 23, 183));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/user.png"))); // NOI18N

        setLayer(txt, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
    }
    
}
