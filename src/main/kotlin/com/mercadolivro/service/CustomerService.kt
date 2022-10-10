package com.mercadolivro.service

import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository
) {

    val customers = mutableListOf<CustomerModel>()

    fun getAll(nome : String?) : List<CustomerModel> {
        nome?.let {
            return customers.filter { it.nome.contains(nome, ignoreCase = true) }
        }
        return customers
    }

    fun getCustomer(id: Int) : CustomerModel {
        return customerRepository.findById(id).orElseThrow()
    }

    fun createCustomer(customer : CustomerModel) {
        customerRepository.save(customer)
    }

    fun update(customer : CustomerModel) {
        if(!customerRepository.existsById(customer.id!!)){
            throw Exception()
        }

        customerRepository.save(customer)
    }

    fun delete(id: Int) {
        customers.removeIf { it.id == id }
    }

}