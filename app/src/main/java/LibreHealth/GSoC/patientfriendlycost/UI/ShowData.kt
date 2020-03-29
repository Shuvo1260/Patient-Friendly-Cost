package LibreHealth.GSoC.patientfriendlycost.UI

import LibreHealth.GSoC.patientfriendlycost.Adapter.MedicalCostListAdapter
import LibreHealth.GSoC.patientfriendlycost.R
import LibreHealth.GSoC.patientfriendlycost.Utils.Utils
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.nio.charset.Charset

class ShowData : AppCompatActivity(){

    lateinit var recyclerView: RecyclerView
    lateinit var toolbar: Toolbar
    lateinit var searchView: SearchView
    lateinit var medicalCostListAdapter: MedicalCostListAdapter

    var utils= arrayListOf<Utils>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_data)

        toolbar = findViewById(R.id.costListToolbar)
        var hospital = intent.getStringExtra("hospital")
        toolbar.title = hospital

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fetchData()

        recyclerView = findViewById(R.id.recyclerView)

        medicalCostListAdapter = MedicalCostListAdapter(this, utils)
        recyclerView.adapter = medicalCostListAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun fetchData() {
        try {
            val filename = resources.openRawResource(R.raw.atlanticare_regional_medical_center)
            var reader = BufferedReader(InputStreamReader(filename, Charset.forName("UTF-8")))
            var line = ""
            while ((reader.readLine()) != null) {
                line = reader.readLine()
                var tokens = line.split(",")

                val util = Utils(
                    tokens[0],
                    "$${tokens[1]}"
                )
                utils.add(util)

                Log.d("Show Data", "Data ${utils[0].testName}")
//                Log.d("Show Data", "Data $tokens")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("Show Data", "Data Error ${e.message}")
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.show_data_menu, menu)
        searchView = menu?.findItem(R.id.searchBarId)?.actionView as SearchView
        setSearchView()
        return super.onCreateOptionsMenu(menu)
    }

    private fun setSearchView() {
        var editText: EditText
        editText = searchView.findViewById(androidx.appcompat.R.id.search_src_text)
        editText.setTextColor(Color.WHITE)
        editText.setHintTextColor(Color.GRAY)
        editText.hint = "Write a test name"

        searchView.imeOptions = EditorInfo.IME_ACTION_DONE

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                var text = editText.text.toString()

                medicalCostListAdapter.getFilter().filter(text)
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
