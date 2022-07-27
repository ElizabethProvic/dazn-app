package com.dazn.playerapp.schedule.ui

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dazn.playerapp.databinding.FragmentScheduleBinding
import com.dazn.playerapp.events.ui.EventListAdapter
import com.dazn.playerapp.model.Event

class ScheduleFragment : Fragment(), ScheduleContract.View {

    private lateinit var presenter: SchedulePresenter

    private var _binding: FragmentScheduleBinding? = null

    private var adapter: EventListAdapter? = null

    private val binding get() = _binding!!

    private val timerHandler = Handler()
    /**
     * Let's get rid of Handler and implement this inside presenter using nothing but RxJava ;)
     * */

    private val updateDataRunnable: Runnable = object : Runnable {
        override fun run() {
            presenter.getData()
            timerHandler.postDelayed(this, EVERY_30_SECONDS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        presenter = SchedulePresenter(this)

        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        val root: View = binding.root
        presenter.getData()

        setupSchedule()

        return root
    }

    override fun onResume() {
        super.onResume()
        timerHandler.postDelayed(updateDataRunnable, EVERY_30_SECONDS)
    }

    private fun setupSchedule() {
        binding.scheduleList.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.scheduleList.adapter = adapter
    }

    override fun onDestroyView() {
        _binding = null
        presenter.clearData()
        super.onDestroyView()
    }

    override fun setItems(items: List<Event>) {
        binding.scheduleList.adapter = EventListAdapter(ArrayList(items), null)
    }

    override fun hideLoadingView() {
        binding.loader.isVisible = false
    }

    override fun showErrorMessage() {
        binding.errorMessage.isVisible = true
    }

    companion object {
        private const val EVERY_30_SECONDS: Long = 30000
    }
}