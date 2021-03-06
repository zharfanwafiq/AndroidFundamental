package com.zharfan.androidfundamental.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.zharfan.androidfundamental.R
import com.zharfan.androidfundamental.databinding.FragmentNavDetailCategoryBinding


class NavDetailCategoryFragment : Fragment() {

    private var _binding: FragmentNavDetailCategoryBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNavDetailCategoryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBundleData()
        setAction()
    }

    private fun setBundleData() {
        val dataName = NavDetailCategoryFragmentArgs.fromBundle(arguments as Bundle).name
        val description = NavDetailCategoryFragmentArgs.fromBundle(arguments as Bundle).stock

        binding.apply {
            tvDetailCategory.text = dataName
            tvCategoryDescription.text = String.format("%s: %s",getString(R.string.tvStock),description)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setAction(){
        binding.apply {
            btnProfile.setOnClickListener (
                Navigation.createNavigateOnClickListener(
                    R.id.action_navDetailCategoryFragment_to_mainNavigationComponentFragment,null
                )
            )
        }
    }

}