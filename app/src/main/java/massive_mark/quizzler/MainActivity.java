package massive_mark.quizzler;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {

    // TODO: Declare member variables here:
    Button mTrueButton, mFalseButton;
    TextView mQuestiontextView;
    TextView mScoreTextView;
    ProgressBar mProgressBar;
    int mindex;
    int mquestion;
    int mScore;
    Toast mToastMessage;

    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };

    // TODO: Declare constants here
    final int PROGRESS_BAR_INCREMENT = (int)Math.ceil(100.0 / mQuestionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTrueButton = (Button)findViewById(R.id.true_button);
        mFalseButton = (Button)findViewById(R.id.false_button);
        mQuestiontextView = (TextView)findViewById(R.id.question_text_view);
        mScoreTextView = (TextView)findViewById(R.id.score);
        mProgressBar = (ProgressBar)findViewById(R.id.progress_bar);

        if(savedInstanceState != null){
            mScore = savedInstanceState.getInt("ScoreKey");
            mindex = savedInstanceState.getInt("IndexKey");
            mScoreTextView.setText("Score "+mScore+"/"+mQuestionBank.length);
        }else{
            mScore = 0;
            mindex = 0;
        }

        mquestion = mQuestionBank[mindex].getmQuestionID();
        mQuestiontextView.setText(mquestion);


        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
                updateQuestion();
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
                updateQuestion();
            }
        });

    }

    public void updateQuestion(){
        mindex = (mindex+1) % mQuestionBank.length;

        if(mindex == 0){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Game Over");
            alert.setCancelable(false);
            alert.setMessage("your scored " + mScore + "point");
            alert.setPositiveButton("close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            alert.show();
        }

        mquestion = mQuestionBank[mindex].getmQuestionID();
        mQuestiontextView.setText(mquestion);
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        mScoreTextView.setText("Score "+mScore+"/"+mQuestionBank.length);
    }

    public void checkAnswer(boolean userSelection){
        boolean correctAnswer = mQuestionBank[mindex].ismAnswer();

        if(mToastMessage != null){
            mToastMessage.cancel();
        }

        if(correctAnswer == userSelection){
            mToastMessage = makeText(getApplicationContext(),R.string.correct_toast,Toast.LENGTH_SHORT);
            mScore = mScore + 1;
        }else{
            mToastMessage = makeText(getApplicationContext(),R.string.incorrect_toast,Toast.LENGTH_SHORT);
        }
        mToastMessage.show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        outState.putInt("ScoreKey", mScore);
        outState.putInt("IndexKey", mindex);

    }

}