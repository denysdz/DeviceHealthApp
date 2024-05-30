package com.test.app.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.test.app.R
import com.test.app.databinding.FragmentMainBinding
import com.test.app.view.adapter.BottomOffsetDecoration
import com.test.app.view.adapter.SectionAdapter
import com.test.app.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _viewBinder:FragmentMainBinding? = null
    private val viewBinding get() = _viewBinder!!

    private val viewModel:MainViewModel by viewModels()

    private var sectionAdapter:SectionAdapter? = null
    private var layoutManager:LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinder = FragmentMainBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        getData()
        clickListener()
    }

    private fun clickListener () = with(viewBinding) {
        outLine.setOnClickListener {
            setScan()
        }
    }

    private fun setScan () = with(viewBinding) {
        outLine.isEnabled = false
        animationView.animate().alpha(1f)
        animationView.playAnimation()
        viewModel.scan(animationView.duration)
    }

    private fun initList () = with(viewBinding) {
        val bottomOffsetPx = resources.getDimensionPixelOffset(R.dimen.item_section_offset)
        layoutManager = LinearLayoutManager(requireContext())
        layoutManager?.orientation = LinearLayoutManager.VERTICAL
        sectionList.layoutManager = layoutManager
        sectionList.addItemDecoration(BottomOffsetDecoration(bottomOffsetPx))
    }

    private fun getData () = with(viewBinding) {
        viewModel.getAllSectionWithAlerts().observe(viewLifecycleOwner, Observer {
            outLine.isEnabled = true
            animationView.animate().alpha(0f)
            animationView.cancelAnimation()
            sectionAdapter = SectionAdapter(it)
            sectionList.adapter = sectionAdapter
        })
        viewModel.getAlertsCount().observe(viewLifecycleOwner, Observer {
            errorContainer.visibility = View.INVISIBLE
            if (it > 0) {
                errorContainer.visibility = View.VISIBLE
                countErrors.text = "$it problems"
            }
        })
    }

}