package view;

import controller.ClientProfileController;
import dto.ClientProfileDto;
import utils.FrameDesign;

import javax.swing.*;
import java.awt.*;

public class ClientProfileView extends JFrame {
    private JFrame frame;
    private JTextArea txtClientName = new JTextArea();
    private JTextArea txtClientBirthday = new JTextArea();
    private JTextArea txtClientSex = new JTextArea();
    private JComboBox<String> comboEdit = new JComboBox<>();
    private final JLabel labelProfile = new JLabel("Your Profile");
    private final JLabel labelEdit = new JLabel("Edit Your Profile");

    private final String idClient;
    private final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    private final ClientProfileController clientProfileController;

    public ClientProfileView(String idClient) {
        this.frame = new JFrame("Client Profile");
        this.idClient = idClient;
        clientProfileController = new ClientProfileController();

        initializeClientProfileView();

        setFrameDesign();

        frame.setVisible(true);

    }

    private void initializeClientProfileView() {

        ClientProfileDto clientProfile = clientProfileController.viewProfileDetails(idClient);

        labelProfile.setBounds(150, 10, 100, 20);
        txtClientName.setBounds(75, 70, 250, 30);
        txtClientBirthday.setBounds(75, 130, 250, 30);
        txtClientSex.setBounds(75,190,250,30);
        labelEdit.setBounds(132,290,250,30);
        comboEdit.setBounds(75,350,250,30);

        txtClientName.setEditable(false);
        txtClientBirthday.setEditable(false);
        txtClientSex.setEditable(false);

        txtClientName.setText("Name:                " + clientProfile.getName());
        txtClientBirthday.setText("Birthday:          " + clientProfile.getBirthday());
        txtClientSex.setText( "Sex:                    " + clientProfile.getSex());

        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);

        addItemsToFrame();

        frame.setLocation(dimension.width / 2 - frame.getSize().width / 2, dimension.height / 2 - frame.getSize().height / 2);

        comboEdit.setModel(new DefaultComboBoxModel<>(new String[]{"Edit Name", "Edit Birthday"}));

        comboEdit.addActionListener(e -> editChoice());


    }

    private void addItemsToFrame(){
        frame.getContentPane().add(labelProfile)
                .getParent().add(labelEdit)
                .getParent().add(txtClientName)
                .getParent().add(txtClientBirthday)
                .getParent().add(txtClientSex)
                .getParent().add(comboEdit);
    }


    public void updateName(String newName){
        txtClientName.setText("Name:                " + newName);
    }

    public void updateBirthday(String newBirthday){
        txtClientBirthday.setText("Birthday:          " + newBirthday);
    }

    public void setFrameDesign(){
        ImageIcon img = new ImageIcon("dumbbell.png");
        frame.setIconImage(img.getImage());
        frame.getContentPane().setBackground(FrameDesign.NAVY_BLUE);

        labelProfile.setForeground(FrameDesign.DARK_PINK);
        labelProfile.setBackground(FrameDesign.NAVY_BLUE);
        labelProfile.setFont(FrameDesign.CALIBRI_BOLD_16);

        labelEdit.setForeground(FrameDesign.DARK_PINK);
        labelEdit.setBackground(FrameDesign.NAVY_BLUE);
        labelEdit.setFont(FrameDesign.CALIBRI_BOLD_16);

        txtClientName.setForeground(FrameDesign.CREAM);
        txtClientName.setBackground(FrameDesign.NAVY_BLUE);
        txtClientName.setFont(FrameDesign.CALIBRI_BOLD_14);

        txtClientBirthday.setForeground(FrameDesign.CREAM);
        txtClientBirthday.setBackground(FrameDesign.NAVY_BLUE);
        txtClientBirthday.setFont(FrameDesign.CALIBRI_BOLD_14);

        txtClientSex.setForeground(FrameDesign.CREAM);
        txtClientSex.setBackground(FrameDesign.NAVY_BLUE);
        txtClientSex.setFont(FrameDesign.CALIBRI_BOLD_14);

        comboEdit.setBackground(FrameDesign.GREY);
        comboEdit.setFont(FrameDesign.CALIBRI_BOLD_14);
    }

    public void editChoice(){
        if(comboEdit.getSelectedItem().toString() == "Edit Name"){
            clientProfileController.editName(this, frame, idClient);
        }

        if(comboEdit.getSelectedItem().toString() == "Edit Birthday"){
            clientProfileController.editBirthday(this, frame, idClient);
        }

    }
}

