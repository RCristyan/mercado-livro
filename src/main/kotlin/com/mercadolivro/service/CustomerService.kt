package com.mercadolivro.service

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val bookService: BookService
) {

    fun getAll(pageable: Pageable, nome: String?) : Page<CustomerModel> {
        nome?.let {
            return customerRepository.findByNomeContaining(pageable, it)
        }

        return customerRepository.findAll(pageable)
    }

    fun findById(id: Int) : CustomerModel {
        return customerRepository.findById(id).orElseThrow{
            NotFoundException("Cliente com id {${id}} não existe", "ML-0002")
        }
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

        customer.status = CustomerStatus.INATIVO
        customerRepository.save(customer)
    }

}