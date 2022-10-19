package com.mercadolivro.enums

enum class Errors(val code: String, val message: String) {

    ML001("ML-001", "Requisição inválida"),

    ML101("ML-101", "Livro com id {%s} não existe"),
    ML102("ML-102", "Não é possível alterar um livro com status {%s}"),

    ML201("ML-201", "Cliente com id {%s} não existe")

}
