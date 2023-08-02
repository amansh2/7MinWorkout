package com.example.a7minworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minworkout.Data.HistoryDB
import com.example.a7minworkout.Data.HistoryDao
import com.example.a7minworkout.Data.HistoryEntity
import com.example.a7minworkout.databinding.ActivityHistoryBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {
        private var binding: ActivityHistoryBinding? = null
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityHistoryBinding.inflate(layoutInflater)
            setContentView(binding?.root)
            setSupportActionBar(binding?.toolbarHistoryActivity)
            val dao=HistoryDB.getInstance(application).historyDao()
            val actionbar = supportActionBar//actionbar
            if (actionbar != null) {
                actionbar.setDisplayHomeAsUpEnabled(true) //set back button
                actionbar.title = "HISTORY" // Setting a title in the action bar.
            }

            binding?.toolbarHistoryActivity?.setNavigationOnClickListener {
                onBackPressed()
            }
            lifecycleScope.launch {
                dao.getList().collect {
                    val list=ArrayList(it)
                    setRecyclerView(list)
                }
            }

    }

    private fun setRecyclerView(it: ArrayList<HistoryEntity>) {
        val adapter=HistoryAdapter(it)
        binding?.recyclerView?.adapter=adapter
        binding?.recyclerView?.layoutManager=LinearLayoutManager(this@HistoryActivity)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}
