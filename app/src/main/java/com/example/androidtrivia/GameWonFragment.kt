package com.example.androidtrivia

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.navigation.Navigation
import androidx.navigation.navArgument
import com.example.androidtrivia.databinding.FragmentGameWonBinding

class GameWonFragment : Fragment(),MenuProvider {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentGameWonBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_game_won, container, false)

        binding.nextMatchButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(GameWonFragmentDirections.actionGameWonFragmentToGameFragment())
        )

//        val args = arguments?.let { GameWonFragmentArgs.fromBundle(it) }
//        Toast.makeText(context,
//            "NumCorrect:${args?.numCorrect},NumQuestions:${args?.numQuestions}",
//            Toast.LENGTH_SHORT).show()

//        Toast.makeText(context,
//            "NumCorrect:${args.numCorrect},NumQuestions:${args.numQuestions}",
//            Toast.LENGTH_SHORT).show()

        activity?.addMenuProvider(this,viewLifecycleOwner)


        return binding.root
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.winner_menu,menu)
        // check if the activity resolves
        if (null == getShareIntent().resolveActivity(requireActivity().packageManager)) {
            // hide the menu item if it doesn't resolve
            menu.findItem(R.id.share)?.isVisible = false
        }
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId){
            R.id.share -> shareSuccess()
        }
        return super.onContextItemSelected(menuItem)
    }

    private fun getShareIntent():Intent{
        val args = GameWonFragmentArgs.fromBundle(requireArguments())
//        return ShareCompat.IntentBuilder(requireView().context)
//            .setText(getString(R.string.share_success_text,args.numCorrect,args.numQuestions))
//            .setType("text/plain")
//            .intent
        return ShareCompat.IntentBuilder(requireView().context)
            .setChooserTitle("Share the text with")
            .setText(getString(R.string.share_success_text,args.numCorrect,args.numQuestions))
            .setType("text/plain")
            .intent
    }

    private fun shareSuccess(){
        startActivity(getShareIntent())
    }
}