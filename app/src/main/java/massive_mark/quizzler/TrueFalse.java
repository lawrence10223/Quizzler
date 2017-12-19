package massive_mark.quizzler;

/**
 * Created by aiden on 2017/12/19.
 */

public class TrueFalse {
    private int mQuestionID;
    private boolean mAnswer;

    public TrueFalse(int QuestionResourceID,boolean trueOrFalse){
        mQuestionID = QuestionResourceID;
        mAnswer = trueOrFalse;
    }

    public int getmQuestionID() {
        return mQuestionID;
    }

    public void setmQuestionID(int mQuestionID) {
        this.mQuestionID = mQuestionID;
    }

    public boolean ismAnswer() {
        return mAnswer;
    }

    public void setmAnswer(boolean mAnswer) {
        this.mAnswer = mAnswer;
    }
}
