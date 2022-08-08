package com.example.train

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.train.databinding.ItemUserBinding
import com.example.train.model.Users

class UsersAdapter: RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {


    class UsersViewHolder(
        val binding : ItemUserBinding) : RecyclerView.ViewHolder(binding.root )

    var users: List<Users> = emptyList()
        set(newValue){
        field = newValue
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = users[position]
        with(holder.binding){
            icUserName.text = user.name
            icCompanyInfo.text = user.company
            if (user.photo.isNotBlank()){
                Glide.with(userAvatar.context).
                    load(user.photo).
                    circleCrop().
                    placeholder(R.drawable.ic_defult_avatar).
                    error(R.drawable.ic_defult_avatar).into(userAvatar)
            }
            else{
                userAvatar.setImageResource(R.drawable.ic_defult_avatar)
            }

        }
    }

    override fun getItemCount(): Int = users.size
}