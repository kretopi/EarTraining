package com.example.eartraining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import org.puredata.android.io.AudioParameters;
import org.puredata.android.io.PdAudio;
import org.puredata.android.utils.PdUiDispatcher;
import org.puredata.core.PdBase;
import org.puredata.core.PdListener;
import org.puredata.core.utils.IoUtils;
import org.puredata.core.utils.PdDispatcher;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    PdDispatcher dispatcher;
    private int Answer;
    private int IsCorrect;

    public String CorrectAnswer;
    public String IsCorrectAnswer;

    private void initPD() throws IOException {
        // Set audio parameters

        int sampleRate = AudioParameters.suggestSampleRate();
        PdAudio.initAudio(sampleRate, 0, 2, 64, true);


        // Set receiver
        dispatcher = new PdUiDispatcher();
        PdBase.setReceiver(dispatcher);

        dispatcher.addListener("CorrectAnswer", new PdListener.Adapter() {
            @Override
            public void receiveBang(String source) {

            }
            @Override
            public void receiveFloat(String source, float x) {
                if(source.equals("CorrectAnswer")) {
                    Answer = (int)x;
                    switch(Answer){
                        case 0:
                            CorrectAnswer = "Perfect Unison";
                            break;

                        case 1:
                            CorrectAnswer = "Minor Second";
                            break;

                        case 2:
                            CorrectAnswer = "Major Second";
                            break;

                        case 3:
                            CorrectAnswer = "Minor Third";
                            break;

                        case 4:
                            CorrectAnswer = "Major Third";
                            break;

                        case 5:
                            CorrectAnswer = "Perfect Fourth";
                            break;

                        case 6:
                            CorrectAnswer = "Triton";
                            break;

                        case 7:
                            CorrectAnswer = "Perfect Fifth";
                            break;

                        case 8:
                            CorrectAnswer = "Minor Sixth";
                            break;

                        case 9:
                            CorrectAnswer = "PMajor Sixth";
                            break;

                        case 10:
                            CorrectAnswer = "Minor Seventh";
                            break;

                        case 11:
                            CorrectAnswer = "Major Seventh";
                            break;

                        case 12:
                            CorrectAnswer = "Perfect Octave";
                            break;

                        case 99:
                            CorrectAnswer = " ";
                            break;
                    }/*
                    if (Answer == 0) {
                        CorrectAnswer = "Perfect Unison";
                    }
                    if (Answer == 1) {
                        CorrectAnswer = "Minor Second";
                    }
                    if (Answer == 2) {
                        CorrectAnswer = "Major Second";
                    }
                    if (Answer == 3) {
                        CorrectAnswer = "Minor Third";
                    }
                    if (Answer == 4) {
                        CorrectAnswer = "Major Third";
                    }
                    if (Answer == 5) {
                        CorrectAnswer = "Perfect Fourth";
                    }
                    if (Answer == 6) {
                        CorrectAnswer = "Triton";
                    }
                    if (Answer == 7) {
                        CorrectAnswer = "Perfect Fifth";
                    }
                    if (Answer == 8) {
                        CorrectAnswer = "Minor Sixth";
                    }
                    if (Answer == 9) {
                        CorrectAnswer = "Major Sixth";
                    }
                    if (Answer == 10) {
                        CorrectAnswer = "Minor Seventh";
                    }
                    if (Answer == 11) {
                        CorrectAnswer = "Major Seventh";
                    }
                    if (Answer == 12) {
                        CorrectAnswer = "Perfect Octave";
                    }
                    if (Answer == 99) {
                        CorrectAnswer = " ";
                    }*/
                }

                final TextView Score = findViewById(R.id.Score);
                Score.setText(CorrectAnswer);

            }
        });

        dispatcher.addListener("IsCorrect", new PdListener() {
            @Override
            public void receiveBang(String source) {

            }

            @Override
            public void receiveFloat(String source, float x) {
                if(source.equals("IsCorrect")) {
                    IsCorrect = (int)x;
                    if (IsCorrect == 0) {
                        IsCorrectAnswer = "Wrong";
                    }
                    if (IsCorrect == 1) {
                        IsCorrectAnswer = "Correct";
                    }
                    if (IsCorrect == 2) {
                        IsCorrectAnswer = " ";
                    }
                }
                final TextView isCorrect = findViewById(R.id.isCorrect);
                isCorrect.setText(IsCorrectAnswer);

            }

            @Override
            public void receiveSymbol(String source, String symbol) {

            }

            @Override
            public void receiveList(String source, Object... args) {

            }

            @Override
            public void receiveMessage(String source, String symbol, Object... args) {

            }
        });
    }

    private void initGui() {
        // Connect UI to program and pd

        Button RandomInterval = findViewById(R.id.RandomInterval);
        RandomInterval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PdBase.sendBang("Start");
            }
        });

        Button Repeat = findViewById(R.id.Repeat);
        Repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PdBase.sendBang("Repeat");
            }
        });

        Button Unison = findViewById(R.id.Unison);
        Unison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PdBase.sendFloat("Interval", 0);
            }
        });

        Button MinorSecond = findViewById(R.id.MinorSecond);
        MinorSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PdBase.sendFloat("Interval", 1);
            }
        });

        Button MajorSecond = findViewById(R.id.MajorSecond);
        MajorSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PdBase.sendFloat("Interval", 2);
            }
        });

        Button MinorThird = findViewById(R.id.MinorThird);
        MinorThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PdBase.sendFloat("Interval", 3);
            }
        });

        Button MajorThird = findViewById(R.id.MajorThird);
        MajorThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PdBase.sendFloat("Interval", 4);
            }
        });

        Button PerfectFourth = findViewById(R.id.PerfectFourth);
        PerfectFourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PdBase.sendFloat("Interval", 5);
            }
        });

        Button Tritone = findViewById(R.id.Tritone);
        Tritone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PdBase.sendFloat("Interval", 6);
            }
        });

        Button PerfectFifth = findViewById(R.id.PerfectFifth);
        PerfectFifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PdBase.sendFloat("Interval", 7);
            }
        });

        Button MinorSixth = findViewById(R.id.MinorSixth);
        MinorSixth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PdBase.sendFloat("Interval", 8);
            }
        });

        Button MajorSixth = findViewById(R.id.MajorSixth);
        MajorSixth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PdBase.sendFloat("Interval", 9);
            }
        });

        Button MinorSeventh = findViewById(R.id.MinorSeventh);
        MinorSeventh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PdBase.sendFloat("Interval", 10);
            }
        });

        Button MajorSeventh = findViewById(R.id.MajorSeventh);
        MajorSeventh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PdBase.sendFloat("Interval", 11);
            }
        });
        Button PerfectOctave = findViewById(R.id.PrefectOctave);
        PerfectOctave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PdBase.sendFloat("Interval", 12);
            }
        });

        Switch Mode = findViewById(R.id.Mode);
        Mode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                float val = (b) ? 1.0f : 0.0f;
                PdBase.sendFloat("HarmMel", val);
            }
        });

        SeekBar TempoSeekBar = findViewById(R.id.TempoSeekBar);
        TempoSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                PdBase.sendFloat("Time", i + 100);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    private void loadPDPatch() throws IOException {
        // Open pd patch

        File dir = getFilesDir();
        IoUtils.extractZipResource(getResources().openRawResource(R.raw.ear_training), dir,true);
        File pdPatch = new File(dir, "ear_training.pd");
        PdBase.openPatch(pdPatch.getAbsolutePath());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            initPD();
            loadPDPatch();
        } catch (IOException e) {
            //finish();
        }
        initGui();
    }

    protected void onResume() {
        super.onResume();
        PdAudio.startAudio(this);
    }
    protected void onDestroy() {
        super.onDestroy();
        PdAudio.stopAudio();
    }
}

