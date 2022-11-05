package com.spinning

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import luckywheel.LuckyItem
import luckywheel.LuckyWheelView
import java.util.*


class MainActivity : AppCompatActivity() {

    private var data = mutableListOf<LuckyItem>()
    private var selectNum:Int = -1
    private var buttonClick:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        //window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val luckyItem1 = LuckyItem()
        luckyItem1.topText = "1"
        //luckyItem1.secondaryText = "One"
        luckyItem1.icon = R.drawable.test1
        luckyItem1.color = -0xc20
        data.add(luckyItem1)

        val luckyItem2 = LuckyItem()
        luckyItem2.topText = "2"
        //luckyItem2.secondaryText = "Two"
        luckyItem2.icon = R.drawable.test2
        luckyItem2.color = -0x1f4e
        data.add(luckyItem2)

        val luckyItem3 = LuckyItem()
        luckyItem3.topText = "3"
        //luckyItem3.secondaryText = "Three"
        luckyItem3.icon = R.drawable.test3
        luckyItem3.color = -0x3380
        data.add(luckyItem3)

        //////////////////
        val luckyItem4 = LuckyItem()
        luckyItem4.topText = "4"
        //luckyItem4.secondaryText = "Four"
        luckyItem4.icon = R.drawable.test4
        luckyItem4.color = -0xc20
        data.add(luckyItem4)

        val luckyItem5 = LuckyItem()
        luckyItem5.topText = "5"
        //luckyItem5.secondaryText = "Five"
        luckyItem5.icon = R.drawable.test5
        luckyItem5.color = -0x1f4e
        data.add(luckyItem5)

        val luckyItem6 = LuckyItem()
        luckyItem6.topText = "6"
        //luckyItem6.secondaryText = "Six"
        luckyItem6.icon = R.drawable.test6
        luckyItem6.color = -0x3380
        data.add(luckyItem6)

        //////////////////////
        val luckyItem7 = LuckyItem()
        luckyItem7.topText = "7"
        //luckyItem7.secondaryText = "Seven"
        luckyItem7.icon = R.drawable.test7
        luckyItem7.color = -0xc20
        data.add(luckyItem7)

        val luckyItem8 = LuckyItem()
        luckyItem8.topText = "8"
        //luckyItem8.secondaryText = "Eight"
        luckyItem8.icon = R.drawable.test8
        luckyItem8.color = -0x1f4e
        data.add(luckyItem8)


        val luckyItem9 = LuckyItem()
        luckyItem9.topText = "9"
        //luckyItem9.secondaryText = "Nine"
        luckyItem9.icon = R.drawable.test9
        luckyItem9.color = -0x3380
        data.add(luckyItem9)

        /////////////////////
        luckyWheel.setData(data)
        luckyWheel.setRound(5)

        /*luckyWheelView.setLuckyWheelBackgrouldColor(0xff0000ff.toInt());
        luckyWheelView.setLuckyWheelTextColor(0xffcc0000.toInt());
        luckyWheelView.setLuckyWheelCenterImage(getResources().getDrawable(R.drawable.icon));
        luckyWheelView.setLuckyWheelCursorImage(R.drawable.ic_cursor);*/


        /*luckyWheelView.setLuckyWheelBackgrouldColor(0xff0000ff);
        luckyWheelView.setLuckyWheelTextColor(0xffcc0000);
        luckyWheelView.setLuckyWheelCenterImage(getResources().getDrawable(R.drawable.icon));
        luckyWheelView.setLuckyWheelCursorImage(R.drawable.ic_cursor);*/
        findViewById<View>(
            R.id.play
        ).setOnClickListener {
            if (selectNum == -1) {
                Toast.makeText(applicationContext, "Please choose your lucky number", Toast.LENGTH_SHORT)
                    .show()
            }else{
                val index: Int = getRandomIndex()
                for (i in 0 until num_group.getChildCount()) {
                    num_group.getChildAt(i).setEnabled(false)
                }
                luckyWheel.startLuckyWheelWithTargetIndex(index)
            }
        }

        wallet_img.setOnClickListener {

        }

        // disable touch to spin
        luckyWheel.isTouchEnabled = false

        luckyWheel.setLuckyRoundItemSelectedListener(object :
            LuckyWheelView.LuckyItemSelectedListener {
            override fun onLuckyItemSelected(index: Int) {

                if (selectNum != -1) {
                    if (selectNum == data.get(index).topText.toInt()) {
                        Toast.makeText(
                            applicationContext,
                            "You win with " + data.get(index).topText,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "You loose with " + data.get(index).topText,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                    for (i in 0 until num_group.getChildCount()) {
                        num_group.getChildAt(i).setEnabled(true)
                    }
                    num_group.clearCheck()
                    selectNum = -1
                }else{
                    Toast.makeText(applicationContext, "Please tap on Play", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })

        // This overrides the radiogroup onCheckListener
        num_group.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->

            // This will get the radiobutton that has changed in its check state
            if (group != null && checkedId != -1) {
                val checkedRadioButton = group.findViewById<View>(checkedId) as RadioButton
                // This puts the value (true/false) into the variable
                val isChecked = checkedRadioButton.isChecked
                // If the radiobutton that has changed in check state is now checked...
                if (isChecked) {
                    // Changes the textview's text to "Checked: example radiobutton text"
                    selectNum = checkedRadioButton.text.toString().toInt()
                    Log.d("TAGG", "selectNum $selectNum")
                }
            }
        })
    }

    private fun getRandomIndex(): Int {
        val rand = Random()
        return rand.nextInt(data.size - 1) + 0
    }

    private fun getRandomRound(): Int {
        val rand = Random()
        return rand.nextInt(10) + 15
    }
}