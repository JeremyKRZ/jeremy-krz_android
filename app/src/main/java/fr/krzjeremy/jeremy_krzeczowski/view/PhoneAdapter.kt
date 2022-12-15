package fr.krzjeremy.jeremy_krzeczowski.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.krzjeremy.jeremy_krzeczowski.R
import fr.krzjeremy.jeremy_krzeczowski.databinding.ItemCustomRecyclerBinding
import fr.krzjeremy.jeremy_krzeczowski.databinding.ItemCustomRecyclerFooterBinding
import fr.krzjeremy.jeremy_krzeczowski.databinding.ItemCustomRecyclerHeaderBinding
import fr.krzjeremy.jeremy_krzeczowski.model.MyObjectForRecyclerView
import fr.krzjeremy.jeremy_krzeczowski.model.ObjectDataFooterSample
import fr.krzjeremy.jeremy_krzeczowski.model.ObjectDataHeaderSample
import fr.krzjeremy.jeremy_krzeczowski.model.ObjectDataSample


private val diffItemUtils = object : DiffUtil.ItemCallback<MyObjectForRecyclerView>() {
    override fun areItemsTheSame(oldItem: MyObjectForRecyclerView, newItem: MyObjectForRecyclerView): Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: MyObjectForRecyclerView, newItem: MyObjectForRecyclerView): Boolean {
        return oldItem == newItem
    }
}

class PhoneAdapter( private val onItemClick: (quoteUi: ObjectDataSample, view: View) -> Unit,) : ListAdapter<MyObjectForRecyclerView, RecyclerView.ViewHolder>(diffItemUtils) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {

            MyItemType.ROW.type -> {
                AndroidVersionViewHolder(
                    ItemCustomRecyclerBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ), onItemClick
                )
            }

            MyItemType.HEADER.type -> {
                AndroidVersionHeaderViewHolder(
                    ItemCustomRecyclerHeaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            MyItemType.FOOTER.type -> {
                AndroidVersionFooterViewHolder(
                    ItemCustomRecyclerFooterBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> throw RuntimeException("Wrong view type received $viewType")
        }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder.itemViewType) {
            MyItemType.ROW.type -> (holder as AndroidVersionViewHolder).bind(getItem(position) as ObjectDataSample)
            MyItemType.HEADER.type -> (holder as AndroidVersionHeaderViewHolder).bind(getItem(position) as ObjectDataHeaderSample)
            MyItemType.FOOTER.type -> (holder as AndroidVersionFooterViewHolder).bind(getItem(position) as ObjectDataFooterSample)
            else -> throw RuntimeException("Wrong view type received ${holder.itemView}")
        }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ObjectDataSample -> MyItemType.ROW.type
            is ObjectDataHeaderSample -> MyItemType.HEADER.type
            is ObjectDataFooterSample -> MyItemType.FOOTER.type
        }
    }
}


class AndroidVersionViewHolder(
    private val binding: ItemCustomRecyclerBinding,
    onItemClick: (objectDataSample: ObjectDataSample, view: View) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var ui: ObjectDataSample

    init {
        binding.root.setOnClickListener {
            onItemClick(ui, itemView)
        }
    }

    fun bind(objectDataSample: ObjectDataSample) {
        ui = objectDataSample
        binding.itemRecyclerViewPhoneName.text = objectDataSample.phoneName
        binding.itemRecyclerViewBrandName.text = objectDataSample.brandName
        Glide.with(itemView.context)
            .load(objectDataSample.phoneImage)
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.itemRecyclerViewImage)
    }
}

class AndroidVersionHeaderViewHolder(
    private val binding: ItemCustomRecyclerHeaderBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(objectDataHeaderSample: ObjectDataHeaderSample) {
        binding.itemRecyclerViewHeader.text = objectDataHeaderSample.header
    }
}

class AndroidVersionFooterViewHolder(
    private val binding: ItemCustomRecyclerFooterBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(objectDataFooterSample: ObjectDataFooterSample) {
        binding.itemRecyclerViewFooter.text = objectDataFooterSample.footer
    }
}

enum class MyItemType(val type: Int) {
    ROW(0),
    HEADER(1),
    FOOTER(2)
}

