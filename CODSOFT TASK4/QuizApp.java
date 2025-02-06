import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApp {
    private static final String[] questions = {
        "What is the capital of France?",
        "What is 2 + 2?",
        "Which programming language is this quiz written in?"
    };

    private static final String[][] options = {
        {"Paris", "London", "Berlin", "Madrid"},
        {"3", "4", "5", "6"},
        {"Java", "Python", "C++", "JavaScript"}
    };

    private static final String[] answers = {"Paris", "4", "Java"};
    private static int score = 0;
    private static int currentQuestion = 0;
    private static Timer timer;
    private static int timeLeft = 10; // Time limit for each question (in seconds)
    private static JLabel timerLabel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Quiz Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Timer Label
        timerLabel = new JLabel("Time Left: " + timeLeft + " seconds", JLabel.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(timerLabel, BorderLayout.NORTH);

        // Question Panel
        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new GridLayout(6, 1));

        JLabel questionLabel = new JLabel(questions[currentQuestion]);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        questionPanel.add(questionLabel);

        ButtonGroup optionGroup = new ButtonGroup();
        JRadioButton[] optionButtons = new JRadioButton[4];
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton(options[currentQuestion][i]);
            optionGroup.add(optionButtons[i]);
            questionPanel.add(optionButtons[i]);
        }

        // Next Button
        JButton nextButton = new JButton("Next");
        questionPanel.add(nextButton);

        frame.add(questionPanel, BorderLayout.CENTER);
        frame.setSize(400, 300);
        frame.setVisible(true);

        // Timer Logic
        startTimer();

        // Next Button ActionListener
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check the selected answer
                for (int i = 0; i < 4; i++) {
                    if (optionButtons[i].isSelected()) {
                        if (optionButtons[i].getText().equals(answers[currentQuestion])) {
                            score++;
                        }
                        break;
                    }
                }

                // Move to the next question
                currentQuestion++;

                if (currentQuestion < questions.length) {
                    questionLabel.setText(questions[currentQuestion]);
                    for (int i = 0; i < 4; i++) {
                        optionButtons[i].setText(options[currentQuestion][i]);
                        optionButtons[i].setSelected(false);
                    }

                    // Reset timer
                    timeLeft = 10;
                    timerLabel.setText("Time Left: " + timeLeft + " seconds");

                } else {
                    // Show Result
                    showResult();
                }
            }
        });
    }

    // Method to start the timer for each question
    public static void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeLeft--;
                timerLabel.setText("Time Left: " + timeLeft + " seconds");

                if (timeLeft <= 0) {
                    timer.cancel();
                    // Move to the next question automatically
                    currentQuestion++;
                    if (currentQuestion < questions.length) {
                        timeLeft = 10;
                        startTimer(); // Restart timer for the next question
                    } else {
                        showResult();
                    }
                }
            }
        }, 1000, 1000);
    }

    // Method to show the final result after all questions are answered
    public static void showResult() {
        JOptionPane.showMessageDialog(null, "Quiz Over!\nYour Score: " + score + "/" + questions.length, "Result", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}
