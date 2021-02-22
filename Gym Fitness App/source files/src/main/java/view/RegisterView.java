package view;

import controller.LoginController;
import controller.RegisterController;
import utils.FrameDesign;

import javax.swing.*;
import java.util.Objects;

public class RegisterView extends JFrame {

    private final RegisterController controller = new RegisterController();

    private JTextField firstNameTextField = new JTextField();
    private JTextField lastNameTextField = new JTextField();
    private JTextField birthDayTextField = new JTextField("dd/mm/yyyy");
    private JTextField userNameTextField = new JTextField();
    private JPasswordField userPasswordTextField = new JPasswordField();
    private JPasswordField userPasswordConfirmationTextField = new JPasswordField();

    private JComboBox<String> chooseSexBox = new JComboBox<>(new javax.swing.DefaultComboBoxModel<>(new String[]{"Male", "Female", "Others"}));

    private JLabel firstNameLabel = new JLabel("FIRST NAME : ");
    private JLabel lastNameLabel = new JLabel("LAST NAME : ");
    private JLabel birthDayLabel = new JLabel("BIRTHDAY : ");
    private JLabel sexLabel = new JLabel("SEX : ");
    private JLabel userNameLabel = new JLabel("USERNAME : ");
    private JLabel userPasswordLabel = new JLabel("PASSWORD : ");
    private JLabel userPasswordConfirmationLabel = new JLabel("CONFIRM PASSWORD : ");

    private JButton registerButton = new JButton("Register");

    public RegisterView() {
        RegisterController.view = this;

        setTitle("Register");

        initialize();

        setFrameDesign();

        setVisible(true);
    }

    private void initialize() {

        registerButton.addActionListener(registerUser ->
                controller.registerUser(
                        firstNameTextField.getText(),
                        lastNameTextField.getText(),
                        birthDayTextField.getText(),
                        Objects.requireNonNull(chooseSexBox.getSelectedItem()).toString(),
                        userNameTextField.getText(),
                        userPasswordTextField.getPassword(),
                        userPasswordConfirmationTextField.getPassword()));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LoginController.textFieldMouseCallback(birthDayTextField);

        pack();
        setLocationRelativeTo(null);
        setResizable(false);

        addItemsToFrame();
    }

    private void setFrameDesign(){
        ImageIcon img = new ImageIcon("dumbbell.png");
        this.setIconImage(img.getImage());

        firstNameTextField.setBackground(FrameDesign.GREY);
        firstNameTextField.setFont(FrameDesign.CALIBRI_SIMPLE_14);

        lastNameTextField.setBackground(FrameDesign.GREY);
        lastNameTextField.setFont(FrameDesign.CALIBRI_SIMPLE_14);

        birthDayTextField.setBackground(FrameDesign.GREY);
        birthDayTextField.setFont(FrameDesign.CALIBRI_SIMPLE_14);

        userNameTextField.setBackground(FrameDesign.GREY);
        userNameTextField.setFont(FrameDesign.CALIBRI_SIMPLE_14);

        userPasswordTextField.setBackground(FrameDesign.GREY);

        firstNameLabel.setForeground(FrameDesign.CREAM);
        firstNameLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        lastNameLabel.setForeground(FrameDesign.CREAM);
        lastNameLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        birthDayLabel.setForeground(FrameDesign.CREAM);
        birthDayLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        sexLabel.setForeground(FrameDesign.CREAM);
        sexLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        userNameLabel.setForeground(FrameDesign.CREAM);
        userNameLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        userPasswordLabel.setForeground(FrameDesign.CREAM);
        userPasswordLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        userPasswordConfirmationLabel.setForeground(FrameDesign.CREAM);
        userPasswordConfirmationLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        registerButton.setBackground(FrameDesign.LIGHT_BLUE);
        registerButton.setFont(FrameDesign.CALIBRI_BOLD_14);

        getContentPane().setBackground(FrameDesign.NAVY_BLUE);
    }

    private void addItemsToFrame(){
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());

        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(99, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(userPasswordConfirmationLabel)
                                        .addComponent(firstNameLabel)
                                        .addComponent(lastNameLabel)
                                        .addComponent(birthDayLabel)
                                        .addComponent(sexLabel)
                                        .addComponent(userNameLabel)
                                        .addComponent(userPasswordLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(lastNameTextField)
                                                .addComponent(firstNameTextField)
                                                .addComponent(userNameTextField)
                                                .addComponent(chooseSexBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(birthDayTextField)
                                                .addComponent(userPasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                                .addComponent(userPasswordConfirmationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(95, 95, 95)
                                .addComponent(registerButton)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(firstNameLabel)
                                        .addComponent(registerButton))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lastNameLabel))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(birthDayTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(birthDayLabel))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(chooseSexBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(sexLabel))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(userNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(userNameLabel))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(userPasswordLabel)
                                        .addComponent(userPasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(userPasswordConfirmationLabel)
                                        .addComponent(userPasswordConfirmationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }
}
