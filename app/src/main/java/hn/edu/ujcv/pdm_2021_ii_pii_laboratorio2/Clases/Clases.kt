package hn.edu.ujcv.pdm_2021_ii_p2_laboratorio2.Clases

import android.os.Parcel
import android.os.Parcelable

class Clases() : Parcelable {
    var codigo   = 0
    var nombre   = ""
    var seccion  = ""
    var hora     = ""
    var edificio = ""
    var aula     = ""
    var n1       = 0.0
    var n2       = 0.0
    var n3       = 0.0

    constructor(parcel: Parcel) : this() {
        codigo = parcel.readInt()
        nombre = parcel.readString().toString()
        seccion = parcel.readString().toString()
        hora = parcel.readString().toString()
        edificio = parcel.readString().toString()
        aula = parcel.readString().toString()
        n1 = parcel.readDouble()
        n2 = parcel.readDouble()
        n3 = parcel.readDouble()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(codigo)
        parcel.writeString(nombre)
        parcel.writeString(seccion)
        parcel.writeString(hora)
        parcel.writeString(edificio)
        parcel.writeString(aula)
        parcel.writeDouble(n1)
        parcel.writeDouble(n2)
        parcel.writeDouble(n3)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Clases> {
        override fun createFromParcel(parcel: Parcel): Clases {
            return Clases(parcel)
        }

        override fun newArray(size: Int): Array<Clases?> {
            return arrayOfNulls(size)
        }

        var Clases: HashMap<Int, Clases> = hashMapOf()
    }

    fun imprimirNotas(): String{
        return "Clase: $nombre\nNota Primer Parcial: $n1\nNota Segundo Parcial: $n2\nNota Tercer Parcial: $n3\n"
    }

    override fun toString(): String {
        return "Codigo : ${codigo}\nNombre: $nombre\nSeccion: $seccion\nHora: $hora\nEdificio: $edificio\nAula: $aula"
    }

}
