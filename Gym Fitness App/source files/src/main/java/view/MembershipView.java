package view;

import controller.MembershipController;
import utils.FrameDesign;

import javax.swing.*;
import java.awt.*;

public class MembershipView {

    private JFrame frame;

    private JLabel chooseType = new JLabel("Choose the type of membership you want: ");

    private JButton silverButton = new JButton("Silver");
    private JButton goldButton = new JButton("Gold");
    private JButton platinumButton = new JButton("Platinum");

    private JTextArea silverTextArea = new JTextArea("Silver membership consists of 10 bookings/month, for the price of 90 RON.");
    private JTextArea goldTextArea = new JTextArea("Gold membership consists of 20 bookings/month, for the price of 170 RON.");
    private JTextArea platinumTextArea = new JTextArea("Platinum membership consists of unlimited bookings/month, for the price of 230 RON.");

    private final String idClient;

    private final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

    private final MembershipController membershipController;

    public MembershipView(String idClient) {
        membershipController = new MembershipController();

        frame = new JFrame("Create Membership");

        this.idClient = idClient;

        initializeMembershipView();

        setFrameDesign();

        frame.setVisible(true);

    }

    private void initializeMembershipView() {
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);

        chooseType.setBounds(140, 15, 350, 30);
        silverButton.setBounds(140, 75, 300, 30);
        silverTextArea.setBounds(70,135,500,30);
        goldButton.setBounds(140,195,300,30);
        goldTextArea.setBounds(70,255,500,30);
        platinumButton.setBounds(140,315,300,30);
        platinumTextArea.setBounds(40,375,550,30);

        silverTextArea.setEditable(false);
        goldTextArea.setEditable(false);
        platinumTextArea.setEditable(false);

        addItemsToFrame();

        frame.setLocation(dimension.width / 2 - frame.getSize().width / 2, dimension.height / 2 - frame.getSize().height / 2);

        silverButton.addActionListener(e -> membershipController.createMembership(idClient, "Silver"));
        goldButton.addActionListener(e -> membershipController.createMembership(idClient, "Gold"));
        platinumButton.addActionListener(e -> membershipController.createMembership(idClient, "Platinum"));

    }

    private void addItemsToFrame(){
        frame.getContentPane().add(chooseType)
                .getParent().add(silverButton)
                .getParent().add(silverTextArea)
                .getParent().add(goldButton)
                .getParent().add(goldTextArea)
                .getParent().add(platinumButton)
                .getParent().add(platinumTextArea);
    }


    private void setFrameDesign(){
        ImageIcon img = new ImageIcon("dumbbell.png");
        frame.setIconImage(img.getImage());
        frame.getContentPane().setBackground(FrameDesign.NAVY_BLUE);

        chooseType.setForeground(FrameDesign.DARK_PINK);
        chooseType.setBackground(FrameDesign.NAVY_BLUE);
        chooseType.setFont(FrameDesign.CALIBRI_BOLD_16);

        silverTextArea.setForeground(FrameDesign.CREAM);
        silverTextArea.setBackground(FrameDesign.NAVY_BLUE);
        silverTextArea.setFont(FrameDesign.CALIBRI_BOLD_14);

        goldTextArea.setForeground(FrameDesign.CREAM);
        goldTextArea.setBackground(FrameDesign.NAVY_BLUE);
        goldTextArea.setFont(FrameDesign.CALIBRI_BOLD_14);

        platinumTextArea.setForeground(FrameDesign.CREAM);
        platinumTextArea.setBackground(FrameDesign.NAVY_BLUE);
        platinumTextArea.setFont(FrameDesign.CALIBRI_BOLD_14);

        silverButton.setBackground(FrameDesign.LIGHT_BLUE);
        silverButton.setFont(FrameDesign.CALIBRI_BOLD_14);

        goldButton.setBackground(FrameDesign.LIGHT_BLUE);
        goldButton.setFont(FrameDesign.CALIBRI_BOLD_14);

        platinumButton.setBackground(FrameDesign.LIGHT_BLUE);
        platinumButton.setFont(FrameDesign.CALIBRI_BOLD_14);
    }

}
