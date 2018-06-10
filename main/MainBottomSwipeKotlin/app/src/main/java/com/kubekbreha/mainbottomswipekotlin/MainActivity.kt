package com.kubekbreha.mainbottomswipekotlin

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.kubekbreha.mainbottomswipekotlin.Fragment.CallsFragment
import com.kubekbreha.mainbottomswipekotlin.Fragment.ChatFragment
import com.kubekbreha.mainbottomswipekotlin.Fragment.ContactsFragment


class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    //This is our viewPager
    private var viewPager: ViewPager? = null


    //Fragments

    private lateinit var chatFragment: ChatFragment
    private lateinit var callsFragment: CallsFragment
    private lateinit var contactsFragment: ContactsFragment
    private  var prevMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initializing viewPager
        viewPager = findViewById(R.id.viewpager) as ViewPager

        //Initializing the bottomNavigationView
        bottomNavigationView = findViewById(R.id.bottom_navigation) as BottomNavigationView

        bottomNavigationView.setOnNavigationItemSelectedListener(
                object : BottomNavigationView.OnNavigationItemSelectedListener {
                    override fun onNavigationItemSelected(item: MenuItem): Boolean {
                        when (item.itemId) {
                            R.id.action_call -> viewPager!!.currentItem = 0
                            R.id.action_chat -> viewPager!!.currentItem = 1
                            R.id.action_contact -> viewPager!!.currentItem = 2
                        }
                        return false
                    }
                })

        viewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                if (prevMenuItem != null) {
                    prevMenuItem!!.isChecked = false
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false)
                }
                Log.d("page", "onPageSelected: $position")
                bottomNavigationView.getMenu().getItem(position).setChecked(true)
                prevMenuItem = bottomNavigationView.getMenu().getItem(position)

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        /*  //Disable ViewPager Swipe

       viewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });

        */

        setupViewPager(viewPager!!)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        callsFragment = CallsFragment()
        chatFragment = ChatFragment()
        contactsFragment = ContactsFragment()
        adapter.addFragment(callsFragment)
        adapter.addFragment(chatFragment)
        adapter.addFragment(contactsFragment)
        viewPager.adapter = adapter
    }
}
