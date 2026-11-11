package com.example

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nomeDigitado = arguments?.getString("NOME_DIGITADO")
        binding.textviewSecond.text = nomeDigitado ?: "Nenhum nome digitado"


        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_ThirdFragment)
        }

        binding.buttonThird.setOnClickListener {
            val nome = binding.etTerceiroNome.text.toString()

            if (nome.isBlank()) {
                binding.etTerceiroNome.error = "Digite um nome"
            } else {
                binding.textviewSecond.text = nome
            }
        }


        binding.buttonThird.setOnClickListener {
            val nome = binding.etTerceiroNome.text.toString()
            if (nome.isBlank()) {
                Toast.makeText(
                    requireContext(),
                    "Digite um nome",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val bundle = Bundle().apply {
                    putString("NOVO_NOME_DIGITADO", nome)
                }
                findNavController().navigate(
                    R.id.action_SecondFragment_to_ThirdFragment,
                    bundle
                )
            }
        }

    }
}