import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class Student {
    int id;
    String name;
    int age;
    String course;
}

public class StudentManagementGUI {
    public static void
    updateSerialNumbers(DefaultTableModel model) {
        for (int i =0; i < model.getRowCount(); i++) {

            model.setValueAt(i + 1, i, 0);
    }
    }
    public static void main(String[] args) {

        ArrayList<Student> students = new ArrayList<>();

        JFrame frame = new JFrame("Student Management System");
        frame.setSize(600, 650);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(40, 44, 52));
        frame.getContentPane().setBackground(new Color(173, 216, 230));

        // Labels
        JLabel idLabel = new JLabel("ID");
        idLabel.setBounds(50, 50, 100, 30);
        frame.add(idLabel);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(50, 100, 100, 30);
        frame.add(nameLabel);

        JLabel ageLabel = new JLabel("Age");
        ageLabel.setBounds(50, 150, 100, 30);
        frame.add(ageLabel);

        JLabel courseLabel = new JLabel("Course");
        courseLabel.setBounds(50, 200, 100, 30);
        frame.add(courseLabel);

        idLabel.setForeground(Color.WHITE);
        nameLabel.setForeground(Color.WHITE);
        ageLabel.setForeground(Color.WHITE);
        courseLabel.setForeground(Color.WHITE);

        // TextFields
        JTextField idField = new JTextField();
        idField.setBounds(150, 50, 150, 30);
        frame.add(idField);

        JTextField nameField = new JTextField();
        nameField.setBounds(150, 100, 150, 30);
        frame.add(nameField);

        JTextField ageField = new JTextField();
        ageField.setBounds(150, 150, 150, 30);
        frame.add(ageField);

        JTextField courseField = new JTextField();
        courseField.setBounds(150, 200, 150, 30);
        frame.add(courseField);

        // Buttons
        JButton addButton = new JButton("Add");
        addButton.setBounds(50, 280, 100, 30);
        frame.add(addButton);

        JButton viewButton = new JButton("View");
        viewButton.setBounds(170, 280, 100, 30);
        frame.add(viewButton);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(290, 280, 100, 30);
        frame.add(searchButton);

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(100, 340, 100, 30);
        frame.add(updateButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(250, 340, 100, 30);
        frame.add(deleteButton);

        addButton.setBackground(new Color(76, 175, 80));
        viewButton.setBackground(new Color(33, 150, 243));
        searchButton.setBackground(new Color(156, 39, 176));
        updateButton.setBackground(new Color(255, 152, 0));
        deleteButton.setBackground(new Color(244, 67, 54));

        String[] columns = {"S.No", "ID", "Name","Age", "Course"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);
        
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 400, 450, 150);

        frame.add(scrollPane);

        // Add Student
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Student s = new Student();

                s.id = Integer.parseInt(idField.getText());
                s.name = nameField.getText();
                s.age = Integer.parseInt(ageField.getText());
                s.course = courseField.getText();

                students.add(s);

                model.addRow(new Object[]{
                    model.getRowCount() + 1,
                    s.id,
                    s.name,
                    s.age,
                    s.course
                });

                JOptionPane.showMessageDialog(frame,
                        "Student Added Successfully!");

                idField.setText("");
                nameField.setText("");
                ageField.setText("");
                courseField.setText("");
            }
        });

        // View Students
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (students.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "No Students Found!");
                } else {

                    String data = "";

                    for (Student s : students) {
                        data += "ID: " + s.id +
                                "\nName: " + s.name +
                                "\nAge: " + s.age +
                                "\nCourse: " + s.course +
                                "\n\n";
                    }

                    JOptionPane.showMessageDialog(frame, data);
                }
            }
        });

        // Search Student
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int id = Integer.parseInt(idField.getText());

                boolean found = false;

                for (Student s : students) {

                    if (s.id == id) {

                        JOptionPane.showMessageDialog(frame,
                                "Student Found\n\nName: "
                                        + s.name +
                                        "\nAge: "
                                        + s.age +
                                        "\nCourse: "
                                        + s.course);

                        found = true;
                        break;
                    }
                }

                if (!found) {
                    JOptionPane.showMessageDialog(frame,
                            "Student Not Found!");
                }
            }
        });

        // Update Student
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int id = Integer.parseInt(idField.getText());

                boolean found = false;

                for (Student s : students) {

                    if (s.id == id) {

                        s.name = nameField.getText();
                        s.age = Integer.parseInt(ageField.getText());
                        s.course = courseField.getText();

                        JOptionPane.showMessageDialog(frame,
                                "Student Updated Successfully!");

                        found = true;
                        break;
                    }
                }

                if (!found) {
                    JOptionPane.showMessageDialog(frame,
                            "Student Not Found!");
                }
            }
        });

        // Delete Student
        deleteButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {

        int selectedRow = table.getSelectedRow();

        if (selectedRow != -1) {

            int response = JOptionPane.showConfirmDialog(
        frame,
        "Are you sure you want to delete this student?",
        "Confirm Delete",
        JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION) {

        students.remove(selectedRow);
        model.removeRow(selectedRow);

        updateSerialNumbers(model);

            JOptionPane.showMessageDialog(
            frame,
            "Student Deleted Successfully!");
}
        } else {

            JOptionPane.showMessageDialog(frame,
                    "Please Select a Student from Table!");
        }
    }
});
        frame.setVisible(true);
    }
}