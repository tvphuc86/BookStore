import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { useCartContext } from '../../context/CartContext'
import BookCard from '../BookCard'

const ListBook = ({books}) => {

    const {addToCart} = useCartContext()

    
    

    return (
        <div class="tab-content">
            <div id="all-genre" data-tab-content className='active'>
                <div class="row">
                    {books.map((book) => {
                        return (
                            <BookCard book={book}></BookCard>
                        )
                    })}
                </div>

            </div>
        </div>

    )
}

export default ListBook