package com.example.kotlinbookappLocal.fragment

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.kotlinbookappLocal.R
import com.example.kotlinbookappLocal.adapter.RecyclerDashboardAdapter
import com.example.kotlinbookappLocal.model.Book
import com.example.kotlinbookappLocal.util.ConnectionManager

class DashboardFragment : Fragment() {

    // create a reference to the recyclerview
    lateinit var recyclerview : RecyclerView
   lateinit var recyclerDashboardAdapter : RecyclerDashboardAdapter
   lateinit var btn_check_internet : Button

    // create a reference to the LayoutManager
    lateinit var layoutManager : RecyclerView.LayoutManager

  //  val bookList = arrayListOf<Book>()

    // lateinit var bookList : ArrayList<Book>
    // create a list of books
    var bookList  = arrayListOf<Book>(
            Book("Things fall apart","Chinue Achebe","$2300","4.5",R.drawable.things_fall_apart),
            Book("The Cashflow Quadrant","Robert Kiyosaki","$5000","4.5",R.drawable.cash_flow_quandrant),
            Book("The richest man in babylon","George S Clason","$3400","3.2",R.drawable.richest_man_in_babylon),
            Book("The ultimate Question","Fred Reicheld","$8300","4.9",R.drawable.the_ultimate_question),
            Book("The Seven Habits","Steven Covey","$6700","4.9",R.drawable.seven_habits),
            Book("48 laws of power","Robert Greene","$2300","4.3",R.drawable.forty_eight_laws),
            Book("Built to last","Jim Collins","$2300","4.9",R.drawable.built_to_last),
            Book("Good to Great","Jim Collins","$2300","4.3",R.drawable.good_to_great),
            Book("Rich Dad Poor Dad","Robert Kiyosaki","$4300","4.8",R.drawable.rich_dad),
            Book("The Hunger Games","Suzanne Collins","$5400","4.6",R.drawable.the_hunger_games),
            Book("The power of a magnetic personality","Robert Conklin","$8600","5.0",R.drawable.the_power_of_a_magnetic_personality),
            Book("The 8th Habit","Stephen Covey","$2300","4.8",R.drawable.eight_habit),
            Book("The millionaire next door","Thomass Stanley","$6700","4.6",R.drawable.millionaire_nd),
            Book("Fuck being humble","Stefanie Sword","$3200","4.6",R.drawable.fuckbeinghumble),
            Book("Attitude is everything","Jeff Keller","$4500","4.7",R.drawable.attitude),
    )



    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)


        // initialise the recyclerview
        recyclerview = view.findViewById(R.id.recyclerView)
        // initialise the layout manager
        layoutManager = LinearLayoutManager(activity)


        // instantiate the dashboard adapter
        recyclerDashboardAdapter = RecyclerDashboardAdapter(activity as Context, bookList)
        // set the adapter and the linear layout to the recycler view object
        recyclerview.adapter = recyclerDashboardAdapter
        recyclerview.layoutManager = layoutManager

        // add divider line
        recyclerview.addItemDecoration(
                DividerItemDecoration(recyclerview.context, (layoutManager as LinearLayoutManager).orientation)
        )




        // instantiate the button
        btn_check_internet = view.findViewById(R.id.btn_checkInternet)

        // create the click event on the button

        btn_check_internet.setOnClickListener {
            if (ConnectionManager().checkConnectivity(activity as Context)) {
                // Internet is available
                val dialog = AlertDialog.Builder(activity as Context)
                dialog.setTitle("Internet Connection")
                dialog.setMessage("Internet Connection Found")
                dialog.setPositiveButton("Ok") { text, listener ->

                }
                dialog.setNegativeButton("Cancel") { text, listener ->

                }
                dialog.create()
                dialog.show()

            } else {
                // Internet is not available
                val dialog = AlertDialog.Builder(activity as Context)
                dialog.setTitle("Error")
                dialog.setMessage("Internet Connection is not  Found")
                dialog.setPositiveButton("Ok") { text, listener ->

                }
                dialog.setNegativeButton("Cancel") { text, listener ->

                }
                dialog.create()
                dialog.show()
            }
        }


        return view
    }


}