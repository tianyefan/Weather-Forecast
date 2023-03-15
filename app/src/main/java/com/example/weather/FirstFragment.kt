package com.example.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.weather.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var bt1 = binding.buttonFirst
        var bt2 = binding.buttonFirst2
        var bt3 = binding.buttonFirst3
        var bt4 = binding.buttonFirst4
        var bt5 = binding.buttonFirst5
        var bt6 = binding.buttonFirst6



        val bundle6 = bundleOf("ct" to "5")
        val bundle5 = bundleOf("ct" to "4")
        val bundle4 = bundleOf("ct" to "3")
        val bundle3 = bundleOf("ct" to "2")
        val bundle2 = bundleOf("ct" to "1")
        val bundle1 = bundleOf("ct" to "0")

        bt1.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle1)
        }

        bt2.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle2)
        }

        bt3.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle3)
        }

        bt4.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle4)
        }

        bt5.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle5)
        }
        bt6.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle6)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}