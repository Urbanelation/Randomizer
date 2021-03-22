package com.example.randomizer

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val rolledNumbersList = mutableListOf<String>()
    private var randNumber: String = ""
    private var saveSlotName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //connects view and activity

        val dbHandler = DBHelper(this, null)

        val rollButton = findViewById<Button>(R.id.rollButton)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val shareButton = findViewById<Button>(R.id.shareButton)

        val resultsTextView = findViewById<TextView>(R.id.resultsTextView)
        val seekBar = findViewById<SeekBar>(R.id.seekBar)

       rollButton.setOnClickListener {
            val rand = Random.nextInt(seekBar.progress + 1) + 1
            randNumber = rand.toString()
            resultsTextView.text = randNumber
            rolledNumbersList.add(randNumber)

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

        saveButton.setOnClickListener{
            //val intent = Intent(this, SaveSlotNameInput::class.java)
            //startActivity(intent)

            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            builder.setTitle("With EditText")
            val dialogLayout = inflater.inflate(R.layout.save_slot_name_input, null)
            val editText  = dialogLayout.findViewById<EditText>(R.id.textEdit_save_slot_name)
            builder.setView(dialogLayout)
            builder.setPositiveButton("OK") { dialogInterface: DialogInterface, i: Int ->
                saveSlotName = editText.toString()
                dbHandler.insertRow(slotName = saveSlotName, data = rolledNumbersList.toString())
            }
            builder.show()
        }

        shareButton.setOnClickListener{
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "I just randomized a $randNumber !")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }



    }


}