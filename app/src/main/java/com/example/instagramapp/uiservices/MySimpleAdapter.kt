package com.example.instagramapp.uiservices

interface MySimpleAdapter {

    fun onItemDelete(position: Int)

    fun onItemMove(fromPosition: Int, toPosition: Int)
}