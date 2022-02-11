package com.example.mvpapplicaton.view.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mvpapplicaton.R
import com.example.mvpapplicaton.databinding.FragmentDetailsBinding
import com.example.mvpapplicaton.view.user.GithubUser

private const val ARG_PARAM1 = "param1"

class DetailsFragment : Fragment() {

    private var vb: FragmentDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDetailsBinding.inflate(inflater, container, false).also { vb = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTextView()
    }

    private fun initTextView() {
        val user = arguments?.getParcelable(ARG_PARAM1) as GithubUser?
        vb?.textDetails?.text = user?.login

    }

    override fun onDestroy() {
        super.onDestroy()
        vb = null
    }

    companion object {
        @JvmStatic
        fun newInstance(data: GithubUser) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, data)
                }
            }
    }
}