package com.pam.tugas3.ui.skill

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pam.tugas3.R
import java.util.*

class SkillFragment : Fragment() {

    private lateinit var adapter: Adaptor
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var skillArrayList = ArrayList<Skill>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_skill, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recycler_view_skill)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = Adaptor(skillArrayList)
        recyclerView.adapter = adapter
        searchView = view.findViewById(R.id.search_action)

        adapter.onItemClick = {
            navigateToDetail(it.heading)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })

        getUserData()
    }

    private fun navigateToDetail(extraName: String) {
        findNavController().navigate(SkillFragmentDirections.actionSkillToDetail(extraName))
    }

    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<Skill>()
            for (i in skillArrayList) {
                if (i.heading.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT))) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(context, "No Data Found", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }

    private fun getUserData() {
        val skillArray = resources.getStringArray(R.array.array_skill)

        val imageArray = resources.obtainTypedArray(R.array.array_image)

        for (i in skillArray.indices) {
            val skill = Skill(imageArray.getResourceId(i, 0), skillArray[i])
            skillArrayList.add(skill)
        }
        imageArray.recycle()
    }
}
