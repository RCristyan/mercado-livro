package com.mercadolivro.extension

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.enums.BookStatus
import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel

fun PostCustomerRequest.toCustomerModel() : CustomerModel {
    return CustomerModel(nome = this.nome, email = this.email, status = CustomerStatus.ATIVO)
}

fun PutCustomerRequest.toCustomerModel(previousValue: CustomerModel) : CustomerModel {
    return CustomerModel(
        id = previousValue.id,
        nome = this.nome,
        email = this.email,
        status = previousValue.status
    )
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
