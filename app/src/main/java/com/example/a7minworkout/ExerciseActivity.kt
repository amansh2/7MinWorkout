package com.example.a7minworkout

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Toast
import com.example.a7minworkout.databinding.ActivityExerciseBinding
import com.example.a7minworkout.databinding.CustomDialogBinding
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var binding:ActivityExerciseBinding?=null
    private var restTimer:CountDownTimer?=null
    private var restProgress=0
    private var exerciseList=ArrayList<ExerciseModel>()
    private var idx=-1
    private var tts:TextToSpeech?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityExerciseBinding.inflate(layoutInflater)
        tts= TextToSpeech(this,this,)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding?.toolbar?.setNavigationOnClickListener {
            customDialogForBackButton()
        }
        exerciseList=Constants.defaultExerciseList()
        setUpRestView()
    }

    private fun customDialogForBackButton() {
        val dialog=Dialog(this)
        val dialogBinding=CustomDialogBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialogBinding.tvYes.setOnClickListener {
            finish()
        }
        dialogBinding.tvNo.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun setUpRestView(){
        if(restTimer!=null){
            restTimer!!.cancel()
            restProgress=0
        }
        setUpTimer()
    }

    private fun setUpTimer(){
        idx++
        binding?.progressBar?.max=11
        binding?.progressBar?.progress=restProgress
        restTimer= object : CountDownTimer(11000,1000){
            override fun onTick(p0: Long) {
                restProgress++
                binding?.progressBar?.progress=11-restProgress
                binding?.textview?.text=(11-restProgress).toString()
            }

            override fun onFinish() {
//                Toast.makeText(this@ExerciseActivity,"Start Exercise",Toast.LENGTH_SHORT).show()
                speakOut(exerciseList[idx].name)
                binding?.tvTitle?.text= exerciseList[idx].name
                binding?.imageView?.setImageResource(exerciseList[idx].image)
                setUpRestView2()
            }

        }.start()
    }

    private fun setUpRestView2() {
        if(restTimer!=null){
            restTimer!!.cancel()
            restProgress=0
        }
        setUpTimer2()
    }

    private fun setUpTimer2() {
        binding?.progressBar?.max=31
        binding?.progressBar?.progress=restProgress
        restTimer= object : CountDownTimer(31000,1000){
            override fun onTick(p0: Long) {
                restProgress++
                binding?.progressBar?.progress=31-restProgress
                binding?.textview?.text=(31-restProgress).toString()
            }

            override fun onFinish() {
               // Toast.makeText(this@ExerciseActivity,"Exercise Complete",Toast.LENGTH_SHORT).show()
                if(idx<exerciseList.size-1){
                    speakOut("get ready for ${exerciseList[idx+1].name}")
                    binding?.tvTitle?.text="Get Ready For "+exerciseList[idx+1].name
                    binding?.imageView?.setImageResource(exerciseList[idx+1].image)
                    setUpRestView()
                }else{
                    finish()
                    startActivity(Intent(this@ExerciseActivity,FinishActivity::class.java))
                }
            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(restTimer!=null){
            restTimer!!.cancel()
            restProgress=0
        }
        if(tts!=null){
            tts!!.stop()
            tts!!.shutdown()
        }
        binding=null

    }
    private fun speakOut(text:String){
        tts?.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }

    override fun onInit(status: Int) {
        if(status==TextToSpeech.SUCCESS){
            val result=tts?.setLanguage(Locale.ENGLISH)
            if(result==TextToSpeech.LANG_MISSING_DATA || result==TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","The Language Specified Is Not Supported")
            }else speakOut("get ready for jumping jacks")
        }else{
            Log.e("TTS","Initialization Failed")
        }
    }

    override fun onBackPressed() {
        customDialogForBackButton()
    }
}