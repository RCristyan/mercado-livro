package com.mercadolivro.service

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.model.CustomerModel
import org.springframework.stereotype.Service

@Service
class CustomerService {

    val customers = mutableListOf<CustomerModel>()

    fun getAll(nome : String?) : List<CustomerModel> {
        nome?.let {
            return customers.filter { it.nome.contains(nome, ignoreCase = true) }
        }
        return customers
    }

    fun getCustomer(id: String) : CustomerModel {
        return customers.first { it.id == id }
    }

    fun createCustomer(customer : CustomerModel) {
        val id = if(customers.isEmpty()){
            1
        } else {
            customers.last().id.toInt() + 1
        }.toString()

        customers.add(customer)
    }

    fun update(id: String, customer : PutCustomerRequest) {
        customers.first { it.id == id }.let {
            it.nome = customer.nome
            it.email = customer.email
        }
    }

    fun delete(id: String) {
        customers.removeIf { it.id == id }
    }

}