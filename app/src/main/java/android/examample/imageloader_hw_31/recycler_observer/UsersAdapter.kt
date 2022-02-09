package android.examample.imageloader_hw_31.recycler_observer

import android.examample.imageloader_hw_31.R
import android.examample.imageloader_hw_31.databinding.ItemUserBinding
import android.examample.imageloader_hw_31.recycler_observer.model.User
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {

    var users: List<User> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    class UsersViewHolder(
        val binding: ItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = users.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user: User = users[position]
        with(holder.binding){
            nameTextView.text = user.name
            companyTextView.text = user.company
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


}