package edu.spring.td1.model

import kotlin.collections.HashSet

class Category {

    var categoryNom : String? = null
    var items : HashSet<Item> = HashSet()

    companion object {
        fun genererListeParDefaut(): HashSet<Category> {
            val categories = HashSet<Category>()

            val category1 = Category().apply {
                categoryNom = "Category 1"
                items.add(Item().apply { nom = "Item 1"; evaluation = 5 })
                items.add(Item().apply { nom = "Item 2"; evaluation = 4 })
            }
            categories.add(category1)

            val category2 = Category().apply {
                categoryNom = "Category 2"
                items.add(Item().apply { nom = "Item 3"; evaluation = 3 })
                items.add(Item().apply { nom = "Item 4"; evaluation = 2 })
            }
            categories.add(category2)

            return categories
        }

    }

}