package com.example.testfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.testfirebase.Books;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText bookISBN,bookName,bookAuthor,bookPublisher;
    DatabaseReference databaseReference;
    Books book;

    long maxBookID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bookISBN=(EditText)findViewById(R.id.etISBN);
        bookName=(EditText)findViewById(R.id.etName);
        bookAuthor=(EditText)findViewById(R.id.etAuthor);
        bookPublisher=(EditText)findViewById(R.id.etPublisher);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Book");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    maxBookID=dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void onAdd(View view){
        String ISBN=bookISBN.getText().toString();
        String name=bookName.getText().toString();
        String author=bookAuthor.getText().toString();
        String publisher=bookPublisher.getText().toString();

        book=new Books();
        book.setISBNNo(ISBN);
        book.setName(name);
        book.setAuthor(author);
        book.setPublisher(publisher);

        databaseReference.child(String.valueOf(maxBookID+1)).setValue(book);

        Toast.makeText(this, "Successfully added", Toast.LENGTH_SHORT).show();
    }

    public void onViewData(View view){
        DatabaseReference childReference;
        for(int i=1;i<=maxBookID;i++){
            childReference=FirebaseDatabase.getInstance().getReference().child("Book").child(i+"");
            childReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String isbnNo=dataSnapshot.child("isbnno").getValue().toString();
                    String name=dataSnapshot.child("name").getValue().toString();
                    String author=dataSnapshot.child("author").getValue().toString();
                    String publisher=dataSnapshot.child("publisher").getValue().toString();
                    System.out.println("ISBN No :"+isbnNo+"\t"+"Name :"+name+"\t"+"Author :"
                            +author+"\t"+"Publisher :"+publisher+"\t");
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
}
