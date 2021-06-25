package br.com.fiap.a3sia_firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val database: FirebaseDatabase = FirebaseDatabase.getInstance("https://sia-firebase.firebaseio.com/")
    val userTable = database.getReference("Users")

    val  adapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCadastrar.setOnClickListener{
            cadastrarUser()
        }
        setupUserListener()
        setupRecyclerView()
    }

    private  fun setupRecyclerView(){
        recyclerView.adapter = adapter
    }

    private fun setupUserListener(){
        val valueListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val users = snapshot.children.mapNotNull{
                    val user: User? = it.getValue(User::class.java)
                    user
                }

               adapter.updateItems(users)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity,"Erro: ${error.message}",Toast.LENGTH_SHORT)
            }
        }

        userTable.addValueEventListener(valueListener)
    }

    private fun cadastrarUser(){
        val userId = UUID.randomUUID().toString()
        val user = User(
            uuid = userId,
            name = editTextTextPersonName.text.toString(),
            phone = editTextTextPersonName2.text.toString()
        )
        userTable.child(userId).setValue(user)
    }

}