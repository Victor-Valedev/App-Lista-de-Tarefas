package com.victor.listadetarefas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.victor.listadetarefas.adapter.TarefaAdapter
import com.victor.listadetarefas.database.TarefaDAO
import com.victor.listadetarefas.databinding.ActivityMainBinding
import com.victor.listadetarefas.model.Tarefa
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }

    private var listaTarefas = emptyList<Tarefa>()
    private var tarefaAdapter: TarefaAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.fabAdicionar.setOnClickListener {
            val intent = Intent(this, AdicionarTarefaActivity::class.java )
            startActivity( intent )
        }
        //RecyclerView
        tarefaAdapter = TarefaAdapter()
        tarefaAdapter?.adicionarLista( listaTarefas )
        binding.rvTarefas.adapter = tarefaAdapter

        binding.rvTarefas.layoutManager = LinearLayoutManager(this)

    }
    private fun atualizarListaTarefas(){
        val tarefaDAO = TarefaDAO(this)
        listaTarefas = tarefaDAO.listar()
        tarefaAdapter?.adicionarLista( listaTarefas )
    }

    override fun onStart() {
        super.onStart()
        atualizarListaTarefas()
    }

}