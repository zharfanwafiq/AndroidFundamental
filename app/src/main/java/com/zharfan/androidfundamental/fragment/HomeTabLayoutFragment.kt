package com.zharfan.androidfundamental.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zharfan.androidfundamental.R
import com.zharfan.androidfundamental.databinding.FragmentHomeTabLayoutBinding

class HomeTabLayoutFragment : Fragment() {

    private var _binding : FragmentHomeTabLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeTabLayoutBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setData()
    }

    private fun setData() {
        binding.apply {
            val index = arguments?.getInt(ARG_SECTION_NUMBER,0)
            tvHomeFragment.text = getString(R.string.content_tab_text,index)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        const val ARG_SECTION_NUMBER = "com.zharfan.androidfundamental.fragment.ARG_SECTION_NUMBER"
    }
}