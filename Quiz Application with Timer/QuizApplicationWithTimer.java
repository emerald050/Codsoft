import java.util.*;

// Class to represent a quiz question
class QuizQuestion {
    String question;
    String[] options;
    int correctOption; // 1-based index

    public QuizQuestion(String question, String[] options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }
}

// Main Quiz Application Class
public class QuizApplicationWithTimer {
    private List<QuizQuestion> questions;
    private int score = 0;
    private List<String> summary = new ArrayList<>();
    private final int TIME_LIMIT_SECONDS = 10;

    public QuizApplicationWithTimer() {
        questions = new ArrayList<>();
        loadQuestions();
    }

    // Load quiz questions
    private void loadQuestions() {
        questions.add(new QuizQuestion("Which keyword is used to inherit a class in Java?",
                new String[]{"implement", "extends", "inherits", "instanceof"}, 2));
        questions.add(new QuizQuestion("What is the default value of an int variable in Java?",
                new String[]{"0", "null", "undefined", "NaN"}, 1));
        questions.add(new QuizQuestion("Which of the following is not a Java access modifier?",
                new String[]{"public", "private", "protected", "internal"}, 4));
        questions.add(new QuizQuestion("What does JVM stand for?",
                new String[]{"Java Visual Machine", "Java Virtual Method", "Java Virtual Machine", "Java Variable Manager"}, 3));
        questions.add(new QuizQuestion("Which method is the entry point in a Java program?",
                new String[]{"start()", "run()", "main()", "init()"}, 3));
    }

    // Start the quiz
    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("🧠 Welcome to the Java Quiz!");
        System.out.println("You have " + TIME_LIMIT_SECONDS + " seconds to answer each question.\n");

        for (int i = 0; i < questions.size(); i++) {
            QuizQuestion q = questions.get(i);
            System.out.println("Q" + (i + 1) + ". " + q.question);

            for (int j = 0; j < q.options.length; j++) {
                System.out.println((j + 1) + ". " + q.options[j]);
            }

            int answer = getAnswerWithinTime(scanner);

            if (answer == q.correctOption) {
                score++;
                summary.add("Q" + (i + 1) + ": ✅ Correct");
            } else if (answer == -1) {
                summary.add("Q" + (i + 1) + ": ⏰ Timed out");
            } else {
                summary.add("Q" + (i + 1) + ": ❌ Incorrect (Correct: " + q.correctOption + ")");
            }

            System.out.println(); // spacing
        }

        showResults();
    }

    // Method to get input with a countdown timer
    private int getAnswerWithinTime(Scanner scanner) {
        final int[] answer = {-1};
        final boolean[] answered = {false};

        Thread inputThread = new Thread(() -> {
            try {
                System.out.print("Enter your answer (1-4): ");
                answer[0] = scanner.nextInt();
                answered[0] = true;
            } catch (Exception e) {
                answer[0] = -2;
            }
        });

        Thread timerThread = new Thread(() -> {
            for (int i = TIME_LIMIT_SECONDS; i > 0; i--) {
                if (answered[0]) break;
                System.out.print("\r⏳ Time left: " + i + " seconds   ");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        inputThread.start();
        timerThread.start();

        try {
            inputThread.join(TIME_LIMIT_SECONDS * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (inputThread.isAlive()) {
            inputThread.interrupt();
            System.out.println("\n⏰ Time's up!");
            return -1;
        }

        if (answer[0] < 1 || answer[0] > 4) {
            System.out.println("\n❌ Invalid option.");
            return -2;
        }

        return answer[0];
    }

    // Display result summary
    private void showResults() {
        System.out.println("🏁 Quiz Completed!");
        System.out.println("✅ Score: " + score + "/" + questions.size());
        System.out.println("\n📋 Summary:");
        for (String line : summary) {
            System.out.println(line);
        }
    }

    // Main method
    public static void main(String[] args) {
        QuizApplicationWithTimer quizApp = new QuizApplicationWithTimer();
        quizApp.startQuiz();
    }
}
