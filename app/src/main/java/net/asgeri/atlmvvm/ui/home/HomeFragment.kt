package net.asgeri.atlmvvm.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import net.asgeri.atlmvvm.databinding.FragmentHomeBinding
import net.asgeri.atlmvvm.utils.gone
import net.asgeri.atlmvvm.utils.visible


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel>()
    private val todoAdapter = TodoAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        binding.rvHome.adapter = todoAdapter


    }

    private fun observeData() {
        viewModel.todo.observe(viewLifecycleOwner) {
            todoAdapter.updateList(it)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) binding.progressBar.visible() else binding.progressBar.gone()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}