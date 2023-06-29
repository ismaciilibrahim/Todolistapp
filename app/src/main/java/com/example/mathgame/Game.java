package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class Game extends AppCompatActivity {

    //defining components
    TextView score,life,time,question;
    EditText answer;
    Button ok,next;

    //random class

    Random random = new Random();

    //integer container
    int number1;
    int number2;
    //useranswer container
    int useranswer;

    //realanswer container
    int realanswer;
    //score container
    int userscore=0;
    //userlife container

    int userlife =3;
    //classka time
    CountDownTimer timer;
    //60000 thousand waxa weeye inaad timeka uu xisabisid as millisocends
    //halkii second waxa le gyhy 1000thousand miiliseconds
    //sido kale final values waa inay noqdan capital letters

    private static final long START_TIMER_IN_MILLIS = 30000;
    //boolean value lagu oganayo waqtigu inuu shaqeeyno inkale

    boolean timer_runing;

    long time_left_miilis = START_TIMER_IN_MILLIS;

    @SuppressLint({"NewApi", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primarry)));
        getWindow().setStatusBarColor(ContextCompat.getColor(Game.this,R.color.primarry));
        getWindow().setNavigationBarColor(ContextCompat .getColor(Game.this,R.color.primarry));

        //matching id
        score = findViewById(R.id.textViewscore2);
        life = findViewById(R.id.textViewllifescore);
        time = findViewById(R.id.Time2);
        question = findViewById(R.id.textViewquestion);
        answer = findViewById(R.id.editTextAnswer);
        ok = findViewById(R.id.buttonOk);
        next = findViewById(R.id.buttonnextquestion);

        gameContinue();


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                useranswer=Integer.valueOf(answer.getText().toString());



                //userku markuu taabto  ok button si uuu timeku  istaago




                if (useranswer == realanswer){

                    userscore = userscore +10;
                    score.setText(""+userscore);
                    question.setText("Congratulation Your answer is true");

                }else {

                    userlife = userlife -1;
                    life.setText(""+userlife);
                    question.setText("Sorry your answer is wrong");

                }
                pauseTimer();


            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer.setText("");
                //markuu uu userku su,aasha xigta tabto waqtigu dib ubilowd
                resetTimer();
                gameContinue();


                if (userlife<=0){
                    Toast.makeText(Game.this, "Game over!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Game.this,Result.class);
                    intent.putExtra("score",userscore);
                    startActivity(intent);
                    finish();
                }else {
                        gameContinue();
                }







            }
        });
    }
    //random class waxu su,aalaha u ganerate garynya si aan la qiysi karin

    public void  gameContinue(){
        //random class object

        //waxu classka random generate garyna number kabacdi waxu ku kaydinaya containerka integer num1
        //boundarty waxa weeye waxan xadidyna numbers uu generate garynayo
        number1 = random.nextInt(100);
        number2 = random.nextInt(100);
        realanswer = number1+number2;

        question.setText(number1+" + "+number2+"");
        startTimer();
    }
    //methodka waqtiga

    public  void startTimer(){
        //construcorkan waxu qaata labo paramter
        //kan kowad waaa waqtiga aan rabno inuu socdo timerkan waa hal daqiiqo
        //kan labadna waxa weeye interval waa inta uu boodayo markiiba
        //waxan kadhigny 1000 second after hal second soco
        timer = new CountDownTimer(time_left_miilis,1000) {
            @Override
            //waxan rabno inaan qabano second kasta oo waqtigas waxan kuqoryna methodkan
            public void onTick(long millsUntilfinshed) {

                //waxa weeye timerku waxu shaqynya lixdan second ookalia
                time_left_miilis = millsUntilfinshed;
                updateText();

            }

            //methodkan waxa kuqorysa waxa rabtid inaad qabtid markuu waqtigu dhamado

            @Override
            public void onFinish() {

                //markuu waqtigu dhamado timerku ha istaago
                timer_runing = false;
                pauseTimer();
                resetTimer();
                updateText();
                //hadii usan userku kaga jawabin su,aasha waqtiga lasiiyey
                //lifekiisa ayaa hoos udhacaya

                userlife = userlife -1;
                life.setText(""+userlife);
                question.setText("The timer is up!");

            }
        }.start();

        timer_runing=true;
    }

    //methodkan wxa loo adeegsnya in waqtin uu dib kasoo bilowdo
    public void resetTimer() {
        time_left_miilis =START_TIMER_IN_MILLIS;
        updateText();
    }

    //methodkan waxa loo adeegsnay in text viewga lagu update garey

    public void updateText() {
        //tan waxaynoo sheegysa waqtiga inta hara marwalb
        int second= (int)(time_left_miilis/1000)%60;
        //kabacdi waxan ubadlyna integer string value
        //string.format waxay natusnynsa waqti cayiman ama waqtiga gaarka ah
        //sidookale waxa kusheegny pranthesiska format inuu noqnyo numbers

        String time_left = String.format(Locale.getDefault(),"%02d",second);
        time.setText(time_left);

    }

    //methodkan waxa uu adeegsnyna in waqtiga lagu joojiyo
    public void  pauseTimer(){

        timer.cancel();
        timer_runing =false;

    }
}