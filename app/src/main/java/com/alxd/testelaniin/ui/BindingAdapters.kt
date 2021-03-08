package com.alxd.testelaniin.ui

import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.alxd.testelaniin.model.region.Results
import com.alxd.testelaniin.ui.regions.RegionsFragmentDirections
import com.alxd.testelaniin.ui.teams.ListTeamsFragmentDirections
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BindingAdapters {

   companion object{

       @BindingAdapter("android:navigateAndSendRegionToTeam")
       @JvmStatic
       fun navigateAndSendRegionToTeam(view: CardView, currentRegion: Results){
           view.setOnClickListener {
               val action = RegionsFragmentDirections.actionRegionsFragmentToListTeamsFragment(currentRegion)
               view.findNavController().navigate(action)
           }
       }

       @BindingAdapter("android:navigateToAddTeam")
       @JvmStatic
       fun navigateToAddTeam(view: FloatingActionButton, currentRegion: Results){
           view.setOnClickListener {
               val action = ListTeamsFragmentDirections.actionListTeamsFragmentToAddTeamsFragment(currentRegion)
               view.findNavController().navigate(action)
           }
       }

   }

}