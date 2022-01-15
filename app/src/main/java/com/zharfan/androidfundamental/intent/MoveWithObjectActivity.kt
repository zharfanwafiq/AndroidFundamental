package com.zharfan.androidfundamental.intent


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.zharfan.androidfundamental.data.Person
import com.zharfan.androidfundamental.databinding.ActivityMoveWithObjectBinding

class MoveWithObjectActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoveWithObjectBinding
    private lateinit var person: Person
    private lateinit var titleText : String


    companion object{
        const val EXTRA_PERSON = "package com.zharfan.androidfundamental.intent.EXTRA_PERSON"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoveWithObjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBundle()
        showActionBar()
        setData()
    }



    private fun setBundle() {
        person = intent.getParcelableExtra<Person>(EXTRA_PERSON) as Person
    }

    private fun showActionBar(){
        supportActionBar?.apply {
            titleText = person.title.toString()
            title = titleText
            setDisplayHomeAsUpEnabled(true)
        }
    }
    private fun setData() {
        binding.apply {

            val text = "Nama ${person.name}\n"+
                    "umur ${person.age}\n" +
                    "email ${person.email}\n" +
                    "Asal kota ${person.city}"
            tvActivityIntentWithObject.text = text

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}