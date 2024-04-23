package com.example.tictactoe_minimax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    static String id = null ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id = getRandomId();

        Button btnAi = findViewById(R.id.btnAi);
        Button btnFr = findViewById(R.id.btnFriend);
        Button btnOnline = findViewById(R.id.btnRealPlayer);

        btnAi.setOnClickListener(v -> {
            Intent P1 = new Intent(getApplicationContext(), AI.class);
            startActivity(P1);
        });
        btnFr.setOnClickListener(v -> {
            Intent P1 = new Intent(getApplicationContext(), TwoPlayer.class);
            startActivity(P1);
        });
        btnOnline.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DatabaseReference MyDbase = database.getReference();
                MyDbase.child(id).setValue(id);
                String WList = Long.toString(getChildCount(MyDbase));
                MyDbase.child("Matching").child(id).setValue(id);
                Intent P1 = new Intent(getApplicationContext() , RealPlayer.class);
                startActivity(P1);
            }
        });
    }

    private long getChildCount(DatabaseReference myDbase) {
        final long[] count = {0};
        myDbase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long childCount = dataSnapshot.getChildrenCount();
                count[0] = childCount;
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                // Eat 5-Star do nothing
            }
        });
        return count[0];
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DatabaseReference MyDbase = database.getReference();
        MyDbase.child(id).removeValue();
        MyDbase.child("Matching").child(id).removeValue();

    }

    private String getRandomId() {
        Random random = new Random();
        int randomId = random.nextInt(9999);
        return Integer.toString(randomId);
    }
}