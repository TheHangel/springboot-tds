package edu.spring.td1.model

import java.util.HashSet

class Item {
    var nom: String? = null
    var evaluation : Int = 0

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if(o !is Item) return false
        val item = o as Item
        return if (evaluation != item.evaluation) false else nom == item.nom
    }

    override fun hashCode(): Int {
        return nom.hashCode()
    }

    companion object {
        fun findByNameFromList(name:String, items: HashSet<Item?>):Item?{
            return items.find { name == it!!.nom }
        }
    }

}
