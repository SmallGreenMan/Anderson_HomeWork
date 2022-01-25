package android.examample.imageloader_hw_31.HomeWork_6.fragments

import android.examample.imageloader_hw_31.HomeWork_6.data.HW_6_ContactsData
import android.examample.imageloader_hw_31.HomeWork_6.data.hw6_contactsData
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.examample.imageloader_hw_31.R
import android.examample.imageloader_hw_31.databinding.FragmentHW6ContactListBinding
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso

class HW_6_Contact_List_Fragment : Fragment() {

    private var _binding: FragmentHW6ContactListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHW6ContactListBinding.inflate(inflater, container, false)
        return this.binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = MyReecyclerViewAdapter()
        }
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image = itemView.findViewById<ImageView>(R.id.imageView)
        val firstName = itemView.findViewById<TextView>(R.id.name_textView)
        val lastname = itemView.findViewById<TextView>(R.id.lastName_textView)
        val telephoneNumber = itemView.findViewById<TextView>(R.id.telephonNumber_textView)
    }

    inner class MyReecyclerViewAdapter : RecyclerView.Adapter<MyViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view: View? = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_item, parent, false)
            val myViewHolder = MyViewHolder(view!!)
            //val position: Int = myViewHolder.adapterPosition
            //if (myViewHolder.adapterPosition != RecyclerView.NO_POSITION) {
                myViewHolder.image.setOnClickListener {
                    Toast.makeText(parent.context, myViewHolder.adapterPosition.toString(), Toast.LENGTH_SHORT).show()
                    val listItem: HW_6_ContactsData = hw6_contactsData[myViewHolder.adapterPosition]!!
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.fragment_container_HW_6,
                            HW_6_Contact_Info_Fragment.newInstans(
                                listItem.name,
                                listItem.lastName,
                                listItem.telephoneNumber,
                                listItem.img,
                                listItem.id
                            )
                        )
                        .addToBackStack(null)
                        .commit()
                }
            //}
            return myViewHolder
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val listItem: HW_6_ContactsData = hw6_contactsData[holder.adapterPosition]!!
            holder.firstName.text = listItem.name
            holder.lastname.text = listItem.lastName
            holder.telephoneNumber.text = listItem.telephoneNumber

//            Glide.with(holder.image.context)
//                .load(listItem.imgSrc)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .placeholder(R.drawable.ic_placeholder_foreground)
//                .into(holder.image)

            Picasso.get()
                .load(listItem.img)
                .placeholder(R.drawable.ic_placeholder_foreground)
                .error(R.drawable.ic_placeholder_error_foreground)
                .into(holder.image)
        }

        override fun getItemCount(): Int {
            return hw6_contactsData.size
        }
    }
}
