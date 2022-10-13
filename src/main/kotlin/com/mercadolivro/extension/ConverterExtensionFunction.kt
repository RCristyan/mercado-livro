package com.mercadolivro.extension

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.enums.BookStatus
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel

fun PostCustomerRequest.toCustomerModel() : CustomerModel {
    return CustomerModel(nome = this.nome, email = this.email)
}

fun PutCustomerRequest.toCustomerModel(id: Int) : CustomerModel {
    return CustomerModel(id = id, nome = this.nome, email = this.email)
}

fun PostBookRequest.toBookModel(customer: CustomerModel): BookModel {
    return BookModel(
        nome = this.nome,
        preco = this.preco,
        status = BookStatus.ATIVO,
        customer = customer
    )
}

fun PutBookRequest.toBookModel(previousValue: BookModel): BookModel {
    return BookModel(
        id = previousValue.id,
        nome = this.nome ?: previousValue.nome,
        preco = this.preco ?: previousValue.preco,
        status = previousValue.status,
        customer = previousValue.customer
    )
}
