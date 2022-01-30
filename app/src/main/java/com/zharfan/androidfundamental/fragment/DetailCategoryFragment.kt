package com.zharfan.androidfundamental.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.zharfan.androidfundamental.activity.ProfileActivity
import com.zharfan.androidfundamental.databinding.FragmentDetailCategoryBinding


class DetailCategoryFragment : Fragment() {

    private lateinit var binding: FragmentDetailCategoryBinding
    var description:String? =null

    companion object{
        var EXTRA_NAME ="com.zharfan.androidfundamental.fragment.EXTRA_NAME"
        var EXTRA_DESCRIPTION="com.zharfan.androidfundamental.fragment.EXTRA_DESCRIPTION"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailCategoryBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (savedInstanceState != null){
            val descFormBundle = savedInstanceState.getString(EXTRA_DESCRIPTION)
            description = descFormBundle
        }

    setCategory()
    }

    private fun setCategory() {
        binding.apply {
            if (arguments != null){
                val categoryName = arguments?.getString(EXTRA_NAME)
                tvCategoryName.text = categoryName
                tvCategoryDescription.text = description

            }

            btnProfile.setOnClickListener{
                startActivity(Intent(requireActivity(),ProfileActivity::class.java))
            }

            btnShowDialog.setOnClickListener {
                val mOptionDialogFragment = OptionDialogFragment()
                val mFragmentManager = childFragmentManager
                mOptionDialogFragment.show(mFragmentManager, OptionDialogFragment::class.java.simpleName)
            }
        }
    }

    internal var optionDialogListener: OptionDialogFragment.OnOptionDialogListener = object : OptionDialogFragment.OnOptionDialogListener {
        override fun onOptionChosen(text: String?) {
            Toast.makeText(requireActivity(),text, Toast.LENGTH_SHORT).show()
        }
    }


}