package com.dazn.playerapp.events.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dazn.playerapp.R
import com.dazn.playerapp.databinding.FragmentEventsBinding
import com.dazn.playerapp.extenstions.observe
import com.dazn.playerapp.model.Event
import com.dazn.playerapp.player.ui.PlayerFragment
import com.dazn.playerapp.player.ui.PlayerFragment.Companion.ARG_VIDEO_ID
import com.dazn.playerapp.events.presentation.EventsViewModel

class EventsFragment : Fragment(), EventListAdapter.OnItemClickListener {

    lateinit var viewModel: EventsViewModel
    private val eventsAdapter = EventListAdapter(arrayListOf(), null)
    private var _binding: FragmentEventsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this).get(EventsViewModel::class.java)

        _binding = FragmentEventsBinding.inflate(inflater, container, false)

        val root: View = binding.root

        viewModel.refresh()

        binding.eventsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = eventsAdapter
        }

        viewModel.refresh()

        with(viewModel) {
            this.state.observe(viewLifecycleOwner, true, ::onNext, ::onError, ::onLoading)
        }

        return root
    }

    private fun onLoading(isLoading: Boolean) {
        binding.loader.isVisible = isLoading
    }

    private fun onNext(data: List<Event>) {
        eventsAdapter.updateEvents(ArrayList(data))
        binding.eventsList.adapter = EventListAdapter(ArrayList(data), this)
    }

    private fun onError(throwable: Throwable) {
        binding.errorMessage.isVisible = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onContentItemClick(id: String) {
        PlayerFragment.newInstance(id)

        val bundle = bundleOf(
            ARG_VIDEO_ID to id
        )

        NavHostFragment.findNavController(this)
            .navigate(R.id.action_global_navigation_player, bundle)
    }
}