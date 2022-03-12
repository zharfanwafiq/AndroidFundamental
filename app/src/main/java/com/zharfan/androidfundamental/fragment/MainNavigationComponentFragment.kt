package com.zharfan.androidfundamental.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.zharfan.androidfundamental.R
import com.zharfan.androidfundamental.databinding.FragmentMainNavigationComponentBinding


class MainNavigationComponentFragment : Fragment() {

    private var _binding: FragmentMainNavigationComponentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMainNavigationComponentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAction()
    }

    private fun setAction(){
        binding.apply {
            btnCategory.setOnClickListener{
                it.findNavController().navigate(R.id.action_mainNavigationComponentFragment_to_navCategoryFragment)
            }

            btnProfile.setOnClickListener{
                it.findNavController().navigate(R.id.action_mainNavigationComponentFragment_to_profileActivity)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }
}