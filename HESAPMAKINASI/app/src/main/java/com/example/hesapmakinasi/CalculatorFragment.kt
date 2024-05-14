package com.example.hesapmakinasi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.hesapmakinasi.databinding.FragmentCalculatorBinding
import kotlin.math.pow


class CalculatorFragment : Fragment() {
    private lateinit var binding: FragmentCalculatorBinding
    var firstEnteredNumber = ""
    var NewOperation = true
    var operation = ""
    var originaltextViewInfoSize = 30f
    var originaltextViewCalculatorSize = 30f
    var result : Double? = null
    var newResult : Double? = null
    var isClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalculatorBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button0.setOnClickListener(){ selectedButton(it)}
        binding.button1.setOnClickListener(){selectedButton(it) }
        binding.button2.setOnClickListener(){selectedButton(it)}
        binding.button3.setOnClickListener(){selectedButton(it)}
        binding.button4.setOnClickListener(){selectedButton(it)}
        binding.button5.setOnClickListener(){selectedButton(it)}
        binding.button6.setOnClickListener(){selectedButton(it)}
        binding.button7.setOnClickListener(){selectedButton(it)}
        binding.button8.setOnClickListener(){selectedButton(it)}
        binding.button9.setOnClickListener(){selectedButton(it)}
        binding.buttonVirgule.setOnClickListener(){selectedButton(it)}
        binding.buttonPlusMinus.setOnClickListener(){ selectedButton(it) }
        binding.buttonSum.setOnClickListener(){operations(it) }
        binding.buttonSub.setOnClickListener(){ operations(it)}
        binding.buttonMul.setOnClickListener(){operations(it)}
        binding.buttonDiv.setOnClickListener(){operations(it)}
        binding.buttonMod.setOnClickListener(){operations(it)}
        binding.buttonPow.setOnClickListener(){ operations(it) }
        binding.buttonEqual.setOnClickListener(){
            equal(it)
        }
        binding.buttonClear.setOnClickListener(){
            clear(it)
            returnToFirstTextSize(it)
        }
    }
    fun selectedButton(view: View){
        if (NewOperation){
            binding.textViewCalculator.text = ""
        }
        NewOperation = false
        var selectButton = view as Button
        var selectedButtonValue : String = binding.textViewCalculator.text.toString()
        when(selectButton.id){
            binding.button0.id ->{
                selectedButtonValue += "0"
            }
            binding.button1.id ->{
                selectedButtonValue += "1"
            }
            binding.button2.id ->{
                selectedButtonValue += "2"
            }
            binding.button3.id ->{
                selectedButtonValue += "3"
            }
            binding.button4.id ->{
                selectedButtonValue += "4"
            }
            binding.button5.id ->{
                selectedButtonValue += "5"
            }
            binding.button6.id ->{
                selectedButtonValue += "6"
            }
            binding.button7.id ->{
                selectedButtonValue += "7"
            }
            binding.button8.id ->{
                selectedButtonValue += "8"
            }
            binding.button9.id ->{
                selectedButtonValue += "9"
            }
            binding.buttonVirgule.id ->{
                selectedButtonValue += "."
            }
            binding.buttonPlusMinus.id ->{ selectedButtonValue += "-" }

        }
        binding.textViewCalculator.setText(selectedButtonValue)
    }

    fun operations(view: View){
        var selectButton = view as Button
        NewOperation = true
        when(selectButton.id){
            binding.buttonSum.id ->{
                operation = "+"
            }
            binding.buttonSub.id ->{
                operation = "-"
            }
            binding.buttonMul.id ->{
                operation = "*"
            }
            binding.buttonDiv.id ->{
                operation = "/"
            }
            binding.buttonMod.id ->{
                operation = "%"
            }
            binding.buttonPow.id ->{ operation = "^" }


        }
        firstEnteredNumber = binding.textViewCalculator.text.toString()

    }
    fun equal(view: View){
        var newNumber = binding.textViewCalculator.text.toString()
        when (operation){
            "+" -> {
                result = firstEnteredNumber.toDouble() + newNumber.toDouble()
                newResult = result!!.toDouble() + newNumber.toDouble()
            }
            "-" -> {
                result = firstEnteredNumber.toDouble() - newNumber.toDouble()
                newResult = result!!.toDouble() - newNumber.toDouble()

            }
            "*" -> {
                result = firstEnteredNumber.toDouble() * newNumber.toDouble()
                newResult = result!!.toDouble() * newNumber.toDouble()

            }
            "/" -> {
                result = firstEnteredNumber.toDouble() / newNumber.toDouble()
                newResult = result!!.toDouble() / newNumber.toDouble()

            }
            "%" -> {
                // rem(remainder) kalanı bulmak için kullanılır. % sembolu ile de gösterilir
                result = firstEnteredNumber.toDouble().rem(newNumber.toDouble())
                newResult = result!!.toDouble().rem(newNumber.toDouble())


            }
            "^" -> {
                result = firstEnteredNumber.toDouble().pow(newNumber.toDouble())
                newResult = result!!.toDouble().pow(newNumber.toDouble())

            }
        }

        /* ****Butona basılınca textview in textSize ni buyutup-kucultme****
          val currentTextSize = myTextView.textSize
          val newTextSize = currentTextSize * 1.2f // İstediğiniz oranda büyütebilirsiniz
          myTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, newTextSize)*/

       // binding.textViewCalculator.textSize = 19.3f
        //binding.textViewCalculator.setText(firstEnteredNumber.toString() +" "+ operation.toString() +" "+ newNumber.toString())

       // binding.textViewInfo.textSize = 42.5f
        // binding.textViewInfo.setText(result.toString())

        if (!isClicked){
            binding.textViewCalculator.textSize = 19.3f
            binding.textViewCalculator.setText(firstEnteredNumber.toString() +" "+ operation.toString() +" "+ newNumber.toString())
            binding.textViewInfo.textSize = 42.5f
            binding.textViewInfo.setText(result.toString())

            isClicked = true
        }
        else{

           // newResult = result.toString() + operation.toString() + newNumber.toString()
            binding.textViewInfo.setText(result.toString())
            binding.textViewCalculator.setText(firstEnteredNumber.toString() +" "+ operation.toString() +" "+ newNumber.toString())

        }
        NewOperation = true
    }
    fun clear(view: View){
        binding.textViewCalculator.text = ""
        binding.textViewInfo.text = ""
        NewOperation = true
    }
    fun returnToFirstTextSize(view: View){
        binding.textViewInfo.textSize = originaltextViewInfoSize
        binding.textViewCalculator.textSize = originaltextViewCalculatorSize
    }
}
