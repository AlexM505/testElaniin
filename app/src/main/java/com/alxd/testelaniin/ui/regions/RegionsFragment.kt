package com.alxd.testelaniin.ui.regions

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.alxd.testelaniin.databinding.FragmentRegionsBinding
import com.alxd.testelaniin.ui.auth.SignInActivity
import com.alxd.testelaniin.viewmodel.RegionsViewModel
import com.google.firebase.auth.FirebaseAuth
import jp.wasabeef.recyclerview.animators.SlideInDownAnimator
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import kotlinx.android.synthetic.main.fragment_regions.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class RegionsFragment : Fragment() {

    private var _binding: FragmentRegionsBinding? = null
    private val binding get() = _binding!!

    private lateinit var mAuth: FirebaseAuth
    private val regionsViewModel: RegionsViewModel by viewModels()
    private val regionAdapter: RegionAdapter by lazy { RegionAdapter() }

    // Corrutina
    private val scope = CoroutineScope(Job() + Dispatchers.Default)
    private var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegionsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        job?.cancel()

        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser

        binding.tvUsername.text = currentUser?.displayName

        regionsViewModel.showProgress.observe(viewLifecycleOwner, {
            if(it)
                progressRegions.visibility = VISIBLE
            else
                progressRegions.visibility = GONE
        })

        adapterRecyclerView()

        job = scope.launch {
            regionsViewModel.getRegions()
        }

        regionsViewModel.regionList.observe(viewLifecycleOwner, {
            regionAdapter.setRegionData(it)
        })

        binding.btnSignOut.setOnClickListener {
            //Cerrar sesion
            signOut()
        }

        return binding.root
    }

    private fun signOut(){
        mAuth.signOut()
        val intent = Intent(requireContext(), SignInActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    private fun adapterRecyclerView(){
        val rvRegions = binding.rvRegions
        rvRegions.adapter = regionAdapter
        rvRegions.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rvRegions.itemAnimator = SlideInUpAnimator().apply {
            addDuration = 350
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}