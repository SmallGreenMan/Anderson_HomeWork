package android.examample.imageloader_hw_31.recycler_observer

import android.examample.imageloader_hw_31.R
import android.examample.imageloader_hw_31.databinding.ItemUserBinding
import android.examample.imageloader_hw_31.recycler_mvvm.screens.UserListItem
import android.examample.imageloader_hw_31.recycler_observer.model.User
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

interface UserActionListener{

    fun onUserMove(user: User, moveBy: Int)

    fun onUserDelete(user: User)

    fun onUserDetails(user: User)

    fun onUserFire(user: User)
}

class UsersDiffCallBack (
    private val oldList: List<User>,
    private val newList: List<User>
    ) : DiffUtil.Callback(){

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = oldList[oldItemPosition]
        val newUser = newList[newItemPosition]
        return oldUser.id == newUser.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = oldList[oldItemPosition]
        val newUser = newList[newItemPosition]
        return oldUser == newUser
    }
}

class UsersAdapter(
    private val actionListener: UserActionListener
) : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>(), View.OnClickListener {

    var users: List<UserListItem> = emptyList()
        set(newValue) {
            //val diffCallBack = UsersDiffCallBack(field, newValue)
            //val diffResult = DiffUtil.calculateDiff(diffCallBack)
            field = newValue
            //diffResult.dispatchUpdatesTo(this)
            notifyDataSetChanged()
        }

    override fun onClick(v: View) {
        val user = v.tag as User
        when(v.id){
            R.id.moreImageViewButton ->{
                showPopupMenu(v)
            }
            else ->{
                actionListener.onUserDetails(user)
            }
        }
    }

    override fun getItemCount(): Int = users.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)

        binding.moreImageViewButton.setOnClickListener(this)

        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val userListItem = users[position]
        val user = userListItem.user
        val context = holder.itemView.context
        with(holder.binding){
            holder.itemView.tag = user
            moreImageViewButton.tag = user

            if (userListItem.isInProgress){
                moreImageViewButton.visibility = View.INVISIBLE
                itemProgressBarr.visibility = View.VISIBLE
                holder.binding.root.setOnClickListener(null)
            } else {
                moreImageViewButton.visibility = View.VISIBLE
                itemProgressBarr.visibility = View.INVISIBLE
                holder.binding.root.setOnClickListener(this@UsersAdapter)
            }

            nameTextView.text = user.name
            companyTextView.text =
                if (user.company.isNotBlank()) user.company
                else context.getString(R.string.unemployed)
            telephonNumberTextView.text = user.telephone
            if (user.photo.isNotBlank()){
                Glide.with(photoImageView.context)
                    .load(user.photo)
                    .circleCrop()
                    .placeholder(R.drawable.ic_serch_image)
                    .error(R.drawable.ic_image_error)
                    .into(photoImageView)
            } else {
                photoImageView.setImageResource(R.drawable.ic_avatar)
            }
        }
    }

    private fun showPopupMenu(view: View){
        val popupMenu = PopupMenu(view.context, view)
        val context = view.context
        val user: User = view.tag as User
        val position = users.indexOfFirst { it.user.id == user.id }

        popupMenu.menu.add(0, ID_MOVE_UP, Menu.NONE, context.getString(R.string.Move_Up)).apply {
            isEnabled = position > 0
        }
        popupMenu.menu.add(0, ID_MOVE_DOWN, Menu.NONE, context.getString(R.string.Move_Down)).apply {
            isEnabled = position < users.size - 1
        }
        popupMenu.menu.add(0, ID_REMOVE, Menu.NONE, "Remove")
        if (user.company.isNotBlank()) {
            popupMenu.menu.add(0, IF_FIRE, Menu.NONE, context.getString(R.string.fire))
        }

        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                ID_MOVE_UP -> {
                 actionListener.onUserMove(user, -1)
                }
                ID_MOVE_DOWN -> {
                    actionListener.onUserMove(user, 1)
                }
                ID_REMOVE -> {
                    actionListener.onUserDelete(user)
                }
                IF_FIRE -> {
                    actionListener.onUserFire(user)
                }
            }
            return@setOnMenuItemClickListener true
        }

        popupMenu.show()
    }

    class UsersViewHolder(
        val binding: ItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private const val ID_MOVE_UP = 1
        private const val ID_MOVE_DOWN = 2
        private const val ID_REMOVE = 3
        private const val IF_FIRE = 4
    }
}