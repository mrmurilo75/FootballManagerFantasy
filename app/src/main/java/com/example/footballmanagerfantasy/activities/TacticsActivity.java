package com.example.footballmanagerfantasy.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.footballmanagerfantasy.R;
import com.example.footballmanagerfantasy.databinding.ActivityTacticsBinding;
import com.example.footballmanagerfantasy.gameEngine.GameEngine;
import com.example.footballmanagerfantasy.gameEngine.GameState;
import com.example.footballmanagerfantasy.gameEngine.Player;
import com.example.footballmanagerfantasy.gameEngine.Tactics;

import java.security.acl.Group;
import java.util.LinkedList;

public class TacticsActivity extends Fullscreen{

    private GameState gs = GameEngine.getGameState();
    private ActivityTacticsBinding binding;
    private Intent intent;
    private int indexI;
    private int indexJ;
    static public String playerName;
    private static float scale;
    private static final String positions = "GDMMMA";
    private static final int numberRows = 5;
    private static final int numberCols = 6;
    private Handler h = new Handler();
    private ImageAndText toSwith;
//    private int toSwithJ;
    private boolean flashing = false;

    class ImageAndText extends RelativeLayout{

        ImageView imageView;
        TextView textView;
        Boolean visible;

        public ImageAndText(Context context,int pos) {
            super(context);
            imageView = new ImageView(context);
            int width = pos == 2 || pos == 4? (int) (150 * scale + 0.5f) : (int) (100 * scale + 0.5f);
            int height = (int) (50 * scale + 0.5f);
            imageView.setLayoutParams(new LayoutParams(width,height));
            imageView.setImageResource(R.drawable.circle);
//            imageView.setBackgroundColor(getResources().getColor(R.color.black));
            textView = new TextView(context);
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_LEFT, imageView.getId());
            params.addRule(RelativeLayout.ALIGN_RIGHT, imageView.getId());
            params.addRule(RelativeLayout.ALIGN_TOP, imageView.getId());
            params.addRule(RelativeLayout.ALIGN_BOTTOM, imageView.getId());
            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(params);
            addView(imageView);
            addView(textView);
        }

        public void setText(String text){
            textView.setText(text);
        }

        public void clearText(){
            textView.setText("");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTacticsBinding.inflate(getLayoutInflater());
        scale = getResources().getDisplayMetrics().density;
        assignButtons();

        for(int i = 0;i < numberRows;i++){
            TableRow row = (TableRow)binding.formation.getChildAt(i);
            int finalI = i;
            for(int j = 0;j < numberCols;j++){
                int finalJ = j;
                ImageAndText col = new ImageAndText(this,j);
                row.addView(col);
                if(j == 0 && i != 2) {
                    col.setVisibility(View.INVISIBLE);
                    continue;
                }
                col.visible = false;
                col.setVisibility(View.INVISIBLE);
                col.setOnClickListener( (evt) -> {
                    ImageAndText evt1 = (ImageAndText)evt;
                    if(flashing && evt1.visible) {
                        return;
                    }
                    else if(flashing){
                        stopFlashing((ImageAndText) evt,finalI,finalJ);
                        return;
                    }
                    indexJ = finalJ;
                    indexI = finalI;
                    intent = new Intent(this,TacticsChoosePlayer.class);
                    intent.putExtra("i", finalI);
                    intent.putExtra("j", finalJ);
                    startActivity(intent);
                } );
                col.setOnLongClickListener((evt) -> {
//                    toSwithJ = finalJ;
                    return changePos(col);
                });
            }
        }
        putPlayers();
        setContentView(binding.getRoot());
    }

    private int toPos(int i,int j){
        int pos = i == 2 && j == 1 ? 0 : (j-1)*5+1+i;
        return pos;
    }

    private int[] toIJ(int pos){
        int i = pos == 0 ? 2 : (pos % 5) == 0 ? 4 : (pos % 5) - 1;
        int j = pos == 0 ? 0 : ((pos - 1 - i)/5) + 1;
        return new int[]{i,j};
    }

    private void putPlayers(){

        for(String playerName : gs.getTeamPlayers()){
            Player p = gs.getPlayer(playerName);
            if(p.fieldPos < 0) continue;
            int ij[] = toIJ(p.fieldPos);
            int i = ij[0];
            int j = ij[1];
            TableRow row = (TableRow)binding.formation.getChildAt(i);
            ImageAndText col = (ImageAndText)row.getChildAt(j);
            col.visible = true;
            col.setText(playerName);
            col.setVisibility(View.VISIBLE);
        }
    }

    Runnable flashingRun = new Runnable() {
        @Override
        public void run() {
            try {
//                String pos1 = positions.charAt(toSwithJ) + "";
                flashing(toSwith);
                for (int i = 0; i < numberRows; i++) {
                    TableRow row = (TableRow) binding.formation.getChildAt(i);
                    for (int j = 0; j < numberCols; j++) {
//                        String pos2 = positions.charAt(j) + "";
                        ImageAndText col = (ImageAndText) row.getChildAt(j);
//                        if(!pos1.equals(pos2)) continue;
                        if ((j == 0 && i != 2) || col.visible) {
                            continue;
                        }
                        flashing(col);
                    }
                }
            } finally {
                h.postDelayed(flashingRun, 1000);
            }
        }
    };

    private void stopFlashing(ImageAndText clicked,int clickedI,int clickedJ) {
        h.removeCallbacks(flashingRun);
        flashing = false;

        toSwith.setVisibility(View.INVISIBLE);

        for (int i = 0; i < numberRows; i++) {
            TableRow row = (TableRow) binding.formation.getChildAt(i);
            for (int j = 0; j < numberCols; j++) {
                ImageAndText col = (ImageAndText) row.getChildAt(j);
                if ((j == 0 && i != 2) || col.visible) {
                    continue;
                }
                col.setVisibility(View.INVISIBLE);
            }
        }
        clicked.setVisibility(View.VISIBLE);
        if(toSwith == clicked) return;
        Player p = gs.getPlayer(toSwith.textView.getText().toString());
        String pos = positions.charAt(clickedJ) + "";
        if(p.position.equals(pos)){
            clicked.setText(toSwith.textView.getText().toString());
            toSwith.clearText();
            toSwith.visible = false;
            clicked.visible = true;
            p.fieldPos = toPos(clickedI,clickedJ);
        }
        else{
            toSwith.clearText();
            toSwith.visible = false;
            clicked.visible = true;
            p.fieldPos = -1;
        }
        //trocar o fieldPos e nao permitir fora de posicao
    }

    private boolean changePos(ImageAndText column){
        this.toSwith = column;
        flashingRun.run();
        flashing = true;
        return true;
    }

    private void flashing(ImageAndText col){

        if(col.getVisibility() == View.VISIBLE){
            col.setVisibility(View.INVISIBLE);
        }
        else{
            col.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(playerName == null) return;
        TableRow row = (TableRow)binding.formation.getChildAt(indexI);
        ImageAndText col = (ImageAndText)row.getChildAt(indexJ);
        String oldPlayer = col.textView.getText().toString();
        if(!oldPlayer.equals("")) gs.getPlayer(oldPlayer).fieldPos = -1; //needs to be changed

        for (int i = 0; i < numberRows; i++) {
            TableRow r = (TableRow) binding.formation.getChildAt(i);
            for (int j = 0; j < numberCols; j++) {
                ImageAndText c = (ImageAndText) r.getChildAt(j);
                if(c.textView.getText().toString().equals(playerName)){
                    c.clearText();
                }
            }
        }

        col.setText(playerName);

        playerName = null;
    }

    private void clear(TableRow row, RadioButton b){
        int n = row.getChildCount();

        for(int i = 0;i < n;i++){
            RadioButton b1 = (RadioButton) row.getChildAt(i);
            if(b1 != b){
                b1.setChecked(false);
            }
        }
    }

    private void assignButtons(){


        binding.offensive.setOnClickListener( (evt) -> {
         gs.getClub().tactics.playStyle = Tactics.PlayStyle.Offensive;
         clear(binding.playStyle,(RadioButton) evt);
        });
        binding.balance.setOnClickListener( (evt) -> {
            gs.getClub().tactics.playStyle = Tactics.PlayStyle.Balance;
            clear(binding.playStyle,(RadioButton) evt);
        });
        binding.deffensive.setOnClickListener( (evt) -> {
            gs.getClub().tactics.playStyle = Tactics.PlayStyle.Defensive;
            clear(binding.playStyle,(RadioButton) evt);
        });
        binding.passShort.setOnClickListener( (evt) -> {
            gs.getClub().tactics.passStyle = Tactics.PassStyle.Short;
            clear(binding.passStyle,(RadioButton) evt);
        });
        binding.passDirect.setOnClickListener( (evt) -> {
            gs.getClub().tactics.passStyle = Tactics.PassStyle.Direct;
            clear(binding.passStyle,(RadioButton) evt);
        });
        binding.passLong.setOnClickListener( (evt) -> {
            gs.getClub().tactics.passStyle = Tactics.PassStyle.Long;
            clear(binding.passStyle,(RadioButton) evt);
        });
        binding.possession.setOnClickListener( (evt) -> {
            gs.getClub().tactics.attackStyle = Tactics.AttackStyle.Possession;
            clear(binding.attackStyle,(RadioButton) evt);
        });
        binding.counter.setOnClickListener( (evt) -> {
            gs.getClub().tactics.attackStyle = Tactics.AttackStyle.Counter;
            clear(binding.attackStyle,(RadioButton) evt);
        });
        binding.useWings.setOnClickListener( (evt) -> {
            gs.getClub().tactics.attackStyle = Tactics.AttackStyle.UseWings;
            clear(binding.attackStyle,(RadioButton) evt);
        });
        binding.highLine.setOnClickListener( (evt) -> {
            gs.getClub().tactics.defenseStyle = Tactics.DefenseStyle.HighLine;
            clear(binding.defenseStyle,(RadioButton) evt);
        });
        binding.lowLine.setOnClickListener( (evt) -> {
            gs.getClub().tactics.defenseStyle = Tactics.DefenseStyle.LowLine;
            clear(binding.defenseStyle,(RadioButton) evt);
        });
        binding.onSight.setOnClickListener( (evt) -> {
            gs.getClub().tactics.shootingStyle = Tactics.ShootingStyle.OnSight;
            clear(binding.shootingStyle,(RadioButton) evt);
        });
        binding.shootingStyle.setOnClickListener( (evt) -> {
            gs.getClub().tactics.shootingStyle = Tactics.ShootingStyle.InsideTheArea;
            clear(binding.shootingStyle,(RadioButton) evt);
        });
        binding.manToMan.setOnClickListener( (evt) -> {
            gs.getClub().tactics.marking = Tactics.Marking.ManToMan;
            clear(binding.marking,(RadioButton) evt);
        });
        binding.zone.setOnClickListener( (evt) -> {
            gs.getClub().tactics.marking = Tactics.Marking.Zone;
            clear(binding.marking,(RadioButton) evt);
        });
    }

}
