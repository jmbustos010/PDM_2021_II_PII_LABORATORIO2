package hn.edu.ujcv.pdm_2021_ii_pii_laboratorio2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import hn.edu.ujcv.pdm_2021_ii_p2_laboratorio2.Clases.Alumnos
import hn.edu.ujcv.pdm_2021_ii_p2_laboratorio2.Clases.Clases
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    var alumnosEnviados: HashMap<Int, Alumnos>? = hashMapOf()
    var valoresClases2 : HashMap<Int, Clases>? = hashMapOf()
    var matriculasAgregadas: HashMap<Alumnos, ArrayList<Clases>> = hashMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        incializar()
        btnAlumno.setOnClickListener { alumno() }
        btnMatricula.setOnClickListener { matricula() }
        btnClases.setOnClickListener { clases() }
        btnNotas.setOnClickListener { notas() }
        btnEnviarMatricula.setOnClickListener { enviarMatricula() }
        btnEnviarNotas.setOnClickListener { enviarNotas() }
    }

    private fun incializar(){
        alumnosEnviados = Alumnos.Alumnos
        valoresClases2 = Clases.Clases
        matriculasAgregadas = Alumnos.Matriculas
    }

    private fun clases() {
        val intent = Intent(this, ClasesActivity::class.java)
        startActivity(intent)
    }

    private fun alumno(){
        val intent = Intent(this,AlumnoActivity::class.java)
        startActivity(intent)
    }
    private fun notas(){
        val intent = Intent(this,NotasActivity::class.java)
        intent.putExtra("matriculasAgregadas",matriculasAgregadas)
        startActivity(intent)
    }

    private fun enviarNotas() {
        val intent = Intent(this,CorreoNotasActivity::class.java)
        startActivity(intent)
    }

    private fun enviarMatricula() {
        val intent = Intent(this,CorreoActivity::class.java)
        startActivity(intent)
    }


    private fun matricula(){
        val intent = Intent(this, MatriculaActivity::class.java)
        intent.putExtra("alumnosEnviados", alumnosEnviados)
        intent.putExtra("valoresClases2" , valoresClases2)
        startActivity(intent)
    }

}