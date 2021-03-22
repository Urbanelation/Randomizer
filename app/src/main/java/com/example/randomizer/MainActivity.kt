package com.example.randomizer

import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //connects view and activity

        val rollButton = findViewById<Button>(R.id.rollButton)
        val resultsTextView = findViewById<TextView>(R.id.resultsTextView)
        val seekBar = findViewById<SeekBar>(R.id.seekBar)



       rollButton.setOnClickListener {
            val rand = Random.nextInt(seekBar.progress) + 1
            resultsTextView.text = rand.toString()





/*            //Create a new file that points to the root directory, with the given name:

            //Create a new file that points to the root directory, with the given name:
            val file = File(getExternalFilesDir(null), filenameExternal)

            //This point and below is responsible for the write operation

            //This point and below is responsible for the write operation
            var outputStream: FileOutputStream? = null
            try {
                file.createNewFile()
                //second argument of FileOutputStream constructor indicates whether
                //to append or create new file if one exists
                outputStream = FileOutputStream(file, true)
                outputStream.write(textToWrite.getBytes())
                outputStream.flush()
                outputStream.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }


*/
        }
    }

}