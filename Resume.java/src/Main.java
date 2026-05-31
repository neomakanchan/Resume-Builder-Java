import javax.swing.*;
import java.io.FileWriter;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import com.itextpdf.text.Image;
import java.time.LocalDate;

public class Main {
    public static void main(String [] args) {
        JFrame frame = new JFrame ("Resume Builder");
        frame.setSize(600,600);
        frame.setLayout(null);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(20,20,250,30);

        JTextField nameField = new JTextField();
        nameField.setBounds(120,20,250,30);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(20,50,250,30);

        JTextField emailField = new JTextField();
        emailField.setBounds(120,50,250,30);

        JLabel phoneLabel = new JLabel("Phone Number");
        phoneLabel.setBounds(20,120,250,30);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(120,120,250,30);

        JLabel educationLabel = new JLabel("Education");
        educationLabel.setBounds(20,200,250,30);

        JTextField educationField = new JTextField();
        educationField.setBounds(120,200,250,30);

        JLabel skillsLabel = new JLabel("Skills");
        skillsLabel.setBounds(20,250,100,30);

        JTextArea skillsArea = new JTextArea();
        skillsArea.setBounds(120,250,250,80);

        JLabel projectLabel = new JLabel("Projects");
        projectLabel.setBounds(20,350,100,30);

        JTextArea projectArea = new JTextArea();
        projectArea.setBounds(120,350,250,80);

        JLabel certificationLabel = new JLabel("Certifications");
        certificationLabel.setBounds(20,440,100,30);

        JTextArea certificationArea = new JTextArea();
        certificationArea.setBounds(120,440,250,60);

        JLabel linkedinLabel = new JLabel("LinkedIn");
        linkedinLabel.setBounds(20,150,100,30);

        JTextField linkedinField = new JTextField();
        linkedinField.setBounds(120,150,250,30);

        JButton button = new JButton("Generate Resume");
        button.setBounds(70,500,180,40);

        JButton resetButton = new JButton("Reset");
        resetButton.setBounds(300,500,100,40);

        JButton photoButton = new JButton("Select Photo");
        photoButton.setBounds(420,500,130,40);
        final String[] photoPath = {""};

        button.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String education = educationField.getText();
            String skills = skillsArea.getText();
            String projects = projectArea.getText();

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Please fill all required fields!");

                return;
            }

           try {

//                FileWriter fw = new FileWriter("Resume.txt");
//
//                fw.write("====================================\n");
//                fw.write("            RESUME\n");
//                fw.write("====================================\n\n");
//
//                fw.write("Name       : " + name + "\n");
//                fw.write("Email      : " + email + "\n");
//                fw.write("Phone      : " + phone + "\n\n");
//
//                fw.write("EDUCATION\n");
//                fw.write("------------------------------------\n");
//                fw.write(education + "\n\n");
//
//                fw.write("SKILLS\n");
//                fw.write("------------------------------------\n");
//                fw.write(skills + "\n\n");
//
//                fw.write("PROJECTS\n");
//                fw.write("------------------------------------\n");
//                fw.write(projects + "\n\n");
//
//                fw.close();

//                JOptionPane.showMessageDialog(
//                        frame,
//                        "Resume Saved Successfully!");
//


               Document document = new Document();

               PdfWriter.getInstance(
                       document,
                       new FileOutputStream(name + "_RESUME.pdf"));

               document.open();

               if(!photoPath[0].isEmpty()) {

                   System.out.println(photoPath[0]);
                   Image image = Image.getInstance(photoPath[0]);

                   image.scaleToFit(100, 100);

                   document.add(image);
               }

               com.itextpdf.text.Font titleFont =
                       new com.itextpdf.text.Font(
                               com.itextpdf.text.Font.FontFamily.HELVETICA,
                               18,
                               com.itextpdf.text.Font.BOLD);

               com.itextpdf.text.Font headingFont =
                       new com.itextpdf.text.Font(
                               com.itextpdf.text.Font.FontFamily.HELVETICA,
                               14,
                               com.itextpdf.text.Font.BOLD);

               document.add(new Paragraph("RESUME", titleFont));
               document.add(new Paragraph(" "));

               document.add(new Paragraph("Name: " + name));
               document.add(new Paragraph("Email: " + email));
               document.add(new Paragraph("Phone: " + phone));
               document.add(new Paragraph("LinkedIn: " + linkedinField.getText()));

               document.add(new Paragraph("==================================="));

               document.add(new Paragraph(" "));
               document.add(new Paragraph("EDUCATION" , headingFont));
               document.add(new Paragraph("==================================="));
               document.add(new Paragraph(education));

               document.add(new Paragraph(" "));
               document.add(new Paragraph("SKILLS" , headingFont));
               document.add(new Paragraph(skills));

               document.add(new Paragraph(" "));
               document.add(new Paragraph("PROJECTS", headingFont));
               document.add(new Paragraph(projects));

               document.add(new Paragraph(" "));
               document.add(new Paragraph("CERTIFICATIONS"));
               document.add(new Paragraph(certificationArea.getText()));

               document.add(new Paragraph("Generated on: " + LocalDate.now()));



               document.close();

               java.awt.Desktop.getDesktop().open(
                       new java.io.File(name + "_RESUME.pdf"));

               JOptionPane.showMessageDialog(
                       frame,
                       "PDF Created Successfully!");
           }
           catch(Exception ex) {

                ex.printStackTrace();

            }
        });


            resetButton.addActionListener(e -> {

                nameField.setText("");
                emailField.setText("");
                phoneField.setText("");
                educationField.setText("");
                skillsArea.setText("");
                projectArea.setText("");
                String linkedin = linkedinField.getText();

            });

        photoButton.addActionListener(e -> {

            JFileChooser chooser = new JFileChooser();

            int result = chooser.showOpenDialog(frame);

            if(result == JFileChooser.APPROVE_OPTION) {

                photoPath[0] = chooser.getSelectedFile().getAbsolutePath();

                JOptionPane.showMessageDialog(
                        frame,
                        "Photo Selected Successfully!");

            }

        });

            frame.add(nameLabel);
            frame.add(nameField);
            frame.add(emailLabel);
            frame.add(emailField);
            frame.add(phoneLabel);
            frame.add(phoneField);
            frame.add(educationLabel);
            frame.add(educationField);
            frame.add(skillsLabel);
            frame.add(skillsArea);
            frame.add(projectLabel);
            frame.add(projectArea);
            frame.add(certificationLabel);
            frame.add(certificationArea);
            frame.add(linkedinLabel);
            frame.add(linkedinField);
            frame.add(button);
            frame.add(resetButton);
            frame.add(photoButton);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

}
}