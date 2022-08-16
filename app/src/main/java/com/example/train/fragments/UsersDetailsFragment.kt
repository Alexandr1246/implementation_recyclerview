package com.example.train.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.train.R
import com.example.train.databinding.FragmentUsersDetailsBinding


class UsersDetailsFragment : Fragment() {

    private lateinit var binding: FragmentUsersDetailsBinding
    private val viewModel: UsersDetailsViewModel by viewModels{factory()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadUser(requireArguments().getLong(ARG_USER_ID))

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUsersDetailsBinding.inflate(layoutInflater, container,false)
        viewModel.userDetails.observe(viewLifecycleOwner, Observer {
            binding.userNameTextView.text = it.user.name
            if (it.user.photo.isNotBlank()){
                Glide.with(this)
                    .load(it.user.photo)
                    .circleCrop()
                    .into(binding.imDetailsPhoto)
            }
            else{
                Glide.with(this)
                    .load(R.drawable.ic_defult_avatar)
                    .into(binding.imDetailsPhoto)
            }
            binding.userDetailsTextView.text = it.details

        })
        binding.deleteButton.setOnClickListener{
            viewModel.deleteUser()
            navigator().showToast(R.string.user_has_been_deleted)
            navigator().goBack()
        }
        return binding.root
    }

    companion object{

        private const val ARG_USER_ID = "ARG_USER_ID"
        fun newInstance(userId: Long): UsersDetailsFragment{
            val fragment = UsersDetailsFragment()
            fragment.arguments = bundleOf(ARG_USER_ID to userId)
            return fragment
        }
    }
}
