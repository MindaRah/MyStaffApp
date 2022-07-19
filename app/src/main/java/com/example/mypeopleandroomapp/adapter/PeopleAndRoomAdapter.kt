package com.example.mypeopleandroomapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mypeopleandroomapp.databinding.PeopleItemLayoutBinding
import com.example.mypeopleandroomapp.databinding.RoomItemLayoutBinding
import com.example.mypeopleandroomapp.model.PeopleItem
import com.example.mypeopleandroomapp.model.RoomItem

const val ROOM = 0
const val STAFF = 1

class PeopleAndRoomAdapter (private val desPeople: (PeopleItem) -> Unit):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var _people: MutableList<PeopleItem> = mutableListOf()
    var _room: MutableList<RoomItem> = mutableListOf()

    inner class PeoViewHolder(private val peopleBinding: PeopleItemLayoutBinding):
            RecyclerView.ViewHolder(peopleBinding.root){
                fun peopleBind(peopleCollection: PeopleItem) {
                    Glide.with(peopleBinding.imageView).load(peopleCollection.avatar).into(peopleBinding.imageView)
                    peopleBinding.apply {
                        firstNameTextView.text = peopleCollection.firstName
                        lastNameTextView.text = peopleCollection.lastName
                        emailTextView.text = peopleCollection.email
                        jobTitleTextView.text = peopleCollection.jobtitle

                    }

                    peopleBinding.root.setOnClickListener {
                        desPeople(peopleCollection)
                    }
                }
            }

    inner class RoomViewHolder(private val roomBinding: RoomItemLayoutBinding):
            RecyclerView.ViewHolder(roomBinding.root) {
                fun roomBind(roomCollection: RoomItem) {
                    val roomChange = roomCollection.isOccupied
                    roomBinding.roomChange.isChecked = roomChange == true
                    var info: String? = roomCollection?.createdAt
                    roomBinding.apply {
                        roomNameTextView.text = roomCollection.id
                        maxOccupancyTextView.text = roomCollection.maxOccupancy.toString()
                        if (info != null) {
                            createdAtTextView.text = info.substring(0,10)
                        }
                    }
                }
            }

    fun changePeopleList(peopleList: List<PeopleItem>) {
        _people.clear()
        _people.addAll(peopleList)
        notifyDataSetChanged()
    }

    fun changeRoomList(roomList: List<RoomItem>) {
        _room.clear()
        _room.addAll(roomList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ROOM) {
            return RoomViewHolder(
                RoomItemLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        if (viewType == STAFF) {
            return PeoViewHolder(
                PeopleItemLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        return PeoViewHolder(
            PeopleItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemViewType(position: Int): Int {
        if (position<_people.size) {
            return STAFF
        }

        if(position - _people.size < _room.size) {
            return ROOM
        }
        return -1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PeoViewHolder) {
            holder.peopleBind(_people[position])
        }

        if (holder is RoomViewHolder) {
            holder.roomBind(_room[position])
        }
    }

    override fun getItemCount(): Int {
        return _people.size + _room.size
    }
}





