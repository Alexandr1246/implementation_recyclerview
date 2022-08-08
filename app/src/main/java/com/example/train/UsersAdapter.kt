package com.example.train

import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.train.UsersAdapter.Companion.ID_MOVE_UP
import com.example.train.databinding.ItemUserBinding
import com.example.train.model.Users

interface UserActionListener{

    fun onUserMove(user: Users, moveBy: Int)

    fun onUserDelete(user: Users)

    fun onUserDetails(user: Users)

}

class UsersAdapter(private val actionListener: UserActionListener): RecyclerView.Adapter<UsersAdapter.UsersViewHolder>(), View.OnClickListener {


    class UsersViewHolder(
        val binding : ItemUserBinding) : RecyclerView.ViewHolder(binding.root )

    var users: List<Users> = emptyList()
        set(newValue){
        field = newValue
        notifyDataSetChanged()
    }

    override fun onClick(v: View) {
        val user = v.tag as Users

        when (v.id){

            R.id.ic_more->{
                showPopupMenu(v)
            }
            else->{
                actionListener.onUserDetails(user)
            }
        }
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(view.context, view)
        val context = view.context
        val user = view.tag as Users
        val position = users.indexOfFirst { it.id == user.id }
        popupMenu.menu.add(0, ID_MOVE_UP, Menu.NONE, context.getString(R.string.move_up)).apply { isEnabled = position>0 }
        popupMenu.menu.add(0, ID_MOVE_DOWN, Menu.NONE, context.getString(R.string.move_down)).apply { isEnabled = position < users.size-1 }
        popupMenu.menu.add(0, ID_REMOVE, Menu.NONE, context.getString(R.string.remove))

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                ID_MOVE_UP->{
                    actionListener.onUserMove(user, -1)
                }
                ID_MOVE_DOWN->{
                    actionListener.onUserMove(user, 1)

                }

                ID_REMOVE->{
                    actionListener.onUserDelete(user)

                }

            }
            return@setOnMenuItemClickListener true
        }
        popupMenu.show()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener(this)
        binding.icMore.setOnClickListener(this)
        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = users[position]
        with(holder.binding){
            holder.itemView.tag = user
            icMore.tag = user
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

    companion object{
        private const val ID_MOVE_UP = 1
        private const val ID_MOVE_DOWN = 2
        private const val ID_REMOVE = 3
    }

}