package com.mercadolivro.service

import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val bookService: BookService
) {

    fun getAll(nome : String?) : List<CustomerModel> {
        nome?.let {
            return customerRepository.findByNomeContaining(it)
        }

        return customerRepository.findAll().toList()
    }

    fun findById(id: Int) : CustomerModel {
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
        val customer = findById(id)

        bookService.deleteByCustomer(customer)
        customerRepository.deleteById(id)
    }

}