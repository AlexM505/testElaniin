package com.alxd.testelaniin.ui.teams

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.alxd.testelaniin.databinding.FragmentListTeamsBinding
import com.alxd.testelaniin.viewmodel.TeamsViewModel
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator

class ListTeamsFragment : Fragment() {

    private var _binding: FragmentListTeamsBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<ListTeamsFragmentArgs>()

    private val teamsViewModel: TeamsViewModel by viewModels()
    private lateinit var adapter: TeamAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListTeamsBinding.inflate(inflater,container, false)
        binding.argsRegion = args

        adapterRecyclerView()

        observeListTeams()

        return binding.root
    }

    private fun adapterRecyclerView(){
        adapter = TeamAdapter(requireContext())
        binding.rvTeams.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTeams.adapter = adapter
        binding.rvTeams.itemAnimator = SlideInUpAnimator().apply {
            addDuration = 350
        }
    }

    private fun observeListTeams(){
        teamsViewModel.fetchTeamsData().observe(viewLifecycleOwner,{
            binding.pdNoDataTeams.visibility = View.GONE
            if(it.size > 0){
                adapter.setListData(it)
                adapter.notifyDataSetChanged()
            }else{
                binding.ivNoDataTeams.visibility = View.VISIBLE
                binding.tvNoDataTeams.visibility = View.VISIBLE
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}