package LibreHealth.GSoC.patientfriendlycost.Adapter

import LibreHealth.GSoC.patientfriendlycost.R
import LibreHealth.GSoC.patientfriendlycost.Utils.Utils
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class MedicalCostListAdapter(var context: Context, var utils: ArrayList<Utils>): RecyclerView.Adapter<MedicalCostListAdapter.ViewHolder>(), Filterable {
    var utilsAll: ArrayList<Utils> = arrayListOf()
    init {
        utilsAll.addAll(utils)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var cardView: CardView =
            LayoutInflater.from(parent.context).inflate(R.layout.cost_view, parent, false) as CardView
        return ViewHolder(cardView)
    }

    override fun getItemCount(): Int {
        return utils.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var cardView = holder.itemView
        var nameTextView: TextView = cardView.findViewById(R.id.testNameId)
        var costTextView: TextView = cardView.findViewById(R.id.testCostId)

        nameTextView.text = utils[position].testName
        costTextView.text = utils[position].testCost
    }
    class ViewHolder(cardView: CardView) : RecyclerView.ViewHolder(cardView) {

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
//                utilsAll.forEach { Log.d("message", "Data all: ${it.testName}") }
                var filteredList: ArrayList<Utils> = arrayListOf()
                if (constraint.toString().isEmpty() || constraint.toString() == "") {
                    Log.d("message", "Data empty")
                utilsAll.forEach { Log.d("message", "Data all: ${it.testName}") }
                    filteredList.addAll(utilsAll)
                } else {
                    utilsAll.forEach {
                        if(it.testName.toLowerCase().contains(constraint.toString().toLowerCase())) {
                            filteredList.add(it)
                        }
                    }
                }

                var filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                utils.clear()
                utils.addAll(results?.values as Collection<Utils>)
                notifyDataSetChanged()
            }
        }
    }


}