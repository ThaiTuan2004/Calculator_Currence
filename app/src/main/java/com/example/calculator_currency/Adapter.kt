package com.example.calculator_currency

import android.view.LayoutInflater
import android.widget.BaseAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class Money_Adapter(val list:List<String>) :BaseAdapter() {
    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view= convertView

        if (view==null){
            view=LayoutInflater.from(parent?.context).inflate(R.layout.view_money,parent,false)
        }

        val money=list[position]

        val money_view= view?.findViewById<TextView>(R.id.money_view)

        money_view?.text=money

        return view!!
    }
}