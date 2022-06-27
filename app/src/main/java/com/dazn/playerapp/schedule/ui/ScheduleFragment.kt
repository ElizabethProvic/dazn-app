package com.dazn.playerapp.schedule.ui

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dazn.playerapp.databinding.FragmentScheduleBinding
import com.dazn.playerapp.events.ui.EventListAdapter
import com.dazn.playerapp.extenstions.observe
import com.dazn.playerapp.model.Event
import com.dazn.playerapp.schedule.presentation.ScheduleViewModel

class ScheduleFragment : Fragment() {

    lateinit var viewModel: ScheduleViewModel

    private var _binding: FragmentScheduleBinding? = null

    private var adapter: EventListAdapter? = null

    private val binding get() = _binding!!

    private val timerHandler = Handler()

    private val updateDataRunnable: Runnable = object : Runnable {
        override fun run() {

            viewModel.refresh()

            timerHandler.postDelayed(this, EVERY_30_SECONDS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this).get(ScheduleViewModel::class.java)

        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel.start()

        setupSchedule()

        with(viewModel) {
            this.state.observe(viewLifecycleOwner, true, ::onNext, ::onError, ::onLoading)
        }

        return root
    }

    override fun onResume() {
        super.onResume()
        timerHandler.postDelayed(updateDataRunnable, EVERY_30_SECONDS)
    }

    private fun onLoading(isLoading: Boolean) {
        binding.loader.isVisible = isLoading
    }

    private fun onNext(data: List<Event>) {
        binding.scheduleList.adapter = EventListAdapter(ArrayList(data), null)
    }

    private fun onError(throwable: Throwable) {
        binding.errorMessage.isVisible = true
    }

    private fun setupSchedule() {
        binding.scheduleList.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.scheduleList.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val EVERY_30_SECONDS: Long = 30000
    }
}