package view;

import controller.LoginController;
import start.ApplicationStart;
import utils.FrameDesign;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Logger;

public class LoginView extends JFrame {

    private final static Logger log = Logger.getLogger(ApplicationStart.class.getName());
    private final LoginController controller = new LoginController();

    private final Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();

    private JLabel picLabel = new JLabel();
    private JLabel userRegistrationPrompt = new JLabel("Don't have an account? Create one now HERE!");

    private JTextField userNameTextField = new JTextField("User Name");

    private JButton loginButton = new JButton("Login");

    private JPasswordField userPasswordTextField = new JPasswordField("Password");


    public LoginView() {
        LoginController.view = this;

        setTitle("Login");

        initComponents();

        setFrameDesign();

        setVisible(true);
    }

    private void initComponents() {
        getContentPane().setLayout(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(screenDimension.width / 2 - getSize().width / 2, screenDimension.height / 2 - getSize().height / 2);
        setResizable(false);

        LoginController.textFieldMouseCallback(userNameTextField);
        LoginController.textFieldMouseCallback(userPasswordTextField);

        userRegistrationPrompt.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                LoginController.startUserRegistration();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                userRegistrationPrompt.setForeground(FrameDesign.CREAM);
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                userRegistrationPrompt.setForeground(FrameDesign.LIGHT_BLUE);
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        loginButton.addActionListener(loginUser -> controller.login(userNameTextField.getText(), String.valueOf(userPasswordTextField.getPassword())));

        addItemsToFrame();

    }

    private void setFrameDesign() {
        try {
            BufferedImage image = ImageIO.read(new File("logo.jpg"));
            picLabel = new JLabel(new ImageIcon(image));
        } catch (Exception e) {
            log.severe("Can't load image");
        }

        picLabel.setBounds(115, 0, 200, 100);
        getContentPane().add(picLabel);

        ImageIcon img = new ImageIcon("dumbbell.png");
        this.setIconImage(img.getImage());

        userNameTextField.setBackground(FrameDesign.GREY);
        userNameTextField.setFont(FrameDesign.CALIBRI_BOLD_14);

        userPasswordTextField.setBackground(FrameDesign.GREY);

        userRegistrationPrompt.setFont(FrameDesign.CALIBRI_BOLD_14);
        userRegistrationPrompt.setForeground(FrameDesign.LIGHT_BLUE);


        loginButton.setFont(FrameDesign.CALIBRI_BOLD_14);
        loginButton.setBackground(FrameDesign.LIGHT_BLUE);
    }

    private void addItemsToFrame() {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        getContentPane().setBackground(FrameDesign.NAVY_BLUE);

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addComponent(userPasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                        .addGap(167, 167, 167)
                                                        .addComponent(userNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(188, 188, 188)
                                                .addComponent(loginButton)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addComponent(userRegistrationPrompt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(86, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(112, 112, 112)
                                .addComponent(userNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(userPasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(loginButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(userRegistrationPrompt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68))
        );

        pack();
    }
}