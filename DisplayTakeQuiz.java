import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.ServerSocket;
import java.util.Random;

public class DisplayTakeQuiz extends JComponent {
    JFrame frame1 = new JFrame("Quiz Options"); //which quiz do u want
    JFrame frame2 = new JFrame("Quiz:"); //for questions
    JLabel whichQuiz;
    JTextField enterQuiz;
    JButton enter;
    //Course c = new Course();




    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == enter) {
                enter(9);// course number j
            }
        }
    };

    public void enter(int j) {
        if (enterQuiz.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter a Quiz name.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            for (int i = 0; i < listofcourses.get(j).getListofquizzes().size(); i++) { // list of courses is an array in server

                //if statement in a for loop to find a valid input
                // maybe create a method in server to get course
            }
        }
    }



}
//for (int i = 0; i < listofcourses.get(j)
//        .getListofquizzes().size(); i++) {
//        System.out.println((i + 1) + ". " + listofcourses.get(j)
//        .getListofquizzes().get(i));
