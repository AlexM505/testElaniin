package com.alxd.testelaniin.ui.teams

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.alxd.testelaniin.R
import com.alxd.testelaniin.databinding.FragmentAddTeamsBinding
import com.alxd.testelaniin.model.generation.PokemonSpecy
import com.alxd.testelaniin.model.region.Results
import com.alxd.testelaniin.model.team.Team
import com.alxd.testelaniin.ui.regions.RegionsFragmentDirections
import com.alxd.testelaniin.utils.LocalStorage
import com.alxd.testelaniin.viewmodel.GenerationViewModel
import com.alxd.testelaniin.viewmodel.RegionsViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.dialog_pokemon.*
import kotlinx.android.synthetic.main.fragment_add_teams.*


class AddTeamsFragment : Fragment() {

    private var _binding: FragmentAddTeamsBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<AddTeamsFragmentArgs>()

    private val regionsViewModel: RegionsViewModel by viewModels()
    private val generationViewModel: GenerationViewModel by viewModels()

    private lateinit var listNamePokemon : List<PokemonSpecy>
    private val database = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddTeamsBinding.inflate(inflater,container, false)
        binding.argsRegion = args

        LocalStorage.deletePokemonList()
        fetchInfoGeneration()
        clicksChoicePokemon()

        binding.btnSaveTeam.setOnClickListener {
            saveTeam()
        }

        return binding.root
    }

    private fun fetchInfoGeneration(){
        var id:String = ""
        id = args.currentRegion.url.substring(33)
        regionsViewModel.getInfoRegionById(id)

        //mandar a traer los nombres de los pokemon
        regionsViewModel.mainGeneration.observe(viewLifecycleOwner,{
            val id = it.url.substring(37)
            generationViewModel.getGenerationById(id)
        })

        generationViewModel.pokemonSpecy.observe(viewLifecycleOwner, {
            listNamePokemon = it
            binding.cvAddPokemon1.isEnabled = true
            binding.cvAddPokemon2.isEnabled = true
            binding.cvAddPokemon3.isEnabled = true
            binding.cvAddPokemon4.isEnabled = true
            binding.cvAddPokemon5.isEnabled = true
            binding.cvAddPokemon6.isEnabled = true
        })
    }

    private fun clicksChoicePokemon(){
        //TODO: refactor por recyclerview
        binding.cvAddPokemon1.isEnabled = false
        binding.cvAddPokemon2.isEnabled = false
        binding.cvAddPokemon3.isEnabled = false
        binding.cvAddPokemon4.isEnabled = false
        binding.cvAddPokemon5.isEnabled = false
        binding.cvAddPokemon6.isEnabled = false
        binding.cvAddPokemon1.setOnClickListener {
            showListOfPokemonDialog(1)
        }
        binding.cvAddPokemon2.setOnClickListener {
            showListOfPokemonDialog(2)
        }
        binding.cvAddPokemon3.setOnClickListener {
            showListOfPokemonDialog(3)
        }
        binding.cvAddPokemon4.setOnClickListener {
            showListOfPokemonDialog(4)
        }
        binding.cvAddPokemon5.setOnClickListener {
            showListOfPokemonDialog(5)
        }
        binding.cvAddPokemon6.setOnClickListener {
            showListOfPokemonDialog(6)
        }
    }

    private fun showListOfPokemonDialog(pos:Int) {
        val pokemonAdapter = PokemonAdapter()
        val dialog = Dialog(requireActivity())
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_pokemon)

        pokemonAdapter.setPokemonList(pos, listNamePokemon, dialog, binding)
        pokemonAdapter.notifyDataSetChanged()

        dialog.rvPokemon.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        //LinearLayoutManager(requireContext(),RecyclerView.VERTICAL, false)
        dialog.rvPokemon.adapter = pokemonAdapter

        dialog.btnCerrarDialog.setOnClickListener { dialog.dismiss() }

        dialog.show()
    }

    private fun saveTeam(){
        showProogress()
        val teamName = binding.etTeamName.text.toString()
        val teamRegion = args.currentRegion.name
        val listPokemon = LocalStorage.getPokemonList()

        val team = Team(teamName, teamRegion, listPokemon)

        database.collection("teams").add(team)
            .addOnSuccessListener {
                hideProgress()
                Toast.makeText(requireContext(),"Se ha guardado su equipo",Toast.LENGTH_SHORT).show()
                val action = AddTeamsFragmentDirections.actionAddTeamsFragmentToListTeamsFragment(Results(teamRegion,""))
                findNavController().navigate(action)
            }.addOnFailureListener {
                hideProgress()
                Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
            }

    }

    private fun showProogress(){
        llPd.visibility = View.VISIBLE
    }

    private fun hideProgress(){
        llPd.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}