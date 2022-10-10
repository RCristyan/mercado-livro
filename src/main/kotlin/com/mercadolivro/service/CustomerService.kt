package com.mercadolivro.service

import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository
) {

    fun getAll(nome : String?) : List<CustomerModel> {
        nome?.let {
            return customerRepository.findByNomeContaining(it)
        }

        return customerRepository.findAll().toList()
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
        if(!customerRepository.existsById(id)){
            throw Exception()
        }

        customerRepository.deleteById(id)
    }

}