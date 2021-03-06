package com.zharfan.androidfundamental.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.zharfan.androidfundamental.R
import com.zharfan.androidfundamental.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false )
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setActionCategory()
    }

    private fun setActionCategory()= with(binding) {
            btnCategory.setOnClickListener {
                val mCategoryFragment = CategoryFragment()
                val mFragmentManager = parentFragmentManager
                mFragmentManager.commit {
                    addToBackStack(null)
                    replace(R.id.frameContainer, mCategoryFragment, CategoryFragment::class.java.simpleName)
                }
            }
    }
}



