import React, { useState } from 'react'
import { useCartContext } from '../context/CartContext'
import { Link } from 'react-router-dom'
import { useEffect } from 'react';

const BookCard = ({ book }) => {
    console.log(typeof book.book_image);
    function dataURItoBlob(dataURI) {
        const byteString = atob(dataURI.split(',')[1]);
        const mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];
        const ab = new ArrayBuffer(byteString.length);
        const ia = new Uint8Array(ab);
      
        for (let i = 0; i < byteString.length; i++) {
          ia[i] = byteString.charCodeAt(i);
        }
      
        return new Blob([ab], { type: mimeString });
      }
    
    const { addToCart } = useCartContext()
    return (
        <div class="col-md-3">
            <div class="product-item">
                <figure className="product-style flex flex-row justify-center p-[5%] xl:h-[450px]  lg:h-[300px] md:h-[220px] ">
                    <Link to={'/books/' + book.id}>
                    {/* <img className='object-cover max-h-[100%]' src={imageURL} alt="Books" class="product-item" /> */}

                        <img className='object-cover max-h-[100%]' src={`data:image/jpeg;base64,${book.book_image}`} alt="Books" class="product-item" />
                    </Link>
                    <button onClick={() => addToCart(book)} type="button" class="add-to-cart" data-product-tile="add-to-cart">Thêm vào giỏ hàng</button>
                </figure>
                <Link to={'/books/' + book.id}>
                    <figcaption className='px-4 mt-2 mb-4 h-full'>
                        <div>
                            <h3 className='line-clamp-2'>{book.book_name}</h3>
                            {/* <span>{book.author.author_name}</span> */}
                        </div>
                        <div className='w-full mt-auto'>
                            <span className="item-price">{ Intl.NumberFormat('vi-VN',{style:'currency',currency:'VND'}).format(book.book_price)}</span>
                        </div>
                    </figcaption>
                </Link>
            </div>
        </div>
    )
}

export default BookCard