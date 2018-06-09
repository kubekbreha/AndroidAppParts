package com.example.bottomnavigation.helper

import android.support.v4.app.Fragment
import com.kubekbreha.mainbottomkotlin.R
import com.kubekbreha.mainbottomkotlin.fragments.Item1
import com.kubekbreha.mainbottomkotlin.fragments.Item2
import com.kubekbreha.mainbottomkotlin.fragments.Item3

enum class BottomNavigationPosition(val position: Int, val id: Int) {
    PEOPLES(0, R.id.item1),
    WATSON(1, R.id.item2),
    PROFILE(2, R.id.item3),
}

fun findNavigationPositionById(id: Int): BottomNavigationPosition = when (id) {
    BottomNavigationPosition.PEOPLES.id -> BottomNavigationPosition.PROFILE
    BottomNavigationPosition.WATSON.id -> BottomNavigationPosition.WATSON
    BottomNavigationPosition.PROFILE.id -> BottomNavigationPosition.PROFILE
    else -> BottomNavigationPosition.PEOPLES
}

fun BottomNavigationPosition.createFragment(): Fragment = when (this) {
    BottomNavigationPosition.PEOPLES -> Item1.newInstance()
    BottomNavigationPosition.WATSON -> Item2.newInstance()
    BottomNavigationPosition.PROFILE -> Item3.newInstance()
}

fun BottomNavigationPosition.getTag(): String = when (this) {
    BottomNavigationPosition.PEOPLES -> Item1.TAG
    BottomNavigationPosition.WATSON -> Item2.TAG
    BottomNavigationPosition.PROFILE -> Item3.TAG
}

