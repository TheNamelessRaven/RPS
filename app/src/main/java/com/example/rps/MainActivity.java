package com.example.rps;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.util.Random;


public class MainActivity<Imageview> extends AppCompatActivity {
    ImageView hp1, hp2, hp3, usrhp1, usrhp2, usrhp3, aicard, playercard, scisor, paper, rock;
    int user, ai, dw, health, aihealth;
    TextView draw;
    Boolean game = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random rnd = new Random();
        init();

        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = 1;
                playercard.setImageResource(R.drawable.rock);
                aimove();


            }
        });

        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = 2;
                playercard.setImageResource(R.drawable.paper);
                aimove();

            }
        });
        scisor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = 3;
                playercard.setImageResource(R.drawable.scissors);
                aimove();

            }
        });
    }

    private void userhp() {
        switch (health) {
            case 3:
                usrhp1.setImageResource(R.drawable.heart1);
                health--;
                break;
            case 2:
                usrhp2.setImageResource(R.drawable.heart1);
                health--;
                break;
            case 1:
                usrhp3.setImageResource(R.drawable.heart1);
                health--;
                break;
            /*case 0:
                Context context = getApplicationContext();
                CharSequence text = "Vesztettél";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                break;*/
        }
    }

    private void aihp() {
        switch (aihealth) {
            case 3:
                hp3.setImageResource(R.drawable.heart1);
                aihealth--;
                break;
            case 2:
                hp2.setImageResource(R.drawable.heart1);
                aihealth--;
                break;
            case 1:
                hp1.setImageResource(R.drawable.heart1);
                aihealth--;
                break;
            /*case 0:
                Context context = getApplicationContext();
                CharSequence text = "Nyertél";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                break;*/
        }

    }

    private void aimove() {
        if(health<=0||aihealth<=0) {
            game=true;
        }
        else {
            Random rnd = new Random();
            ai = rnd.nextInt(3) + 1;
            //Itt döntjük el az eredményeket is...

            if (ai == 1) {

                ai = 1;
                aicard.setImageResource(R.drawable.rock);
                if (user == 3) {
                    aihp();
                } else if (user == 2) {
                    userhp();
                } else if (user == ai) {
                    dw++;
                    draw.setText(Integer.toString(dw));
                }
            }
            if (ai == 2) {
                ai = 2;
                aicard.setImageResource(R.drawable.paper);
                if (user == 3) {
                    aihp();
                } else if (user == 1) {
                    userhp();
                } else if (user == ai) {
                    dw++;
                    draw.setText(Integer.toString(dw));
                }


            }
            if (ai == 3) {
                ai = 3;
                aicard.setImageResource(R.drawable.scissors);
                if (user == 1) {
                    aihp();
                } else if (user == 2) {
                    userhp();
                } else if (user == ai) {
                    dw++;
                    draw.setText(Integer.toString(dw));
                }
            }
        }

        if(game) {
            if (health <= 0) {
                Context context = getApplicationContext();
                CharSequence text = "Vesztettél";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                alertView("Vesztettél!");
            }
            if(aihealth <= 0){
                Context context = getApplicationContext();
                CharSequence text = "Nyertél";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                alertView("Győztél!");
            }
        }
    }

    private void init() {
        //Inicializáljuk a meglévő értékeinket
        rock = findViewById(R.id.rock);
        paper = findViewById(R.id.paper);
        scisor = findViewById(R.id.scisor);
        playercard = findViewById(R.id.playercard);
        aicard = findViewById(R.id.aicard);
        hp1 = findViewById(R.id.hp1);
        hp2 = findViewById(R.id.hp2);
        hp3 = findViewById(R.id.hp3);
        usrhp1 = findViewById(R.id.usrhp1);
        usrhp2 = findViewById(R.id.usrhp2);
        usrhp3 = findViewById(R.id.usrhp3);
        health = 3;
        aihealth = 3;
        dw = 0;
        ai = (int) (Math.random() * 3) + 1;
        draw = findViewById(R.id.draw);

    }

    private void alert(String message) {
        AlertDialog.Builder ai = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Nyertél")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

    }
    private void alertView( String message ) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle( "Szeretnél újra játszani?" )
                .setMessage(message)
                .setNegativeButton("Kilépés", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                        finish();
                        System.exit(0);
                    }})
                .setPositiveButton("Új játék", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        newgame();
                    }
                }).show();
    }

    private void newgame(){
        dw=0;
        draw.setText(Integer.toString(dw));
        health=3;
        aihealth=3;
        usrhp1.setImageResource(R.drawable.heart2);
        usrhp2.setImageResource(R.drawable.heart2);
        usrhp3.setImageResource(R.drawable.heart2);
        hp1.setImageResource(R.drawable.heart2);
        hp2.setImageResource(R.drawable.heart2);
        hp3.setImageResource(R.drawable.heart2);



    }
}