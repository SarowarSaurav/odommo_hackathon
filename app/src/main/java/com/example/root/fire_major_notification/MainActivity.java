package com.example.root.fire_major_notification;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
public class MainActivity extends AppCompatActivity {
    DatabaseReference Dref;
    String StatuS;
    TextView Text;
    Integer Stat;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Text= (TextView) findViewById(R.id.text);
        Dref= FirebaseDatabase.getInstance().getReference();
        Dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                StatuS=dataSnapshot.child("fire_sensor_status").getValue().toString();
                Stat = Integer.parseInt(StatuS);
                if (Stat == 1){
                    Text.setText("Fire Breakage at Your Home" );

                }
                else {
                    Text.setText("Everything Goes Fine no fire disaster" );
                }
            }
   @Override
   public void onCancelled(DatabaseError databaseError) {

   }
        });


        String tokenfordb = FirebaseInstanceId.getInstance().getToken();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        Log.d(TAG, "Refreshed token for Server: " + tokenfordb);
        myRef.setValue(tokenfordb);
    }
}
