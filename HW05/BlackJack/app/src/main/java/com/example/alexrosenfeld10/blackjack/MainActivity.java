package com.example.alexrosenfeld10.blackjack;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

    private TextView txtCurrentTotal;
    private GestureDetectorCompat GD;
    private static final String[] cards = new String[]{"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    TextView card1, card2, card3, cardsPulled;
    RelativeLayout layout;
    Button resetGame;
    private int count;
    private Boolean dealt = false;
    private Boolean gameOver = false;
    private String cardList = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCurrentTotal = (TextView) findViewById(R.id.txtCurrentTotal);
        cardsPulled = (TextView) findViewById(R.id.cardsPulled);
        GD = new GestureDetectorCompat(this, this);
        layout = (RelativeLayout) findViewById(R.id.rel);
        card1 = (TextView) findViewById(R.id.cardOne);
        card2 = (TextView) findViewById(R.id.cardTwo);
        card3 = (TextView) findViewById(R.id.cardThree);
        txtCurrentTotal = (TextView) findViewById(R.id.txtCurrentTotal);
        resetGame = (Button) findViewById(R.id.newgame);
        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {
                    recreate();
                }}}
        );
    }



    // Set up our gesture detector to receive touch events
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.GD.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        // hit the player a new card
        if(gameOver == false){
            //game still not over
            if(card1.getVisibility()== View.VISIBLE && card2.getVisibility() == View.VISIBLE){
                String nextCard = (cards[new Random().nextInt(cards.length)]);
                if(nextCard.equals("A") == false){
                    if(nextCard.equals("J") || nextCard.equals("Q") || nextCard.equals("K")){
                        count = count + 10;
                        cardList = cardList + " " + nextCard;
                        txtCurrentTotal.setText(Integer.toString(count));
                    }
                    else{
                        count = count + Integer.parseInt(nextCard);
                        cardList = cardList + " " + nextCard;
                        txtCurrentTotal.setText(Integer.toString(count));
                    }
                }
                else { // card is Ace
                    count = count + 1;
                    cardList = cardList + " " + nextCard;
                    txtCurrentTotal.setText(Integer.toString(count));
                }
                cardsPulled.setText("Cards pulled so far: " + cardList);
                card3.setText(nextCard);
                card3.setVisibility(View.VISIBLE);
            }
            if(count > 17 && count <= 21){
                Toast.makeText(MainActivity.this,"You Win",Toast.LENGTH_LONG).show();
                gameOver = true;
                return false;
                //user wins
            }

            if(count > 21){
                Toast.makeText(MainActivity.this,"You Lose",Toast.LENGTH_LONG).show();
                gameOver = true;
                return false;
                //user loses
            }

        }




        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        // re deal
        if(dealt == false) {
            if(gameOver == false){
                String dealtCard1 = (cards[new Random().nextInt(cards.length)]);
                String dealtCard2 = (cards[new Random().nextInt(cards.length)]);
                if(dealtCard1.equals("J") || dealtCard1.equals("Q") || dealtCard1.equals("K")){
                    count = count + 10;
                    cardList = cardList + " " + dealtCard1;
                    txtCurrentTotal.setText(Integer.toString(count));
                }
                else if(dealtCard1.equals("A")){
                    count = count +1;
                    cardList = cardList + " " + dealtCard1;
                    txtCurrentTotal.setText(Integer.toString(count));
                }
                else{
                    count = count + Integer.parseInt(dealtCard1);
                    cardList = cardList + " " + dealtCard1;
                    txtCurrentTotal.setText(Integer.toString(count));
                }

                if(dealtCard2.equals("J") || dealtCard2.equals("Q") || dealtCard2.equals("K")){
                    count = count + 10;
                    cardList = cardList + " " + dealtCard2;
                    txtCurrentTotal.setText(Integer.toString(count));
                }
                else if(dealtCard2.equals("A")){
                    count = count +1;
                    cardList = cardList + " " + dealtCard2;
                    txtCurrentTotal.setText(Integer.toString(count));
                }
                else{
                    count = count + Integer.parseInt(dealtCard2);
                    cardList = cardList + " " + dealtCard2;
                    txtCurrentTotal.setText(Integer.toString(count));
                }
                cardsPulled.setText("Cards pulled so far: "+ cardList);
                dealt = true;
                card1.setText(dealtCard1);
                card2.setText(dealtCard2);
                card1.setVisibility(View.VISIBLE);
                card2.setVisibility(View.VISIBLE);

                if(count > 17 && count <= 21){
                    Toast.makeText(MainActivity.this,"You Win",Toast.LENGTH_LONG).show();
                    gameOver = true;
                    return false;
                    //user wins
                }

                if(count > 21){
                    Toast.makeText(MainActivity.this,"You Lose",Toast.LENGTH_LONG).show();
                    gameOver = true;
                    return false;
                    //user loses
                }

                return true;
            }

        }

        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
