package com.example.tictactoe_minimax;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class RealPlayer extends AppCompatActivity {

    private Handler handler;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_player);
        handler = new Handler(Looper.getMainLooper());
        showDelayedToast();
    }

//    private static String getDataForNthChild(DatabaseReference parentReference, int desiredChildIndex) {
//        // Order by key and limit to the desired child
//        Query query = parentReference.orderByKey().limitToFirst(desiredChildIndex + 1);
//
//        final String[] data = {""};
//        // Read the data
//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
//                    // Access the data of the nth child
//                    String childData = childSnapshot.getValue(String.class);
//                    data[0] = childData;
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                System.err.println("Error reading data: " + databaseError.getMessage());
//            }
//        });
//        return data[0];
//    }

    private void showDelayedToast()
    {
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                DatabaseReference MyDbase = database.getReference();
                String opponent = getPlayerWaiting();
            }
        }, 5000);
    }

    private String getPlayerWaiting() {
        String Player2 = null ;
        DatabaseReference myDbase = database.getReference();
        myDbase.child("Matching").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String pos = " ";
                for(int a = 0 ; a < 10 ;a ++)
                {
                    //hame yha check krna hai pehle agar saare slots khaali hue to ise waiting me daal do nhi to pair bna do
                    String data = (String) dataSnapshot.child(Integer.toString(a)).getValue();
                    if(!(data == null) && !data.equals(MainActivity.id))
                    {
                            pos = data;
                    }
                }
                if(pos.equals(" "))
                {
                    myDbase.child("Paired").child("p1").setValue(MainActivity.id);
                    myDbase.child("Paired").child("p2").setValue(pos);
                }
                // ab hamne use group r diya h
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                // Eat 5-Star do nothing
            }
        });
        return "";
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        DatabaseReference MyDbase = database.getReference();
//        MyDbase.child(MainActivity.id).removeValue();
        MyDbase.child("Matching").child(MainActivity.id).removeValue();

    }
}