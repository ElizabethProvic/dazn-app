package com.dazn.playerapp.events.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dazn.playerapp.R
import com.dazn.playerapp.databinding.FragmentEventsBinding
import com.dazn.playerapp.model.Event
import com.dazn.playerapp.player.ui.PlayerFragment
import com.dazn.playerapp.player.ui.PlayerFragment.Companion.ARG_VIDEO_ID
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class EventsFragment : DaggerFragment(), EventListAdapter.OnItemClickListener, EventsContract.View {

    @Inject
    lateinit var presenter: EventsContract.Presenter

    private val eventsAdapter = EventListAdapter(arrayListOf(), null)
    private var _binding: FragmentEventsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventsBinding.inflate(inflater, container, false)

        presenter.getData(this)
        val root: View = binding.root

        binding.eventsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = eventsAdapter
        }

        return root
    }

    override fun onDestroyView() {
        _binding = null
        presenter.clearData()
        super.onDestroyView()
    }

    override fun onContentItemClick(id: String) {
        PlayerFragment.newInstance(id)

        val bundle = bundleOf(
            ARG_VIDEO_ID to id
        )

        NavHostFragment.findNavController(this)
            .navigate(R.id.action_global_navigation_player, bundle)
    }

    override fun setItems(items: List<Event>) {
        eventsAdapter.updateEvents(ArrayList(items))
        binding.eventsList.adapter = EventListAdapter(ArrayList(items), this)
    }

    override fun hideLoadingView() {
        binding.loader.isVisible = false
    }

    override fun showErrorMessage() {
        binding.errorMessage.isVisible = true
    }
}