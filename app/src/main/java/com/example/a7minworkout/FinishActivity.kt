package com.example.a7minworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.a7minworkout.Data.HistoryDB
import com.example.a7minworkout.Data.HistoryDao
import com.example.a7minworkout.Data.HistoryEntity
import com.example.a7minworkout.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {
    private var binding:ActivityFinishBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val dao=HistoryDB.getInstance(application).historyDao()
        binding?.toolbar?.setNavigationOnClickListener {
            insertData(dao)
            onBackPressed()
        }
        binding?.btnFinish?.setOnClickListener {
            insertData(dao)
            finish()
        }
    }

    private fun insertData(dao: HistoryDao){
        val dateTime=Calendar.getInstance().time
        val sdf=SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
        val date=sdf.format(dateTime)
        lifecycleScope.launch {
            dao.insert(HistoryEntity(date))
        }
    }
}