package com.example.androidtrivia

import android.icu.text.CaseMap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.androidtrivia.databinding.FragmentTitleBinding

class TitleFragment : Fragment(), MenuProvider {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding =
            DataBindingUtil.inflate<FragmentTitleBinding>(
                inflater, R.layout.fragment_title, container,
                false
            )

        binding.playButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(TitleFragmentDirections.actionTitleFragmentToGameFragment())
        )

        activity?.addMenuProvider(this, viewLifecycleOwner)




        return binding.root
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            menuItem,
            requireView().findNavController()
        ) || super.onContextItemSelected(menuItem)
    }


}