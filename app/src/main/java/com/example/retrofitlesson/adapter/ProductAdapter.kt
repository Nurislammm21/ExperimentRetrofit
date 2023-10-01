package com.example.retrofitlesson.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitlesson.R
import com.example.retrofitlesson.Retrofit.Product
import com.example.retrofitlesson.databinding.ListItemBinding

class ProductAdapter : ListAdapter<Product, ProductAdapter.Holder>(Comparator()) {

    class Holder(view : View) : RecyclerView.ViewHolder(view){
        private val binding = ListItemBinding.bind(view)

         fun setData(product : Product) = with(binding){
                txtTittle.text = product.title
                txtDesc.text = product.description
        }
    }




    class Comparator : DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setData(getItem(position))
    }

}