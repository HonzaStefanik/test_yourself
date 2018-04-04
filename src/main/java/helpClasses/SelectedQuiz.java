package helpClasses;

import model.Quiz;

public class SelectedQuiz {

    private static Quiz quiz;

    public static Quiz getQuiz() {
        return quiz;
    }

    public static void setQuiz(Quiz quiz) {
        SelectedQuiz.quiz = quiz;
    }
}

