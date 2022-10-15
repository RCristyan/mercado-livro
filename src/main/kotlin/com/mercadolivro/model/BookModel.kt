package com.mercadolivro.model

import com.mercadolivro.enums.BookStatus
import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "book")
data class BookModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var nome: String,

    @Column
    var preco: BigDecimal,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: CustomerModel? = null

){
    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null
        set(value) {
            if(field == BookStatus.CANCELADO || field == BookStatus.CANCELADO)
                throw Exception("ERRO: Não é possível alterar um livro com status $field")

            field = value
        }

    constructor(
        id: Int? = null,
        nome: String,
        preco: BigDecimal,
        customer: CustomerModel? = null,
        status: BookStatus?
    ): this(id, nome, preco, customer) {
        this.status = status
    }
}
