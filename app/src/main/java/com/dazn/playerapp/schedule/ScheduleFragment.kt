package com.dazn.playerapp.schedule.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dazn.playerapp.databinding.FragmentScheduleBinding
import com.dazn.playerapp.model.ScheduleItem
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ScheduleFragment : DaggerFragment(), ScheduleContract.View {

    @Inject
    lateinit var presenter: ScheduleContract.Presenter

    private var adapter: ScheduleListAdapter? = null
    private var _binding: FragmentScheduleBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)

        presenter.getData(this)
        val root: View = binding.root

        setupSchedule()

        return root
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

    override fun setItems(items: List<ScheduleItem>) {
        adapter?.updateSchedule(ArrayList(items))
        binding.scheduleList.adapter = ScheduleListAdapter(ArrayList(items))
    }

    override fun hideLoadingView() {
        binding.loader.isVisible = false
    }

    override fun showErrorMessage() {
        binding.errorMessage.isVisible = true
    }
}