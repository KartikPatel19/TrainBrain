package com.deucat.kartik.trainbrain.route

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.deucat.kartik.trainbrain.R

internal class RouteClassAdapter(private val mRouteClasses: Array<RouteClass>) :
    RecyclerView.Adapter<RouteClassAdapter.RouteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteClassAdapter.RouteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.train_route_list, parent, false)
        return RouteViewHolder(view)
    }

    override fun onBindViewHolder(holder: RouteClassAdapter.RouteViewHolder, position: Int) {
        holder.bindRoute(mRouteClasses[position])
    }

    override fun getItemCount(): Int {
        return mRouteClasses.size
    }

    internal inner class RouteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mIndexNumber: TextView = itemView.findViewById(R.id.routeListIndexNumber)
        var mDistence: TextView = itemView.findViewById(R.id.routeListDistance)
        var mSchArr: TextView = itemView.findViewById(R.id.routeListSch)
        var mSchDep: TextView = itemView.findViewById(R.id.routeListDep)
        var mNameOfStation: TextView = itemView.findViewById(R.id.routeListStationName)
        var mNameOfState: TextView = itemView.findViewById(R.id.routeListStateName)

        fun bindRoute(routeClass: RouteClass) {
            mIndexNumber.text = routeClass.indexNumber.toString()
            mDistence.text = routeClass.distance.toString()
            mSchArr.text = routeClass.schArr
            mSchDep.text = routeClass.schDep
            mNameOfStation.text = routeClass.stationName
            mNameOfState.text = routeClass.state
        }

    }

}
