package com.mercadolivro.extension

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.model.CustomerModel

fun PostCustomerRequest.toCustomerModel() : CustomerModel {
    return CustomerModel(nome = this.nome, email = this.email)
}
